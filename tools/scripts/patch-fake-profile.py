#!/usr/bin/env python3
"""Патч Telegram/BUILD: фиктивный provisioning profile для device-билда
без подписи (SideLoadly-сборка).

rules_apple жёстко требует provisioning_profile != None для ios_application
при --ios_multi_cpus=arm64 (device), даже с disableProvisioningProfiles.
Решение: генерируем фиктивный .mobileprovision (пустой plist-обёртку) и
подключаем его в build_configuration repository — профиль используется
только на этапе анализа; реальная подпись будет заменена SideLoadly.

Плюс: убираем select-условие для главного приложения (всегда фиктивный),
и добавляем файл в build-input/configuration-repository/provisioning.
"""
import os
import sys

p = 'ios/telegram-ios/Telegram/BUILD'
s = open(p, encoding='utf-8').read()

if 'ExteraGram fake profile patch' in s:
    print('already patched')
    sys.exit(0)

# 1. Главное приложение: профиль всегда фиктивный
old_app = '''    provisioning_profile = select({
        ":disableProvisioningProfilesSetting": None,
        "//conditions:default": ":Telegram_xcode_profile" if telegram_use_xcode_managed_codesigning else "@build_configuration//provisioning:Telegram.mobileprovision",
    }),
    entitlements = ":TelegramEntitlements.entitlements",'''
new_app = '''    # ExteraGram fake profile patch: фиктивный профиль для безпрофильной
    # device-сборки (SideLoadly переподписывает ipa при установке)
    provisioning_profile = ":ExteraFakeProfileFile",
    entitlements = ":TelegramEntitlements.entitlements",'''
if old_app not in s:
    print('app pattern not found')
    sys.exit(1)
s = s.replace(old_app, new_app, 1)

# 2. Описание фиктивного профиля
anchor = 'ios_application(\n    name = "Telegram",'
fake_def = '''filegroup(
    name = "ExteraFakeProfileFile",
    srcs = ["Telegram-iOS/ExteraFakeProfile.mobileprovision"],
)

'''
if 'ExteraFakeProfile' not in s.split(anchor)[0]:
    s = s[: s.index(anchor)] + fake_def + s[s.index(anchor):]

open(p, 'w', encoding='utf-8', newline='\n').write(s)

# 3. Создаём сам файл .mobileprovision: валидный plist со всеми ключами,
#    которые читает rules_apple provisioning_profile_tool (AppIDName и пр.)
os.makedirs('ios/telegram-ios/Telegram/Telegram-iOS', exist_ok=True)
prof_path = 'ios/telegram-ios/Telegram/Telegram-iOS/ExteraFakeProfile.mobileprovision'
content = '''<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
\t<key>AppIDName</key>
\t<string>ExteraGram Sideload</string>
\t<key>ApplicationIdentifierPrefix</key>
\t<array>
\t\t<string>EXTERAGRAM</string>
\t</array>
\t<key>CreationDate</key>
\t<date>2026-10-30T00:00:00Z</date>
\t<key>Platform</key>
\t<array>
\t\t<string>iOS</string>
\t</array>
\t<key>IsXcodeManaged</key>
\t<false/>
\t<key>DeveloperCertificates</key>
\t<array/>
\t<key>Entitlements</key>
\t<dict>
\t\t<key>application-identifier</key>
\t\t<string>.app.exteragram.sideloading</string>
\t\t<key>aps-environment</key>
\t\t<string>development</string>
\t\t<key>get-task-allow</key>
\t\t<true/>
\t</dict>
\t<key>ExpirationDate</key>
\t<date>2036-10-30T00:00:00Z</date>
\t<key>Name</key>
\t<string>ExteraGram Sideload Profile</string>
\t<key>ProvisionedDevices</key>
\t<array/>
\t<key>TeamIdentifier</key>
\t<array>
\t\t<string>EXTERAGRAM</string>
\t</array>
\t<key>TeamName</key>
\t<string>ExteraGram</string>
\t<key>TimeToLive</key>
\t<integer>3650</integer>
\t<key>UUID</key>
\t<string>11111111-2222-3333-4444-555555555555</string>
\t<key>Version</key>
\t<integer>1</integer>
</dict>
</plist>
'''
with open(prof_path, 'w', encoding='utf-8', newline='\n') as f:
    f.write(content)
print('fake profile file written')

print('BUILD patched with fake profile')
