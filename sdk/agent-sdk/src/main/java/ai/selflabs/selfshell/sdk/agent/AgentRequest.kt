package ai.selflabs.selfshell.sdk.agent

data class AgentRequest(
    val id: String,
    val input: String,
    val context: Map<String, String>,
    val createdAtEpochMs: Long
)
