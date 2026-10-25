package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zznz extends zzaz {
    final /* synthetic */ zzoa zza;

    public zznz(zzoa r1, zzjg r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzaz
    public final void zza() {
        zzoa r5 = this.zza;
        zzoc r0 = r5.zzc;
        r0.zzg();
        zzic r1 = r0.zzu;
        r5.zzd(false, false, r1.zzba().elapsedRealtime());
        r0.zzu.zzw().zzc(r1.zzba().elapsedRealtime());
    }
}
