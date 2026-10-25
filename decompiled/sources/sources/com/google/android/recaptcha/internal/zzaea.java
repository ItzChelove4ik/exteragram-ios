package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzaea {
    private final zzady zza;

    private zzaea(zzady r1) {
        this.zza = r1;
    }

    public static zzaea zza(byte[] r0, zzsy r1) {
        return new zzaea(zzady.zza(r0));
    }

    public final byte[] zzb(zzsy r1) {
        return this.zza.zzc();
    }

    public final int zzc() {
        return this.zza.zzd();
    }
}
