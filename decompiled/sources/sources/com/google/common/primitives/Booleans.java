package com.google.common.primitives;

/* loaded from: classes5.dex */
public abstract class Booleans {
    public static boolean contains(boolean[] r4, boolean r5) {
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2] == r5) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }
}
