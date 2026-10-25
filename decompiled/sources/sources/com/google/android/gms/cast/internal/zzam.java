package com.google.android.gms.cast.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzam implements zzat {
    final /* synthetic */ zzat zza;
    final /* synthetic */ zzar zzb;

    public zzam(zzar r1, zzat r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zza(String r1, long r2, long r4, long r6) {
        zzat r0 = this.zza;
        if (r0 == null) goto L6;
        r0.zza(r1, r2, r4, r6);
        return;
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zzb(String r3, long r4, int r6, Object r7, long r8, long r10) {
        this.zzb.zzO(null);
        zzat r2 = this.zza;
        if (r2 == null) goto L6;
        r2.zzb(r3, r4, r6, r7, r8, r10);
        return;
    }
}
