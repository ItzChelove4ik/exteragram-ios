package org.telegram.ui;

import org.telegram.messenger.IMapsProvider;

/* loaded from: classes6.dex */
public final /* synthetic */ class LocationActivity$$ExternalSyntheticLambda46 implements IMapsProvider.OnMarkerClickListener {
    public final /* synthetic */ LocationActivity f$0;
    public final /* synthetic */ float f$1;

    public /* synthetic */ LocationActivity$$ExternalSyntheticLambda46(LocationActivity r1, float r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.IMapsProvider.OnMarkerClickListener
    public final boolean onClick(IMapsProvider.IMarker r2) {
        return LocationActivity.m15720$r8$lambda$xGOyzXxltTVT84oEr12Hxo1jG0(this.f$0, this.f$1, r2);
    }
}
