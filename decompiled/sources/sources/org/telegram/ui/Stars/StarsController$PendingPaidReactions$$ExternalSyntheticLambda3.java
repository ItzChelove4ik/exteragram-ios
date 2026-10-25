package org.telegram.ui.Stars;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$PendingPaidReactions$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ StarsController.PendingPaidReactions f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ StarsController$PendingPaidReactions$$ExternalSyntheticLambda3(StarsController.PendingPaidReactions r1, MessagesController r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StarsController.PendingPaidReactions.m19154$r8$lambda$opaa03BD6n4U0m5NpxpofrLgxI(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
