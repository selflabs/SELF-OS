# Scans code-like paths under public-self-shell for risky patterns.
# Excludes docs/ and AUDIT_PUBLIC_EXPORT.md from content scans (they may cite threats by name).
$ErrorActionPreference = "Stop"
$Root = Split-Path -Parent $PSScriptRoot
Set-Location $Root

$exitCode = 0

$riskFiles = @(
    "*.jks", "*.keystore", "google-services.json", "GoogleService-Info.plist",
    "service-account*.json", "*.pem", "*.p12", "*.key", ".env", ".env.*"
)
foreach ($pat in $riskFiles) {
    $hits = Get-ChildItem -Path $Root -Recurse -Force -ErrorAction SilentlyContinue |
        Where-Object { $_.FullName -notmatch "\\build\\|\\\.gradle\\" } |
        Where-Object { $_.Name -ne ".env.example" } |
        Where-Object { $_.Name -like $pat -or $_.Name -eq ".env" }
    if ($hits) {
        Write-Warning "Risky filename pattern '$pat' found:"
        $hits | ForEach-Object { Write-Warning $_.FullName }
        $exitCode = 1
    }
}

$scanDirs = @("apps", "sdk", "mock", "examples")
$extensions = @("*.kt", "*.kts", "*.xml", "*.properties", "*.json")
$patterns = @(
    "apiKey", "API_KEY", "secret", "bearer", "private_key", "service_account",
    "firebase", "supabase", "telnyx", "stripe", "solana",
    "mnemonic", "seed phrase", "BEGIN PRIVATE KEY", "AIza"
)

foreach ($dir in $scanDirs) {
    $base = Join-Path $Root $dir
    if (-not (Test-Path $base)) { continue }
    foreach ($ext in $extensions) {
        Get-ChildItem -Path $base -Recurse -File -Filter $ext -ErrorAction SilentlyContinue |
            Where-Object { $_.FullName -notmatch "\\build\\" } |
            ForEach-Object {
                $text = Get-Content $_.FullName -Raw -ErrorAction SilentlyContinue
                if (-not $text) { return }
                foreach ($p in $patterns) {
                    if ($text -match [regex]::Escape($p)) {
                        Write-Warning "Pattern '$p' in $($_.FullName)"
                        $exitCode = 1
                    }
                }
            }
    }
}

# Root Gradle files
foreach ($gf in @("build.gradle.kts", "settings.gradle.kts", "gradle.properties")) {
    $fp = Join-Path $Root $gf
    if (Test-Path $fp) {
        $text = Get-Content $fp -Raw
        foreach ($p in $patterns) {
            if ($text -match [regex]::Escape($p)) {
                Write-Warning "Pattern '$p' in $fp"
                $exitCode = 1
            }
        }
    }
}

if ($exitCode -ne 0) {
    Write-Host "scan-public-safety: FAILED (see warnings)" -ForegroundColor Red
} else {
    Write-Host "scan-public-safety: OK (code paths checked)" -ForegroundColor Green
}
exit $exitCode
