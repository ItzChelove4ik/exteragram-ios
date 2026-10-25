package org.telegram.messenger.voip;

import org.telegram.messenger.voip.NativeInstance;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda75 implements NativeInstance.PayloadCallback {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean[] f$2;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda75(VoIPService r1, int r2, boolean[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.voip.NativeInstance.PayloadCallback
    public final void run(int r3, String r4) {
        VoIPService.$r8$lambda$ydnD09NTI5JDQa2Iy9Zrlnme1Ig(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
