package ai.selflabs.selfshell.mock.harmony

data class MeshNodeStatus(
    val nodeId: String,
    val displayLabel: String,
    val isConnected: Boolean,
    val previewNote: String
)

data class MockMeshTask(
    val id: String,
    val title: String,
    val summary: String
)

data class MockProofReceipt(
    val id: String,
    val message: String
)

data class MockMeshEarnings(
    val label: String,
    val amountDisplay: String,
    val disclaimer: String
)
