package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda29 implements Runnable {
    public final /* synthetic */ Utilities.Callback2 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda29(Utilities.Callback2 r1, TLObject r2, TLRPC.TL_error r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ExportRequestsController.$r8$lambda$w5QCUScDJuCGB_rvoe7X9bpqBng(this.f$0, this.f$1, this.f$2);
    }
}
