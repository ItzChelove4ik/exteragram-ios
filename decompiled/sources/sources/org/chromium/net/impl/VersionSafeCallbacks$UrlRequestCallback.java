package org.chromium.net.impl;

import java.nio.ByteBuffer;
import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

/* loaded from: classes5.dex */
public final class VersionSafeCallbacks$UrlRequestCallback extends UrlRequest.Callback {
    private final UrlRequest.Callback mWrappedCallback;

    public VersionSafeCallbacks$UrlRequestCallback(UrlRequest.Callback r1) {
        this.mWrappedCallback = r1;
    }

    @Override // org.chromium.net.UrlRequest.Callback
    public void onCanceled(UrlRequest r1, UrlResponseInfo r2) {
        this.mWrappedCallback.onCanceled(r1, r2);
    }

    @Override // org.chromium.net.UrlRequest.Callback
    public void onFailed(UrlRequest r1, UrlResponseInfo r2, CronetException r3) {
        this.mWrappedCallback.onFailed(r1, r2, r3);
    }

    @Override // org.chromium.net.UrlRequest.Callback
    public void onReadCompleted(UrlRequest r1, UrlResponseInfo r2, ByteBuffer r3) {
        this.mWrappedCallback.onReadCompleted(r1, r2, r3);
    }

    @Override // org.chromium.net.UrlRequest.Callback
    public void onRedirectReceived(UrlRequest r1, UrlResponseInfo r2, String r3) {
        this.mWrappedCallback.onRedirectReceived(r1, r2, r3);
    }

    @Override // org.chromium.net.UrlRequest.Callback
    public void onResponseStarted(UrlRequest r1, UrlResponseInfo r2) {
        this.mWrappedCallback.onResponseStarted(r1, r2);
    }

    @Override // org.chromium.net.UrlRequest.Callback
    public void onSucceeded(UrlRequest r1, UrlResponseInfo r2) {
        this.mWrappedCallback.onSucceeded(r1, r2);
    }
}
