package org.telegram.utils.proxy;

import org.telegram.tgnet.RequestTimeDelegate;
import org.telegram.utils.proxy.WebProxyConnectionTester;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebProxyConnectionTester$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ WebProxyConnectionTester f$0;
    public final /* synthetic */ WebProxyConnectionTester.TestImplementation f$1;
    public final /* synthetic */ ProxySettings f$2;
    public final /* synthetic */ RequestTimeDelegate f$3;

    public /* synthetic */ WebProxyConnectionTester$$ExternalSyntheticLambda0(WebProxyConnectionTester r1, WebProxyConnectionTester.TestImplementation r2, ProxySettings r3, RequestTimeDelegate r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WebProxyConnectionTester.$r8$lambda$ufe7vls3QFXLZj8v4zidu7dGV6M(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
