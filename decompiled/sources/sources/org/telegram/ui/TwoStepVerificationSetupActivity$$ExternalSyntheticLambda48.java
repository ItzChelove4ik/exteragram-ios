package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class TwoStepVerificationSetupActivity$$ExternalSyntheticLambda48 implements RequestDelegate {
    public final /* synthetic */ TwoStepVerificationSetupActivity f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ TwoStepVerificationSetupActivity$$ExternalSyntheticLambda48(TwoStepVerificationSetupActivity r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        TwoStepVerificationSetupActivity.m21044$r8$lambda$0N14BsnqMsJCPCXA5gr2iEtbjY(this.f$0, this.f$1, r2, r3);
    }
}
