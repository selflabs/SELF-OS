package ai.selflabs.selfshell.mock.wallet

data class MockBalance(
    val amountDisplay: String,
    val disclaimer: String
)

data class MockTransaction(
    val id: String,
    val label: String,
    val amountDisplay: String
)

data class MockPayoutRequest(
    val reference: String,
    val message: String
)
