package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelMonetizationLayout$$ExternalSyntheticLambda46 implements RequestDelegate {
    public final /* synthetic */ ChannelMonetizationLayout f$0;
    public final /* synthetic */ TwoStepVerificationActivity f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ ChannelMonetizationLayout$$ExternalSyntheticLambda46(ChannelMonetizationLayout r1, TwoStepVerificationActivity r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ChannelMonetizationLayout.$r8$lambda$rcE3SDHwFf_1o7jiYRBsWHhAvr4(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
