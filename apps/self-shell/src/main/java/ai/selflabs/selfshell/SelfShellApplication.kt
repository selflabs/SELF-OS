package ai.selflabs.selfshell

import android.app.Application
import ai.selflabs.selfshell.mock.appstore.AppStoreClient
import ai.selflabs.selfshell.mock.appstore.MockAppStoreClient
import ai.selflabs.selfshell.mock.aura.AuraDataClient
import ai.selflabs.selfshell.mock.aura.MockAuraDataClient
import ai.selflabs.selfshell.mock.harmony.HarmonyMeshClient
import ai.selflabs.selfshell.mock.harmony.MockHarmonyMeshClient
import ai.selflabs.selfshell.mock.wallet.MockSelfWalletClient
import ai.selflabs.selfshell.mock.wallet.SelfWalletClient
import ai.selflabs.selfshell.sdk.agent.AgentRuntime
import ai.selflabs.selfshell.sdk.agent.DefaultAgentRuntime
import ai.selflabs.selfshell.sdk.app.DefaultPluginRegistry
import ai.selflabs.selfshell.sdk.app.PluginRegistry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow

enum class ThemeChoice {
    SYSTEM,
    LIGHT,
    DARK
}

class SelfShellApplication : Application() {

    internal val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    val themeChoice = MutableStateFlow(ThemeChoice.SYSTEM)

    val agentRuntime: AgentRuntime = DefaultAgentRuntime()
    val pluginRegistry: PluginRegistry = DefaultPluginRegistry()
    val harmonyMesh: HarmonyMeshClient = MockHarmonyMeshClient()
    val wallet: SelfWalletClient = MockSelfWalletClient()
    val appStore: AppStoreClient = MockAppStoreClient()
    val aura: AuraDataClient = MockAuraDataClient()

    override fun onCreate() {
        super.onCreate()
        ShellBootstrap.start(this)
    }
}
