package com.google.android.recaptcha.internal;

import java.util.Arrays;

/* loaded from: classes5.dex */
public final class zzpv {
    Object[] zza;
    int zzb;
    zzpu zzc;

    public zzpv() {
        this(4);
    }

    public zzpv(int r1) {
        this.zza = new Object[r1 + r1];
        this.zzb = 0;
    }

    public final zzpv zza(Object r4, Object r5) {
        int r0 = this.zzb + 1;
        Object[] r1 = this.zza;
        int r2 = r1.length;
        int r02 = r0 + r0;
        if (r02 <= r2) goto L5;
        this.zza = Arrays.copyOf(r1, zzpp.zzc(r2, r02));
    L5:
        zzpa.zza(r4, r5);
        Object[] r03 = this.zza;
        int r12 = this.zzb;
        int r22 = r12 + r12;
        r03[r22] = r4;
        r03[r22 + 1] = r5;
        this.zzb = r12 + 1;
        return this;
    }

    public final zzpw zzb() {
        zzpu r0 = this.zzc;
        if (r0 != null) goto L10;
        zzqq r02 = zzqq.zzf(this.zzb, this.zza, this);
        zzpu r2 = this.zzc;
        if (r2 != null) goto L8;
        return r02;
    L8:
        throw r2.zza();
    L10:
        throw r0.zza();
    }
}
