#!/usr/bin/env python3
"""Патч ShareScreen/BUILD: -disable-sil-ownership-verifier (Swift compiler bug workaround).

Swift 26.6 крэшится на ShareScreen (OSSA lifetime verifier bug — известный
класс ошибок, компилятор сам советует этот флаг). Опция отключает проверку
в этом модуле; рантайм-эффекта нет.
"""
import sys

p = 'ios/telegram-ios/submodules/TelegramUI/Components/ShareScreen/BUILD'
s = open(p, encoding='utf-8').read()

if 'disable-sil-ownership-verifier' in s:
    print('already patched')
    sys.exit(0)

old = '''swift_library(
    name = "ShareScreen",
    module_name = "ShareScreen",
    srcs = glob([
        "Sources/**/*.swift",
    ]),
    copts = [
        "-warnings-as-errors",
    ],'''
new = '''swift_library(
    name = "ShareScreen",
    module_name = "ShareScreen",
    srcs = glob([
        "Sources/**/*.swift",
    ]),
    copts = [
        "-warnings-as-errors",
        # ExteraGram workaround: Swift 26.6 compiler crash (OSSA lifetime)
        "-Xfrontend", "-disable-sil-ownership-verifier",
    ],'''
if old not in s:
    print('pattern not found')
    sys.exit(1)

s = s.replace(old, new, 1)
open(p, 'w', encoding='utf-8', newline='\n').write(s)
print('ShareScreen BUILD patched')
