package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotVerifySheet$$ExternalSyntheticLambda4 implements RequestDelegate {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ BottomSheet f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ BotVerifySheet$$ExternalSyntheticLambda4(ButtonWithCounterView r1, BottomSheet r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        BotVerifySheet.$r8$lambda$xlFu_NtTsgyBAI6Da4WZvNnhyMU(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
