package org.telegram.messenger.voip;

import org.telegram.messenger.voip.Instance;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda4 implements Instance.OnStateUpdatedListener {
    public final /* synthetic */ VoIPService f$0;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda4(VoIPService r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.voip.Instance.OnStateUpdatedListener
    public final void onStateUpdated(int r1, boolean r2) {
        this.f$0.onConnectionStateChanged(r1, r2);
    }
}
