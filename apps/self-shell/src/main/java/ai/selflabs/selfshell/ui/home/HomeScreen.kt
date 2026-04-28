package ai.selflabs.selfshell.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ai.selflabs.selfshell.SelfShellApplication
import ai.selflabs.selfshell.mock.harmony.MeshNodeStatus
import ai.selflabs.selfshell.mock.wallet.MockBalance
import ai.selflabs.selfshell.ui.components.MockPreviewBanner
import ai.selflabs.selfshell.ui.components.PreviewDisclaimer
@Composable
fun HomeScreen(app: SelfShellApplication) {
    var node by remember { mutableStateOf<MeshNodeStatus?>(null) }
    var balance by remember { mutableStateOf<MockBalance?>(null) }

    LaunchedEffect(Unit) {
        app.harmonyMesh.connectMock()
        node = app.harmonyMesh.getNodeStatus()
        balance = app.wallet.getMockBalance()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "SELF Shell Community Edition",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Welcome to your local personal intelligence shell.",
            style = MaterialTheme.typography.bodyMedium
        )
        MockPreviewBanner()

        Card(colors = CardDefaults.cardColors()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Local agents", style = MaterialTheme.typography.titleMedium)
                Text("${app.agentRuntime.listAgents().size} example agents registered (on-device).")
            }
        }

        Card(colors = CardDefaults.cardColors()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Installed SELF Apps", style = MaterialTheme.typography.titleMedium)
                Text("${app.pluginRegistry.listPlugins().size} demo apps in the local registry.")
            }
        }

        Card(colors = CardDefaults.cardColors()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Mock mesh status", style = MaterialTheme.typography.titleMedium)
                node?.let {
                    Text("Node: ${it.displayLabel}")
                    Text("Connected (mock): ${it.isConnected}")
                } ?: Text("Loading preview…")
            }
        }

        Card(colors = CardDefaults.cardColors()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Mock rewards balance", style = MaterialTheme.typography.titleMedium)
                balance?.let {
                    Text(it.amountDisplay)
                    PreviewDisclaimer()
                } ?: Text("Loading preview…")
            }
        }
    }
}
