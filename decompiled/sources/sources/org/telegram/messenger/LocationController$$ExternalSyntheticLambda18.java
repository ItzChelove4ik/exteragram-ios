package org.telegram.messenger;

import android.location.Location;
import java.util.Locale;
import org.telegram.messenger.LocationController;

/* loaded from: classes5.dex */
public final /* synthetic */ class LocationController$$ExternalSyntheticLambda18 implements Runnable {
    public final /* synthetic */ Locale f$0;
    public final /* synthetic */ Location f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Locale f$3;
    public final /* synthetic */ LocationController.LocationFetchCallback f$4;

    public /* synthetic */ LocationController$$ExternalSyntheticLambda18(Locale r1, Location r2, int r3, Locale r4, LocationController.LocationFetchCallback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LocationController.$r8$lambda$tlUU8lHFc_rWHayxFAniRRtUDs0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
