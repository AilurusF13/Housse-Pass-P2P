package fr.ailurus.housepassp2p.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.db.SupportSQLiteOpenHelper.Factory
import fr.ailurus.housepassp2p.data.dao.EntryDao
import fr.ailurus.housepassp2p.data.dao.GroupDao
import fr.ailurus.housepassp2p.data.entities.Entry
import fr.ailurus.housepassp2p.data.entities.Group
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Entry::class,
        Group::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao

    abstract fun groupDao(): GroupDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        // val passphrase =  UUID.randomUUID().toString().toByteArray() -> temporary passphrase to use in the calling method
        // factory = SupportFactory(passphrase)

        fun getDatabase(
            context: Context,
            factory: Factory,
        ): AppDatabase =
            instance ?: synchronized(this) {
                val instance =
                    Room
                        .databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "house-pass-db",
                        ).openHelperFactory(factory)
                        .addCallback(
                            object : Callback() {
                                override fun onCreate(connection: SQLiteConnection) {
                                    super.onCreate(connection)
                                    CoroutineScope(Dispatchers.IO).launch {
                                        instance?.let { db ->
                                            db.groupDao().insert(SELF_GROUP)

                                            // temporary for testing purposes DELETEME
                                            db.groupDao().insert(MOCK_GROUP_1)
                                            db.groupDao().insert(MOCK_GROUP_2)
                                            db.groupDao().insert(MOCK_GROUP_3)
                                            db.groupDao().insert(MOCK_GROUP_4)
                                            db.entryDao().insertEntries(PREPOPULATE_ENTRIES)
                                        }
                                    }
                                }
                            },
                        ).build()
                Companion.instance = instance
                instance
            }

        // TODO Default Name and Description should be edited in the future, maybe a bit more dependent to the system language
        val SELF_GROUP =
            Group(
                groupId = 1,
                name = "Personnel",
                description = "Coffre-fort privé local",
                passphrase = ByteArray(0),
            )

        // DELETEME
        val MOCK_GROUP_1 =
            Group(
                groupId = 2,
                name = "Projet L3 - HousePass",
                description = "Partage d'accès pour l'équipe de dev",
                passphrase = "key_dev_p2p_2026".toByteArray(),
            )

        val MOCK_GROUP_2 =
            Group(
                groupId = 3,
                name = "Famille",
                description = "Streaming, Wi-Fi et comptes communs",
                passphrase = "family_secret_key".toByteArray(),
            )

        val MOCK_GROUP_3 =
            Group(
                groupId = 4,
                name = "Gaming",
                description = "Steam, Epic Games et serveurs",
                passphrase = "gaming_access_only".toByteArray(),
            )

        val MOCK_GROUP_4 =
            Group(
                groupId = 5,
                name = "Services Cloud",
                description = "AWS, Azure et outils infra",
                passphrase = "cloud_ops_master".toByteArray(),
            )
        val PREPOPULATE_ENTRIES: List<Entry> =
            listOf(
                // Group 1: SELF_GROUP (Personnel)
                Entry(
                    site = "Banque Populaire",
                    login = "ailurus_88",
                    groupId = 1,
                    password = "pwd".toByteArray(),
                    timestamp = 1705200000000,
                ),
                Entry(site = "Impôts Gouv", login = "ailurus_fr", groupId = 1, password = "pwd".toByteArray(), timestamp = 1705200000001),
                Entry(site = "Doctolib", login = "0601020304", groupId = 1, password = "pwd".toByteArray(), timestamp = 1705200000002),
                Entry(
                    site = "Assurance Maladie",
                    login = "199010203040",
                    groupId = 1,
                    password = "pwd".toByteArray(),
                    timestamp = 1705200000003,
                ),
                // Group 2: MOCK_GROUP_1 (Projet L3)
                Entry(site = "GitHub", login = "ailurus-dev", groupId = 2, password = "pwd".toByteArray(), timestamp = 1705200000004),
                Entry(
                    site = "GitLab (Université)",
                    login = "etu_math_info",
                    groupId = 2,
                    password = "pwd".toByteArray(),
                    timestamp = 1705200000005,
                ),
                Entry(
                    site = "DigitalOcean",
                    login = "admin@housepass.io",
                    groupId = 2,
                    password = "pwd".toByteArray(),
                    timestamp = 1705200000006,
                ),
                Entry(site = "Trello Team", login = "dev_leader", groupId = 2, password = "pwd".toByteArray(), timestamp = 1705200000007),
                // Group 3: MOCK_GROUP_2 (Famille)
                Entry(
                    site = "Netflix",
                    login = "famille@orange.fr",
                    groupId = 3,
                    password = "pwd".toByteArray(),
                    timestamp = 1705200000008,
                ),
                Entry(
                    site = "Disney+",
                    login = "famille@orange.fr",
                    groupId = 3,
                    password = "pwd".toByteArray(),
                    timestamp = 1705200000009,
                ),
                Entry(
                    site = "Wi-Fi Maison",
                    login = "Livebox-A4B2",
                    groupId = 3,
                    password = "pwd".toByteArray(),
                    timestamp = 1705200000010,
                ),
                Entry(
                    site = "Amazon Prime",
                    login = "maman_account",
                    groupId = 3,
                    password = "pwd".toByteArray(),
                    timestamp = 1705200000011,
                ),
                // Group 4: MOCK_GROUP_3 (Gaming)
                Entry(site = "Steam", login = "GamerZ_99", groupId = 4, password = "pwd".toByteArray(), timestamp = 1705200000012),
                Entry(site = "Epic Games", login = "ailurus_play", groupId = 4, password = "pwd".toByteArray(), timestamp = 1705200000013),
                Entry(site = "Battle.net", login = "healer_main", groupId = 4, password = "pwd".toByteArray(), timestamp = 1705200000014),
                Entry(site = "Discord", login = "ailurus#1337", groupId = 4, password = "pwd".toByteArray(), timestamp = 1705200000015),
                // Group 5: MOCK_GROUP_4 (Services Cloud)
                Entry(site = "AWS Console", login = "root_user", groupId = 5, password = "pwd".toByteArray(), timestamp = 1705200000016),
                Entry(
                    site = "Azure Portal",
                    login = "student@azure.com",
                    groupId = 5,
                    password = "pwd".toByteArray(),
                    timestamp = 1705200000017,
                ),
                Entry(site = "Heroku", login = "deploy_bot", groupId = 5, password = "pwd".toByteArray(), timestamp = 1705200000018),
                Entry(site = "Cloudflare", login = "dns_admin", groupId = 5, password = "pwd".toByteArray(), timestamp = 1705200000019),
            )
    }
}
