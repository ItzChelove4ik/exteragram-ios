package org.chromium.net.impl;

import android.content.Context;
import org.chromium.net.httpflags.HttpFlagsLoader;
import org.chromium.net.httpflags.ResolvedFlags;
import org.chromium.net.impl.CronetLogger;

/* loaded from: classes5.dex */
public abstract class HttpFlagsForImpl {
    public static ResolvedFlags getHttpFlags(Context r2, CronetLogger.CronetSource r3) {
        return HttpFlagsLoader.getHttpFlags(r2, ImplVersion.getCronetVersion(), false, CronetManifest.isAppOptedInForTelemetry(r2, r3));
    }
}
