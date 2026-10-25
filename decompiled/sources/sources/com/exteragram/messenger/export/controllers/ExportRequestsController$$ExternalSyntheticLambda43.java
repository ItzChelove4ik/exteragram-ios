package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda43 implements RequestDelegate {
    public final /* synthetic */ ExportRequestsController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda43(ExportRequestsController r1, long r2, Utilities.Callback2 r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ExportRequestsController.$r8$lambda$lmRX6fF1QzoG_gs8r_wrPo5AgA0(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
