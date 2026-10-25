package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPGroupNotification$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ int f$0;

    public /* synthetic */ VoIPGroupNotification$$ExternalSyntheticLambda2(int r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        VoIPGroupNotification.$r8$lambda$6xjOsDUB65Zk6_tWg6lepZcnZis(this.f$0, r1, r2);
    }
}
