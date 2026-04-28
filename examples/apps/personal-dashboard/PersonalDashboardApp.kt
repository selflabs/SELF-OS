package ai.selflabs.selfshell.examples.apps.dashboard

import android.content.Context
import android.widget.Toast
import ai.selflabs.selfshell.sdk.app.SelfApp
import ai.selflabs.selfshell.sdk.app.SelfAppManifest

/**
 * Example app: mock widget summary via Toast (no separate Activity).
 */
class PersonalDashboardApp : SelfApp {

    override fun getManifest(): SelfAppManifest = SelfAppManifest(
        id = "personal-dashboard",
        name = "Personal dashboard",
        description = "Mock widgets: focus, notes, agents — preview text only.",
        version = "1.0.0",
        author = "SELF Shell examples",
        icon = "ic_dashboard",
        category = "Productivity",
        permissions = emptySet(),
        entryPoint = "ai.selflabs.selfshell.examples.apps.dashboard.PersonalDashboardApp"
    )

    override fun launch(context: Context) {
        val summary = """
            Mock dashboard
            • Focus: Deep work 25m (sample)
            • Notes: 2 local items (sample)
            • Agents: 3 registered examples
            • Tasks: Preview only — no sync
        """.trimIndent()
        Toast.makeText(context, summary, Toast.LENGTH_LONG).show()
    }

    override fun stop() {}
}
