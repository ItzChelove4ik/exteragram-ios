package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzie implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzjd zzb;

    public zzie(zzjd r1, zzr r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjd r0 = this.zzb;
        r0.zzL().zzaa();
        r0.zzL().zzai(this.zza);
    }
}
