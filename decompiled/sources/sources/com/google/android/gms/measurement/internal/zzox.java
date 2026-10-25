package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzox implements zzgw {
    final /* synthetic */ String zza;
    final /* synthetic */ zzpj zzb;
    final /* synthetic */ zzpg zzc;

    public zzox(zzpg r1, String r2, zzpj r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public final void zza(String r1, int r2, Throwable r3, byte[] r4, Map r5) {
        zzpj r52 = this.zzb;
        this.zzc.zzQ(this.zza, r2, r3, r4, r52);
    }
}
