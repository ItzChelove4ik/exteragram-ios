package org.chromium.net.impl;

import org.chromium.net.UrlResponseInfo;
import org.chromium.net.impl.JavaUrlRequest;
import org.chromium.net.impl.JavaUrlRequestUtils;

/* loaded from: classes5.dex */
public final /* synthetic */ class JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda8 implements JavaUrlRequestUtils.CheckedRunnable {
    public final /* synthetic */ JavaUrlRequest.AsyncUrlRequestCallback f$0;
    public final /* synthetic */ UrlResponseInfo f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda8(JavaUrlRequest.AsyncUrlRequestCallback r1, UrlResponseInfo r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.chromium.net.impl.JavaUrlRequestUtils.CheckedRunnable
    public final void run() {
        JavaUrlRequest.AsyncUrlRequestCallback.$r8$lambda$RXe1m0PdxXhwUrKBIben1xR2I_M(this.f$0, this.f$1, this.f$2);
    }
}
