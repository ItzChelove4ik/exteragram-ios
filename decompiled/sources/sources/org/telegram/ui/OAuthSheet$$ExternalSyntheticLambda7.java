package org.telegram.ui;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda7 implements View.OnClickListener {
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ BottomSheet f$2;
    public final /* synthetic */ ButtonWithCounterView f$3;
    public final /* synthetic */ BotWebViewContainer f$4;
    public final /* synthetic */ int f$5;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda7(TLRPC.TL_messages_requestUrlAuth r1, boolean[] r2, BottomSheet r3, ButtonWithCounterView r4, BotWebViewContainer r5, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r8) {
        OAuthSheet.$r8$lambda$zN4O2JcVZJ2SPRwDmEJNBYP5WTw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r8);
    }
}
