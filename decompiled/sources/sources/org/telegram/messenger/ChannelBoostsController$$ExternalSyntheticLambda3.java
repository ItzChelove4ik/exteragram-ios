package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChannelBoostsController$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ ChannelBoostsController$$ExternalSyntheticLambda3(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        ChannelBoostsController.$r8$lambda$yTESJkbTEm_kXA770gSRm3mebzA(this.f$0, r1, r2);
    }
}
