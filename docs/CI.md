# Continuous integration

Required checks for merging to `main` on [selflabs/SELF-OS](https://github.com/selflabs/SELF-OS):

| Workflow | Job | What it verifies |
|----------|-----|------------------|
| [Android build](.github/workflows/android.yml) | `android` | Unit tests, debug APK, release APK (R8) |
| [iOS build](.github/workflows/ios.yml) | `ios` | Simulator debug/release builds + unit tests |
| [Safety scan](.github/workflows/safety-scan.yml) | `scan` | No secrets / risky patterns in tree |

## Branch protection (maintainers)

In **Settings → Branches → Branch protection rules** for `main`:

1. Require a pull request before merging (recommended).
2. Require status checks to pass:
   - `android`
   - `ios`
   - `scan`
3. Do not allow bypassing the above settings (optional).

If check names differ in the GitHub UI, pick the three jobs listed above from the autocomplete list after a workflow has run at least once.

Apply via CLI (org admin):

```bash
gh api repos/selflabs/SELF-OS/branches/main/protection -X PUT \
  -f required_status_checks[strict]=true \
  -f required_status_checks[contexts][]=android \
  -f required_status_checks[contexts][]=ios \
  -f required_status_checks[contexts][]=scan \
  -f enforce_admins=true \
  -f required_pull_request_reviews[required_approving_review_count]=0 \
  -F restrictions=
```

Adjust review count and restrictions to match your team policy.
