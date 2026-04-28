# Mock services

All clients in `mock/` are **local-only** and return **fake** data.

| Client | Package | Behavior |
|--------|---------|----------|
| `HarmonyMeshClient` | `mock.harmony` | Mock node status, tasks, proofs, earnings. |
| `SelfWalletClient` | `mock.wallet` | Mock balance, transactions, payout reference strings. |
| `AppStoreClient` | `mock.appstore` | In-memory catalog install/remove. |
| `AuraDataClient` | `mock.aura` | Mock categories, consent map, value estimates. |

Replace these with your own implementations **without** embedding private production URLs. If you add networking, do so in a fork with your own backends and keys — never commit secrets here.
