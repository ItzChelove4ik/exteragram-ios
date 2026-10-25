package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class MessagePrivateSeenView$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ MessagePrivateSeenView f$0;

    public /* synthetic */ MessagePrivateSeenView$$ExternalSyntheticLambda5(MessagePrivateSeenView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        MessagePrivateSeenView.$r8$lambda$F4wqKx8CaXEC400kn9LeyNuRTsU(this.f$0, r1, r2);
    }
}
