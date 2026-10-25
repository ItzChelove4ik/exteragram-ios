package org.mvel2.sh.text;

/* loaded from: classes5.dex */
public class TextUtil {
    public TextUtil() {
    }

    public static String pad(int r1, int r2) {
        StringBuilder r0 = new StringBuilder();
        int r22 = r2 - r1;
    L4:
        if (r22 == (-1)) goto L7;
        r0.append(' ');
        r22 = r22 - 1;
        goto L4
    L7:
        return r0.toString();
    }

    public static String padTwo(Object r2, Object r3, int r4) {
        return String.valueOf(r2) + pad(String.valueOf(r2).length(), r4) + r3;
    }

    public static String paint(char r2, int r3) {
        StringBuilder r0 = new StringBuilder();
    L4:
        if (r3 == (-1)) goto L7;
        r0.append(r2);
        r3 = r3 - 1;
        goto L4
    L7:
        return r0.toString();
    }
}
