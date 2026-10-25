package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class LivePlayer$$ExternalSyntheticLambda17 implements RequestDelegate {
    public final /* synthetic */ LivePlayer f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ LivePlayer$$ExternalSyntheticLambda17(LivePlayer r1, int[] r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        LivePlayer.$r8$lambda$QlgEAYFrsBfEI8I_MIPUX4PEmRg(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
