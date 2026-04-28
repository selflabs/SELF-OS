# Local models

SELF Shell Community Edition does **not** bundle a large language model or ONNX runtime by default.

## Recommended directions

- Integrate **on-device** models via your chosen inference stack (e.g., executors compatible with Android NNAPI), behind a thin interface.
- Keep model weights and licenses **out of git**; download to app-private storage with explicit user consent.
- Never send user prompts to production SELF OS endpoints from this tree without a separate, reviewed integration.

## Placeholder

The example agents use deterministic string templates so the shell compiles and runs without ML dependencies.
