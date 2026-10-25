package org.telegram.messenger;

import androidx.core.util.Consumer;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import org.telegram.messenger.GoogleMapsProvider;

/* loaded from: classes5.dex */
public final /* synthetic */ class GoogleMapsProvider$GoogleMapView$$ExternalSyntheticLambda0 implements OnMapReadyCallback {
    public final /* synthetic */ GoogleMapsProvider.GoogleMapView f$0;
    public final /* synthetic */ Consumer f$1;

    public /* synthetic */ GoogleMapsProvider$GoogleMapView$$ExternalSyntheticLambda0(GoogleMapsProvider.GoogleMapView r1, Consumer r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public final void onMapReady(GoogleMap r2) {
        GoogleMapsProvider.GoogleMapView.m4121$r8$lambda$FMZm8X3foe7lisub9JfmitZn8(this.f$0, this.f$1, r2);
    }
}
