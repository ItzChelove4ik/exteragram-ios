package org.telegram.ui.web;

import java.util.List;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.MultiContactsSelectorBottomSheet;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda44 implements MultiContactsSelectorBottomSheet.SelectorListener {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TL_keyboard.TL_buttonTypeRequestPeer f$3;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$4;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda44(BotWebViewContainer r1, boolean[] r2, String r3, TL_keyboard.TL_buttonTypeRequestPeer r4, BotWebViewContainer.WebRequestContext r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.ui.MultiContactsSelectorBottomSheet.SelectorListener
    public final void onUserSelected(List r7) {
        BotWebViewContainer.$r8$lambda$8NDPmdVMVYESF97_vL5FBVZDv4w(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
