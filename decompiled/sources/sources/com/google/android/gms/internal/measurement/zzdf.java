package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzdf extends zzeo {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Object zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ zzez zze;

    public zzdf(zzez r1, String r2, String r3, Object r4, boolean r5) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        Objects.requireNonNull(r1);
        this.zze = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        zzcp r1 = (zzcp) Preconditions.checkNotNull(this.zze.zzS());
        Object r0 = this.zzc;
        r1.setUserProperty(this.zza, this.zzb, ObjectWrapper.wrap(r0), this.zzd, this.zzi);
    }
}
