package ai.selflabs.selfshell.ui.mesh

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
import androidx.compose.ui.unit.dp
import ai.selflabs.selfshell.SelfShellApplication
import ai.selflabs.selfshell.mock.harmony.MockMeshTask
import ai.selflabs.selfshell.mock.harmony.MeshNodeStatus
import ai.selflabs.selfshell.mock.harmony.MockMeshEarnings
import ai.selflabs.selfshell.mock.harmony.MockProofReceipt
import ai.selflabs.selfshell.ui.components.MockPreviewBanner
import ai.selflabs.selfshell.ui.components.PreviewDisclaimer
import kotlinx.coroutines.launch

@Composable
fun MeshPreviewScreen(app: SelfShellApplication) {
    val scope = rememberCoroutineScope()
    var status by remember { mutableStateOf<MeshNodeStatus?>(null) }
    var tasks by remember { mutableStateOf<List<MockMeshTask>>(emptyList()) }
    var earnings by remember { mutableStateOf<MockMeshEarnings?>(null) }
    var lastProof by remember { mutableStateOf<MockProofReceipt?>(null) }

    LaunchedEffect(Unit) {
        app.harmonyMesh.connectMock()
        status = app.harmonyMesh.getNodeStatus()
        tasks = app.harmonyMesh.listAvailableTasks()
        earnings = app.harmonyMesh.getMockEarnings()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Mesh preview", style = MaterialTheme.typography.headlineSmall)
        MockPreviewBanner()
        PreviewDisclaimer()
        Text(
            "Harmony Mesh live-network routing is available in SELF OS Core.",
            style = MaterialTheme.typography.bodyMedium
        )

        status?.let {
            Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Node status", style = MaterialTheme.typography.titleMedium)
                    Text(it.displayLabel)
                    Text("Connected (mock): ${it.isConnected}")
                }
            }
        }

        Text("Mock available tasks", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tasks, key = { it.id }) { t ->
                Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors()) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(t.title, style = MaterialTheme.typography.titleSmall)
                        Text(t.summary, style = MaterialTheme.typography.bodySmall)
                        Button(
                            onClick = {
                                scope.launch {
                                    lastProof = app.harmonyMesh.submitMockProof(t.id)
                                }
                            }
                        ) { Text("Submit mock proof") }
                    }
                }
            }
        }

        earnings?.let {
            Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Mock earnings", style = MaterialTheme.typography.titleMedium)
                    Text(it.amountDisplay)
                    Text(it.disclaimer, style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        lastProof?.let {
            Text("Last mock proof: ${it.message}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
