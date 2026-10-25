package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzih implements Runnable {
    final /* synthetic */ zzah zza;
    final /* synthetic */ zzr zzb;
    final /* synthetic */ zzjd zzc;

    public zzih(zzjd r1, zzah r2, zzr r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjd r0 = this.zzc;
        r0.zzL().zzaa();
        zzah r1 = this.zza;
        Object r2 = r1.zzc.zza();
        zzr r3 = this.zzb;
        if (r2 != null) goto L6;
        r0.zzL().zzan(r1, r3);
        return;
    L6:
        r0.zzL().zzal(r1, r3);
    }
}
