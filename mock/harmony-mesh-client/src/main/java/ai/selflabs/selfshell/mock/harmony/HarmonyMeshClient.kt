package ai.selflabs.selfshell.mock.harmony

interface HarmonyMeshClient {
    suspend fun getNodeStatus(): MeshNodeStatus
    suspend fun listAvailableTasks(): List<MockMeshTask>
    suspend fun submitMockProof(taskId: String): MockProofReceipt
    suspend fun getMockEarnings(): MockMeshEarnings
    suspend fun connectMock()
}
