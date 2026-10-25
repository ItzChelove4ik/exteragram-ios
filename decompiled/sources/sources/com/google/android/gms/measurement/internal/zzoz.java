package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
final class zzoz implements Callable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzpg zzb;

    public zzoz(zzpg r1, zzr r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        zzr r0 = this.zza;
        String r1 = (String) Preconditions.checkNotNull(r0.zza);
        zzpg r4 = this.zzb;
        zzjl r12 = r4.zzB(r1);
        zzjk r2 = zzjk.zzb;
        if (r12.zzo(r2) == true) goto L5;
    L9:
        r4.zzaW().zzk().zza("Analytics storage consent denied. Returning null app instance id");
        return null;
    L5:
        if (zzjl.zzf(r0.zzs, 100).zzo(r2) == false) goto L9;
        return r4.zzap(r0).zzd();
    }
}
