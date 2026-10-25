package org.telegram.ui.Gifts;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class ProfileGiftsContainer$$ExternalSyntheticLambda17 implements RequestDelegate {
    public final /* synthetic */ ProfileGiftsContainer f$0;
    public final /* synthetic */ BaseFragment f$1;

    public /* synthetic */ ProfileGiftsContainer$$ExternalSyntheticLambda17(ProfileGiftsContainer r1, BaseFragment r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ProfileGiftsContainer.$r8$lambda$PiflF7dP_XL2XId7FDu0rHYSHs0(this.f$0, this.f$1, r2, r3);
    }
}
