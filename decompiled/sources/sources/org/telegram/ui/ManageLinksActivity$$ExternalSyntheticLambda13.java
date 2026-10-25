package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ManageLinksActivity$$ExternalSyntheticLambda13 implements RequestDelegate {
    public final /* synthetic */ ManageLinksActivity f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteExported f$1;

    public /* synthetic */ ManageLinksActivity$$ExternalSyntheticLambda13(ManageLinksActivity r1, TLRPC.TL_chatInviteExported r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ManageLinksActivity.m16054$r8$lambda$3Ba7LF5x_lC6NVWhrFiWRG129c(this.f$0, this.f$1, r2, r3);
    }
}
