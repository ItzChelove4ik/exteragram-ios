package org.chromium.net.impl;

import java.util.Collection;
import org.chromium.net.CronetException;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UrlResponseInfo;

/* loaded from: classes5.dex */
class AndroidRequestFinishedInfoWrapper extends RequestFinishedInfoImpl {
    private static final String TAG = null;

    static {
        TAG = RequestFinishedInfoImpl.class.getSimpleName();
    }

    private AndroidRequestFinishedInfoWrapper(String r1, Collection<Object> r2, RequestFinishedInfo.Metrics r3, int r4, UrlResponseInfo r5, CronetException r6) {
        super(r1, r2, r3, r4, r5, r6);
    }

    public static void reportFinished(AndroidHttpEngineWrapper r7, String r8, Collection<Object> r9, VersionSafeCallbacks$RequestFinishedInfoListener r10, int r11, UrlResponseInfo r12, CronetException r13) {
        r7.reportRequestFinished(new AndroidRequestFinishedInfoWrapper(r8, r9, CronetMetrics.empty(), r11, r12, r13), r10);
    }
}
