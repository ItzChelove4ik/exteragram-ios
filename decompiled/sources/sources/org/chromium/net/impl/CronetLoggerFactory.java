package org.chromium.net.impl;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import org.chromium.net.impl.CronetLogger;
import org.chromium.net.telemetry.CronetLoggerImpl;

/* loaded from: classes5.dex */
public abstract class CronetLoggerFactory {
    private static final String TAG = "CronetLoggerFactory";
    private static CronetLogger sLogger;

    static {
    }

    public static CronetLogger createLogger(Context r3, CronetLogger.CronetSource r4) {
        monitor-enter(CronetLoggerFactory.class);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (sLogger != null) goto L17;
        if (Build.VERSION.SDK_INT < 30) goto L17;
        if (CronetManifest.isAppOptedInForTelemetry(r3, r4) == false) goto L17;
        sLogger = new CronetLoggerImpl(1);     // Catch: Throwable -> L12 Exception -> L14
    L14:
        e = move-exception;
        Log.e(TAG, "Exception creating an instance of CronetLoggerImpl", e);     // Catch: Throwable -> L12
    L17:
        if (sLogger != null) goto L19;
        sLogger = new NoOpLogger();     // Catch: Throwable -> L12
    L19:
        CronetLogger r32 = sLogger;     // Catch: Throwable -> L12
        monitor-exit(CronetLoggerFactory.class);     // Catch: Throwable -> L12
        return r32;
    }
}
