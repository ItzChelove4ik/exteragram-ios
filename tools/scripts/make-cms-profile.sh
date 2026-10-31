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
  KC="$HOME/Library/Keychains/extera.keychain-db"
  KC_PASS="extera123"
  rm -f "$KC"
  security create-keychain -p "$KC_PASS" "$KC" || true
  # добавить наш keychain в поисковый список (перезаписью с существующими)
  EXISTING=$(security list-keychains -d user | sed "s/^\"//;s/\"$//" | tr "
" " ")
  security list-keychains -d user -s "$KC" $EXISTING || true
  security unlock-keychain -p "$KC_PASS" "$KC" || true
  openssl pkcs12 -export -legacy -out "$CERT.p12" -inkey "$CERT.key" -in "$CERT.pem" -name "ExteraGram Sideload" -passout pass:"$KC_PASS" 2>/dev/null || openssl pkcs12 -export -out "$CERT.p12" -inkey "$CERT.key" -in "$CERT.pem" -name "ExteraGram Sideload" -passout pass:"$KC_PASS" 2>/dev/null
  # p12 import может потерять ключ — импортируем key и cert отдельно
  security import "$CERT.key" -k "$KC" -T /usr/bin/codesign -T /usr/bin/security || true
  security import "$CERT.pem" -k "$KC" -T /usr/bin/codesign -T /usr/bin/security -P "" || true
  security set-key-partition-list -S apple-tool:,apple: -k "$KC_PASS" "$KC" || true
  security find-identity -v -p codesigning | grep "ExteraGram" ||     security find-identity -v -p codesigning "$KC" | grep "ExteraGram" ||     (echo "identity diagnostics:"; security find-identity -v -p codesigning; security find-identity -v -p codesigning "$KC"; echo "dump cert:"; security find-certificate -a "$KC" | head -20)
fi

echo "CMS profile with throwaway cert ready: $(wc -c < "$OUT") bytes"
