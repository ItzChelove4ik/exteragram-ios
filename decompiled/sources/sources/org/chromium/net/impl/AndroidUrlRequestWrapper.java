package org.chromium.net.impl;

import android.net.http.UrlRequest;
import java.nio.ByteBuffer;
import java.util.Collection;
import org.chromium.net.CronetException;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.RequestFinishedInfo;

/* loaded from: classes5.dex */
class AndroidUrlRequestWrapper extends ExperimentalUrlRequest {
    private final Collection<Object> mAnnotations;
    private final UrlRequest mBackend;
    private final AndroidHttpEngineWrapper mEngine;
    private final String mInitialUrl;
    private final VersionSafeCallbacks$RequestFinishedInfoListener mRequestFinishedInfoListener;

    public AndroidUrlRequestWrapper(UrlRequest r1, AndroidHttpEngineWrapper r2, String r3, Collection<Object> r4, RequestFinishedInfo.Listener r5) {
        this.mBackend = r1;
        this.mEngine = r2;
        this.mInitialUrl = r3;
        this.mAnnotations = r4;
        if (r5 != null) goto L5;
        VersionSafeCallbacks$RequestFinishedInfoListener r12 = null;
    L6:
        this.mRequestFinishedInfoListener = r12;
        return;
    L5:
        r12 = new VersionSafeCallbacks$RequestFinishedInfoListener(r5);
        goto L6
    }

    public static AndroidUrlRequestWrapper createAndAddToCallback(UrlRequest r6, AndroidUrlRequestCallbackWrapper r7, AndroidHttpEngineWrapper r8, String r9, Collection<Object> r10, RequestFinishedInfo.Listener r11) {
        AndroidUrlRequestWrapper r0 = new AndroidUrlRequestWrapper(r6, r8, r9, r10, r11);
        r7.setRequest(r0);
        return r0;
    }

    @Override // org.chromium.net.UrlRequest
    public void cancel() {
        this.mBackend.cancel();
    }

    @Override // org.chromium.net.UrlRequest
    public void followRedirect() {
        this.mBackend.followRedirect();
    }

    public void maybeReportMetrics(int r8, AndroidUrlResponseInfoWrapper r9, CronetException r10) {
        AndroidRequestFinishedInfoWrapper.reportFinished(this.mEngine, this.mInitialUrl, this.mAnnotations, this.mRequestFinishedInfoListener, r8, r9, r10);
    }

    @Override // org.chromium.net.UrlRequest
    public void read(ByteBuffer r1) {
        this.mBackend.read(r1);
    }

    @Override // org.chromium.net.UrlRequest
    public void start() {
        this.mBackend.start();
    }
}
