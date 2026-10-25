package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzzy {
    private zzzv zza;
    private BigInteger zzb;
    private Integer zzc;

    public /* synthetic */ zzzy(byte[] r1) {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
    }

    public final zzzy zza(zzzv r1) {
        this.zza = r1;
        return this;
    }

    public final zzzy zzb(BigInteger r1) {
        this.zzb = r1;
        return this;
    }

    public final zzzy zzc(Integer r1) {
        this.zzc = r1;
        return this;
    }

    public final zzzz zzd() {
        if (this.zza == null) goto L43;
        BigInteger r0 = this.zzb;
        if (r0 == null) goto L41;
        int r02 = r0.bitLength();
        int r2 = this.zza.zzb();
        if (r02 == r2) goto L9;
        int r1 = String.valueOf(r02).length();
        StringBuilder r3 = new StringBuilder((r1 + 56) + String.valueOf(r2).length());
        r3.append("Got modulus size ");
        r3.append(r02);
        r3.append(", but parameters requires modulus size ");
        r3.append(r2);
        throw new GeneralSecurityException(r3.toString());
    L9:
        if (this.zza.zzf() == false) goto L16;
        if (this.zzc != null) goto L16;
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot create key without ID requirement with parameters with ID requirement");
        return null;
    L16:
        if (this.zza.zzf() == true) goto L23;
        if (this.zzc == null) goto L23;
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot create key with ID requirement with parameters without ID requirement");
        return null;
    L23:
        if (this.zza.zzd() != zzzu.zzd) goto L27;
        zzady r03 = zzvc.zza;
    L25:
        zzady r4 = r03;
        return new zzzz(this.zza, this.zzb, r4, this.zzc, null);
    L27:
        if (this.zza.zzd() != zzzu.zzc) goto L29;
    L36:
        r03 = zzvc.zza(this.zzc.intValue());
        goto L25
    L29:
        if (this.zza.zzd() == zzzu.zzb) goto L36;
        if (this.zza.zzd() != zzzu.zza) goto L34;
        r03 = zzvc.zzb(this.zzc.intValue());
        goto L25
    L34:
        Segment$$ExternalSyntheticBUOutline1.m("Unknown RsaSsaPkcs1Parameters.Variant: ".concat(String.valueOf(this.zza.zzd())));
        return null;
    L41:
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot build without modulus");
        return null;
    L43:
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot build without parameters");
        return null;
    }
}
