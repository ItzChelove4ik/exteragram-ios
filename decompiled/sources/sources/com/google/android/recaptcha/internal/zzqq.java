package com.google.android.recaptcha.internal;

import java.util.Arrays;
import java.util.Objects;
import kotlin.UByte;

/* loaded from: classes5.dex */
final class zzqq extends zzpw {
    static final zzpw zza = null;
    final transient Object[] zzb;
    private final transient Object zzc;
    private final transient int zzd;

    static {
        zza = new zzqq(null, new Object[0], 0);
    }

    private zzqq(Object r1, Object[] r2, int r3) {
        this.zzc = r1;
        this.zzb = r2;
        this.zzd = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v10 */
    /* JADX WARN: Type inference failed for: r16v11 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r16v8 */
    /* JADX WARN: Type inference failed for: r16v9 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.lang.Object[]] */
    public static zzqq zzf(int r19, Object[] r20, zzpv r21) {
        int r0 = r19;
        Object[] r1 = r20;
        if (r0 == 0) goto L5;
        Object r2 = null;
        boolean r3 = false;
        int r4 = 1;
        if (r0 != 1) goto L10;
        Object r02 = r1[0];
        Objects.requireNonNull(r02);
        Object r32 = r1[1];
        Objects.requireNonNull(r32);
        zzpa.zza(r02, r32);
        return new zzqq(null, r1, 1);
    L10:
        zzoq.zzf(r0, r1.length >> 1, "index");
        int r5 = zzqb.zzg(r0);
        if (r0 != 1) goto L13;
        Object r03 = r1[0];
        Objects.requireNonNull(r03);
        Object r52 = r1[1];
        Objects.requireNonNull(r52);
        zzpa.zza(r03, r52);
        ?? r16 = 0;
        r0 = 1;
        int r17 = 1;
    L71:
        boolean r33 = r2 instanceof Object[];
        Object r22 = r2;
        if (r33 == false) goto L75;
        Object[] r23 = (Object[]) r2;
        r21.zzc = (zzpu) r23[2];
        Object r04 = r23[r16];
        int r24 = ((Integer) r23[r17]).intValue();
        r1 = Arrays.copyOf(r1, r24 + r24);
        r22 = r04;
        r0 = r24;
    L75:
        return new zzqq(r22, r1, r0);
    L13:
        int r7 = r5 - 1;
        if (r5 > 128) goto L34;
        byte[] r53 = new byte[r5];
        Arrays.fill(r53, (byte) -1);
        int r8 = 0;
        int r10 = 0;
    L16:
        if (r8 >= r0) goto L29;
        int r11 = r10 + r10;
        int r12 = r8 + r8;
        Object r13 = r1[r12];
        Objects.requireNonNull(r13);
        Object r122 = r1[r12 ^ 1];
        Objects.requireNonNull(r122);
        zzpa.zza(r13, r122);
        int r14 = zzpn.zza(r13.hashCode());
    L18:
        int r142 = r14 & r7;
        boolean r162 = r3;
        int r15 = r53[r142] & UByte.MAX_VALUE;
        if (r15 == 255) goto L20;
        if (r13.equals(r1[r15]) == true) goto L26;
        r14 = r142 + 1;
        r3 = r162;
        goto L18
    L26:
        int r25 = r15 ^ 1;
        Object r112 = r1[r25];
        Objects.requireNonNull(r112);
        zzpu r34 = new zzpu(r13, r122, r112);
        r1[r25] = r122;
        r2 = r34;
    L27:
        r8 = r8 + 1;
        r3 = r162;
        goto L16
    L20:
        r53[r142] = (byte) r11;
        if (r10 >= r8) goto L23;
        r1[r11] = r13;
        r1[r11 ^ 1] = r122;
    L23:
        r10 = r10 + 1;
        goto L27
    L29:
        boolean r163 = r3;
        byte[] r54 = r53;
        boolean r164 = r163;
        if (r10 != r0) goto L32;
    L31:
        r17 = 1;
        r2 = r54;
        r16 = r164;
        goto L71
    L32:
        short[] r35 = new Object[3];
        r35[r163 ? 1 : 0] = r53;
        r35[1] = Integer.valueOf(r10);
        r35[2] = r2;
    L33:
        r2 = r35;
        r17 = 1;
        r16 = r163;
        goto L71
    L34:
        r163 = false;
        if (r5 > 32768) goto L53;
        r35 = new short[r5];
        Arrays.fill(r35, -1);
        int r55 = 0;
        int r82 = 0;
    L37:
        if (r55 >= r0) goto L50;
        int r102 = r82 + r82;
        int r113 = r55 + r55;
        Object r123 = r1[r113];
        Objects.requireNonNull(r123);
        Object r114 = r1[r113 ^ 1];
        Objects.requireNonNull(r114);
        zzpa.zza(r123, r114);
        int r132 = zzpn.zza(r123.hashCode());
    L39:
        int r133 = r132 & r7;
        char r143 = (char) r35[r133];
        if (r143 == 65535) goto L41;
        if (r123.equals(r1[r143]) == true) goto L47;
        r132 = r133 + 1;
        goto L39
    L47:
        int r26 = r143 ^ 1;
        Object r134 = r1[r26];
        Objects.requireNonNull(r134);
        zzpu r103 = new zzpu(r123, r114, r134);
        r1[r26] = r114;
        r2 = r103;
    L48:
        r55 = r55 + 1;
        goto L37
    L41:
        r35[r133] = (short) r102;
        if (r82 >= r55) goto L44;
        r1[r102] = r123;
        r1[r102 ^ 1] = r114;
    L44:
        r82 = r82 + 1;
        goto L48
    L50:
        if (r82 == r0) goto L33;
        r54 = new Object[]{r35, Integer.valueOf(r82), r2};
        r164 = r163;
        goto L31
    L53:
        int[] r36 = new int[r5];
        Arrays.fill(r36, -1);
        int r56 = 0;
        int r83 = 0;
    L54:
        if (r56 >= r0) goto L67;
        int r115 = r83 + r83;
        int r124 = r56 + r56;
        Object r135 = r1[r124];
        Objects.requireNonNull(r135);
        Object r125 = r1[r124 ^ r4];
        Objects.requireNonNull(r125);
        zzpa.zza(r135, r125);
        int r144 = zzpn.zza(r135.hashCode());
    L56:
        int r145 = r144 & r7;
        int r152 = r36[r145];
        if (r152 == (-1)) goto L58;
        int r172 = r4;
        if (r135.equals(r1[r152]) == true) goto L64;
        r144 = r145 + 1;
        r4 = r172;
        goto L56
    L64:
        int r27 = r152 ^ 1;
        Object r116 = r1[r27];
        Objects.requireNonNull(r116);
        zzpu r42 = new zzpu(r135, r125, r116);
        r1[r27] = r125;
        r2 = r42;
    L65:
        r56 = r56 + 1;
        r4 = r172;
        goto L54
    L58:
        r36[r145] = r115;
        if (r83 >= r56) goto L61;
        r1[r115] = r135;
        r1[r115 ^ 1] = r125;
    L61:
        r83 = r83 + 1;
        r172 = r4;
        goto L65
    L67:
        r17 = r4;
        if (r83 != r0) goto L70;
        r2 = r36;
        r16 = r163;
        goto L71
    L70:
        Object[] r43 = new Object[3];
        r43[0] = r36;
        r43[r17] = Integer.valueOf(r83);
        r43[2] = r2;
        r2 = r43;
        r16 = r163;
        goto L71
    L5:
        return (zzqq) zza;
    }

    @Override // com.google.android.recaptcha.internal.zzpw, java.util.Map
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
        int r84 = zzpn.zza(r9.hashCode());
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
        int r86 = zzpn.zza(r9.hashCode());
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
        int r53 = zzpn.zza(r9.hashCode());
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

    @Override // com.google.android.recaptcha.internal.zzpw
    public final zzqb zzb() {
        int r0 = this.zzd;
        return new zzqn(this, this.zzb, 0, r0);
    }

    @Override // com.google.android.recaptcha.internal.zzpw
    public final zzqb zzc() {
        int r0 = this.zzd;
        return new zzqo(this, new zzqp(this.zzb, 0, r0));
    }

    @Override // com.google.android.recaptcha.internal.zzpw
    public final zzpq zze() {
        int r0 = this.zzd;
        return new zzqp(this.zzb, 1, r0);
    }
}
