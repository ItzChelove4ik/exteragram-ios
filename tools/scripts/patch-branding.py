#!/usr/bin/env python3
"""Pravka: zamena Telegram-logotipov (tlogo PDF) na ExteraGram plane.

Zamenyaet imagesety s tlogo_24.pdf (belvi samolitik Telegram) na PNG-siluet
samolitika ExteraGram (template-rendering — tint cveta primenyaetsya kodom,
kak v originalne).

Imagesety:
- Telegram/Telegram-iOS/Icons.xcassets/Shortcuts/AppIcon.imageset (tlogo_24)
- submodules/TelegramUI/Images.xcassets/Chat/Context Menu/Telegram.imageset (tlogo_24)
Takzhe recolor vseh Intro*.tgs / PlaneLogo*.tgs (sinii Telegram -> krasnvi
ExteraGram #e83030) — vizualnii stil' vhodnih ekranov.
"""
import gzip
import json
import os
import shutil

BASE = 'ios/telegram-ios'
PLANE_SRC = 'ios/telegram-ios/extera_plane_48.png'  # белый силуэт 48px
PLANE_96 = 'ios/telegram-ios/extera_plane_96.png'
PLANE_192 = 'ios/telegram-ios/extera_plane_192.png'

RECOLOR_TARGETS = [
    f'{BASE}/Telegram/Telegram-iOS/Resources/IntroFragment.tgs',
    f'{BASE}/Telegram/Telegram-iOS/Resources/IntroLetter.tgs',
    f'{BASE}/Telegram/Telegram-iOS/Resources/IntroMail.tgs',
    f'{BASE}/Telegram/Telegram-iOS/Resources/IntroMessage.tgs',
    f'{BASE}/Telegram/Telegram-iOS/Resources/IntroPassword.tgs',
    f'{BASE}/Telegram/Telegram-iOS/Resources/IntroPhone.tgs',
    f'{BASE}/Telegram/Telegram-iOS/Resources/PlaneLogo.tgs',
    f'{BASE}/Telegram/Telegram-iOS/Resources/PlaneLogoPlain.tgs',
    f'{BASE}/submodules/PasswordSetupUI/Resources/TwoFactorSetupIntro.tgs',
    f'{BASE}/submodules/PasswordSetupUI/Resources/TwoFactorSetupDone.tgs',
]

EXTRA_RED = (0.909, 0.188, 0.188)  # #e83030


def recolor_blue(obj):
    if isinstance(obj, dict):
        for k, v in obj.items():
            if k == 'k' and isinstance(v, list) and len(v) == 4 and all(isinstance(x, (int, float)) for x in v):
                r, g, b, a = v
                if b > 0.3 and b > r + 0.1 and b > g + 0.05:
                    obj[k] = [EXTRA_RED[0], EXTRA_RED[1], EXTRA_RED[2], a]
            else:
                recolor_blue(v)
    elif isinstance(obj, list):
        for x in obj:
            recolor_blue(x)


def make_plane_images():
    """Белый силуэт самолётика ExteraGram из ic_launcher_foreground."""
    from PIL import Image
    fg = Image.open('decompiled/resources/res/mipmap-xxxhdpi/ic_launcher_foreground.webp').convert('RGBA')
    w = fg.size[0]
    visible = int(w * 66 / 108)
    left = (w - visible) // 2
    crop = fg.crop((left, left, left + visible, left + visible))
    alpha = crop.split()[3]
    white = Image.new('RGBA', crop.size, (255, 255, 255, 0))
    white.putalpha(alpha)
    white.resize((48, 48), Image.LANCZOS).save(PLANE_SRC, 'PNG')
    white.resize((96, 96), Image.LANCZOS).save(PLANE_96, 'PNG')
    white.resize((192, 192), Image.LANCZOS).save(PLANE_192, 'PNG')
    print('plane PNGs created')


def patch_imageset(path, force=False):
    """Заменяет tlogo PDF на PNG в imageset (Contents.json filename)."""
    if not os.path.isdir(path):
        print('skip (no dir):', path)
        return
    cj = os.path.join(path, 'Contents.json')
    if not os.path.exists(cj):
        return
    with open(cj, encoding='utf-8') as f:
        data = json.load(f)
    if not data.get('images'):
        return
    changed = False
    for img in data['images']:
        fn = img.get('filename', '')
        if fn.endswith('.pdf') and ('tlogo' in fn or 'Telegram' in fn or force):
            scale = img.get('scale', '2x')
            src = PLANE_96 if scale in ('2x', '3x') else PLANE_SRC
            new_name = 'extera_plane_' + scale.replace('x', '') + 'x.png'
            shutil.copyfile(src, os.path.join(path, new_name))
            # удалить старый pdf
            old = os.path.join(path, fn)
            if os.path.exists(old):
                os.remove(old)
            img['filename'] = new_name
            changed = True
    if changed:
        with open(cj, 'w', encoding='utf-8', newline=chr(10)) as f:
            json.dump(data, f, indent=2)
        print('imageset patched:', path)


def main():
    make_plane_images()

    # tgs recolor
    for p in RECOLOR_TARGETS:
        if not os.path.exists(p):
            continue
        data = open(p, 'rb').read()
        j = json.loads(gzip.decompress(data))
        recolor_blue(j)
        open(p, 'wb').write(gzip.compress(json.dumps(j).encode(), compresslevel=9, mtime=0))
    print('tgs recolored:', len(RECOLOR_TARGETS))

    # logo imagesets
    patch_imageset(f'{BASE}/Telegram/Telegram-iOS/Icons.xcassets/Shortcuts/AppIcon.imageset')
    patch_imageset(f'{BASE}/Telegram/Telegram-iOS/Icons.xcassets/Shortcuts/Account.imageset')
    patch_imageset(f'{BASE}/submodules/TelegramUI/Images.xcassets/Chat/Context Menu/Telegram.imageset')


if __name__ == '__main__':
    main()
