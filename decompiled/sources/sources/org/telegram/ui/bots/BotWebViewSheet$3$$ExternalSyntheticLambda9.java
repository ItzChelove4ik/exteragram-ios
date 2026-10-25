package org.telegram.ui.bots;

import org.telegram.ui.Components.OverlayActionBarLayoutDialog;
import org.telegram.ui.PaymentFormActivity;
import org.telegram.ui.bots.BotWebViewSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotWebViewSheet$3$$ExternalSyntheticLambda9 implements PaymentFormActivity.PaymentFormCallback {
    public final /* synthetic */ BotWebViewSheet.AnonymousClass3 f$0;
    public final /* synthetic */ OverlayActionBarLayoutDialog f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ BotWebViewSheet$3$$ExternalSyntheticLambda9(BotWebViewSheet.AnonymousClass3 r1, OverlayActionBarLayoutDialog r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.PaymentFormActivity.PaymentFormCallback
    public final void onInvoiceStatusChanged(PaymentFormActivity.InvoiceStatus r3) {
        BotWebViewSheet.AnonymousClass3.$r8$lambda$vpSP74aKGk4re1yRRB7SZOtdQfs(this.f$0, this.f$1, this.f$2, r3);
    }
}
