package com.exteragram.messenger.export.controllers;

import com.exteragram.messenger.export.api.ExportRequests$InitTakeoutSession;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda38 implements RequestDelegate {
    public final /* synthetic */ ExportRequestsController f$0;
    public final /* synthetic */ ExportRequests$InitTakeoutSession f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda38(ExportRequestsController r1, ExportRequests$InitTakeoutSession r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ExportRequestsController.$r8$lambda$R7VhIwrAe6QIqfwQQzTTweqLrjw(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
