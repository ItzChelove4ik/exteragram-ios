#!/usr/bin/env python3
"""Баг #1 (главный фикс): app group container при ad-hoc/без provisioning.

AppDelegate.swift:699: `guard let appGroupUrl = maybeAppGroupUrl else {
presentNative("Error 2"); return }` — app group контейнер существует ТОЛЬКО
с provisioning profile (entitlement com.apple.security.application-groups).
При ad-hoc подписи (SideLoadly) контейнера нет → alert «Error 2» → return
→ приложение не инициализируется → чёрный экран.

Фикс: fallback на локальную папку приложения (Documents/…/app-group):
теле­грам использует appGroupUrl только как хранилище (rootPath); обычная
папка работает для single-app без расширений (наш случай — расширения
выпилены из сборки).
"""
import sys

p = 'ios/telegram-ios/submodules/TelegramUI/Sources/AppDelegate.swift'
s = open(p, encoding='utf-8').read()

marker = 'ExteraGram appgroup fallback patch'
if marker in s:
    print('already patched')
    sys.exit(0)

old = '''        guard let appGroupUrl = maybeAppGroupUrl else {
            self.mainWindow?.presentNative(UIAlertController(title: nil, message: "Error 2", preferredStyle: .alert))
            return true
        }'''
new = '''        // ExteraGram appgroup fallback patch: ad-hoc подпись (SideLoadly) не даёт
        // app group контейнер — используем локальную папку приложения.
        // Расширения выпилены из сборки, отдельный контейнер не нужен.
        let appGroupUrl: URL
        if let maybeAppGroupUrl = maybeAppGroupUrl {
            appGroupUrl = maybeAppGroupUrl
        } else {
            let fallbackPath = (NSSearchPathForDirectoriesInDomains(.documentDirectory, .userDomainMask, true).first ?? NSTemporaryDirectory()) + "/app-group"
            try? FileManager.default.createDirectory(atPath: fallbackPath, withIntermediateDirectories: true)
            appGroupUrl = URL(fileURLWithPath: fallbackPath)
        }'''
if old not in s:
    print('pattern not found')
    sys.exit(1)

s = s.replace(old, new, 1)
open(p, 'w', encoding='utf-8', newline='\n').write(s)
print('AppDelegate.swift patched: app group → Documents fallback')
