package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda82 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda82(StarsController r1, long r2, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StarsController.$r8$lambda$yE40G0iQ6nMoeT79S7W0VJZXm2I(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
