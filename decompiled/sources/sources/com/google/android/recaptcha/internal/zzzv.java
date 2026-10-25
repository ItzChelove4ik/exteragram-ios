package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzzv extends zzaav {
    public static final BigInteger zza = null;
    private final int zzb;
    private final BigInteger zzc;
    private final zzzu zzd;
    private final zzzt zze;

    static {
        zza = BigInteger.valueOf(65537);
    }

    public /* synthetic */ zzzv(int r1, BigInteger r2, zzzu r3, zzzt r4, byte[] r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
    }

    public static zzzs zza() {
        return new zzzs(null);
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzzv) == true) goto L5;
        return false;
    L5:
        zzzv r42 = (zzzv) r4;
        if (r42.zzb == this.zzb) goto L8;
    L15:
        return false;
    L8:
        if (Objects.equals(r42.zzc, this.zzc) == false) goto L15;
        if (r42.zzd != this.zzd) goto L15;
        if (r42.zze != this.zze) goto L15;
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzzv.class, Integer.valueOf(this.zzb), this.zzc, this.zzd, this.zze});
    }

    public final String toString() {
        BigInteger r0 = this.zzc;
        zzzt r1 = this.zze;
        String r2 = String.valueOf(this.zzd);
        String r12 = String.valueOf(r1);
        String r02 = String.valueOf(r0);
        int r3 = r2.length();
        int r4 = r12.length();
        int r5 = r02.length();
        int r7 = this.zzb;
        int r32 = ((((r3 + 47) + r4) + 18) + r5) + 6;
        StringBuilder r42 = new StringBuilder((r32 + String.valueOf(r7).length()) + 13);
        r42.append("RSA SSA PKCS1 Parameters (variant: ");
        r42.append(r2);
        r42.append(", hashType: ");
        r42.append(r12);
        r42.append(", publicExponent: ");
        r42.append(r02);
        r42.append(", and ");
        r42.append(r7);
        r42.append("-bit modulus)");
        return r42.toString();
    }

    public final int zzb() {
        return this.zzb;
    }

    public final BigInteger zzc() {
        return this.zzc;
    }

    public final zzzu zzd() {
        return this.zzd;
    }

    public final zzzt zze() {
        return this.zze;
    }

    public final boolean zzf() {
        if (this.zzd == zzzu.zzd) goto L6;
        return true;
    L6:
        return false;
    }
}
