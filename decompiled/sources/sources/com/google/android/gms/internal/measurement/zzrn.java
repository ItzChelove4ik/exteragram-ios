package com.google.android.gms.internal.measurement;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes4.dex */
public abstract class zzrn {
    private static final Object zza = null;
    private static Thread zzb;
    private static volatile Handler zzc;

    static {
        zza = new Object();
    }

    public static boolean zza(Thread r1) {
        if (zzb != null) goto L6;
        zzb = Looper.getMainLooper().getThread();
    L6:
        if (r1 != zzb) goto L9;
        return true;
    L9:
        return false;
    }

    public static Handler zzb() {
        if (zzc != null) goto L16;
        Object r0 = zza;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (zzc != null) goto L11;
        zzc = new Handler(Looper.getMainLooper());     // Catch: Throwable -> L9
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
    L16:
        return zzc;
    }
}
