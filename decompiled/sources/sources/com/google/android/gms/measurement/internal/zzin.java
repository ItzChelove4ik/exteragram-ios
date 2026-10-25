package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzin implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzjd zzb;

    public zzin(zzjd r1, zzr r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjd r0 = this.zzb;
        r0.zzL().zzaa();
        zzpg r02 = r0.zzL();
        r02.zzaX().zzg();
        r02.zzu();
        zzr r2 = this.zza;
        Preconditions.checkNotEmpty(r2.zza);
        r02.zzap(r2);
    }
}
