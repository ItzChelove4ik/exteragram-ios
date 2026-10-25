package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda104 implements RequestDelegate {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TL_phone.checkGroupCall f$1;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda104(VoIPService r1, TL_phone.checkGroupCall r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        VoIPService.$r8$lambda$yC4JEJgzOiuaYpwH4DJ7EpTxX_M(this.f$0, this.f$1, r2, r3);
    }
}
