package com.google.android.recaptcha.internal;

import java.util.Arrays;

/* loaded from: classes5.dex */
class zztk {
    final long[] zza;
    final long[] zzb;
    final long[] zzc;

    public zztk(zztk r3) {
        this.zza = Arrays.copyOf(r3.zza, 10);
        this.zzb = Arrays.copyOf(r3.zzb, 10);
        this.zzc = Arrays.copyOf(r3.zzc, 10);
    }

    public zztk(long[] r1, long[] r2, long[] r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public void zza(long[] r2, long[] r3) {
        System.arraycopy(r3, 0, r2, 0, 10);
    }

    public final void zzb(zztk r3, int r4) {
        zztj.zza(this.zza, r3.zza, r4);
        zztj.zza(this.zzb, r3.zzb, r4);
        zztj.zza(this.zzc, r3.zzc, r4);
    }
}
