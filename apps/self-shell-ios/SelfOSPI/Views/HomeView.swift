import SwiftUI

struct HomeView: View {
    @EnvironmentObject private var env: AppEnvironment

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: 12) {
                    Text("SELF OS Personal Intelligence (Community Edition)")
                        .font(.title2.bold())
                    Text("Welcome to your local personal intelligence workspace.")
                    MockPreviewBanner()
                    card("Local agents") { Text("\(env.agentRuntime.manifests.count) example agents registered.") }
                    card("Installed SELF Apps") { Text("\(env.selfApps.count) demo apps in the local registry.") }
                    card("Mock mesh status") {
                        let s = env.harmonyMesh.nodeStatus()
                        Text(s.displayLabel)
                        Text("Connected (mock): \(s.isConnected ? "Yes" : "No")")
                    }
                    card("Mock rewards balance") {
                        Text(env.harmonyMesh.earningsDisplay)
                        PreviewDisclaimer()
                    }
                }
                .padding()
            }
            .navigationTitle("Home")
        }
    }

    @ViewBuilder
    private func card(_ title: String, @ViewBuilder content: () -> some View) -> some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title).font(.headline)
            content()
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding()
        .background(.thinMaterial)
        .clipShape(RoundedRectangle(cornerRadius: 12))
    }
}
