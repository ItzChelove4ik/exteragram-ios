package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda33 implements RequestDelegate {
    public final /* synthetic */ StoriesController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda33(StoriesController r1, int r2, String r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        StoriesController.m19690$r8$lambda$OLCiP3VXd9goUoP_bdddvmdojc(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
