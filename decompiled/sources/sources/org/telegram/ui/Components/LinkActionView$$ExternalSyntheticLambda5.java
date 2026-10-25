package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class LinkActionView$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ LinkActionView f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteExported f$1;

    public /* synthetic */ LinkActionView$$ExternalSyntheticLambda5(LinkActionView r1, TLRPC.TL_chatInviteExported r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        LinkActionView.$r8$lambda$n8jRFUCg0m2vxS4Yj3QcIwH7hT4(this.f$0, this.f$1, r2, r3);
    }
}
