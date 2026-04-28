package ai.selflabs.selfshell.sdk.agent

data class AgentManifest(
    val id: String,
    val name: String,
    val description: String,
    val version: String,
    val author: String,
    val permissions: Set<AgentPermission>,
    val entryPoint: String
)
