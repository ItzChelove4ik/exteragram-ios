package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class TwoStepVerificationActivity$$ExternalSyntheticLambda12 implements RequestDelegate {
    public final /* synthetic */ TwoStepVerificationActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ TwoStepVerificationActivity$$ExternalSyntheticLambda12(TwoStepVerificationActivity r1, boolean r2, boolean r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        TwoStepVerificationActivity.m21029$r8$lambda$z4Yi0zTrpcbjwdpWdAbzq88f0(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
