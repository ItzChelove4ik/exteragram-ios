package org.telegram.messenger.voip;

import org.telegram.messenger.voip.NativeInstance;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda76 implements NativeInstance.AudioLevelsCallback {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda76(VoIPService r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.voip.NativeInstance.AudioLevelsCallback
    public final void run(int[] r2, float[] r3, boolean[] r4) {
        VoIPService.$r8$lambda$x31hxO9DKR8ogXII_qhgh9Mv56U(this.f$0, this.f$1, r2, r3, r4);
    }
}
