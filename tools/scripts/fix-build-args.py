p = '.github/workflows/build-ipa.yml'
s = open(p, encoding='utf-8').read()

old1 = '''            --xcodeManagedCodesigning \\
            --disableProvisioningProfiles \\
            --disableExtensions \\
            --overrideXcodeVersion \\
            --buildNumber=1 \\'''
new1 = '''            --xcodeManagedCodesigning \\
            --bazelArguments="--//Telegram:disableProvisioningProfiles" \\
            --buildNumber=1 \\'''

old2 = '''            --xcodeManagedCodesigning \\
            --disableProvisioningProfiles \\
            --disableExtensions \\
            --buildNumber=1 \\'''
new2 = new1

if old1 in s:
    s = s.replace(old1, new1)
    print('variant 1 replaced')
elif old2 in s:
    s = s.replace(old2, new2)
    print('variant 2 replaced')
else:
    # fallback: line-based removal
    lines = s.split('\n')
    out = []
    skip_next_flag = False
    for line in lines:
        stripped = line.strip()
        if stripped in ('--disableProvisioningProfiles \\', '--disableExtensions \\', '--overrideXcodeVersion \\'):
            continue
        if stripped.startswith('--xcodeManagedCodesigning'):
            out.append(line)
            out.append('            --bazelArguments="--//Telegram:disableProvisioningProfiles" \\')
            continue
        out.append(line)
    s = '\n'.join(out)
    print('fallback applied')

# also update comment lines mentioning removed flags
s = s.replace('          # disableProvisioningProfiles: без mobileprovision\n', '')
s = s.replace('          # disableExtensions: без extension-таргетов (не нужны для SideLoadly)\n', '')
open(p, 'w', encoding='utf-8', newline='\n').write(s)
print('done')
