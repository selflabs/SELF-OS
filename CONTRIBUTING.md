# Contributing

Thank you for your interest in **SELF OS Personal Intelligence**.

**Public GitHub:** [github.com/selflabs/SELF-OS](https://github.com/selflabs/SELF-OS) — open issues and pull requests there unless your maintainer directs otherwise.

**Local setup:** see [DEVELOPMENT.md](DEVELOPMENT.md) for build, test, and first-agent steps.

## Principles

- Keep changes focused and reviewable.
- Do not introduce production endpoints, secrets, or wallet/signing logic into this repository.
- Prefer extending the **agent SDK**, **app SDK**, or **mock clients** over adding opaque networking layers.
- Match existing Kotlin and Compose style in `apps/self-shell`.

## Before you open a PR

1. Build: `scripts/build-public.ps1` or `./scripts/build-public.sh`
2. Tests: `./gradlew :agent-sdk:testDebugUnitTest :self-shell:testDebugUnitTest` (and mock modules as needed)
3. Safety scan: `scripts/scan-public-safety.ps1` or `./scripts/scan-public-safety.sh`
4. Confirm mock-only behavior for mesh, wallet, store, and Aura surfaces.

## Licensing

By contributing, you agree your contributions will be licensed under the same terms as this project (see `LICENSE`, AGPL-3.0).
