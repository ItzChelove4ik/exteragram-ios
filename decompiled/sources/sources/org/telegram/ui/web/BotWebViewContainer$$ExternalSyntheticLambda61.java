package org.telegram.ui.web;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda61 implements RequestDelegate {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ String[] f$1;
    public final /* synthetic */ AlertDialog f$2;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda61(BotWebViewContainer r1, String[] r2, AlertDialog r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        BotWebViewContainer.m21967$r8$lambda$ezt8Hi95Mzku_2MsScFPNSrfvw(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
