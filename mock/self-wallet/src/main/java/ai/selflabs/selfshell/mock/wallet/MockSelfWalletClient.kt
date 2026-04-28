package ai.selflabs.selfshell.mock.wallet

import kotlinx.coroutines.delay

class MockSelfWalletClient : SelfWalletClient {

    private var linked = false

    override suspend fun getMockBalance(): MockBalance {
        delay(20)
        return MockBalance(
            amountDisplay = if (linked) "42 ESSENCE (mock)" else "0 ESSENCE (mock)",
            disclaimer = "Preview only. No real earnings are generated in SELF OS Personal Intelligence (Community Edition)."
        )
    }

    override suspend fun getMockTransactions(): List<MockTransaction> = listOf(
        MockTransaction("tx-mock-1", "Welcome bonus (mock)", "+10 ESSENCE"),
        MockTransaction("tx-mock-2", "Local task preview", "+0 ESSENCE")
    )

    override suspend fun requestMockPayout(amountDisplay: String): MockPayoutRequest {
        delay(40)
        return MockPayoutRequest(
            reference = "mock-payout-${System.currentTimeMillis()}",
            message = "Mock payout only. No funds move on-chain or to custodial wallets."
        )
    }

    override suspend fun connectMockWallet() {
        delay(60)
        linked = true
    }
}
