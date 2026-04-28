# SELF Shell

[![GitHub](https://img.shields.io/badge/GitHub-selflabs%2FSELF--OS-181717?logo=github)](https://github.com/selflabs/SELF-OS)

**Home repository:** [github.com/selflabs/SELF-OS](https://github.com/selflabs/SELF-OS) — canonical source for issues, PRs, and releases of the open personal intelligence shell.

**An open-source personal intelligence shell for building, running, and customizing AI agents, apps, and device-native workflows.**

SELF Shell is the open developer layer of SELF OS — a **personal intelligence environment** where people can install agents, run local AI tools, connect apps, and build custom automations around their life.

The open-source shell gives developers and power users the foundation to shape their own **agent-powered personal intelligence** setup on-device.

The full SELF OS experience adds managed agents, Harmony Mesh access, ESSENCE rewards, Aura data monetization, phone services, cloud sync, and production-grade security.

**Tagline:** Build your own personal intelligence system — or connect to the full SELF OS ecosystem when you are ready.

---

## 1. What is SELF Shell?

SELF Shell **Community Edition** is a standalone Android app and SDK workspace: a reference **personal intelligence shell** that shows how the open layer fits together.

- A **shell UI** for your personal intelligence workspace (home, agents, apps, mesh preview, wallet preview, settings).
- **On-device agent execution** via `AgentRuntime` — core to a local-first personal intelligence stack.
- **SELF Apps** in a `PluginRegistry`, so intelligence capabilities can grow like mini-programs around the user.
- **Mock** Harmony Mesh, wallet, app store, and Aura-style consent previews — safe stand-ins while you design real personal intelligence flows.

Live SELF OS backends are intentionally out of scope here. See [docs/OPEN_SOURCE_BOUNDARY.md](docs/OPEN_SOURCE_BOUNDARY.md).

## 2. What can you build for personal intelligence?

- **Agents** that implement the `Agent` interface — specialized reasoning or helpers inside your intelligence shell ([`examples/agents/`](examples/agents/)).
- **SELF Apps** via `SelfApp` — surfaces and tools that extend what the user can do from one personal intelligence home ([`examples/apps/`](examples/apps/)).
- **Custom mocks** under `mock/` so prototypes keep the same contracts without touching private infrastructure.

## 3. SELF Shell vs SELF OS (personal intelligence stack)

| This repo: open **personal intelligence shell** | SELF OS Core (separate): full **managed** intelligence & platform |
|---------------------------------------------------|---------------------------------------------------------------------|
| Local shell UI & SDKs for your own PI setup | Live Harmony Mesh routing |
| Mock economics & tasks (preview only) | ESSENCE earning & settlement |
| Educational Aura toggles (consent UX) | Aura monetization programs |
| No telecom provisioning | Telnyx / phone services |
| No managed cloud agents | Orchestrated cloud workloads |

## 4. Features (personal intelligence experience)

- **Material 3 / Jetpack Compose** shell — six main destinations plus agent chat, tuned for daily personal intelligence use.
- **Theme** controls (system / light / dark) so the shell feels like *your* environment.
- **Agent chat** wired to `AgentRuntime.sendRequest` — conversational layer over local intelligence.
- **Mock app catalog** — practice how apps plug into a personal intelligence launcher (in-memory).
- **Safety-first** layout for an open PI project: no `google-services.json`, no keystores committed in-tree.

## 5. Architecture of the open intelligence layer

The codebase is modular so you can evolve **personal intelligence** without a monolith. See [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md).

- `apps/self-shell` — the Android **personal intelligence shell** application.
- `sdk/agent-sdk`, `sdk/app-sdk` — contracts for agents and apps that live in the user’s PI stack.
- `mock/*` — local-only stand-ins for mesh, wallet, store, and data-consent previews.

## 6. Quick Start (run the shell locally)

Clone from GitHub (or open the `public-self-shell` folder if it lives inside your monorepo):

```bash
git clone https://github.com/selflabs/SELF-OS.git
cd SELF-OS
```

Requirements: **JDK 17**, **Android SDK** (compileSdk 34).

```bash
./gradlew :self-shell:assembleDebug
```

On Windows:

```powershell
.\gradlew.bat :self-shell:assembleDebug
```

Open the project in Android Studio to iterate on your **personal intelligence** UI and agents.

**Monorepo maintainers:** see [docs/GITHUB.md](docs/GITHUB.md) for pushing this tree to `selflabs/SELF-OS`.

## 7. Creating an agent (intelligence primitive)

Agents are how the shell does work on behalf of the user. Read [docs/AGENT_DEVELOPMENT.md](docs/AGENT_DEVELOPMENT.md) and study:

- `examples/agents/hello-agent/HelloAgent.kt`
- `examples/agents/task-planner-agent/TaskPlannerAgent.kt`
- `examples/agents/local-notes-agent/LocalNotesAgent.kt`

## 8. Creating a SELF App (intelligence surface)

SELF Apps are installable surfaces in the same **personal intelligence** workspace. Read [docs/PLUGIN_DEVELOPMENT.md](docs/PLUGIN_DEVELOPMENT.md) and study:

- `examples/apps/hello-self-app/HelloSelfApp.kt`
- `examples/apps/personal-dashboard/PersonalDashboardApp.kt`

## 9. Mock services (safe personal intelligence sandbox)

By default there is **no** live link to SELF OS Core. Mocks under `mock/` give deterministic previews so you can design personal intelligence flows without keys or production endpoints. Details: [docs/MOCK_SERVICES.md](docs/MOCK_SERVICES.md).

## 10. Open-source boundary

What stays in the open **personal intelligence shell** vs what remains in SELF OS Core: [docs/OPEN_SOURCE_BOUNDARY.md](docs/OPEN_SOURCE_BOUNDARY.md).

## 11. Roadmap

Where the open personal intelligence layer may go next: [docs/ROADMAP.md](docs/ROADMAP.md).

## 12. Security

Protecting users and forks of this **personal intelligence** codebase: [SECURITY.md](SECURITY.md). Before release, run:

- `scripts/scan-public-safety.sh` or `scripts/scan-public-safety.ps1`

## 13. Contributing

Help improve the open personal intelligence shell: [CONTRIBUTING.md](CONTRIBUTING.md) and [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md).

## 14. License

SELF Shell is licensed under the **GNU Affero General Public License v3.0** — see [`LICENSE`](LICENSE). See also [NOTICE](NOTICE).

---

**Preview disclaimer (also shown in-app):** *Preview only. No real earnings are generated in SELF Shell Community Edition.*
