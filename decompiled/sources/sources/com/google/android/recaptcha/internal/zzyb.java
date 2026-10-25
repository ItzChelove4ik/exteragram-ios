package com.google.android.recaptcha.internal;

import java.security.spec.ECPoint;

/* loaded from: classes5.dex */
public final class zzyb extends zzaax {
    private final zzxx zza;
    private final ECPoint zzb;
    private final zzady zzc;
    private final Integer zzd;

    public /* synthetic */ zzyb(zzxx r1, ECPoint r2, zzady r3, Integer r4, byte[] r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public static zzya zzc() {
        return new zzya(null);
    }

    @Override // com.google.android.recaptcha.internal.zzse
    public final /* synthetic */ zzsl zza() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzse
    public final Integer zzb() {
        return this.zzd;
    }

    public final ECPoint zzd() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzaax
    public final zzady zze() {
        return this.zzc;
    }

    public final zzxx zzf() {
        return this.zza;
    }
}
