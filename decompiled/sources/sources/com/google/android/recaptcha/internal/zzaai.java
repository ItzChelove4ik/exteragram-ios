package com.google.android.recaptcha.internal;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public final class zzaai {
    private zzaal zza;
    private zzadz zzb;
    private zzadz zzc;
    private zzadz zzd;
    private zzadz zze;
    private zzadz zzf;
    private zzadz zzg;

    public /* synthetic */ zzaai(byte[] r1) {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = null;
        this.zzf = null;
        this.zzg = null;
    }

    public final zzaai zza(zzaal r1) {
        this.zza = r1;
        return this;
    }

    public final zzaai zzb(zzadz r1, zzadz r2) {
        this.zzc = r1;
        this.zzd = r2;
        return this;
    }

    public final zzaai zzc(zzadz r1) {
        this.zzb = r1;
        return this;
    }

    public final zzaai zzd(zzadz r1, zzadz r2) {
        this.zze = r1;
        this.zzf = r2;
        return this;
    }

    public final zzaai zze(zzadz r1) {
        this.zzg = r1;
        return this;
    }

    public final zzaaj zzf() {
        zzaal r0 = this.zza;
        if (r0 != null) goto L5;
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot build without a RSA SSA PKCS1 public key");
        return null;
    L5:
        if (this.zzc != null) goto L7;
    L52:
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot build without prime factors");
        return null;
    L7:
        if (this.zzd == null) goto L52;
        if (this.zzb != null) goto L11;
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot build without private exponent");
        return null;
    L11:
        if (this.zze != null) goto L13;
    L48:
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot build without prime exponents");
        return null;
    L13:
        if (this.zzf == null) goto L48;
        if (this.zzg == null) goto L46;
        BigInteger r02 = r0.zzf().zzc();
        BigInteger r2 = this.zza.zzd();
        BigInteger r3 = this.zzc.zzb(zzsd.zza());
        BigInteger r4 = this.zzd.zzb(zzsd.zza());
        BigInteger r5 = this.zzb.zzb(zzsd.zza());
        BigInteger r6 = this.zze.zzb(zzsd.zza());
        BigInteger r7 = this.zzf.zzb(zzsd.zza());
        BigInteger r8 = this.zzg.zzb(zzsd.zza());
        if (r3.isProbablePrime(10) == true) goto L19;
        zzsj$$ExternalSyntheticBUOutline1.m("p is not a prime");
        return null;
    L19:
        if (r4.isProbablePrime(10) == true) goto L21;
        zzsj$$ExternalSyntheticBUOutline1.m("q is not a prime");
        return null;
    L21:
        if (r3.multiply(r4).equals(r2) == false) goto L40;
        BigInteger r22 = BigInteger.ONE;
        BigInteger r9 = r3.subtract(r22);
        BigInteger r10 = r4.subtract(r22);
        BigInteger r11 = r9.divide(r9.gcd(r10)).multiply(r10);
        if (r02.multiply(r5).mod(r11).equals(r22) == true) goto L25;
        zzsj$$ExternalSyntheticBUOutline1.m("D is invalid.");
        return null;
    L25:
        if (r02.multiply(r6).mod(r9).equals(r22) == true) goto L27;
        zzsj$$ExternalSyntheticBUOutline1.m("dP is invalid.");
        return null;
    L27:
        if (r02.multiply(r7).mod(r10).equals(r22) == true) goto L29;
        zzsj$$ExternalSyntheticBUOutline1.m("dQ is invalid.");
        return null;
    L29:
        if (r4.multiply(r8).mod(r3).equals(r22) == true) goto L31;
        zzsj$$ExternalSyntheticBUOutline1.m("qInv is invalid.");
        return null;
    L31:
        return new zzaaj(this.zza, this.zzc, this.zzd, this.zzb, this.zze, this.zzf, this.zzg, null);
    L40:
        zzsj$$ExternalSyntheticBUOutline1.m("Prime p times prime q is not equal to the public key's modulus");
        return null;
    L46:
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot build without CRT coefficient");
        return null;
    }
}
