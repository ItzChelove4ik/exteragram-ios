package org.telegram.ui.Components;

import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.PaymentFormActivity;
import org.telegram.ui.bots.ChatAttachAlertBotWebViewLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlert$1$$ExternalSyntheticLambda6 implements PaymentFormActivity.PaymentFormCallback {
    public final /* synthetic */ OverlayActionBarLayoutDialog f$0;
    public final /* synthetic */ ChatAttachAlertBotWebViewLayout f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ ChatAttachAlert$1$$ExternalSyntheticLambda6(OverlayActionBarLayoutDialog r1, ChatAttachAlertBotWebViewLayout r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.PaymentFormActivity.PaymentFormCallback
    public final void onInvoiceStatusChanged(PaymentFormActivity.InvoiceStatus r3) {
        ChatAttachAlert.AnonymousClass1.$r8$lambda$PmxU41FRyzgAm9jTxK4Sg8RKKow(this.f$0, this.f$1, this.f$2, r3);
    }
}
