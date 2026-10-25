package org.telegram.utils.proxy;

import org.telegram.utils.proxy.WebProxyConnectionTester;
import org.telegram.utils.proxy.WebProxyTransport;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebProxyConnectionTester$$ExternalSyntheticLambda2 implements WebProxyTransport.ReadyCallback {
    public final /* synthetic */ WebProxyConnectionTester f$0;
    public final /* synthetic */ WebProxyConnectionTester.Request f$1;

    public /* synthetic */ WebProxyConnectionTester$$ExternalSyntheticLambda2(WebProxyConnectionTester r1, WebProxyConnectionTester.Request r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.utils.proxy.WebProxyTransport.ReadyCallback
    public final void onReady() {
        WebProxyConnectionTester.m22242$r8$lambda$r3LDqvZeWschI7s1_9SRdtqs(this.f$0, this.f$1);
    }
}
