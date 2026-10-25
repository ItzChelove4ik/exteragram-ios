package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzay implements Runnable {
    final /* synthetic */ zzjg zza;
    final /* synthetic */ zzaz zzb;

    public zzay(zzaz r1, zzjg r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjg r0 = this.zza;
        r0.zzaV();
        if (zzae.zza() == false) goto L6;
        r0.zzaX().zzj(this);
        return;
    L6:
        zzaz r3 = this.zzb;
        boolean r02 = r3.zzc();
        r3.zze(0);
        if (r02 == false) goto L10;
        r3.zza();
        return;
    }
}
