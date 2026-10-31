#!/bin/bash
# Создание self-signed сертификата + CMS-профиля с ним, импорт в keychain.
# codesigningtool ищет identity по сертификатам профиля (DeveloperCertificates).
# Генерируем throwaway cert, встраиваем в профиль, импортируем в CI-keychain.
set -euo pipefail

WORKDIR="ios/telegram-ios/Telegram/Telegram-iOS"
CERT="$WORKDIR/extera-throwaway"
PLIST="$WORKDIR/ExteraFakeProfile.mobileprovision.plist"
OUT="$WORKDIR/ExteraFakeProfile.mobileprovision"

# 1. Self-signed certificate (throwaway, ad-hoc signing identity)
if [ ! -f "$CERT.key" ]; then
  printf '%s\n' "[req]" "distinguished_name = dn" "x509_extensions = v3_ext" "prompt = no" "[dn]" "CN = ExteraGram Sideload" "O = ExteraGram" "[v3_ext]" "keyUsage = digitalSignature" > "$CERT.cnf"
  openssl req -x509 -newkey rsa:2048 -keyout "$CERT.key" -out "$CERT.pem" -days 3650 -nodes -config "$CERT.cnf" 2>/dev/null
fi

# 2. DER-сертификат для встраивания в профиль
openssl x509 -in "$CERT.pem" -outform DER -out "$CERT.der"

# 3. Plist профиля с сертификатом внутри (base64 DER)
CERT_B64=$(base64 < "$CERT.der" | tr -d '\n')

cat > "$PLIST" <<PLISTEOF
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
	<array>
		<data>$CERT_B64</data>
	</array>
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

# 4. CMS-обёртка (security cms -D требует PKCS#7)
openssl cms -digest_create -outform DER -out "$OUT" -in "$PLIST" -binary -md sha256

# 5. Импорт identity в keychain (macOS-only — на CI)
if command -v security >/dev/null 2>&1; then
  # создаём отдельный keychain (надёжно на CI)
  KC="$WORKDIR/extera.keychain-db"
  KC_PASS="extera123"
  rm -f "$KC"
  security create-keychain -p "$KC_PASS" "$KC" 2>/dev/null || true
  security list-keychains -d user -s "$KC" $(security list-keychains -d user | tr '
' ' ') 2>/dev/null || true
  security unlock-keychain -p "$KC_PASS" "$KC" 2>/dev/null || true
  openssl pkcs12 -export -out "$CERT.p12" -inkey "$CERT.key" -in "$CERT.pem"     -name "ExteraGram Sideload" -passout pass:"$KC_PASS" 2>/dev/null
  security import "$CERT.p12" -k "$KC" -P "$KC_PASS" -T /usr/bin/codesign 2>&1 | head -2 || true
  security set-key-partition-list -S apple-tool:,apple: -s -k "$KC_PASS" "$KC" 2>/dev/null || true
  security find-identity -v -p codesigning 2>/dev/null | grep "ExteraGram" || echo "identity not found in keychains"
fi

echo "CMS profile with throwaway cert ready: $(wc -c < "$OUT") bytes"
