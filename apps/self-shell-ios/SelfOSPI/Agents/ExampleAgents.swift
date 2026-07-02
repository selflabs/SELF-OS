import Foundation

struct HelloAgent: PIAgent {
    let manifest = AgentManifest(
        id: "hello-agent",
        name: "Hello — SELF OS Personal Intelligence",
        description: "Greets you and explains Community Edition."
    )

    func initialize() async {}
    func shutdown() async {}

    func handleRequest(_ request: AgentRequest) async -> AgentResponse {
        let body = """
        Hi there — this is the Hello agent running locally in SELF OS Personal Intelligence.

        SELF OS Personal Intelligence is the open developer layer for building agents and apps on your device.
        It does not connect to live Harmony Mesh, wallets, or rewards.

        You said: "\(request.input.trimmingCharacters(in: .whitespacesAndNewlines))"
        """
        return AgentResponse(requestId: request.id, output: body, ok: true)
    }
}

struct TaskPlannerAgent: PIAgent {
    let manifest = AgentManifest(
        id: "task-planner-agent",
        name: "Task planner",
        description: "Turns a goal into a short local checklist."
    )

    func initialize() async {}
    func shutdown() async {}

    func handleRequest(_ request: AgentRequest) async -> AgentResponse {
        let goal = request.input.trimmingCharacters(in: .whitespacesAndNewlines)
        let g = goal.isEmpty ? "your goal" : goal
        let lines = [
            "Clarify success for \"\(g)\".",
            "List 3 concrete steps (each under 30 minutes).",
            "Do step 1 now; note blockers.",
            "Review and adjust after step 1."
        ]
        let output = "Simple plan (local preview):\n" + lines.enumerated().map { "\($0.offset + 1). \($0.element)" }.joined(separator: "\n")
        return AgentResponse(requestId: request.id, output: output, ok: true)
    }
}

struct LocalNotesAgent: PIAgent {
    private static var notes: [String] = []

    let manifest = AgentManifest(
        id: "local-notes-agent",
        name: "Local notes",
        description: "In-memory notes for this session."
    )

    func initialize() async {}
    func shutdown() async { Self.notes.removeAll() }

    func handleRequest(_ request: AgentRequest) async -> AgentResponse {
        let text = request.input.trimmingCharacters(in: .whitespacesAndNewlines)
        let lower = text.lowercased()
        if lower.hasPrefix("add ") {
            let note = String(text.dropFirst(4)).trimmingCharacters(in: .whitespacesAndNewlines)
            guard !note.isEmpty else {
                return AgentResponse(requestId: request.id, output: "Usage: add <your note>", ok: false)
            }
            Self.notes.append(note)
            return AgentResponse(requestId: request.id, output: "Saved note #\(Self.notes.count): \(note)", ok: true)
        }
        if lower == "list" || lower == "show" {
            if Self.notes.isEmpty {
                return AgentResponse(requestId: request.id, output: "No notes yet. Try: add Buy milk", ok: true)
            }
            let all = Self.notes.enumerated().map { "\($0.offset + 1). \($0.element)" }.joined(separator: "\n")
            return AgentResponse(requestId: request.id, output: all, ok: true)
        }
        if lower == "clear" {
            Self.notes.removeAll()
            return AgentResponse(requestId: request.id, output: "Cleared all in-memory notes.", ok: true)
        }
        return AgentResponse(requestId: request.id, output: "Commands: add <text> | list | clear", ok: true)
    }
}
