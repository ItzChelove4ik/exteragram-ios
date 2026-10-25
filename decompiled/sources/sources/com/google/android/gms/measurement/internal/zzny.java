package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzny {
    final /* synthetic */ zzoc zza;
    private zznx zzb;

    public zzny(zzoc r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    public final void zza() {
        zzoc r0 = this.zza;
        r0.zzg();
        zznx r2 = this.zzb;
        if (r2 == null) goto L5;
        r0.zzm().removeCallbacks(r2);
    L5:
        r0.zzu.zzd().zzn.zzb(false);
        r0.zzh(false);
    }

    public final void zzb(long r8) {
        zzoc r6 = this.zza;
        this.zzb = new zznx(this, r6.zzu.zzba().currentTimeMillis(), r8);
        r6.zzm().postDelayed(this.zzb, 2000);
    }
}
