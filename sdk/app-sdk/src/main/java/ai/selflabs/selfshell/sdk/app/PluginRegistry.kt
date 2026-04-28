package ai.selflabs.selfshell.sdk.app

interface PluginRegistry {
    fun registerApp(app: SelfApp)
    fun unregisterApp(appId: String)
    fun listPlugins(): List<SelfAppManifest>
    fun launchApp(context: android.content.Context, id: String): Result<Unit>
    fun uninstallApp(id: String)
    fun getApp(id: String): SelfApp?
}
