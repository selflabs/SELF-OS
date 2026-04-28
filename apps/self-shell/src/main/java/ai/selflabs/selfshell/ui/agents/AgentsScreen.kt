package ai.selflabs.selfshell.ui.agents

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ai.selflabs.selfshell.SelfShellApplication
import ai.selflabs.selfshell.ui.components.PreviewDisclaimer

@Composable
fun AgentsScreen(
    app: SelfShellApplication,
    onOpenChat: (String) -> Unit
) {
    val agents = app.agentRuntime.listAgents()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Agents", style = MaterialTheme.typography.headlineSmall)
        Text(
            "Run and chat with agents entirely on-device in this Community Edition.",
            style = MaterialTheme.typography.bodyMedium
        )
        PreviewDisclaimer()
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(agents, key = { it.id }) { manifest ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors()
                ) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(manifest.name, style = MaterialTheme.typography.titleMedium)
                        Text(manifest.description, style = MaterialTheme.typography.bodySmall)
                        Button(onClick = { onOpenChat(manifest.id) }) {
                            Text("Chat (local / mock)")
                        }
                    }
                }
            }
        }
    }
}
