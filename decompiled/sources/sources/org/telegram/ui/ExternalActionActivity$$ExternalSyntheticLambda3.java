package org.telegram.ui;

import android.content.Intent;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes6.dex */
public final /* synthetic */ class ExternalActionActivity$$ExternalSyntheticLambda3 implements AlertsCreator.AccountSelectDelegate {
    public final /* synthetic */ ExternalActionActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Intent f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ ExternalActionActivity$$ExternalSyntheticLambda3(ExternalActionActivity r1, int r2, Intent r3, boolean r4, boolean r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.AccountSelectDelegate
    public final void didSelectAccount(int r8) {
        ExternalActionActivity.$r8$lambda$hfUCy1DSFusA_MCYjDmMSMq8B_g(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r8);
    }
}
