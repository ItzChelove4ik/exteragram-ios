package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzgx implements Runnable {
    private final zzgw zza;
    private final int zzb;
    private final Throwable zzc;
    private final byte[] zzd;
    private final String zze;
    private final Map zzf;

    public /* synthetic */ zzgx(String r1, zzgw r2, int r3, Throwable r4, byte[] r5, Map r6, byte[] r7) {
        Preconditions.checkNotNull(r2);
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r1;
        this.zzf = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zze, this.zzb, this.zzc, this.zzd, this.zzf);
    }
}
