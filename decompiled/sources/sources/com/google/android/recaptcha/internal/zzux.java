package com.google.android.recaptcha.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public final class zzux {
    private static final zzux zza = null;
    private final AtomicReference zzb;

    static {
        zza = new zzux();
    }

    public zzux() {
        this.zzb = new AtomicReference(new zzvs(new zzvq(null), null));
    }

    public static zzux zza() {
        return zza;
    }

    public final synchronized void zzb(zzvp r5) {
        monitor-enter(this);
        AtomicReference r0 = this.zzb;     // Catch: Throwable -> L6
        zzvq r2 = new zzvq((zzvs) r0.get(), null);     // Catch: Throwable -> L6
        r2.zza(r5);     // Catch: Throwable -> L6
        r0.set(new zzvs(r2, null));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized void zzc(zzvu r5) {
        monitor-enter(this);
        AtomicReference r0 = this.zzb;     // Catch: Throwable -> L6
        zzvq r2 = new zzvq((zzvs) r0.get(), null);     // Catch: Throwable -> L6
        r2.zzb(r5);     // Catch: Throwable -> L6
        r0.set(new zzvs(r2, null));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
