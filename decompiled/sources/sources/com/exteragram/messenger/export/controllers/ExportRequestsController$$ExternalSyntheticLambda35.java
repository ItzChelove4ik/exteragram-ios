package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda35 implements Utilities.Callback {
    public final /* synthetic */ ExportRequestsController f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda35(ExportRequestsController r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ExportRequestsController.$r8$lambda$grVlOxfUNIKamC6ha7oDM6_s4eE(this.f$0, this.f$1, (TLRPC.InputUser) r2);
    }
}
