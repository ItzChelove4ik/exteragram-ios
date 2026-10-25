package com.google.android.gms.internal.measurement;

import java.nio.charset.StandardCharsets;

/* loaded from: classes4.dex */
abstract class zzagi {
    public static final int zza(String r2, byte[] r3, int r4, int r5) {
        byte[] r22 = r2.getBytes(StandardCharsets.UTF_8);
        int r0 = r22.length;
        if ((r0 - r4) > r5) goto L7;
        System.arraycopy(r22, 0, r3, r4, r0);
        return r4 + r0;
    L7:
        throw new ArrayIndexOutOfBoundsException("Not enough space in output buffer to encode UTF-8 string");
    }
}
