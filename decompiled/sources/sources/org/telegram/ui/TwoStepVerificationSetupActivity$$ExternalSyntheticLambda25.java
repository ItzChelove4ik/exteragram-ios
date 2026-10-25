package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class TwoStepVerificationSetupActivity$$ExternalSyntheticLambda25 implements RequestDelegate {
    public final /* synthetic */ TwoStepVerificationSetupActivity f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ TwoStepVerificationSetupActivity$$ExternalSyntheticLambda25(TwoStepVerificationSetupActivity r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        TwoStepVerificationSetupActivity.m21061$r8$lambda$gamcyqC23n4cD5cS4ud1IS0KGQ(this.f$0, this.f$1, r2, r3);
    }
}
