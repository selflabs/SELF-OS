package ai.selflabs.selfshell.mock.aura

data class MockDataCategory(
    val id: String,
    val title: String,
    val description: String
)

data class ConsentPreference(
    val categoryId: String,
    val allowed: Boolean
)

data class MockValueEstimate(
    val categoryId: String,
    val displayValue: String,
    val note: String
)
