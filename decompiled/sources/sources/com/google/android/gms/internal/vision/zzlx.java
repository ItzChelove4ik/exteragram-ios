package com.google.android.gms.internal.vision;

import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.util.Arrays;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzlx {
    private static final zzlx zza = null;
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    static {
        zza = new zzlx(0, new int[0], new Object[0], false);
    }

    private zzlx() {
        this(0, new int[8], new Object[8], true);
    }

    private zzlx(int r2, int[] r3, Object[] r4, boolean r5) {
        this.zze = -1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zzf = r5;
    }

    public static zzlx zza() {
        return zza;
    }

    public static zzlx zza(zzlx r6, zzlx r7) {
        int r0 = r6.zzb + r7.zzb;
        int[] r1 = Arrays.copyOf(r6.zzc, r0);
        System.arraycopy(r7.zzc, 0, r1, r6.zzb, r7.zzb);
        Object[] r2 = Arrays.copyOf(r6.zzd, r0);
        System.arraycopy(r7.zzd, 0, r2, r6.zzb, r7.zzb);
        return new zzlx(r0, r1, r2, true);
    }

    private static void zza(int r2, Object r3, zzmr r4) {
        int r0 = r2 >>> 3;
        int r22 = r2 & 7;
        if (r22 != 0) goto L5;
        r4.zza(r0, ((Long) r3).longValue());
        return;
    L5:
        if (r22 != 1) goto L7;
        r4.zzd(r0, ((Long) r3).longValue());
        return;
    L7:
        if (r22 != 2) goto L9;
        r4.zza(r0, (zzht) r3);
        return;
    L9:
        if (r22 == 3) goto L17;
        if (r22 != 5) goto L14;
        r4.zzd(r0, ((Integer) r3).intValue());
        return;
    L14:
        HttpUrl$$ExternalSyntheticBUOutline0.m(zzjk.zzf());
        return;
    L17:
        if (r4.zza() != zzmq.zza) goto L20;
        r4.zza(r0);
        ((zzlx) r3).zzb(r4);
        r4.zzb(r0);
        return;
    L20:
        r4.zzb(r0);
        ((zzlx) r3).zzb(r4);
        r4.zza(r0);
    }

    public static zzlx zzb() {
        return new zzlx();
    }

    public final boolean equals(Object r9) {
        if (this != r9) goto L6;
        return true;
    L6:
        if (r9 != null) goto L9;
        return false;
    L9:
        if ((r9 instanceof zzlx) == true) goto L11;
        return false;
    L11:
        zzlx r92 = (zzlx) r9;
        int r2 = this.zzb;
        if (r2 != r92.zzb) goto L26;
        int[] r3 = this.zzc;
        int[] r4 = r92.zzc;
        int r5 = 0;
    L14:
        if (r5 >= r2) goto L19;
        if (r3[r5] != r4[r5]) goto L26;
        r5 = r5 + 1;
        goto L14
    L19:
        Object[] r22 = this.zzd;
        Object[] r93 = r92.zzd;
        int r8 = this.zzb;
        int r32 = 0;
    L20:
        if (r32 >= r8) goto L25;
        if (r22[r32].equals(r93[r32]) == false) goto L26;
        r32 = r32 + 1;
        goto L20
    L25:
        return true;
    L26:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzb;
        int r1 = (r0 + 527) * 31;
        int[] r2 = this.zzc;
        int r3 = 17;
        int r4 = 0;
        int r6 = 17;
        int r5 = 0;
    L3:
        if (r5 >= r0) goto L5;
        r6 = (r6 * 31) + r2[r5];
        r5 = r5 + 1;
        goto L3
    L5:
        int r12 = (r1 + r6) * 31;
        Object[] r02 = this.zzd;
        int r8 = this.zzb;
    L6:
        if (r4 >= r8) goto L9;
        r3 = (r3 * 31) + r02[r4].hashCode();
        r4 = r4 + 1;
        goto L6
    L9:
        return r12 + r3;
    }

    public final void zza(int r4, Object r5) {
        if (this.zzf == false) goto L13;
        int r0 = this.zzb;
        int[] r1 = this.zzc;
        if (r0 == r1.length) goto L7;
    L11:
        int[] r02 = this.zzc;
        int r12 = this.zzb;
        r02[r12] = r4;
        this.zzd[r12] = r5;
        this.zzb = r12 + 1;
        return;
    L7:
        if (r0 >= 4) goto L9;
        int r2 = 8;
    L10:
        int r03 = r0 + r2;
        this.zzc = Arrays.copyOf(r1, r03);
        this.zzd = Arrays.copyOf(this.zzd, r03);
        goto L11
    L9:
        r2 = r0 >> 1;
        goto L10
    L13:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    public final void zza(zzmr r4) {
        if (r4.zza() != zzmq.zzb) goto L7;
        int r0 = this.zzb - 1;
    L5:
        if (r0 < 0) goto L11;
        r4.zza(this.zzc[r0] >>> 3, this.zzd[r0]);
        r0 = r0 - 1;
        goto L5
    L11:
        return;
    L7:
        int r02 = 0;
    L9:
        if (r02 >= this.zzb) goto L14;
        r4.zza(this.zzc[r02] >>> 3, this.zzd[r02]);
        r02 = r02 + 1;
        goto L9
    }

    public final void zza(StringBuilder r4, int r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.zzb) goto L6;
        zzkp.zza(r4, r5, String.valueOf(this.zzc[r0] >>> 3), this.zzd[r0]);
        r0 = r0 + 1;
        goto L4
    }

    public final void zzb(zzmr r4) {
        if (this.zzb != 0) goto L6;
        return;
    L6:
        if (r4.zza() != zzmq.zza) goto L11;
        int r0 = 0;
    L9:
        if (r0 >= this.zzb) goto L17;
        zza(this.zzc[r0], this.zzd[r0], r4);
        r0 = r0 + 1;
        goto L9
    L17:
        return;
    L11:
        int r02 = this.zzb - 1;
    L12:
        if (r02 < 0) goto L18;
        zza(this.zzc[r02], this.zzd[r02], r4);
        r02 = r02 - 1;
        goto L12
    }

    public final void zzc() {
        this.zzf = false;
    }

    public final int zzd() {
        int r0 = this.zze;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        int r02 = 0;
        int r1 = 0;
    L7:
        if (r02 >= this.zzb) goto L9;
        r1 = r1 + zzii.zzd(this.zzc[r02] >>> 3, (zzht) this.zzd[r02]);
        r02 = r02 + 1;
        goto L7
    L9:
        this.zze = r1;
        return r1;
    }

    public final int zze() {
        int r0 = this.zze;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        int r02 = 0;
        int r1 = 0;
    L7:
        if (r02 >= this.zzb) goto L27;
        int r2 = this.zzc[r02];
        int r3 = r2 >>> 3;
        int r22 = r2 & 7;
        if (r22 != 0) goto L11;
        int r23 = zzii.zze(r3, ((Long) this.zzd[r02]).longValue());
    L19:
        r1 = r1 + r23;
        r02 = r02 + 1;
        goto L7
    L11:
        if (r22 != 1) goto L13;
        r23 = zzii.zzg(r3, ((Long) this.zzd[r02]).longValue());
        goto L19
    L13:
        if (r22 != 2) goto L15;
        r23 = zzii.zzc(r3, (zzht) this.zzd[r02]);
        goto L19
    L15:
        if (r22 != 3) goto L17;
        r23 = (zzii.zze(r3) << 1) + ((zzlx) this.zzd[r02]).zze();
        goto L19
    L17:
        if (r22 != 5) goto L20;
        r23 = zzii.zzi(r3, ((Integer) this.zzd[r02]).intValue());
        goto L19
    L20:
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(zzjk.zzf());
        return 0;
    L27:
        this.zze = r1;
        return r1;
    }
}
