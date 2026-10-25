package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda20 implements Utilities.Callback2 {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ BottomSheet f$1;
    public final /* synthetic */ String f$10;
    public final /* synthetic */ Theme.ResourcesProvider f$11;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Integer f$3;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ TLRPC.UrlAuthResult f$6;
    public final /* synthetic */ String[] f$7;
    public final /* synthetic */ boolean f$8;
    public final /* synthetic */ BotWebViewContainer f$9;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda20(AlertDialog r1, BottomSheet r2, boolean r3, Integer r4, TLRPC.TL_messages_requestUrlAuth r5, String r6, TLRPC.UrlAuthResult r7, String[] r8, boolean r9, BotWebViewContainer r10, String r11, Theme.ResourcesProvider r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r11;
        this.f$11 = r12;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r15, Object r16) {
        OAuthSheet.m16352$r8$lambda$QH7aFdNPh8Gei4JjHn1iAiSUKU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, (TLRPC.UrlAuthResult) r15, (TLRPC.TL_error) r16);
    }
}
