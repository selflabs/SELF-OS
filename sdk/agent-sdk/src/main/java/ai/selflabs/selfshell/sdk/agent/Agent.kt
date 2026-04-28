package ai.selflabs.selfshell.sdk.agent

interface Agent {
    fun getManifest(): AgentManifest
    suspend fun initialize()
    suspend fun handleRequest(request: AgentRequest): AgentResponse
    suspend fun shutdown()
}
