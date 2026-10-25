package org.telegram.ui;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectChatUserSheet$$ExternalSyntheticLambda15 implements Runnable {
    public final /* synthetic */ SelectChatUserSheet f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLRPC.InputCheckPasswordSRP f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ TwoStepVerificationActivity f$4;
    public final /* synthetic */ TLRPC.TL_channels_editCreator f$5;

    public /* synthetic */ SelectChatUserSheet$$ExternalSyntheticLambda15(SelectChatUserSheet r1, TLRPC.TL_error r2, TLRPC.InputCheckPasswordSRP r3, TLRPC.User r4, TwoStepVerificationActivity r5, TLRPC.TL_channels_editCreator r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SelectChatUserSheet.m18768$r8$lambda$gPgWI2TI4wO6yjTMaSHA660gqg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
