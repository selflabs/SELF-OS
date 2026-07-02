package ai.selflabs.selfshell.mock.appstore

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MockAppStoreClientTest {

    private val client = MockAppStoreClient()

    @Test
    fun getFeaturedMockApps_returnsSamples() = runTest {
        val featured = client.getFeaturedMockApps()

        assertEquals(2, featured.size)
        assertTrue(featured.any { it.id == "feat-focus" })
    }

    @Test
    fun installLocalApp_appearsInList() = runTest {
        val app = CatalogApp("test-app", "Test", "Desc", "Tools")
        client.installLocalApp(app)

        val installed = client.listLocalApps()
        assertEquals(1, installed.size)
        assertEquals("test-app", installed.first().id)
    }

    @Test
    fun removeLocalApp_dropsFromList() = runTest {
        val app = CatalogApp("test-app", "Test", "Desc", "Tools")
        client.installLocalApp(app)
        client.removeLocalApp("test-app")

        assertTrue(client.listLocalApps().isEmpty())
    }
}
