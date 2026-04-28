package ai.selflabs.selfshell.mock.aura

interface AuraDataClient {
    suspend fun listMockDataCategories(): List<MockDataCategory>
    suspend fun updateConsentPreference(pref: ConsentPreference)
    suspend fun estimateMockDataValue(categoryId: String): MockValueEstimate
    fun snapshotConsent(): Map<String, Boolean>
}
