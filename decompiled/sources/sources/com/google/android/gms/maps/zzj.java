package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzao;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzj extends zzao {
    final /* synthetic */ GoogleMap.OnMapLoadedCallback zza;

    public zzj(GoogleMap r1, GoogleMap.OnMapLoadedCallback r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.maps.internal.zzap
    public final void zzb() {
        this.zza.onMapLoaded();
    }
}
