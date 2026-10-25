package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes6.dex */
public final /* synthetic */ class LocationActivity$$ExternalSyntheticLambda29 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ LocationActivity f$0;
    public final /* synthetic */ TLRPC.TL_messageMediaVenue f$1;

    public /* synthetic */ LocationActivity$$ExternalSyntheticLambda29(LocationActivity r1, TLRPC.TL_messageMediaVenue r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r2, int r3, int r4) {
        LocationActivity.m15716$r8$lambda$mPNjgCqIHoA_szQtd2XiCCCYEw(this.f$0, this.f$1, r2, r3, r4);
    }
}
