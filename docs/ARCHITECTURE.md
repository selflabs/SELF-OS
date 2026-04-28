# Architecture

## Modules (Gradle)

| Module | Role |
|--------|------|
| `apps/self-shell` | Android application, navigation, screens, theming. |
| `sdk/agent-sdk` | `Agent`, `AgentRuntime`, manifests, permissions, requests/responses. |
| `sdk/app-sdk` | `SelfApp`, `SelfAppManifest`, `PluginRegistry`. |
| `mock/harmony-mesh-client` | `HarmonyMeshClient` + `MockHarmonyMeshClient`. |
| `mock/self-wallet` | `SelfWalletClient` + `MockSelfWalletClient`. |
| `mock/app-store` | `AppStoreClient` + `MockAppStoreClient`. |
| `mock/aura-data` | `AuraDataClient` + `MockAuraDataClient`. |

## Data flow

1. **Application** (`SelfShellApplication`) owns singletons for runtime, registry, and mocks.
2. **ShellBootstrap** registers example agents and apps on startup (coroutine on main).
3. **UI** reads mock state via suspend calls in `LaunchedEffect` or coroutine scopes; no Retrofit/Ktor in this tree.

## Extension points

- Implement `Agent` and register with `AgentRuntime`.
- Implement `SelfApp` and register with `PluginRegistry`.
- Swap mock modules for your own **local** implementations that keep the same interfaces.
