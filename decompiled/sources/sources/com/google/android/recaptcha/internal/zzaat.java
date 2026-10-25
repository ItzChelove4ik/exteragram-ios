package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public abstract class zzaat {
    private static final zzsc zza = null;

    static {
        zza = new zzaas();
    }

    public static zzsc zza() {
        if (zztf.zza() == false) goto L5;
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot use non-FIPS-compliant SignatureConfig2026 in FIPS mode");
        return null;
    L5:
        return zza;
    }
}
