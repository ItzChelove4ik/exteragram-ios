package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Arrays;

/* loaded from: classes4.dex */
abstract class zzme extends zzmf {
    Object[] zza;
    int zzb;
    boolean zzc;

    public zzme(int r2) {
        zzlu.zzb(r2, "initialCapacity");
        this.zza = new Object[r2];
        this.zzb = 0;
    }

    private final void zzd(int r3) {
        int r0 = this.zza.length;
        int r32 = zzmf.zzc(r0, this.zzb + r3);
        if (r32 <= r0) goto L5;
    L8:
        this.zza = Arrays.copyOf(this.zza, r32);
        this.zzc = false;
        return;
    L5:
        if (this.zzc == true) goto L8;
    }

    public final zzme zza(Object r4) {
        r4.getClass();
        zzd(1);
        Object[] r0 = this.zza;
        int r1 = this.zzb;
        this.zzb = r1 + 1;
        r0[r1] = r4;
        return this;
    }

    public final void zzb(Object[] r4, int r5) {
        zzna.zza(r4, r5);
        zzd(r5);
        System.arraycopy(r4, 0, this.zza, this.zzb, r5);
        this.zzb += r5;
    }
}
