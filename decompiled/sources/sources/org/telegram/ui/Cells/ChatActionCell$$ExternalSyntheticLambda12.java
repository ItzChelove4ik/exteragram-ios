package org.telegram.ui.Cells;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActionCell$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ ChatActionCell f$0;
    public final /* synthetic */ TLRPC.TL_messageActionNoForwardsRequest f$1;

    public /* synthetic */ ChatActionCell$$ExternalSyntheticLambda12(ChatActionCell r1, TLRPC.TL_messageActionNoForwardsRequest r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActionCell.$r8$lambda$pZe4_IGdoW_ShdrRMWwL33ZDjhY(this.f$0, this.f$1);
    }
}
