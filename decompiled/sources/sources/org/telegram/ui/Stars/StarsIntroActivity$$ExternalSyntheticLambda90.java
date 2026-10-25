package org.telegram.ui.Stars;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsIntroActivity$$ExternalSyntheticLambda90 implements Runnable {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ BottomSheet[] f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ TLRPC.TL_messages_checkChatInvite f$5;

    public /* synthetic */ StarsIntroActivity$$ExternalSyntheticLambda90(ButtonWithCounterView r1, TLObject r2, BottomSheet[] r3, Theme.ResourcesProvider r4, int r5, TLRPC.TL_messages_checkChatInvite r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsIntroActivity.m19188$r8$lambda$v_vgNFcTwMDDVzGV44BUag7P1M(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
