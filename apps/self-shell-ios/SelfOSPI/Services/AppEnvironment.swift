import SwiftUI

@MainActor
final class AppEnvironment: ObservableObject {
    static let shared = AppEnvironment()

    let agentRuntime = AgentRuntime()
    let harmonyMesh = MockHarmonyMeshClient()
    let wallet = MockSelfWalletClient()
    let appStore = MockAppStoreClient()
    let aura = MockAuraDataClient()

    @Published var themeChoice: ThemeChoice = .system

    let selfApps: [SelfAppManifest] = [
        SelfAppManifest(id: "hello-self-app", name: "Hello SELF App", description: "Minimal demo app (local alert)."),
        SelfAppManifest(id: "personal-dashboard", name: "Personal dashboard", description: "Mock widget summary.")
    ]

    private init() {}

    func bootstrap() async {
        await agentRuntime.register(HelloAgent())
        await agentRuntime.register(TaskPlannerAgent())
        await agentRuntime.register(LocalNotesAgent())
        await harmonyMesh.connectMock()
        await wallet.connectMock()
    }

    func launchSelfApp(id: String) -> String {
        switch id {
        case "hello-self-app":
            return "Hello SELF OS Personal Intelligence — this app runs locally with no backend."
        case "personal-dashboard":
            return "Mock dashboard: Focus 25m • 2 notes • 3 agents • Preview tasks only."
        default:
            return "Unknown app"
        }
    }
}

enum ThemeChoice: String, CaseIterable, Identifiable {
    case system, light, dark
    var id: String { rawValue }
    var label: String { rawValue.capitalized }
}
