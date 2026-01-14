package fr.ailurus.housepassp2p.data.dao

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.ailurus.housepassp2p.data.AppDatabase
import fr.ailurus.housepassp2p.data.entities.Group
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: GroupDao

    companion object {
        @BeforeClass
        @JvmStatic
        fun loadNativeLibraries() {
            try {
                System.loadLibrary("sqlcipher")
            } catch (e: UnsatisfiedLinkError) {
                // Log critical error if native lib is missing in test env
                Log.e("DAO_TEST", "Error lib not found : $e")
            }
        }
    }

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // Simulating encrypted DB environment
        val factory = SupportOpenHelperFactory("test_key".toByteArray())

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .openHelperFactory(factory)
            .allowMainThreadQueries() // Allowed only for testing
            .build()
        dao = db.groupDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    // --- TEST 1: Conflict Strategy (ABORT) ---
    @Test(expected = SQLiteConstraintException::class)
    fun insertDuplicateId_shouldThrowException() = runBlocking {
        val group1 = Group(groupId = 1, name = "G1", description = "D1", passphrase = "p1".toByteArray())
        val group2 = Group(groupId = 1, name = "G2", description = "D2", passphrase = "p2".toByteArray())

        dao.insert(group1)
        dao.insert(group2)
    }

    // --- TEST 2: ByteArray Integrity ---
    @Test
    fun getPassphrase_shouldReturnExactByteArray() = runBlocking {
        val secret = byteArrayOf(0x00, 0x01, 0xFF.toByte(), 0x42)
        val group = Group(name = "Secret", description = "Encrypted", passphrase = secret)

        dao.insert(group)

        // Fetch to get the auto-generated ID
        val groups = dao.getGroups().first()
        val generatedId = groups[0].groupId

        val retrieved = dao.getPassphrase(generatedId)

        assertNotNull(retrieved)
        assertArrayEquals("Retrieved bytes must match inserted bytes", secret, retrieved)
    }

    // --- TEST 3: Flow Reactivity ---
    @Test
    fun getGroups_shouldEmitNewListOnInsert() = runBlocking {
        val initialList = dao.getGroups().first()
        assertTrue("Initial list should be empty", initialList.isEmpty())

        val group = Group(name = "Work", description = "Work groups", passphrase = "123".toByteArray())
        dao.insert(group)

        val updatedList = dao.getGroups().first()
        assertEquals(1, updatedList.size)
        assertEquals("Work", updatedList[0].name)
    }

    // --- TEST 4: Robustness (Delete non-existent) ---
    @Test
    fun deleteNonExistentId_shouldNotCrash() = runBlocking {
        dao.deleteById(999)
    }

    // --- TEST 5: Edge Case - Empty passphrase ---
    @Test
    fun insertGroupWithEmptyPassphrase_shouldWork() = runBlocking {
        val emptyPass = byteArrayOf()
        val group = Group(name = "Empty", description = "No secret", passphrase = emptyPass)

        dao.insert(group)
        val groups = dao.getGroups().first()

        val result = dao.getPassphrase(groups[0].groupId)
        assertNotNull(result)
        assertEquals(0, result?.size)
    }

    // --- TEST 6: Data Integrity (UTF-8) ---
    @Test
    fun insertGroupWithSpecialCharacters_shouldPreserveEncoding() = runBlocking {
        val nameWithEmoji = "Work 🚀 & Études"
        val group = Group(name = nameWithEmoji, description = "Test", passphrase = "p".toByteArray())

        dao.insert(group)
        val result = dao.getGroups().first()

        assertEquals(nameWithEmoji, result[0].name)
    }

    // --- TEST 7: Sorting Logic ---
    @Test
    fun getGroups_shouldBeSortedChronologically() = runBlocking {
        val now = System.currentTimeMillis()

        // Arrange: Prepare data with distinct timestamps
        val groupOld = Group(name = "Old", description = "First",
            passphrase = "1".toByteArray(), timestamp = now - 10000)
        val groupNew = Group(name = "New", description = "Last",
            passphrase = "2".toByteArray(), timestamp = now)
        val groupMid = Group(name = "Mid", description = "Middle",
            passphrase = "3".toByteArray(), timestamp = now - 5000)

        // Act: Insert in RANDOM order
        dao.insert(groupNew)
        dao.insert(groupOld)
        dao.insert(groupMid)

        // Assert: Verify retrieval order (ASC by default in DAO)
        val list = dao.getGroups().first()

        assertEquals(3, list.size)
        assertEquals("Old", list[0].name)
        assertEquals("Mid", list[1].name)
        assertEquals("New", list[2].name)
    }

    // Need to apply UI related limits to strings
}