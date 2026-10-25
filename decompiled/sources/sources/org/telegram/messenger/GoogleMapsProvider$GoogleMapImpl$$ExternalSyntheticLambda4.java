package org.telegram.messenger;

import com.google.android.gms.maps.GoogleMap;

/* loaded from: classes5.dex */
public final /* synthetic */ class GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda4 implements GoogleMap.OnMapLoadedCallback {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda4(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback
    public final void onMapLoaded() {
        this.f$0.run();
    }
}
