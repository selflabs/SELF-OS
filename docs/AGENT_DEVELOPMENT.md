# Agent development

## Lifecycle

1. `initialize()` — prepare in-memory or local resources.
2. `handleRequest(request)` — return an `AgentResponse` with `AgentRunStatus`.
3. `shutdown()` — clear session state.

## Permissions

`AgentPermission` enumerates coarse capabilities (`FILE_READ`, `NETWORK_OPTIONAL`, etc.). Community Edition does not enforce a sandbox; treat this as API documentation for future hosts.

## Registration

```kotlin
agentRuntime.registerAgent(MyAgent())
```

## Messaging

Use `sendRequest` or `sendMessageToAgent` on `AgentRuntime`. IDs are generated per request.

Examples: `examples/agents/hello-agent`, `task-planner-agent`, `local-notes-agent`.
