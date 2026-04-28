package ai.selflabs.selfshell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ai.selflabs.selfshell.ui.agents.AgentChatScreen
import ai.selflabs.selfshell.ui.agents.AgentsScreen
import ai.selflabs.selfshell.ui.apps.AppsScreen
import ai.selflabs.selfshell.ui.home.HomeScreen
import ai.selflabs.selfshell.ui.mesh.MeshPreviewScreen
import ai.selflabs.selfshell.ui.settings.SettingsScreen
import ai.selflabs.selfshell.ui.theme.SelfShellTheme
import ai.selflabs.selfshell.ui.wallet.WalletPreviewScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as SelfShellApplication
        setContent {
            val themeChoice by app.themeChoice.collectAsStateWithLifecycle(initialValue = ThemeChoice.SYSTEM)
            val systemDark = isSystemInDarkTheme()
            val dark = when (themeChoice) {
                ThemeChoice.SYSTEM -> systemDark
                ThemeChoice.LIGHT -> false
                ThemeChoice.DARK -> true
            }
            SelfShellTheme(darkTheme = dark) {
                val nav = rememberNavController()
                val backStack by nav.currentBackStackEntryAsState()
                val route = backStack?.destination?.route ?: Routes.Home
                val hideBar = route.startsWith("chat/")
                Scaffold(
                    bottomBar = {
                        if (!hideBar) {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = route == Routes.Home,
                                    onClick = {
                                        nav.navigate(Routes.Home) {
                                            launchSingleTop = true
                                        }
                                    },
                                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                                    label = { Text("Home") }
                                )
                                NavigationBarItem(
                                    selected = route == Routes.Agents,
                                    onClick = { nav.navigate(Routes.Agents) },
                                    icon = { Icon(Icons.Default.PhoneAndroid, contentDescription = null) },
                                    label = { Text("Agents") }
                                )
                                NavigationBarItem(
                                    selected = route == Routes.Apps,
                                    onClick = { nav.navigate(Routes.Apps) },
                                    icon = { Icon(Icons.Default.GridView, contentDescription = null) },
                                    label = { Text("Apps") }
                                )
                                NavigationBarItem(
                                    selected = route == Routes.Mesh,
                                    onClick = { nav.navigate(Routes.Mesh) },
                                    icon = { Icon(Icons.Default.Hub, contentDescription = null) },
                                    label = { Text("Mesh") }
                                )
                                NavigationBarItem(
                                    selected = route == Routes.Wallet,
                                    onClick = { nav.navigate(Routes.Wallet) },
                                    icon = { Icon(Icons.Default.AccountBalanceWallet, contentDescription = null) },
                                    label = { Text("Wallet") }
                                )
                                NavigationBarItem(
                                    selected = route == Routes.Settings,
                                    onClick = { nav.navigate(Routes.Settings) },
                                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                                    label = { Text("Settings") }
                                )
                            }
                        }
                    }
                ) { padding ->
                    NavHost(
                        navController = nav,
                        startDestination = Routes.Home,
                        modifier = Modifier.padding(padding)
                    ) {
                        composable(Routes.Home) { HomeScreen(app) }
                        composable(Routes.Agents) {
                            AgentsScreen(app, onOpenChat = { id -> nav.navigate("chat/$id") })
                        }
                        composable(Routes.Apps) { AppsScreen(app) }
                        composable(Routes.Mesh) { MeshPreviewScreen(app) }
                        composable(Routes.Wallet) { WalletPreviewScreen(app) }
                        composable(Routes.Settings) { SettingsScreen(app) }
                        composable("chat/{agentId}") { entry ->
                            val id = entry.arguments?.getString("agentId") ?: return@composable
                            AgentChatScreen(app, agentId = id, onBack = { nav.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}

private object Routes {
    const val Home = "home"
    const val Agents = "agents"
    const val Apps = "apps"
    const val Mesh = "mesh"
    const val Wallet = "wallet"
    const val Settings = "settings"
}
