package com.google.android.recaptcha.internal;

import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.util.Arrays;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzaia {
    private static final zzaia zza = null;
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    static {
        zza = new zzaia(0, new int[0], new Object[0], false);
    }

    private zzaia(int r2, int[] r3, Object[] r4, boolean r5) {
        this.zze = -1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zzf = r5;
    }

    public static zzaia zza() {
        return zza;
    }

    public static zzaia zzb() {
        return new zzaia(0, new int[8], new Object[8], true);
    }

    public static zzaia zzc(zzaia r6, zzaia r7) {
        int r0 = r6.zzb + r7.zzb;
        int[] r1 = Arrays.copyOf(r6.zzc, r0);
        System.arraycopy(r7.zzc, 0, r1, r6.zzb, r7.zzb);
        Object[] r2 = Arrays.copyOf(r6.zzd, r0);
        System.arraycopy(r7.zzd, 0, r2, r6.zzb, r7.zzb);
        return new zzaia(r0, r1, r2, true);
    }

    private final void zzm(int r4) {
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
        if ((r9 instanceof zzaia) == true) goto L11;
        return false;
    L11:
        zzaia r92 = (zzaia) r9;
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
        if (this.zzf == false) goto L6;
        this.zzf = false;
        return;
    }

    public final void zze() {
        if (this.zzf == false) goto L5;
        return;
    L5:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    public final void zzg(zzafb r6) {
        if (this.zzb == 0) goto L25;
        int r0 = 0;
    L6:
        if (r0 >= this.zzb) goto L33;
        int r1 = this.zzc[r0];
        Object r2 = this.zzd[r0];
        int r3 = r1 >>> 3;
        int r12 = r1 & 7;
        if (r12 != 0) goto L10;
        r6.zzc(r3, ((Long) r2).longValue());
    L24:
        r0 = r0 + 1;
        goto L6
    L10:
        if (r12 != 1) goto L12;
        r6.zzj(r3, ((Long) r2).longValue());
        goto L24
    L12:
        if (r12 != 2) goto L14;
        r6.zzn(r3, (zzaer) r2);
        goto L24
    L14:
        if (r12 != 3) goto L16;
        r6.zzt(r3);
        ((zzaia) r2).zzg(r6);
        r6.zzu(r3);
        goto L24
    L16:
        if (r12 != 5) goto L18;
        r6.zzk(r3, ((Integer) r2).intValue());
        goto L24
    L18:
        HttpUrl$$ExternalSyntheticBUOutline0.m(new zzagj("Protocol message tag had invalid wire type."));
        return;
    L33:
        return;
    }

    public final int zzh() {
        int r0 = this.zze;
        if (r0 != (-1)) goto L10;
        int r02 = 0;
        int r1 = 0;
    L6:
        if (r02 >= this.zzb) goto L8;
        int r2 = this.zzc[r02] >>> 3;
        zzaer r3 = (zzaer) this.zzd[r02];
        int r4 = zzafa.zzu(8);
        int r5 = zzafa.zzu(16) + zzafa.zzu(r2);
        int r22 = zzafa.zzu(24);
        int r32 = r3.zzb();
        r1 = r1 + (((r4 + r4) + r5) + (r22 + (zzafa.zzu(r32) + r32)));
        r02 = r02 + 1;
        goto L6
    L8:
        this.zze = r1;
        return r1;
    L10:
        return r0;
    }

    public final int zzi() {
        int r0 = this.zze;
        if (r0 != (-1)) goto L29;
        int r02 = 0;
        int r1 = 0;
    L6:
        if (r02 >= this.zzb) goto L27;
        int r2 = this.zzc[r02];
        int r3 = r2 >>> 3;
        int r22 = r2 & 7;
        if (r22 != 0) goto L10;
        int r23 = r3 << 3;
        long r32 = ((Long) this.zzd[r02]).longValue();
        int r24 = zzafa.zzu(r23);
        int r33 = zzafa.zzv(r32);
    L22:
        int r25 = r24 + r33;
    L18:
        r1 = r1 + r25;
        r02 = r02 + 1;
        goto L6
    L10:
        if (r22 != 1) goto L12;
        ((Long) this.zzd[r02]).getClass();
        r25 = zzafa.zzu(r3 << 3) + 8;
        goto L18
    L12:
        if (r22 != 2) goto L14;
        int r26 = r3 << 3;
        zzaer r34 = (zzaer) this.zzd[r02];
        int r27 = zzafa.zzu(r26);
        int r35 = r34.zzb();
        r25 = r27 + (zzafa.zzu(r35) + r35);
        goto L18
    L14:
        if (r22 != 3) goto L16;
        int r28 = zzafa.zzu(r3 << 3);
        r24 = r28 + r28;
        r33 = ((zzaia) this.zzd[r02]).zzi();
        goto L22
    L16:
        if (r22 != 5) goto L19;
        ((Integer) this.zzd[r02]).getClass();
        r25 = zzafa.zzu(r3 << 3) + 4;
        goto L18
    L19:
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(new zzagj("Protocol message tag had invalid wire type."));
        return 0;
    L27:
        this.zze = r1;
        return r1;
    L29:
        return r0;
    }

    public final void zzj(StringBuilder r4, int r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.zzb) goto L6;
        zzagz.zzb(r4, r5, String.valueOf(this.zzc[r0] >>> 3), this.zzd[r0]);
        r0 = r0 + 1;
        goto L4
    }

    public final void zzk(int r3, Object r4) {
        zze();
        zzm(this.zzb + 1);
        int[] r0 = this.zzc;
        int r1 = this.zzb;
        r0[r1] = r3;
        this.zzd[r1] = r4;
        this.zzb = r1 + 1;
    }

    public final zzaia zzl(zzaia r7) {
        if (r7.equals(zza) == false) goto L5;
        return this;
    L5:
        zze();
        int r0 = this.zzb + r7.zzb;
        zzm(r0);
        System.arraycopy(r7.zzc, 0, this.zzc, this.zzb, r7.zzb);
        System.arraycopy(r7.zzd, 0, this.zzd, this.zzb, r7.zzb);
        this.zzb = r0;
        return this;
    }
}
