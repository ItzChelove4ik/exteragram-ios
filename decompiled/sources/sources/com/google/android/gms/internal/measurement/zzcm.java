package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.util.Log;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
public final class zzcm extends zzcr {
    private final AtomicReference zza;
    private boolean zzb;

    public zzcm() {
        this.zza = new AtomicReference();
    }

    public static final Object zzf(Bundle r2, Class r3) {
        if (r2 == null) goto L11;
        Object r22 = r2.get("r");
        if (r22 == null) goto L11;
        return r3.cast(r22);
    L8:
        e = move-exception;
        Log.w("AM", String.format("%s: %s, %s", new Object[]{"Unexpected object type. Expected, Received", r3.getCanonicalName(), r22.getClass().getCanonicalName()}), e);
        throw e;
    L11:
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzcs
    public final void zzb(Bundle r2) {
        AtomicReference r0 = this.zza;
        monitor-enter(r0);
        r0.set(r2);     // Catch: Throwable -> L10
        this.zzb = true;     // Catch: Throwable -> L10
        this.zza.notify();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    L10:
        th = move-exception;
        this.zza.notify();     // Catch: Throwable -> L8
        throw th;     // Catch: Throwable -> L8
    }

    public final String zzc(long r1) {
        return (String) zzf(zze(r1), String.class);
    }

    public final Long zzd(long r1) {
        return (Long) zzf(zze(r1), Long.class);
    }

    public final Bundle zze(long r3) {
        AtomicReference r0 = this.zza;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.zzb == false) goto L18;
    L13:
        Bundle r2 = (Bundle) this.zza.get();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r2;
    L18:
        r0.wait(r3);     // Catch: Throwable -> L8 InterruptedException -> L10
    L11:
        return null;
    }
}
