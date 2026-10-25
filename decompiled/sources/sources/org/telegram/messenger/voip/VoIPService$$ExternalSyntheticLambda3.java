package org.telegram.messenger.voip;

import org.telegram.messenger.voip.NativeInstance;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda3 implements NativeInstance.AudioLevelsCallback {
    public final /* synthetic */ VoIPService f$0;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda3(VoIPService r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.voip.NativeInstance.AudioLevelsCallback
    public final void run(int[] r1, float[] r2, boolean[] r3) {
        VoIPService.$r8$lambda$PMdSfYxBjtSkVPxjvzGjxxMbHqw(this.f$0, r1, r2, r3);
    }
}
