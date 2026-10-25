package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda45 implements Utilities.Callback2 {
    public final /* synthetic */ ExportRequestsController f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda45(ExportRequestsController r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        ExportRequestsController.$r8$lambda$UfhuqH_l5CtFSzIR8sl9OJ5ezus(this.f$0, this.f$1, (TLObject) r2, (TLRPC.TL_error) r3);
    }
}
