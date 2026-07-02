import SwiftUI

struct MeshPreviewView: View {
    @EnvironmentObject private var env: AppEnvironment
    @State private var lastProof = ""

    var body: some View {
        NavigationStack {
            List {
                Section {
                    MockPreviewBanner()
                    PreviewDisclaimer()
                    Text("Harmony Mesh live-network routing is available in SELF OS Core.")
                        .font(.caption)
                }
                Section("Node status") {
                    let s = env.harmonyMesh.nodeStatus()
                    Text(s.displayLabel)
                    Text("Connected (mock): \(s.isConnected ? "Yes" : "No")")
                }
                Section("Mock available tasks") {
                    ForEach(env.harmonyMesh.listTasks()) { task in
                        VStack(alignment: .leading, spacing: 6) {
                            Text(task.title).font(.headline)
                            Text(task.summary).font(.caption)
                            Button("Submit mock proof") {
                                Task { lastProof = await env.harmonyMesh.submitMockProof(taskId: task.id) }
                            }
                        }
                    }
                }
                Section("Mock earnings") {
                    Text(env.harmonyMesh.earningsDisplay)
                    Text(env.harmonyMesh.earningsDisclaimer).font(.caption)
                }
                if !lastProof.isEmpty {
                    Section("Last mock proof") { Text(lastProof).font(.caption) }
                }
            }
            .navigationTitle("Mesh preview")
        }
    }
}
