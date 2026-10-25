package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda14 implements RequestDelegate {
    public final /* synthetic */ StoriesController f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda14(StoriesController r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r4, TLRPC.TL_error r5) {
        StoriesController.$r8$lambda$B__RWNSG6JLDKd9PfMqAGYg4tdE(this.f$0, this.f$1, r4, r5);
    }
}
