package com.google.android.recaptcha.internal;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public final class zzzz extends zzaax {
    private final zzzv zza;
    private final BigInteger zzb;
    private final zzady zzc;
    private final Integer zzd;

    public /* synthetic */ zzzz(zzzv r1, BigInteger r2, zzady r3, Integer r4, byte[] r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public static zzzy zzc() {
        return new zzzy(null);
    }

    @Override // com.google.android.recaptcha.internal.zzse
    public final /* synthetic */ zzsl zza() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzse
    public final Integer zzb() {
        return this.zzd;
    }

    public final BigInteger zzd() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzaax
    public final zzady zze() {
        return this.zzc;
    }

    public final zzzv zzf() {
        return this.zza;
    }
}
