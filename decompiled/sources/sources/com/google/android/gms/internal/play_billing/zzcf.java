package com.google.android.gms.internal.play_billing;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import java.util.Objects;
import kotlin.UByte;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
final class zzcf extends zzbw {
    static final zzbw zza = null;
    final transient Object[] zzb;
    private final transient Object zzc;
    private final transient int zzd;

    static {
        zza = new zzcf(null, new Object[0], 0);
    }

    private zzcf(Object r1, Object[] r2, int r3) {
        this.zzc = r1;
        this.zzb = r2;
        this.zzd = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v10 */
    /* JADX WARN: Type inference failed for: r16v11 */
    /* JADX WARN: Type inference failed for: r16v12 */
    /* JADX WARN: Type inference failed for: r16v13 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.lang.Object[]] */
    public static zzcf zzg(int r19, Object[] r20, zzbv r21) {
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
        zzbo.zza(r02, r2);
        return new zzcf(null, r1, 1);
    L10:
        zzbg.zzb(r0, r1.length >> 1, "index");
        char r6 = 2;
        int r7 = Math.max(r0, 2);
        if (r7 >= 751619276) goto L16;
        int r8 = Integer.highestOneBit(r7 - 1);
    L13:
        r8 = r8 + r8;
        if ((r8 * 0.7d) < r7) goto L13;
    L18:
        if (r0 != 1) goto L21;
        Object r03 = r1[0];
        Objects.requireNonNull(r03);
        Object r72 = r1[1];
        Objects.requireNonNull(r72);
        zzbo.zza(r03, r72);
        boolean r16 = false;
        r0 = 1;
        int r17 = 1;
    L20:
        char r18 = 2;
        Object r32 = r3;
        ?? r162 = r16;
    L79:
        boolean r42 = r32 instanceof Object[];
        Object r33 = r32;
        if (r42 == false) goto L87;
        Object[] r34 = (Object[]) r32;
        zzbu r04 = (zzbu) r34[r18];
        if (r21 == null) goto L85;
        r21.zzc = r04;
        Object r05 = r34[r162];
        int r22 = ((Integer) r34[r17]).intValue();
        r1 = Arrays.copyOf(r1, r22 + r22);
        r33 = r05;
        r0 = r22;
        goto L87
    L85:
        throw r04.zza();
    L87:
        return new zzcf(r33, r1, r0);
    L21:
        int r73 = r8 - 1;
        if (r8 > 128) goto L42;
        byte[] r82 = new byte[r8];
        Arrays.fill(r82, (byte) -1);
        int r9 = 0;
        int r11 = 0;
    L24:
        if (r9 >= r0) goto L37;
        int r12 = r11 + r11;
        int r13 = r9 + r9;
        Object r14 = r1[r13];
        Objects.requireNonNull(r14);
        Object r132 = r1[r13 ^ r5];
        Objects.requireNonNull(r132);
        zzbo.zza(r14, r132);
        int r15 = zzbp.zza(r14.hashCode());
    L26:
        int r152 = r15 & r73;
        boolean r163 = r4;
        int r172 = r5;
        int r43 = r82[r152] & UByte.MAX_VALUE;
        if (r43 == 255) goto L28;
        if (r14.equals(r1[r43]) == true) goto L34;
        r15 = r152 + 1;
        r4 = r163;
        r5 = r172;
        goto L26
    L34:
        int r35 = r43 ^ 1;
        Object r52 = r1[r35];
        Objects.requireNonNull(r52);
        zzbu r44 = new zzbu(r14, r132, r52);
        r1[r35] = r132;
        r3 = r44;
    L35:
        r9 = r9 + 1;
        r4 = r163;
        r5 = r172;
        goto L24
    L28:
        r82[r152] = (byte) r12;
        if (r11 >= r9) goto L31;
        r1[r12] = r14;
        r1[r12 ^ 1] = r132;
    L31:
        r11 = r11 + 1;
        goto L35
    L37:
        boolean r164 = r4;
        r17 = r5;
        if (r11 != r0) goto L40;
        r18 = 2;
        r32 = r82;
        r162 = r164;
        goto L79
    L40:
        short[] r45 = new Object[3];
        r45[r164 ? 1 : 0] = r82;
        r45[r17] = Integer.valueOf(r11);
        r45[2] = r3;
    L41:
        r3 = r45;
        r16 = r164;
        goto L20
    L42:
        r164 = false;
        r17 = 1;
        if (r8 > 32768) goto L61;
        r45 = new short[r8];
        Arrays.fill(r45, -1);
        int r53 = 0;
        int r83 = 0;
    L45:
        if (r53 >= r0) goto L58;
        int r92 = r83 + r83;
        int r112 = r53 + r53;
        Object r122 = r1[r112];
        Objects.requireNonNull(r122);
        Object r113 = r1[r112 ^ 1];
        Objects.requireNonNull(r113);
        zzbo.zza(r122, r113);
        int r133 = zzbp.zza(r122.hashCode());
    L47:
        int r134 = r133 & r73;
        char r142 = (char) r45[r134];
        if (r142 == 65535) goto L49;
        if (r122.equals(r1[r142]) == true) goto L55;
        r133 = r134 + 1;
        goto L47
    L55:
        int r36 = r142 ^ 1;
        Object r135 = r1[r36];
        Objects.requireNonNull(r135);
        zzbu r93 = new zzbu(r122, r113, r135);
        r1[r36] = r113;
        r3 = r93;
    L56:
        r53 = r53 + 1;
        goto L45
    L49:
        r45[r134] = (short) r92;
        if (r83 >= r53) goto L52;
        r1[r92] = r122;
        r1[r92 ^ 1] = r113;
    L52:
        r83 = r83 + 1;
        goto L56
    L58:
        if (r83 == r0) goto L41;
        r3 = new Object[]{r45, Integer.valueOf(r83), r3};
        r16 = r164;
        goto L20
    L61:
        int[] r46 = new int[r8];
        Arrays.fill(r46, -1);
        int r54 = 0;
        int r84 = 0;
    L62:
        if (r54 >= r0) goto L75;
        int r94 = r84 + r84;
        int r123 = r54 + r54;
        Object r136 = r1[r123];
        Objects.requireNonNull(r136);
        Object r124 = r1[r123 ^ 1];
        Objects.requireNonNull(r124);
        zzbo.zza(r136, r124);
        int r143 = zzbp.zza(r136.hashCode());
    L64:
        int r144 = r143 & r73;
        int r153 = r46[r144];
        if (r153 == (-1)) goto L66;
        char r182 = r6;
        if (r136.equals(r1[r153]) == true) goto L72;
        r143 = r144 + 1;
        r6 = r182;
        goto L64
    L72:
        int r37 = r153 ^ 1;
        Object r95 = r1[r37];
        Objects.requireNonNull(r95);
        zzbu r62 = new zzbu(r136, r124, r95);
        r1[r37] = r124;
        r3 = r62;
    L73:
        r54 = r54 + 1;
        r6 = r182;
        goto L62
    L66:
        r46[r144] = r94;
        if (r84 >= r54) goto L69;
        r1[r94] = r136;
        r1[r94 ^ 1] = r124;
    L69:
        r84 = r84 + 1;
        r182 = r6;
        goto L73
    L75:
        r18 = r6;
        if (r84 != r0) goto L78;
        r32 = r46;
        r162 = r164;
        goto L79
    L78:
        Object[] r55 = new Object[3];
        r55[0] = r46;
        r55[1] = Integer.valueOf(r84);
        r55[r18] = r3;
        r32 = r55;
        r162 = r164;
        goto L79
    L16:
        r8 = TLObject.FLAG_30;
        if (r7 < 1073741824) goto L18;
        f$$ExternalSyntheticBUOutline1.m("collection too large");
        return null;
    L5:
        return (zzcf) zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbw, java.util.Map
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
        int r84 = zzbp.zza(r9.hashCode());
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
        int r86 = zzbp.zza(r9.hashCode());
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
        int r53 = zzbp.zza(r9.hashCode());
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

    @Override // com.google.android.gms.internal.play_billing.zzbw
    public final zzbq zza() {
        int r0 = this.zzd;
        return new zzce(this.zzb, 1, r0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbw
    public final zzbx zzd() {
        int r0 = this.zzd;
        return new zzcc(this, this.zzb, 0, r0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbw
    public final zzbx zze() {
        int r0 = this.zzd;
        return new zzcd(this, new zzce(this.zzb, 0, r0));
    }
}
