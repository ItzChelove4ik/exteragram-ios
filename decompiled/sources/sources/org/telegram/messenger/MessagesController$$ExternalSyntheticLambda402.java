package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda402 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.TL_contacts_getBlocked f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda402(MessagesController r1, TLObject r2, boolean r3, TLRPC.TL_contacts_getBlocked r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4520$r8$lambda$DIdt8ON8kx0OSBGE9fkIX9GkIM(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
