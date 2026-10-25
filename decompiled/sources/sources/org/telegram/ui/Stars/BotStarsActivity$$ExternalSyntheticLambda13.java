package org.telegram.ui.Stars;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStarsActivity$$ExternalSyntheticLambda13 implements TwoStepVerificationActivity.TwoStepVerificationActivityDelegate {
    public final /* synthetic */ BotStarsActivity f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TwoStepVerificationActivity f$2;

    public /* synthetic */ BotStarsActivity$$ExternalSyntheticLambda13(BotStarsActivity r1, long r2, TwoStepVerificationActivity r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.ui.TwoStepVerificationActivity.TwoStepVerificationActivityDelegate
    public final void didEnterPassword(TLRPC.InputCheckPasswordSRP r4) {
        BotStarsActivity.$r8$lambda$vjtqdpjEfLioQoWH7LOmEhTgt0g(this.f$0, this.f$1, this.f$2, r4);
    }
}
