package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzoj extends zzaz {
    final /* synthetic */ zzok zza;

    public zzoj(zzok r1, zzjg r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzaz
    public final void zza() {
        zzok r2 = this.zza;
        r2.zzf();
        r2.zzu.zzaW().zzk().zza("Starting upload from DelayedRunnable");
        r2.zzg.zzM();
    }
}
