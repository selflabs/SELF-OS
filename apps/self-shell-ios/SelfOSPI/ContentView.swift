import SwiftUI

struct ContentView: View {
    @EnvironmentObject private var env: AppEnvironment
    @AppStorage("preferredColorScheme") private var schemeRaw = ThemeChoice.system.rawValue

    var body: some View {
        TabView {
            HomeView()
                .tabItem { Label("Home", systemImage: "house") }
                .accessibilityIdentifier(UiTestTags.tabHome)
            AgentsView()
                .tabItem { Label("Agents", systemImage: "cpu") }
            AppsView()
                .tabItem { Label("Apps", systemImage: "square.grid.2x2") }
            MeshPreviewView()
                .tabItem { Label("Mesh", systemImage: "network") }
            WalletPreviewView()
                .tabItem { Label("Wallet", systemImage: "wallet.pass") }
            SettingsView()
                .tabItem { Label("Settings", systemImage: "gearshape") }
        }
        .preferredColorScheme(colorScheme)
        .task { await env.bootstrap() }
    }

    private var colorScheme: ColorScheme? {
        switch ThemeChoice(rawValue: schemeRaw) ?? .system {
        case .system: return nil
        case .light: return .light
        case .dark: return .dark
        }
    }
}
