package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class TwoStepVerificationActivity$$ExternalSyntheticLambda21 implements RequestDelegate {
    public final /* synthetic */ TwoStepVerificationActivity f$0;
    public final /* synthetic */ byte[] f$1;
    public final /* synthetic */ byte[] f$2;

    public /* synthetic */ TwoStepVerificationActivity$$ExternalSyntheticLambda21(TwoStepVerificationActivity r1, byte[] r2, byte[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        TwoStepVerificationActivity.m21018$r8$lambda$OOPyyiEH9tIA6GjpIHHU4QWBlI(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
