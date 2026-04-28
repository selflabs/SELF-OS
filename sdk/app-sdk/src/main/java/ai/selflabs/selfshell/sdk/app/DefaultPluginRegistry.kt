package ai.selflabs.selfshell.sdk.app

import android.content.Context
import java.util.concurrent.ConcurrentHashMap

class DefaultPluginRegistry : PluginRegistry {

    private val apps = ConcurrentHashMap<String, SelfApp>()

    override fun registerApp(app: SelfApp) {
        apps[app.getManifest().id] = app
    }

    override fun unregisterApp(appId: String) {
        apps.remove(appId)?.stop()
    }

    override fun listPlugins(): List<SelfAppManifest> =
        apps.values.map { it.getManifest() }

    override fun launchApp(context: Context, id: String): Result<Unit> {
        val app = apps[id] ?: return Result.failure(IllegalStateException("Unknown app: $id"))
        return try {
            app.launch(context)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun uninstallApp(id: String) {
        apps.remove(id)?.stop()
    }

    override fun getApp(id: String): SelfApp? = apps[id]
}
