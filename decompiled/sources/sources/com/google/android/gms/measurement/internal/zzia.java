package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzia implements Runnable {
    final /* synthetic */ zzjs zza;
    final /* synthetic */ zzic zzb;

    public zzia(zzic r1, zzjs r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzic r0 = this.zzb;
        zzjs r1 = this.zza;
        r0.zzK(r1);
        r0.zza(r1.zzd);
    }
}
