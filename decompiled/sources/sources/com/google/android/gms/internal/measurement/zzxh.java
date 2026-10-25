package com.google.android.gms.internal.measurement;

import android.os.SystemClock;
import com.google.common.base.Ticker;

/* loaded from: classes4.dex */
public abstract class zzxh {
    private static final Ticker zza = null;

    static {
        SystemClock.elapsedRealtimeNanos();     // Catch: Throwable -> L4
        Ticker r0 = new zzxf();     // Catch: Throwable -> L4
    L5:
        zza = r0;
        return;
    L4:
        SystemClock.elapsedRealtime();
        r0 = new zzxg();
        goto L5
    }

    public static Ticker zza() {
        return zza;
    }
}
