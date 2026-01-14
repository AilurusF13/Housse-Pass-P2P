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

    @Query("SELECT id, site_name, user_login, group_id FROM 'entries' ORDER BY created_at")
    fun getEntries(): Flow<List<EntrySummary>>

    @Query("SELECT password_blob FROM 'entries' WHERE id = :id")
    suspend fun getPassword(id: Int): CharArray?

    @Query("DELETE FROM 'entries' WHERE id = :id")
    suspend fun deleteById(id: Int)
}
