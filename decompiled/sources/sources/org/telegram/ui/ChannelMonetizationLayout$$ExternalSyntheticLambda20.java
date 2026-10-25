package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelMonetizationLayout$$ExternalSyntheticLambda20 implements TwoStepVerificationActivity.TwoStepVerificationActivityDelegate {
    public final /* synthetic */ ChannelMonetizationLayout f$0;
    public final /* synthetic */ TwoStepVerificationActivity f$1;

    public /* synthetic */ ChannelMonetizationLayout$$ExternalSyntheticLambda20(ChannelMonetizationLayout r1, TwoStepVerificationActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.TwoStepVerificationActivity.TwoStepVerificationActivityDelegate
    public final void didEnterPassword(TLRPC.InputCheckPasswordSRP r2) {
        ChannelMonetizationLayout.m7345$r8$lambda$OIj3e0e6pVs8fI1Iro0AXupLBs(this.f$0, this.f$1, r2);
    }
}
