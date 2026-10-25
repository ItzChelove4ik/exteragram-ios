package org.telegram.ui.Stars;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda158 implements TwoStepVerificationActivity.TwoStepVerificationActivityDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TwoStepVerificationActivity f$1;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda158(StarGiftSheet r1, TwoStepVerificationActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.TwoStepVerificationActivity.TwoStepVerificationActivityDelegate
    public final void didEnterPassword(TLRPC.InputCheckPasswordSRP r2) {
        StarGiftSheet.$r8$lambda$ubT_9L350q7Aml0Ck5Oua9tnitc(this.f$0, this.f$1, r2);
    }
}
