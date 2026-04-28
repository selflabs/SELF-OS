package ai.selflabs.selfshell.examples.agents.hello

import ai.selflabs.selfshell.sdk.agent.Agent
import ai.selflabs.selfshell.sdk.agent.AgentManifest
import ai.selflabs.selfshell.sdk.agent.AgentPermission
import ai.selflabs.selfshell.sdk.agent.AgentRequest
import ai.selflabs.selfshell.sdk.agent.AgentResponse
import ai.selflabs.selfshell.sdk.agent.AgentRunStatus

/**
 * Example agent: greeting and short product explanation. No network.
 */
class HelloAgent : Agent {

    override fun getManifest(): AgentManifest = AgentManifest(
        id = "hello-agent",
        name = "Hello SELF Shell",
        description = "Greets you and explains SELF Shell Community Edition.",
        version = "1.0.0",
        author = "SELF Shell examples",
        permissions = setOf(AgentPermission.LOCAL_STORAGE),
        entryPoint = "ai.selflabs.selfshell.examples.agents.hello.HelloAgent"
    )

    override suspend fun initialize() {
        // no-op
    }

    override suspend fun handleRequest(request: AgentRequest): AgentResponse {
        val body = """
            Hi there — this is the Hello agent running locally in SELF Shell.
            
            SELF Shell is the open developer layer for building agents and apps on your device.
            It does not connect to live Harmony Mesh, wallets, or rewards.
            
            You said: "${request.input.trim()}"
        """.trimIndent()

        return AgentResponse(
            requestId = request.id,
            output = body,
            status = AgentRunStatus.OK,
            metadata = mapOf("agent" to getManifest().id)
        )
    }

    override suspend fun shutdown() {
        // no-op
    }
}
