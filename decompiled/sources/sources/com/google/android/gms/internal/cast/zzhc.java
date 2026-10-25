package com.google.android.gms.internal.cast;

import java.io.Serializable;

/* loaded from: classes4.dex */
public abstract class zzhc implements Serializable {
    public zzhc() {
    }

    public static zzhc zzb() {
        return zzha.zza;
    }

    public static zzhc zzc(Object r1) {
        if (r1 != null) goto L6;
        return zzha.zza;
    L6:
        return new zzhe(r1);
    }

    public abstract Object zza(Object r1);
}
