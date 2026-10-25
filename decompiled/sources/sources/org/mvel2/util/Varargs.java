package org.mvel2.util;

import java.lang.reflect.Array;

/* loaded from: classes5.dex */
public class Varargs {
    public Varargs() {
    }

    public static Object[] normalizeArgsForVarArgs(Class<?>[] r4, Object[] r5, boolean r6) {
        if (r6 == true) goto L4;
        return r5;
    L4:
        int r0 = 0;
        if (r5.length <= 0) goto L7;
        Object r62 = r5[r5.length - 1];
    L9:
        if (r4.length != r5.length) goto L14;
        if (r62 != null) goto L12;
    L13:
        return r5;
    L12:
        if (r62.getClass().isArray() == true) goto L13;
    L14:
        int r63 = (r5.length - r4.length) + 1;
        Object r1 = Array.newInstance(r4[r4.length - 1].getComponentType(), r63);
        int r2 = 0;
    L15:
        if (r2 >= r63) goto L17;
        Array.set(r1, r2, r5[(r4.length - 1) + r2]);
        r2 = r2 + 1;
        goto L15
    L17:
        Object[] r64 = new Object[r4.length];
    L19:
        if (r0 >= (r4.length - 1)) goto L21;
        r64[r0] = r5[r0];
        r0 = r0 + 1;
        goto L19
    L21:
        r64[r4.length - 1] = r1;
        return r64;
    L7:
        r62 = Array.newInstance(r4[r4.length - 1].getComponentType(), 0);
        goto L9
    }

    public static Class<?> paramTypeVarArgsSafe(Class<?>[] r0, int r1, boolean r2) {
        if (r2 == true) goto L6;
        return r0[r1];
    L6:
        if (r1 >= (r0.length - 1)) goto L10;
        return r0[r1];
    L10:
        return r0[r0.length - 1].getComponentType();
    }
}
