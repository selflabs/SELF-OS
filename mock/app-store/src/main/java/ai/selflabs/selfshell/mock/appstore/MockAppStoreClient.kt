package ai.selflabs.selfshell.mock.appstore

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MockAppStoreClient : AppStoreClient {

    private val mutex = Mutex()
    private val installed = linkedMapOf<String, CatalogApp>()

    override suspend fun listLocalApps(): List<CatalogApp> = mutex.withLock {
        installed.values.toList()
    }

    override suspend fun installLocalApp(app: CatalogApp) {
        mutex.withLock { installed[app.id] = app }
    }

    override suspend fun removeLocalApp(appId: String) {
        mutex.withLock { installed.remove(appId) }
    }

    override suspend fun getFeaturedMockApps(): List<CatalogApp> = listOf(
        CatalogApp(
            id = "feat-focus",
            name = "Focus timer",
            description = "Local-only sample listing — no purchase backend.",
            category = "Productivity"
        ),
        CatalogApp(
            id = "feat-journal",
            name = "Journal",
            description = "Educational mock catalog entry.",
            category = "Lifestyle"
        )
    )
}
