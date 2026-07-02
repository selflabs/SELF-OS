package ai.selflabs.selfshell.mock.wallet

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MockSelfWalletClientTest {

    private val client = MockSelfWalletClient()

    @Test
    fun getMockBalance_beforeConnect_showsZero() = runTest {
        val balance = client.getMockBalance()

        assertTrue(balance.amountDisplay.startsWith("0 ESSENCE"))
    }

    @Test
    fun connectMockWallet_updatesBalance() = runTest {
        client.connectMockWallet()

        val balance = client.getMockBalance()
        assertTrue(balance.amountDisplay.contains("42 ESSENCE"))
    }

    @Test
    fun getMockTransactions_returnsPreviewRows() = runTest {
        val txs = client.getMockTransactions()

        assertEquals(2, txs.size)
        assertTrue(txs.first().label.contains("mock"))
    }

    @Test
    fun requestMockPayout_returnsNonEmptyMessage() = runTest {
        val payout = client.requestMockPayout("10 ESSENCE (mock)")

        assertTrue(payout.reference.startsWith("mock-payout-"))
        assertTrue(payout.message.contains("Mock payout"))
    }
}
