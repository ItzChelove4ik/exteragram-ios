package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
final class zzne implements Runnable {
    final /* synthetic */ ConnectionResult zza;
    final /* synthetic */ zznf zzb;

    public zzne(zznf r1, ConnectionResult r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznl r0 = this.zzb.zza;
        r0.zzaa(null);
        if (this.zza.getErrorCode() == 7777) goto L5;
        r0.zzX();
        return;
    L5:
        if (r0.zzab() != null) goto L7;
        r0.zzac(Executors.newScheduledThreadPool(1));
    L7:
        r0.zzab().schedule(new zznc(this), ((Long) zzfy.zzZ.zzb(null)).longValue(), TimeUnit.MILLISECONDS);
    }
}
