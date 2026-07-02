package ai.selflabs.selfshell.sdk.agent

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class DefaultAgentRuntimeTest {

    private val runtime = DefaultAgentRuntime()

    @Test
    fun registerAgent_listsManifest() = runTest {
        runtime.registerAgent(StubAgent("stub-1", "hello"))

        val manifests = runtime.listAgents()
        assertEquals(1, manifests.size)
        assertEquals("stub-1", manifests.first().id)
    }

    @Test
    fun sendRequest_returnsAgentOutput() = runTest {
        runtime.registerAgent(StubAgent("stub-1", "pong"))

        val response = runtime.sendRequest("stub-1", "ping")

        assertEquals(AgentRunStatus.OK, response.status)
        assertEquals("pong", response.output)
        assertTrue(response.requestId.isNotEmpty())
    }

    @Test
    fun sendRequest_unknownAgent_returnsError() = runTest {
        val response = runtime.sendRequest("missing", "hi")

        assertEquals(AgentRunStatus.ERROR, response.status)
        assertTrue(response.output.contains("missing"))
    }

    @Test
    fun unregisterAgent_removesAgent() = runTest {
        runtime.registerAgent(StubAgent("stub-1", "ok"))
        runtime.unregisterAgent("stub-1")

        assertNull(runtime.getAgent("stub-1"))
        assertTrue(runtime.listAgents().isEmpty())
    }

    @Test
    fun registerAgent_replacesExistingId() = runTest {
        runtime.registerAgent(StubAgent("stub-1", "first"))
        runtime.registerAgent(StubAgent("stub-1", "second"))

        val response = runtime.sendRequest("stub-1", "ping")
        assertEquals("second", response.output)
    }

    @Test
    fun sendMessageToAgent_delegatesToSendRequest() = runTest {
        runtime.registerAgent(StubAgent("stub-1", "reply"))

        val response = runtime.sendMessageToAgent("stub-1", "msg")

        assertEquals(AgentRunStatus.OK, response.status)
        assertEquals("reply", response.output)
    }

    @Test
    fun stopAgent_removesAndShutsDown() = runTest {
        runtime.registerAgent(StubAgent("stub-1", "ok"))
        runtime.stopAgent("stub-1")

        assertNotNull(runtime)
        assertNull(runtime.getAgent("stub-1"))
    }

    private class StubAgent(
        private val id: String,
        private val reply: String
    ) : Agent {
        override fun getManifest(): AgentManifest = AgentManifest(
            id = id,
            name = "Stub",
            description = "Test agent",
            version = "1.0.0",
            author = "tests",
            permissions = emptySet(),
            entryPoint = "stub"
        )

        override suspend fun initialize() {}

        override suspend fun handleRequest(request: AgentRequest): AgentResponse =
            AgentResponse(request.id, reply, AgentRunStatus.OK, emptyMap())

        override suspend fun shutdown() {}
    }
}
