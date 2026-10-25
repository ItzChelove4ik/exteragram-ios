package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzdf extends Cast.Listener {
    final /* synthetic */ zzdg zza;

    public zzdf(zzdg r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onVolumeChanged() {
        this.zza.zza();
    }
}
