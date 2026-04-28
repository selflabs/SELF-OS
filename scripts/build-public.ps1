$ErrorActionPreference = "Stop"
$Root = Split-Path -Parent $PSScriptRoot
Set-Location $Root
& "$Root\gradlew.bat" :self-shell:assembleDebug --no-daemon
Write-Host "build-public: done" -ForegroundColor Green
