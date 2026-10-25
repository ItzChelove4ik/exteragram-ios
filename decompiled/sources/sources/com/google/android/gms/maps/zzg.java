package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzba;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzg extends zzba {
    final /* synthetic */ GoogleMap.OnMyLocationChangeListener zza;

    public zzg(GoogleMap r1, GoogleMap.OnMyLocationChangeListener r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.maps.internal.zzbb
    public final void zzb(IObjectWrapper r1) {
        Location r12 = (Location) ObjectWrapper.unwrap(r1);
        this.zza.onMyLocationChange(r12);
    }
}
