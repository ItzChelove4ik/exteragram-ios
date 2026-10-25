package com.exteragram.messenger.pillstack.ui.pills.weather;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LocationActivity;

/* loaded from: classes4.dex */
public final /* synthetic */ class WeatherSettingsActivity$$ExternalSyntheticLambda1 implements LocationActivity.LocationActivityDelegate {
    public final /* synthetic */ WeatherSettingsActivity f$0;
    public final /* synthetic */ LocationActivity f$1;

    public /* synthetic */ WeatherSettingsActivity$$ExternalSyntheticLambda1(WeatherSettingsActivity r1, LocationActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.LocationActivity.LocationActivityDelegate
    public final void didSelectLocation(TLRPC.MessageMedia r9, int r10, boolean r11, int r12, long r13) {
        WeatherSettingsActivity.$r8$lambda$r1F3JTTSwGevkR3rdkI74dTACBQ(this.f$0, this.f$1, r9, r10, r11, r12, r13);
    }
}
