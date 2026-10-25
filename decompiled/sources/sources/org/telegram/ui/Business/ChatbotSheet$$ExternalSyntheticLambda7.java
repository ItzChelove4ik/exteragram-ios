package org.telegram.ui.Business;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatbotSheet$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ ChatbotSheet f$0;
    public final /* synthetic */ TL_account.TL_connectedBot f$1;
    public final /* synthetic */ TL_account.TL_businessBotRecipients f$2;

    public /* synthetic */ ChatbotSheet$$ExternalSyntheticLambda7(ChatbotSheet r1, TL_account.TL_connectedBot r2, TL_account.TL_businessBotRecipients r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ChatbotSheet.$r8$lambda$sxCmLRDBRaET3eM0LmXT_JTpYWw(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
