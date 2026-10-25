package com.google.android.gms.internal.fido;

import java.math.RoundingMode;
import java.util.Arrays;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzgb {
    final int zza;
    final int zzb;
    final int zzc;
    final int zzd;
    private final String zze;
    private final char[] zzf;
    private final byte[] zzg;
    private final boolean zzh;

    public zzgb(String r10, char[] r11) {
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
        zzbm.zzd(r7, "Non-ASCII character: %s", r5);
        if (r1[r5] == (-1)) goto L13;
        r6 = false;
    L13:
        zzbm.zzd(r6, "Duplicate character: %s", r5);
        r1[r5] = (byte) r4;
        r4 = r4 + 1;
        goto L4
    L8:
        r7 = false;
        goto L9
    L14:
        this(r10, r11, r1, false);
    }

    private zzgb(String r4, char[] r5, byte[] r6, boolean r7) {
        this.zze = r4;
        r5.getClass();
        this.zzf = r5;
        int r42 = r5.length;     // Catch: ArithmeticException -> L10
        int r0 = zzgh.zzb(r42, RoundingMode.UNNECESSARY);     // Catch: ArithmeticException -> L10
        this.zzb = r0;     // Catch: ArithmeticException -> L10
        int r52 = Integer.numberOfTrailingZeros(r0);
        int r1 = 1 << (3 - r52);
        this.zzc = r1;
        this.zzd = r0 >> r52;
        this.zza = r42 - 1;
        this.zzg = r6;
        boolean[] r43 = new boolean[r1];
        int r53 = 0;
    L6:
        if (r53 >= this.zzd) goto L8;
        r43[zzgh.zza(r53 * 8, this.zzb, RoundingMode.CEILING)] = true;
        r53 = r53 + 1;
        goto L6
    L8:
        this.zzh = r7;
        return;
    L10:
        e = move-exception;
        throw new IllegalArgumentException("Illegal alphabet length " + r5.length, e);
    }

    public static /* bridge */ /* synthetic */ char[] zzd(zzgb r0) {
        return r0.zzf;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzgb) == false) goto L10;
        zzgb r42 = (zzgb) r4;
        if (this.zzh != r42.zzh) goto L10;
        if (Arrays.equals(this.zzf, r42.zzf) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        boolean r0 = this.zzh;
        int r2 = Arrays.hashCode(this.zzf);
        if (true == r0) goto L5;
        int r02 = 1237;
    L7:
        return r2 + r02;
    L5:
        r02 = 1231;
        goto L7
    }

    public final String toString() {
        return this.zze;
    }

    public final char zza(int r1) {
        return this.zzf[r1];
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8 */
    public final zzgb zzb() {
        int r0 = 0;
        int r1 = 0;
    L3:
        char[] r2 = this.zzf;
        if (r1 >= r2.length) goto L42;
        if (zzba.zza(r2[r1]) == true) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        int r12 = 0;
    L8:
        int r5 = 65;
        if (r12 >= r2.length) goto L15;
        char r3 = r2[r12];
        if (r3 < 'A') goto L14;
        if (r3 > 'Z') goto L14;
        boolean r13 = true;
    L16:
        zzbm.zzf(!r13, "Cannot call upperCase() on a mixed-case alphabet");
        char[] r14 = new char[this.zzf.length];
    L17:
        char[] r22 = this.zzf;
        if (r0 >= r22.length) goto L23;
        int r23 = r22[r0];
        if (zzba.zza(r23) == false) goto L22;
        r23 = r23 ^ 32;
    L22:
        r14[r0] = (char) r23;
        r0 = r0 + 1;
        goto L17
    L23:
        zzgb r24 = new zzgb(this.zze.concat(".upperCase()"), r14);
        if (this.zzh == true) goto L26;
        return r24;
    L26:
        if (r24.zzh == true) goto L57;
        byte[] r10 = r24.zzg;
        byte[] r102 = Arrays.copyOf(r10, r10.length);
    L29:
        if (r5 > 90) goto L39;
        int r02 = r5 | 32;
        byte[] r15 = r24.zzg;
        byte r32 = r15[r5];
        byte r16 = r15[r02];
        if (r32 != (-1)) goto L33;
        r102[r5] = r16;
    L36:
        r5 = r5 + 1;
        goto L29
    L33:
        char r8 = (char) r5;
        char r9 = (char) r02;
        if (r16 != (-1)) goto L37;
        r102[r02] = r32;
        goto L36
    L37:
        Segment$$ExternalSyntheticBUOutline1.m(zzbo.zza("Can't ignoreCase() since '%s' and '%s' encode different values", new Object[]{Character.valueOf(r8), Character.valueOf(r9)}));
        return null;
    L39:
        String r03 = r24.zze;
        return new zzgb(r03.concat(".ignoreCase()"), r24.zzf, r102, true);
    L57:
        return r24;
    L14:
        r12 = r12 + 1;
        goto L8
    L15:
        r13 = false;
        goto L16
    L42:
        return this;
    }

    public final boolean zzc(char r2) {
        byte[] r1 = this.zzg;
        if (r1.length > 61) goto L5;
        return false;
    L5:
        if (r1[61] == (-1)) goto L10;
        return true;
    L10:
        return false;
    }
}
