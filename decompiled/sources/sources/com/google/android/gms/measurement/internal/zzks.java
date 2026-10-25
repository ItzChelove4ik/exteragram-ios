package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzks implements Runnable {
    final /* synthetic */ Boolean zza;
    final /* synthetic */ zzlj zzb;

    public zzks(zzlj r1, Boolean r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzai(this.zza, true);
    }
}
