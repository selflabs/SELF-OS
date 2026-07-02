import SwiftUI

struct SettingsView: View {
    @EnvironmentObject private var env: AppEnvironment
    @AppStorage("preferredColorScheme") private var schemeRaw = ThemeChoice.system.rawValue
    @State private var estimates: [String: String] = [:]

    var body: some View {
        NavigationStack {
            Form {
                Section("Theme") {
                    Picker("Appearance", selection: $schemeRaw) {
                        ForEach(ThemeChoice.allCases) { choice in
                            Text(choice.label).tag(choice.rawValue)
                        }
                    }
                    .pickerStyle(.segmented)
                }
                Section("About") {
                    Text("SELF OS Personal Intelligence keeps agents and mock services on-device. See docs/OPEN_SOURCE_BOUNDARY.md in the repository.")
                        .font(.caption)
                }
                Section("Aura data (educational mock)") {
                    ForEach(env.aura.categories()) { cat in
                        Toggle(cat.title, isOn: Binding(
                            get: { env.aura.consent[cat.id] ?? false },
                            set: { env.aura.updateConsent(categoryId: cat.id, allowed: $0) }
                        ))
                        Text(cat.description).font(.caption)
                        Button("Mock value estimate") {
                            estimates[cat.id] = env.aura.estimate(categoryId: cat.id)
                        }
                        if let est = estimates[cat.id] { Text(est).font(.caption) }
                    }
                }
                Section("Public safety") {
                    Text("Never paste private credentials or live-service URLs into forks. Run scripts/scan-public-safety before release.")
                        .font(.caption)
                }
            }
            .navigationTitle("Settings")
        }
    }
}
