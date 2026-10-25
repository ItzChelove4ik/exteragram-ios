package org.telegram.ui.Stars;

import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$PaidMessagesToast$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ StarsController.PaidMessagesToast f$0;

    public /* synthetic */ StarsController$PaidMessagesToast$$ExternalSyntheticLambda1(StarsController.PaidMessagesToast r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.undo();
    }
}
