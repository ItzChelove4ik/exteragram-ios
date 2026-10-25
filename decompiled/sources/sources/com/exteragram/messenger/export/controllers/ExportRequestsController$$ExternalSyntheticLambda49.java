package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda49 implements Utilities.Callback2 {
    public final /* synthetic */ ExportRequestsController f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda49(ExportRequestsController r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        ExportRequestsController.m1328$r8$lambda$FpZP15H65FKsSDHDt6t_Z_JEPg(this.f$0, this.f$1, (TLObject) r2, (TLRPC.TL_error) r3);
    }
}
