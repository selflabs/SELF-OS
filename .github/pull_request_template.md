## Summary

<!-- What changed and why? Keep mock-only / public-safe scope. -->

## Platform

- [ ] Android
- [ ] iOS
- [ ] SDK / mocks / docs only

## Checklist

- [ ] Builds locally (`scripts/build-public.sh` or platform-specific steps in README)
- [ ] Unit tests pass (`./gradlew :agent-sdk:testDebugUnitTest :self-shell:testDebugUnitTest` when Kotlin changed)
- [ ] Safety scan passes (`scripts/scan-public-safety.sh` or `.ps1`)
- [ ] No secrets, keystores, `.env`, or production API URLs added
- [ ] Mesh / wallet / Aura changes remain **mock / preview only** (no live SELF OS Core endpoints)
- [ ] AGPL-3.0 compatible contribution (see `LICENSE`)

## Test plan

<!-- Steps a reviewer can follow. -->
