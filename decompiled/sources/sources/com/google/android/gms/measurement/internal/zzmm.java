package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmm extends zzaz {
    final /* synthetic */ zznl zza;

    public zzmm(zznl r1, zzjg r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzaz
    public final void zza() {
        zznl r2 = this.zza;
        r2.zzg();
        if (r2.zzh() == true) goto L5;
        return;
    L5:
        r2.zzu.zzaW().zzk().zza("Inactivity, disconnecting from the service");
        r2.zzM();
    }
}
