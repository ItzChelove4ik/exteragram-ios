package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ ButtonWithCounterView f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$10;
    public final /* synthetic */ boolean f$11;
    public final /* synthetic */ String f$12;
    public final /* synthetic */ TLRPC.TL_urlAuthResultRequest f$13;
    public final /* synthetic */ BotWebViewContainer f$14;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$2;
    public final /* synthetic */ String[] f$3;
    public final /* synthetic */ TextCheckCell f$4;
    public final /* synthetic */ boolean[] f$5;
    public final /* synthetic */ int[] f$6;
    public final /* synthetic */ boolean[] f$7;
    public final /* synthetic */ BottomSheet f$8;
    public final /* synthetic */ String f$9;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda8(ButtonWithCounterView r1, ButtonWithCounterView r2, TLRPC.TL_messages_requestUrlAuth r3, String[] r4, TextCheckCell r5, boolean[] r6, int[] r7, boolean[] r8, BottomSheet r9, String r10, Theme.ResourcesProvider r11, boolean r12, String r13, TLRPC.TL_urlAuthResultRequest r14, BotWebViewContainer r15) {
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
        this.f$12 = r13;
        this.f$13 = r14;
        this.f$14 = r15;
    }

    @Override // java.lang.Runnable
    public final void run() {
        OAuthSheet.$r8$lambda$Ksf0UZGXVXhBJxF7xJZJZbnNlec(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14);
    }
}
