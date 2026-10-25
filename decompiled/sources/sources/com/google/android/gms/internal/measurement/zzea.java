package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzea extends zzeo {
    final /* synthetic */ String zza;
    final /* synthetic */ Object zzb;
    final /* synthetic */ zzez zzc;

    public zzea(zzez r1, boolean r2, int r3, String r4, Object r5, Object r6, Object r7) {
        this.zza = r4;
        this.zzb = r5;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1, false);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        zzcp r1 = (zzcp) Preconditions.checkNotNull(this.zzc.zzS());
        Object r0 = this.zzb;
        r1.logHealthData(5, this.zza, ObjectWrapper.wrap(r0), ObjectWrapper.wrap(null), ObjectWrapper.wrap(null));
    }
}
