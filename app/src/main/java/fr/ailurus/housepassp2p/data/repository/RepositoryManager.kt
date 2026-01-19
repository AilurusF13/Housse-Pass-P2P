package fr.ailurus.housepassp2p.data.repository

import fr.ailurus.housepassp2p.data.entities.Entry
import fr.ailurus.housepassp2p.data.entities.EntrySummary
import fr.ailurus.housepassp2p.data.entities.Group
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import kotlinx.coroutines.flow.Flow

interface RepositoryManager {

    // VAULT ACCESS

    /**
     * Creates and saves the master key for the application.
     * This should be called during the initial setup.
     * @param pin The master key to save.
     */
    suspend fun setupVault(pin: ByteArray)

    /**
     * Checks if the provided master key is correct and open the vault for use
     * @param pin The master key to check.
     * @return True if the master key is correct, false otherwise.
     */
    suspend fun openVault(pin:  ByteArray): Boolean

    // GROUP OPERATIONS

    /**
     * Creates a new group in the database.
     * @param group The group to create.
     */
    suspend fun createGroup(group: Group)

    /**
     * @return a flow of all group summaries.
     */
    fun getGroups(): Flow<List<GroupSummary>>

    /**
     * @param group The group to fetch the passphrase for.
     * @return The passphrase for the group.
     */
    suspend fun getPassphrase(group: GroupSummary): ByteArray

    /**
     * Delete a group from database.
     * @param group The group summary to delete.
     */
    suspend fun deleteGroup(group: GroupSummary)

    // ENTRY OPERATIONS

    /**
     * @return a flow of all entry summaries.
     */
    fun getEntries(): Flow<List<EntrySummary>>

    /**
     * Saves an entry to the database.
     * This will either insert a new entry or update an existing one.
     * @param entry The entry summary to save.
     */
    suspend fun saveEntry(entry : Entry)

    /**
     * Saves a list of entries to the database.
     * This will either insert new entries or update existing ones.
     * @param entries The list of entries to save.
     */
    suspend fun saveEntries(entries : List<Entry>)

    /**
     * @param entry The entry to fetch the password for.
     * @return The password for the entry.
     */
    suspend fun getPassword(entry: EntrySummary): ByteArray

    /**
     * Deletes an entry from the database.
     * @param entry The entry to delete.
     */
    suspend fun deleteEntry(entry: EntrySummary)
}

