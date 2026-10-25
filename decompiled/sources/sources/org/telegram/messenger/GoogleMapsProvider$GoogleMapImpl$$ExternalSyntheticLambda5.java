package org.telegram.messenger;

import com.google.android.gms.maps.GoogleMap;
import org.telegram.messenger.GoogleMapsProvider;
import org.telegram.messenger.IMapsProvider;

/* loaded from: classes5.dex */
public final /* synthetic */ class GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda5 implements GoogleMap.OnCameraMoveStartedListener {
    public final /* synthetic */ IMapsProvider.OnCameraMoveStartedListener f$0;

    public /* synthetic */ GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda5(IMapsProvider.OnCameraMoveStartedListener r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener
    public final void onCameraMoveStarted(int r1) {
        GoogleMapsProvider.GoogleMapImpl.m4117$r8$lambda$r9kkoqpW97OxjoEF5_g6CEGB9s(this.f$0, r1);
    }
}
