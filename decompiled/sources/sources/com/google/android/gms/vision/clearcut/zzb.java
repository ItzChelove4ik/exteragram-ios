package com.google.android.gms.vision.clearcut;

/* loaded from: classes5.dex */
public final class zzb {
    private final long zza;
    private final Object zzb;
    private long zzc;

    public zzb(double r1) {
        this.zzb = new Object();
        this.zzc = Long.MIN_VALUE;
        this.zza = Math.round(30000.0d);
    }

    public final boolean zza() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        long r1 = System.currentTimeMillis();     // Catch: Throwable -> L9
        if ((this.zzc + this.zza) > r1) goto L7;
        this.zzc = r1;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return true;
    L7:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return false;
    L9:
        th = move-exception;
        throw th;
    }
}
