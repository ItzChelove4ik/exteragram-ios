package com.google.android.play.integrity.internal;

import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public abstract class am {
    public static String a(String r10, Object... r11) {
        int r1 = 0;
        int r2 = 0;
    L3:
        int r0 = r11.length;
        if (r2 >= r0) goto L13;
        Object r3 = r11[r2];
        if (r3 != null) goto L29;
        String r02 = "null";
    L12:
        r11[r2] = r02;
        r2 = r2 + 1;
        goto L3
    L29:
        r02 = r3.toString();     // Catch: Exception -> L10
    L10:
        e = move-exception;
        String r03 = r3.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(r3));
        Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for ".concat(r03), e);
        r02 = "<" + r03 + " threw " + e.getClass().getName() + ">";
        goto L12
    L13:
        StringBuilder r32 = new StringBuilder(r10.length() + (r0 * 16));
        int r04 = 0;
    L14:
        int r22 = r11.length;
        if (r1 >= r22) goto L20;
        int r4 = r10.indexOf("%s", r04);
        if (r4 == (-1)) goto L20;
        r32.append(r10, r04, r4);
        r32.append(r11[r1]);
        r1 = r1 + 1;
        r04 = r4 + 2;
    L20:
        r32.append(r10, r04, r10.length());
        if (r1 >= r22) goto L28;
        r32.append(" [");
        int r102 = r1 + 1;
        r32.append(r11[r1]);
    L24:
        if (r102 >= r11.length) goto L26;
        r32.append(", ");
        r32.append(r11[r102]);
        r102 = r102 + 1;
        goto L24
    L26:
        r32.append(']');
    L28:
        return r32.toString();
    }
}
