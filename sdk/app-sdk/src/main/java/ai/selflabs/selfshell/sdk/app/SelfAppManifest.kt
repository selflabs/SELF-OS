package ai.selflabs.selfshell.sdk.app

data class SelfAppManifest(
    val id: String,
    val name: String,
    val description: String,
    val version: String,
    val author: String,
    val icon: String,
    val category: String,
    val permissions: Set<String>,
    val entryPoint: String
)
