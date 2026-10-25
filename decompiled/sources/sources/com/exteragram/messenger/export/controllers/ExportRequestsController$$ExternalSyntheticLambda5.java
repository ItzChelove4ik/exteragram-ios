package com.exteragram.messenger.export.controllers;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExportRequestsController$$ExternalSyntheticLambda5 implements Utilities.Callback {
    public final /* synthetic */ ExportRequestsController f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ ExportRequestsController$$ExternalSyntheticLambda5(ExportRequestsController r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ExportRequestsController.$r8$lambda$DmwZRarXo4KJhcPxoUUiBRgasA4(this.f$0, this.f$1, (TLRPC.messages_Messages) r2);
    }
}
