package ai.selflabs.selfshell.mock.aura

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MockAuraDataClient : AuraDataClient {

    private val mutex = Mutex()
    private val consent = mutableMapOf(
        "cat-usage" to false,
        "cat-diagnostics" to false
    )

    override suspend fun listMockDataCategories(): List<MockDataCategory> = listOf(
        MockDataCategory(
            id = "cat-usage",
            title = "Product usage patterns",
            description = "Educational toggle only — no monetization routes in Community Edition."
        ),
        MockDataCategory(
            id = "cat-diagnostics",
            title = "Diagnostics (mock)",
            description = "Preview consent UX. Available in SELF OS Core for real programs."
        )
    )

    override suspend fun updateConsentPreference(pref: ConsentPreference) {
        mutex.withLock { consent[pref.categoryId] = pref.allowed }
    }

    override suspend fun estimateMockDataValue(categoryId: String): MockValueEstimate =
        MockValueEstimate(
            categoryId = categoryId,
            displayValue = "$0.00 (mock)",
            note = "Preview only. Aura data monetization is not active in SELF Shell."
        )

    override fun snapshotConsent(): Map<String, Boolean> = consent.toMap()
}
