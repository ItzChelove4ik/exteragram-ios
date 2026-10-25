package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStarsActivity$$ExternalSyntheticLambda26 implements RequestDelegate {
    public final /* synthetic */ BotStarsActivity f$0;
    public final /* synthetic */ TwoStepVerificationActivity f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ BotStarsActivity$$ExternalSyntheticLambda26(BotStarsActivity r1, TwoStepVerificationActivity r2, boolean r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        BotStarsActivity.$r8$lambda$leq2EE2qk8M_VeC8ek1cHPVdls0(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
