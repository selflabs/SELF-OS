package ai.selflabs.selfshell.mock.aura

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MockAuraDataClientTest {

    private val client = MockAuraDataClient()

    @Test
    fun listMockDataCategories_returnsEducationalSamples() = runTest {
        val categories = client.listMockDataCategories()

        assertEquals(2, categories.size)
        assertTrue(categories.any { it.id == "cat-usage" })
        assertTrue(categories.any { it.id == "cat-diagnostics" })
    }

    @Test
    fun updateConsentPreference_persistsInSnapshot() = runTest {
        client.updateConsentPreference(ConsentPreference("cat-usage", true))

        val snapshot = client.snapshotConsent()
        assertTrue(snapshot["cat-usage"] == true)
        assertFalse(snapshot["cat-diagnostics"] == true)
    }

    @Test
    fun estimateMockDataValue_returnsPreviewOnlyCopy() = runTest {
        val estimate = client.estimateMockDataValue("cat-usage")

        assertEquals("cat-usage", estimate.categoryId)
        assertTrue(estimate.displayValue.contains("mock", ignoreCase = true))
        assertTrue(estimate.note.contains("Preview only", ignoreCase = true))
    }

    @Test
    fun snapshotConsent_startsWithDefaultsOff() {
        val snapshot = client.snapshotConsent()

        assertEquals(false, snapshot["cat-usage"])
        assertEquals(false, snapshot["cat-diagnostics"])
    }
}
