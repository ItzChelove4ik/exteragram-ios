package org.telegram.ui.Stories.recorder;

import android.location.Location;
import android.location.LocationListener;
import org.telegram.messenger.Utilities;

/* loaded from: classes3.dex */
public final /* synthetic */ class Weather$$ExternalSyntheticLambda8 implements LocationListener {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ Utilities.Callback[] f$1;

    public /* synthetic */ Weather$$ExternalSyntheticLambda8(Runnable r1, Utilities.Callback[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.location.LocationListener
    public final void onLocationChanged(Location r2) {
        Weather.m20593$r8$lambda$My5p7ErkTbKx6HW0WXr0AxDwNU(this.f$0, this.f$1, r2);
    }
}
