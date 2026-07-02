import SwiftUI

struct AgentsView: View {
    @EnvironmentObject private var env: AppEnvironment

    var body: some View {
        NavigationStack {
            List(env.agentRuntime.manifests) { manifest in
                NavigationLink(value: manifest.id) {
                    VStack(alignment: .leading, spacing: 4) {
                        Text(manifest.name).font(.headline)
                        Text(manifest.description).font(.caption).foregroundStyle(.secondary)
                    }
                }
            }
            .navigationTitle("Agents")
            .navigationDestination(for: String.self) { id in
                AgentChatView(agentId: id)
            }
            .overlay(alignment: .top) {
                VStack {
                    PreviewDisclaimer().padding(.horizontal)
                    Spacer()
                }
            }
        }
    }
}

struct AgentChatView: View {
    @EnvironmentObject private var env: AppEnvironment
    let agentId: String
    @State private var input = ""
    @State private var lines: [(String, String)] = []

    var body: some View {
        VStack {
            ScrollView {
                LazyVStack(alignment: .leading, spacing: 8) {
                    ForEach(Array(lines.enumerated()), id: \.offset) { _, line in
                        Text("\(line.0): \(line.1)")
                    }
                }
                .frame(maxWidth: .infinity, alignment: .leading)
            }
            HStack {
                TextField("Message", text: $input)
                    .textFieldStyle(.roundedBorder)
                Button("Send") {
                    let msg = input.trimmingCharacters(in: .whitespacesAndNewlines)
                    guard !msg.isEmpty else { return }
                    lines.append(("You", msg))
                    input = ""
                    Task {
                        let res = await env.agentRuntime.sendRequest(agentId: agentId, input: msg)
                        lines.append((res.ok ? "Agent" : "Agent (error)", res.output))
                    }
                }
            }
            .padding(.horizontal)
        }
        .padding(.top)
        .navigationTitle(env.agentRuntime.manifests.first { $0.id == agentId }?.name ?? "Agent")
        .navigationBarTitleDisplayMode(.inline)
    }
}
