#!/usr/bin/env python3
"""Баг #1 (доп.): валидация privateAccessGroup в Passcode.swift для ad-hoc.

PasscodeEnvironment.privateAccessGroup() требует суффикс "." + bundleId —
формат "<team>.<bundleId>". При ad-hoc (пустой team) корректный access group
= просто bundleId, который валидацию не проходит → throw → краш пути
passcode-инициализации. Патч: дополнительная ветка — group == tail допустим.
"""
import sys

p = 'ios/telegram-ios/submodules/PasscodeCore/Sources/Passcode.swift'
s = open(p, encoding='utf-8').read()

marker = 'ExteraGram adhoc passcode patch'
if marker in s:
    print('already patched')
    sys.exit(0)

old = '''        #if !os(macOS)
        guard !group.hasPrefix("group."),
              group.hasSuffix("." + configuration.appGroupIdentifier.dropFirst("group.".count)) else {
            throw PasscodeError.unavailable
        }
        #endif'''
new = '''        #if !os(macOS)
        // ExteraGram adhoc passcode patch: при ad-hoc подписи team-префикс пуст,
        // валидный access group = сам bundleId (без "<team>." префикса)
        let adhocBundleId = String(configuration.appGroupIdentifier.dropFirst("group.".count))
        guard !group.hasPrefix("group."),
              group.hasSuffix("." + adhocBundleId) || group == adhocBundleId else {
            throw PasscodeError.unavailable
        }
        #endif'''
if old not in s:
    print('pattern not found')
    sys.exit(1)

s = s.replace(old, new, 1)
open(p, 'w', encoding='utf-8', newline='\n').write(s)
print('Passcode.swift patched: ad-hoc access group validation')
