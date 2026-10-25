package org.telegram.ui;

import org.telegram.messenger.LocationController;
import org.telegram.ui.Components.SharingLocationsAlert;

/* loaded from: classes3.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda31 implements SharingLocationsAlert.SharingLocationsAlertDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ int[] f$1;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda31(LaunchActivity r1, int[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.SharingLocationsAlert.SharingLocationsAlertDelegate
    public final void didSelectLocation(LocationController.SharingLocationInfo r2) {
        LaunchActivity.m15588$r8$lambda$1QkEMoqxFZeMeHScQD0ImaUulY(this.f$0, this.f$1, r2);
    }
}
