s = open('tools/scripts/make-cms-profile.sh', encoding='utf-8').read()
lines = s.split('\n')
out = []
i = 0
while i < len(lines):
    line = lines[i]
    if 'openssl req -x509 -newkey' in line and '-subj' in '\n'.join(lines[i:i+3]):
        # replace this + next 2 lines
        out.append('  printf \'%s\\n\' "[req]" "distinguished_name = dn" "x509_extensions = v3_ext" "prompt = no" "[dn]" "CN = ExteraGram Sideload" "O = ExteraGram" "[v3_ext]" "keyUsage = digitalSignature" > "$CERT.cnf"')
        out.append('  openssl req -x509 -newkey rsa:2048 -keyout "$CERT.key" -out "$CERT.pem" -days 3650 -nodes -config "$CERT.cnf" 2>/dev/null')
        i += 3
        continue
    out.append(line)
    i += 1
open('tools/scripts/make-cms-profile.sh', 'w', encoding='utf-8', newline='\n').write('\n'.join(out))
print('replaced via line scan')
