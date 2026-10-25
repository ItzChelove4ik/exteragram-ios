package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Arrays;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzayr {
    private static final zzayr zza = null;
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private boolean zze;

    static {
        zza = new zzayr(0, new int[0], new Object[0], false);
    }

    private zzayr(int r1, int[] r2, Object[] r3, boolean r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
    }

    public static zzayr zza() {
        return zza;
    }

    public static zzayr zzb() {
        return new zzayr(0, new int[8], new Object[8], true);
    }

    public static zzayr zzc(zzayr r6, zzayr r7) {
        int r0 = r6.zzb + r7.zzb;
        int[] r1 = Arrays.copyOf(r6.zzc, r0);
        System.arraycopy(r7.zzc, 0, r1, r6.zzb, r7.zzb);
        Object[] r2 = Arrays.copyOf(r6.zzd, r0);
        System.arraycopy(r7.zzd, 0, r2, r6.zzb, r7.zzb);
        return new zzayr(r0, r1, r2, true);
    }

    private final void zzi(int r4) {
        int[] r0 = this.zzc;
        if (r4 <= r0.length) goto L12;
        int r1 = this.zzb;
        int r12 = r1 + (r1 / 2);
        if (r12 < r4) goto L8;
        r4 = r12;
    L8:
        if (r4 >= 8) goto L10;
        r4 = 8;
    L10:
        this.zzc = Arrays.copyOf(r0, r4);
        this.zzd = Arrays.copyOf(this.zzd, r4);
        return;
    }

    public final boolean equals(Object r9) {
        if (this != r9) goto L6;
        return true;
    L6:
        if (r9 != null) goto L9;
        return false;
    L9:
        if ((r9 instanceof zzayr) == true) goto L11;
        return false;
    L11:
        zzayr r92 = (zzayr) r9;
        int r2 = this.zzb;
        if (r2 != r92.zzb) goto L25;
        int[] r3 = this.zzc;
        int[] r4 = r92.zzc;
        int r5 = 0;
    L14:
        if (r5 >= r2) goto L19;
        if (r3[r5] != r4[r5]) goto L25;
        r5 = r5 + 1;
        goto L14
    L19:
        Object[] r22 = this.zzd;
        Object[] r93 = r92.zzd;
        int r8 = this.zzb;
        int r32 = 0;
    L20:
        if (r32 >= r8) goto L24;
        if (r22[r32].equals(r93[r32]) == false) goto L25;
        r32 = r32 + 1;
        goto L20
    L24:
        return true;
    L25:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzb;
        int r1 = r0 + 527;
        int[] r2 = this.zzc;
        int r3 = 0;
        int r4 = 17;
        int r5 = 0;
        int r6 = 17;
    L3:
        if (r5 >= r0) goto L5;
        r6 = (r6 * 31) + r2[r5];
        r5 = r5 + 1;
        goto L3
    L5:
        int r12 = ((r1 * 31) + r6) * 31;
        Object[] r02 = this.zzd;
        int r8 = this.zzb;
    L6:
        if (r3 >= r8) goto L9;
        r4 = (r4 * 31) + r02[r3].hashCode();
        r3 = r3 + 1;
        goto L6
    L9:
        return r12 + r4;
    }

    public final void zzd() {
        if (this.zze == false) goto L6;
        this.zze = false;
        return;
    }

    public final void zze() {
        if (this.zze == false) goto L5;
        return;
    L5:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    public final void zzf(StringBuilder r4, int r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.zzb) goto L6;
        zzaxy.zzb(r4, r5, String.valueOf(this.zzc[r0] >>> 3), this.zzd[r0]);
        r0 = r0 + 1;
        goto L4
    }

    public final void zzg(int r3, Object r4) {
        zze();
        zzi(this.zzb + 1);
        int[] r0 = this.zzc;
        int r1 = this.zzb;
        r0[r1] = r3;
        this.zzd[r1] = r4;
        this.zzb = r1 + 1;
    }

    public final zzayr zzh(zzayr r7) {
        if (r7.equals(zza) == false) goto L5;
        return this;
    L5:
        zze();
        int r0 = this.zzb + r7.zzb;
        zzi(r0);
        System.arraycopy(r7.zzc, 0, this.zzc, this.zzb, r7.zzb);
        System.arraycopy(r7.zzd, 0, this.zzd, this.zzb, r7.zzb);
        this.zzb = r0;
        return this;
    }
}
