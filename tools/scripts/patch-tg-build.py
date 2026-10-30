#!/usr/bin/env python3
"""Патч Telegram/BUILD: подключить ExteraGram как депенденси приложения.

Идемпотентно: проверяет маркер. Добавляет "//submodules/ExteraGram:ExteraGram"
в deps основного ios_application "Telegram" (через frameworks-список нельзя —
это swift_library, не framework; используем deps через мост objc —
проще: добавляем в deps одного из framework-таргетов приложения).

Надёжный путь: swift_library добавляется в deps Telegram через
SwiftObjCBridge — но у правила ios_application deps допустимы.
"""
import re
import sys

p = "ios/telegram-ios/Telegram/BUILD"
marker = "//submodules/ExteraGram:ExteraGram"

s = open(p, encoding="utf-8").read()
if marker in s:
    print("BUILD already patched")
    sys.exit(0)

# Найдём первый ios_application(name = "Telegram") и добавим dep.
i = s.index('ios_application(\n    name = "Telegram",')
if i == -1:
    print("ios_application Telegram not found")
    sys.exit(1)
# Ищем ближайший список deps/frameworks после него
j = s.index("frameworks = [", i)
insert = "\n        \"//submodules/ExteraGram:ExteraGram\","
s = s[: j + len("frameworks = [")] + insert + s[j + len("frameworks = [") :]
open(p, "w", encoding="utf-8", newline="\n").write(s)
print("BUILD patched: ExteraGram dep added")
