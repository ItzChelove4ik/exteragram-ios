#!/usr/bin/env bash
# Build the package map: where original Telegram code lives (org.telegram.*)
# vs. ExteraGram mod code (own packages/classes), from jadx sources.
# Usage: bash package-map.sh <decompiled/sources/root> <out.md>
set -euo pipefail
SRC="${1:?sources root}"
OUT="${2:?output md}"
mkdir -p "$(dirname "$OUT")"

{
  echo "# Package map — ExteraGram APK (jadx sources)"
  echo
  echo "Generated: $(date -u +%Y-%m-%dT%H:%M:%SZ)"
  echo
  echo "## Top-level packages"
  echo
  echo '| Package | .java files | Role |'
  echo '|---|---|---|'
  for d in "$SRC"/sources/* "$SRC"/sources/*/*; do
    [ -d "$d" ] || continue
    rel="${d#"$SRC"/sources/}"
    # only top 2 levels
    case "$rel" in */*/*) continue ;; esac
    n=$(find "$d" -name '*.java' | wc -l)
    case "$rel" in
      org/telegram) role="Оригинальный код Telegram (ядро)" ;;
      org/*) role="Прочие org.* библиотеки" ;;
      com/*) role="Сторонние библиотеки / vendor" ;;
      ru/*|ua/*|io/*|me/*|app/*) role="Возможные пакеты мода — проверить" ;;
      *) role="Прочее" ;;
    esac
    echo "| \`$rel\` | $n | $role |"
  done | sort -t'|' -k2 -rn
  echo
} > "$OUT"
echo "Wrote $OUT"
