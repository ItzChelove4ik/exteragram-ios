package org.telegram.ui.Stars;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$PendingPaidReactions$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLObject f$1;

    public /* synthetic */ StarsController$PendingPaidReactions$$ExternalSyntheticLambda5(MessagesController r1, TLObject r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.PendingPaidReactions.$r8$lambda$kijp3R4HHE7Ta17aLoMBIisboSE(this.f$0, this.f$1);
    }
}
