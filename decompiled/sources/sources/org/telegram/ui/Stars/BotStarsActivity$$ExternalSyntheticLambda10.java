package org.telegram.ui.Stars;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStarsActivity$$ExternalSyntheticLambda10 implements TwoStepVerificationActivity.TwoStepVerificationActivityDelegate {
    public final /* synthetic */ BotStarsActivity f$0;
    public final /* synthetic */ TwoStepVerificationActivity f$1;

    public /* synthetic */ BotStarsActivity$$ExternalSyntheticLambda10(BotStarsActivity r1, TwoStepVerificationActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.TwoStepVerificationActivity.TwoStepVerificationActivityDelegate
    public final void didEnterPassword(TLRPC.InputCheckPasswordSRP r2) {
        BotStarsActivity.$r8$lambda$I5RczT58kvPHSwH2vblHI650PLA(this.f$0, this.f$1, r2);
    }
}
