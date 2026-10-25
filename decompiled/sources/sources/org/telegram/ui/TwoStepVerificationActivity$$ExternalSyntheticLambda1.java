package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class TwoStepVerificationActivity$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ TwoStepVerificationActivity f$0;

    public /* synthetic */ TwoStepVerificationActivity$$ExternalSyntheticLambda1(TwoStepVerificationActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        TwoStepVerificationActivity.$r8$lambda$w8rhMKVNmpkhhcVgcSjPS8RLSOM(this.f$0, r1, r2);
    }
}
