package org.telegram.utils.proxy;

import org.telegram.tgnet.RequestTimeDelegate;
import org.telegram.utils.proxy.WebProxyConnectionTester;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebProxyConnectionTester$$ExternalSyntheticLambda5 implements RequestTimeDelegate {
    public final /* synthetic */ WebProxyConnectionTester f$0;
    public final /* synthetic */ WebProxyConnectionTester.Request f$1;

    public /* synthetic */ WebProxyConnectionTester$$ExternalSyntheticLambda5(WebProxyConnectionTester r1, WebProxyConnectionTester.Request r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestTimeDelegate
    public final void run(long r2) {
        WebProxyConnectionTester.$r8$lambda$Btp45qf0GPwxEwFhLVJHl6nDqaQ(this.f$0, this.f$1, r2);
    }
}
