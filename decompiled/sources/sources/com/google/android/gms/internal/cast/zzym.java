package com.google.android.gms.internal.cast;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes4.dex */
public abstract class zzym {
    static final Charset zza = null;
    public static final byte[] zzb = null;

    static {
        zza = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        byte[] r2 = new byte[0];
        zzb = r2;
        ByteBuffer.wrap(r2);
        int r1 = zzxm.$r8$clinit;
        new zzxl(r2, 0, 0, false, null).zza(0);
    }

    public static int zza(boolean r0) {
        if (r0 == false) goto L5;
        return 1231;
    L5:
        return 1237;
    }

    public static int zzb(int r2, byte[] r3, int r4, int r5) {
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
