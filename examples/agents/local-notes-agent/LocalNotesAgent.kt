package ai.selflabs.selfshell.examples.agents.notes

import ai.selflabs.selfshell.sdk.agent.Agent
import ai.selflabs.selfshell.sdk.agent.AgentManifest
import ai.selflabs.selfshell.sdk.agent.AgentPermission
import ai.selflabs.selfshell.sdk.agent.AgentRequest
import ai.selflabs.selfshell.sdk.agent.AgentResponse
import ai.selflabs.selfshell.sdk.agent.AgentRunStatus

/**
 * In-memory notes only (lost when process dies). No network or disk persistence.
 */
class LocalNotesAgent : Agent {

    override fun getManifest(): AgentManifest = AgentManifest(
        id = "local-notes-agent",
        name = "Local notes",
        description = "Stores short notes in memory for this session.",
        version = "1.0.0",
        author = "SELF Shell examples",
        permissions = setOf(AgentPermission.LOCAL_STORAGE, AgentPermission.FILE_WRITE),
        entryPoint = "ai.selflabs.selfshell.examples.agents.notes.LocalNotesAgent"
    )

    override suspend fun initialize() {}

    override suspend fun handleRequest(request: AgentRequest): AgentResponse {
        val text = request.input.trim()
        val lower = text.lowercase()
        return when {
            lower.startsWith("add ") -> {
                val note = text.removePrefix("add ").trim()
                if (note.isEmpty()) {
                    AgentResponse(request.id, "Usage: add <your note>", AgentRunStatus.ERROR, emptyMap())
                } else {
                    val id = NoteStore.add(note)
                    AgentResponse(request.id, "Saved note #$id: $note", AgentRunStatus.OK, emptyMap())
                }
            }
            lower == "list" || lower == "show" -> {
                val all = NoteStore.all()
                if (all.isEmpty()) {
                    AgentResponse(request.id, "No notes yet. Try: add Buy milk", AgentRunStatus.OK, emptyMap())
                } else {
                    AgentResponse(
                        request.id,
                        all.joinToString("\n") { "${it.first}. ${it.second}" },
                        AgentRunStatus.OK,
                        emptyMap()
                    )
                }
            }
            lower == "clear" -> {
                NoteStore.clear()
                AgentResponse(request.id, "Cleared all in-memory notes.", AgentRunStatus.OK, emptyMap())
            }
            else -> AgentResponse(
                request.id,
                "Commands: add <text> | list | clear",
                AgentRunStatus.OK,
                emptyMap()
            )
        }
    }

    override suspend fun shutdown() {
        NoteStore.clear()
    }

    private object NoteStore {
        private val notes = mutableListOf<String>()
        private var next = 1

        fun add(text: String): Int {
            val id = next++
            notes.add(text)
            return id
        }

        fun all(): List<Pair<Int, String>> =
            notes.mapIndexed { index, s -> index + 1 to s }

        fun clear() {
            notes.clear()
            next = 1
        }
    }
}
