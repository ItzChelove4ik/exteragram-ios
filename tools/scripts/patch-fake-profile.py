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

# 3. Создаём сам файл .mobileprovision (пустой — SideLoadly перезапишет)
os.makedirs('ios/telegram-ios/Telegram/Telegram-iOS', exist_ok=True)
prof_path = 'ios/telegram-ios/Telegram/Telegram-iOS/ExteraFakeProfile.mobileprovision'
if not os.path.exists(prof_path):
    # минимальный валидный plist-профиль: обёртка XML
    content = b'<?xml version="1.0" encoding="UTF-8"?>\n<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">\n<plist version="1.0">\n<dict>\n\t<key>ExteraGramFakeProfile</key>\n\t<true/>\n</dict>\n</plist>\n'
    open(prof_path, 'wb').write(content)
    print('fake profile file written')

print('BUILD patched with fake profile')
