package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzhw implements Thread.UncaughtExceptionHandler {
    final /* synthetic */ zzhz zza;
    private final String zzb;

    public zzhw(zzhz r1, String r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        Preconditions.checkNotNull(r2);
        this.zzb = r2;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final synchronized void uncaughtException(Thread r2, Throwable r3) {
        monitor-enter(this);
        this.zza.zzu.zzaW().zzb().zzb(this.zzb, r3);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
