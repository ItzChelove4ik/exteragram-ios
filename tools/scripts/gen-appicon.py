#!/usr/bin/env python3
"""Правка #4: иконка приложения → оригинальная ExteraGram.

Заменяет Telegram.icon (композерный asset) на фиксированные PNG:
- Telegram-iOS/Telegram.icon/ → полный AppIcon-набор PNG (все iOS-размеры),
  icon.json убран, папка превращается в набор готовых PNG.
- rules_apple app_icons принимает filegroup PNG-файлов из папки .icon
  (формат: AppIcon60x60@2x.png и т.д. — как в стандартных xcassets).

Иконка составлена из adaptive-icon APK:
  фон #e83030 (ic_background), foreground ic_launcher_foreground (белый
  самолётик ExteraGram), безопасная зона 66/108, без alpha.
"""
import os
from PIL import Image

SRC_FG = 'decompiled/resources/res/mipmap-xxxhdpi/ic_launcher_foreground.webp'
ICON_DIR = 'ios/telegram-ios/Telegram/Telegram-iOS/Telegram.icon'
BG = (232, 48, 48)  # #e83030

# iOS AppIcon-размеры (имена как в asset catalogs — rules_apple берёт по имени)
SIZES = {
    'AppIcon20x20@2x.png': 40,
    'AppIcon20x20@3x.png': 60,
    'AppIcon29x29@2x.png': 58,
    'AppIcon29x29@3x.png': 87,
    'AppIcon40x40@2x.png': 80,
    'AppIcon40x40@3x.png': 120,
    'AppIcon60x60@2x.png': 120,
    'AppIcon60x60@3x.png': 180,
    'AppIcon76x76@2x.png': 152,
    'AppIcon83.5x83.5@2x.png': 167,
    'AppIcon1024x1024.png': 1024,
    # legacy
    'AppIcon57x57@2x.png': 114,
    'AppIcon72x72@2x.png': 144,
}


def compose_base() -> Image:
    fg = Image.open(SRC_FG).convert('RGBA')
    src_w = fg.size[0]  # 432
    visible = int(src_w * 66 / 108)  # 264 — safe zone
    left = (src_w - visible) // 2
    crop = fg.crop((left, left, left + visible, left + visible))
    # до 1024
    base = crop.resize((1024, 1024), Image.LANCZOS)
    canvas = Image.new('RGB', (1024, 1024), BG)
    canvas.paste(base, (0, 0), base)
    return canvas


def main():
    base = compose_base()
    os.makedirs(ICON_DIR, exist_ok=True)
    # очистить старый композерный asset
    for fn in os.listdir(ICON_DIR):
        os.remove(os.path.join(ICON_DIR, fn))
    for name, size in SIZES.items():
        im = base.resize((size, size), Image.LANCZOS)
        im.save(os.path.join(ICON_DIR, name), 'PNG')
    print('AppIcon set:', len(SIZES), 'files in', ICON_DIR)


if __name__ == '__main__':
    main()
