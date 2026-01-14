package fr.ailurus.housepassp2p.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "entries",
    foreignKeys = [
        ForeignKey(
            entity = Group::class,
            parentColumns = ["groupId"],
            childColumns = ["groupId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["created_at"], orders=[Index.Order.DESC])
    ]
)
data class Entry(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "site_name")
    val site: String,

    @ColumnInfo(name = "user_login")
    val login: String,

    @ColumnInfo(name = "password_blob")
    val password: ByteArray,

    @ColumnInfo(name = "group_id")
    val groupId: Int,

    @ColumnInfo(name = "created_at")
    val timestamp: Long = System.currentTimeMillis()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (this != other) return false

        other as Entry

        if (id != other.id) return false
        if (groupId != other.groupId) return false
        if (timestamp != other.timestamp) return false
        if (site != other.site) return false
        if (login != other.login) return false
        if (!password.contentEquals(other.password)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + groupId
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + site.hashCode()
        result = 31 * result + login.hashCode()
        result = 31 * result + password.contentHashCode()
        return result
    }
}

/**
 * Projection to use and display entries without loading the password in memory for security purposes
 */
data class EntrySummary(
    @ColumnInfo(name="id")          val id: Int,
    @ColumnInfo(name="site_name")   val site: String,
    @ColumnInfo(name="user_login")  val login: String,
    @ColumnInfo(name="group_id")    val groupId: Int,
    @ColumnInfo(name="created_at")  val timestamp: Long
)