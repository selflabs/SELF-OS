import SwiftUI

struct WalletPreviewView: View {
    @EnvironmentObject private var env: AppEnvironment
    @State private var payoutMessage = ""

    var body: some View {
        NavigationStack {
            List {
                Section {
                    MockPreviewBanner()
                    PreviewDisclaimer()
                    Text("Wallet signing and ESSENCE economics are part of SELF OS Core.")
                        .font(.caption)
                }
                Section("Mock balance") {
                    Text(env.wallet.balance().amountDisplay)
                }
                Section {
                    Button("Request mock payout") {
                        Task { payoutMessage = await env.wallet.requestMockPayout() }
                    }
                    if !payoutMessage.isEmpty { Text(payoutMessage).font(.caption) }
                }
                Section("Mock transactions") {
                    ForEach(env.wallet.transactions()) { tx in
                        VStack(alignment: .leading) {
                            Text(tx.label)
                            Text(tx.amountDisplay).font(.caption)
                        }
                    }
                }
            }
            .navigationTitle("Wallet preview")
        }
    }
}
