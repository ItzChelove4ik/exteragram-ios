package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Arrays;

/* loaded from: classes4.dex */
abstract class zzj extends zzk {
    Object[] zza;
    int zzb;
    boolean zzc;

    public zzj(int r1) {
        this.zza = new Object[4];
        this.zzb = 0;
    }

    private final void zzb(int r5) {
        Object[] r0 = this.zza;
        int r1 = r0.length;
        if (r1 >= r5) goto L12;
        int r12 = (r1 + (r1 >> 1)) + 1;
        if (r12 >= r5) goto L7;
        int r52 = Integer.highestOneBit(r5 - 1);
        r12 = r52 + r52;
    L7:
        if (r12 >= 0) goto L9;
        r12 = Integer.MAX_VALUE;
    L9:
        this.zza = Arrays.copyOf(r0, r12);
        this.zzc = false;
        return;
    L12:
        if (this.zzc == false) goto L15;
        this.zza = (Object[]) r0.clone();
        this.zzc = false;
        return;
    }

    public final zzj zza(Object r4) {
        r4.getClass();
        zzb(this.zzb + 1);
        Object[] r0 = this.zza;
        int r1 = this.zzb;
        this.zzb = r1 + 1;
        r0[r1] = r4;
        return this;
    }
}
