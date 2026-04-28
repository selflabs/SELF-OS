package ai.selflabs.selfshell.mock.harmony

import kotlinx.coroutines.delay

class MockHarmonyMeshClient : HarmonyMeshClient {

    private var connected = false

    override suspend fun getNodeStatus(): MeshNodeStatus {
        return MeshNodeStatus(
            nodeId = "mock-node-local",
            displayLabel = "Preview node (offline)",
            isConnected = connected,
            previewNote = "Mock / Preview Only. No live Harmony Mesh traffic."
        )
    }

    override suspend fun listAvailableTasks(): List<MockMeshTask> = listOf(
        MockMeshTask("mock-1", "Organize notes", "Locally simulated task for UI preview."),
        MockMeshTask("mock-2", "Summarize document", "Deterministic placeholder — no network."),
        MockMeshTask("mock-3", "Plan your day", "Educational example only.")
    )

    override suspend fun submitMockProof(taskId: String): MockProofReceipt {
        delay(50)
        return MockProofReceipt(
            id = "proof-$taskId",
            message = "Mock proof recorded locally. No chain or live-network verifier."
        )
    }

    override suspend fun getMockEarnings(): MockMeshEarnings = MockMeshEarnings(
        label = "Preview only",
        amountDisplay = "0 ESSENCE (mock)",
        disclaimer = "Preview only. No real earnings are generated in SELF OS Personal Intelligence (Community Edition)."
    )

    override suspend fun connectMock() {
        delay(80)
        connected = true
    }
}
