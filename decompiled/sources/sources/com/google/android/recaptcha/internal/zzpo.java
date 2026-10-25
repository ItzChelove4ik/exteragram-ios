package com.google.android.recaptcha.internal;

import java.util.Arrays;

/* loaded from: classes5.dex */
abstract class zzpo extends zzpp {
    Object[] zza;
    int zzb;
    boolean zzc;

    public zzpo(int r2) {
        zzpa.zzb(r2, "initialCapacity");
        this.zza = new Object[r2];
        this.zzb = 0;
    }

    public final zzpo zza(Object r4) {
        int r0 = this.zza.length;
        int r1 = zzpp.zzc(r0, this.zzb + 1);
        if (r1 <= r0) goto L5;
    L6:
        this.zza = Arrays.copyOf(this.zza, r1);
        this.zzc = false;
    L7:
        Object[] r02 = this.zza;
        int r12 = this.zzb;
        this.zzb = r12 + 1;
        r02[r12] = r4;
        return this;
    L5:
        if (this.zzc == false) goto L7;
        goto L6
    }
}
