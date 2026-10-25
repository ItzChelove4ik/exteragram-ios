package com.google.android.gms.internal.fido;

import kotlin.UByte;

/* loaded from: classes4.dex */
public abstract class zzft {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final char[] zza = null;

    static {
        zza = "0123456789abcdef".toCharArray();
    }

    public zzft() {
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzft) == false) goto L10;
        zzft r42 = (zzft) r4;
        if (zzb() != r42.zzb()) goto L10;
        if (zzc(r42) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        if (zzb() >= 32) goto L5;
        byte[] r4 = zze();
        int r0 = r4[0] & UByte.MAX_VALUE;
        int r1 = 1;
    L8:
        if (r1 >= r4.length) goto L10;
        r0 = r0 | ((r4[r1] & UByte.MAX_VALUE) << (r1 * 8));
        r1 = r1 + 1;
        goto L8
    L10:
        return r0;
    L5:
        return zza();
    }

    public final String toString() {
        byte[] r6 = zze();
        int r0 = r6.length;
        StringBuilder r1 = new StringBuilder(r0 + r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L6;
        byte r3 = r6[r2];
        char[] r5 = zza;
        r1.append(r5[(r3 >> 4) & 15]);
        r1.append(r5[r3 & 15]);
        r2 = r2 + 1;
        goto L3
    L6:
        return r1.toString();
    }

    public abstract int zza();

    public abstract int zzb();

    public abstract boolean zzc(zzft r1);

    public abstract byte[] zzd();

    public abstract byte[] zze();
}
