package org.chromium.net.impl;

import android.content.Context;
import android.net.http.HttpEngine;
import android.os.Build;
import android.os.ext.SdkExtensions;
import org.chromium.base.metrics.ScopedSysTraceEvent;
import org.chromium.net.CronetEngine;
import org.chromium.net.CronetProvider;
import org.chromium.net.ExperimentalCronetEngine;

/* loaded from: classes5.dex */
public class HttpEngineNativeProvider extends CronetProvider {
    public HttpEngineNativeProvider(Context r1) {
        super(r1);
    }

    public static boolean isHttpEngineAvailable() {
        if (Build.VERSION.SDK_INT >= 30) goto L5;
        return false;
    L5:
        if (SdkExtensions.getExtensionVersion(31) < 7) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.chromium.net.CronetProvider
    public CronetEngine.Builder createBuilder() {
        ScopedSysTraceEvent r0 = ScopedSysTraceEvent.scoped("HttpEngineNativeProvider#createBuilder");
        HttpEngineNativeProvider$$ExternalSyntheticApiModelOutline1.m();     // Catch: Throwable -> L7
        ExperimentalCronetEngine.Builder r1 = new ExperimentalCronetEngine.Builder(new AndroidHttpEngineBuilderWrapper(HttpEngineNativeProvider$$ExternalSyntheticApiModelOutline0.m(this.mContext)));     // Catch: Throwable -> L7
        if (r0 == null) goto L6;
        r0.close();
    L6:
        return r1;
    L7:
        th = move-exception;
        if (r0 != null) goto L14;
    L13:
        throw th;
    L14:
        r0.close();     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        th.addSuppressed(th);
        goto L13
    }

    @Override // org.chromium.net.CronetProvider
    public String getName() {
        return "HttpEngine-Native-Provider";
    }

    @Override // org.chromium.net.CronetProvider
    public String getVersion() {
        return HttpEngine.getVersionString();
    }

    @Override // org.chromium.net.CronetProvider
    public boolean isEnabled() {
        return isHttpEngineAvailable();
    }
}
