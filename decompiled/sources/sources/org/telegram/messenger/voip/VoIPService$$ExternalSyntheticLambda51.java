package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda51 implements RequestDelegate {
    public final /* synthetic */ VoIPService f$0;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda51(VoIPService r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        VoIPService.m5295$r8$lambda$2T2NcP6lV_shD2UpykOoyD_UMQ(this.f$0, r1, r2);
    }
}
