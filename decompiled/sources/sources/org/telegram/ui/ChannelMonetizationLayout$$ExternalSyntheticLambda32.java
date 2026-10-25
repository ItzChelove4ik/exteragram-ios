package org.telegram.ui;

import android.app.Activity;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelMonetizationLayout$$ExternalSyntheticLambda32 implements RequestDelegate {
    public final /* synthetic */ ChannelMonetizationLayout f$0;
    public final /* synthetic */ TwoStepVerificationActivity f$1;
    public final /* synthetic */ Activity f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ ChannelMonetizationLayout$$ExternalSyntheticLambda32(ChannelMonetizationLayout r1, TwoStepVerificationActivity r2, Activity r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ChannelMonetizationLayout.$r8$lambda$wl0KdogpbFFLm4SxoA3JahmNwqQ(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
