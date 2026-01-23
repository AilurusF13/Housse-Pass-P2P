package fr.ailurus.housepassp2p.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.ailurus.housepassp2p.data.entities.Entry
import fr.ailurus.housepassp2p.data.entities.EntrySummary
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: Entry)

    @Insert
    suspend fun insertEntries(entries: List<Entry>)

    @Query("SELECT id, site_name, user_login, group_id FROM entries ORDER BY created_at DESC")
    fun getEntries(): Flow<List<EntrySummary>>

    //  Mostly for debug tests
    @Query("SELECT id, site_name, user_login, group_id FROM entries")
    suspend fun getAllOnce(): List<EntrySummary>

    @Query("SELECT password_blob FROM entries WHERE id = :id")
    suspend fun getPassword(id: Int): ByteArray?

    @Query("DELETE FROM entries WHERE id = :id")
    suspend fun deleteById(id: Int)
}
