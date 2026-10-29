#!/usr/bin/env python3
"""
Конвертер локализации ExteraGram: Android strings.xml -> iOS Localizable.strings.

34 локали из decompiled/resources/assets/extera_locales -> ios/ExteraGram/Resources/
<locale>.lproj/Extera.strings. Ключи сохраняются 1-в-1 (extera-ключи мода).

Плюс парсинг plurals (<item quantity="one|few|many|other">) в отдельные ключи
<key>__one / __few / __many / __other — как в Android (port of getQuantityString
реализуется в ExteraLocaleController).
"""
import os
import re
import sys
import xml.etree.ElementTree as ET

SRC = 'decompiled/resources/assets/extera_locales'
DST = 'ios/ExteraGram/Resources'

LANG_MAP = {
    'values': 'Base',
    'values-in': 'id',
    'values-iw': 'he',
    'values-pt-rBR': 'pt-BR',
    'values-zh-rCN': 'zh-Hans',
    'values-zh-rTW': 'zh-Hant',
    'values-nb': 'nb',
}

def esc(s: str) -> str:
    return s.replace('\\', '\\\\').replace('"', '\\"').replace('\n', '\\n').replace('\t', '\\t')

def parse_xml(path):
    """Возвращает dict key->value и key->dict(quantity->value) для plurals."""
    strings = {}
    plurals = {}
    try:
        tree = ET.parse(path)
    except ET.ParseError:
        return strings, plurals
    root = tree.getroot()
    for el in root:
        tag = el.tag.split('}')[-1]
        name = el.get('name')
        if not name:
            continue
        if tag == 'string':
            # unescape android escapes: \' \" \n
            val = (el.text or '')
            val = val.replace('\\\'', '\'').replace('\\"', '"').replace('\\n', '\n')
            strings[name] = val
        elif tag == 'plurals':
            q = {}
            for item in el:
                qty = item.get('quantity')
                if qty:
                    q[qty] = (item.text or '').replace('\\\'', '\'').replace('\\"', '"').replace('\\n', '\n')
            plurals[name] = q
    return strings, plurals

def main():
    total = 0
    for d in sorted(os.listdir(SRC)):
        src_dir = os.path.join(SRC, d)
        if not os.path.isdir(src_dir):
            continue
        xml_file = os.path.join(src_dir, 'extera.xml')
        if not os.path.isfile(xml_file):
            continue
        lang = LANG_MAP.get(d, d.replace('values-', ''))
        lproj = os.path.join(DST, f'{lang}.lproj')
        os.makedirs(lproj, exist_ok=True)
        strings, plurals = parse_xml(xml_file)
        lines = []
        for k in sorted(strings):
            lines.append(f'"{k}" = "{esc(strings[k])}";')
        for name, q in sorted(plurals.items()):
            for qty, val in q.items():
                lines.append(f'"{name}__{qty}" = "{esc(val)}";')
        with open(os.path.join(lproj, 'Extera.strings'), 'w', encoding='utf-8', newline='\n') as f:
            f.write('\n'.join(lines) + '\n')
        total += 1
        print(f'{d} -> {lang}.lproj ({len(lines)} строк)')
    print(f'Итого локалей: {total}')

if __name__ == '__main__':
    main()
