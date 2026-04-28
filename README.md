# SELF OS Personal Intelligence

[![GitHub](https://img.shields.io/badge/GitHub-selflabs%2FSELF--OS-181717?logo=github)](https://github.com/selflabs/SELF-OS)

**Home repository:** [github.com/selflabs/SELF-OS](https://github.com/selflabs/SELF-OS) — canonical source for issues, PRs, and releases of **SELF OS Personal Intelligence**.

**An open-source personal intelligence shell for building, running, and customizing AI agents, apps, and device-native workflows.**

**SELF OS Personal Intelligence** is the open developer layer of SELF OS — a **personal intelligence environment** where people can install agents, run local AI tools, connect apps, and build custom automations around their life.

The open-source edition gives developers and power users the foundation to shape their own **agent-powered personal intelligence** setup on-device.

The full SELF OS experience adds managed agents, Harmony Mesh access, ESSENCE rewards, Aura data monetization, phone services, cloud sync, and production-grade security.

**Tagline:** Build your own personal intelligence system — or connect to the full SELF OS ecosystem when you are ready.

**Platforms:** **SELF OS Personal Intelligence** is a **mobile** layer — meant to run on the devices people carry every day. **Android** is **available now** in this repository (phone and tablet). **Apple (iOS)** is **coming soon**; the same product vision applies across platforms once the iOS edition ships.

**Model providers:** You are not locked into one AI vendor. As you extend agents and apps, you can integrate **different model providers** — for example **[Resonatia](https://resonatia.io/docs)** (quantized models through a REST chat API; authentication and request shape are documented there), [OpenRouter](https://openrouter.ai), **OpenAI**, **Anthropic (Claude)**, **Google (Gemini)**, and other compatible APIs or on-device runtimes. **Community Edition** ships **deterministic example agents** only; wiring a provider means adding your own client code, credentials, and privacy review. See [docs/LOCAL_MODELS.md](docs/LOCAL_MODELS.md) for local vs cloud notes.

---

## 1. What is SELF OS Personal Intelligence?

**Community Edition** is a standalone **Android** app and SDK workspace today: a reference **mobile personal intelligence shell** that shows how the open layer fits together. (An **iOS** edition is planned next.)

- A **shell UI** for your personal intelligence workspace (home, agents, apps, mesh preview, wallet preview, settings).
- **On-device agent execution** via `AgentRuntime` — core to a local-first personal intelligence stack.
- **SELF Apps** in a `PluginRegistry`, so intelligence capabilities can grow like mini-programs around the user.
- **Mock** Harmony Mesh, wallet, app store, and Aura-style consent previews — safe stand-ins while you design real personal intelligence flows.

Live SELF OS backends are intentionally out of scope here. See [docs/OPEN_SOURCE_BOUNDARY.md](docs/OPEN_SOURCE_BOUNDARY.md).

## 2. What can you build for personal intelligence?

- **Agents** that implement the `Agent` interface — specialized reasoning or helpers inside your intelligence shell ([`examples/agents/`](examples/agents/)). Agents can call **your choice** of model backends (e.g. [Resonatia](https://resonatia.io/docs), OpenRouter, OpenAI, Claude, Gemini, or local models) once you add the integration.
- **SELF Apps** via `SelfApp` — surfaces and tools that extend what the user can do from one personal intelligence home ([`examples/apps/`](examples/apps/)).
- **Custom mocks** under `mock/` so prototypes keep the same contracts without touching private infrastructure.

## 3. SELF OS Personal Intelligence vs SELF OS Core (full platform)

| This repo: open **personal intelligence** edition | SELF OS Core (separate): full **managed** intelligence & platform |
|---------------------------------------------------|---------------------------------------------------------------------|
| Local shell UI & SDKs for your own PI setup | Live Harmony Mesh routing |
| Mock economics & tasks (preview only) | ESSENCE earning & settlement |
| Educational Aura toggles (consent UX) | Aura monetization programs |
| No telecom provisioning | Telnyx / phone services |
| No managed cloud agents | Orchestrated cloud workloads |

## 4. Features (personal intelligence experience)

- **Mobile-native Android shell** — Material 3 / Jetpack Compose, six main destinations plus agent chat, tuned for daily personal intelligence on handhelds and tablets (**iOS** version coming soon).
- **Theme** controls (system / light / dark) so the shell feels like *your* environment.
- **Agent chat** wired to `AgentRuntime.sendRequest` — conversational layer over local intelligence; swap in real LLMs via the providers you configure (OpenAI, Claude, Gemini, OpenRouter, [Resonatia](https://resonatia.io/docs), etc.).
- **Mock app catalog** — practice how apps plug into a personal intelligence launcher (in-memory).
- **Safety-first** layout for an open PI project: no `google-services.json`, no keystores committed in-tree.

## 5. Architecture of the open intelligence layer

The codebase is modular so you can evolve **personal intelligence** without a monolith. See [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md).

- `apps/self-shell` — the Android **personal intelligence** application (Gradle module name unchanged).
- `sdk/agent-sdk`, `sdk/app-sdk` — contracts for agents and apps that live in the user’s PI stack.
- `mock/*` — local-only stand-ins for mesh, wallet, store, and data-consent previews.

## 6. Quick Start (Android — run the app on a device or emulator)

This Quick Start is for **Android**; **iOS** setup will be documented when the Apple release is available.

Clone from GitHub (or open the `public-self-shell` folder if it lives inside your monorepo):

```bash
git clone https://github.com/selflabs/SELF-OS.git
cd SELF-OS
```

Requirements: **JDK 17**, **Android SDK** (compileSdk 34), and a physical **Android** device or emulator.

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

What stays in the open **personal intelligence** edition vs what remains in SELF OS Core: [docs/OPEN_SOURCE_BOUNDARY.md](docs/OPEN_SOURCE_BOUNDARY.md).

## 11. Roadmap

Where the open personal intelligence layer may go next: [docs/ROADMAP.md](docs/ROADMAP.md).

## 12. Security

Protecting users and forks of this **personal intelligence** codebase: [SECURITY.md](SECURITY.md). Before release, run:

- `scripts/scan-public-safety.sh` or `scripts/scan-public-safety.ps1`

## 13. Contributing

Help improve **SELF OS Personal Intelligence**: [CONTRIBUTING.md](CONTRIBUTING.md) and [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md).

## 14. License

**SELF OS Personal Intelligence** is licensed under the **GNU Affero General Public License v3.0** — see [`LICENSE`](LICENSE). See also [NOTICE](NOTICE).

---

**Preview disclaimer (also shown in-app):** *Preview only. No real earnings are generated in SELF OS Personal Intelligence (Community Edition).*
