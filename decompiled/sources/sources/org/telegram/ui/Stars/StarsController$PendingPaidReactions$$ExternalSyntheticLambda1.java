package org.telegram.ui.Stars;

import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$PendingPaidReactions$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ StarsController.PendingPaidReactions f$0;

    public /* synthetic */ StarsController$PendingPaidReactions$$ExternalSyntheticLambda1(StarsController.PendingPaidReactions r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.cancel();
    }
}
