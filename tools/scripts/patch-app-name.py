#!/usr/bin/env python3
"""Правка #3: имя приложения → exteraGram.

1. CFBundleDisplayName в AppNameInfoPlist (Telegram/BUILD) → exteraGram
2. CFBundleDisplayName во всех *.lproj/InfoPlist.strings (ar/ko имеют локализованное
   имя «تيليجرام»/«텔레그램») → exteraGram везде
3. Добавляем CFBundleDisplayName в локали, где его нет (fallback Info.plist)
"""
import glob
import os
import sys

APP_NAME = 'exteraGram'

# 1. BUILD: AppNameInfoPlist
p = 'ios/telegram-ios/Telegram/BUILD'
s = open(p, encoding='utf-8').read()
old = '''    <key>CFBundleDisplayName</key>
    <string>Telegram</string>'''
new = f'''    <key>CFBundleDisplayName</key>
    <string>{APP_NAME}</string>
    <key>CFBundleName</key>
    <string>{APP_NAME}</string>'''
if 'ExteraGram app name patch' not in s:
    if old not in s:
        print('WARN: AppNameInfoPlist pattern not found')
    else:
        s = s.replace(old, new, 1)
        # маркер
        s = s.replace('plist_fragment(\n    name = "AppNameInfoPlist",',
                      'plist_fragment(\n    # ExteraGram app name patch\n    name = "AppNameInfoPlist",', 1)
        open(p, 'w', encoding='utf-8', newline='\n').write(s)
        print('BUILD: AppNameInfoPlist -> exteraGram')

# 2. InfoPlist.strings: локализованные имена
changed = 0
for path in glob.glob('ios/telegram-ios/Telegram/Telegram-iOS/*.lproj/InfoPlist.strings'):
    s = open(path, encoding='utf-8').read()
    import re
    # заменить существующее значение CFBundleDisplayName (любое)
    s2 = re.sub(r'"CFBundleDisplayName"\s*=\s*"[^"]*";',
                f'"CFBundleDisplayName" = "{APP_NAME}";', s)
    # добавить, если нет
    if 'CFBundleDisplayName' not in s2:
        s2 = s.rstrip('\n') + f'\n"CFBundleDisplayName" = "{APP_NAME}";\n'
    if s2 != s:
        open(path, 'w', encoding='utf-8', newline='\n').write(s2)
        changed += 1
print(f'InfoPlist.strings updated: {changed} locales')

# 3. Код: локальные отображения имени приложения
# (AppDelegate/AccountContext appName если есть — базовый Telegram UI берёт имя из
#  Info.plist в основных местах; plist-фрагмент покрывает домашний экран и настройки)
