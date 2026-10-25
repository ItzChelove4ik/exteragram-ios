package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda10 implements Utilities.Callback2 {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda10(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        ExportRequestsController.m1330$r8$lambda$K6Z8Dxak9YlqZ3WIvUBpQOpswQ(this.f$0, (TLObject) r1, (TLRPC.TL_error) r2);
    }
}
