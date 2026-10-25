#!/usr/bin/env bash
# Identify ExteraGram modifications vs stock Telegram.
# 1) Classes only in mod (new files)
# 2) Classes present in both but changed (in org.telegram.* the mod edited stock code)
set -uo pipefail
MOD="decompiled/sources/sources"
STOCK="decompiled/diff-vs-stock/stock-java"
OUT="docs/mod-analysis.md"

{
echo "# ExteraGram vs stock Telegram — analysis"
echo
echo "Generated: $(date -u +%Y-%m-%dT%H:%M:%SZ)"
echo
echo "## 1. Mod-only packages (com.exteragram.*)"
echo
echo '```'
find "$MOD/com/exteragram" -maxdepth 2 -type d | sed "s|$MOD/||" | sort
echo '```'
echo
echo "## 2. org.telegram classes present in mod but NOT in stock source (new/renamed)"
echo
echo "Stock version: 2026-10-20 (0aae9598), mod versionName 12.10.6 — версии близки, но diff приблизителен: часть расхождений — расхождение версий стока, часть — мод. Каждый файл ниже требует ручной сверки."
echo
} > "$OUT"

# classes in mod org.telegram but not in stock
find "$MOD/org/telegram" -name '*.java' | sed "s|$MOD/||" | sort > /tmp/mod_classes.txt
find "$STOCK/org/telegram" -name '*.java' | sed "s|$STOCK/||" | sort > /tmp/stock_classes.txt
comm -23 /tmp/mod_classes.txt /tmp/stock_classes.txt | grep -v '\$\$' | grep -v '^\s*$' >> "$OUT"
echo "Wrote $OUT ($(wc -l < "$OUT") lines)"
