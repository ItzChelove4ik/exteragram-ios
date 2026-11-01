#!/usr/bin/env python3
"""Pravka #4: ikonka ExteraGram v DefaultAppIcon.xcassets (appiconset)."""
import json
import os
import shutil
from PIL import Image

SRC_FG = 'decompiled/resources/res/mipmap-xxxhdpi/ic_launcher_foreground.webp'
XCASSETS = 'ios/telegram-ios/Telegram/Telegram-iOS/DefaultAppIcon.xcassets'
APPICONSET = os.path.join(XCASSETS, 'AppIcon.appiconset')
BG = (232, 48, 48)

SIZES = [
    ('AppIcon20x20@2x.png', 'iphone', '2x', '20x20'),
    ('AppIcon20x20@3x.png', 'iphone', '3x', '20x20'),
    ('AppIcon29x29@2x.png', 'iphone', '2x', '29x29'),
    ('AppIcon29x29@3x.png', 'iphone', '3x', '29x29'),
    ('AppIcon40x40@2x.png', 'iphone', '2x', '40x40'),
    ('AppIcon40x40@3x.png', 'iphone', '3x', '40x40'),
    ('AppIcon60x60@2x.png', 'iphone', '2x', '60x60'),
    ('AppIcon60x60@3x.png', 'iphone', '3x', '60x60'),
    ('AppIcon20x20@2x~ipad.png', 'ipad', '2x', '20x20'),
    ('AppIcon29x29@2x~ipad.png', 'ipad', '2x', '29x29'),
    ('AppIcon40x40@2x~ipad.png', 'ipad', '2x', '40x40'),
    ('AppIcon76x76@2x~ipad.png', 'ipad', '2x', '76x76'),
    ('AppIcon83.5x83.5@2x~ipad.png', 'ipad', '2x', '83.5x83.5'),
    ('AppIcon1024x1024.png', 'ios-marketing', '1x', '1024x1024'),
]

NL = chr(10)


def scale_px(size, scale):
    return int(float(size.split('x')[0]) * float(scale.replace('x', '')))


def compose_base():
    fg = Image.open(SRC_FG).convert('RGBA')
    src_w = fg.size[0]
    visible = int(src_w * 66 / 108)
    left = (src_w - visible) // 2
    crop = fg.crop((left, left, left + visible, left + visible))
    base = crop.resize((1024, 1024), Image.LANCZOS)
    canvas = Image.new('RGB', (1024, 1024), BG)
    canvas.paste(base, (0, 0), base)
    return canvas


def main():
    if os.path.isdir(APPICONSET):
        shutil.rmtree(APPICONSET)
    os.makedirs(APPICONSET, exist_ok=True)

    base = compose_base()
    images = []
    for filename, idiom, scale, size in SIZES:
        px = scale_px(size, scale)
        im = base.resize((px, px), Image.LANCZOS)
        im.save(os.path.join(APPICONSET, filename), 'PNG')
        images.append({'filename': filename, 'idiom': idiom, 'scale': scale, 'size': size})
    contents = {'images': images, 'info': {'version': 1, 'author': 'xcode'}}
    with open(os.path.join(APPICONSET, 'Contents.json'), 'w', encoding='utf-8', newline=NL) as f:
        json.dump(contents, f, indent=2)
    print('AppIcon.appiconset:', len(images), 'icons')

    llc = os.path.join(XCASSETS, 'AppIconLLC.appiconset')
    if os.path.isdir(llc):
        shutil.rmtree(llc)
        print('removed AppIconLLC.appiconset')

    p = 'ios/telegram-ios/Telegram/BUILD'
    s2 = open(p, encoding='utf-8').read()
    if 'ExteraGram app icons patch' not in s2:
        old = 'app_icons = [ ":{}_icon".format(name) for name in composer_icon_folders ],'
        new = '# ExteraGram app icons patch: DefaultAppIcon.xcassets' + NL + '    app_icons = [":DefaultAppIcon"],'
        if old in s2:
            s2 = s2.replace(old, new, 1)
            with open(p, 'w', encoding='utf-8', newline=NL) as f:
                f.write(s2)
            print('BUILD patched: app_icons = DefaultAppIcon')
        else:
            print('WARN: app_icons pattern not found')


if __name__ == '__main__':
    main()
