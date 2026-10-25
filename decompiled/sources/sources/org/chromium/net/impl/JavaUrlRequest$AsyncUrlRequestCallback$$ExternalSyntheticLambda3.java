package org.chromium.net.impl;

import java.nio.ByteBuffer;
import org.chromium.net.UrlResponseInfo;
import org.chromium.net.impl.JavaUrlRequest;
import org.chromium.net.impl.JavaUrlRequestUtils;

/* loaded from: classes5.dex */
public final /* synthetic */ class JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3 implements JavaUrlRequestUtils.CheckedRunnable {
    public final /* synthetic */ JavaUrlRequest.AsyncUrlRequestCallback f$0;
    public final /* synthetic */ UrlResponseInfo f$1;
    public final /* synthetic */ ByteBuffer f$2;

    public /* synthetic */ JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3(JavaUrlRequest.AsyncUrlRequestCallback r1, UrlResponseInfo r2, ByteBuffer r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.chromium.net.impl.JavaUrlRequestUtils.CheckedRunnable
    public final void run() {
        JavaUrlRequest.AsyncUrlRequestCallback.m3841$r8$lambda$L2MmDQF1IP9TU3XYnKRRuWu5Xs(this.f$0, this.f$1, this.f$2);
    }
}
