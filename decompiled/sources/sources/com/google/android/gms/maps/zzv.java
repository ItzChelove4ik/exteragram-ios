package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzu;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzv extends zzu {
    final /* synthetic */ GoogleMap.OnCameraMoveStartedListener zza;

    public zzv(GoogleMap r1, GoogleMap.OnCameraMoveStartedListener r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.maps.internal.zzv
    public final void zzb(int r1) {
        this.zza.onCameraMoveStarted(r1);
    }
}
