package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzas;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzag extends zzas {
    final /* synthetic */ OnMapReadyCallback zza;

    public zzag(zzah r1, OnMapReadyCallback r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.maps.internal.zzat
    public final void zzb(IGoogleMapDelegate r2) {
        GoogleMap r0 = new GoogleMap(r2);
        this.zza.onMapReady(r0);
    }
}
