package com.google.android.gms.cast.framework.media.internal;

import android.graphics.Bitmap;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzj implements zza {
    final /* synthetic */ zzl zza;
    final /* synthetic */ zzm zzb;

    public zzj(zzm r1, zzl r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap r2) {
        zzl r0 = this.zza;
        r0.zzb = r2;
        zzm r1 = this.zzb;
        r1.zze(r0);
        r1.zzd();
    }
}
