package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback2 f$0;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda7(Utilities.Callback2 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        ExportRequestsController.$r8$lambda$vkObqxU0pvSsBX3VHgHI3JaG6Os(this.f$0, r1, r2);
    }
}
