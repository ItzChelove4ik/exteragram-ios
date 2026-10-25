package com.google.android.recaptcha.internal;

import java.math.RoundingMode;
import java.util.Arrays;

/* loaded from: classes5.dex */
final class zzrq {
    final int zza;
    final int zzb;
    final int zzc;
    final int zzd;
    private final String zze;
    private final char[] zzf;
    private final byte[] zzg;
    private final boolean[] zzh;
    private final boolean zzi;

    public zzrq(String r10, char[] r11) {
        byte[] r1 = new byte[128];
        Arrays.fill(r1, (byte) -1);
        int r4 = 0;
    L4:
        if (r4 >= r11.length) goto L14;
        char r5 = r11[r4];
        boolean r6 = true;
        if (r5 >= 128) goto L8;
        boolean r7 = true;
    L9:
        zzoq.zzb(r7, "Non-ASCII character: %s", r5);
        if (r1[r5] == (-1)) goto L13;
        r6 = false;
    L13:
        zzoq.zzb(r6, "Duplicate character: %s", r5);
        r1[r5] = (byte) r4;
        r4 = r4 + 1;
        goto L4
    L8:
        r7 = false;
        goto L9
    L14:
        this(r10, r11, r1, false);
    }

    private zzrq(String r4, char[] r5, byte[] r6, boolean r7) {
        this.zze = r4;
        r5.getClass();
        this.zzf = r5;
        int r42 = r5.length;     // Catch: ArithmeticException -> L10
        int r72 = zzrx.zza(r42, RoundingMode.UNNECESSARY);     // Catch: ArithmeticException -> L10
        this.zzb = r72;     // Catch: ArithmeticException -> L10
        int r52 = Integer.numberOfTrailingZeros(r72);
        int r0 = 1 << (3 - r52);
        this.zzc = r0;
        this.zzd = r72 >> r52;
        this.zza = r42 - 1;
        this.zzg = r6;
        boolean[] r43 = new boolean[r0];
        int r62 = 0;
    L6:
        if (r62 >= this.zzd) goto L8;
        r43[zzrx.zzb(r62 * 8, this.zzb, RoundingMode.CEILING)] = true;
        r62 = r62 + 1;
        goto L6
    L8:
        this.zzh = r43;
        this.zzi = false;
        return;
    L10:
        e = move-exception;
        int r44 = r5.length;
        StringBuilder r73 = new StringBuilder(String.valueOf(r44).length() + 24);
        r73.append("Illegal alphabet length ");
        r73.append(r44);
        throw new IllegalArgumentException(r73.toString(), e);
    }

    public final boolean equals(Object r3) {
        if ((r3 instanceof zzrq) == true) goto L5;
    L8:
        return false;
    L5:
        if (Arrays.equals(this.zzf, ((zzrq) r3).zzf) == false) goto L8;
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzf) + 1237;
    }

    public final String toString() {
        return this.zze;
    }

    public final char zza(int r1) {
        return this.zzf[r1];
    }

    public final boolean zzb(int r2) {
        int r0 = this.zzc;
        return this.zzh[r2 % r0];
    }

    public final int zzc(char r4) {
        if (r4 > 127) goto L16;
        byte r3 = this.zzg[r4];
        if (r3 == (-1)) goto L7;
        return r3;
    L7:
        if (r4 <= ' ') goto L13;
        if (r4 == 127) goto L13;
        StringBuilder r1 = new StringBuilder(String.valueOf(r4).length() + 24);
        r1.append("Unrecognized character: ");
        r1.append(r4);
        throw new zzrt(r1.toString());
    L13:
        throw new zzrt("Unrecognized character: 0x".concat(String.valueOf(Integer.toHexString(r4))));
    L16:
        throw new zzrt("Unrecognized character: 0x".concat(String.valueOf(Integer.toHexString(r4))));
    }

    public final boolean zzd(char r1) {
        if (this.zzg[61] == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public final /* synthetic */ char[] zze() {
        return this.zzf;
    }
}
