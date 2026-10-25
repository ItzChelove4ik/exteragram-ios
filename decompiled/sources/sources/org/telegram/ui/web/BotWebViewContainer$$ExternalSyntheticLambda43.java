package org.telegram.ui.web;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda43 implements Utilities.Callback {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TL_keyboard.TL_buttonTypeRequestPeer f$3;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda43(BotWebViewContainer r1, BotWebViewContainer.WebRequestContext r2, String r3, TL_keyboard.TL_buttonTypeRequestPeer r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        BotWebViewContainer.$r8$lambda$kzVCxsSbWX2YAXvSwYoSp0uUrug(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.User) r4);
    }
}
