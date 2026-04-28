package ai.selflabs.selfshell.ui.settings

import ai.selflabs.selfshell.SelfShellApplication
import ai.selflabs.selfshell.ThemeChoice
import ai.selflabs.selfshell.mock.aura.MockDataCategory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(app: SelfShellApplication) {
    var localMode by remember { mutableStateOf(true) }
    var devMode by remember { mutableStateOf(false) }
    val themeChoice by app.themeChoice.collectAsStateWithLifecycle(initialValue = ThemeChoice.SYSTEM)

    val categories = remember { mutableStateListOf<MockDataCategory>() }
    val consent = remember { mutableStateMapOf<String, Boolean>() }
    val estimates = remember { mutableStateMapOf<String, String>() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        categories.clear()
        categories.addAll(app.aura.listMockDataCategories())
        consent.clear()
        app.aura.snapshotConsent().forEach { (k, v) -> consent[k] = v }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineSmall)

        RowSwitch("Local-first mode (recommended)", localMode) { localMode = it }
        RowSwitch("Developer mode", devMode) { devMode = it }

        Text("Theme", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            TextButton(onClick = { app.themeChoice.value = ThemeChoice.SYSTEM }) {
                Text(if (themeChoice == ThemeChoice.SYSTEM) "System ✓" else "System")
            }
            TextButton(onClick = { app.themeChoice.value = ThemeChoice.LIGHT }) {
                Text(if (themeChoice == ThemeChoice.LIGHT) "Light ✓" else "Light")
            }
            TextButton(onClick = { app.themeChoice.value = ThemeChoice.DARK }) {
                Text(if (themeChoice == ThemeChoice.DARK) "Dark ✓" else "Dark")
            }
        }

        Text(
            "SELF Shell keeps agents and mock services on-device. " +
                "See docs/OPEN_SOURCE_BOUNDARY.md in the repo for what remains in SELF OS Core.",
            style = MaterialTheme.typography.bodyMedium
        )

        Text("Aura data (educational mock)", style = MaterialTheme.typography.titleMedium)
        Text(
            "Toggles are UI-only in Community Edition. Real Aura programs are part of SELF OS Core.",
            style = MaterialTheme.typography.bodySmall
        )

        categories.forEach { cat ->
            val checked = consent[cat.id] ?: false
            RowSwitch(cat.title, checked) { on ->
                consent[cat.id] = on
                scope.launch {
                    app.aura.updateConsentPreference(
                        ai.selflabs.selfshell.mock.aura.ConsentPreference(cat.id, on)
                    )
                }
            }
            Text(cat.description, style = MaterialTheme.typography.bodySmall)
            Button(
                onClick = {
                    scope.launch {
                        val e = app.aura.estimateMockDataValue(cat.id)
                        estimates[cat.id] = "${e.displayValue} — ${e.note}"
                    }
                }
            ) { Text("Mock value estimate") }
            estimates[cat.id]?.let { Text(it, style = MaterialTheme.typography.bodySmall) }
        }

        Text("Public safety", style = MaterialTheme.typography.titleMedium)
        Text(
            "Never paste private credentials, keys, or live-service URLs into forks of this tree. " +
                "Run scripts/scan-public-safety.ps1 before release.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun RowSwitch(label: String, checked: Boolean, onChecked: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
        Switch(checked = checked, onCheckedChange = onChecked)
    }
}
