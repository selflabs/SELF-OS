package ai.selflabs.selfshell.sdk.agent

enum class AgentRunStatus {
    OK,
    ERROR,
    PENDING
}

data class AgentResponse(
    val requestId: String,
    val output: String,
    val status: AgentRunStatus,
    val metadata: Map<String, String>
)
