import Foundation

struct AgentManifest: Identifiable {
    let id: String
    let name: String
    let description: String
}

struct AgentRequest {
    let id: String
    let input: String
    let createdAt: Date
}

struct AgentResponse {
    let requestId: String
    let output: String
    let ok: Bool
}

struct CatalogApp: Identifiable, Hashable {
    let id: String
    let name: String
    let description: String
    let category: String
}

struct SelfAppManifest: Identifiable {
    let id: String
    let name: String
    let description: String
}

struct MeshNodeStatus {
    let displayLabel: String
    let isConnected: Bool
}

struct MockMeshTask: Identifiable {
    let id: String
    let title: String
    let summary: String
}

struct MockBalance {
    let amountDisplay: String
}

struct MockTransaction: Identifiable {
    let id: String
    let label: String
    let amountDisplay: String
}

struct MockDataCategory: Identifiable {
    let id: String
    let title: String
    let description: String
}
