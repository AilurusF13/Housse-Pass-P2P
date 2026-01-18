package fr.ailurus.housepassp2p.data.repository

import android.content.Context
import android.util.Log
import fr.ailurus.housepassp2p.security.keystore.KeystoreManager
import fr.ailurus.housepassp2p.data.AppDatabase
import fr.ailurus.housepassp2p.data.entities.Entry
import fr.ailurus.housepassp2p.data.entities.EntrySummary
import fr.ailurus.housepassp2p.data.entities.Group
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import kotlinx.coroutines.flow.Flow

class RepositoryManagerImpl(
    private val context: Context,
    private val keystoreManager: KeystoreManager,
) : RepositoryManager {

    private var db: AppDatabase? = null

    override suspend fun setupVault(pin: ByteArray) {
        val dek = keystoreManager.createMasterKey(pin)
        db = AppDatabase.getDatabase(context, dek)
    }

    override suspend fun checkVaultKey(pin: ByteArray): Boolean {
        return try {
            val dek = keystoreManager.getMasterKey(pin)
            db = AppDatabase.getDatabase(context, dek)
            true
        } catch (e: Exception) {
            Log.e("AUTH", "Wrong PIN or corrupt key : ${e.message}")
            db = null
            false
        } finally {
            pin.fill(0)
        }
    }

    override suspend fun createGroup(group: Group) {
        db?.groupDao()?.insert(group)
            ?: throw IllegalStateException("Database not initialized. Cannot create group")
    }

    override fun getGroups(): Flow<List<GroupSummary>> {
        return db?.groupDao()?.getGroups()
            ?: throw IllegalStateException("Database not initialized. Cannot get groups")
    }

    override suspend fun getPassphrase(group: GroupSummary): ByteArray {
        return db?.groupDao()?.getPassphrase(group.groupId)
            ?: throw IllegalStateException("Database not initialized. Cannot get passphrase")
    }

    override suspend fun deleteGroup(group: GroupSummary) {
        db?.groupDao()?.deleteById(group.groupId)
            ?: throw IllegalStateException("Database not initialized. Cannot delete group")
    }

    override fun getEntries(): Flow<List<EntrySummary>> {
        return db?.entryDao()?.getEntries()
            ?: throw IllegalStateException("Database not initialized. Cannot get entries")
    }

    override suspend fun saveEntry(entry: Entry) {
        db?.entryDao()?.insert(entry)
            ?: throw IllegalStateException("Database not initialized. Cannot save entry")
    }

    override suspend fun saveEntries(entries: List<Entry>) {
        db?.entryDao()?.insertEntries(entries)
            ?: throw IllegalStateException("Database not initialized. Cannot save entries")
    }

    override suspend fun getPassword(entry: EntrySummary): ByteArray {
        return db?.entryDao()?.getPassword(entry.id)
            ?: throw IllegalStateException("Database not initialized. Cannot get password")
    }

    override suspend fun deleteEntry(entry: EntrySummary) {
        db?.entryDao()?.deleteById(entry.id)
            ?: throw IllegalStateException("Database not initialized. Cannot delete entry")
    }
}