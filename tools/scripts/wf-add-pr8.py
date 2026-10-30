p = '.github/workflows/ios-build.yml'
lines = open(p, encoding='utf-8').read().split('\n')
out = []
for line in lines:
    out.append(line)
    if 'ios/ExteraGram/ExteraPluginEngine.swift' in line:
        out.append('            ios/ExteraGram/ExteraSettingsBackup.swift \\')
open(p, 'w', encoding='utf-8', newline='\n').write('\n'.join(out))
print('added')
