package fr.ailurus.housepassp2p.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.ailurus.housepassp2p.data.entities.Group
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(group: Group)

    @Query("SELECT group_id, group_name, group_description FROM `groups` ORDER BY created_at ASC")
    fun getGroups(): Flow<List<GroupSummary>>

    @Query("SELECT group_passphrase FROM `groups` WHERE group_id = :groupId")
    suspend fun getPassphrase(groupId: Int): ByteArray?

    @Update
    suspend fun update(group: Group)

    @Query("DELETE FROM `groups` WHERE group_id = :groupId")
    suspend fun deleteById(groupId: Int)
}
