package org.telegram.utils.proxy;

import org.telegram.utils.proxy.WebProxyTransport;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebProxyTransport$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ WebProxyTransport.ReadyCallback f$0;

    public /* synthetic */ WebProxyTransport$$ExternalSyntheticLambda9(WebProxyTransport.ReadyCallback r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.onReady();
    }
}
