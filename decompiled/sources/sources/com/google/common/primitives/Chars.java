package com.google.common.primitives;

import com.google.common.base.Preconditions;
import kotlin.UByte;

/* loaded from: classes5.dex */
public abstract class Chars {
    public static char checkedCast(long r3) {
        char r0 = (char) r3;
        if (r0 != r3) goto L5;
        boolean r1 = true;
    L6:
        Preconditions.checkArgument(r1, "Out of range: %s", r3);
        return r0;
    L5:
        r1 = false;
        goto L6
    }

    public static boolean contains(char[] r4, char r5) {
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

    public static char fromBytes(byte r0, byte r1) {
        return (char) ((r0 << 8) | (r1 & UByte.MAX_VALUE));
    }
}
