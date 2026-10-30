#!/usr/bin/env python3
"""Патч WindowContent.swift: guard iOS 27.1-API под Xcode 26 (CI-раннер).

telegram-ios master требует Xcode 27.1 (versions.json), на macos-latest
максимум 26.6. Единственное использование нового API в Display:
UIView.reservedRegions(kind: .division). Под Xcode 26 код не компилируется
(нет API в SDK) — обходим #if compiler(>=6.3)/swift-версией: код внутри
availability-guard'а и безвреден при возврате nil.
"""
import sys

p = 'ios/telegram-ios/submodules/Display/Source/WindowContent.swift'
s = open(p, encoding='utf-8').read()

marker = 'ExteraGram Xcode26 compat patch'
if marker in s:
    print('already patched')
    sys.exit(0)

old = '''    fileprivate var activeDivision: CGRect? {
        if #available(iOS 27.1, *) {
            return self.eventView.reservedRegions(kind: .division).first(where: \\.isActive)?.frame
        }
        return nil
    }'''
new = '''    fileprivate var activeDivision: CGRect? {
        // ExteraGram Xcode26 compat patch: reservedRegions недоступен в SDK Xcode 26
        // (CI-раннер); под Xcode 27.1 (требуемый versions.json) ветка активна
        #if canImport UIKit, swift(>=6.4)
        if #available(iOS 27.1, *) {
            return self.eventView.reservedRegions(kind: .division).first(where: \\.isActive)?.frame
        }
        #endif
        return nil
    }'''
if old not in s:
    # try without exact whitespace
    print('pattern not found — searching loose')
    import re
    m = re.search(r'fileprivate var activeDivision: CGRect\? \{.*?\n    \}', s, re.S)
    if not m:
        print('activeDivision not found at all')
        sys.exit(1)
    print('loose match:', m.group(0)[:120])
    sys.exit(1)

s = s.replace(old, new, 1)
open(p, 'w', encoding='utf-8', newline='\n').write(s)
print('WindowContent patched')
