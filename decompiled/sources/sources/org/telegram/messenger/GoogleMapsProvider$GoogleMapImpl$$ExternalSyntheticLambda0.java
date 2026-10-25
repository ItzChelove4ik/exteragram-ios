package org.telegram.messenger;

import android.location.Location;
import androidx.core.util.Consumer;
import com.google.android.gms.maps.GoogleMap;

/* loaded from: classes5.dex */
public final /* synthetic */ class GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda0 implements GoogleMap.OnMyLocationChangeListener {
    public final /* synthetic */ Consumer f$0;

    public /* synthetic */ GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda0(Consumer r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener
    public final void onMyLocationChange(Location r1) {
        this.f$0.accept(r1);
    }
}
