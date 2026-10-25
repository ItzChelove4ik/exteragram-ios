package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzir implements Runnable {
    final /* synthetic */ zzbh zza;
    final /* synthetic */ zzr zzb;
    final /* synthetic */ zzjd zzc;

    public zzir(zzjd r1, zzbh r2, zzr r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbh r0 = this.zza;
        zzr r1 = this.zzb;
        zzjd r2 = this.zzc;
        r2.zzb(r2.zzc(r0, r1), r1);
    }
}
