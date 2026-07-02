# Security

## Reporting a vulnerability

If you believe you have found a security issue that affects **SELF OS Personal Intelligence** or its users, please report it **privately**. Do not open a public GitHub issue with exploit details, proof-of-concept code, or steps that could harm users before a fix is available.

### Preferred: GitHub private reporting

1. Open **[Report a vulnerability](https://github.com/selflabs/SELF-OS/security/advisories/new)** on this repository (GitHub Private Vulnerability Reporting).
2. Include a clear description, affected version or commit, impact, and reproduction steps if possible.

### Email

If you cannot use GitHub reporting, email **hello@mono-ai.com** with subject line **`[SECURITY] SELF-OS CE`** and the same details. This address is published on [selflabs.ai/contact](https://selflabs.ai/contact).

### What we aim for

| Stage | Target |
|-------|--------|
| Acknowledgement | Within **72 hours** |
| Status update | Within **14 days** for confirmed issues |
| Fix (Community Edition) | Best effort; critical issues prioritized |

We welcome good-faith research on the **Community Edition** mock-only tree. Issues in production SELF OS Core or live services outside this repo should be reported through official Self Labs channels when available.

## Scope of this repository

This tree is intentionally **not** wired to production SELF OS infrastructure. It must not contain:

- API keys, tokens, or service account files
- Keystores, signing keys, or certificates used for store releases
- Private backend URLs or cloud function endpoints
- Wallet seeds, mnemonics, or signing material

Run `scripts/scan-public-safety.ps1` (Windows) or `scripts/scan-public-safety.sh` (Unix) before tagging a release.

## Out of scope

- Mock balances, mesh tasks, and preview “ESSENCE” strings (deterministic fixtures, not real assets)
- Third-party model providers you wire in yourself (review their terms and key handling)
- Issues that require access to private SELF OS Core infrastructure not present in this repo

## Mock data

Preview balances, tasks, and “ESSENCE” strings in the app are **deterministic mocks**. They do not represent real assets or obligations.
