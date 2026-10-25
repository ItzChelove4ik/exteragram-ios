package org.chromium.net.impl;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import org.chromium.base.metrics.ScopedSysTraceEvent;
import org.chromium.net.impl.CronetLogger;

/* loaded from: classes5.dex */
public abstract class CronetManifest {

    @SuppressLint({"StaticFieldLeak"})
    private static Context sLastContext;
    private static final Object sLock = null;
    private static Bundle sMetaData;

    static {
        sLock = new Object();
    }

    private static Bundle getMetaData(Context r5) {
        Context r52 = r5.getApplicationContext();
        Object r0 = sLock;
        monitor-enter(r0);
    L21:
        th = move-exception;
        throw th;
    L5:
        if (r52 == sLastContext) goto L29;
        ScopedSysTraceEvent r1 = ScopedSysTraceEvent.scoped("CronetManifest#getMetaData fetching info");     // Catch: Throwable -> L21
        ServiceInfo r2 = r52.getPackageManager().getServiceInfo(new ComponentName(r52, "android.net.http.MetaDataHolder"), 787072);     // Catch: Throwable -> L9 Throwable -> L11
    L12:
        if (r2 == null) goto L16;
        Bundle r22 = r2.metaData;     // Catch: Throwable -> L9
        if (r22 == null) goto L16;
    L17:
        sMetaData = r22;     // Catch: Throwable -> L9
        sLastContext = r52;     // Catch: Throwable -> L9
        if (r1 == null) goto L29;
        r1.close();     // Catch: Throwable -> L21
        goto L29
    L16:
        r22 = new Bundle();     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        if (r1 != null) goto L36;
    L28:
        throw th;     // Catch: Throwable -> L21
    L36:
        r1.close();     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L21
    L11:
        r2 = null;
    L29:
        Bundle r53 = sMetaData;     // Catch: Throwable -> L21
        monitor-exit(r0);     // Catch: Throwable -> L21
        return r53;
    }

    public static boolean isAppOptedInForTelemetry(Context r1, CronetLogger.CronetSource r2) {
        if (r2 != CronetLogger.CronetSource.CRONET_SOURCE_PLATFORM) goto L5;
    L8:
        boolean r22 = true;
    L10:
        return getMetaData(r1).getBoolean("android.net.http.EnableTelemetry", r22);
    L5:
        if (r2 == CronetLogger.CronetSource.CRONET_SOURCE_PLAY_SERVICES) goto L8;
        r22 = false;
        goto L10
    }

    public static boolean shouldReadHttpFlags(Context r2) {
        return getMetaData(r2).getBoolean("android.net.http.ReadHttpFlags", true);
    }
}
