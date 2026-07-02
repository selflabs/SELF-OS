package ai.selflabs.selfshell.examples.agents

import ai.selflabs.selfshell.examples.agents.hello.HelloAgent
import ai.selflabs.selfshell.examples.agents.notes.LocalNotesAgent
import ai.selflabs.selfshell.examples.agents.planner.TaskPlannerAgent
import ai.selflabs.selfshell.sdk.agent.AgentRequest
import ai.selflabs.selfshell.sdk.agent.AgentRunStatus
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleAgentsTest {

    private val notesAgent = LocalNotesAgent()

    @After
    fun tearDown() {
        runBlocking { notesAgent.shutdown() }
    }

    @Test
    fun helloAgent_echoesUserInput() = runTest {
        val agent = HelloAgent()
        val request = AgentRequest("req-1", "  testing  ", emptyMap(), 0L)

        val response = agent.handleRequest(request)

        assertEquals(AgentRunStatus.OK, response.status)
        assertTrue(response.output.contains("testing"))
        assertTrue(response.output.contains("SELF OS Personal Intelligence"))
    }

    @Test
    fun taskPlannerAgent_blankGoalUsesDefault() = runTest {
        val agent = TaskPlannerAgent()
        val request = AgentRequest("req-2", "   ", emptyMap(), 0L)

        val response = agent.handleRequest(request)

        assertEquals(AgentRunStatus.OK, response.status)
        assertTrue(response.output.contains("your goal"))
        assertTrue(response.output.contains("1."))
    }

    @Test
    fun localNotesAgent_addAndList() = runTest {
        val add = notesAgent.handleRequest(AgentRequest("r1", "add Buy milk", emptyMap(), 0L))
        assertEquals(AgentRunStatus.OK, add.status)
        assertTrue(add.output.contains("Buy milk"))

        val list = notesAgent.handleRequest(AgentRequest("r2", "list", emptyMap(), 0L))
        assertTrue(list.output.contains("Buy milk"))
    }

    @Test
    fun localNotesAgent_clearRemovesNotes() = runTest {
        notesAgent.handleRequest(AgentRequest("r1", "add temp", emptyMap(), 0L))
        notesAgent.handleRequest(AgentRequest("r2", "clear", emptyMap(), 0L))

        val list = notesAgent.handleRequest(AgentRequest("r3", "list", emptyMap(), 0L))
        assertTrue(list.output.contains("No notes yet"))
    }

    @Test
    fun localNotesAgent_bareAdd_returnsHelp() = runTest {
        val response = notesAgent.handleRequest(AgentRequest("r1", "add", emptyMap(), 0L))

        assertEquals(AgentRunStatus.OK, response.status)
        assertTrue(response.output.contains("Commands"))
    }
}
