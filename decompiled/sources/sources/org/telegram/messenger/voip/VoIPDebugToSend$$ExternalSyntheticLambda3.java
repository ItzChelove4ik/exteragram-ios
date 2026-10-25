package org.telegram.messenger.voip;

import org.telegram.messenger.voip.VoIPDebugToSend;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPDebugToSend$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ VoIPDebugToSend f$0;
    public final /* synthetic */ VoIPDebugToSend.Data f$1;
    public final /* synthetic */ TL_phone.saveCallDebug f$2;

    public /* synthetic */ VoIPDebugToSend$$ExternalSyntheticLambda3(VoIPDebugToSend r1, VoIPDebugToSend.Data r2, TL_phone.saveCallDebug r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        VoIPDebugToSend.$r8$lambda$rgCk66cDJmQmzrHAOOrfGYWnTek(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
