package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectChatUserSheet$$ExternalSyntheticLambda6 implements TwoStepVerificationActivity.TwoStepVerificationActivityDelegate {
    public final /* synthetic */ SelectChatUserSheet f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ TwoStepVerificationActivity f$2;

    public /* synthetic */ SelectChatUserSheet$$ExternalSyntheticLambda6(SelectChatUserSheet r1, TLRPC.User r2, TwoStepVerificationActivity r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.TwoStepVerificationActivity.TwoStepVerificationActivityDelegate
    public final void didEnterPassword(TLRPC.InputCheckPasswordSRP r3) {
        SelectChatUserSheet.m18765$r8$lambda$UPXQpy_XCse6vv8kAjG02P2pTI(this.f$0, this.f$1, this.f$2, r3);
    }
}
