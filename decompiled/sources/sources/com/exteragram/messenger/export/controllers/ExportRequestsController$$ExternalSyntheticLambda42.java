package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda42 implements Utilities.Callback2 {
    public final /* synthetic */ ExportRequestsController f$0;
    public final /* synthetic */ Integer f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda42(ExportRequestsController r1, Integer r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        ExportRequestsController.m1336$r8$lambda$lA0eqqubdwImVIMiTJWatiC_mY(this.f$0, this.f$1, this.f$2, (TLObject) r3, (TLRPC.TL_error) r4);
    }
}
