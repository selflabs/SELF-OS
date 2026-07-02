import SwiftUI

struct AppsView: View {
    @EnvironmentObject private var env: AppEnvironment
    @State private var alertMessage: String?

    var body: some View {
        NavigationStack {
            List {
                Section("SELF App registry") {
                    ForEach(env.selfApps) { app in
                        VStack(alignment: .leading, spacing: 6) {
                            Text(app.name).font(.headline)
                            Text(app.description).font(.caption)
                            Button("Launch") { alertMessage = env.launchSelfApp(id: app.id) }
                        }
                    }
                }
                Section("Mock app store (local)") {
                    ForEach(env.appStore.featured()) { item in
                        VStack(alignment: .leading, spacing: 6) {
                            Text(item.name).font(.headline)
                            Text(item.description).font(.caption)
                            Button("Add to local catalog") { env.appStore.install(item) }
                        }
                    }
                }
                if !env.appStore.installed.isEmpty {
                    Section("Installed from mock catalog") {
                        ForEach(env.appStore.installed) { item in
                            HStack {
                                Text(item.name)
                                Spacer()
                                Button("Remove", role: .destructive) { env.appStore.remove(id: item.id) }
                            }
                        }
                    }
                }
            }
            .navigationTitle("Apps")
            .alert("SELF App", isPresented: Binding(
                get: { alertMessage != nil },
                set: { if !$0 { alertMessage = nil } }
            )) {
                Button("OK", role: .cancel) { alertMessage = nil }
            } message: {
                Text(alertMessage ?? "")
            }
        }
    }
}
