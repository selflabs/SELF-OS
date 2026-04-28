package ai.selflabs.selfshell.sdk.agent

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DefaultAgentRuntime : AgentRuntime {

    private val agents = ConcurrentHashMap<String, Agent>()
    private val mutex = Mutex()

    override suspend fun registerAgent(agent: Agent) {
        mutex.withLock {
            val id = agent.getManifest().id
            agents[id]?.shutdown()
            agents[id] = agent
            agent.initialize()
        }
    }

    override suspend fun unregisterAgent(agentId: String) {
        mutex.withLock {
            agents.remove(agentId)?.shutdown()
        }
    }

    override fun listAgents(): List<AgentManifest> =
        agents.values.map { it.getManifest() }

    override fun getAgent(id: String): Agent? = agents[id]

    override suspend fun sendRequest(
        agentId: String,
        input: String,
        context: Map<String, String>
    ): AgentResponse {
        val agent = agents[agentId]
            ?: return AgentResponse(
                requestId = "",
                output = "Agent not found: $agentId",
                status = AgentRunStatus.ERROR,
                metadata = emptyMap()
            )
        val request = AgentRequest(
            id = UUID.randomUUID().toString(),
            input = input,
            context = context,
            createdAtEpochMs = System.currentTimeMillis()
        )
        return try {
            agent.handleRequest(request)
        } catch (e: Exception) {
            AgentResponse(
                requestId = request.id,
                output = e.message ?: "error",
                status = AgentRunStatus.ERROR,
                metadata = mapOf("exception" to (e::class.simpleName ?: "Exception"))
            )
        }
    }

    override suspend fun stopAgent(agentId: String) {
        mutex.withLock {
            agents.remove(agentId)?.shutdown()
        }
    }

    override suspend fun sendMessageToAgent(agentId: String, message: String): AgentResponse =
        sendRequest(agentId, message, emptyMap())
}
