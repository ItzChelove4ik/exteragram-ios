package org.telegram.messenger;

import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChannelBoostsController$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ ChannelBoostsController.CanApplyBoost f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ ChannelBoostsController$$ExternalSyntheticLambda2(ChannelBoostsController.CanApplyBoost r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChannelBoostsController.$r8$lambda$jS7ipboGIzjP5Pq2w1Aoiw_q3VM(this.f$0, this.f$1, (TLRPC.TL_error) r2);
    }
}
