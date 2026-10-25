package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public abstract class zzub {
    private final String zza;

    public /* synthetic */ zzub(String r1, byte[] r2) {
        this.zza = r1;
    }

    public static zzub zzc(zzua r1, String r2) {
        return new zztz(r2, r1);
    }

    public abstract zzse zza(zzsp r1, zzsy r2);

    public final String zzb() {
        return this.zza;
    }
}
