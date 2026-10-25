package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class MessagePrivateSeenView$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ BottomSheet f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ MessagePrivateSeenView$$ExternalSyntheticLambda2(ButtonWithCounterView r1, BottomSheet r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MessagePrivateSeenView.$r8$lambda$7SzQMeODf1mM0uEfnn6a1cRtd24(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
