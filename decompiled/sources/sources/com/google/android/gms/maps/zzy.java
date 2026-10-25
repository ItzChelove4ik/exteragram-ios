package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzo;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzy extends zzo {
    final /* synthetic */ GoogleMap.OnCameraIdleListener zza;

    public zzy(GoogleMap r1, GoogleMap.OnCameraIdleListener r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.maps.internal.zzp
    public final void zzb() {
        this.zza.onCameraIdle();
    }
}
