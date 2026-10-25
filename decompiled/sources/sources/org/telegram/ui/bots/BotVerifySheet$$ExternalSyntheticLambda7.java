package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotVerifySheet$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ BottomSheet f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ BotVerifySheet$$ExternalSyntheticLambda7(ButtonWithCounterView r1, TLObject r2, BottomSheet r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BotVerifySheet.$r8$lambda$CqNQ0xOAXduerhTwwAKKDewTzMc(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
