package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public abstract class zzue {
    private final Class zza;

    public /* synthetic */ zzue(Class r1, byte[] r2) {
        this.zza = r1;
    }

    public static zzue zzc(zzud r1, Class r2) {
        return new zzuc(r2, r1);
    }

    public abstract zzsp zza(zzse r1, zzsy r2);

    public final Class zzb() {
        return this.zza;
    }
}
