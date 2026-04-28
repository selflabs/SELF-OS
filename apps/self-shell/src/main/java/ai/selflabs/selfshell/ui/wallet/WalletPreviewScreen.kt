package ai.selflabs.selfshell.ui.wallet

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
import ai.selflabs.selfshell.mock.wallet.MockBalance
import ai.selflabs.selfshell.mock.wallet.MockPayoutRequest
import ai.selflabs.selfshell.mock.wallet.MockTransaction
import ai.selflabs.selfshell.ui.components.MockPreviewBanner
import ai.selflabs.selfshell.ui.components.PreviewDisclaimer
import kotlinx.coroutines.launch

@Composable
fun WalletPreviewScreen(app: SelfShellApplication) {
    val scope = rememberCoroutineScope()
    var balance by remember { mutableStateOf<MockBalance?>(null) }
    var txs by remember { mutableStateOf<List<MockTransaction>>(emptyList()) }
    var payout by remember { mutableStateOf<MockPayoutRequest?>(null) }

    LaunchedEffect(Unit) {
        app.wallet.connectMockWallet()
        balance = app.wallet.getMockBalance()
        txs = app.wallet.getMockTransactions()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Wallet preview", style = MaterialTheme.typography.headlineSmall)
        MockPreviewBanner()
        PreviewDisclaimer()
        Text(
            "Wallet signing, payments, and ESSENCE economics are part of SELF OS Core.",
            style = MaterialTheme.typography.bodyMedium
        )

        balance?.let {
            Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Mock balance", style = MaterialTheme.typography.titleMedium)
                    Text(it.amountDisplay)
                    Text(it.disclaimer, style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Button(
            onClick = {
                scope.launch {
                    payout = app.wallet.requestMockPayout("10 ESSENCE (mock)")
                }
            }
        ) { Text("Request mock payout") }

        payout?.let {
            Text("${it.reference}: ${it.message}", style = MaterialTheme.typography.bodySmall)
        }

        Text("Mock transactions", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(txs, key = { it.id }) { tx ->
                Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors()) {
                    Column(Modifier.padding(16.dp)) {
                        Text(tx.label, style = MaterialTheme.typography.titleSmall)
                        Text(tx.amountDisplay, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
