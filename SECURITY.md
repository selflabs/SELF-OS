# Security

## Reporting

If you believe you have found a security issue that affects **SELF OS Personal Intelligence** or its users, please report it responsibly through the contact method published on the maintainer’s website or repository security policy when available.

Do not file public issues that include exploit details for unfixed vulnerabilities.

## Scope of this repository

This tree is intentionally **not** wired to production SELF OS infrastructure. It must not contain:

- API keys, tokens, or service account files
- Keystores, signing keys, or certificates used for release builds
- Private backend URLs or cloud function endpoints
- Wallet seeds, mnemonics, or signing material

Run `scripts/scan-public-safety.ps1` (Windows) or `scripts/scan-public-safety.sh` (Unix) before tagging a release.

## Mock data

Preview balances, tasks, and “ESSENCE” strings in the app are **deterministic mocks**. They do not represent real assets or obligations.
