package org.telegram.messenger;

import com.google.android.gms.maps.GoogleMap;

/* loaded from: classes5.dex */
public final /* synthetic */ class GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda1 implements GoogleMap.OnCameraMoveListener {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda1(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnCameraMoveListener
    public final void onCameraMove() {
        this.f$0.run();
    }
}
