package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ ExportRequestsController f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda0(ExportRequestsController r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r4, Object r5) {
        ExportRequestsController.$r8$lambda$PMUx98NPmod1Jx4SEsfQdFvUrYg(this.f$0, this.f$1, (TLObject) r4, (TLRPC.TL_error) r5);
    }
}
