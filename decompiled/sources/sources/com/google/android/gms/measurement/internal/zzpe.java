package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
final class zzpe {
    private final zzpg zza;
    private int zzb;
    private long zzc;

    public zzpe(zzpg r3) {
        this.zza = r3;
        this.zzb = 1;
        this.zzc = zzd();
    }

    private final long zzd() {
        zzpg r0 = this.zza;
        Preconditions.checkNotNull(r0);
        long r3 = ((Long) zzfy.zzu.zzb(null)).longValue();
        long r1 = ((Long) zzfy.zzv.zzb(null)).longValue();
        int r5 = 1;
    L4:
        if (r5 >= this.zzb) goto L10;
        r3 = r3 + r3;
        if (r3 >= r1) goto L10;
        r5 = r5 + 1;
    L10:
        return r0.zzba().currentTimeMillis() + Math.min(r3, r1);
    }

    public final void zza() {
        this.zzb++;
        this.zzc = zzd();
    }

    public final boolean zzb() {
        if (this.zza.zzba().currentTimeMillis() < this.zzc) goto L6;
        return true;
    L6:
        return false;
    }

    public final /* synthetic */ long zzc() {
        return this.zzc;
    }
}
