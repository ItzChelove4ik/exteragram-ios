package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPPreNotificationService$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ int f$0;

    public /* synthetic */ VoIPPreNotificationService$$ExternalSyntheticLambda6(int r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        VoIPPreNotificationService.m5288$r8$lambda$xd9f9h0l8PWlBVsXRSKfIBo_k(this.f$0, r1, r2);
    }
}
