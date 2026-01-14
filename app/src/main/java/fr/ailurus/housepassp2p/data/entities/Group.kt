package fr.ailurus.housepassp2p.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "groups",
    indices = [
        Index(value = ["created_at"], orders = [Index.Order.ASC])
    ]
)
data class Group(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "group_id")
    val groupId: Int = 0,

    @ColumnInfo(name = "group_name")
    val name: String,

    @ColumnInfo(name = "group_description")
    val description: String,

    @ColumnInfo(name = "group_passphrase")
    val passphrase: ByteArray,

    @ColumnInfo(name="created_at")
    val timestamp: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Group) return false // Smart cast automatique

        if (groupId != other.groupId) return false
        if (name != other.name) return false
        if (description != other.description) return false

        return passphrase.contentEquals(other.passphrase)
    }

    override fun hashCode(): Int {
        var result = groupId
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + passphrase.contentHashCode()
        return result
    }
}

/**
 * Projection to use and display all Groups without loading the passphrase in memory for security purposes
 */
data class GroupSummary(
    @ColumnInfo(name = "group_id")          val groupId: Int,
    @ColumnInfo(name = "group_name")        val name: String,
    @ColumnInfo(name = "group_description") val description: String
)