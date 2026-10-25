package org.telegram.ui;

import android.view.View;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.ui.Components.voip.VoIpSwitchLayout;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPFragment$$ExternalSyntheticLambda42 implements VoIpSwitchLayout.VoIpButtonView.OnBtnClickedListener {
    public final /* synthetic */ VoIPFragment f$0;
    public final /* synthetic */ VoIPService f$1;
    public final /* synthetic */ VoIpSwitchLayout f$2;

    public /* synthetic */ VoIPFragment$$ExternalSyntheticLambda42(VoIPFragment r1, VoIPService r2, VoIpSwitchLayout r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.voip.VoIpSwitchLayout.VoIpButtonView.OnBtnClickedListener
    public final void onClicked(View r3) {
        VoIPFragment.$r8$lambda$78f7mgXYiRGqVH4NbzpYJrPs4CI(this.f$0, this.f$1, this.f$2, r3);
    }
}
