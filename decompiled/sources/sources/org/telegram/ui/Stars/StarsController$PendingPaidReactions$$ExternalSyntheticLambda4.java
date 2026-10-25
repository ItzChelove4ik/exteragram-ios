package org.telegram.ui.Stars;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$PendingPaidReactions$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ StarsController.PendingPaidReactions f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ MessagesController f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ StarsController$PendingPaidReactions$$ExternalSyntheticLambda4(StarsController.PendingPaidReactions r1, TLObject r2, MessagesController r3, TLRPC.TL_error r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.PendingPaidReactions.m19153$r8$lambda$keWct6GSWiIWiowXyY1ptwxujs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
