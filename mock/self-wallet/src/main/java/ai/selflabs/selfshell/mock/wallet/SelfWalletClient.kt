package ai.selflabs.selfshell.mock.wallet

interface SelfWalletClient {
    suspend fun getMockBalance(): MockBalance
    suspend fun getMockTransactions(): List<MockTransaction>
    suspend fun requestMockPayout(amountDisplay: String): MockPayoutRequest
    suspend fun connectMockWallet()
}
