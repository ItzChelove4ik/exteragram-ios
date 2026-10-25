package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* loaded from: classes5.dex */
public final class zzaae {
    private static final BigInteger zzg = null;
    private static final BigInteger zzh = null;
    private Integer zza;
    private BigInteger zzb;
    private zzaaf zzc;
    private zzaaf zzd;
    private Integer zze;
    private zzaag zzf;

    static {
        BigInteger r0 = BigInteger.valueOf(2);
        zzg = r0;
        zzh = r0.pow(256);
    }

    public /* synthetic */ zzaae(byte[] r2) {
        this.zza = null;
        this.zzb = zzaah.zza;
        this.zzc = null;
        this.zzd = null;
        this.zze = null;
        this.zzf = zzaag.zzd;
    }

    public final zzaae zza(int r1) {
        this.zza = Integer.valueOf(r1);
        return this;
    }

    public final zzaae zzb(BigInteger r1) {
        this.zzb = r1;
        return this;
    }

    public final zzaae zzc(zzaag r1) {
        this.zzf = r1;
        return this;
    }

    public final zzaae zzd(zzaaf r1) {
        this.zzc = r1;
        return this;
    }

    public final zzaae zze(zzaaf r1) {
        this.zzd = r1;
        return this;
    }

    public final zzaae zzf(int r2) {
        if (r2 < 0) goto L6;
        this.zze = Integer.valueOf(r2);
        return this;
    L6:
        throw new GeneralSecurityException(String.format("Invalid salt length in bytes %d; salt length must be positive", new Object[]{Integer.valueOf(r2)}));
    }

    public final zzaah zzg() {
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
        zzsj$$ExternalSyntheticBUOutline1.m("signature hash type is not set");
        return null;
    L9:
        if (this.zzd != null) goto L11;
        zzsj$$ExternalSyntheticBUOutline1.m("mgf1 hash type is not set");
        return null;
    L11:
        if (this.zzf != null) goto L13;
        zzsj$$ExternalSyntheticBUOutline1.m("variant is not set");
        return null;
    L13:
        if (this.zze != null) goto L15;
        zzsj$$ExternalSyntheticBUOutline1.m("salt length is not set");
        return null;
    L15:
        if (r0.intValue() < 2048) goto L37;
        if (this.zzc != this.zzd) goto L34;
        BigInteger r02 = this.zzb;
        int r1 = r02.compareTo(zzaah.zza);
        if (r1 == 0) goto L27;
        if (r1 < 0) goto L33;
        if (r02.mod(zzg).equals(BigInteger.ZERO) == true) goto L31;
        if (r02.compareTo(zzh) <= 0) goto L27;
        throw new InvalidAlgorithmParameterException("Public exponent cannot be larger than 2^256.");
    L31:
        throw new InvalidAlgorithmParameterException("Invalid public exponent");
    L33:
        throw new InvalidAlgorithmParameterException("Public exponent must be at least 65537.");
    L27:
        return new zzaah(this.zza.intValue(), this.zzb, this.zzf, this.zzc, this.zzd, this.zze.intValue(), null);
    L34:
        zzsj$$ExternalSyntheticBUOutline1.m("MGF1 hash is different from signature hash");
        return null;
    L37:
        throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; must be at least %d bits", new Object[]{this.zza, 2048}));
    }
}
