#!/usr/bin/env python3
"""Патч Telegram/BUILD: отключить расширения приложения для SideLoadly-билда.

rules_apple требует provisioning_profile для ios_extension при device-билде —
без профилей (SideLoadly подпишет сам) расширения собрать нельзя.
Расширения (Share/Notification/Widget/Intents/Broadcast) выпиливаются из
embed-списка приложения: функциональность основного приложения не страдает
(нотификации-сервис, шэр-экстеншен и виджеты — опциональные цели).

Идемпотентно.
"""
import sys

p = 'ios/telegram-ios/Telegram/BUILD'
s = open(p, encoding='utf-8').read()

if 'ExteraGram sideload extensions patch' in s:
    print('already patched')
    sys.exit(0)

old = '''    extensions = select({
        ":disableExtensionsSetting": [],
        "//conditions:default": [
            ":ShareExtension",
            ":NotificationContentExtension",
            ":NotificationServiceExtension" + notificationServiceExtensionVersion,
            ":IntentsExtension",
            ":WidgetExtension",
            ":BroadcastUploadExtension",
        ],
    }),'''

new = '''    # ExteraGram sideload extensions patch: расширения выпилены — rules_apple
    # требует provisioning profiles для device-билдов, а SideLoadly-сборка идёт
    # без профилей (SideLoadly переподписывает ipa при установке).
    extensions = [],'''

if old not in s:
    print('pattern not found')
    sys.exit(1)

s = s.replace(old, new, 1)
open(p, 'w', encoding='utf-8', newline='\n').write(s)
print('extensions removed from app target')
