package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzaj;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zza extends zzau {
    final /* synthetic */ GoogleMap.OnMarkerClickListener zza;

    public zza(GoogleMap r1, GoogleMap.OnMarkerClickListener r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.maps.internal.zzav
    public final boolean zzb(zzaj r2) {
        Marker r0 = new Marker(r2);
        return this.zza.onMarkerClick(r0);
    }
}
