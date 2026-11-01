lines = open('tools/scripts/gen-appicon.py', encoding='utf-8').read().split('\n')
out = []
i = 0
while i < len(lines):
    l = lines[i]
    stripped = l.strip()
    cond1 = stripped.startswith('with open(os.path.join(APPICONSET') and not l.rstrip().endswith("f:")
    cond2 = stripped.startswith('new = ') and i + 1 < len(lines) and 'app_icons' in lines[i+1] and l.rstrip().endswith("icon)")
    if cond1:
        out.append("with open(os.path.join(APPICONSET, 'Contents.json'), 'w', encoding='utf-8', newline='\\n') as f:")
        i += 2
        continue
    if cond2:
        out.append("new = '# ExteraGram app icons patch: DefaultAppIcon.xcassets' + chr(10) + '    app_icons = [\":DefaultAppIcon\"],'")
        i += 2
        continue
    out.append(l)
    i += 1
s = '\n'.join(out)
open('tools/scripts/gen-appicon.py', 'w', encoding='utf-8', newline='\n').write(s)
import ast
ast.parse(s)
print('repaired OK')
