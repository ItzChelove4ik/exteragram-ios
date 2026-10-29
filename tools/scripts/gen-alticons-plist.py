#!/usr/bin/env python3
"""
Генерирует фрагмент Info.plist для 31 альтернативной иконки приложения
(CFBundleAlternateIcons) из docs/app-icons.json — как требует iOS для
setAlternateIconName. Имена: AppIcon-<id> (без подчёркиваний).

Также генерирует список icon files (полные иконки: 1024px из mipmap-xxxhdpi
масштабированы до 1024? iOS alternate icons кладутся напрямую как PNG-файлы
в бандл; наш CI-скрипт build-icons готовит их в AppIcons.xcassets — для
alternate icons нужны отдельные PNG, конвертация отдельным шагом ниже).
"""
import json

icons = json.load(open('docs/app-icons.json', encoding='utf-8'))

def alt_name(ic):
    return 'AppIcon-' + ic['id'].replace('_', '')

entries = []
for ic in icons:
    if ic['id'] == 'default':
        continue
    name = alt_name(ic)
    entries.append((name, ic))

lines = []
lines.append('<key>CFBundleIcons</key>')
lines.append('<dict>')
lines.append('\t<key>CFBundlePrimaryIcon</key>')
lines.append('\t<dict>')
lines.append('\t\t<key>CFBundleIconName</key>')
lines.append('\t\t<string>AppIcon</string>')
lines.append('\t\t<key>UIPrerenderedIcon</key>')
lines.append('\t\t<false/>')
lines.append('\t</dict>')
lines.append('\t<key>CFBundleAlternateIcons</key>')
lines.append('\t<dict>')
for name, ic in entries:
    fname = name + '.png'
    lines.append(f'\t\t<key>{name}</key>')
    lines.append('\t\t<dict>')
    lines.append('\t\t\t<key>CFBundleIconFiles</key>')
    lines.append('\t\t\t<array>')
    lines.append(f'\t\t\t\t<string>{name}</string>')
    lines.append('\t\t\t</array>')
    lines.append('\t\t\t<key>UIPrerenderedIcon</key>')
    lines.append('\t\t\t<false/>')
    lines.append('\t\t</dict>')
lines.append('\t</dict>')
lines.append('</dict>')

open('ios/patches/alternate-icons.plist', 'w', encoding='utf-8', newline='\n').write('\n'.join(lines) + '\n')
print('plist fragment:', len(entries), 'alternate icons')

# JSON-версия для merge в CI
import plistlib
data = {
    'CFBundleIcons': {
        'CFBundlePrimaryIcon': {'CFBundleIconName': 'AppIcon', 'UIPrerenderedIcon': False},
        'CFBundleAlternateIcons': {
            name: {'CFBundleIconFiles': [name], 'UIPrerenderedIcon': False}
            for name, _ in entries
        },
    }
}
with open('ios/patches/alternate-icons.json', 'wb') as f:
    plistlib.dump(data, f)
print('json plist written')
