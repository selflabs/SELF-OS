# GitHub: `selflabs/SELF-OS`

The **canonical public repository** for **SELF OS Personal Intelligence** is:

**https://github.com/selflabs/SELF-OS**

Use that URL for stars, issues, pull requests, and cloning **SELF OS Personal Intelligence**.

## If you develop inside a monorepo

Some teams keep `public-self-shell/` inside a larger private tree while publishing to GitHub.

### Automated publish (recommended)

Workflow: `.github/workflows/publish-self-os.yml` in the **monorepo root** (not inside `public-self-shell` alone).

| Secret | Description |
|--------|-------------|
| `SELF_OS_PUBLISH_TOKEN` | GitHub PAT with `contents: write` on `selflabs/SELF-OS` |

Add under **Settings → Secrets and variables → Actions** in the monorepo. On push to `main` that touches `public-self-shell/**`, the workflow subtree-splits and pushes to `selflabs/SELF-OS`.

### Manual publish

1. Add the GitHub remote (once per clone):

   ```bash
   git remote add self-os https://github.com/selflabs/SELF-OS.git
   ```

2. Split, merge remote `main`, and push:

   ```bash
   git subtree split --prefix=public-self-shell -b self-os-publish
   git worktree add self-shell-wt self-os-publish
   cd self-shell-wt
   git fetch self-os main
   git merge FETCH_HEAD -m "Merge remote main"
   git push self-os HEAD:main
   ```

   Plain `git subtree push` often fails with non-fast-forward history; the merge step above is the supported path.

### Alternative: standalone clone

```bash
git clone https://github.com/selflabs/SELF-OS.git
cd SELF-OS
```

## CI and branch protection

Required checks: see [docs/CI.md](CI.md). Enable branch protection on `main` after workflows have run once.

## Releases

Tag community editions (example: `v0.1.0-community`). Pushing a tag runs `.github/workflows/release.yml` and creates a GitHub Release. History: [CHANGELOG.md](../CHANGELOG.md).

## License

The public tree uses **AGPL-3.0** (`LICENSE`). Keep monorepo copies of `public-self-shell/LICENSE` in sync when publishing.
