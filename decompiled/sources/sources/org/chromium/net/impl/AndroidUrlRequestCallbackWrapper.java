package org.chromium.net.impl;

import android.net.http.HttpException;
import android.net.http.UrlRequest$Callback;
import android.net.http.UrlResponseInfo;
import java.nio.ByteBuffer;
import java.util.Objects;
import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;

/* loaded from: classes5.dex */
class AndroidUrlRequestCallbackWrapper implements UrlRequest$Callback {
    private final UrlRequest.Callback mBackend;
    private AndroidUrlRequestWrapper mWrappedRequest;

    public static /* synthetic */ Object $r8$lambda$0YSUpRBhWItOzjE5XTPKTjltwwU(AndroidUrlRequestCallbackWrapper r0, UrlResponseInfo r1, ByteBuffer r2) {
        return r0.lambda$onReadCompleted$2(r1, r2);
    }

    public static /* synthetic */ Object $r8$lambda$g4UEVjfEGVPQs1LW7r7pKONVtkI(AndroidUrlRequestCallbackWrapper r0, UrlResponseInfo r1) {
        return r0.lambda$onResponseStarted$1(r1);
    }

    public static /* synthetic */ Object $r8$lambda$lfnCnZ7eGSbsnvDQaHpEwevkPZ0(AndroidUrlRequestCallbackWrapper r0, UrlResponseInfo r1, String r2) {
        return r0.lambda$onRedirectReceived$0(r1, r2);
    }

    public AndroidUrlRequestCallbackWrapper(UrlRequest.Callback r2) {
        Objects.requireNonNull(r2, "Callback is required.");
        this.mBackend = r2;
    }

    private /* synthetic */ Object lambda$onReadCompleted$2(UrlResponseInfo r2, ByteBuffer r3) {
        AndroidUrlResponseInfoWrapper r22 = AndroidUrlResponseInfoWrapper.createForUrlRequest(r2);
        this.mBackend.onReadCompleted(this.mWrappedRequest, r22, r3);
        return null;
    }

    private /* synthetic */ Object lambda$onRedirectReceived$0(UrlResponseInfo r2, String r3) {
        AndroidUrlResponseInfoWrapper r22 = AndroidUrlResponseInfoWrapper.createForUrlRequest(r2);
        this.mBackend.onRedirectReceived(this.mWrappedRequest, r22, r3);
        return null;
    }

    private /* synthetic */ Object lambda$onResponseStarted$1(UrlResponseInfo r2) {
        AndroidUrlResponseInfoWrapper r22 = AndroidUrlResponseInfoWrapper.createForUrlRequest(r2);
        this.mBackend.onResponseStarted(this.mWrappedRequest, r22);
        return null;
    }

    public void onCanceled(android.net.http.UrlRequest r4, UrlResponseInfo r5) {
        AndroidUrlResponseInfoWrapper r42 = AndroidUrlResponseInfoWrapper.createForUrlRequest(r5);
        this.mBackend.onCanceled(this.mWrappedRequest, r42);     // Catch: Throwable -> L6
        this.mWrappedRequest.maybeReportMetrics(2, r42, null);
        return;
    L6:
        th = move-exception;
        this.mWrappedRequest.maybeReportMetrics(2, r42, null);
        throw th;
    }

    public void onFailed(android.net.http.UrlRequest r3, UrlResponseInfo r4, HttpException r5) {
        AndroidUrlResponseInfoWrapper r32 = AndroidUrlResponseInfoWrapper.createForUrlRequest(r4);
        CronetException r42 = CronetExceptionTranslationUtils.translateCheckedAndroidCronetException(r5);
        this.mBackend.onFailed(this.mWrappedRequest, r32, r42);     // Catch: Throwable -> L6
        this.mWrappedRequest.maybeReportMetrics(1, r32, r42);
        return;
    L6:
        th = move-exception;
        this.mWrappedRequest.maybeReportMetrics(1, r32, r42);
        throw th;
    }

    public void onReadCompleted(android.net.http.UrlRequest r1, UrlResponseInfo r2, ByteBuffer r3) {
        CronetExceptionTranslationUtils.executeTranslatingCronetExceptions(new AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda1(this, r2, r3), Exception.class);
    }

    public void onRedirectReceived(android.net.http.UrlRequest r1, UrlResponseInfo r2, String r3) {
        CronetExceptionTranslationUtils.executeTranslatingCronetExceptions(new AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda2(this, r2, r3), Exception.class);
    }

    public void onResponseStarted(android.net.http.UrlRequest r1, UrlResponseInfo r2) {
        CronetExceptionTranslationUtils.executeTranslatingCronetExceptions(new AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda0(this, r2), Exception.class);
    }

    public void onSucceeded(android.net.http.UrlRequest r4, UrlResponseInfo r5) {
        AndroidUrlResponseInfoWrapper r42 = AndroidUrlResponseInfoWrapper.createForUrlRequest(r5);
        this.mBackend.onSucceeded(this.mWrappedRequest, r42);     // Catch: Throwable -> L6
        this.mWrappedRequest.maybeReportMetrics(0, r42, null);
        return;
    L6:
        th = move-exception;
        this.mWrappedRequest.maybeReportMetrics(0, r42, null);
        throw th;
    }

    public void setRequest(AndroidUrlRequestWrapper r1) {
        this.mWrappedRequest = r1;
    }
}
