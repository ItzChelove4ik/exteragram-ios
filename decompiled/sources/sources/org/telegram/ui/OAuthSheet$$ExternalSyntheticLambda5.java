package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda5 implements Utilities.Callback {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$10;
    public final /* synthetic */ BottomSheet f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ TLRPC.UrlAuthResult f$5;
    public final /* synthetic */ String[] f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ BotWebViewContainer f$8;
    public final /* synthetic */ String f$9;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda5(int[] r1, TLRPC.TL_messages_requestUrlAuth r2, BottomSheet r3, boolean r4, String r5, TLRPC.UrlAuthResult r6, String[] r7, boolean r8, BotWebViewContainer r9, String r10, Theme.ResourcesProvider r11) {
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
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r13) {
        OAuthSheet.$r8$lambda$_XhonlkijnqPLxQI1Pl3uWgZHTg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, (Integer) r13);
    }
}
