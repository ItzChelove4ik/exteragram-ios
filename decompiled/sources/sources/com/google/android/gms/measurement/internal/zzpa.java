package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzpa implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Bundle zzc;
    final /* synthetic */ zzpb zzd;

    public zzpa(zzpb r1, String r2, String r3, Bundle r4) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzpg r0 = this.zzd.zza;
        zzpp r1 = r0.zzt();
        long r6 = r0.zzba().currentTimeMillis();
        if (r0.zzd().zzp(null, zzfy.zzbe) == false) goto L6;
        long r2 = r0.zzba().elapsedRealtime();
    L5:
        long r8 = r2;
        Bundle r4 = this.zzc;
        String r3 = this.zzb;
        String r22 = this.zza;
        r0.zzD((zzbh) Preconditions.checkNotNull(r1.zzaf(r22, r3, r4, "auto", r6, r8, false, true)), r22);
        return;
    L6:
        r2 = 0;
        goto L5
    }
}
