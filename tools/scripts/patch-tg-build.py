#!/usr/bin/env python3
"""Патч Telegram/BUILD: подключить ExteraGram как dep модуля Lib.

swift_library в deps ios_application не линкуется статически в бинарник —
dep должен быть в swift-модуле, который линкуется (Lib → main binary).
Идемпотентно.
"""
import sys

p = "ios/telegram-ios/Telegram/BUILD"
marker = "//submodules/ExteraGram:ExteraGram"

s = open(p, encoding="utf-8").read()
if marker in s:
    print("BUILD already patched")
    sys.exit(0)

# Lib deps: после "//submodules/TelegramUI:TelegramUI",
i_lib = s.index('name = "Lib"')
i_dep = s.index('"//submodules/TelegramUI:TelegramUI",', i_lib)
insert = '"//submodules/ExteraGram:ExteraGram",'
s = s[:i_dep] + insert + chr(10) + '        ' + s[i_dep:]
open(p, "w", encoding="utf-8", newline=chr(10)).write(s)
print("BUILD patched: ExteraGram dep added to Lib")
