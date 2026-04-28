# Local models

SELF OS Personal Intelligence (Community Edition) does **not** bundle a large language model or ONNX runtime by default.

## Cloud model providers

You can route agent logic to hosted APIs — for example **[Resonatia](https://resonatia.io/docs)** ([Resonant Intelligence Gateway](https://resonatia.io); chat completions and related endpoints per [their docs](https://resonatia.io/docs)), [OpenRouter](https://openrouter.ai), OpenAI, Anthropic (Claude), Google (Gemini), or others. Use **your** API keys (e.g. Bearer token where documented), follow each vendor’s terms, and keep keys out of source control.

### Resonatia

Integrate from Android agents using any HTTP client: authenticate as in [Resonatia’s Quick Start](https://resonatia.io/docs), send chat-style requests to their API base (see current host and paths in the docs), and map responses back inside `Agent.handleRequest`. This repo does not ship a bundled Resonatia SDK; treat it like any other HTTPS model backend you opt into.

## Recommended directions

- Integrate **on-device** models via your chosen inference stack (e.g., executors compatible with Android NNAPI), behind a thin interface.
- Keep model weights and licenses **out of git**; download to app-private storage with explicit user consent.
- Never send user prompts to production SELF OS endpoints from this tree without a separate, reviewed integration.

## Placeholder

The example agents use deterministic string templates so the shell compiles and runs without ML dependencies.
