package ai.selflabs.selfshell.sdk.agent

interface AgentRuntime {
    suspend fun registerAgent(agent: Agent)
    suspend fun unregisterAgent(agentId: String)
    fun listAgents(): List<AgentManifest>
    fun getAgent(id: String): Agent?
    suspend fun sendRequest(agentId: String, input: String, context: Map<String, String> = emptyMap()): AgentResponse
    suspend fun stopAgent(agentId: String)
    suspend fun sendMessageToAgent(agentId: String, message: String): AgentResponse
}
