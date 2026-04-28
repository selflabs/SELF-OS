package ai.selflabs.selfshell.mock.appstore

interface AppStoreClient {
    suspend fun listLocalApps(): List<CatalogApp>
    suspend fun installLocalApp(app: CatalogApp)
    suspend fun removeLocalApp(appId: String)
    suspend fun getFeaturedMockApps(): List<CatalogApp>
}
