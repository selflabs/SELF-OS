# Open-source boundary

## SELF Shell includes (this repository)

- Open shell UI (Material 3 / Jetpack Compose).
- **Agent SDK**: manifests, permissions model, `AgentRuntime`, local execution.
- **App SDK**: SELF App manifests, `PluginRegistry`, launch hooks.
- **Local-first** example agents and apps (no required network).
- **Mock service clients** with deterministic data for Harmony Mesh, wallet, app store, and Aura-style consent education.
- Developer documentation in `docs/`.

## SELF OS Core remains private / separate (not shipped here)

The following are **intentionally excluded** from Community Edition builds for user safety, economic security, and infrastructure integrity:

- Harmony Mesh **production** routing, job dispatch, and proof verification against live infrastructure.
- **ESSENCE** earning, settlement, and production reward accounting.
- Wallet **signing**, custodial integrations, and payment rails.
- **Aura** data monetization programs tied to real markets or payouts.
- Telecom / **Telnyx** (or similar) number purchase and provisioning.
- **Cloud sync**, managed cloud agents, and proprietary orchestration.
- **Production authentication** stacks, anti-fraud systems, and node reputation economics used in live networks.
- Private Firebase/Supabase configurations, cloud functions, and business job endpoints.

Labels such as **“Available in SELF OS Core”** in the app refer to this boundary — not to implementation details of private systems.

## Why split public and private layers?

- Prevents accidental leakage of keys, URLs, and economic logic into forks.
- Gives developers a **safe sandbox** for agents and UI experiments.
- Preserves integrity of production networks and user funds.

## Preview-only economics copy

Any reference to balances, “ESSENCE”, payouts, or tasks in SELF Shell is **mocked** unless you deliberately replace mocks with your own **non-production** logic. The UI states:

> Preview only. No real earnings are generated in SELF Shell Community Edition.
