package org.telegram.ui.Business;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class BusinessLinksController$$ExternalSyntheticLambda10 implements RequestDelegate {
    public final /* synthetic */ BusinessLinksController f$0;
    public final /* synthetic */ TL_account.TL_businessChatLink f$1;

    public /* synthetic */ BusinessLinksController$$ExternalSyntheticLambda10(BusinessLinksController r1, TL_account.TL_businessChatLink r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        BusinessLinksController.$r8$lambda$e6BELc2xeS3g1UzOyHFowy5n_3g(this.f$0, this.f$1, r2, r3);
    }
}
