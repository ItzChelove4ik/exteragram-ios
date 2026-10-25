package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda41 implements Utilities.Callback2 {
    public final /* synthetic */ ExportRequestsController f$0;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda41(ExportRequestsController r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        ExportRequestsController.$r8$lambda$klc9ccxrT__Ncry5OLACYl3lmp4(this.f$0, (TLObject) r1, (TLRPC.TL_error) r2);
    }
}
