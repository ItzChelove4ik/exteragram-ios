package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class PremiumPreviewFragment$$ExternalSyntheticLambda22 implements RequestDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ TLRPC.TL_payments_assignPlayMarketTransaction f$3;

    public /* synthetic */ PremiumPreviewFragment$$ExternalSyntheticLambda22(int r1, Runnable r2, BaseFragment r3, TLRPC.TL_payments_assignPlayMarketTransaction r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        PremiumPreviewFragment.m17580$r8$lambda$6jew7CtL0cInUdEVCw9VPbiy1Y(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
