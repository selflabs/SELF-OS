package ai.selflabs.selfshell.examples.apps.hello

import android.content.Context
import android.widget.Toast
import ai.selflabs.selfshell.sdk.app.SelfApp
import ai.selflabs.selfshell.sdk.app.SelfAppManifest

class HelloSelfApp : SelfApp {

    override fun getManifest(): SelfAppManifest = SelfAppManifest(
        id = "hello-self-app",
        name = "Hello SELF App",
        description = "Minimal example SELF App — shows a toast.",
        version = "1.0.0",
        author = "SELF OS Personal Intelligence examples",
        icon = "ic_hello",
        category = "Demo",
        permissions = emptySet(),
        entryPoint = "ai.selflabs.selfshell.examples.apps.hello.HelloSelfApp"
    )

    override fun launch(context: Context) {
        Toast.makeText(
            context,
            "Hello SELF OS Personal Intelligence — this app runs locally with no backend.",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun stop() {}
}
