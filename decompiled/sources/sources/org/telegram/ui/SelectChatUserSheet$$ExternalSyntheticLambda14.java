package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectChatUserSheet$$ExternalSyntheticLambda14 implements RequestDelegate {
    public final /* synthetic */ SelectChatUserSheet f$0;
    public final /* synthetic */ TLRPC.InputCheckPasswordSRP f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ TwoStepVerificationActivity f$3;
    public final /* synthetic */ TLRPC.TL_channels_editCreator f$4;

    public /* synthetic */ SelectChatUserSheet$$ExternalSyntheticLambda14(SelectChatUserSheet r1, TLRPC.InputCheckPasswordSRP r2, TLRPC.User r3, TwoStepVerificationActivity r4, TLRPC.TL_channels_editCreator r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        SelectChatUserSheet.$r8$lambda$Ak9gsq0220uPd26F3YczaLXrojk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
