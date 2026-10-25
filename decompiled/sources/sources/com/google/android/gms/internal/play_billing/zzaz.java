package com.google.android.gms.internal.play_billing;

import android.os.SystemClock;

/* loaded from: classes5.dex */
public abstract class zzaz {
    private static final zzbl zza = null;

    static {
        SystemClock.elapsedRealtimeNanos();     // Catch: Throwable -> L4
        zzbl r0 = new zzax();     // Catch: Throwable -> L4
    L5:
        zza = r0;
        return;
    L4:
        SystemClock.elapsedRealtime();
        r0 = new zzay();
        goto L5
    }

    public static zzbl zza() {
        return zza;
    }
}
