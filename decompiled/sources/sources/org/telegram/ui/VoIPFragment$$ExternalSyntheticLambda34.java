package org.telegram.ui;

import android.view.View;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.ui.Components.voip.VoIpSwitchLayout;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPFragment$$ExternalSyntheticLambda34 implements VoIpSwitchLayout.VoIpButtonView.OnBtnClickedListener {
    public final /* synthetic */ VoIPFragment f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ VoIpSwitchLayout f$2;
    public final /* synthetic */ VoIPService f$3;

    public /* synthetic */ VoIPFragment$$ExternalSyntheticLambda34(VoIPFragment r1, int r2, VoIpSwitchLayout r3, VoIPService r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.voip.VoIpSwitchLayout.VoIpButtonView.OnBtnClickedListener
    public final void onClicked(View r4) {
        VoIPFragment.$r8$lambda$IRtdx08_kW4Q3okRYwraol8kvc0(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
