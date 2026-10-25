package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public abstract class zzvp {
    private final Class zza;
    private final Class zzb;

    public /* synthetic */ zzvp(Class r1, Class r2, byte[] r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    public static zzvp zzc(zzvo r1, Class r2, Class r3) {
        return new zzvn(r2, r3, r1);
    }

    public final Class zza() {
        return this.zza;
    }

    public final Class zzb() {
        return this.zzb;
    }
}
