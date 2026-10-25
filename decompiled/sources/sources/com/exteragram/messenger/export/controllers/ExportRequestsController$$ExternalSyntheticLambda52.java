package com.exteragram.messenger.export.controllers;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda52 implements RequestDelegate {
    public final /* synthetic */ ExportRequestsController f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda52(ExportRequestsController r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ExportRequestsController.$r8$lambda$843cuR6nUap0rKjH079ozdMbQGk(this.f$0, this.f$1, r2, r3);
    }
}
