package com.google.android.gms.internal.play_billing;

import java.util.Arrays;

/* loaded from: classes5.dex */
public final class zzbv {
    Object[] zza;
    int zzb;
    zzbu zzc;

    public zzbv() {
        this.zza = new Object[8];
        this.zzb = 0;
    }

    public final zzbv zza(Object r5, Object r6) {
        int r0 = this.zzb + 1;
        Object[] r1 = this.zza;
        int r2 = r1.length;
        int r02 = r0 + r0;
        if (r02 <= r2) goto L11;
        if (r02 <= r2) goto L10;
        r2 = (r2 + (r2 >> 1)) + 1;
        if (r2 >= r02) goto L8;
        int r03 = Integer.highestOneBit(r02 - 1);
        r2 = r03 + r03;
    L8:
        if (r2 >= 0) goto L10;
        r2 = Integer.MAX_VALUE;
    L10:
        this.zza = Arrays.copyOf(r1, r2);
    L11:
        zzbo.zza(r5, r6);
        Object[] r04 = this.zza;
        int r12 = this.zzb;
        int r22 = r12 + r12;
        r04[r22] = r5;
        r04[r22 + 1] = r6;
        this.zzb = r12 + 1;
        return this;
    }

    public final zzbw zzb() {
        zzbu r0 = this.zzc;
        if (r0 != null) goto L10;
        zzcf r02 = zzcf.zzg(this.zzb, this.zza, this);
        zzbu r2 = this.zzc;
        if (r2 != null) goto L8;
        return r02;
    L8:
        throw r2.zza();
    L10:
        throw r0.zza();
    }
}
