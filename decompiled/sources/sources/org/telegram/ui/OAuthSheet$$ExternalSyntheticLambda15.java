package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda15 implements Utilities.Callback2 {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ BottomSheet f$1;
    public final /* synthetic */ BotWebViewContainer f$10;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ int[] f$5;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$6;
    public final /* synthetic */ String f$7;
    public final /* synthetic */ TLRPC.TL_urlAuthResultRequest f$8;
    public final /* synthetic */ TLRPC.TL_messages_acceptUrlAuth f$9;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda15(boolean[] r1, BottomSheet r2, String r3, Theme.ResourcesProvider r4, boolean r5, int[] r6, TLRPC.TL_messages_requestUrlAuth r7, String r8, TLRPC.TL_urlAuthResultRequest r9, TLRPC.TL_messages_acceptUrlAuth r10, BotWebViewContainer r11) {
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

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r14, Object r15) {
        OAuthSheet.$r8$lambda$x55_jhWYHbgJgOmuImGb3tCtbd8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, (TLRPC.UrlAuthResult) r14, (TLRPC.TL_error) r15);
    }
}
