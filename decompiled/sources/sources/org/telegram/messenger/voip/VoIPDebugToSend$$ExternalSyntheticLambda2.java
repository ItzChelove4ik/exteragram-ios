package org.telegram.messenger.voip;

import java.io.File;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPDebugToSend$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ VoIPDebugToSend f$0;
    public final /* synthetic */ File f$1;
    public final /* synthetic */ TL_phone.saveCallDebug f$2;

    public /* synthetic */ VoIPDebugToSend$$ExternalSyntheticLambda2(VoIPDebugToSend r1, File r2, TL_phone.saveCallDebug r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPDebugToSend.$r8$lambda$hCdI5FFkTDm2QMcyUgMnOyrfHJI(this.f$0, this.f$1, this.f$2);
    }
}
