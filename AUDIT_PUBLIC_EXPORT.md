# SELF Shell — Public Export Audit

Internal report for extracting a public-safe **SELF Shell Community Edition** from the private `selfoscompanion` monorepo. Audit date: 2026-04-28.

## 1. Candidate modules safe to copy (patterns only; reimplemented under `ai.selflabs.selfshell`)

| Area | Private module | Notes |
|------|----------------|--------|
| Material3 / Compose theme | `selfos-core` (`ui/theme/Theme.kt`) | **Reimplemented** as `SelfShellTheme` with new package. Do not copy `SelfOSCore`, Ktor, WorkManager wiring from the same module. |
| High-level dashboard layout idea | `selfos-dashboard` | **Reimplemented** with mock data only. Original references auth/payment/signal packs — excluded. |
| Generic task/status enums (non-economic) | `selfos-mesh` model files | **Not copied verbatim**; public mocks use neutral DTOs without production semantics. |

**Reality check:** The private `app` module is a thin shell (`MainActivity` + service starts). Most “product UI” lives in feature modules tightly coupled to mesh/auth/rewards/store. The public app is therefore **new Compose UI** that follows the same *product shape* (dashboard, agents, apps) without copying private feature code.

## 2. Candidate modules that must be stubbed / replaced with mocks

| Private module | Replace with |
|----------------|--------------|
| `selfos-mesh` | `mock/harmony-mesh-client`: local-only `HarmonyMeshClient` + `MockHarmonyMeshClient`. No `HarmonyMeshApiImpl`, `HarmonyMeshConfig`, JWT, or HTTP. |
| `selfos-rewards` | `mock/self-wallet`: mock balance/transactions only. No `RewardDispatcher`, deductions, or ESSENCE logic. |
| `selfos-store` | `mock/app-store`: local catalog install/remove. No `PaymentManager`, Stripe, or real billing. |
| `selfos-dashboard` (auth/payment cards) | Settings + disclaimers; “Available in SELF OS Core” placeholders. |
| `selfos-executor` | Local `AgentRuntime` in `sdk/agent-sdk` (no distributed execution, no proof/reward hooks). |
| `selfos-energy` | Omitted or static placeholder text in UI only. |
| `selfos-pel` | Omitted (background signal / pickup events — not appropriate for public Community Edition without review). |
| `selfos-core` (non-theme) | Omitted (Ktor client stack, `SelfOSCore`, etc.). |

## 3. Candidate modules that must be excluded completely

- **All production HTTP clients:** `HarmonyMeshApiImpl.kt`, `HarmonyMeshAuthService.kt`, `TokenCryptoManager.kt`, `JwtUtils.kt`, `network/HarmonyMeshConfig.kt` (contains **hardcoded production base URL** `https://api.selflabs.com/mesh`).
- **Reward / payment / store economics:** `DefaultRewardDispatcher`, `PaymentManager`, store repositories tied to real commerce.
- **Reputation / anti-fraud / mesh economics:** `reputation/*` (`NodeTier`, `BonusRewardCalculator`), `DistributedExecutionManager` (reward calculation, proof generation), `proof/ProofLedger`, leaderboard repositories calling real backends.
- **Background services:** `HarmonyMeshService`, mesh task receivers, PEL services — not copied.
- **Build outputs / IDE:** `**/build/`, `.idea/` — never export.

## 4. Discovered secrets or risky config (in private tree — do not export)

| Finding | Location | Action |
|---------|----------|--------|
| Hardcoded mesh API base URL | `selfos-mesh/.../HarmonyMeshConfig.kt` | **Exclude**; public mocks use no URLs. |
| Auth token storage keys | Same file / auth services | **Exclude** entire auth implementation. |
| JWT / JJWT dependencies | `selfos-mesh/build.gradle.kts` | **Exclude** from public Gradle deps for mesh mocks. |
| Public marketing URLs in store samples | `SignalPackRepository.kt` (`https://selflabs.com/...`) | Acceptable in docs only if desired; **not** copied into public app code paths as production config. |

**Scan note:** No `google-services.json`, `.env`, or keystores were found under `src/` during audit; still treat any future files matching `*.jks`, `service-account*.json`, `.env` as **blockers**.

## 5. Production endpoints to replace

- Any `https://api.selflabs.com/mesh` (and paths implied by `HarmonyMeshApiImpl`: `/tasks`, `/proofs`, `/auth/*`).
- Auth refresh/login/logout routes in `HarmonyMeshAuthService`.

**Public package:** zero outbound calls to these endpoints; mock clients return deterministic data.

## 6. Package names, app IDs, branding

| Item | Private | Public |
|------|---------|--------|
| Application ID | `com.selflabs.companion` | `ai.selflabs.selfshell` |
| Namespace | `com.selflabs.*` | `ai.selflabs.selfshell.*` |
| Display name | SELF OS (in modules) | **SELF Shell** / **SELF Shell Community Edition** |
| Product naming in UI | “SELF OS Dashboard” | **SELF Shell** + boundary copy to SELF OS Core |

## 7. Compile blockers expected if naively copying private code

- Cross-module imports from `com.selflabs.mesh`, `com.selflabs.rewards`, `com.selflabs.store`, etc.
- Compose BOM / compiler version alignment across modules.
- Missing R class if resources not migrated.

**Mitigation:** Standalone Gradle project under `public-self-shell/` with explicit modules and **no** `project()` references to private modules.

## 8. Summary decision

- **Copied:** Theme *ideas* only (reimplemented).
- **Stubbed:** Harmony Mesh, wallet/rewards, app store, Aura-style consent as **mock** modules.
- **Excluded:** Auth, real HTTP mesh client, payment, reputation, proof/reward math, background mesh services, PEL.

This audit should be refreshed before any release if private modules change.

## 9. Build verification (2026-04-28)

- `:self-shell:compileDebugKotlin` **succeeds** on JDK 17 with AGP 8.2.2 / Kotlin 1.9.22.
- Full `assembleDebug` may occasionally fail with **Gradle cache I/O errors** on some Windows/OneDrive paths; retry with `--no-build-cache` or build from a local non-synced directory if needed.
- `scripts/scan-public-safety.ps1` **passes** when excluding the intentional template file `.env.example` from env-pattern matching.

## 10. Manual steps before publishing to GitHub

1. Confirm `LICENSE` matches the policy for **github.com/selflabs/SELF-OS** (currently **AGPL-3.0**).
2. Run safety scans; confirm no `local.properties` or keystores are committed.
3. Remove or redact any maintainer-specific paths from contributor docs if copied elsewhere.
4. Optionally split `public-self-shell` into its own git root for a clean public repository (without monorepo history), if required by your release process.

## 11. GitHub publication

- **Canonical public repo:** [https://github.com/selflabs/SELF-OS](https://github.com/selflabs/SELF-OS)
- In this monorepo, a remote **`self-os`** may be added: `git remote add self-os https://github.com/selflabs/SELF-OS.git`
- Push workflow: see `public-self-shell/docs/GITHUB.md` (`git subtree push --prefix=public-self-shell self-os main`).
