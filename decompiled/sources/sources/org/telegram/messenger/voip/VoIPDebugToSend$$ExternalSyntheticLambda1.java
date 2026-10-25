package org.telegram.messenger.voip;

import java.io.File;
import org.telegram.messenger.voip.VoIPDebugToSend;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPDebugToSend$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ VoIPDebugToSend f$0;
    public final /* synthetic */ VoIPDebugToSend.Data f$1;
    public final /* synthetic */ File f$2;
    public final /* synthetic */ TL_phone.saveCallDebug f$3;

    public /* synthetic */ VoIPDebugToSend$$ExternalSyntheticLambda1(VoIPDebugToSend r1, VoIPDebugToSend.Data r2, File r3, TL_phone.saveCallDebug r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPDebugToSend.$r8$lambda$IqRemkD8_jxY1i_0eXr8IJOo8cQ(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
