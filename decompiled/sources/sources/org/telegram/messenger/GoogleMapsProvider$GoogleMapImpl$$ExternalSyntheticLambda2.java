package org.telegram.messenger;

import com.google.android.gms.maps.GoogleMap;

/* loaded from: classes5.dex */
public final /* synthetic */ class GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda2 implements GoogleMap.OnCameraIdleListener {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda2(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnCameraIdleListener
    public final void onCameraIdle() {
        this.f$0.run();
    }
}
