package com.google.android.recaptcha.internal;

import kotlin.UByte;

/* loaded from: classes5.dex */
public abstract class zzre {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final char[] zza = null;

    static {
        zza = "0123456789abcdef".toCharArray();
    }

    public zzre() {
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzre) == false) goto L10;
        zzre r42 = (zzre) r4;
        if (zza() != r42.zza()) goto L10;
        if (zzf(r42) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        if (zza() >= 32) goto L5;
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
        return zzc();
    }

    public final String toString() {
        byte[] r6 = zze();
        int r1 = r6.length;
        StringBuilder r0 = new StringBuilder(r1 + r1);
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        byte r3 = r6[r2];
        char[] r5 = zza;
        r0.append(r5[(r3 >> 4) & 15]);
        r0.append(r5[r3 & 15]);
        r2 = r2 + 1;
        goto L3
    L6:
        return r0.toString();
    }

    public abstract int zza();

    public abstract byte[] zzb();

    public abstract int zzc();

    public abstract long zzd();

    public byte[] zze() {
        return zzb();
    }

    public abstract boolean zzf(zzre r1);
}
