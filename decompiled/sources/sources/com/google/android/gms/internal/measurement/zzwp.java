package com.google.android.gms.internal.measurement;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
final class zzwp {
    private final int[] zza;
    private final zzwn zzb;
    private zzwn zzc;
    private int zzd;
    private int zze;
    private int zzf;

    private zzwp(int[] r3) {
        this.zza = r3;
        zzwn r32 = new zzwn(-1, -1, null);
        this.zzb = r32;
        this.zzc = r32;
    }

    public static zzwp zza(int[] r12) {
        zzwp r0 = new zzwp(r12);
        int r1 = 0;
    L4:
        if (r1 >= r12.length) goto L30;
        r0.zzf++;
        int[] r2 = r0.zza;
        int r3 = r2[r1];
    L6:
        zzwn r5 = null;
    L8:
        if (r0.zzf <= 0) goto L29;
        int r6 = r0.zze;
        zzwn r7 = r0.zzc;
        if (r6 == 0) goto L11;
        int r62 = ((zzwn) r7.zzd.get(Integer.valueOf(r2[r0.zzd]))).zza;
        int r72 = r0.zze;
        if (r2[r62 + r72] == r3) goto L22;
        zzwn r63 = (zzwn) r0.zzc.zzd.get(Integer.valueOf(r2[r0.zzd]));
        zzwn r73 = new zzwn(r63.zza, (r0.zze + r9) - 1, null);
        r0.zzc.zzd.put(Integer.valueOf(r2[r0.zzd]), r73);
        Map r9 = r73.zzd;
        int r10 = r73.zzb + 1;
        r9.put(Integer.valueOf(r2[r10]), r63);
        r63.zza = r10;
        if (r5 == null) goto L28;
        r5.zzc = r73;
    L28:
        r9.put(Integer.valueOf(r3), new zzwn(r1, TLObject.FLAG_30, null));
        r0.zzf--;
        r0.zzc();
        r5 = r73;
        goto L8
    L22:
        if (r5 == null) goto L24;
        r5.zzc = r0.zzc;
    L24:
        r0.zze = r72 + 1;
        r0.zzb();
        goto L29
    L11:
        Map r64 = r7.zzd;
        Integer r74 = Integer.valueOf(r3);
        if (r64.containsKey(r74) == true) goto L17;
        r0.zzc.zzd.put(r74, new zzwn(r1, TLObject.FLAG_30, null));
        if (r5 == null) goto L16;
        r5.zzc = r0.zzc;
    L16:
        r0.zzf--;
        r0.zzc();
        goto L6
    L17:
        if (r5 == null) goto L19;
        r5.zzc = r0.zzc;
    L19:
        r0.zzd = r1;
        r0.zze++;
        r0.zzb();
    L29:
        r1 = r1 + 1;
        goto L4
    L30:
        return r0;
    }

    private final void zze(zzwn r7, StringBuilder r8) {
        Iterator r0 = r7.zzd.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        zzwn r1 = (zzwn) r0.next();
        r8.append("  ");
        r8.append(r7);
        r8.append(" -> ");
        r8.append(r1);
        r8.append(" [label=\"");
        int[] r2 = this.zza;
        int r3 = r1.zza;
        int r4 = r1.zzb + 1;
        r8.append(Arrays.toString(Arrays.copyOfRange(r2, r3, Math.min(r2.length, r4))));
        r8.append("\"]\n");
        zze(r1, r8);
        goto L4
    }

    private final boolean zzf(int r4, int r5, int r6, int r7) {
        if (r4 < 0) goto L16;
        if (r6 < 0) goto L16;
        int[] r3 = this.zza;
        int r1 = r3.length;
        int r52 = Math.min(r1, r5);
        int r72 = Math.min(r1, r7);
        if ((r52 - r4) != (r72 - r6)) goto L16;
        int r73 = r4;
    L9:
        if (r73 > r52) goto L14;
        if (r3[r73] != r3[(r6 + r73) - r4]) goto L12;
        r73 = r73 + 1;
        goto L9
    L12:
        return false;
    L14:
        return true;
    L16:
        return false;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("digraph {\n");
        zze(this.zzb, r0);
        r0.append("}");
        return r0.toString();
    }

    public final void zzb() {
        if (this.zze == 0) goto L11;
        Map r0 = this.zzc.zzd;
        int[] r1 = this.zza;
        zzwn r02 = (zzwn) r0.get(Integer.valueOf(r1[this.zzd]));
    L6:
        int r2 = (r02.zzb - r02.zza) + 1;
        int r3 = this.zze;
        if (r2 > r3) goto L17;
        int r4 = this.zzd + r2;
        this.zzd = r4;
        this.zzc = r02;
        int r32 = r3 - r2;
        this.zze = r32;
        if (r32 <= 0) goto L6;
        r02 = (zzwn) r02.zzd.get(Integer.valueOf(r1[r4]));
        goto L6
    L17:
        return;
    }

    public final void zzc() {
        zzwn r0 = this.zzc.zzc;
        if (r0 == null) goto L5;
        this.zzc = r0;
    L11:
        zzb();
        return;
    L5:
        this.zzc = this.zzb;
        int r02 = this.zze;
        if (r02 <= 0) goto L9;
        this.zze = r02 - 1;
    L9:
        if (this.zzf <= 0) goto L11;
        this.zzd++;
        goto L11
    }

    public final zzwo zzd() {
        ArrayDeque r0 = new ArrayDeque();
        zzwn r2 = this.zzb;
        zzwm r1 = new zzwm(r2, 0, -1, -1, null);
        r0.push(r1);
    L4:
        if (r0.isEmpty() == true) goto L21;
        zzwm r3 = (zzwm) r0.pop();
        Iterator r4 = r3.zzd.zzd.values().iterator();
    L7:
        if (r4.hasNext() == false) goto L4;
        zzwn r7 = (zzwn) r4.next();
        int r9 = r3.zzb;
        int r10 = r3.zzc;
        int r5 = r7.zza;
        int r102 = r7.zzb;
        if (zzf(r9, r10, r5, r102) == true) goto L16;
        if (r7.zzd.isEmpty() == false) goto L15;
        int r52 = r7.zza;
        if (zzf(r9, r10, r52, (r52 + r10) - r9) == true) goto L16;
    L15:
        zzwm r6 = new zzwm(r7, 1, r7.zza, r102, null);
    L18:
        if (r1.zza >= r6.zza) goto L20;
        r1 = r6;
    L20:
        r0.push(r6);
    L16:
        r6 = new zzwm(r7, r3.zza + 1, r9, r10, null);
        goto L18
    L21:
        int[] r12 = this.zza;
        int r02 = r1.zzc + 1;
        int r03 = Math.min(r12.length, r02);
        int r32 = 0;
    L22:
        int r42 = r1.zzb;
        int r53 = r03 - r42;
        int r62 = r12[(r32 % r53) + r42];
        r2 = (zzwn) r2.zzd.get(Integer.valueOf(r62));
        if (r2 == null) goto L34;
        int r63 = r2.zza;
    L27:
        if (r63 >= (r2.zzb + 1)) goto L22;
        if (r63 >= r12.length) goto L22;
        if (r12[(r32 % r53) + r42] != r12[r63]) goto L34;
        r32 = r32 + 1;
        r63 = r63 + 1;
    L34:
        return new zzwo(r42, r03, r32 / r53);
    }
}
