package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda49 implements RequestDelegate {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ byte[] f$1;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda49(VoIPService r1, byte[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        VoIPService.m5317$r8$lambda$eNKhIeT7JmsCoHCpo43PWKWk4(this.f$0, this.f$1, r2, r3);
    }
}
