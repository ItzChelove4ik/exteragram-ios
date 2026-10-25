package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda0(StarsController r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StarsController.$r8$lambda$0t_OXs7j5DKnBN5gtJx7zK22GfE(this.f$0, this.f$1, r2, r3);
    }
}
