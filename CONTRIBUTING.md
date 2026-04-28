# Contributing

Thank you for your interest in SELF Shell.

**Public GitHub:** [github.com/selflabs/SELF-OS](https://github.com/selflabs/SELF-OS) — open issues and pull requests there unless your maintainer directs otherwise.

## Principles

- Keep changes focused and reviewable.
- Do not introduce production endpoints, secrets, or wallet/signing logic into this repository.
- Prefer extending the **agent SDK**, **app SDK**, or **mock clients** over adding opaque networking layers.
- Match existing Kotlin and Compose style in `apps/self-shell`.

## Before you open a PR

1. Build: `scripts/build-public.ps1` or `./scripts/build-public.sh`
2. Safety scan: `scripts/scan-public-safety.ps1` or `./scripts/scan-public-safety.sh`
3. Confirm mock-only behavior for mesh, wallet, store, and Aura surfaces.

## Licensing

By contributing, you agree your contributions will be licensed under the same terms as this project (see `LICENSE` once the PolyForm Noncommercial text is in place).
