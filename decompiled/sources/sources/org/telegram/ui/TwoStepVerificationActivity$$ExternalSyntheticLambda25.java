package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class TwoStepVerificationActivity$$ExternalSyntheticLambda25 implements RequestDelegate {
    public final /* synthetic */ TwoStepVerificationActivity f$0;

    public /* synthetic */ TwoStepVerificationActivity$$ExternalSyntheticLambda25(TwoStepVerificationActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        TwoStepVerificationActivity.$r8$lambda$lBDTXUqfBbtoZwsmE4WGAuUh0fA(this.f$0, r1, r2);
    }
}
