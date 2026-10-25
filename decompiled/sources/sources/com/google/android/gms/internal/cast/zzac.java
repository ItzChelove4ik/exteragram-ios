package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.DefaultClock;

/* loaded from: classes4.dex */
public final class zzac {
    final int zza;
    final long zzb;
    private long zzc;

    public zzac(zzab r3) {
        this.zza = r3.zza;
        this.zzb = DefaultClock.getInstance().currentTimeMillis();
    }

    public final void zza(long r1) {
        this.zzc = r1;
    }

    public final zzrd zzb() {
        zzrc r0 = zzrd.zza();
        r0.zza((int) (this.zzb - this.zzc));
        int r5 = this.zza;
        int r1 = 2;
        if (r5 != 1) goto L5;
    L10:
        r0.zzb(r1);
        return (zzrd) r0.zzu();
    L5:
        if (r5 == 2) goto L9;
        if (r5 == 3) goto L8;
        r1 = 1;
        goto L10
    L8:
        r1 = 4;
        goto L10
    L9:
        r1 = 3;
        goto L10
    }
}
