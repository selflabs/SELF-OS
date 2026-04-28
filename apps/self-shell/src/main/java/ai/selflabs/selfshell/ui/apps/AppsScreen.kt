package ai.selflabs.selfshell.ui.apps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ai.selflabs.selfshell.SelfShellApplication
import ai.selflabs.selfshell.mock.appstore.CatalogApp
import kotlinx.coroutines.launch

@Composable
fun AppsScreen(app: SelfShellApplication) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var catalog by remember { mutableStateOf<List<CatalogApp>>(emptyList()) }
    var installedCatalog by remember { mutableStateOf<List<CatalogApp>>(emptyList()) }
    val plugins = app.pluginRegistry.listPlugins()

    suspend fun refresh() {
        catalog = app.appStore.getFeaturedMockApps()
        installedCatalog = app.appStore.listLocalApps()
    }

    LaunchedEffect(Unit) { refresh() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Apps", style = MaterialTheme.typography.headlineSmall)
        Text(
            "Launch demo SELF Apps or install entries from the local mock catalog.",
            style = MaterialTheme.typography.bodyMedium
        )

        Text("SELF App registry", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(plugins, key = { it.id }) { m ->
                Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors()) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(m.name, style = MaterialTheme.typography.titleMedium)
                        Text(m.description, style = MaterialTheme.typography.bodySmall)
                        Button(onClick = { app.pluginRegistry.launchApp(context, m.id) }) {
                            Text("Launch")
                        }
                    }
                }
            }
        }

        Text("Mock app store (local)", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(catalog, key = { it.id }) { item ->
                Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors()) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(item.name, style = MaterialTheme.typography.titleMedium)
                        Text(item.description, style = MaterialTheme.typography.bodySmall)
                        Button(
                            onClick = {
                                scope.launch {
                                    app.appStore.installLocalApp(item)
                                    refresh()
                                }
                            }
                        ) { Text("Add to local catalog") }
                    }
                }
            }
        }

        if (installedCatalog.isNotEmpty()) {
            Text("Installed from mock catalog", style = MaterialTheme.typography.titleMedium)
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(installedCatalog, key = { it.id }) { item ->
                    Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors()) {
                        RowBetween(item.name) {
                            Button(
                                onClick = {
                                    scope.launch {
                                        app.appStore.removeLocalApp(item.id)
                                        refresh()
                                    }
                                }
                            ) { Text("Remove") }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RowBetween(title: String, actions: @Composable () -> Unit) {
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        actions()
    }
}
