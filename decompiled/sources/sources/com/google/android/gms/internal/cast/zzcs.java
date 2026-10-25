package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.DefaultClock;

/* loaded from: classes4.dex */
public final class zzcs {
    final long zza;
    private final Integer zzb;
    private final Boolean zzc;
    private long zzd;
    private final int zze;

    public zzcs(zzcr r3) {
        this.zze = r3.zze();
        this.zzb = r3.zzc();
        this.zzc = r3.zzd();
        this.zza = DefaultClock.getInstance().currentTimeMillis();
    }

    public final void zza(long r1) {
        this.zzd = r1;
    }

    public final zzqx zzb() {
        zzqw r0 = zzqx.zza();
        r0.zze(this.zze);
        int r1 = (int) (this.zza - this.zzd);
        r0.zzd(r1);
        r0.zza(r1);
        Integer r12 = this.zzb;
        if (r12 == null) goto L5;
        r0.zzb(r12.intValue());
    L5:
        Boolean r5 = this.zzc;
        if (r5 == null) goto L9;
        r0.zzc(r5.booleanValue());
    L9:
        return (zzqx) r0.zzu();
    }

    public final int zzc() {
        return this.zze;
    }
}
