package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zzas extends zzao {
    public zzas() {
        super(4);
    }

    public final zzas zza(Object r7) {
        r7.getClass();
        int r0 = this.zzb;
        int r1 = r0 + 1;
        Object[] r2 = this.zza;
        int r3 = r2.length;
        if (r3 >= r1) goto L11;
        int r32 = (r3 + (r3 >> 1)) + 1;
        if (r32 >= r1) goto L7;
        int r02 = Integer.highestOneBit(r0);
        r32 = r02 + r02;
    L7:
        if (r32 >= 0) goto L9;
        r32 = Integer.MAX_VALUE;
    L9:
        this.zza = Arrays.copyOf(r2, r32);
        this.zzc = false;
    L13:
        Object[] r03 = this.zza;
        int r12 = this.zzb;
        this.zzb = r12 + 1;
        r03[r12] = r7;
        return this;
    L11:
        if (this.zzc == false) goto L13;
        this.zza = (Object[]) r2.clone();
        this.zzc = false;
        goto L13
    }

    public final zzav zzb() {
        this.zzc = true;
        return zzav.zzg(this.zza, this.zzb);
    }
}
