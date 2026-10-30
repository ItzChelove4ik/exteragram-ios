#!/usr/bin/env python3
"""
Генерация .alticon-наборов ExteraGram для telegram-ios из наших PNG (192px).

Формат telegram-ios Telegram-iOS/<Name>.alticon/:
  <Name>Icon@2x.png (120), <Name>Icon@3x.png (180),
  <Name>IconIpad.png (76), <Name>IconIpad@2x.png (152),
  <Name>IconLargeIpad@2x.png (167),
  <Name>NotificationIcon.png (20), @2x (40), @3x (60)

Плюс: AlternateIcons.plist / AlternateIcons-iPad.plist фрагменты + патч
Telegram/BUILD (alternate_icon_folders список).
"""
import json
import os
import shutil
from PIL import Image

SRC = 'ios/ExteraGram/Resources/AlternateIcons'  # 31 PNG (AppIcon-<id>.png)
TG_ICONS = 'ios/telegram-ios/Telegram/Telegram-iOS'
BUILD_PATCH_MARKER = 'ExteraAmethystIcon'  # маркер в alternate_icon_folders

SIZES = {
    'Icon@2x.png': 120,
    'Icon@3x.png': 180,
    'IconIpad.png': 76,
    'IconIpad@2x.png': 152,
    'IconLargeIpad@2x.png': 167,
    'NotificationIcon.png': 20,
    'NotificationIcon@2x.png': 40,
    'NotificationIcon@3x.png': 60,
}


def camel(name):
    return name.replace('_', ' ').title().replace(' ', '')


def main():
    icons = json.load(open('docs/app-icons.json', encoding='utf-8'))
    entries_plist = []
    entries_plist_ipad = []
    folder_names = []

    for ic in icons:
        if ic['id'] == 'default':
            continue
        name = camel(ic['id'])  # e.g. NyanCat, IceAge
        src = os.path.join(SRC, f"AppIcon-{ic['id'].replace('_', '')}.png")
        if not os.path.exists(src):
            print('MISSING', src)
            continue
        folder = f"{name}Extera"  # уникальный префикс, не конфликтует со стоковыми
        dst_dir = os.path.join(TG_ICONS, f"{folder}.alticon")
        os.makedirs(dst_dir, exist_ok=True)
        base = Image.open(src).convert('RGBA')
        for suffix, size in SIZES.items():
            im = base.resize((size, size), Image.LANCZOS)
            out = os.path.join(dst_dir, f"{folder}{suffix}")
            im.save(out, 'PNG')
        folder_names.append(folder)
        # plist-фрагменты (имя ключа = setAlternateIconName-имя)
        key = f"Extera-{ic['id']}"
        entries_plist.append(f'''    <key>{key}</key>
    <dict>
        <key>CFBundleIconFiles</key>
        <array>
            <string>{folder}Icon</string>
            <string>{folder}NotificationIcon</string>
        </array>
        <key>UIPrerenderedIcon</key>
        <true/>
    </dict>''')
        entries_plist_ipad.append(f'''    <key>{key}</key>
    <dict>
        <key>CFBundleIconFiles</key>
        <array>
            <string>{folder}Icon</string>
            <string>{folder}IconIpad</string>
            <string>{folder}IconLargeIpad</string>
            <string>{folder}NotificationIcon</string>
        </array>
        <key>UIPrerenderedIcon</key>
        <true/>
    </dict>''')

    # Merge в существующие plist-файлы (в конец dict перед </dict>)
    for plist, entries in [(f'{TG_ICONS}/AlternateIcons.plist', entries_plist),
                            (f'{TG_ICONS}/AlternateIcons-iPad.plist', entries_plist_ipad)]:
        s = open(plist, encoding='utf-8').read()
        if 'Extera-' in s:
            print('plist already has Extera entries:', plist)
            continue
        inject = '\n' + '\n'.join(entries) + '\n'
        s = s.replace('</dict>\n</plist>', inject + '</dict>\n</plist>')
        if s == open(plist, encoding='utf-8').read():
            # другой формат закрытия
            idx = s.rindex('</dict>')
            s = s[:idx] + inject + s[idx:]
        open(plist, 'w', encoding='utf-8', newline='\n').write(s)
        print('plist updated:', plist)

    # Патч Telegram/BUILD: alternate_icon_folders
    build_path = 'ios/telegram-ios/Telegram/BUILD'
    s = open(build_path, encoding='utf-8').read()
    if BUILD_PATCH_MARKER not in s:
        old = '''alternate_icon_folders = [
    "BlackIcon",'''
        new = 'alternate_icon_folders = [\n' + ',\n'.join(f'    "{n}"' for n in folder_names) + ',\n    "BlackIcon",'
        s = s.replace(old, new)
        open(build_path, 'w', encoding='utf-8', newline='\n').write(s)
        print('BUILD patched: alternate_icon_folders +', len(folder_names))
    else:
        print('BUILD already patched')

    print('Generated', len(folder_names), 'alticon folders')


if __name__ == '__main__':
    main()
