package org.telegram.ui.Business;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class BusinessLinksController$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ BusinessLinksController f$0;
    public final /* synthetic */ TL_account.TL_businessChatLink f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ BusinessLinksController$$ExternalSyntheticLambda7(BusinessLinksController r1, TL_account.TL_businessChatLink r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        BusinessLinksController.m6330$r8$lambda$aSav2QKRCbP6lkw2fghxfB2H4g(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
