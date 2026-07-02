# Development guide

How to build, test, and extend **SELF OS Personal Intelligence (Community Edition)** locally.

**Public repo:** [github.com/selflabs/SELF-OS](https://github.com/selflabs/SELF-OS)

## Prerequisites

| Platform | Requirements |
|----------|--------------|
| **Android** | JDK 17, Android SDK (compileSdk 34), Android Studio (recommended) or command line |
| **iOS** | macOS, Xcode 15+, iOS 17+ simulator or device |
| **All** | Git |

Optional: copy `.env.example` to `.env` when you add your own model-provider integration (CE example agents do not read it).

## Clone and open

```bash
git clone https://github.com/selflabs/SELF-OS.git
cd SELF-OS
```

If you work inside a monorepo, open the `public-self-shell/` folder — it is the same tree published to GitHub.

## Android

### Build

```bash
./gradlew :self-shell:assembleDebug
```

Windows:

```powershell
.\gradlew.bat :self-shell:assembleDebug
```

Or use the helper script:

```bash
./scripts/build-public.sh
```

APK output: `apps/self-shell/build/outputs/apk/debug/`

### Run on device or emulator

1. Open the repo root in **Android Studio**.
2. Select the **self-shell** run configuration.
3. Choose an emulator or connected device and press **Run**.

### Unit tests

```bash
./gradlew \
  :agent-sdk:testDebugUnitTest \
  :mock-harmony-mesh:testDebugUnitTest \
  :mock-self-wallet:testDebugUnitTest \
  :mock-app-store:testDebugUnitTest \
  :self-shell:testDebugUnitTest
```

### Launch smoke test (Android, device or emulator)

```bash
./gradlew :self-shell:connectedDebugAndroidTest \
  -Pandroid.testInstrumentationRunnerArguments.class=ai.selflabs.selfshell.LaunchSmokeTest
```

iOS launch smoke tests run with the **SelfOSPI** scheme (`SelfOSPIUITests`) via Xcode or `xcodebuild test`.

Release build (R8 minify, debug signing for CI only):

```bash
./gradlew :self-shell:assembleRelease
```

## iOS

```bash
cd apps/self-shell-ios
open SelfOSPI.xcodeproj
```

1. Select the **SelfOSPI** scheme.
2. Pick an **iOS Simulator** (iPhone, iOS 17+).
3. Press **Run** (⌘R) or **Test** (⌘U).

See [apps/self-shell-ios/README.md](apps/self-shell-ios/README.md) and [docs/IOS.md](docs/IOS.md).

## Project layout

```
apps/self-shell/          Android shell (Compose)
apps/self-shell-ios/      iOS shell (SwiftUI)
sdk/agent-sdk/            Agent contracts + DefaultAgentRuntime
sdk/app-sdk/              SELF App contracts + PluginRegistry
mock/                     Public-safe mock clients (mesh, wallet, store, Aura)
examples/agents/          Sample agents (compiled into Android app)
examples/apps/            Sample SELF Apps
```

Architecture and boundaries: [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md), [docs/OPEN_SOURCE_BOUNDARY.md](docs/OPEN_SOURCE_BOUNDARY.md).

## Add an example agent (Android)

1. Copy `examples/agents/hello-agent/` to a new folder under `examples/agents/`.
2. Implement `Agent` — see [docs/AGENT_DEVELOPMENT.md](docs/AGENT_DEVELOPMENT.md).
3. Register it in `apps/self-shell/src/main/java/ai/selflabs/selfshell/ShellBootstrap.kt`:

   ```kotlin
   app.agentRuntime.registerAgent(MyAgent())
   ```

4. Add the source folder to `apps/self-shell/build.gradle.kts` `sourceSets` (same pattern as other examples).
5. Add a unit test under `apps/self-shell/src/test/` if the agent has non-trivial logic.

Open the app → **Agents** tab → select your agent → chat.

## Add a SELF App (Android)

1. Copy `examples/apps/hello-self-app/`.
2. Implement `SelfApp` — see [docs/PLUGIN_DEVELOPMENT.md](docs/PLUGIN_DEVELOPMENT.md).
3. Register in `ShellBootstrap.kt` via `pluginRegistry.registerApp(...)`.
4. Add the source folder to `build.gradle.kts` `sourceSets`.

## iOS parity

iOS reimplements the same product shape in Swift (`PIAgent`, `MockClients`, `ExampleAgents.swift`). There is no shared Kotlin Multiplatform module yet — keep behavior aligned manually and document differences in your PR.

## Safety checks (required before release)

```bash
./scripts/scan-public-safety.sh
```

Windows: `.\scripts\scan-public-safety.ps1`

Do **not** commit:

- API keys, `.env`, keystores, or production backend URLs
- Real wallet seeds or signing material

## CI (what runs on PRs)

| Job | Workflow |
|-----|----------|
| `android` | Unit tests + debug/release APK build |
| `ios` | Unit tests + simulator debug/release build |
| `scan` | Safety pattern scan |

Details: [docs/CI.md](docs/CI.md).

## Install without building

Tagged releases attach Android APKs:

- **selfos-pi-community-debug.apk** — fastest to sideload for trying CE
- **selfos-pi-community-release.apk** — R8-minified build (still CE / mock-only)

Download from [GitHub Releases](https://github.com/selflabs/SELF-OS/releases). iOS builds require Xcode locally (no IPA in CE releases yet).

## First pull request checklist

1. Read [CONTRIBUTING.md](CONTRIBUTING.md) and [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md).
2. Keep changes scoped; stay inside the [open-source boundary](docs/OPEN_SOURCE_BOUNDARY.md).
3. Run unit tests and the safety scan locally.
4. Open a PR against `main` — required CI checks must pass (branch protection).

## Get help

- **Bugs / features:** [GitHub Issues](https://github.com/selflabs/SELF-OS/issues)
- **Security:** [SECURITY.md](SECURITY.md) — do not file public issues with exploit details
