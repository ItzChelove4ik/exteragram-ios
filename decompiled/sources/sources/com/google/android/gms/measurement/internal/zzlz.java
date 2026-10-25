package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzlz implements Runnable {
    final /* synthetic */ zzlu zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ zzmb zzc;

    public zzlz(zzmb r1, zzlu r2, long r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzmb r0 = this.zzc;
        r0.zzu(this.zza, false, this.zzb);
        r0.zza = null;
        r0.zzu.zzt().zzG(null);
    }
}
