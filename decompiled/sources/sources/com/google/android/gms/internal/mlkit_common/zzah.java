package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zzah {
    Object[] zza;
    int zzb;
    zzag zzc;

    public zzah() {
        this.zza = new Object[8];
        this.zzb = 0;
    }

    public final zzah zza(Object r4, Object r5) {
        int r0 = this.zzb + 1;
        Object[] r1 = this.zza;
        int r2 = r1.length;
        int r02 = r0 + r0;
        if (r02 <= r2) goto L5;
        this.zza = Arrays.copyOf(r1, zzaa.zza(r2, r02));
    L5:
        zzw.zza(r4, r5);
        Object[] r03 = this.zza;
        int r12 = this.zzb;
        int r22 = r12 + r12;
        r03[r22] = r4;
        r03[r22 + 1] = r5;
        this.zzb = r12 + 1;
        return this;
    }

    public final zzai zzb() {
        zzag r0 = this.zzc;
        if (r0 != null) goto L10;
        zzaq r02 = zzaq.zzg(this.zzb, this.zza, this);
        zzag r2 = this.zzc;
        if (r2 != null) goto L8;
        return r02;
    L8:
        throw r2.zza();
    L10:
        throw r0.zza();
    }
}
