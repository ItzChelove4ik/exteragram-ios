package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.util.Arrays;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzey {
    private static final zzey zzoz = null;
    private int count;
    private boolean zzfa;
    private int zzjq;
    private Object[] zzmj;
    private int[] zzpa;

    static {
        zzoz = new zzey(0, new int[0], new Object[0], false);
    }

    private zzey() {
        this(0, new int[8], new Object[8], true);
    }

    private zzey(int r2, int[] r3, Object[] r4, boolean r5) {
        this.zzjq = -1;
        this.count = r2;
        this.zzpa = r3;
        this.zzmj = r4;
        this.zzfa = r5;
    }

    public static zzey zza(zzey r6, zzey r7) {
        int r0 = r6.count + r7.count;
        int[] r1 = Arrays.copyOf(r6.zzpa, r0);
        System.arraycopy(r7.zzpa, 0, r1, r6.count, r7.count);
        Object[] r2 = Arrays.copyOf(r6.zzmj, r0);
        System.arraycopy(r7.zzmj, 0, r2, r6.count, r7.count);
        return new zzey(r0, r1, r2, true);
    }

    private static void zzb(int r2, Object r3, zzfr r4) {
        int r0 = r2 >>> 3;
        int r22 = r2 & 7;
        if (r22 != 0) goto L5;
        r4.zzi(r0, ((Long) r3).longValue());
        return;
    L5:
        if (r22 != 1) goto L7;
        r4.zzc(r0, ((Long) r3).longValue());
        return;
    L7:
        if (r22 != 2) goto L9;
        r4.zza(r0, (zzbb) r3);
        return;
    L9:
        if (r22 == 3) goto L17;
        if (r22 != 5) goto L14;
        r4.zzf(r0, ((Integer) r3).intValue());
        return;
    L14:
        HttpUrl$$ExternalSyntheticBUOutline0.m(zzco.zzbn());
        return;
    L17:
        if (r4.zzaj() != zzcg.zzg.zzko) goto L20;
        r4.zzaa(r0);
        ((zzey) r3).zzb(r4);
        r4.zzab(r0);
        return;
    L20:
        r4.zzab(r0);
        ((zzey) r3).zzb(r4);
        r4.zzaa(r0);
    }

    public static zzey zzea() {
        return zzoz;
    }

    public static zzey zzeb() {
        return new zzey();
    }

    public final boolean equals(Object r9) {
        if (this != r9) goto L6;
        return true;
    L6:
        if (r9 != null) goto L9;
        return false;
    L9:
        if ((r9 instanceof zzey) == true) goto L11;
        return false;
    L11:
        zzey r92 = (zzey) r9;
        int r2 = this.count;
        if (r2 != r92.count) goto L26;
        int[] r3 = this.zzpa;
        int[] r4 = r92.zzpa;
        int r5 = 0;
    L14:
        if (r5 >= r2) goto L19;
        if (r3[r5] != r4[r5]) goto L26;
        r5 = r5 + 1;
        goto L14
    L19:
        Object[] r22 = this.zzmj;
        Object[] r93 = r92.zzmj;
        int r8 = this.count;
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
        int r0 = this.count;
        int r1 = (r0 + 527) * 31;
        int[] r2 = this.zzpa;
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
        Object[] r02 = this.zzmj;
        int r8 = this.count;
    L6:
        if (r4 >= r8) goto L9;
        r3 = (r3 * 31) + r02[r4].hashCode();
        r4 = r4 + 1;
        goto L6
    L9:
        return r12 + r3;
    }

    public final void zza(zzfr r4) {
        if (r4.zzaj() != zzcg.zzg.zzkp) goto L7;
        int r0 = this.count - 1;
    L5:
        if (r0 < 0) goto L11;
        r4.zza(this.zzpa[r0] >>> 3, this.zzmj[r0]);
        r0 = r0 - 1;
        goto L5
    L11:
        return;
    L7:
        int r02 = 0;
    L9:
        if (r02 >= this.count) goto L14;
        r4.zza(this.zzpa[r02] >>> 3, this.zzmj[r02]);
        r02 = r02 + 1;
        goto L9
    }

    public final void zza(StringBuilder r4, int r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.count) goto L6;
        zzdr.zza(r4, r5, String.valueOf(this.zzpa[r0] >>> 3), this.zzmj[r0]);
        r0 = r0 + 1;
        goto L4
    }

    public final int zzas() {
        int r0 = this.zzjq;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        int r02 = 0;
        int r1 = 0;
    L7:
        if (r02 >= this.count) goto L27;
        int r2 = this.zzpa[r02];
        int r3 = r2 >>> 3;
        int r22 = r2 & 7;
        if (r22 != 0) goto L11;
        int r23 = zzbn.zze(r3, ((Long) this.zzmj[r02]).longValue());
    L19:
        r1 = r1 + r23;
        r02 = r02 + 1;
        goto L7
    L11:
        if (r22 != 1) goto L13;
        r23 = zzbn.zzg(r3, ((Long) this.zzmj[r02]).longValue());
        goto L19
    L13:
        if (r22 != 2) goto L15;
        r23 = zzbn.zzc(r3, (zzbb) this.zzmj[r02]);
        goto L19
    L15:
        if (r22 != 3) goto L17;
        r23 = (zzbn.zzr(r3) << 1) + ((zzey) this.zzmj[r02]).zzas();
        goto L19
    L17:
        if (r22 != 5) goto L20;
        r23 = zzbn.zzj(r3, ((Integer) this.zzmj[r02]).intValue());
        goto L19
    L20:
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(zzco.zzbn());
        return 0;
    L27:
        this.zzjq = r1;
        return r1;
    }

    public final void zzb(int r4, Object r5) {
        if (this.zzfa == false) goto L13;
        int r0 = this.count;
        int[] r1 = this.zzpa;
        if (r0 == r1.length) goto L7;
    L11:
        int[] r02 = this.zzpa;
        int r12 = this.count;
        r02[r12] = r4;
        this.zzmj[r12] = r5;
        this.count = r12 + 1;
        return;
    L7:
        if (r0 >= 4) goto L9;
        int r2 = 8;
    L10:
        int r03 = r0 + r2;
        this.zzpa = Arrays.copyOf(r1, r03);
        this.zzmj = Arrays.copyOf(this.zzmj, r03);
        goto L11
    L9:
        r2 = r0 >> 1;
        goto L10
    L13:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    public final void zzb(zzfr r4) {
        if (this.count != 0) goto L6;
        return;
    L6:
        if (r4.zzaj() != zzcg.zzg.zzko) goto L11;
        int r0 = 0;
    L9:
        if (r0 >= this.count) goto L17;
        zzb(this.zzpa[r0], this.zzmj[r0], r4);
        r0 = r0 + 1;
        goto L9
    L17:
        return;
    L11:
        int r02 = this.count - 1;
    L12:
        if (r02 < 0) goto L18;
        zzb(this.zzpa[r02], this.zzmj[r02], r4);
        r02 = r02 - 1;
        goto L12
    }

    public final int zzec() {
        int r0 = this.zzjq;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        int r02 = 0;
        int r1 = 0;
    L7:
        if (r02 >= this.count) goto L9;
        r1 = r1 + zzbn.zzd(this.zzpa[r02] >>> 3, (zzbb) this.zzmj[r02]);
        r02 = r02 + 1;
        goto L7
    L9:
        this.zzjq = r1;
        return r1;
    }

    public final void zzv() {
        this.zzfa = false;
    }
}
