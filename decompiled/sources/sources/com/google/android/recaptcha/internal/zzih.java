package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzih implements zzii {
    public static final zzih zza = null;
    private static final String zzb = null;
    private static final String zzc = null;

    static {
        zza = new zzih();
        zzb = "https://www.gstatic.com/recaptcha/verify_key/orcas/prod/android/verify_key.txt";
        zzc = "https://www.recaptcha.net/recaptcha/api3";
        zzig r0 = zzig.zza;
    }

    private zzih() {
    }

    @Override // com.google.android.recaptcha.internal.zzii
    public final String zza() {
        return zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzii
    public final String zzb() {
        return zzc;
    }
}
