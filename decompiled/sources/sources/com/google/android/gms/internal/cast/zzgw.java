package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public abstract class zzgw {
    private static final ThreadLocal zza = null;

    static {
        zza = new zzgq();
    }

    public zzgw() {
    }

    public static zzgw zzb() {
        return (zzgw) zza.get();
    }

    public abstract void zza(zzgt r1);
}
