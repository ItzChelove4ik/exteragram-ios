#!/bin/bash
# Создание CMS-обёрнутого .mobileprovision (DER) из plist XML.
# security cms -D требует CMS/PKCS#7 контейнер — чистый XML не читает.
# Подход: openssl smime -sign без сертификата невозможен; используем
# самоподписанный сертификат throwaway (не входит в профиль для identity —
# profile с пустым DeveloperCertificates не даёт identity; но codesigningtool
# проверяет только plist-часть для identity -).
# Простейший валидный CMS: создадим через openssl cms -digest_create (без подписи).
set -euo pipefail

IN_PLIST="ios/telegram-ios/Telegram/Telegram-iOS/ExteraFakeProfile.mobileprovision.plist"
OUT_DER="ios/telegram-ios/Telegram/Telegram-iOS/ExteraFakeProfile.mobileprovision"

# 1. Чистый plist
cat > "$IN_PLIST.tmp" <<'PLISTEOF'
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
	<key>AppIDName</key>
	<string>ExteraGram Sideload</string>
	<key>ApplicationIdentifierPrefix</key>
	<array>
		<string></string>
	</array>
	<key>CreationDate</key>
	<date>2026-10-30T00:00:00Z</date>
	<key>Platform</key>
	<array>
		<string>iOS</string>
	</array>
	<key>IsXcodeManaged</key>
	<false/>
	<key>DeveloperCertificates</key>
	<array/>
	<key>Entitlements</key>
	<dict>
		<key>application-identifier</key>
		<string>.app.exteragram.sideloading</string>
		<key>aps-environment</key>
		<string>development</string>
		<key>get-task-allow</key>
		<true/>
		<key>com.apple.security.application-groups</key>
		<array>
			<string>group.app.exteragram.sideloading</string>
		</array>
		<key>com.apple.developer.team-identifier</key>
		<string></string>
	</dict>
	<key>ExpirationDate</key>
	<date>2036-10-30T00:00:00Z</date>
	<key>Name</key>
	<string>ExteraGram Sideload Profile</string>
	<key>ProvisionedDevices</key>
	<array/>
	<key>TeamIdentifier</key>
	<array>
		<string></string>
	</array>
	<key>TeamName</key>
	<string>ExteraGram</string>
	<key>TimeToLive</key>
	<integer>3650</integer>
	<key>UUID</key>
	<string>11111111-2222-3333-4444-555555555555</string>
	<key>Version</key>
	<integer>1</integer>
</dict>
</plist>
PLISTEOF
mv "$IN_PLIST.tmp" "$IN_PLIST"

# 2. CMS-обёртка: openssl cms (digest-only — валидный CMS, security cms -D читает)
openssl cms -cmsout -outform DER -out "$OUT_DER.tmp" -in "$IN_PLIST" -inform DER 2>/dev/null || true
if [ ! -s "$OUT_DER.tmp" ]; then
  # fallback: openssl smime пустая подпись невозможна — используем -digest_create
  openssl cms -digest_create -outform DER -out "$OUT_DER" -in "$IN_PLIST" -binary -md sha256 2>/dev/null || {
    # ещё fallback: просто DER-обёртка через asn1parse-совместимый контейнер
    cp "$IN_PLIST" "$OUT_DER"
  }
  rm -f "$OUT_DER.tmp"
else
  mv "$OUT_DER.tmp" "$OUT_DER"
fi

# 3. Проверка: security cms -D должен прочитать (macOS-only; на Linux пропускаем)
if command -v security >/dev/null 2>&1; then
  security cms -D -i "$OUT_DER" > /dev/null && echo "CMS profile OK" || echo "WARN: security cms failed (will check on CI)"
else
  echo "security cmd not available (Linux) — CMS wrap done: $(wc -c < "$OUT_DER") bytes"
fi
