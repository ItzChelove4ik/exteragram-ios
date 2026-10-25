package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzaah extends zzaav {
    public static final BigInteger zza = null;
    private final int zzb;
    private final BigInteger zzc;
    private final zzaag zzd;
    private final zzaaf zze;
    private final zzaaf zzf;
    private final int zzg;

    static {
        zza = BigInteger.valueOf(65537);
    }

    public /* synthetic */ zzaah(int r1, BigInteger r2, zzaag r3, zzaaf r4, zzaaf r5, int r6, byte[] r7) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        this.zzf = r5;
        this.zzg = r6;
    }

    public static zzaae zza() {
        return new zzaae(null);
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzaah) == true) goto L5;
        return false;
    L5:
        zzaah r42 = (zzaah) r4;
        if (r42.zzb == this.zzb) goto L8;
    L19:
        return false;
    L8:
        if (Objects.equals(r42.zzc, this.zzc) == false) goto L19;
        if (Objects.equals(r42.zzd, this.zzd) == false) goto L19;
        if (Objects.equals(r42.zze, this.zze) == false) goto L19;
        if (Objects.equals(r42.zzf, this.zzf) == false) goto L19;
        if (r42.zzg != this.zzg) goto L19;
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzaah.class, Integer.valueOf(this.zzb), this.zzc, this.zzd, this.zze, this.zzf, Integer.valueOf(this.zzg)});
    }

    public final String toString() {
        BigInteger r0 = this.zzc;
        zzaaf r1 = this.zzf;
        zzaaf r2 = this.zze;
        String r3 = String.valueOf(this.zzd);
        String r22 = String.valueOf(r2);
        String r12 = String.valueOf(r1);
        String r02 = String.valueOf(r0);
        int r4 = r3.length();
        int r5 = r22.length();
        int r6 = r12.length();
        int r7 = this.zzg;
        int r8 = String.valueOf(r7).length();
        int r9 = r02.length();
        int r11 = this.zzb;
        int r42 = ((((((((r4 + 55) + r5) + 17) + r6) + 19) + r8) + 18) + r9) + 6;
        StringBuilder r52 = new StringBuilder((r42 + String.valueOf(r11).length()) + 13);
        r52.append("RSA SSA PSS Parameters (variant: ");
        r52.append(r3);
        r52.append(", signature hashType: ");
        r52.append(r22);
        r52.append(", mgf1 hashType: ");
        r52.append(r12);
        r52.append(", saltLengthBytes: ");
        r52.append(r7);
        r52.append(", publicExponent: ");
        r52.append(r02);
        r52.append(", and ");
        r52.append(r11);
        r52.append("-bit modulus)");
        return r52.toString();
    }

    public final int zzb() {
        return this.zzb;
    }

    public final BigInteger zzc() {
        return this.zzc;
    }

    public final zzaag zzd() {
        return this.zzd;
    }

    public final zzaaf zze() {
        return this.zze;
    }

    public final zzaaf zzf() {
        return this.zzf;
    }

    public final int zzg() {
        return this.zzg;
    }

    public final boolean zzh() {
        if (this.zzd == zzaag.zzd) goto L6;
        return true;
    L6:
        return false;
    }
}
