# SELF OS Personal Intelligence — iOS

SwiftUI reference app for **SELF OS Personal Intelligence (Community Edition)** on iPhone and iPad.

## Requirements

- macOS with **Xcode 15+**
- **iOS 17+** simulator or device
- Apple Developer signing team (for physical devices)

## Open and run

```bash
cd apps/self-shell-ios
open SelfOSPI.xcodeproj
```

1. Select the **SelfOSPI** scheme.
2. Choose an iOS simulator or your device.
3. Press **Run** (⌘R).

## What ships

- Six tabs: Home, Agents, Apps, Mesh preview, Wallet preview, Settings
- Three example agents (Hello, Task planner, Local notes)
- Mock Harmony Mesh, wallet, app store, and Aura consent — same public-safe boundary as Android
- Theme: system / light / dark

## Bundle ID

`ai.selflabs.selfospi` — display name **SELF OS PI** in the launcher.

## Android counterpart

The Gradle Android app lives in [`../self-shell/`](../self-shell/). Kotlin SDKs and examples under `sdk/` and `examples/` are Android-oriented today; iOS agents are implemented in Swift under `SelfOSPI/Agents/`.

See [docs/IOS.md](../../docs/IOS.md) for architecture notes.
