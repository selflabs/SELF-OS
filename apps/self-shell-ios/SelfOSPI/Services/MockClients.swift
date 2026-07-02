import Foundation

@MainActor
final class MockHarmonyMeshClient: ObservableObject {
    @Published var connected = false

    func connectMock() async {
        try? await Task.sleep(nanoseconds: 80_000_000)
        connected = true
    }

    func nodeStatus() -> MeshNodeStatus {
        MeshNodeStatus(
            displayLabel: connected ? "Preview node (mock connected)" : "Preview node (offline)",
            isConnected: connected
        )
    }

    func listTasks() -> [MockMeshTask] {
        [
            MockMeshTask(id: "mock-1", title: "Organize notes", summary: "Locally simulated task for UI preview."),
            MockMeshTask(id: "mock-2", title: "Summarize document", summary: "Deterministic placeholder — no network."),
            MockMeshTask(id: "mock-3", title: "Plan your day", summary: "Educational example only.")
        ]
    }

    func submitMockProof(taskId: String) async -> String {
        try? await Task.sleep(nanoseconds: 50_000_000)
        return "Mock proof recorded for \(taskId). No live verifier."
    }

    var earningsDisclaimer: String {
        "Preview only. No real earnings are generated in SELF OS Personal Intelligence (Community Edition)."
    }

    var earningsDisplay: String { "0 ESSENCE (mock)" }
}

@MainActor
final class MockSelfWalletClient: ObservableObject {
    @Published var linked = false

    func connectMock() async {
        try? await Task.sleep(nanoseconds: 60_000_000)
        linked = true
    }

    func balance() -> MockBalance {
        MockBalance(amountDisplay: linked ? "42 ESSENCE (mock)" : "0 ESSENCE (mock)")
    }

    func transactions() -> [MockTransaction] {
        [
            MockTransaction(id: "tx-mock-1", label: "Welcome bonus (mock)", amountDisplay: "+10 ESSENCE"),
            MockTransaction(id: "tx-mock-2", label: "Local task preview", amountDisplay: "+0 ESSENCE")
        ]
    }

    func requestMockPayout() async -> String {
        try? await Task.sleep(nanoseconds: 40_000_000)
        return "Mock payout only. No funds move on-chain or to custodial wallets."
    }
}

@MainActor
final class MockAppStoreClient: ObservableObject {
    @Published var installed: [CatalogApp] = []

    func featured() -> [CatalogApp] {
        [
            CatalogApp(id: "feat-focus", name: "Focus timer", description: "Local-only sample listing.", category: "Productivity"),
            CatalogApp(id: "feat-journal", name: "Journal", description: "Educational mock catalog entry.", category: "Lifestyle")
        ]
    }

    func install(_ app: CatalogApp) { installed.append(app) }
    func remove(id: String) { installed.removeAll { $0.id == id } }
}

@MainActor
final class MockAuraDataClient: ObservableObject {
    @Published var consent: [String: Bool] = ["cat-usage": false, "cat-diagnostics": false]

    func categories() -> [MockDataCategory] {
        [
            MockDataCategory(id: "cat-usage", title: "Product usage patterns", description: "Educational toggle only in Community Edition."),
            MockDataCategory(id: "cat-diagnostics", title: "Diagnostics (mock)", description: "Preview consent UX only.")
        ]
    }

    func updateConsent(categoryId: String, allowed: Bool) { consent[categoryId] = allowed }

    func estimate(categoryId: String) -> String {
        "$0.00 (mock) — Preview only. Aura monetization is not active in Community Edition."
    }
}
