package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class TopicsController$$ExternalSyntheticLambda29 implements RequestDelegate {
    public final /* synthetic */ TopicsController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ TopicsController$$ExternalSyntheticLambda29(TopicsController r1, long r2, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        TopicsController.$r8$lambda$nJ7F91oxCOB_Piffj5k6_pWOkGM(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
