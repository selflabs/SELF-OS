package ai.selflabs.selfshell.ui.agents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ai.selflabs.selfshell.SelfShellApplication
import ai.selflabs.selfshell.sdk.agent.AgentRunStatus
import kotlinx.coroutines.launch

data class ChatLine(val role: String, val text: String)

@Composable
fun AgentChatScreen(
    app: SelfShellApplication,
    agentId: String,
    onBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val lines = remember { mutableStateListOf<ChatLine>() }
    var input by remember { mutableStateOf("") }
    val name = app.agentRuntime.getAgent(agentId)?.getManifest()?.name ?: agentId

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(name, style = MaterialTheme.typography.headlineSmall)
        Text(
            "Messages are handled locally by the agent. No cloud agent infrastructure.",
            style = MaterialTheme.typography.bodySmall
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(lines) { line ->
                Text("${line.role}: ${line.text}", style = MaterialTheme.typography.bodyMedium)
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Message") }
            )
            Button(
                onClick = {
                    val msg = input.trim()
                    if (msg.isEmpty()) return@Button
                    lines.add(ChatLine("You", msg))
                    input = ""
                    scope.launch {
                        val res = app.agentRuntime.sendRequest(agentId, msg)
                        val prefix = if (res.status == AgentRunStatus.OK) "Agent" else "Agent (error)"
                        lines.add(ChatLine(prefix, res.output))
                    }
                }
            ) { Text("Send") }
        }
        Button(onClick = onBack) { Text("Back") }
    }
}
