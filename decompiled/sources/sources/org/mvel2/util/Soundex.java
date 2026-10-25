package org.mvel2.util;

/* loaded from: classes5.dex */
public class Soundex {
    public static final char[] MAP = null;

    static {
        MAP = new char[]{'0', '1', '2', '3', '0', '1', '2', '0', '0', '2', '2', '4', '5', '5', '0', '1', '2', '6', '2', '3', '0', '1', '0', '2', '0', '2'};
    }

    public Soundex() {
    }

    public static String soundex(String r7) {
        char[] r72 = r7.toUpperCase().toCharArray();
        StringBuilder r0 = new StringBuilder();
        char r1 = '?';
        int r2 = 0;
    L4:
        if (r2 >= r72.length) goto L20;
        if (r0.length() >= 4) goto L20;
        char r3 = r72[r2];
        if (r3 == ',') goto L20;
        if (r3 < 'A') goto L18;
        if (r3 > 'Z') goto L18;
        if (r3 == r1) goto L18;
        char r12 = MAP[r3 - 'A'];
        if (r12 == '0') goto L17;
        r0.append(r12);
    L17:
        r1 = r3;
    L18:
        r2 = r2 + 1;
    L20:
        if (r0.length() != 0) goto L23;
        return null;
    L23:
        int r73 = r0.length();
    L24:
        if (r73 >= 4) goto L27;
        r0.append('0');
        r73 = r73 + 1;
        goto L24
    L27:
        return r0.toString();
    }
}
