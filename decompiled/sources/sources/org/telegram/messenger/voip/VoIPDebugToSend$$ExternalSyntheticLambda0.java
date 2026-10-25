package org.telegram.messenger.voip;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPDebugToSend$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ VoIPDebugToSend f$0;
    public final /* synthetic */ TL_phone.saveCallDebug f$1;

    public /* synthetic */ VoIPDebugToSend$$ExternalSyntheticLambda0(VoIPDebugToSend r1, TL_phone.saveCallDebug r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        VoIPDebugToSend.m5284$r8$lambda$rsujs8CUsOFHBW3o_DvrvL7BcA(this.f$0, this.f$1, (TLRPC.InputFile) r2);
    }
}
