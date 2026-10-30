p = '.github/workflows/ios-build.yml'
lines = open(p, encoding='utf-8').read().split('\n')
add = [
    'ExteraFeed.swift',
    'ExteraDrawer.swift',
    'ExteraAiController.swift',
    'ExteraPluginEngine.swift',
]
out = []
for line in lines:
    out.append(line)
    if 'ios/ExteraGram/ExteraPillStack.swift' in line:
        for a in add:
            out.append('            ios/ExteraGram/' + a + ' \\')
open(p, 'w', encoding='utf-8', newline='\n').write('\n'.join(out))
print('added', len(add) * 2)
