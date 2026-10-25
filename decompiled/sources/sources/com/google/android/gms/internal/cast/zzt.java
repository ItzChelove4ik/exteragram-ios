package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.DefaultClock;

/* loaded from: classes4.dex */
public final class zzt {
    private final int zza;
    private final long zzb;
    private long zzc;

    public zzt(zzs r3) {
        this.zza = r3.zza();
        this.zzb = DefaultClock.getInstance().currentTimeMillis();
    }

    public final boolean zza() {
        if (this.zza != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzb(long r1) {
        this.zzc = r1;
    }

    public final zzqv zzc() {
        int r0 = this.zza;
        zzqu r1 = zzqv.zza();
        int r2 = 2;
        if (r0 != 1) goto L5;
    L12:
        r1.zzb(r2);
        r1.zza((int) (this.zzb - this.zzc));
        return (zzqv) r1.zzu();
    L5:
        if (r0 == 2) goto L11;
        r2 = 4;
        if (r0 == 3) goto L12;
        if (r0 == 4) goto L10;
        r2 = 1;
        goto L12
    L10:
        r2 = 5;
        goto L12
    L11:
        r2 = 3;
        goto L12
    }
}
