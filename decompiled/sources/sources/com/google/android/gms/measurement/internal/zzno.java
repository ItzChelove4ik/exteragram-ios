package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzno implements Runnable {
    final /* synthetic */ zzpg zza;
    final /* synthetic */ Runnable zzb;

    public zzno(zznt r1, zzpg r2, Runnable r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzpg r0 = this.zza;
        r0.zzaa();
        r0.zzZ(this.zzb);
        r0.zzM();
    }
}
