package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.common.base.Preconditions;
import com.google.common.base.Suppliers;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
public abstract class zzlw {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Object zzb = null;
    private static volatile zzlt zzc;
    private static final AtomicInteger zze = null;

    static {
        zzb = new Object();
        new AtomicReference();
        Preconditions.checkNotNull(zzlu.zza, "BuildInfo must be non-null");
        zze = new AtomicInteger();
    }

    public static void zza(Context r3) {
        if (zzc != null) goto L33;
        if (r3 == null) goto L37;
        Object r0 = zzb;
        monitor-enter(r0);
    L27:
        th = move-exception;
        throw th;
    L9:
        if (zzc != null) goto L29;
        monitor-enter(r0);     // Catch: Throwable -> L27
        zzlt r1 = zzc;     // Catch: Throwable -> L18
        Context r2 = r3.getApplicationContext();     // Catch: Throwable -> L18
        if (r2 == null) goto L14;
        r3 = r2;
    L14:
        if (r1 != null) goto L16;
    L20:
        if (r1 == null) goto L22;
        zzld.zza();     // Catch: Throwable -> L18
        zzma.zza();     // Catch: Throwable -> L18
    L22:
        zzc = new zzlc(r3, Suppliers.memoize(new zzlv(r3)));     // Catch: Throwable -> L18
        zze.incrementAndGet();     // Catch: Throwable -> L18
    L23:
        monitor-exit(r0);     // Catch: Throwable -> L18
    L16:
        if (r1.zza() == r3) goto L23;
    L18:
        th = move-exception;
        throw th;     // Catch: Throwable -> L27
    L29:
        monitor-exit(r0);     // Catch: Throwable -> L27
        return;
    L37:
        return;
    }
}
