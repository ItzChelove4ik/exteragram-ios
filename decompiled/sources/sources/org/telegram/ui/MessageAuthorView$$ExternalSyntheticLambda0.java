package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class MessageAuthorView$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ MessageAuthorView f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MessageAuthorView$$ExternalSyntheticLambda0(MessageAuthorView r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        MessageAuthorView.m16107$r8$lambda$ikcB4ULHe_uYPvPNph2QN6kvME(this.f$0, this.f$1, r2, r3);
    }
}
