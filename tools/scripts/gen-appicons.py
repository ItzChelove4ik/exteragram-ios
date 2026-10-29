import re
import json

src = open('decompiled/sources/sources/com/exteragram/messenger/appicons/GeneratedAppIcons.java', encoding='utf-8', errors='ignore').read()

# Точная структура AppIcon.of(id, component, availability, titleRes, title, descriptionRes, description, color, authorUsername, authorName, background, backgroundIsColor, foreground, monochrome)
body = re.search(r'return new AppIcon\[\]\{(.*)\};', src, re.S).group(1)
calls = re.findall(r'AppIcon\.of\((.*?)\)(?:,|$)', body, re.S)

xml = open('decompiled/resources/assets/extera_locales/values/extera.xml', encoding='utf-8', errors='ignore').read()
titles = dict(re.findall(r'name="(AppIcon[A-Za-z0-9]+)"[^>]*>([^<]*)<', xml))
infos = dict(re.findall(r'name="(AppIcon[A-Za-z0-9]+Info)"[^>]*>([^<]*)<', xml))
xml_res = open('decompiled/resources/res/values/strings.xml', encoding='utf-8', errors='ignore').read()
titles.update(dict(re.findall(r'name="(AppIcon[A-Za-z0-9]+)"[^>]*>([^<]*)<', xml_res)))
infos.update(dict(re.findall(r'name="(AppIcon[A-Za-z0-9]+Info)"[^>]*>([^<]*)<', xml_res)))

def split_args(call):
    args = []
    depth = 0
    cur = ''
    in_str = False
    for ch in call:
        if in_str:
            cur += ch
            if ch == '"':
                in_str = False
            continue
        if ch == '"':
            in_str = True
            cur += ch
            continue
        if ch == '(':
            depth += 1
            cur += ch
            continue
        if ch == ')':
            depth -= 1
            cur += ch
            continue
        if ch == ',' and depth == 0:
            args.append(cur.strip())
            cur = ''
            continue
        cur += ch
    if cur.strip():
        args.append(cur.strip())
    return args

icons = []
for call in calls:
    a = split_args(call)
    if len(a) < 14:
        continue
    icon_id = a[0].strip('"') if a[0].startswith('"') else a[0]
    # PluginsConstants.Settings.DEFAULT = "default" on Android
    if 'DEFAULT' in icon_id:
        icon_id = 'default'
    comp = a[1].strip('"')
    availability = a[2].strip('"')
    # title: a[3] = R.string.X or 0; a[4] = literal title or null
    title = None
    m = re.match(r'R\.string\.(\w+)', a[3])
    if m:
        title = titles.get(m.group(1))
    if not title and a[4] not in ('null',):
        title = a[4].strip('"')
    # description
    info = None
    m = re.match(r'R\.string\.(\w+)', a[5])
    if m:
        info = infos.get(m.group(1))
    if info is None and a[6] != 'null':
        info = a[6].strip('"')
    if info:
        info = info.replace("\\'", "'")
    author_username = a[8].strip('"') if a[8] != 'null' else None
    author_name = a[9].strip('"') if a[9] != 'null' else None
    # background/foreground resource names
    def res_name(x):
        if x in ('0', 'null'):
            return None
        m = re.search(r'\.(?:mipmap|drawable|color)\.(\w+)', x)
        return m.group(1) if m else None
    background = res_name(a[10])
    background_is_color = 'R.color' in a[10]
    foreground = res_name(a[12])
    monochrome = res_name(a[13])
    icons.append({
        'id': icon_id,
        'component': comp,
        'availability': availability,
        'title': title or icon_id,
        'info': info or '',
        'authorUsername': author_username,
        'authorName': author_name,
        'background': background,
        'backgroundIsColor': background_is_color,
        'foreground': foreground,
        'monochrome': monochrome,
    })

print('icons parsed:', len(icons))
json.dump(icons, open('docs/app-icons.json', 'w', encoding='utf-8'), indent=1, ensure_ascii=False)
for r in icons[:8]:
    print(r['id'], '|', r['title'], '|', r['info'][:28], '|', r['authorUsername'], '|', r['availability'])
