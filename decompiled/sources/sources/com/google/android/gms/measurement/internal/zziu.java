package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zziu implements Runnable {
    final /* synthetic */ zzpl zza;
    final /* synthetic */ zzr zzb;
    final /* synthetic */ zzjd zzc;

    public zziu(zzjd r1, zzpl r2, zzr r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjd r0 = this.zzc;
        r0.zzL().zzaa();
        zzpl r1 = this.zza;
        Object r2 = r1.zza();
        zzr r3 = this.zzb;
        if (r2 != null) goto L6;
        String r12 = r1.zzb;
        r0.zzL().zzae(r12, r3);
        return;
    L6:
        r0.zzL().zzad(r1, r3);
    }
}
