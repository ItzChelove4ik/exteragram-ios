#!/usr/bin/env python3
"""Баг #1 (чёрный экран): keychain access group при ad-hoc подписи.

Причина краша при запуске (до отрисовки UI → чёрный экран):
  AppDelegate.swift:573 `try! PasscodeEnvironment.shared.configure(...)`
  → privateAccessGroup = BuildConfig.keychainAccessGroup(bundleId)
  → bundleSeedId из keychain-прове (префикс application-identifier)
  → при ad-hoc подписи префикс ПУСТОЙ → keychainAccessGroup = nil
  → configure(processRole: .mainApp) ТРЕБУЕТ не-nil → throw
  → try! = fatal crash → чёрный экран.

Фикс: keychainAccessGroupForBaseAppBundleId при пустом bundleSeedId
возвращает сам bundleId (без team-префикса). Для single-app keychain
(наш случай: приложение без расширений, SideLoadly) пустой access group
префикс допустим — kSecAttrAccessGroup без team работает в рамках одного
приложения. kSecClassGenericPassword-элементы в таком режиме создаются
без явного access group (system default = bundle id).
"""
import sys

p = 'ios/telegram-ios/submodules/BuildConfig/Sources/BuildConfig.m'
s = open(p, encoding='utf-8').read()

marker = 'ExteraGram adhoc keychain patch'
if marker in s:
    print('already patched')
    sys.exit(0)

old = '''+ (NSString * _Nullable)keychainAccessGroupForBaseAppBundleId:(NSString * _Nonnull)baseAppBundleId {
    NSString *bundleSeedId = [self bundleSeedId];
    if (bundleSeedId.length == 0 || baseAppBundleId.length == 0) {
        return nil;
    }
    return [bundleSeedId stringByAppendingFormat:@".%@", baseAppBundleId];
}'''
new = '''// ExteraGram adhoc keychain patch: при ad-hoc подписи bundleSeedId пуст
// (application-identifier без team-префикса) — возвращаем bundleId как
// access group: PasscodeEnvironment.configure требует не-nil privateAccessGroup
// для mainApp (иначе try! крашит приложение → чёрный экран при запуске).
+ (NSString * _Nullable)keychainAccessGroupForBaseAppBundleId:(NSString * _Nonnull)baseAppBundleId {
    if (baseAppBundleId.length == 0) {
        return nil;
    }
    NSString *bundleSeedId = [self bundleSeedId];
    if (bundleSeedId.length == 0) {
        // ad-hoc / без team: access group = bundle id (single-app keychain)
        return baseAppBundleId;
    }
    return [bundleSeedId stringByAppendingFormat:@".%@", baseAppBundleId];
}'''
if old not in s:
    print('pattern not found')
    sys.exit(1)

s = s.replace(old, new, 1)
open(p, 'w', encoding='utf-8', newline='\n').write(s)
print('BuildConfig.m patched: ad-hoc keychain access group')
