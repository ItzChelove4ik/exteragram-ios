package org.telegram.messenger.voip;

import org.telegram.messenger.voip.Instance;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda5 implements Instance.OnSignalBarsUpdatedListener {
    public final /* synthetic */ VoIPService f$0;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda5(VoIPService r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.voip.Instance.OnSignalBarsUpdatedListener
    public final void onSignalBarsUpdated(int r1) {
        this.f$0.onSignalBarCountChanged(r1);
    }
}
