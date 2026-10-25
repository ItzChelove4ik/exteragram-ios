package org.chromium.net.impl;

import org.chromium.net.UrlResponseInfo;
import org.chromium.net.impl.JavaUrlRequest;

/* loaded from: classes5.dex */
public final /* synthetic */ class JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ JavaUrlRequest.AsyncUrlRequestCallback f$0;
    public final /* synthetic */ UrlResponseInfo f$1;

    public /* synthetic */ JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda0(JavaUrlRequest.AsyncUrlRequestCallback r1, UrlResponseInfo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        JavaUrlRequest.AsyncUrlRequestCallback.m3842$r8$lambda$UzbUASvl6MLjUvoY4iIFSdcqHE(this.f$0, this.f$1);
    }
}
