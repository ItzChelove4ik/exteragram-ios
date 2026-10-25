package org.telegram.messenger.voip;

import org.telegram.messenger.voip.NativeInstance;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda79 implements NativeInstance.RequestBroadcastPartCallback {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda79(VoIPService r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.voip.NativeInstance.RequestBroadcastPartCallback
    public final void run(long r9, long r11, int r13, int r14) {
        VoIPService.$r8$lambda$hPDB1E04mMX0qn86iuYDNMPDhe0(this.f$0, this.f$1, r9, r11, r13, r14);
    }
}
