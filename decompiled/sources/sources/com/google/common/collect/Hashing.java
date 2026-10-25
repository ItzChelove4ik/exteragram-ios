package com.google.common.collect;

import org.telegram.tgnet.TLObject;

/* loaded from: classes.dex */
abstract class Hashing {
    public static int closedTableSize(int r3, double r4) {
        int r32 = Math.max(r3, 2);
        int r0 = Integer.highestOneBit(r32);
        if (r32 <= ((int) (r4 * r0))) goto L9;
        int r33 = r0 << 1;
        if (r33 <= 0) goto L7;
        return r33;
    L7:
        return TLObject.FLAG_30;
    L9:
        return r0;
    }

    public static int smear(int r4) {
        return (int) (Integer.rotateLeft((int) (r4 * (-862048943)), 15) * 461845907);
    }

    public static int smearedHash(Object r0) {
        if (r0 != null) goto L4;
        int r02 = 0;
    L6:
        return smear(r02);
    L4:
        r02 = r0.hashCode();
        goto L6
    }
}
