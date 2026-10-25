package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzon {
    private final zzni zza;
    private final zzng zzb;
    private final zznm zzc;

    public zzon(zzni r1, zzng r2, zznm r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final zzni zza() {
        return this.zza;
    }

    public final zzng zzb() {
        return this.zzb;
    }

    public final zznm zzc() {
        return this.zzc;
    }

    public final Object zzd(zzakg r2, Continuation r3) {
        return new zzmc(new zzok(this, r2, null));
    }

    public final Object zze(zzaer r7, zzaer r8, String r9, Continuation r10) {
        return new zzmc(new zzol(r9, r8, r7, this, null));
    }

    public final Object zzf(Continuation r2) {
        return new zzmc(new zzoi(this, null));
    }

    public final Object zzg(Continuation r2) {
        return new zzmc(new zzog(this, null));
    }

    public final Object zzh(Continuation r2) {
        return new zzmc(new zzof(this, null));
    }
}
