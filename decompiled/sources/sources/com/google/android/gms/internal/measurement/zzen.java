package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzen extends zzeo {
    final /* synthetic */ Long zza;
    final /* synthetic */ Long zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ Bundle zze;
    final /* synthetic */ boolean zzf;
    final /* synthetic */ boolean zzg;
    final /* synthetic */ zzez zzh;

    public zzen(zzez r1, Long r2, Long r3, String r4, String r5, Bundle r6, boolean r7, boolean r8) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
        Objects.requireNonNull(r1);
        this.zzh = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        Long r0 = this.zza;
        if (r0 != null) goto L6;
        long r02 = this.zzi;
    L5:
        long r8 = r02;
        if (this.zzb != null) goto L11;
        long r03 = this.zzj;
    L10:
        long r10 = r03;
        ((zzcp) Preconditions.checkNotNull(this.zzh.zzS())).logEventWithElapsedTime(this.zzc, this.zzd, this.zze, this.zzf, this.zzg, r8, r10);
        return;
    L11:
        r03 = 0;
        goto L10
    L6:
        r02 = r0.longValue();
        goto L5
    }
}
