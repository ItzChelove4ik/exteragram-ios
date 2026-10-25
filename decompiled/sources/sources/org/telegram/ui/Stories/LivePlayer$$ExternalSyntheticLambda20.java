package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class LivePlayer$$ExternalSyntheticLambda20 implements RequestDelegate {
    public final /* synthetic */ LivePlayer f$0;
    public final /* synthetic */ TLObject f$1;

    public /* synthetic */ LivePlayer$$ExternalSyntheticLambda20(LivePlayer r1, TLObject r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        LivePlayer.$r8$lambda$bukYYc3ywdM5fKgcAK_FQ7fIPhQ(this.f$0, this.f$1, r2, r3);
    }
}
