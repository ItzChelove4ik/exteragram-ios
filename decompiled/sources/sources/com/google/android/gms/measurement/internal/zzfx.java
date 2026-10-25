package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* loaded from: classes5.dex */
public final class zzfx {
    private static final Object zze = null;
    private final String zza;
    private final zzbo zzb;
    private final Object zzc;
    private final Object zzd;
    private volatile Object zzf;
    private volatile Object zzg;

    static {
        zze = new Object();
    }

    public /* synthetic */ zzfx(String r1, Object r2, Object r3, zzbo r4, byte[] r5) {
        this.zzd = new Object();
        this.zzf = null;
        this.zzg = null;
        this.zza = r1;
        this.zzc = r2;
        this.zzb = r4;
    }

    public final String zza() {
        return this.zza;
    }

    public final Object zzb(Object r4) {
        Object r0 = this.zzd;
        monitor-enter(r0);
        monitor-exit(r0);     // Catch: Throwable -> L50
        if (r4 == null) goto L8;
        return r4;
    L8:
        if (zzfr.zza == null) goto L10;
        Object r42 = zze;
        monitor-enter(r42);
    L18:
        th = move-exception;
        throw th;
    L14:
        if (zzae.zza() == true) goto L16;
        monitor-exit(r42);     // Catch: Throwable -> L18
        Iterator r43 = zzfy.zzb().iterator();     // Catch: SecurityException -> L54
    L26:
        if (r43.hasNext() == false) goto L43;
        zzfx r02 = (zzfx) r43.next();     // Catch: SecurityException -> L54
        if (zzae.zza() == true) goto L42;
        Object r1 = null;
        zzbo r2 = r02.zzb;     // Catch: IllegalStateException -> L53 SecurityException -> L54
        if (r2 == null) goto L33;
        r1 = r2.zza();     // Catch: IllegalStateException -> L53 SecurityException -> L54
    L33:
        Object r22 = zze;     // Catch: SecurityException -> L54
        monitor-enter(r22);     // Catch: SecurityException -> L54
        r02.zzg = r1;     // Catch: Throwable -> L38
        monitor-exit(r22);     // Catch: Throwable -> L38
    L38:
        th = move-exception;
        throw th;     // Catch: SecurityException -> L54
    L42:
        throw new IllegalStateException("Refreshing flag cache must be done on a worker thread.");     // Catch: SecurityException -> L54
    L43:
        zzbo r44 = this.zzb;
        if (r44 == null) goto L68;
        return r44.zza();
    L68:
        return this.zzc;
    L16:
        if (this.zzg != null) goto L20;
        Object r3 = this.zzc;     // Catch: Throwable -> L18
    L21:
        monitor-exit(r42);     // Catch: Throwable -> L18
        return r3;
    L20:
        r3 = this.zzg;     // Catch: Throwable -> L18
        goto L21
    L10:
        return this.zzc;
    L50:
        th = move-exception;
        throw th;
    }
}
