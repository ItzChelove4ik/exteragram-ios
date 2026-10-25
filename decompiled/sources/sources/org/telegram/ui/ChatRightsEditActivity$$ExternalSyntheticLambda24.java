package org.telegram.ui;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatRightsEditActivity$$ExternalSyntheticLambda24 implements Runnable {
    public final /* synthetic */ ChatRightsEditActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLRPC.InputCheckPasswordSRP f$2;
    public final /* synthetic */ TwoStepVerificationActivity f$3;
    public final /* synthetic */ TLRPC.TL_channels_editCreator f$4;

    public /* synthetic */ ChatRightsEditActivity$$ExternalSyntheticLambda24(ChatRightsEditActivity r1, TLRPC.TL_error r2, TLRPC.InputCheckPasswordSRP r3, TwoStepVerificationActivity r4, TLRPC.TL_channels_editCreator r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatRightsEditActivity.$r8$lambda$YwHNmBqv15dRPRcFACCgjfw1pjQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
