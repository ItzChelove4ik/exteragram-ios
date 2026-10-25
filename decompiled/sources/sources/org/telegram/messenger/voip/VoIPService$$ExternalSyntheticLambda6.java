package org.telegram.messenger.voip;

import org.telegram.messenger.voip.Instance;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda6 implements Instance.OnSignalingDataListener {
    public final /* synthetic */ VoIPService f$0;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda6(VoIPService r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.voip.Instance.OnSignalingDataListener
    public final void onSignalingData(byte[] r1) {
        this.f$0.onSignalingData(r1);
    }
}
