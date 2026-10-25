package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzow implements zzgw {
    final /* synthetic */ String zza;
    final /* synthetic */ List zzb;
    final /* synthetic */ zzpg zzc;

    public zzow(zzpg r1, String r2, List r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public final void zza(String r9, int r10, Throwable r11, byte[] r12, Map r13) {
        this.zzc.zzW(true, r10, r11, r12, this.zza, this.zzb, r13);
    }
}
