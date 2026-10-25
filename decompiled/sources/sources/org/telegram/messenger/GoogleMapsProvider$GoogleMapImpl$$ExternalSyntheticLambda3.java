package org.telegram.messenger;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import org.telegram.messenger.GoogleMapsProvider;
import org.telegram.messenger.IMapsProvider;

/* loaded from: classes5.dex */
public final /* synthetic */ class GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda3 implements GoogleMap.OnMarkerClickListener {
    public final /* synthetic */ GoogleMapsProvider.GoogleMapImpl f$0;
    public final /* synthetic */ IMapsProvider.OnMarkerClickListener f$1;

    public /* synthetic */ GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda3(GoogleMapsProvider.GoogleMapImpl r1, IMapsProvider.OnMarkerClickListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
    public final boolean onMarkerClick(Marker r2) {
        return GoogleMapsProvider.GoogleMapImpl.$r8$lambda$zkoWPOSjj37cOg4icYE3UQPcyKY(this.f$0, this.f$1, r2);
    }
}
