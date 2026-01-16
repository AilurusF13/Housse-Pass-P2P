package fr.ailurus.housepassp2p.security.keystore

import android.content.Context
import java.io.File
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class JavaKeystoreSystem(private val context: Context) : KeystoreManager {

    private val keyfile = File(context.filesDir, "vault_key.enc")

    // Crypto constants
    private val gcmTagLen = 128
    private val keyLength = 256
    private val dekLength = 32
    private val saltLength = 16
    private val ivLength = 12
    private val iterations = 100_000

    override fun createMasterKey(pin: ByteArray): ByteArray {

        val dek = ByteArray(dekLength).apply { SecureRandom().nextBytes(this) }

        val salt = ByteArray(saltLength).apply { SecureRandom().nextBytes(this) }

        val kek = deriveKey(pin, salt)
        pin.fill(0)

        // encryption
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, kek)
        val iv = cipher.iv
        val encryptedDek = cipher.doFinal(dek)

        // saving salt  + iv + encrypted bytes
        val output = salt + iv + encryptedDek
        keyfile.writeBytes(output)

        return dek
    }

    override fun getMasterKey(pin: ByteArray): ByteArray {
        if (!keyfile.exists()) throw java.io.FileNotFoundException("No vault key found")
        val fileBytes = keyfile.readBytes()

        // extract pieces
        val salt = fileBytes.copyOfRange(0, saltLength)
        val iv = fileBytes.copyOfRange(saltLength, saltLength + ivLength)
        val encryptedDek = fileBytes.copyOfRange(saltLength + ivLength, fileBytes.size)

        // find kek
        val kek = deriveKey(pin, salt)
        pin.fill(0)

        // decryption
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val spec = GCMParameterSpec(gcmTagLen, iv)
        cipher.init(Cipher.DECRYPT_MODE, kek, spec)

        return cipher.doFinal(encryptedDek)
    }

    /**
     * Derive a key from a password and a salt.
     * Transforms the weak limited digits pin code to a strong key
     **/
    private fun deriveKey(pin: ByteArray, salt: ByteArray): SecretKeySpec {

        val charPin = pin.map {it.toInt().toChar()}.toCharArray() // conversion char array vers byte array ? pourquoi faire

        val spec = PBEKeySpec(charPin, salt, iterations, keyLength)

        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val keyBytes = factory.generateSecret(spec).encoded

        charPin.fill(Char(0)) // char Pin Is No Longer used
        spec.clearPassword()

        return SecretKeySpec(keyBytes, "AES")
    }
}