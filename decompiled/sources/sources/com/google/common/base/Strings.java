package com.google.common.base;

import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public abstract class Strings {
    public static String emptyToNull(String r0) {
        return Platform.emptyToNull(r0);
    }

    public static boolean isNullOrEmpty(String r0) {
        return Platform.stringIsNullOrEmpty(r0);
    }

    public static String lenientFormat(String r6, Object... r7) {
        String r62 = String.valueOf(r6);
        int r0 = 0;
        if (r7 != null) goto L5;
        r7 = new Object[]{"(Object[])null"};
    L5:
        StringBuilder r1 = new StringBuilder(r62.length() + (r7.length * 16));
        int r2 = 0;
    L7:
        if (r0 >= r7.length) goto L12;
        int r3 = r62.indexOf("%s", r2);
        if (r3 == (-1)) goto L12;
        r1.append(r62, r2, r3);
        r1.append(lenientToString(r7[r0]));
        r2 = r3 + 2;
        r0 = r0 + 1;
    L12:
        r1.append(r62, r2, r62.length());
        if (r0 >= r7.length) goto L20;
        String r63 = " [";
    L16:
        if (r0 >= r7.length) goto L18;
        r1.append(r63);
        r1.append(lenientToString(r7[r0]));
        r0 = r0 + 1;
        r63 = ", ";
        goto L16
    L18:
        r1.append(']');
    L20:
        return r1.toString();
    }

    private static String lenientToString(Object r4) {
        if (r4 != null) goto L10;
        return "null";
    L10:
        return r4.toString();
    L7:
        e = move-exception;
        String r42 = r4.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(r4));
        Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for ".concat(r42), e);
        return "<" + r42 + " threw " + e.getClass().getName() + ">";
    }

    public static String nullToEmpty(String r0) {
        return Platform.nullToEmpty(r0);
    }
}
