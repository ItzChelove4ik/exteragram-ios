package org.telegram.messenger;

import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.RequestTimeDelegate;

/* loaded from: classes5.dex */
public final /* synthetic */ class ProxyRotationController$$ExternalSyntheticLambda2 implements RequestTimeDelegate {
    public final /* synthetic */ SharedConfig.ProxyInfo f$0;

    public /* synthetic */ ProxyRotationController$$ExternalSyntheticLambda2(SharedConfig.ProxyInfo r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestTimeDelegate
    public final void run(long r1) {
        ProxyRotationController.m4814$r8$lambda$eWTdX8ujvsi5gV2KcZj1iGeQgI(this.f$0, r1);
    }
}
