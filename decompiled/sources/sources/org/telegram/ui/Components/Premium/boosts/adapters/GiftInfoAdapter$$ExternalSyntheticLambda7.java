package org.telegram.ui.Components.Premium.boosts.adapters;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Premium.boosts.cells.ActionBtnCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class GiftInfoAdapter$$ExternalSyntheticLambda7 implements Utilities.Callback {
    public final /* synthetic */ GiftInfoAdapter f$0;
    public final /* synthetic */ ActionBtnCell f$1;

    public /* synthetic */ GiftInfoAdapter$$ExternalSyntheticLambda7(GiftInfoAdapter r1, ActionBtnCell r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        GiftInfoAdapter.m12155$r8$lambda$4Nbaj00BVv3olzbKpE92TUw1ZY(this.f$0, this.f$1, (TLRPC.TL_error) r2);
    }
}
