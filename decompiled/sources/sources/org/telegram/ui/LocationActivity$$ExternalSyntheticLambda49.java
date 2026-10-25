package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LocationActivity$$ExternalSyntheticLambda49 implements RequestDelegate {
    public final /* synthetic */ LocationActivity f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ LocationActivity$$ExternalSyntheticLambda49(LocationActivity r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r4, TLRPC.TL_error r5) {
        LocationActivity.$r8$lambda$FjqhIWzTbC_WnO_AP8SHHTWrcUU(this.f$0, this.f$1, r4, r5);
    }
}
