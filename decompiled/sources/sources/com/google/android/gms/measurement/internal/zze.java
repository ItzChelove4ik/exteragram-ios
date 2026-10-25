package com.google.android.gms.measurement.internal;

import android.text.TextUtils;

/* loaded from: classes5.dex */
final class zze {
    private final zzji zza;

    public zze(zzji r1) {
        this.zza = r1;
    }

    public static zze zzc(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L5;
    L8:
        zzji r22 = zzji.zza;
    L10:
        return new zze(r22);
    L5:
        if (r2.length() > 1) goto L8;
        r22 = zzjl.zzj(r2.charAt(0));
        goto L10
    }

    public final zzji zza() {
        return this.zza;
    }

    public final String zzb() {
        return String.valueOf(zzjl.zzm(this.zza));
    }
}
