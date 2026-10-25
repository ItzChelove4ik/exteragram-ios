package com.google.common.primitives;

import androidx.media3.container.MdtaMetadataEntry$$ExternalSyntheticBackport0;
import com.google.common.base.Preconditions;

/* loaded from: classes5.dex */
public abstract class UnsignedBytes {
    public static byte checkedCast(long r4) {
        if ((r4 >> 8) != 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "out of range: %s", r4);
        return (byte) r4;
    L5:
        r0 = false;
        goto L6
    }

    public static int toInt(byte r0) {
        return MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r0);
    }
}
