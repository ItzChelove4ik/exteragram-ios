package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda4 implements Utilities.Callback2 {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda4(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        ExportRequestsController.m1329$r8$lambda$K5E2oN2LAwJDAki5Obw5XGaixo(this.f$0, (TLObject) r1, (TLRPC.TL_error) r2);
    }
}
