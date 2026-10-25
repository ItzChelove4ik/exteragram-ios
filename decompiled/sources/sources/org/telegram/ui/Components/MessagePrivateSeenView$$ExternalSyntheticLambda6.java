package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class MessagePrivateSeenView$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ TLRPC.TL_error f$0;
    public final /* synthetic */ ButtonWithCounterView f$1;
    public final /* synthetic */ BottomSheet f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ MessagePrivateSeenView$$ExternalSyntheticLambda6(TLRPC.TL_error r1, ButtonWithCounterView r2, BottomSheet r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagePrivateSeenView.m11296$r8$lambda$guwLb45BisSIq2eY_xOdk0X5nY(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
