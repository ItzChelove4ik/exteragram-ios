package com.google.android.gms.internal.flags;

import android.os.StrictMode;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public abstract class zzd {
    public static <T> T zza(Callable<T> r2) {
        StrictMode.ThreadPolicy r0 = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);     // Catch: Throwable -> L6
        T r22 = r2.call();     // Catch: Throwable -> L6
        StrictMode.setThreadPolicy(r0);
        return r22;
    L6:
        th = move-exception;
        StrictMode.setThreadPolicy(r0);
        throw th;
    }
}
