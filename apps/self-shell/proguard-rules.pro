# SELF OS Personal Intelligence — release shrinking (Community Edition)

-keepattributes *Annotation*, InnerClasses, EnclosingMethod, Signature, SourceFile, LineNumberTable

# Kotlin / coroutines
-dontwarn kotlinx.coroutines.**
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# App, SDK, examples, and mocks (no reflection-based agent loading in CE)
-keep class ai.selflabs.selfshell.** { *; }

# Compose (stable runtime)
-dontwarn androidx.compose.**
-keep class androidx.compose.runtime.** { *; }
