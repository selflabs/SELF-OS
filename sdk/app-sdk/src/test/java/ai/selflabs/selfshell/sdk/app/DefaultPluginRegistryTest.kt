package ai.selflabs.selfshell.sdk.app

import android.content.Context
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock

class DefaultPluginRegistryTest {

    private val registry = DefaultPluginRegistry()
    private val context: Context = mock(Context::class.java)

    @Test
    fun registerApp_listsManifest() {
        registry.registerApp(stubApp("app-1"))

        assertEquals(1, registry.listPlugins().size)
        assertEquals("app-1", registry.listPlugins().first().id)
    }

    @Test
    fun registerApp_replacesSameId() {
        var stopCount = 0
        registry.registerApp(stubApp("app-1", onStop = { stopCount++ }))
        registry.registerApp(stubApp("app-1"))

        assertEquals(1, registry.listPlugins().size)
        assertEquals(1, stopCount)
    }

    @Test
    fun launchApp_unknownId_fails() {
        val result = registry.launchApp(context, "missing")

        assertTrue(result.isFailure)
    }

    @Test
    fun launchApp_registeredApp_succeeds() {
        var launched = false
        registry.registerApp(stubApp("app-1", onLaunch = { launched = true }))

        val result = registry.launchApp(context, "app-1")

        assertTrue(result.isSuccess)
        assertTrue(launched)
    }

    @Test
    fun launchApp_throwingApp_fails() {
        registry.registerApp(
            stubApp("app-1", onLaunch = { throw IllegalStateException("boom") })
        )

        val result = registry.launchApp(context, "app-1")

        assertTrue(result.isFailure)
    }

    @Test
    fun uninstallApp_removesAndStops() {
        var stopped = false
        registry.registerApp(stubApp("app-1", onStop = { stopped = true }))

        registry.uninstallApp("app-1")

        assertTrue(stopped)
        assertNull(registry.getApp("app-1"))
        assertTrue(registry.listPlugins().isEmpty())
    }

    @Test
    fun unregisterApp_removesAndStops() {
        var stopped = false
        registry.registerApp(stubApp("app-1", onStop = { stopped = true }))

        registry.unregisterApp("app-1")

        assertTrue(stopped)
        assertFalse(registry.listPlugins().isNotEmpty())
    }

    @Test
    fun getApp_returnsRegisteredInstance() {
        val app = stubApp("app-1")
        registry.registerApp(app)

        assertNotNull(registry.getApp("app-1"))
        assertEquals("app-1", registry.getApp("app-1")?.getManifest()?.id)
    }

    private fun stubApp(
        id: String,
        onLaunch: () -> Unit = {},
        onStop: () -> Unit = {}
    ): SelfApp = object : SelfApp {
        override fun getManifest(): SelfAppManifest = SelfAppManifest(
            id = id,
            name = "Test App",
            description = "Stub",
            version = "1.0.0",
            author = "tests",
            icon = "ic_test",
            category = "Demo",
            permissions = emptySet(),
            entryPoint = "stub"
        )

        override fun launch(context: Context) = onLaunch()

        override fun stop() = onStop()
    }
}
