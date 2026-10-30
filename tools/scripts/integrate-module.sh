#!/bin/bash
# Интеграция ExteraGram-модуля в сборку telegram-ios (копирование + BUILD-патч).
# Запускается CI перед bazel build. Идемпотентен.
set -euo pipefail

TG_IOS="ios/telegram-ios"
MODULE_SRC="ios/ExteraGram"
ASSETS="assets"

# 1. Swift-файлы модуля
DEST_SW="$TG_IOS/submodules/ExteraGram"
mkdir -p "$DEST_SW/Sources"
cp "$MODULE_SRC"/*.swift "$DEST_SW/Sources/"

# 2. Ресурсы модуля (локали, registration_dates.bin)
mkdir -p "$DEST_SW/Resources"
cp -r "$MODULE_SRC/Resources/"*.lproj "$DEST_SW/Resources/" 2>/dev/null || true
cp "$MODULE_SRC/Resources/registration_dates.bin" "$DEST_SW/Resources/" 2>/dev/null || true

# 3. Иконки ExteraGram (только для UIImage(named:) в UI выбора иконок)
rm -rf "$DEST_SW/Resources/Icons.xcassets" "$DEST_SW/Resources/AppIcons.xcassets"
cp -r "$ASSETS/Icons.xcassets" "$DEST_SW/Resources/Icons.xcassets"
cp -r "$ASSETS/AppIcons.xcassets" "$DEST_SW/Resources/AppIcons.xcassets"

# 4. BUILD-файл модуля
cat > "$DEST_SW/BUILD" <<'BUILDEOF'
load("@build_bazel_rules_swift//swift:swift.bzl", "swift_library")

swift_library(
    name = "ExteraGram",
    module_name = "ExteraGram",
    srcs = glob([
        "Sources/*.swift",
    ]),
    data = [
        ":resources",
    ],
    visibility = ["//visibility:public"],
)

filegroup(
    name = "resources",
    srcs = glob([
        "Resources/**/*",
    ]),
)
BUILDEOF

# 5. Патч Telegram/BUILD: подключить модуль как депенденси приложения (идемпотентно)
python3 tools/scripts/patch-tg-build.py

# 6. Патч Make.py: проброс --bazelArguments (dead в апстриме)
python3 tools/scripts/patch-make-bazelargs.py

echo "ExteraGram module integrated."
