package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class PremiumPreviewFragment$$ExternalSyntheticLambda25 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ TLRPC.TL_payments_assignPlayMarketTransaction f$3;

    public /* synthetic */ PremiumPreviewFragment$$ExternalSyntheticLambda25(int r1, TLRPC.TL_error r2, BaseFragment r3, TLRPC.TL_payments_assignPlayMarketTransaction r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PremiumPreviewFragment.m17585$r8$lambda$k5w78zd7EGukkl5gJ82iQLPhws(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
