# GitHub: `selflabs/SELF-OS`

The **canonical public repository** for SELF Shell is:

**https://github.com/selflabs/SELF-OS**

Use that URL for stars, issues, pull requests, and cloning the open personal intelligence shell.

## If you develop inside a monorepo

Some teams keep `public-self-shell/` inside a larger private tree while publishing to GitHub. In that case:

1. Add the GitHub remote (once per clone):

   ```bash
   git remote add self-os https://github.com/selflabs/SELF-OS.git
   ```

2. Push only the `public-self-shell` subtree to the remote’s branch (example: `main`):

   ```bash
   git subtree push --prefix=public-self-shell self-os main
   ```

   You need push access to `selflabs/SELF-OS`. The first push may require reconciling history if the GitHub repo already has commits (for example an initial `LICENSE`); coordinate with maintainers or use a PR branch instead of force-pushing.

### Alternative: standalone clone

To work only against GitHub:

```bash
git clone https://github.com/selflabs/SELF-OS.git
cd SELF-OS
```

(After the subtree is published, the layout should match this project’s `public-self-shell` contents at the repo root.)

## License note

The license file on GitHub may differ from placeholders in a monorepo copy (for example **AGPL-3.0** on `selflabs/SELF-OS` vs a **PolyForm** placeholder locally). **Treat the license on the default branch of `selflabs/SELF-OS` as authoritative** for that published tree unless maintainers say otherwise.
