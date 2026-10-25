package com.google.android.recaptcha.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public final class zzvb {
    private static final zzvb zza = null;
    private final AtomicReference zzb;

    static {
        zza = (zzvb) zzwb.zza(zzva.zza);
    }

    public zzvb() {
        this.zzb = new AtomicReference(new zzvy(new zzvx(), null));
    }

    public static zzvb zza() {
        return zza;
    }

    public final synchronized void zzb(zzue r4) {
        monitor-enter(this);
        AtomicReference r1 = this.zzb;     // Catch: Throwable -> L6
        zzvx r0 = new zzvx((zzvy) r1.get());     // Catch: Throwable -> L6
        r0.zza(r4);     // Catch: Throwable -> L6
        r1.set(new zzvy(r0, null));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized void zzc(zzub r4) {
        monitor-enter(this);
        AtomicReference r1 = this.zzb;     // Catch: Throwable -> L6
        zzvx r0 = new zzvx((zzvy) r1.get());     // Catch: Throwable -> L6
        r0.zzb(r4);     // Catch: Throwable -> L6
        r1.set(new zzvy(r0, null));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized void zzd(zzvi r4) {
        monitor-enter(this);
        AtomicReference r1 = this.zzb;     // Catch: Throwable -> L6
        zzvx r0 = new zzvx((zzvy) r1.get());     // Catch: Throwable -> L6
        r0.zzc(r4);     // Catch: Throwable -> L6
        r1.set(new zzvy(r0, null));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized void zze(zzvf r4) {
        monitor-enter(this);
        AtomicReference r1 = this.zzb;     // Catch: Throwable -> L6
        zzvx r0 = new zzvx((zzvy) r1.get());     // Catch: Throwable -> L6
        r0.zzd(r4);     // Catch: Throwable -> L6
        r1.set(new zzvy(r0, null));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final boolean zzf(zzsp r1) {
        return ((zzvy) this.zzb.get()).zzc(r1);
    }

    public final zzse zzg(zzsp r1, zzsy r2) {
        return ((zzvy) this.zzb.get()).zza(r1, r2);
    }

    public final zzsp zzh(zzse r1, zzsy r2) {
        return ((zzvy) this.zzb.get()).zzb(r1, r2);
    }
}
