#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"
FAIL=0

scan_file() {
  local f="$1"
  if grep -Eini \
    'apiKey|API_KEY|secret|bearer|private_key|service_account|firebase|supabase|telnyx|stripe|solana|mnemonic|seed phrase|BEGIN PRIVATE KEY|AIza' \
    "$f" >/dev/null 2>&1; then
    echo "WARNING: risky pattern in $f" >&2
    FAIL=1
  fi
}

while IFS= read -r -d '' f; do
  case "$f" in
    */build/*) continue ;;
  esac
  scan_file "$f"
done < <(find apps sdk mock examples -type f \( -name '*.kt' -o -name '*.kts' -o -name '*.xml' -o -name '*.properties' -o -name '*.json' \) -print0 2>/dev/null)

for gf in build.gradle.kts settings.gradle.kts gradle.properties; do
  [[ -f "$gf" ]] || continue
  scan_file "$gf"
done

for risky in google-services.json GoogleService-Info.plist .env .env.local; do
  if find . -path ./build -prune -o -name "$risky" -print -quit | grep -q .; then
    echo "WARNING: found file matching $risky" >&2
    FAIL=1
  fi
done

if [[ "$FAIL" -ne 0 ]]; then
  echo "scan-public-safety: FAILED" >&2
  exit 1
fi
echo "scan-public-safety: OK"
