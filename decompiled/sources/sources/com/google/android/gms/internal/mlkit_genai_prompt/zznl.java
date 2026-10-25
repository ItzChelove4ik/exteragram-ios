package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Arrays;
import java.util.Objects;
import kotlin.UByte;

/* loaded from: classes4.dex */
final class zznl extends zzmo {
    static final zzmo zza = null;
    final transient Object[] zzb;
    private final transient Object zzc;
    private final transient int zzd;

    static {
        zza = new zznl(null, new Object[0], 0);
    }

    private zznl(Object r1, Object[] r2, int r3) {
        this.zzc = r1;
        this.zzb = r2;
        this.zzd = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v11 */
    /* JADX WARN: Type inference failed for: r16v12 */
    /* JADX WARN: Type inference failed for: r16v13 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.lang.Object[]] */
    public static zznl zzj(int r19, Object[] r20, zzmn r21) {
        int r0 = r19;
        Object[] r1 = r20;
        if (r0 == 0) goto L5;
        Object r3 = null;
        boolean r4 = false;
        int r5 = 1;
        if (r0 != 1) goto L10;
        Object r02 = r1[0];
        Objects.requireNonNull(r02);
        Object r2 = r1[1];
        Objects.requireNonNull(r2);
        zzlu.zza(r02, r2);
        return new zznl(null, r1, 1);
    L10:
        zzla.zzg(r0, r1.length >> 1, "index");
        int r6 = zzms.zzh(r0);
        char r7 = 2;
        if (r0 != 1) goto L14;
        Object r03 = r1[0];
        Objects.requireNonNull(r03);
        Object r62 = r1[1];
        Objects.requireNonNull(r62);
        zzlu.zza(r03, r62);
        boolean r16 = false;
        r0 = 1;
        int r17 = 1;
    L13:
        char r18 = 2;
        Object r32 = r3;
        ?? r162 = r16;
    L72:
        boolean r42 = r32 instanceof Object[];
        Object r33 = r32;
        if (r42 == false) goto L80;
        Object[] r34 = (Object[]) r32;
        zzmm r04 = (zzmm) r34[r18];
        if (r21 == null) goto L78;
        r21.zzc = r04;
        Object r05 = r34[r162];
        int r22 = ((Integer) r34[r17]).intValue();
        r1 = Arrays.copyOf(r1, r22 + r22);
        r33 = r05;
        r0 = r22;
        goto L80
    L78:
        throw r04.zza();
    L80:
        return new zznl(r33, r1, r0);
    L14:
        int r8 = r6 - 1;
        if (r6 > 128) goto L35;
        byte[] r63 = new byte[r6];
        Arrays.fill(r63, (byte) -1);
        int r9 = 0;
        int r11 = 0;
    L17:
        if (r9 >= r0) goto L30;
        int r12 = r11 + r11;
        int r13 = r9 + r9;
        Object r14 = r1[r13];
        Objects.requireNonNull(r14);
        Object r132 = r1[r13 ^ r5];
        Objects.requireNonNull(r132);
        zzlu.zza(r14, r132);
        int r15 = zzmd.zza(r14.hashCode());
    L19:
        int r152 = r15 & r8;
        boolean r163 = r4;
        int r172 = r5;
        int r43 = r63[r152] & UByte.MAX_VALUE;
        if (r43 == 255) goto L21;
        if (r14.equals(r1[r43]) == true) goto L27;
        r15 = r152 + 1;
        r4 = r163;
        r5 = r172;
        goto L19
    L27:
        int r35 = r43 ^ 1;
        Object r52 = r1[r35];
        Objects.requireNonNull(r52);
        zzmm r44 = new zzmm(r14, r132, r52);
        r1[r35] = r132;
        r3 = r44;
    L28:
        r9 = r9 + 1;
        r4 = r163;
        r5 = r172;
        goto L17
    L21:
        r63[r152] = (byte) r12;
        if (r11 >= r9) goto L24;
        r1[r12] = r14;
        r1[r12 ^ 1] = r132;
    L24:
        r11 = r11 + 1;
        goto L28
    L30:
        boolean r164 = r4;
        r17 = r5;
        if (r11 != r0) goto L33;
        r3 = r63;
        r16 = r164;
        goto L13
    L33:
        short[] r45 = new Object[3];
        r45[r164 ? 1 : 0] = r63;
        r45[r17] = Integer.valueOf(r11);
        r45[2] = r3;
    L34:
        r3 = r45;
        r16 = r164;
        goto L13
    L35:
        r164 = false;
        r17 = 1;
        if (r6 > 32768) goto L54;
        r45 = new short[r6];
        Arrays.fill(r45, -1);
        int r53 = 0;
        int r64 = 0;
    L38:
        if (r53 >= r0) goto L51;
        int r92 = r64 + r64;
        int r112 = r53 + r53;
        Object r122 = r1[r112];
        Objects.requireNonNull(r122);
        Object r113 = r1[r112 ^ 1];
        Objects.requireNonNull(r113);
        zzlu.zza(r122, r113);
        int r133 = zzmd.zza(r122.hashCode());
    L40:
        int r134 = r133 & r8;
        char r142 = (char) r45[r134];
        if (r142 == 65535) goto L42;
        if (r122.equals(r1[r142]) == true) goto L48;
        r133 = r134 + 1;
        goto L40
    L48:
        int r36 = r142 ^ 1;
        Object r135 = r1[r36];
        Objects.requireNonNull(r135);
        zzmm r93 = new zzmm(r122, r113, r135);
        r1[r36] = r113;
        r3 = r93;
    L49:
        r53 = r53 + 1;
        goto L38
    L42:
        r45[r134] = (short) r92;
        if (r64 >= r53) goto L45;
        r1[r92] = r122;
        r1[r92 ^ 1] = r113;
    L45:
        r64 = r64 + 1;
        goto L49
    L51:
        if (r64 == r0) goto L34;
        r3 = new Object[]{r45, Integer.valueOf(r64), r3};
        r16 = r164;
        goto L13
    L54:
        int[] r46 = new int[r6];
        Arrays.fill(r46, -1);
        int r54 = 0;
        int r65 = 0;
    L55:
        if (r54 >= r0) goto L68;
        int r94 = r65 + r65;
        int r123 = r54 + r54;
        Object r136 = r1[r123];
        Objects.requireNonNull(r136);
        Object r124 = r1[r123 ^ 1];
        Objects.requireNonNull(r124);
        zzlu.zza(r136, r124);
        int r143 = zzmd.zza(r136.hashCode());
    L57:
        int r144 = r143 & r8;
        int r153 = r46[r144];
        if (r153 == (-1)) goto L59;
        char r182 = r7;
        if (r136.equals(r1[r153]) == true) goto L65;
        r143 = r144 + 1;
        r7 = r182;
        goto L57
    L65:
        int r37 = r153 ^ 1;
        Object r95 = r1[r37];
        Objects.requireNonNull(r95);
        zzmm r72 = new zzmm(r136, r124, r95);
        r1[r37] = r124;
        r3 = r72;
    L66:
        r54 = r54 + 1;
        r7 = r182;
        goto L55
    L59:
        r46[r144] = r94;
        if (r65 >= r54) goto L62;
        r1[r94] = r136;
        r1[r94 ^ 1] = r124;
    L62:
        r65 = r65 + 1;
        r182 = r7;
        goto L66
    L68:
        r18 = r7;
        if (r65 != r0) goto L71;
        r32 = r46;
        r162 = r164;
        goto L72
    L71:
        Object[] r55 = new Object[3];
        r55[0] = r46;
        r55[1] = Integer.valueOf(r65);
        r55[r18] = r3;
        r32 = r55;
        r162 = r164;
        goto L72
    L5:
        return (zznl) zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmo, java.util.Map
    public final Object get(Object r9) {
        if (r9 != null) goto L5;
    L4:
        Object r8 = null;
    L40:
        if (r8 != null) goto L42;
        return null;
    L42:
        return r8;
    L5:
        int r1 = this.zzd;
        Object[] r2 = this.zzb;
        if (r1 != 1) goto L10;
        Object r82 = r2[0];
        Objects.requireNonNull(r82);
        if (r82.equals(r9) == false) goto L4;
        r8 = r2[1];
        Objects.requireNonNull(r8);
        goto L40
    L10:
        Object r83 = this.zzc;
        if (r83 == null) goto L4;
        if ((r83 instanceof byte[]) == false) goto L24;
        byte[] r12 = (byte[]) r83;
        int r5 = r12.length - 1;
        int r84 = zzmd.zza(r9.hashCode());
    L16:
        int r85 = r84 & r5;
        int r4 = r12[r85] & UByte.MAX_VALUE;
        if (r4 == 255) goto L4;
        if (r9.equals(r2[r4]) == true) goto L21;
        r84 = r85 + 1;
        goto L16
    L21:
        r8 = r2[r4 ^ 1];
        goto L40
    L24:
        if ((r83 instanceof short[]) == false) goto L33;
        short[] r13 = (short[]) r83;
        int r52 = r13.length - 1;
        int r86 = zzmd.zza(r9.hashCode());
    L26:
        int r87 = r86 & r52;
        char r42 = (char) r13[r87];
        if (r42 == 65535) goto L4;
        if (r9.equals(r2[r42]) == true) goto L31;
        r86 = r87 + 1;
        goto L26
    L31:
        r8 = r2[r42 ^ 1];
        goto L40
    L33:
        int[] r88 = (int[]) r83;
        int r14 = r88.length - 1;
        int r53 = zzmd.zza(r9.hashCode());
    L34:
        int r54 = r53 & r14;
        int r6 = r88[r54];
        if (r6 == (-1)) goto L4;
        if (r9.equals(r2[r6]) == true) goto L39;
        r53 = r54 + 1;
        goto L34
    L39:
        r8 = r2[r6 ^ 1];
        goto L40
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmo
    public final zzms zzf() {
        int r0 = this.zzd;
        return new zzni(this, this.zzb, 0, r0);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmo
    public final zzms zzg() {
        int r0 = this.zzd;
        return new zznj(this, new zznk(this.zzb, 0, r0));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmo
    public final zzmg zzi() {
        int r0 = this.zzd;
        return new zznk(this.zzb, 1, r0);
    }
}
