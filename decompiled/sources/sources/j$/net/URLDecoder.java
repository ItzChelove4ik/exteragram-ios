package j$.net;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.BitSet;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public class URLDecoder {
    static {
        BitSet r0 = URLEncoder.f174a;
    }

    public static String a(String r12, Charset r13) {
        Objects.requireNonNull(r13, "Charset");
        int r0 = r12.length();
        if (r0 <= 500) goto L5;
        int r2 = r0 / 2;
    L6:
        StringBuilder r1 = new StringBuilder(r2);
        byte[] r3 = null;
        int r4 = 0;
        boolean r5 = false;
    L7:
        if (r4 >= r0) goto L46;
        char r6 = r12.charAt(r4);
        if (r6 == '%') goto L15;
        if (r6 != '+') goto L12;
        r1.append(' ');
        r4 = r4 + 1;
    L14:
        r5 = true;
        goto L7
    L12:
        r1.append(r6);
        r4 = r4 + 1;
        goto L7
    L15:
        if (r3 == null) goto L49;
    L18:
        int r52 = 0;
    L19:
        int r9 = r4 + 2;     // Catch: NumberFormatException -> L43
        if (r9 >= r0) goto L36;
        if (r6 != '%') goto L36;
        int r10 = r4 + 1;     // Catch: NumberFormatException -> L43
        if (b(r12.charAt(r10)) == false) goto L35;
        if (b(r12.charAt(r9)) == false) goto L35;
        int r92 = r4 + 3;     // Catch: NumberFormatException -> L43
        int r102 = Integer.parseInt(r12.subSequence(r10, r92).toString(), 16);     // Catch: NumberFormatException -> L43
        if (r102 < 0) goto L33;
        int r42 = r52 + 1;     // Catch: NumberFormatException -> L43
        r3[r52] = (byte) r102;     // Catch: NumberFormatException -> L43
        if (r92 >= r0) goto L31;
        r6 = r12.charAt(r92);     // Catch: NumberFormatException -> L43
    L31:
        r52 = r42;
        r4 = r92;
        goto L19
    L33:
        throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern - negative value : " + r12.substring(r4, r92));     // Catch: NumberFormatException -> L43
    L35:
        throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern : " + r12.substring(r4, r4 + 3));     // Catch: NumberFormatException -> L43
    L36:
        if (r4 >= r0) goto L41;
        if (r6 != '%') goto L41;
        throw new IllegalArgumentException("URLDecoder: Incomplete trailing escape (%) pattern");     // Catch: NumberFormatException -> L43
    L41:
        r1.append(new String(r3, 0, r52, r13));     // Catch: NumberFormatException -> L43
        goto L14
    L49:
        r3 = new byte[(r0 - r4) / 3];     // Catch: NumberFormatException -> L43
    L43:
        e = move-exception;
        throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern - " + e.getMessage());
    L46:
        if (r5 == true) goto L48;
        return r12;
    L48:
        return r1.toString();
    L5:
        r2 = r0;
        goto L6
    }

    public static boolean b(char r1) {
        if ('0' > r1) goto L7;
        if (r1 > '9') goto L7;
        return true;
    L7:
        if ('a' > r1) goto L11;
        if (r1 > 'f') goto L11;
        return true;
    L11:
        if ('A' <= r1) goto L13;
        return false;
    L13:
        if (r1 > 'F') goto L20;
        return true;
    L20:
        return false;
    }

    public static String decode(String r1, String r2) {
        if (r2.isEmpty() == true) goto L9;
        return a(r1, Charset.forName(r2));
    L7:
        throw new UnsupportedEncodingException(r2);
    L9:
        throw new UnsupportedEncodingException("URLDecoder: empty string enc parameter");
    }
}
