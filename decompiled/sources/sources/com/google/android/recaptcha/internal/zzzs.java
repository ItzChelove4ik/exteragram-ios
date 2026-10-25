package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;

/* loaded from: classes5.dex */
public final class zzzs {
    private static final BigInteger zze = null;
    private static final BigInteger zzf = null;
    private Integer zza;
    private BigInteger zzb;
    private zzzt zzc;
    private zzzu zzd;

    static {
        BigInteger r0 = BigInteger.valueOf(2);
        zze = r0;
        zzf = r0.pow(256);
    }

    public /* synthetic */ zzzs(byte[] r2) {
        this.zza = null;
        this.zzb = zzzv.zza;
        this.zzc = null;
        this.zzd = zzzu.zzd;
    }

    public final zzzs zza(int r1) {
        this.zza = Integer.valueOf(r1);
        return this;
    }

    public final zzzs zzb(BigInteger r1) {
        this.zzb = r1;
        return this;
    }

    public final zzzs zzc(zzzu r1) {
        this.zzd = r1;
        return this;
    }

    public final zzzs zzd(zzzt r1) {
        this.zzc = r1;
        return this;
    }

    public final zzzv zze() {
        Integer r0 = this.zza;
        if (r0 != null) goto L5;
        zzsj$$ExternalSyntheticBUOutline1.m("key size is not set");
        return null;
    L5:
        if (this.zzb != null) goto L7;
        zzsj$$ExternalSyntheticBUOutline1.m("publicExponent is not set");
        return null;
    L7:
        if (this.zzc != null) goto L9;
        zzsj$$ExternalSyntheticBUOutline1.m("hash type is not set");
        return null;
    L9:
        if (this.zzd != null) goto L11;
        zzsj$$ExternalSyntheticBUOutline1.m("variant is not set");
        return null;
    L11:
        if (r0.intValue() < 2048) goto L29;
        BigInteger r02 = this.zzb;
        int r1 = r02.compareTo(zzzv.zza);
        if (r1 == 0) goto L21;
        if (r1 < 0) goto L27;
        if (r02.mod(zze).equals(BigInteger.ZERO) == true) goto L25;
        if (r02.compareTo(zzf) <= 0) goto L21;
        throw new InvalidAlgorithmParameterException("Public exponent cannot be larger than 2^256.");
    L25:
        throw new InvalidAlgorithmParameterException("Invalid public exponent");
    L27:
        throw new InvalidAlgorithmParameterException("Public exponent must be at least 65537.");
    L21:
        return new zzzv(this.zza.intValue(), this.zzb, this.zzd, this.zzc, null);
    L29:
        throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; must be at least 2048 bits", new Object[]{this.zza}));
    }
}
