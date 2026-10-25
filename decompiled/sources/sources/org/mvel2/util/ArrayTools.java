package org.mvel2.util;

/* loaded from: classes5.dex */
public class ArrayTools {
    public ArrayTools() {
    }

    public static int findFirst(char r1, int r2, int r3, char[] r4) {
        int r32 = r3 + r2;
    L3:
        if (r2 >= r32) goto L8;
        if (r4[r2] == r1) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r2;
    L8:
        return -1;
    }

    public static int findLast(char r0, int r1, int r2, char[] r3) {
        int r12 = (r1 + r2) - 1;
    L3:
        if (r12 < 0) goto L8;
        if (r3[r12] == r0) goto L6;
        r12 = r12 - 1;
        goto L3
    L6:
        return r12;
    L8:
        return -1;
    }
}
