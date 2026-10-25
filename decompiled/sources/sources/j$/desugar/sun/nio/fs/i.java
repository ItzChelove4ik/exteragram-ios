package j$.desugar.sun.nio.fs;

import j$.nio.file.FileSystem;
import j$.nio.file.Path;
import j$.nio.file.attribute.y;
import j$.nio.file.g0;
import j$.nio.file.q0;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class i extends FileSystem {

    /* renamed from: a, reason: collision with root package name */
    public final String f159a;

    /* renamed from: b, reason: collision with root package name */
    public final String f160b;

    /* renamed from: c, reason: collision with root package name */
    public final m f161c;

    public i(m r1, String r2, String r3) {
        this.f161c = r1;
        this.f159a = r2;
        this.f160b = r3;
    }

    @Override // j$.nio.file.FileSystem
    public final Iterable a() {
        throw new UnsupportedOperationException(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    @Override // j$.nio.file.FileSystem
    public final g0 b(String r17) {
        int r1 = r17.indexOf(58);
        if (r1 <= 0) goto L124;
        if (r1 == r17.length()) goto L124;
        String r3 = r17.substring(0, r1);
        boolean r4 = true;
        String r0 = r17.substring(r1 + 1);
        if (r3.equalsIgnoreCase("glob") == false) goto L118;
        StringBuilder r12 = new StringBuilder("^");
        int r32 = 0;
        boolean r5 = false;
    L10:
        if (r32 >= r0.length()) goto L113;
        int r6 = r32 + 1;
        char r7 = r0.charAt(r32);
        if (r7 != '*') goto L14;
        boolean r16 = r4;
        if (g.y(r0, r6) != '*') goto L110;
        r12.append(".*");
        r32 = r32 + 2;
    L111:
        r4 = r16;
        goto L10
    L110:
        r12.append("[^/]*");
    L94:
        r32 = r6;
        goto L111
    L14:
        if (r7 == ',') goto L103;
        char r8 = '/';
        if (r7 != '/') goto L18;
        r16 = r4;
        r12.append(r7);
        goto L94
    L18:
        if (r7 != '?') goto L20;
        r16 = r4;
        r12.append("[^/]");
        goto L94
    L20:
        if (r7 != '{') goto L22;
        r16 = r4;
        if (r5 == true) goto L100;
        r12.append("(?:(?:");
        r5 = r16;
        goto L94
    L100:
        throw new PatternSyntaxException("Cannot nest groups", r0, r32);
    L22:
        if (r7 != '}') goto L24;
        r16 = r4;
        if (r5 == false) goto L95;
        r12.append("))");
        r5 = false;
        goto L94
    L95:
        r12.append('}');
        goto L94
    L24:
        if (r7 != '[') goto L26;
        r12.append("[[^/]&&[");
        if (g.y(r0, r6) != '^') goto L46;
        r12.append("\\^");
        r32 = r32 + 2;
    L52:
        boolean r62 = false;
        char r122 = 0;
    L54:
        if (r32 >= r0.length()) goto L86;
        int r72 = r32 + 1;
        char r13 = r0.charAt(r32);
        if (r13 == ']') goto L57;
        if (r13 == r8) goto L85;
        if (r13 == '\\') goto L66;
        if (r13 == '[') goto L66;
        r16 = r4;
        if (r13 == '&') goto L64;
    L68:
        r12.append(r13);
        if (r13 == '-') goto L71;
        r8 = '/';
        r4 = r16;
        r62 = r4;
        r32 = r72;
        r7 = r13;
        r122 = r7;
        goto L54
    L71:
        if (r62 == false) goto L82;
        int r63 = r32 + 2;
        r7 = g.y(r0, r72);
        if (r7 == 0) goto L80;
        if (r7 == ']') goto L80;
        if (r7 < r122) goto L79;
        r12.append(r7);
        r8 = '/';
        r4 = r16;
        r32 = r63;
        r62 = false;
        goto L54
    L79:
        throw new PatternSyntaxException("Invalid range", r0, r32 - 1);
    L80:
        r32 = r63;
    L87:
        if (r7 != ']') goto L90;
        r12.append("]]");
        goto L111
    L90:
        throw new PatternSyntaxException("Missing ']", r0, r32 - 1);
    L82:
        throw new PatternSyntaxException("Invalid range", r0, r32);
    L64:
        if (g.y(r0, r72) != '&') goto L68;
    L67:
        r12.append('\\');
    L66:
        r16 = r4;
        goto L67
    L85:
        throw new PatternSyntaxException("Explicit 'name separator' in class", r0, r32);
    L57:
        r16 = r4;
        r32 = r72;
        r7 = r13;
        goto L87
    L86:
        r16 = r4;
        goto L87
    L46:
        if (g.y(r0, r6) != '!') goto L49;
        r12.append('^');
        r6 = r32 + 2;
    L49:
        if (g.y(r0, r6) != '-') goto L51;
        r12.append(SignatureVisitor.SUPER);
        r32 = r6 + 1;
        goto L52
    L51:
        r32 = r6;
        goto L52
    L26:
        if (r7 == '\\') goto L32;
        if (".^$+{[]|()".indexOf(r7) == (-1)) goto L30;
        r12.append('\\');
    L30:
        r12.append(r7);
        r16 = r4;
        goto L94
    L32:
        if (r6 == r0.length()) goto L41;
        r32 = r32 + 2;
        char r64 = r0.charAt(r6);
        if ("\\*?[{".indexOf(r64) == (-1)) goto L37;
    L38:
        r12.append('\\');
    L39:
        r12.append(r64);
        r16 = r4;
        goto L111
    L37:
        if (".^$+{[]|()".indexOf(r64) == (-1)) goto L39;
    L41:
        throw new PatternSyntaxException("No character to escape", r0, r32);
    L103:
        r16 = r4;
        if (r5 == false) goto L106;
        r12.append(")|(?:");
        goto L94
    L106:
        r12.append(',');
        goto L94
    L113:
        if (r5 == true) goto L116;
        r12.append(Typography.dollar);
        r0 = r12.toString();
    L120:
        return new h(Pattern.compile(r0), 0);
    L116:
        throw new PatternSyntaxException("Missing '}", r0, r32 - 1);
    L118:
        if (r3.equalsIgnoreCase("regex") == true) goto L120;
        j$.nio.file.b.e("Syntax '", r3, "' not recognized");
        return null;
    L124:
        throw new IllegalArgumentException(String.format("Requested <syntax>:<pattern> spliterator(':') position(%d) is out of bound in %s", new Object[]{Integer.valueOf(r1), r17}));
    }

    @Override // j$.nio.file.FileSystem
    public final Iterable c() {
        return g.p(new Object[]{new o(this, "/", this.f159a, this.f160b)});
    }

    @Override // j$.nio.file.FileSystem, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.FileSystem
    public final y d() {
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.FileSystem
    public final boolean e() {
        return false;
    }

    @Override // j$.nio.file.FileSystem
    public final q0 f() {
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.FileSystem
    public final j$.nio.file.spi.d g() {
        return this.f161c;
    }

    @Override // j$.nio.file.FileSystem
    public final Path getPath(String r5, String[] r6) {
        if (r6.length == 0) goto L16;
        StringBuilder r0 = new StringBuilder();
        r0.append(r5);
        int r52 = r6.length;
        int r1 = 0;
    L6:
        if (r1 >= r52) goto L14;
        String r2 = r6[r1];
        if (r2.isEmpty() == true) goto L13;
        if (r0.length() <= 0) goto L12;
        r0.append('/');
    L12:
        r0.append(r2);
    L13:
        r1 = r1 + 1;
        goto L6
    L14:
        r5 = r0.toString();
    L16:
        return new o(this, r5, this.f159a, this.f160b);
    }

    @Override // j$.nio.file.FileSystem
    public final String getSeparator() {
        return "/";
    }

    @Override // j$.nio.file.FileSystem
    public final Set h() {
        return g.q(new Object[]{"basic"});
    }

    @Override // j$.nio.file.FileSystem
    public final boolean isOpen() {
        return true;
    }
}
