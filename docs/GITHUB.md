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

## License

The public tree uses **AGPL-3.0** (`LICENSE`). Keep monorepo copies of `public-self-shell/LICENSE` in sync when publishing.
