package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda179 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TwoStepVerificationActivity f$1;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda179(StarGiftSheet r1, TwoStepVerificationActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StarGiftSheet.$r8$lambda$7k7hiYrp6kqiEmzvArlccl4BzJo(this.f$0, this.f$1, r2, r3);
    }
}
