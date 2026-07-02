package ai.selflabs.selfshell.mock.harmony

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MockHarmonyMeshClientTest {

    private val client = MockHarmonyMeshClient()

    @Test
    fun listAvailableTasks_returnsThreePreviewTasks() = runTest {
        assertEquals(3, client.listAvailableTasks().size)
    }

    @Test
    fun getNodeStatus_beforeConnect_isOffline() = runTest {
        val status = client.getNodeStatus()

        assertFalse(status.isConnected)
        assertEquals("mock-node-local", status.nodeId)
    }

    @Test
    fun connectMock_marksNodeConnected() = runTest {
        client.connectMock()

        assertTrue(client.getNodeStatus().isConnected)
    }

    @Test
    fun submitMockProof_returnsReceiptForTask() = runTest {
        val receipt = client.submitMockProof("mock-1")

        assertEquals("proof-mock-1", receipt.id)
        assertTrue(receipt.message.contains("mock"))
    }

    @Test
    fun getMockEarnings_includesPreviewDisclaimer() = runTest {
        val earnings = client.getMockEarnings()

        assertTrue(earnings.amountDisplay.contains("mock"))
        assertTrue(earnings.disclaimer.contains("Preview only"))
    }
}
