package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes6.dex */
public final /* synthetic */ class LocationActivity$$ExternalSyntheticLambda36 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ LocationActivity f$0;
    public final /* synthetic */ TLRPC.TL_messageMediaGeo f$1;

    public /* synthetic */ LocationActivity$$ExternalSyntheticLambda36(LocationActivity r1, TLRPC.TL_messageMediaGeo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r2, int r3, int r4) {
        LocationActivity.$r8$lambda$miQj1RxbPHyLBCweepAdis_A_dE(this.f$0, this.f$1, r2, r3, r4);
    }
}
