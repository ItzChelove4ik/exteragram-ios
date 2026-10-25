package org.telegram.ui.Business;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatbotSheet$$ExternalSyntheticLambda8 implements RequestDelegate {
    public final /* synthetic */ ChatbotSheet f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ ChatbotSheet$$ExternalSyntheticLambda8(ChatbotSheet r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChatbotSheet.$r8$lambda$m6Kc_JtvtDAQhB1zJo8WSgVbcQ0(this.f$0, this.f$1, r2, r3);
    }
}
