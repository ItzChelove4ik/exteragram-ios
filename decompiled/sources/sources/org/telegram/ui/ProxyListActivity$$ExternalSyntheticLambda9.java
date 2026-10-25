package org.telegram.ui;

import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.RequestTimeDelegate;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProxyListActivity$$ExternalSyntheticLambda9 implements RequestTimeDelegate {
    public final /* synthetic */ SharedConfig.ProxyInfo f$0;

    public /* synthetic */ ProxyListActivity$$ExternalSyntheticLambda9(SharedConfig.ProxyInfo r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestTimeDelegate
    public final void run(long r1) {
        ProxyListActivity.$r8$lambda$Q4MvhcXUL51Lzm8dGd4P8OBtNYA(this.f$0, r1);
    }
}
