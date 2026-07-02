# Changelog

All notable changes to **SELF OS Personal Intelligence (Community Edition)** are documented here.

Format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/). Versioning uses `MAJOR.MINOR.PATCH-community` for open releases.

## [Unreleased]

### Added

- Android **launch smoke test** (`LaunchSmokeTest`) and iOS **UI launch test** (`SelfOSPIUITests`).
- GitHub Release workflow now attaches **Android APKs** (debug + release R8 builds).
- [DEVELOPMENT.md](DEVELOPMENT.md) — local build, test, and first-agent contributor guide.
- [SECURITY.md](SECURITY.md) — private vulnerability reporting and disclosure timelines.

## [0.1.0-community] - 2026-07-02

### Added

- **Android** shell (Jetpack Compose): six tabs, agent chat, theme, mock mesh/wallet/store/Aura previews.
- **iOS** shell (SwiftUI): parity with Android product shape; Xcode project `SelfOSPI`.
- Kotlin **agent-sdk** and **app-sdk** with example agents and SELF Apps.
- Mock modules for Harmony Mesh, wallet, app store, and Aura (public-safe, no production endpoints).
- `.env.example` for optional model provider keys when extending agents.
- CI: Android build (tests + debug + release R8), iOS build (debug + release), safety scan.
- JVM unit tests for `DefaultAgentRuntime`, mock clients, and example agents.
- Dependabot for Gradle and GitHub Actions; issue/PR templates.
- Docs: architecture, boundaries, local models, iOS quick start, GitHub publish guide.

### Security

- `scripts/scan-public-safety` blocks risky filenames and patterns before release.

[0.1.0-community]: https://github.com/selflabs/SELF-OS/releases/tag/v0.1.0-community
