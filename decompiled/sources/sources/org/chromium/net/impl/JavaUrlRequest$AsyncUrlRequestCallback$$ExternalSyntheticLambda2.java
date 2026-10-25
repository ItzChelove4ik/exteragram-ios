package org.chromium.net.impl;

import org.chromium.net.CronetException;
import org.chromium.net.UrlResponseInfo;
import org.chromium.net.impl.JavaUrlRequest;

/* loaded from: classes5.dex */
public final /* synthetic */ class JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ JavaUrlRequest.AsyncUrlRequestCallback f$0;
    public final /* synthetic */ UrlResponseInfo f$1;
    public final /* synthetic */ CronetException f$2;

    public /* synthetic */ JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda2(JavaUrlRequest.AsyncUrlRequestCallback r1, UrlResponseInfo r2, CronetException r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        JavaUrlRequest.AsyncUrlRequestCallback.$r8$lambda$YvZtwCJrGUY8GOrtOFEOSZ9D6T8(this.f$0, this.f$1, this.f$2);
    }
}
