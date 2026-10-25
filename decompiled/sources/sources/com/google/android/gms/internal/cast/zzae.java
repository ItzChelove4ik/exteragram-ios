package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.DefaultClock;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public final class zzae {
    final long zza;
    long zzb;
    private long zzc;
    private final AtomicInteger zzd;
    private final int zze;

    public zzae(zzad r3) {
        this.zze = r3.zza();
        long r0 = DefaultClock.getInstance().currentTimeMillis();
        this.zza = r0;
        this.zzb = r0;
        this.zzd = new AtomicInteger(1);
    }

    public final zzrb zza() {
        zzra r0 = zzrb.zza();
        r0.zzd(this.zze);
        r0.zza(this.zzd.get());
        r0.zzb((int) (this.zza - this.zzc));
        r0.zzc((int) (this.zzb - this.zzc));
        return (zzrb) r0.zzu();
    }

    public final void zzb(long r1) {
        this.zzc = r1;
    }

    public final void zzc() {
        this.zzd.incrementAndGet();
        this.zzb = DefaultClock.getInstance().currentTimeMillis();
    }
}
