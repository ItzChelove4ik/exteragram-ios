package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
final class zzqf implements Runnable {
    final /* synthetic */ Runnable zza;
    final /* synthetic */ ListeningScheduledExecutorService zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ TimeUnit zzd;

    public zzqf(zzqi r1, Runnable r2, ListeningScheduledExecutorService r3, long r4, TimeUnit r6) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r6;
        Objects.requireNonNull(r1);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.run();
        long r0 = this.zzc;
        TimeUnit r2 = this.zzd;
        zzpx.zza(this.zzb.schedule(this, r0, r2));
    }
}
