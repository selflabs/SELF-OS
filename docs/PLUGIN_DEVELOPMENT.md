# Plugin development (SELF Apps)

## Manifest

Define a `SelfAppManifest` with stable `id`, semantic `version`, and human-readable metadata. `permissions` is a set of string labels for documentation (enforcement is up to your host app).

## Interface

Implement `SelfApp`:

- `getManifest()`
- `launch(context)` — receive an Android `Context` for Toasts, starting Activities, etc.
- `stop()` — release resources when unregistered.

## Registration

```kotlin
pluginRegistry.registerApp(MyApp())
```

## Launch

```kotlin
pluginRegistry.launchApp(context, "my-app-id")
```

See `examples/apps/` for `HelloSelfApp` and `PersonalDashboardApp`.
