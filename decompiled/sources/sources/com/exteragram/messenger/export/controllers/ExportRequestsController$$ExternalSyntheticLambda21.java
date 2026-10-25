package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda21 implements Utilities.Callback {
    public final /* synthetic */ ExportRequestsController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda21(ExportRequestsController r1, int r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        ExportRequestsController.$r8$lambda$ID3PlX3WVIkpqmRir75qkyEtSFQ(this.f$0, this.f$1, this.f$2, (TLRPC.messages_Messages) r3);
    }
}
