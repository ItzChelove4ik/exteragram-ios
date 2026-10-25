package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.DefaultClock;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public final class zzu {
    private static zzu zza;

    private zzu(zzj r1, String r2) {
        new ConcurrentHashMap();
        DefaultClock.getInstance();
    }

    public static synchronized void zza(zzj r2, String r3) {
        monitor-enter(zzu.class);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L11;
        zza = new zzu(r2, r3);     // Catch: Throwable -> L9
        monitor-exit(zzu.class);
        return;
    L11:
        monitor-exit(zzu.class);
    }
}
