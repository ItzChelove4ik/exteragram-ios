package org.telegram.ui;

import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.LocationActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LocationActivity$MapOverlayView$$ExternalSyntheticLambda1 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ LocationActivity.MapOverlayView f$0;
    public final /* synthetic */ LocationActivity.VenueLocation f$1;

    public /* synthetic */ LocationActivity$MapOverlayView$$ExternalSyntheticLambda1(LocationActivity.MapOverlayView r1, LocationActivity.VenueLocation r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r2, int r3, int r4) {
        LocationActivity.MapOverlayView.m15767$r8$lambda$LknAM6LiX0VM0_90X2keWbe3e4(this.f$0, this.f$1, r2, r3, r4);
    }
}
