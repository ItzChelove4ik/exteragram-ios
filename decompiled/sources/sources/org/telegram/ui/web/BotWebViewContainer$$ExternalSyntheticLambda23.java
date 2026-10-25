package org.telegram.ui.web;

import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda23 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$1;
    public final /* synthetic */ BotWebViewContainer.PopupButton f$2;
    public final /* synthetic */ AtomicBoolean f$3;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda23(BotWebViewContainer r1, BotWebViewContainer.WebRequestContext r2, BotWebViewContainer.PopupButton r3, AtomicBoolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r7, int r8) {
        BotWebViewContainer.$r8$lambda$oGp1DOAVggxjGKoJroyWsj6ZzSI(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
