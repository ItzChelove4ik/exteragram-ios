#!/usr/bin/env python3
"""Pravka #3: imya prilozheniya -> exteraGram (vo vsex mestax).

1. Telegram/BUILD: TelegramInfoPlist (glavnyi app) + AppNameInfoPlist (rasshireniya):
   CFBundleDisplayName / CFBundleName Telegram -> exteraGram
2. *.lproj/InfoPlist.strings: lokalizovannye imena -> exteraGram
"""
import glob
import re

APP_NAME = 'exteraGram'
NL = chr(10)

# 1. BUILD plist fragments: all DisplayName/Name keys
p = 'ios/telegram-ios/Telegram/BUILD'
s = open(p, encoding='utf-8').read()
count = 0
for key in ['CFBundleDisplayName', 'CFBundleName']:
    pattern = '<key>' + key + '</key>' + NL + '    <string>Telegram</string>'
    s, n = re.subn(re.escape(pattern),
                   '<key>' + key + '</key>' + NL + '    <string>' + APP_NAME + '</string>', s)
    count += n
if count:
    with open(p, 'w', encoding='utf-8', newline=NL) as f:
        f.write(s)
    print('BUILD plist fragments:', count, 'keys replaced')

# 2. InfoPlist.strings (all locales)
changed = 0
for path in glob.glob('ios/telegram-ios/Telegram/Telegram-iOS/*.lproj/InfoPlist.strings'):
    s = open(path, encoding='utf-8').read()
    s2 = re.sub(r'"CFBundleDisplayName"' + r'\s*=\s*"[^"]*";',
                '"CFBundleDisplayName" = "' + APP_NAME + '";', s)
    if 'CFBundleDisplayName' not in s2:
        s2 = s.rstrip(NL) + NL + '"CFBundleDisplayName" = "' + APP_NAME + '";' + NL
    if s2 != s:
        with open(path, 'w', encoding='utf-8', newline=NL) as f:
            f.write(s2)
        changed += 1
print('InfoPlist.strings updated:', changed, 'locales')
