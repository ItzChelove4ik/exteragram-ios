package com.google.android.recaptcha.internal;

import java.util.Arrays;

/* loaded from: classes5.dex */
final class zztm {
    final zztn zza;
    final long[] zzb;

    public zztm(zztm r3) {
        this.zza = new zztn(r3.zza);
        this.zzb = Arrays.copyOf(r3.zzb, 10);
    }

    public zztm(zztn r1, long[] r2) {
        this.zza = r1;
        this.zzb = r2;
    }
}
