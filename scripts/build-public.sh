#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"
./gradlew :self-shell:assembleDebug --no-daemon
echo "build-public: done"
