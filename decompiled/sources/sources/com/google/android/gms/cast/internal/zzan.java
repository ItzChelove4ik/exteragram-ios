package com.google.android.gms.cast.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzan implements zzat {
    final /* synthetic */ zzat zza;
    final /* synthetic */ zzar zzb;

    public zzan(zzar r1, zzat r2) {
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
    public final void zzb(String r5, long r6, int r8, Object r9, long r10, long r12) {
        zzat r4 = this.zza;
        if (r4 != null) goto L5;
        return;
    L5:
        if (r8 != 2001) goto L7;
        zzar r82 = this.zzb;
        r82.zza.w("Possibility of local queue out of sync with receiver queue. Refetching sequence number. Current Local Sequence Number = %d", new Object[]{Integer.valueOf(r82.zzQ())});
        r82.zzP().zzm();
        r8 = 2001;
    L7:
        r4.zzb(r5, r6, r8, r9, r10, r12);
    }
}
