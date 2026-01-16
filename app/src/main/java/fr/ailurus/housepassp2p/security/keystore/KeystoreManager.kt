package fr.ailurus.housepassp2p.security.keystore

interface KeystoreManager {

    /**
     * Initialise the vault :
     * - Generate a random MasterKey (DEK) (32 bytes).
     * - Generate a random Salt.
     * - Derive KEK from [pin] + Salt.
     * - Encrypt DEK with KEK.
     * - Save the result (Salt + IV + EncryptedDEK).
     *
     * @param pin The user password as raw bytes (allows memory wiping).
     * @return The cleartext DEK ready to initialize SQLCipher.
     */
    fun createMasterKey(pin: ByteArray): ByteArray

    /**
     * Tries to fetch MasterKey (DEK) :
     * - Read the encrypted blob from storage.
     * - Extract Salt and IV.
     * - Derive KEK from [pin] + Salt.
     * - Decrypt the blob.
     *
     * @param pin The user password as raw bytes.
     * @throws java.io.FileNotFoundException If the key file doesn't exist (Vault not setup).
     * @throws java.security.GeneralSecurityException If decryption fails (Wrong PIN).
     * @return The decrypted DEK.
     */
    fun getMasterKey(pin: ByteArray): ByteArray
}