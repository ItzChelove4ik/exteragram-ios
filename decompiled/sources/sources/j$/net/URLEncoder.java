package j$.net;

import j$.sun.security.action.a;
import java.io.CharArrayWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.util.BitSet;
import java.util.Objects;
import org.mvel2.asm.signature.SignatureVisitor;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public class URLEncoder {

    /* renamed from: a, reason: collision with root package name */
    public static final BitSet f174a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final String f175b = null;

    static {
        f174a = new BitSet(256);
        int r0 = 97;
    L4:
        if (r0 > 122) goto L6;
        f174a.set(r0);
        r0 = r0 + 1;
        goto L4
    L6:
        int r02 = 65;
    L8:
        if (r02 > 90) goto L10;
        f174a.set(r02);
        r02 = r02 + 1;
        goto L8
    L10:
        int r03 = 48;
    L12:
        if (r03 > 57) goto L14;
        f174a.set(r03);
        r03 = r03 + 1;
        goto L12
    L14:
        BitSet r04 = f174a;
        r04.set(32);
        r04.set(45);
        r04.set(95);
        r04.set(46);
        r04.set(42);
        if (System.getSecurityManager() != null) goto L17;
        String r05 = System.getProperty("file.encoding");
    L18:
        f175b = r05;
        return;
    L17:
        a r06 = new a();
        r06.f279b = "file.encoding";
        r05 = (String) AccessController.doPrivileged(r06);
        goto L18
    }

    public static String a(String r10, Charset r11) {
        Objects.requireNonNull(r11, "charset");
        StringBuilder r0 = new StringBuilder(r10.length());
        CharArrayWriter r1 = new CharArrayWriter();
        int r3 = 0;
        boolean r4 = false;
    L4:
        if (r3 >= r10.length()) goto L37;
        char r5 = r10.charAt(r3);
        if (f174a.get(r5) == true) goto L8;
    L11:
        r1.write(r5);
        if (r5 >= 55296) goto L14;
    L22:
        r3 = r3 + 1;
        if (r3 >= r10.length()) goto L26;
        BitSet r42 = f174a;
        r5 = r10.charAt(r3);
        if (r42.get(r5) == false) goto L11;
    L26:
        r1.flush();
        byte[] r43 = new String(r1.toCharArray()).getBytes(r11);
        int r52 = 0;
    L28:
        if (r52 >= r43.length) goto L36;
        r0.append('%');
        char r6 = Character.forDigit((r43[r52] >> 4) & 15, 16);
        if (Character.isLetter(r6) == false) goto L32;
        r6 = (char) (r6 - ' ');
    L32:
        r0.append(r6);
        char r62 = Character.forDigit(r43[r52] & 15, 16);
        if (Character.isLetter(r62) == false) goto L35;
        r62 = (char) (r62 - ' ');
    L35:
        r0.append(r62);
        r52 = r52 + 1;
        goto L28
    L36:
        r1.reset();
        r4 = true;
        goto L4
    L14:
        if (r5 > 56319) goto L22;
        int r44 = r3 + 1;
        if (r44 >= r10.length()) goto L22;
        char r53 = r10.charAt(r44);
        if (r53 < 56320) goto L22;
        if (r53 > 57343) goto L22;
        r1.write(r53);
        r3 = r44;
        goto L22
    L8:
        if (r5 != ' ') goto L10;
        r5 = SignatureVisitor.EXTENDS;
        r4 = true;
    L10:
        r0.append(r5);
        r3 = r3 + 1;
        goto L4
    L37:
        if (r4 == true) goto L39;
        return r10;
    L39:
        return r0.toString();
    }

    public static String encode(String r1) {
        return encode(r1, f175b);
    L4:
        return null;
    }

    public static String encode(String r1, String r2) {
        if (r2 == null) goto L8;
        return a(r1, Charset.forName(r2));
    L6:
        throw new UnsupportedEncodingException(r2);
    L8:
        throw new NullPointerException("charsetName");
    }
}
