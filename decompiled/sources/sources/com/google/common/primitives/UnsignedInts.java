package com.google.common.primitives;

import com.google.common.base.Preconditions;

/* loaded from: classes5.dex */
public abstract class UnsignedInts {
    public static int checkedCast(long r4) {
        if ((r4 >> 32) != 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "out of range: %s", r4);
        return (int) r4;
    L5:
        r0 = false;
        goto L6
    }
}
