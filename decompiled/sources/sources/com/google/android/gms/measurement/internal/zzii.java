package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzii implements Runnable {
    final /* synthetic */ zzah zza;
    final /* synthetic */ zzjd zzb;

    public zzii(zzjd r1, zzah r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjd r0 = this.zzb;
        r0.zzL().zzaa();
        zzah r2 = this.zza;
        if (r2.zzc.zza() != null) goto L6;
        r0.zzL().zzam(r2);
        return;
    L6:
        r0.zzL().zzak(r2);
    }
}
