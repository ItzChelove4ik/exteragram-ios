package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
abstract class zzaz {
    private static volatile Handler zzb;
    private final zzjg zza;
    private final Runnable zzc;
    private volatile long zzd;

    public zzaz(zzjg r2) {
        Preconditions.checkNotNull(r2);
        this.zza = r2;
        this.zzc = new zzay(this, r2);
    }

    private final Handler zzf() {
        if (zzb == null) goto L7;
        return zzb;
    L7:
        monitor-enter(zzaz.class);
    L11:
        th = move-exception;
        throw th;
    L9:
        if (zzb != null) goto L13;
        zzb = new com.google.android.gms.internal.measurement.zzcl(this.zza.zzaZ().getMainLooper());     // Catch: Throwable -> L11
    L13:
        Handler r2 = zzb;     // Catch: Throwable -> L11
        monitor-exit(zzaz.class);     // Catch: Throwable -> L11
        return r2;
    }

    public abstract void zza();

    public final void zzb(long r4) {
        zzd();
        if (r4 < 0) goto L8;
        zzjg r0 = this.zza;
        this.zzd = r0.zzba().currentTimeMillis();
        if (zzf().postDelayed(this.zzc, r4) == true) goto L9;
        r0.zzaW().zzb().zzb("Failed to schedule delayed post. time", Long.valueOf(r4));
        return;
    L9:
        return;
    }

    public final boolean zzc() {
        if (this.zzd == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzd() {
        this.zzd = 0;
        zzf().removeCallbacks(this.zzc);
    }

    public final /* synthetic */ void zze(long r1) {
        this.zzd = 0;
    }
}
