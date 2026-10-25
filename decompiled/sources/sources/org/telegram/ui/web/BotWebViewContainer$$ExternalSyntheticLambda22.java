package org.telegram.ui.web;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda22 implements Utilities.Callback2 {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda22(BotWebViewContainer r1, BotWebViewContainer.WebRequestContext r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        BotWebViewContainer.$r8$lambda$WM1cflUWlwXm_J8ggOi2TM50gm4(this.f$0, this.f$1, this.f$2, (TL_keyboard.KeyboardButton) r3, (TLRPC.TL_error) r4);
    }
}
