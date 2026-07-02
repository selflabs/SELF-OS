# iOS edition

**SELF OS Personal Intelligence** ships a native **SwiftUI** app under `apps/self-shell-ios/`.

## Layout

| Path | Role |
|------|------|
| `SelfOSPI/SelfOSPIApp.swift` | `@main` entry, wires `AppEnvironment` |
| `SelfOSPI/ContentView.swift` | Tab bar (six destinations) |
| `SelfOSPI/Services/` | `AgentRuntime`, mock clients, bootstrap |
| `SelfOSPI/Agents/` | Example `PIAgent` implementations |
| `SelfOSPI/Views/` | Home, Agents, Apps, Mesh, Wallet, Settings |
| `SelfOSPI.xcodeproj` | Xcode project (iOS 17+, bundle `ai.selflabs.selfospi`) |

## Parity with Android

The iOS app mirrors the Android shell’s **product shape**:

- Local agent runtime with deterministic example agents
- Mock mesh / wallet / store / Aura — no production endpoints
- In-app preview disclaimer for economics

Kotlin `sdk/agent-sdk` types are not shared at compile time; Swift defines parallel DTOs in `Models.swift` and the `PIAgent` protocol.

## Extending

1. Add a type conforming to `PIAgent` in `SelfOSPI/Agents/`.
2. Register it in `AppEnvironment.bootstrap()`.
3. Agents appear automatically in the Agents tab.

To call external model APIs (OpenRouter, OpenAI, [Resonatia](https://resonatia.io/docs), etc.), add your own HTTP client inside an agent — Community Edition does not bundle vendor SDKs.

## CI

GitHub Actions workflow `.github/workflows/ios.yml` builds the app on `macos-latest` with `xcodebuild` (simulator, no code signing).
