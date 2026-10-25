package org.telegram.ui.web;

import java.util.ArrayList;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.TopicsFragment;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda46 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TL_keyboard.TL_buttonTypeRequestPeer f$3;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$4;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda46(BotWebViewContainer r1, boolean[] r2, String r3, TL_keyboard.TL_buttonTypeRequestPeer r4, BotWebViewContainer.WebRequestContext r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r14, ArrayList r15, CharSequence r16, boolean r17, boolean r18, int r19, int r20, TopicsFragment r21) {
        return BotWebViewContainer.m21963$r8$lambda$WsDXKJRSSsBX7vtPIIg6jMH7M(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r14, r15, r16, r17, r18, r19, r20, r21);
    }
}
