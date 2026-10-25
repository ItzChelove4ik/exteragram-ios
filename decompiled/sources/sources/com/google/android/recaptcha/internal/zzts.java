package com.google.android.recaptcha.internal;

import java.math.BigInteger;

/* loaded from: classes5.dex */
final class zzts {
    static final zzts zzd = null;
    final BigInteger zza;
    final BigInteger zzb;
    final BigInteger zzc;

    static {
        BigInteger r1 = BigInteger.ONE;
        zzd = new zzts(r1, r1, BigInteger.ZERO);
    }

    public zzts(BigInteger r1, BigInteger r2, BigInteger r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final boolean zza() {
        return this.zzc.equals(BigInteger.ZERO);
    }
}
