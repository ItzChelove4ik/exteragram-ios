package org.telegram.tgnet;

import org.telegram.utils.proxy.ProxySettings;
import org.telegram.utils.proxy.WebProxyConnectionTester;

/* loaded from: classes6.dex */
public final /* synthetic */ class ConnectionsManager$$ExternalSyntheticLambda26 implements WebProxyConnectionTester.TestImplementation {
    public final /* synthetic */ ConnectionsManager f$0;

    public /* synthetic */ ConnectionsManager$$ExternalSyntheticLambda26(ConnectionsManager r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.utils.proxy.WebProxyConnectionTester.TestImplementation
    public final void doCheck(ProxySettings r1, int r2, RequestTimeDelegate r3) {
        ConnectionsManager.m5380$r8$lambda$5CxBz6DPyQrpMKFxOOS5plyRSI(this.f$0, r1, r2, r3);
    }
}
