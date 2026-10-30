p = '.github/workflows/ios-build.yml'
lines = open(p, encoding='utf-8').read().split('\n')
new_line = '            ios/ExteraGram/ExteraPillStack.swift \\'
out = []
for line in lines:
    out.append(line)
    if 'ios/ExteraGram/ExteraSubsystemModules.swift' in line:
        out.append(new_line)
open(p, 'w', encoding='utf-8', newline='\n').write('\n'.join(out))
print('added')
