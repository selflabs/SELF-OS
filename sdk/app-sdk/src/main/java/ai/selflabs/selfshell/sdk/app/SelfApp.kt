package ai.selflabs.selfshell.sdk.app

import android.content.Context

interface SelfApp {
    fun getManifest(): SelfAppManifest
    fun launch(context: Context)
    fun stop()
}
