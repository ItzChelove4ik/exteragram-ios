package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.TrendingStickersLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class TrendingStickersLayout$TrendingStickersAdapter$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ TrendingStickersLayout.TrendingStickersAdapter f$0;

    public /* synthetic */ TrendingStickersLayout$TrendingStickersAdapter$$ExternalSyntheticLambda2(TrendingStickersLayout.TrendingStickersAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        TrendingStickersLayout.TrendingStickersAdapter.$r8$lambda$wIJ2GqiiBAVFUhUQr_0ZxgZhnnE(this.f$0, r1, r2);
    }
}
