package org.telegram.messenger.voip;

import org.telegram.messenger.voip.NativeInstance;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda77 implements NativeInstance.VideoSourcesCallback {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda77(VoIPService r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.voip.NativeInstance.VideoSourcesCallback
    public final void run(long r2, int[] r4) {
        VoIPService.$r8$lambda$mi8HrxrwBjm3axO5vTSUpYeEd1w(this.f$0, this.f$1, r2, r4);
    }
}
