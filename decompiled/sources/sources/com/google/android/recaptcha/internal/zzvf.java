package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public abstract class zzvf {
    private final String zza;

    public /* synthetic */ zzvf(String r1, byte[] r2) {
        this.zza = r1;
    }

    public static zzvf zzb(zzve r1, String r2) {
        return new zzvd(r2, r1);
    }

    public final String zza() {
        return this.zza;
    }
}
