import Foundation

protocol PIAgent {
    var manifest: AgentManifest { get }
    func initialize() async
    func handleRequest(_ request: AgentRequest) async -> AgentResponse
    func shutdown() async
}

@MainActor
final class AgentRuntime: ObservableObject {
    @Published private(set) var manifests: [AgentManifest] = []
    private var agents: [String: PIAgent] = [:]

    func register(_ agent: PIAgent) async {
        let id = agent.manifest.id
        await agents[id]?.shutdown()
        agents[id] = agent
        await agent.initialize()
        manifests = agents.values.map(\.manifest)
    }

    func sendRequest(agentId: String, input: String) async -> AgentResponse {
        guard let agent = agents[agentId] else {
            return AgentResponse(requestId: "", output: "Agent not found", ok: false)
        }
        let request = AgentRequest(id: UUID().uuidString, input: input, createdAt: Date())
        return await agent.handleRequest(request)
    }
}
