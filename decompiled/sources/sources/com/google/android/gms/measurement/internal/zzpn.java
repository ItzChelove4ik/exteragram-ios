package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
final class zzpn {
    final String zza;
    final String zzb;
    final String zzc;
    final long zzd;
    final Object zze;

    public zzpn(String r1, String r2, String r3, long r4, Object r6) {
        Preconditions.checkNotEmpty(r1);
        Preconditions.checkNotEmpty(r3);
        Preconditions.checkNotNull(r6);
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r6;
    }
}
