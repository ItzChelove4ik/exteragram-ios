package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$GiftsList$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ StarsController.GiftsList f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ StarsController$GiftsList$$ExternalSyntheticLambda0(StarsController.GiftsList r1, int[] r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        StarsController.GiftsList.$r8$lambda$tMKXcJCxS_WwOpBsxNLl9vdYpQ8(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
