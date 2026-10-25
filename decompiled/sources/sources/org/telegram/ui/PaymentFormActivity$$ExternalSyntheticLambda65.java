package org.telegram.ui;

import android.app.Activity;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.INavigationLayout;

/* loaded from: classes6.dex */
public final /* synthetic */ class PaymentFormActivity$$ExternalSyntheticLambda65 implements MessagesController.NewMessageCallback {
    public final /* synthetic */ PaymentFormActivity f$0;
    public final /* synthetic */ INavigationLayout f$1;
    public final /* synthetic */ Activity f$2;

    public /* synthetic */ PaymentFormActivity$$ExternalSyntheticLambda65(PaymentFormActivity r1, INavigationLayout r2, Activity r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.MessagesController.NewMessageCallback
    public final boolean onMessageReceived(TLRPC.Message r3) {
        return PaymentFormActivity.$r8$lambda$Lai7ru93IFq_rGOPdiYzN4MqkaA(this.f$0, this.f$1, this.f$2, r3);
    }
}
