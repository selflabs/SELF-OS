package ai.selflabs.selfshell.examples.agents.planner

import ai.selflabs.selfshell.sdk.agent.Agent
import ai.selflabs.selfshell.sdk.agent.AgentManifest
import ai.selflabs.selfshell.sdk.agent.AgentPermission
import ai.selflabs.selfshell.sdk.agent.AgentRequest
import ai.selflabs.selfshell.sdk.agent.AgentResponse
import ai.selflabs.selfshell.sdk.agent.AgentRunStatus

/**
 * Example agent: trivial on-device task breakdown. No network.
 */
class TaskPlannerAgent : Agent {

    override fun getManifest(): AgentManifest = AgentManifest(
        id = "task-planner-agent",
        name = "Task planner",
        description = "Turns a goal into a short local checklist.",
        version = "1.0.0",
        author = "SELF Shell examples",
        permissions = setOf(AgentPermission.LOCAL_STORAGE, AgentPermission.FILE_READ),
        entryPoint = "ai.selflabs.selfshell.examples.agents.planner.TaskPlannerAgent"
    )

    override suspend fun initialize() {}

    override suspend fun handleRequest(request: AgentRequest): AgentResponse {
        val goal = request.input.trim().ifBlank { "your goal" }
        val lines = listOf(
            "Clarify success: what does \"done\" look like for \"$goal\"?",
            "List 3 concrete steps (each under 30 minutes).",
            "Do step 1 now; note blockers.",
            "Review and adjust the plan after step 1."
        )
        val output = buildString {
            appendLine("Simple plan (local preview):")
            lines.forEachIndexed { i, line -> appendLine("${i + 1}. $line") }
        }

        return AgentResponse(
            requestId = request.id,
            output = output.trim(),
            status = AgentRunStatus.OK,
            metadata = mapOf("agent" to getManifest().id)
        )
    }

    override suspend fun shutdown() {}
}
