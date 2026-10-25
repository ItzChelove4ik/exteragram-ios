package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzs;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzw extends zzs {
    final /* synthetic */ GoogleMap.OnCameraMoveListener zza;

    public zzw(GoogleMap r1, GoogleMap.OnCameraMoveListener r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.maps.internal.zzt
    public final void zzb() {
        this.zza.onCameraMove();
    }
}
