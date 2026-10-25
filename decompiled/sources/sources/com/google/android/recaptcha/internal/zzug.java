package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public class zzug implements zzsf {
    final String zza;

    public zzug(String r1, Class r2, int r3, zzahe r4) {
        this.zza = r1;
    }

    public static zzsm zzb(String r1, Class r2, zzahe r3) {
        return new zzuf(r1, r2, r3);
    }

    public static zzsf zzc(String r1, Class r2, int r3, zzahe r4) {
        return new zzug(r1, r2, 5, r4);
    }

    @Override // com.google.android.recaptcha.internal.zzsf
    public final String zza() {
        return this.zza;
    }
}
