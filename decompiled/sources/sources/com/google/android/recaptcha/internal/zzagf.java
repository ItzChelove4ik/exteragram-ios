package com.google.android.recaptcha.internal;

import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public abstract class zzagf {
    public static final byte[] zza = null;
    public static final ByteBuffer zzb = null;

    static {
        byte[] r1 = new byte[0];
        zza = r1;
        zzb = ByteBuffer.wrap(r1);
        zzaev.zzE(r1, 0, 0, false);
    }

    public static int zza() {
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static int zzb(boolean r0) {
        if (r0 == false) goto L5;
        return 1231;
    L5:
        return 1237;
    }

    public static int zzc(int r2, byte[] r3, int r4, int r5) {
        int r0 = r4;
    L4:
        if (r0 >= (r4 + r5)) goto L6;
        r2 = (r2 * 31) + r3[r0];
        r0 = r0 + 1;
        goto L4
    L6:
        return r2;
    }
}
