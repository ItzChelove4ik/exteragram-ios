package com.google.android.gms.internal.play_billing;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline0;
import sun.misc.Unsafe;

/* loaded from: classes5.dex */
final class zzgo<T> implements zzgv<T> {
    private static final int[] zza = null;
    private static final Unsafe zzb = null;
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzgl zzg;
    private final boolean zzh;
    private final int[] zzi;
    private final int zzj;
    private final int zzk;
    private final zzhh zzl;
    private final zzev zzm;

    static {
        zza = new int[0];
        zzb = zzho.zzg();
    }

    private zzgo(int[] r1, Object[] r2, int r3, int r4, zzgl r5, boolean r6, int[] r7, int r8, int r9, zzgq r10, zzfy r11, zzhh r12, zzev r13, zzgg r14) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzh = false;
        this.zzi = r7;
        this.zzj = r8;
        this.zzk = r9;
        this.zzl = r12;
        this.zzm = r13;
        this.zzg = r5;
    }

    private static void zzA(Object r1) {
        if (zzL(r1) == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Mutating immutable message: ".concat(String.valueOf(r1)));
    }

    private final void zzB(Object r6, Object r7, int r8) {
        if (zzI(r7, r8) == true) goto L5;
        return;
    L5:
        int r0 = zzs(r8) & 1048575;
        Unsafe r1 = zzb;
        long r2 = r0;
        Object r02 = r1.getObject(r7, r2);
        if (r02 == null) goto L21;
        zzgv r72 = zzv(r8);
        if (zzI(r6, r8) == false) goto L10;
        Object r5 = r1.getObject(r6, r2);
        if (zzL(r5) == true) goto L18;
        Object r82 = r72.zze();
        r72.zzg(r82, r5);
        r1.putObject(r6, r2, r82);
        r5 = r82;
    L18:
        r72.zzg(r5, r02);
        return;
    L10:
        if (zzL(r02) == true) goto L12;
        r1.putObject(r6, r2, r02);
    L13:
        zzD(r6, r8);
        return;
    L12:
        Object r4 = r72.zze();
        r72.zzg(r4, r02);
        r1.putObject(r6, r2, r4);
        goto L13
    L21:
        throw new IllegalStateException("Source subfield " + this.zzc[r8] + " is present but null: " + r7.toString());
    }

    private final void zzC(Object r7, Object r8, int r9) {
        int[] r0 = this.zzc;
        int r1 = r0[r9];
        if (zzM(r8, r1, r9) == true) goto L5;
        return;
    L5:
        int r2 = zzs(r9) & 1048575;
        Unsafe r3 = zzb;
        long r4 = r2;
        Object r22 = r3.getObject(r8, r4);
        if (r22 == null) goto L21;
        zzgv r82 = zzv(r9);
        if (zzM(r7, r1, r9) == false) goto L10;
        Object r6 = r3.getObject(r7, r4);
        if (zzL(r6) == true) goto L18;
        Object r92 = r82.zze();
        r82.zzg(r92, r6);
        r3.putObject(r7, r4, r92);
        r6 = r92;
    L18:
        r82.zzg(r6, r22);
        return;
    L10:
        if (zzL(r22) == true) goto L12;
        r3.putObject(r7, r4, r22);
    L13:
        zzE(r7, r1, r9);
        return;
    L12:
        Object r02 = r82.zze();
        r82.zzg(r02, r22);
        r3.putObject(r7, r4, r02);
        goto L13
    L21:
        throw new IllegalStateException("Source subfield " + r0[r9] + " is present but null: " + r8.toString());
    }

    private final void zzD(Object r5, int r6) {
        int r4 = zzp(r6);
        long r0 = 1048575 & r4;
        if (r0 != 1048575) goto L5;
        return;
    L5:
        zzho.zzq(r5, r0, (1 << (r4 >>> 20)) | zzho.zzc(r5, r0));
    }

    private final void zzE(Object r3, int r4, int r5) {
        zzho.zzq(r3, zzp(r5) & 1048575, r4);
    }

    private final void zzF(Object r4, int r5, Object r6) {
        zzb.putObject(r4, zzs(r5) & 1048575, r6);
        zzD(r4, r5);
    }

    private final void zzG(Object r4, int r5, int r6, Object r7) {
        zzb.putObject(r4, zzs(r6) & 1048575, r7);
        zzE(r4, r5, r6);
    }

    private final boolean zzH(Object r1, Object r2, int r3) {
        if (zzI(r1, r3) != zzI(r2, r3)) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean zzI(Object r8, int r9) {
        int r0 = zzp(r9);
        long r2 = r0 & 1048575;
        if (r2 != 1048575) goto L89;
        int r7 = zzs(r9);
        long r02 = r7 & 1048575;
        switch(zzr(r7)) {
            case 0: goto L85;
            case 1: goto L81;
            case 2: goto L77;
            case 3: goto L73;
            case 4: goto L69;
            case 5: goto L65;
            case 6: goto L61;
            case 7: goto L59;
            case 8: goto L44;
            case 9: goto L41;
            case 10: goto L37;
            case 11: goto L33;
            case 12: goto L29;
            case 13: goto L25;
            case 14: goto L21;
            case 15: goto L17;
            case 16: goto L13;
            case 17: goto L9;
            default: goto L6;
        };
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        return false;
    L44:
        Object r72 = zzho.zzf(r8, r02);
        if ((r72 instanceof String) == false) goto L51;
        if (((String) r72).isEmpty() == true) goto L49;
        return true;
    L49:
        return false;
    L51:
        if ((r72 instanceof zzei) == true) goto L53;
        Segment$$ExternalSyntheticBUOutline0.m();
        return false;
    L53:
        if (zzei.zzb.equals(r72) == true) goto L55;
        return true;
    L55:
        return false;
    L9:
        if (zzho.zzf(r8, r02) == null) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (zzho.zzd(r8, r02) == 0) goto L15;
        return true;
    L15:
        return false;
    L17:
        if (zzho.zzc(r8, r02) == 0) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (zzho.zzd(r8, r02) == 0) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (zzho.zzc(r8, r02) == 0) goto L27;
        return true;
    L27:
        return false;
    L29:
        if (zzho.zzc(r8, r02) == 0) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (zzho.zzc(r8, r02) == 0) goto L35;
        return true;
    L35:
        return false;
    L37:
        if (zzei.zzb.equals(zzho.zzf(r8, r02)) == true) goto L39;
        return true;
    L39:
        return false;
    L41:
        if (zzho.zzf(r8, r02) == null) goto L43;
        return true;
    L43:
        return false;
    L59:
        return zzho.zzw(r8, r02);
    L61:
        if (zzho.zzc(r8, r02) == 0) goto L63;
        return true;
    L63:
        return false;
    L65:
        if (zzho.zzd(r8, r02) == 0) goto L67;
        return true;
    L67:
        return false;
    L69:
        if (zzho.zzc(r8, r02) == 0) goto L71;
        return true;
    L71:
        return false;
    L73:
        if (zzho.zzd(r8, r02) == 0) goto L75;
        return true;
    L75:
        return false;
    L77:
        if (zzho.zzd(r8, r02) == 0) goto L79;
        return true;
    L79:
        return false;
    L81:
        if (Float.floatToRawIntBits(zzho.zzb(r8, r02)) == 0) goto L83;
        return true;
    L83:
        return false;
    L85:
        if (Double.doubleToRawLongBits(zzho.zza(r8, r02)) == 0) goto L87;
        return true;
    L87:
        return false;
    L89:
        if (((1 << (r0 >>> 20)) & zzho.zzc(r8, r2)) == 0) goto L91;
        return true;
    L91:
        return false;
    }

    private final boolean zzJ(Object r2, int r3, int r4, int r5, int r6) {
        if (r4 != 1048575) goto L7;
        return zzI(r2, r3);
    L7:
        if ((r5 & r6) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean zzK(Object r2, int r3, zzgv r4) {
        return r4.zzk(zzho.zzf(r2, r3 & 1048575));
    }

    private static boolean zzL(Object r1) {
        if (r1 != null) goto L6;
        return false;
    L6:
        if ((r1 instanceof zzfi) == true) goto L8;
        return true;
    L8:
        return ((zzfi) r1).zzz();
    }

    private final boolean zzM(Object r3, int r4, int r5) {
        if (zzho.zzc(r3, zzp(r5) & 1048575) != r4) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean zzN(Object r0, long r1) {
        return ((Boolean) zzho.zzf(r0, r1)).booleanValue();
    }

    private static final void zzO(int r1, Object r2, zzhu r3) {
        if ((r2 instanceof String) == false) goto L6;
        r3.zzG(r1, (String) r2);
        return;
    L6:
        r3.zzd(r1, (zzei) r2);
    }

    public static zzhi zzd(Object r2) {
        zzfi r22 = (zzfi) r2;
        zzhi r0 = r22.zzc;
        if (r0 != zzhi.zzc()) goto L6;
        zzhi r02 = zzhi.zzf();
        r22.zzc = r02;
        return r02;
    L6:
        return r0;
    }

    public static zzgo zzl(Class r32, zzgi r33, zzgq r34, zzfy r35, zzhh r36, zzev r37, zzgg r38) {
        if ((r33 instanceof zzgu) == false) goto L208;
        zzgu r0 = (zzgu) r33;
        String r1 = r0.zzd();
        int r2 = r1.length();
        char r5 = 55296;
        if (r1.charAt(0) < 55296) goto L10;
        int r4 = 1;
    L7:
        int r7 = r4 + 1;
        if (r1.charAt(r4) < 55296) goto L11;
        r4 = r7;
    L11:
        int r42 = r7 + 1;
        int r72 = r1.charAt(r7);
        if (r72 < 55296) goto L18;
        int r73 = r72 & 8191;
        int r9 = 13;
    L14:
        int r10 = r42 + 1;
        char r43 = r1.charAt(r42);
        if (r43 < 55296) goto L17;
        r73 = r73 | ((r43 & 8191) << r9);
        r9 = r9 + 13;
        r42 = r10;
        goto L14
    L17:
        r72 = r73 | (r43 << r9);
        r42 = r10;
    L18:
        if (r72 != 0) goto L20;
        int r92 = 0;
        int r102 = 0;
        int r11 = 0;
        int r12 = 0;
        int r13 = 0;
        int r17 = 0;
        int[] r16 = zza;
        int r74 = 0;
    L77:
        Unsafe r14 = zzb;
        Object[] r15 = r0.zze();
        Class<?> r3 = r0.zza().getClass();
        int r18 = r17 + r92;
        int r93 = r11 + r11;
        int[] r112 = new int[r11 * 3];
        Object[] r94 = new Object[r93];
        int r21 = r17;
        int r22 = r18;
        int r19 = 0;
        int r20 = 0;
    L78:
        if (r42 >= r2) goto L207;
        int r23 = r42 + 1;
        int r44 = r1.charAt(r42);
        if (r44 < r5) goto L86;
        int r45 = r44 & 8191;
        int r8 = r23;
        int r232 = 13;
    L82:
        int r24 = r8 + 1;
        char r82 = r1.charAt(r8);
        if (r82 < r5) goto L85;
        r45 = r45 | ((r82 & 8191) << r232);
        r232 = r232 + 13;
        r8 = r24;
        goto L82
    L85:
        r44 = r45 | (r82 << r232);
        int r83 = r24;
    L87:
        int r233 = r83 + 1;
        int r84 = r1.charAt(r83);
        if (r84 < r5) goto L94;
        int r85 = r84 & 8191;
        int r6 = r233;
        int r234 = 13;
    L90:
        int r25 = r6 + 1;
        char r62 = r1.charAt(r6);
        if (r62 < r5) goto L93;
        r85 = r85 | ((r62 & 8191) << r234);
        r234 = r234 + 13;
        r6 = r25;
        goto L90
    L93:
        r84 = r85 | (r62 << r234);
        int r63 = r25;
    L96:
        if ((r84 & 1024) == 0) goto L98;
        r16[r19] = r20;
        r19 = r19 + 1;
    L98:
        int r52 = r84 & 255;
        zzgu r252 = r0;
        int r26 = r84 & 2048;
        if (r52 < 51) goto L134;
        int r02 = r63 + 1;
        int r64 = r1.charAt(r63);
        char r03 = 55296;
        if (r64 < 55296) goto L107;
        int r27 = r64 & 8191;
        int r65 = r02;
        int r30 = 13;
    L103:
        int r31 = r65 + 1;
        char r66 = r1.charAt(r65);
        if (r66 < r03) goto L106;
        r27 = r27 | ((r66 & 8191) << r30);
        r30 = r30 + 13;
        r65 = r31;
        r03 = 55296;
        goto L103
    L106:
        r64 = r27 | (r66 << r30);
        int r04 = r31;
    L108:
        int r272 = r04;
        int r05 = r52 - 51;
        int r302 = r2;
        if (r05 == 9) goto L123;
        if (r05 == 17) goto L123;
        if (r05 == 12) goto L116;
    L122:
        int r06 = r26;
    L124:
        int r67 = r64 + r64;
        Object r28 = r15[r67];
        int r262 = r06;
        if ((r28 instanceof Field) == false) goto L128;
        Field r29 = (Field) r28;
    L127:
        int r07 = r74;
        int r210 = (int) r14.objectFieldOffset(r29);
        int r68 = r67 + 1;
        Object r75 = r15[r68];
        int r312 = r07;
        if ((r75 instanceof Field) == false) goto L132;
        Field r76 = (Field) r75;
    L133:
        int r282 = (int) r14.objectFieldOffset(r76);
        String r77 = r1;
        int r08 = r262;
        r63 = r272;
        int r110 = 0;
        char r235 = 55296;
    L194:
        int r263 = r20 + 1;
        r112[r20] = r44;
        int r46 = r20 + 2;
        int r273 = r08;
        if ((r84 & 512) == 0) goto L197;
        int r09 = 536870912;
    L199:
        if ((r84 & 256) == 0) goto L201;
        int r86 = 268435456;
    L202:
        if (r273 == 0) goto L204;
        int r274 = Integer.MIN_VALUE;
    L205:
        r112[r263] = (((r09 | r86) | r274) | (r52 << 20)) | r210;
        r20 = r20 + 3;
        r112[r46] = (r110 << 20) | r282;
        r42 = r63;
        r1 = r77;
        r5 = r235;
        r0 = r252;
        r2 = r302;
        r74 = r312;
        goto L78
    L204:
        r274 = 0;
        goto L205
    L201:
        r86 = 0;
        goto L202
    L197:
        r09 = 0;
        goto L199
    L132:
        r76 = zzz(r3, (String) r75);
        r15[r68] = r76;
        goto L133
    L128:
        r29 = zzz(r3, (String) r28);
        r15[r67] = r29;
        goto L127
    L116:
        if (r252.zzc() == 1) goto L120;
        if (r26 != 0) goto L120;
        r06 = 0;
    L120:
        int r010 = r102 + 1;
        int r242 = r20 / 3;
        r94[(r242 + r242) + 1] = r15[r102];
    L121:
        r102 = r010;
    L123:
        r010 = r102 + 1;
        int r243 = r20 / 3;
        r94[(r243 + r243) + 1] = r15[r102];
        goto L121
    L107:
        r04 = r02;
        goto L108
    L134:
        r302 = r2;
        r312 = r74;
        int r011 = r102 + 1;
        Field r211 = zzz(r3, (String) r15[r102]);
        if (r52 != 9) goto L137;
    L138:
        int r283 = r011;
        int r78 = r20 / 3;
        r94[(r78 + r78) + 1] = r211.getType();
    L168:
        r77 = r1;
        r102 = r283;
    L169:
        r210 = (int) r14.objectFieldOffset(r211);
        if ((r84 & 4096) != 0) goto L172;
    L187:
        r235 = 55296;
        r282 = 1048575;
        r110 = 0;
    L189:
        if (r52 >= 18) goto L191;
    L193:
        r08 = r26;
        goto L194
    L191:
        if (r52 > 49) goto L193;
        r16[r22] = r210;
        r22 = r22 + 1;
        goto L193
    L172:
        if (r52 > 17) goto L187;
        int r012 = r63 + 1;
        int r111 = r77.charAt(r63);
        if (r111 < 55296) goto L180;
        int r113 = r111 & 8191;
        int r236 = 13;
    L176:
        int r284 = r012 + 1;
        char r013 = r77.charAt(r012);
        if (r013 < 55296) goto L179;
        r113 = r113 | ((r013 & 8191) << r236);
        r236 = r236 + 13;
        r012 = r284;
        goto L176
    L179:
        r111 = r113 | (r013 << r236);
    L181:
        int r014 = (r312 + r312) + (r111 / 32);
        Object r69 = r15[r014];
        if ((r69 instanceof Field) == false) goto L185;
        Field r610 = (Field) r69;
    L184:
        int r292 = r111;
        int r015 = (int) r14.objectFieldOffset(r610);
        r110 = r292 % 32;
        r63 = r284;
        r235 = 55296;
        r282 = r015;
        goto L189
    L185:
        r610 = zzz(r3, (String) r69);
        r15[r014] = r610;
        goto L184
    L180:
        r284 = r012;
        goto L181
    L137:
        if (r52 == 17) goto L138;
        if (r52 != 27) goto L142;
        int r285 = r011;
        int r016 = 1;
        r102 = r102 + 2;
    L166:
        int r79 = r20 / 3;
        r94[(r79 + r79) + r016] = r15[r285];
    L157:
        r77 = r1;
        goto L169
    L142:
        if (r52 != 49) goto L145;
        r102 = r102 + 2;
        r285 = r011;
        r016 = 1;
        goto L166
    L145:
        if (r52 == 12) goto L160;
        if (r52 == 30) goto L160;
        if (r52 == 44) goto L160;
        if (r52 != 50) goto L158;
        int r710 = r102 + 2;
        int r286 = r21 + 1;
        r16[r21] = r20;
        int r212 = r20 / 3;
        int r213 = r212 + r212;
        r94[r213] = r15[r011];
        if (r26 == 0) goto L156;
        r94[r213 + 1] = r15[r710];
        r102 = r102 + 3;
        r77 = r1;
        r21 = r286;
        goto L169
    L156:
        r102 = r710;
        r21 = r286;
        r26 = 0;
        goto L157
    L158:
        r283 = r011;
    L160:
        if (r252.zzc() == 1) goto L164;
        if (r26 != 0) goto L164;
        r77 = r1;
        r102 = r011;
        r26 = 0;
    L164:
        r102 = r102 + 2;
        int r711 = r20 / 3;
        r94[(r711 + r711) + 1] = r15[r011];
        goto L157
    L94:
        r63 = r233;
        goto L96
    L86:
        r83 = r23;
        goto L87
    L207:
        return new zzgo(r112, r94, r12, r13, r0.zza(), false, r16, r17, r18, r34, r35, r36, r37, r38);
    L20:
        int r712 = r42 + 1;
        int r47 = r1.charAt(r42);
        if (r47 < 55296) goto L27;
        int r48 = r47 & 8191;
        int r95 = 13;
    L23:
        int r103 = r712 + 1;
        char r713 = r1.charAt(r712);
        if (r713 < 55296) goto L26;
        r48 = r48 | ((r713 & 8191) << r95);
        r95 = r95 + 13;
        r712 = r103;
        goto L23
    L26:
        r47 = r48 | (r713 << r95);
        r712 = r103;
    L27:
        int r96 = r712 + 1;
        int r714 = r1.charAt(r712);
        if (r714 < 55296) goto L34;
        int r715 = r714 & 8191;
        int r104 = 13;
    L30:
        int r114 = r96 + 1;
        char r97 = r1.charAt(r96);
        if (r97 < 55296) goto L33;
        r715 = r715 | ((r97 & 8191) << r104);
        r104 = r104 + 13;
        r96 = r114;
        goto L30
    L33:
        r714 = r715 | (r97 << r104);
        r96 = r114;
    L34:
        int r105 = r96 + 1;
        int r98 = r1.charAt(r96);
        if (r98 < 55296) goto L41;
        int r99 = r98 & 8191;
        int r115 = 13;
    L37:
        int r122 = r105 + 1;
        char r106 = r1.charAt(r105);
        if (r106 < 55296) goto L40;
        r99 = r99 | ((r106 & 8191) << r115);
        r115 = r115 + 13;
        r105 = r122;
        goto L37
    L40:
        r98 = r99 | (r106 << r115);
        r105 = r122;
    L41:
        int r116 = r105 + 1;
        int r107 = r1.charAt(r105);
        if (r107 < 55296) goto L48;
        int r108 = r107 & 8191;
        int r123 = 13;
    L44:
        int r132 = r116 + 1;
        char r117 = r1.charAt(r116);
        if (r117 < 55296) goto L47;
        r108 = r108 | ((r117 & 8191) << r123);
        r123 = r123 + 13;
        r116 = r132;
        goto L44
    L47:
        r107 = r108 | (r117 << r123);
        r116 = r132;
    L48:
        int r124 = r116 + 1;
        r11 = r1.charAt(r116);
        if (r11 < 55296) goto L55;
        int r118 = r11 & 8191;
        int r133 = 13;
    L51:
        int r142 = r124 + 1;
        char r125 = r1.charAt(r124);
        if (r125 < 55296) goto L54;
        r118 = r118 | ((r125 & 8191) << r133);
        r133 = r133 + 13;
        r124 = r142;
        goto L51
    L54:
        r11 = r118 | (r125 << r133);
        r124 = r142;
    L55:
        int r134 = r124 + 1;
        int r126 = r1.charAt(r124);
        if (r126 < 55296) goto L62;
        int r127 = r126 & 8191;
        int r143 = 13;
    L58:
        int r152 = r134 + 1;
        char r135 = r1.charAt(r134);
        if (r135 < 55296) goto L61;
        r127 = r127 | ((r135 & 8191) << r143);
        r143 = r143 + 13;
        r134 = r152;
        goto L58
    L61:
        r126 = r127 | (r135 << r143);
        r134 = r152;
    L62:
        int r144 = r134 + 1;
        int r136 = r1.charAt(r134);
        if (r136 < 55296) goto L69;
        int r137 = r136 & 8191;
        int r153 = 13;
    L65:
        int r162 = r144 + 1;
        char r145 = r1.charAt(r144);
        if (r145 < 55296) goto L68;
        r137 = r137 | ((r145 & 8191) << r153);
        r153 = r153 + 13;
        r144 = r162;
        goto L65
    L68:
        r136 = r137 | (r145 << r153);
        r144 = r162;
    L69:
        int r154 = r144 + 1;
        int r146 = r1.charAt(r144);
        if (r146 < 55296) goto L76;
        int r147 = r146 & 8191;
        int r163 = 13;
    L72:
        int r172 = r154 + 1;
        char r155 = r1.charAt(r154);
        if (r155 < 55296) goto L75;
        r147 = r147 | ((r155 & 8191) << r163);
        r163 = r163 + 13;
        r154 = r172;
        goto L72
    L75:
        r146 = r147 | (r155 << r163);
        r154 = r172;
    L76:
        int r164 = (r47 + r47) + r714;
        int[] r716 = new int[(r146 + r126) + r136];
        int r138 = r126;
        r12 = r98;
        r92 = r138;
        r13 = r107;
        r17 = r146;
        r102 = r164;
        r16 = r716;
        r74 = r47;
        r42 = r154;
        goto L77
    L10:
        r7 = 1;
        goto L11
    L208:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r33);
        throw null;
    }

    private static double zzm(Object r0, long r1) {
        return ((Double) zzho.zzf(r0, r1)).doubleValue();
    }

    private static float zzn(Object r0, long r1) {
        return ((Float) zzho.zzf(r0, r1)).floatValue();
    }

    private static int zzo(Object r0, long r1) {
        return ((Integer) zzho.zzf(r0, r1)).intValue();
    }

    private final int zzp(int r1) {
        return this.zzc[r1 + 2];
    }

    private final int zzq(int r6, int r7) {
        int[] r5 = this.zzc;
        int r0 = (r5.length / 3) - 1;
    L3:
        if (r7 > r0) goto L10;
        int r2 = (r0 + r7) >>> 1;
        int r3 = r2 * 3;
        int r4 = r5[r3];
        if (r6 == r4) goto L6;
        if (r6 < r4) goto L8;
        r7 = r2 + 1;
        goto L3
    L8:
        r0 = r2 - 1;
        goto L3
    L6:
        return r3;
    L10:
        return -1;
    }

    private static int zzr(int r0) {
        return (r0 >>> 20) & 255;
    }

    private final int zzs(int r1) {
        return this.zzc[r1 + 1];
    }

    private static long zzt(Object r0, long r1) {
        return ((Long) zzho.zzf(r0, r1)).longValue();
    }

    private final zzfl zzu(int r1) {
        int r12 = r1 / 3;
        return (zzfl) this.zzd[(r12 + r12) + 1];
    }

    private final zzgv zzv(int r3) {
        Object[] r2 = this.zzd;
        int r32 = r3 / 3;
        int r33 = r32 + r32;
        zzgv r0 = (zzgv) r2[r33];
        if (r0 == null) goto L5;
        return r0;
    L5:
        zzgv r02 = zzgs.zza().zzb((Class) r2[r33 + 1]);
        r2[r33] = r02;
        return r02;
    }

    private final Object zzw(int r1) {
        int r12 = r1 / 3;
        return this.zzd[r12 + r12];
    }

    private final Object zzx(Object r4, int r5) {
        zzgv r0 = zzv(r5);
        int r1 = zzs(r5) & 1048575;
        if (zzI(r4, r5) == false) goto L5;
        Object r3 = zzb.getObject(r4, r1);
        if (zzL(r3) == false) goto L9;
        return r3;
    L9:
        Object r42 = r0.zze();
        if (r3 == null) goto L12;
        r0.zzg(r42, r3);
    L12:
        return r42;
    L5:
        return r0.zze();
    }

    private final Object zzy(Object r4, int r5, int r6) {
        zzgv r0 = zzv(r6);
        if (zzM(r4, r5, r6) == false) goto L5;
        Object r3 = zzb.getObject(r4, zzs(r6) & 1048575);
        if (zzL(r3) == false) goto L9;
        return r3;
    L9:
        Object r42 = r0.zze();
        if (r3 == null) goto L12;
        r0.zzg(r42, r3);
    L12:
        return r42;
    L5:
        return r0.zze();
    }

    private static Field zzz(Class r7, String r8) {
        return r7.getDeclaredField(r8);
    L4:
        e = move-exception;
        Field[] r0 = r7.getDeclaredFields();
        int r1 = r0.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        Field r3 = r0[r2];
        if (r8.equals(r3.getName()) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return r3;
    L11:
        zzgo$$ExternalSyntheticBUOutline0.m("Field ", r8, " for ", r7.getName(), " not found. Known fields are ", Arrays.toString(r0), e);
        return null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final int zza(Object r18) {
        zzgo<T> r0 = this;
        Object r1 = r18;
        Unsafe r6 = zzb;
        int r2 = 0;
        int r4 = 0;
        int r9 = 0;
        int r3 = 1048575;
    L3:
        int[] r5 = r0.zzc;
        if (r2 >= r5.length) goto L277;
        int r10 = r0.zzs(r2);
        int r12 = zzr(r10);
        int r13 = r5[r2];
        int r52 = r5[r2 + 2];
        int r14 = r52 & 1048575;
        if (r12 > 17) goto L13;
        if (r14 == r3) goto L12;
        if (r14 != 1048575) goto L10;
        r4 = 0;
    L11:
        r3 = r14;
        goto L12
    L10:
        r4 = r6.getInt(r1, r14);
    L12:
        int r53 = 1 << (r52 >>> 20);
    L14:
        int r102 = r10 & 1048575;
        if (r12 < zzfa.zzJ.zza()) goto L17;
        zzfa.zzW.zza();
    L17:
        long r142 = r102;
        switch(r12) {
            case 0: goto L274;
            case 1: goto L271;
            case 2: goto L268;
            case 3: goto L265;
            case 4: goto L262;
            case 5: goto L259;
            case 6: goto L256;
            case 7: goto L253;
            case 8: goto L247;
            case 9: goto L244;
            case 10: goto L240;
            case 11: goto L237;
            case 12: goto L234;
            case 13: goto L230;
            case 14: goto L226;
            case 15: goto L223;
            case 16: goto L217;
            case 17: goto L214;
            case 18: goto L212;
            case 19: goto L211;
            case 20: goto L207;
            case 21: goto L203;
            case 22: goto L199;
            case 23: goto L198;
            case 24: goto L197;
            case 25: goto L193;
            case 26: goto L173;
            case 27: goto L166;
            case 28: goto L159;
            case 29: goto L155;
            case 30: goto L151;
            case 31: goto L150;
            case 32: goto L149;
            case 33: goto L145;
            case 34: goto L140;
            case 35: goto L137;
            case 36: goto L134;
            case 37: goto L131;
            case 38: goto L128;
            case 39: goto L125;
            case 40: goto L122;
            case 41: goto L119;
            case 42: goto L116;
            case 43: goto L113;
            case 44: goto L110;
            case 45: goto L107;
            case 46: goto L104;
            case 47: goto L101;
            case 48: goto L96;
            case 49: goto L89;
            case 50: goto L82;
            case 51: goto L80;
            case 52: goto L77;
            case 53: goto L74;
            case 54: goto L71;
            case 55: goto L68;
            case 56: goto L65;
            case 57: goto L62;
            case 58: goto L59;
            case 59: goto L53;
            case 60: goto L50;
            case 61: goto L46;
            case 62: goto L43;
            case 63: goto L40;
            case 64: goto L36;
            case 65: goto L32;
            case 66: goto L29;
            case 67: goto L25;
            case 68: goto L21;
            default: goto L276;
        };
    L82:
        zzgf r54 = (zzgf) r6.getObject(r1, r142);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.zzw(r2));
        if (r54.isEmpty() == true) goto L276;
        Iterator r55 = r54.entrySet().iterator();
        if (r55.hasNext() == false) goto L276;
        Map.Entry r02 = (Map.Entry) r55.next();
        r02.getKey();
        r02.getValue();
        throw null;
    L89:
        List r56 = (List) r6.getObject(r1, r142);
        zzgv r103 = r0.zzv(r2);
        int r11 = zzgx.$r8$clinit;
        int r112 = r56.size();
        if (r112 != 0) goto L92;
        int r143 = 0;
    L95:
        r9 = r9 + r143;
        goto L276
    L92:
        int r122 = 0;
        r143 = 0;
    L93:
        if (r122 >= r112) goto L95;
        r143 = r143 + zzep.zzy(r13, (zzgl) r56.get(r122), r103);
        r122 = r122 + 1;
        goto L93
    L96:
        int r57 = zzgx.zzj((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        int r104 = zzep.zzC(r13 << 3);
        int r113 = zzep.zzC(r57);
    L99:
        int r105 = (r104 + r113) + r57;
    L100:
        r9 = r9 + r105;
        goto L276
    L101:
        r57 = zzgx.zzi((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L104:
        r57 = zzgx.zze((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L107:
        r57 = zzgx.zzc((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L110:
        r57 = zzgx.zza((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L113:
        r57 = zzgx.zzk((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L116:
        List r58 = (List) r6.getObject(r1, r142);
        int r106 = zzgx.$r8$clinit;
        r57 = r58.size();
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L119:
        r57 = zzgx.zzc((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L122:
        r57 = zzgx.zze((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L125:
        r57 = zzgx.zzf((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L128:
        r57 = zzgx.zzl((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L131:
        r57 = zzgx.zzg((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L134:
        r57 = zzgx.zzc((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L137:
        r57 = zzgx.zze((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L276;
        r104 = zzep.zzC(r13 << 3);
        r113 = zzep.zzC(r57);
        goto L99
    L140:
        List r59 = (List) r6.getObject(r1, r142);
        int r107 = zzgx.$r8$clinit;
        int r108 = r59.size();
        if (r108 != 0) goto L143;
    L142:
        int r510 = 0;
    L23:
        r9 = r9 + r510;
        goto L276
    L143:
        int r511 = zzgx.zzj(r59);
        int r114 = zzep.zzC(r13 << 3);
    L144:
        int r109 = r108 * r114;
    L27:
        r510 = r511 + r109;
        goto L23
    L145:
        List r512 = (List) r6.getObject(r1, r142);
        int r1010 = zzgx.$r8$clinit;
        r108 = r512.size();
        if (r108 == 0) goto L142;
        r511 = zzgx.zzi(r512);
        r114 = zzep.zzC(r13 << 3);
        goto L144
    L149:
        r510 = zzgx.zzd(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L150:
        r510 = zzgx.zzb(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L151:
        List r513 = (List) r6.getObject(r1, r142);
        int r1011 = zzgx.$r8$clinit;
        r108 = r513.size();
        if (r108 == 0) goto L142;
        r511 = zzgx.zza(r513);
        r114 = zzep.zzC(r13 << 3);
        goto L144
    L155:
        List r514 = (List) r6.getObject(r1, r142);
        int r1012 = zzgx.$r8$clinit;
        r108 = r514.size();
        if (r108 == 0) goto L142;
        r511 = zzgx.zzk(r514);
        r114 = zzep.zzC(r13 << 3);
        goto L144
    L159:
        List r515 = (List) r6.getObject(r1, r142);
        int r1013 = zzgx.$r8$clinit;
        int r1014 = r515.size();
        if (r1014 != 0) goto L162;
        r105 = 0;
        goto L100
    L162:
        r105 = r1014 * zzep.zzC(r13 << 3);
        int r115 = 0;
    L164:
        if (r115 >= r515.size()) goto L100;
        int r123 = ((zzei) r515.get(r115)).zzd();
        r105 = r105 + (zzep.zzC(r123) + r123);
        r115 = r115 + 1;
        goto L164
    L166:
        List r516 = (List) r6.getObject(r1, r142);
        zzgv r1015 = r0.zzv(r2);
        int r116 = zzgx.$r8$clinit;
        int r117 = r516.size();
        if (r117 != 0) goto L169;
        int r124 = 0;
    L172:
        r9 = r9 + r124;
        goto L276
    L169:
        r124 = zzep.zzC(r13 << 3) * r117;
        int r132 = 0;
    L170:
        if (r132 >= r117) goto L172;
        r124 = r124 + zzep.zzA((zzgl) r516.get(r132), r1015);
        r132 = r132 + 1;
        goto L170
    L173:
        List r517 = (List) r6.getObject(r1, r142);
        int r1016 = zzgx.$r8$clinit;
        int r1017 = r517.size();
        if (r1017 != 0) goto L176;
    L175:
        int r118 = 0;
    L192:
        r9 = r9 + r118;
        goto L276
    L176:
        r118 = zzep.zzC(r13 << 3) * r1017;
        if ((r517 instanceof zzfx) == false) goto L185;
        zzfx r518 = (zzfx) r517;
        int r125 = 0;
    L179:
        if (r125 >= r1017) goto L192;
        Object r133 = r518.zza();
        if ((r133 instanceof zzei) == false) goto L183;
        int r134 = ((zzei) r133).zzd();
        r118 = r118 + (zzep.zzC(r134) + r134);
    L184:
        r125 = r125 + 1;
        goto L179
    L183:
        r118 = r118 + zzep.zzB((String) r133);
        goto L184
    L185:
        int r126 = 0;
    L186:
        if (r126 >= r1017) goto L192;
        Object r135 = r517.get(r126);
        if ((r135 instanceof zzei) == false) goto L190;
        int r136 = ((zzei) r135).zzd();
        r118 = r118 + (zzep.zzC(r136) + r136);
    L191:
        r126 = r126 + 1;
        goto L186
    L190:
        r118 = r118 + zzep.zzB((String) r135);
        goto L191
    L193:
        List r519 = (List) r6.getObject(r1, r142);
        int r1018 = zzgx.$r8$clinit;
        int r520 = r519.size();
        if (r520 == 0) goto L142;
        r510 = r520 * (zzep.zzC(r13 << 3) + 1);
        goto L23
    L197:
        r510 = zzgx.zzb(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L198:
        r510 = zzgx.zzd(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L199:
        List r521 = (List) r6.getObject(r1, r142);
        int r1019 = zzgx.$r8$clinit;
        r108 = r521.size();
        if (r108 == 0) goto L142;
        r511 = zzgx.zzf(r521);
        r114 = zzep.zzC(r13 << 3);
        goto L144
    L203:
        List r522 = (List) r6.getObject(r1, r142);
        int r1020 = zzgx.$r8$clinit;
        r108 = r522.size();
        if (r108 == 0) goto L142;
        r511 = zzgx.zzl(r522);
        r114 = zzep.zzC(r13 << 3);
        goto L144
    L207:
        List r523 = (List) r6.getObject(r1, r142);
        int r1021 = zzgx.$r8$clinit;
        if (r523.size() == 0) goto L175;
        r118 = zzgx.zzg(r523) + (r523.size() * zzep.zzC(r13 << 3));
        goto L192
    L211:
        r510 = zzgx.zzb(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L212:
        r510 = zzgx.zzd(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L21:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        r510 = zzep.zzy(r13, (zzgl) r6.getObject(r1, r142), r0.zzv(r2));
        goto L23
    L25:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        long r119 = zzt(r1, r142);
        r511 = zzep.zzC(r13 << 3);
        r109 = zzep.zzD((r119 >> 63) ^ (r119 + r119));
        goto L27
    L29:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        int r1022 = zzo(r1, r142);
        r511 = zzep.zzC(r13 << 3);
        r109 = zzep.zzC((r1022 >> 31) ^ (r1022 + r1022));
        goto L27
    L32:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        int r524 = zzep.zzC(r13 << 3);
    L34:
        r510 = r524 + 8;
        goto L23
    L36:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        int r525 = zzep.zzC(r13 << 3);
    L38:
        r510 = r525 + 4;
        goto L23
    L40:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        long r1023 = zzo(r1, r142);
        r511 = zzep.zzC(r13 << 3);
        r109 = zzep.zzD(r1023);
        goto L27
    L43:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        int r1024 = zzo(r1, r142);
        r511 = zzep.zzC(r13 << 3);
        r109 = zzep.zzC(r1024);
        goto L27
    L46:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        zzei r1025 = (zzei) r6.getObject(r1, r142);
        int r526 = zzep.zzC(r13 << 3);
        int r1026 = r1025.zzd();
        int r1110 = zzep.zzC(r1026);
    L48:
        r510 = r526 + (r1110 + r1026);
        goto L23
    L50:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        r510 = zzgx.zzh(r13, r6.getObject(r1, r142), r0.zzv(r2));
        goto L23
    L53:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        int r527 = r13 << 3;
        Object r1027 = r6.getObject(r1, r142);
        if ((r1027 instanceof zzei) == false) goto L57;
        r526 = zzep.zzC(r527);
        r1026 = ((zzei) r1027).zzd();
        r1110 = zzep.zzC(r1026);
        goto L48
    L57:
        r511 = zzep.zzC(r527);
        r109 = zzep.zzB((String) r1027);
        goto L27
    L59:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        r510 = zzep.zzC(r13 << 3) + 1;
        goto L23
    L62:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        r525 = zzep.zzC(r13 << 3);
        goto L38
    L65:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        r524 = zzep.zzC(r13 << 3);
        goto L34
    L68:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        long r1028 = zzo(r1, r142);
        r511 = zzep.zzC(r13 << 3);
        r109 = zzep.zzD(r1028);
        goto L27
    L71:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        long r1029 = zzt(r1, r142);
        r511 = zzep.zzC(r13 << 3);
        r109 = zzep.zzD(r1029);
        goto L27
    L74:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        long r1030 = zzt(r1, r142);
        r511 = zzep.zzC(r13 << 3);
        r109 = zzep.zzD(r1030);
        goto L27
    L77:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        r525 = zzep.zzC(r13 << 3);
        goto L38
    L80:
        if (r0.zzM(r1, r13, r2) == false) goto L276;
        r524 = zzep.zzC(r13 << 3);
        goto L34
    L214:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L276;
        r510 = zzep.zzy(r13, (zzgl) r6.getObject(r1, r142), r0.zzv(r2));
        goto L23
    L217:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        long r1111 = r6.getLong(r1, r142);
        int r03 = zzep.zzC(r13 << 3);
        int r528 = zzep.zzD((r1111 >> 63) ^ (r1111 + r1111));
    L219:
        int r04 = r03 + r528;
    L220:
        r9 = r9 + r04;
    L221:
        r0 = this;
        goto L276
    L223:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        int r529 = r6.getInt(r1, r142);
        r03 = zzep.zzC(r13 << 3);
        r528 = zzep.zzC((r529 >> 31) ^ (r529 + r529));
        goto L219
    L226:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        int r05 = zzep.zzC(r13 << 3);
    L228:
        r04 = r05 + 8;
        goto L220
    L230:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        int r06 = zzep.zzC(r13 << 3);
    L232:
        r04 = r06 + 4;
        goto L220
    L234:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        long r1031 = r6.getInt(r1, r142);
        r03 = zzep.zzC(r13 << 3);
        r528 = zzep.zzD(r1031);
        goto L219
    L237:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        int r530 = r6.getInt(r1, r142);
        r03 = zzep.zzC(r13 << 3);
        r528 = zzep.zzC(r530);
        goto L219
    L240:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        zzei r531 = (zzei) r6.getObject(r1, r142);
        int r07 = zzep.zzC(r13 << 3);
        int r532 = r531.zzd();
        int r1032 = zzep.zzC(r532);
    L242:
        r04 = r07 + (r1032 + r532);
        goto L220
    L244:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L276;
        r510 = zzgx.zzh(r13, r6.getObject(r1, r142), r0.zzv(r2));
        goto L23
    L247:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        int r08 = r13 << 3;
        Object r533 = r6.getObject(r1, r142);
        if ((r533 instanceof zzei) == false) goto L251;
        r07 = zzep.zzC(r08);
        r532 = ((zzei) r533).zzd();
        r1032 = zzep.zzC(r532);
        goto L242
    L251:
        r03 = zzep.zzC(r08);
        r528 = zzep.zzB((String) r533);
        goto L219
    L253:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        r04 = zzep.zzC(r13 << 3) + 1;
        goto L220
    L256:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        r06 = zzep.zzC(r13 << 3);
        goto L232
    L259:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        r05 = zzep.zzC(r13 << 3);
        goto L228
    L262:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        long r1033 = r6.getInt(r1, r142);
        r03 = zzep.zzC(r13 << 3);
        r528 = zzep.zzD(r1033);
        goto L219
    L265:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        long r1034 = r6.getLong(r1, r142);
        r03 = zzep.zzC(r13 << 3);
        r528 = zzep.zzD(r1034);
        goto L219
    L268:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        long r1035 = r6.getLong(r1, r142);
        r03 = zzep.zzC(r13 << 3);
        r528 = zzep.zzD(r1035);
        goto L219
    L271:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L221;
        r06 = zzep.zzC(r13 << 3);
        goto L232
    L274:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L276;
        r9 = r9 + (zzep.zzC(r13 << 3) + 8);
    L276:
        r2 = r2 + 3;
        r1 = r18;
        goto L3
    L13:
        r53 = 0;
        goto L14
    L277:
        int r92 = r9 + ((zzfi) r18).zzc.zza();
        if (r0.zzh == true) goto L280;
        return r92;
    L280:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r18);
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final int zzb(Object r9) {
        int r0 = 0;
        int r1 = 0;
    L3:
        int[] r2 = this.zzc;
        if (r0 >= r2.length) goto L90;
        int r3 = zzs(r0);
        int r4 = 1048575 & r3;
        int r32 = zzr(r3);
        int r22 = r2[r0];
        long r42 = r4;
        int r6 = 37;
        switch(r32) {
            case 0: goto L88;
            case 1: goto L87;
            case 2: goto L86;
            case 3: goto L85;
            case 4: goto L84;
            case 5: goto L83;
            case 6: goto L82;
            case 7: goto L81;
            case 8: goto L80;
            case 9: goto L77;
            case 10: goto L76;
            case 11: goto L75;
            case 12: goto L74;
            case 13: goto L73;
            case 14: goto L72;
            case 15: goto L71;
            case 16: goto L70;
            case 17: goto L66;
            case 18: goto L65;
            case 19: goto L65;
            case 20: goto L65;
            case 21: goto L65;
            case 22: goto L65;
            case 23: goto L65;
            case 24: goto L65;
            case 25: goto L65;
            case 26: goto L65;
            case 27: goto L65;
            case 28: goto L65;
            case 29: goto L65;
            case 30: goto L65;
            case 31: goto L65;
            case 32: goto L65;
            case 33: goto L65;
            case 34: goto L65;
            case 35: goto L65;
            case 36: goto L65;
            case 37: goto L65;
            case 38: goto L65;
            case 39: goto L65;
            case 40: goto L65;
            case 41: goto L65;
            case 42: goto L65;
            case 43: goto L65;
            case 44: goto L65;
            case 45: goto L65;
            case 46: goto L65;
            case 47: goto L65;
            case 48: goto L65;
            case 49: goto L65;
            case 50: goto L64;
            case 51: goto L62;
            case 52: goto L59;
            case 53: goto L56;
            case 54: goto L53;
            case 55: goto L50;
            case 56: goto L47;
            case 57: goto L44;
            case 58: goto L41;
            case 59: goto L38;
            case 60: goto L35;
            case 61: goto L32;
            case 62: goto L29;
            case 63: goto L26;
            case 64: goto L23;
            case 65: goto L20;
            case 66: goto L17;
            case 67: goto L13;
            case 68: goto L9;
            default: goto L89;
        };
    L64:
        int r12 = r1 * 53;
        int r23 = zzho.zzf(r9, r42).hashCode();
    L11:
        r1 = r12 + r23;
        goto L89
    L65:
        r12 = r1 * 53;
        r23 = zzho.zzf(r9, r42).hashCode();
        goto L11
    L66:
        int r13 = r1 * 53;
        Object r24 = zzho.zzf(r9, r42);
        if (r24 == null) goto L69;
        r6 = r24.hashCode();
    L69:
        r1 = r13 + r6;
        goto L89
    L70:
        r12 = r1 * 53;
        long r25 = zzho.zzd(r9, r42);
        byte[] r43 = zzfo.zzb;
    L15:
        r23 = (int) (r25 ^ (r25 >>> 32));
        goto L11
    L71:
        r12 = r1 * 53;
        r23 = zzho.zzc(r9, r42);
        goto L11
    L72:
        r12 = r1 * 53;
        r25 = zzho.zzd(r9, r42);
        byte[] r44 = zzfo.zzb;
        goto L15
    L73:
        r12 = r1 * 53;
        r23 = zzho.zzc(r9, r42);
        goto L11
    L74:
        r12 = r1 * 53;
        r23 = zzho.zzc(r9, r42);
        goto L11
    L75:
        r12 = r1 * 53;
        r23 = zzho.zzc(r9, r42);
        goto L11
    L76:
        r12 = r1 * 53;
        r23 = zzho.zzf(r9, r42).hashCode();
        goto L11
    L77:
        r13 = r1 * 53;
        Object r26 = zzho.zzf(r9, r42);
        if (r26 == null) goto L69;
        r6 = r26.hashCode();
        goto L69
    L80:
        r12 = r1 * 53;
        r23 = ((String) zzho.zzf(r9, r42)).hashCode();
        goto L11
    L81:
        r12 = r1 * 53;
        r23 = zzfo.zza(zzho.zzw(r9, r42));
        goto L11
    L82:
        r12 = r1 * 53;
        r23 = zzho.zzc(r9, r42);
        goto L11
    L83:
        r12 = r1 * 53;
        r25 = zzho.zzd(r9, r42);
        byte[] r45 = zzfo.zzb;
        goto L15
    L84:
        r12 = r1 * 53;
        r23 = zzho.zzc(r9, r42);
        goto L11
    L85:
        r12 = r1 * 53;
        r25 = zzho.zzd(r9, r42);
        byte[] r46 = zzfo.zzb;
        goto L15
    L86:
        r12 = r1 * 53;
        r25 = zzho.zzd(r9, r42);
        byte[] r47 = zzfo.zzb;
        goto L15
    L87:
        r12 = r1 * 53;
        r23 = Float.floatToIntBits(zzho.zzb(r9, r42));
        goto L11
    L88:
        r12 = r1 * 53;
        r25 = Double.doubleToLongBits(zzho.zza(r9, r42));
        byte[] r48 = zzfo.zzb;
        goto L15
    L9:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzho.zzf(r9, r42).hashCode();
        goto L11
    L13:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = zzt(r9, r42);
        byte[] r49 = zzfo.zzb;
        goto L15
    L17:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzo(r9, r42);
        goto L11
    L20:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = zzt(r9, r42);
        byte[] r410 = zzfo.zzb;
        goto L15
    L23:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzo(r9, r42);
        goto L11
    L26:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzo(r9, r42);
        goto L11
    L29:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzo(r9, r42);
        goto L11
    L32:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzho.zzf(r9, r42).hashCode();
        goto L11
    L35:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzho.zzf(r9, r42).hashCode();
        goto L11
    L38:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = ((String) zzho.zzf(r9, r42)).hashCode();
        goto L11
    L41:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzfo.zza(zzN(r9, r42));
        goto L11
    L44:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzo(r9, r42);
        goto L11
    L47:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = zzt(r9, r42);
        byte[] r411 = zzfo.zzb;
        goto L15
    L50:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzo(r9, r42);
        goto L11
    L53:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = zzt(r9, r42);
        byte[] r412 = zzfo.zzb;
        goto L15
    L56:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = zzt(r9, r42);
        byte[] r413 = zzfo.zzb;
        goto L15
    L59:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = Float.floatToIntBits(zzn(r9, r42));
        goto L11
    L62:
        if (zzM(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = Double.doubleToLongBits(zzm(r9, r42));
        byte[] r414 = zzfo.zzb;
    L89:
        r0 = r0 + 3;
        goto L3
    L90:
        int r14 = (r1 * 53) + ((zzfi) r9).zzc.hashCode();
        if (this.zzh == true) goto L93;
        return r14;
    L93:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r9);
        throw null;
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    public final int zzc(java.lang.Object r34, byte[] r35, int r36, int r37, int r38, com.google.android.gms.internal.play_billing.zzdw r39) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: MOVE (r3v0 ?? I:??[OBJECT, ARRAY]) = (r35v0 ?? I:??[OBJECT, ARRAY]) A[DECLARE_VAR] in method: com.google.android.gms.internal.play_billing.zzgo.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.play_billing.zzdw):int, file: classes5.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            Method dump skipped, instructions count: 3212
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzgo.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.play_billing.zzdw):int");
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final Object zze() {
        return ((zzfi) this.zzg).zzo();
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final void zzf(Object r8) {
        if (zzL(r8) == false) goto L44;
        int r1 = 0;
        if ((r8 instanceof zzfi) == false) goto L8;
        zzfi r0 = (zzfi) r8;
        r0.zzx(Integer.MAX_VALUE);
        r0.zza = 0;
        r0.zzv();
    L8:
        int[] r02 = this.zzc;
    L10:
        if (r1 >= r02.length) goto L30;
        int r2 = zzs(r1);
        int r3 = 1048575 & r2;
        int r22 = zzr(r2);
        long r32 = r3;
        if (r22 == 9) goto L27;
        if (r22 == 60) goto L24;
        if (r22 == 68) goto L24;
        switch(r22) {
            case 17: goto L27;
            case 18: goto L22;
            case 19: goto L22;
            case 20: goto L22;
            case 21: goto L22;
            case 22: goto L22;
            case 23: goto L22;
            case 24: goto L22;
            case 25: goto L22;
            case 26: goto L22;
            case 27: goto L22;
            case 28: goto L22;
            case 29: goto L22;
            case 30: goto L22;
            case 31: goto L22;
            case 32: goto L22;
            case 33: goto L22;
            case 34: goto L22;
            case 35: goto L22;
            case 36: goto L22;
            case 37: goto L22;
            case 38: goto L22;
            case 39: goto L22;
            case 40: goto L22;
            case 41: goto L22;
            case 42: goto L22;
            case 43: goto L22;
            case 44: goto L22;
            case 45: goto L22;
            case 46: goto L22;
            case 47: goto L22;
            case 48: goto L22;
            case 49: goto L22;
            case 50: goto L19;
            default: goto L29;
        };
    L19:
        Unsafe r23 = zzb;
        Object r5 = r23.getObject(r8, r32);
        if (r5 == null) goto L29;
        ((zzgf) r5).zzc();
        r23.putObject(r8, r32, r5);
        goto L29
    L22:
        ((zzfn) zzho.zzf(r8, r32)).zzb();
    L29:
        r1 = r1 + 3;
    L24:
        if (zzM(r8, r02[r1], r1) == false) goto L29;
        zzv(r1).zzf(zzb.getObject(r8, r32));
    L27:
        if (zzI(r8, r1) == false) goto L29;
        zzv(r1).zzf(zzb.getObject(r8, r32));
        goto L29
    L30:
        this.zzl.zzb(r8);
        if (this.zzh == false) goto L43;
        this.zzm.zza(r8);
        return;
    L43:
        return;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final void zzg(Object r9, Object r10) {
        zzA(r9);
        r10.getClass();
        int r0 = 0;
    L3:
        int[] r1 = this.zzc;
        if (r0 >= r1.length) goto L79;
        int r2 = zzs(r0);
        int r3 = 1048575 & r2;
        int r22 = zzr(r2);
        int r12 = r1[r0];
        long r32 = r3;
        switch(r22) {
            case 0: goto L76;
            case 1: goto L73;
            case 2: goto L70;
            case 3: goto L67;
            case 4: goto L64;
            case 5: goto L61;
            case 6: goto L58;
            case 7: goto L55;
            case 8: goto L52;
            case 9: goto L50;
            case 10: goto L48;
            case 11: goto L45;
            case 12: goto L42;
            case 13: goto L39;
            case 14: goto L36;
            case 15: goto L33;
            case 16: goto L30;
            case 17: goto L28;
            case 18: goto L17;
            case 19: goto L17;
            case 20: goto L17;
            case 21: goto L17;
            case 22: goto L17;
            case 23: goto L17;
            case 24: goto L17;
            case 25: goto L17;
            case 26: goto L17;
            case 27: goto L17;
            case 28: goto L17;
            case 29: goto L17;
            case 30: goto L17;
            case 31: goto L17;
            case 32: goto L17;
            case 33: goto L17;
            case 34: goto L17;
            case 35: goto L17;
            case 36: goto L17;
            case 37: goto L17;
            case 38: goto L17;
            case 39: goto L17;
            case 40: goto L17;
            case 41: goto L17;
            case 42: goto L17;
            case 43: goto L17;
            case 44: goto L17;
            case 45: goto L17;
            case 46: goto L17;
            case 47: goto L17;
            case 48: goto L17;
            case 49: goto L17;
            case 50: goto L16;
            case 51: goto L14;
            case 52: goto L14;
            case 53: goto L14;
            case 54: goto L14;
            case 55: goto L14;
            case 56: goto L14;
            case 57: goto L14;
            case 58: goto L14;
            case 59: goto L14;
            case 60: goto L12;
            case 61: goto L10;
            case 62: goto L10;
            case 63: goto L10;
            case 64: goto L10;
            case 65: goto L10;
            case 66: goto L10;
            case 67: goto L10;
            case 68: goto L8;
            default: goto L78;
        };
    L8:
        zzC(r9, r10, r0);
        goto L78
    L12:
        zzC(r9, r10, r0);
        goto L78
    L16:
        int r13 = zzgx.$r8$clinit;
        zzho.zzs(r9, r32, zzgg.zza(zzho.zzf(r9, r32), zzho.zzf(r10, r32)));
        goto L78
    L17:
        zzfn r14 = (zzfn) zzho.zzf(r9, r32);
        zzfn r23 = (zzfn) zzho.zzf(r10, r32);
        int r5 = r14.size();
        int r6 = r23.size();
        if (r5 <= 0) goto L24;
        if (r6 <= 0) goto L24;
        if (r14.zzc() == true) goto L23;
        r14 = r14.zzd(r6 + r5);
    L23:
        r14.addAll(r23);
    L24:
        if (r5 <= 0) goto L27;
        r23 = r14;
    L27:
        zzho.zzs(r9, r32, r23);
        goto L78
    L28:
        zzB(r9, r10, r0);
        goto L78
    L50:
        zzB(r9, r10, r0);
        goto L78
    L10:
        if (zzM(r10, r12, r0) == false) goto L78;
        zzho.zzs(r9, r32, zzho.zzf(r10, r32));
        zzE(r9, r12, r0);
        goto L78
    L14:
        if (zzM(r10, r12, r0) == false) goto L78;
        zzho.zzs(r9, r32, zzho.zzf(r10, r32));
        zzE(r9, r12, r0);
        goto L78
    L30:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzr(r9, r32, zzho.zzd(r10, r32));
        zzD(r9, r0);
        goto L78
    L33:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzq(r9, r32, zzho.zzc(r10, r32));
        zzD(r9, r0);
        goto L78
    L36:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzr(r9, r32, zzho.zzd(r10, r32));
        zzD(r9, r0);
        goto L78
    L39:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzq(r9, r32, zzho.zzc(r10, r32));
        zzD(r9, r0);
        goto L78
    L42:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzq(r9, r32, zzho.zzc(r10, r32));
        zzD(r9, r0);
        goto L78
    L45:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzq(r9, r32, zzho.zzc(r10, r32));
        zzD(r9, r0);
        goto L78
    L48:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzs(r9, r32, zzho.zzf(r10, r32));
        zzD(r9, r0);
        goto L78
    L52:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzs(r9, r32, zzho.zzf(r10, r32));
        zzD(r9, r0);
        goto L78
    L55:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzm(r9, r32, zzho.zzw(r10, r32));
        zzD(r9, r0);
        goto L78
    L58:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzq(r9, r32, zzho.zzc(r10, r32));
        zzD(r9, r0);
        goto L78
    L61:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzr(r9, r32, zzho.zzd(r10, r32));
        zzD(r9, r0);
        goto L78
    L64:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzq(r9, r32, zzho.zzc(r10, r32));
        zzD(r9, r0);
        goto L78
    L67:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzr(r9, r32, zzho.zzd(r10, r32));
        zzD(r9, r0);
        goto L78
    L70:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzr(r9, r32, zzho.zzd(r10, r32));
        zzD(r9, r0);
        goto L78
    L73:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzp(r9, r32, zzho.zzb(r10, r32));
        zzD(r9, r0);
        goto L78
    L76:
        if (zzI(r10, r0) == false) goto L78;
        zzho.zzo(r9, r32, zzho.zza(r10, r32));
        zzD(r9, r0);
    L78:
        r0 = r0 + 3;
        goto L3
    L79:
        zzgx.zzp(this.zzl, r9, r10);
        if (this.zzh == false) goto L127;
        zzgx.zzo(this.zzm, r9, r10);
        return;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final void zzh(Object r8, byte[] r9, int r10, int r11, zzdw r12) {
        zzc(r8, r9, r10, r11, 0, r12);
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final void zzi(Object r18, zzhu r19) {
        zzgo<T> r0 = this;
        if (r0.zzh == true) goto L190;
        int[] r8 = r0.zzc;
        Unsafe r9 = zzb;
        int r10 = 1048575;
        int r3 = 1048575;
        int r2 = 0;
        int r4 = 0;
    L6:
        if (r2 >= r8.length) goto L188;
        int r5 = r0.zzs(r2);
        int r12 = zzr(r5);
        int r13 = r8[r2];
        if (r12 > 17) goto L16;
        int r14 = r8[r2 + 2];
        Throwable r16 = null;
        int r7 = r14 & r10;
        if (r7 == r3) goto L15;
        if (r7 != r10) goto L13;
        r4 = 0;
    L14:
        r3 = r7;
        goto L15
    L13:
        r4 = r9.getInt(r18, r7);
    L15:
        int r72 = 1 << (r14 >>> 20);
    L17:
        long r102 = r5 & r10;
        switch(r12) {
            case 0: goto L185;
            case 1: goto L182;
            case 2: goto L179;
            case 3: goto L176;
            case 4: goto L173;
            case 5: goto L170;
            case 6: goto L167;
            case 7: goto L164;
            case 8: goto L161;
            case 9: goto L158;
            case 10: goto L155;
            case 11: goto L152;
            case 12: goto L149;
            case 13: goto L146;
            case 14: goto L143;
            case 15: goto L140;
            case 16: goto L137;
            case 17: goto L134;
            case 18: goto L132;
            case 19: goto L131;
            case 20: goto L130;
            case 21: goto L129;
            case 22: goto L128;
            case 23: goto L127;
            case 24: goto L126;
            case 25: goto L125;
            case 26: goto L120;
            case 27: goto L112;
            case 28: goto L107;
            case 29: goto L106;
            case 30: goto L105;
            case 31: goto L104;
            case 32: goto L103;
            case 33: goto L102;
            case 34: goto L101;
            case 35: goto L100;
            case 36: goto L99;
            case 37: goto L98;
            case 38: goto L97;
            case 39: goto L96;
            case 40: goto L95;
            case 41: goto L94;
            case 42: goto L93;
            case 43: goto L92;
            case 44: goto L91;
            case 45: goto L90;
            case 46: goto L89;
            case 47: goto L88;
            case 48: goto L87;
            case 49: goto L79;
            case 50: goto L75;
            case 51: goto L72;
            case 52: goto L69;
            case 53: goto L66;
            case 54: goto L63;
            case 55: goto L60;
            case 56: goto L57;
            case 57: goto L54;
            case 58: goto L51;
            case 59: goto L48;
            case 60: goto L45;
            case 61: goto L42;
            case 62: goto L39;
            case 63: goto L36;
            case 64: goto L33;
            case 65: goto L30;
            case 66: goto L27;
            case 67: goto L24;
            case 68: goto L21;
            default: goto L187;
        };
    L79:
        int r52 = r8[r2];
        List r73 = (List) r9.getObject(r18, r102);
        zzgv r103 = r0.zzv(r2);
        int r11 = zzgx.$r8$clinit;
        if (r73 == null) goto L187;
        if (r73.isEmpty() == true) goto L187;
        int r112 = 0;
    L85:
        if (r112 >= r73.size()) goto L187;
        ((zzeq) r19).zzq(r52, r73.get(r112), r103);
        r112 = r112 + 1;
        goto L85
    L87:
        zzgx.zzB(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L88:
        zzgx.zzA(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L89:
        zzgx.zzz(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L90:
        zzgx.zzy(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L91:
        zzgx.zzs(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L92:
        zzgx.zzC(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L93:
        zzgx.zzq(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L94:
        zzgx.zzt(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L95:
        zzgx.zzu(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L96:
        zzgx.zzw(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L97:
        zzgx.zzD(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L98:
        zzgx.zzx(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L99:
        zzgx.zzv(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L100:
        zzgx.zzr(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L101:
        zzgx.zzB(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L102:
        zzgx.zzA(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L103:
        zzgx.zzz(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L104:
        zzgx.zzy(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L105:
        zzgx.zzs(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L106:
        zzgx.zzC(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L107:
        int r53 = r8[r2];
        List r74 = (List) r9.getObject(r18, r102);
        int r104 = zzgx.$r8$clinit;
        if (r74 == null) goto L187;
        if (r74.isEmpty() == true) goto L187;
        r19.zze(r53, r74);
        goto L187
    L112:
        int r54 = r8[r2];
        List r75 = (List) r9.getObject(r18, r102);
        zzgv r105 = r0.zzv(r2);
        int r113 = zzgx.$r8$clinit;
        if (r75 == null) goto L187;
        if (r75.isEmpty() == true) goto L187;
        int r122 = 0;
    L118:
        if (r122 >= r75.size()) goto L187;
        ((zzeq) r19).zzv(r54, r75.get(r122), r105);
        r122 = r122 + 1;
        goto L118
    L120:
        int r55 = r8[r2];
        List r76 = (List) r9.getObject(r18, r102);
        int r106 = zzgx.$r8$clinit;
        if (r76 == null) goto L187;
        if (r76.isEmpty() == true) goto L187;
        r19.zzH(r55, r76);
        goto L187
    L125:
        zzgx.zzq(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L126:
        zzgx.zzt(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L127:
        zzgx.zzu(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L128:
        zzgx.zzw(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L129:
        zzgx.zzD(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L130:
        zzgx.zzx(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L131:
        zzgx.zzv(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L132:
        zzgx.zzr(r8[r2], (List) r9.getObject(r18, r102), r19, false);
    L187:
        r2 = r2 + 3;
        r10 = 1048575;
        r0 = this;
        goto L6
    L21:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzq(r13, r9.getObject(r18, r102), r0.zzv(r2));
        goto L187
    L24:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzD(r13, zzt(r18, r102));
        goto L187
    L27:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzB(r13, zzo(r18, r102));
        goto L187
    L30:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzz(r13, zzt(r18, r102));
        goto L187
    L33:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzx(r13, zzo(r18, r102));
        goto L187
    L36:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzi(r13, zzo(r18, r102));
        goto L187
    L39:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzI(r13, zzo(r18, r102));
        goto L187
    L42:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzd(r13, (zzei) r9.getObject(r18, r102));
        goto L187
    L45:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzv(r13, r9.getObject(r18, r102), r0.zzv(r2));
        goto L187
    L48:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        zzO(r13, r9.getObject(r18, r102), r19);
        goto L187
    L51:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzb(r13, zzN(r18, r102));
        goto L187
    L54:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzk(r13, zzo(r18, r102));
        goto L187
    L57:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzm(r13, zzt(r18, r102));
        goto L187
    L60:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzr(r13, zzo(r18, r102));
        goto L187
    L63:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzK(r13, zzt(r18, r102));
        goto L187
    L66:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzt(r13, zzt(r18, r102));
        goto L187
    L69:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzo(r13, zzn(r18, r102));
        goto L187
    L72:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzf(r13, zzm(r18, r102));
        goto L187
    L75:
        if (r9.getObject(r18, r102) == null) goto L187;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.zzw(r2));
        throw r16;
    L134:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzq(r13, r9.getObject(r18, r102), r0.zzv(r2));
        goto L187
    L137:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzD(r13, r9.getLong(r18, r102));
        goto L187
    L140:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzB(r13, r9.getInt(r18, r102));
        goto L187
    L143:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzz(r13, r9.getLong(r18, r102));
        goto L187
    L146:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzx(r13, r9.getInt(r18, r102));
        goto L187
    L149:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzi(r13, r9.getInt(r18, r102));
        goto L187
    L152:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzI(r13, r9.getInt(r18, r102));
        goto L187
    L155:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzd(r13, (zzei) r9.getObject(r18, r102));
        goto L187
    L158:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzv(r13, r9.getObject(r18, r102), r0.zzv(r2));
        goto L187
    L161:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        zzO(r13, r9.getObject(r18, r102), r19);
        goto L187
    L164:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzb(r13, zzho.zzw(r18, r102));
        goto L187
    L167:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzk(r13, r9.getInt(r18, r102));
        goto L187
    L170:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzm(r13, r9.getLong(r18, r102));
        goto L187
    L173:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzr(r13, r9.getInt(r18, r102));
        goto L187
    L176:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzK(r13, r9.getLong(r18, r102));
        goto L187
    L179:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzt(r13, r9.getLong(r18, r102));
        goto L187
    L182:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzo(r13, zzho.zzb(r18, r102));
        goto L187
    L185:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzf(r13, zzho.zza(r18, r102));
        goto L187
    L16:
        r16 = null;
        r72 = 0;
        goto L17
    L188:
        ((zzfi) r18).zzc.zzl(r19);
        return;
    L190:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r18);
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final boolean zzj(Object r8, Object r9) {
        int r1 = 0;
    L4:
        if (r1 >= this.zzc.length) goto L109;
        int r2 = zzs(r1);
        long r4 = r2 & 1048575;
        switch(zzr(r2)) {
            case 0: goto L103;
            case 1: goto L98;
            case 2: goto L93;
            case 3: goto L88;
            case 4: goto L83;
            case 5: goto L78;
            case 6: goto L73;
            case 7: goto L68;
            case 8: goto L63;
            case 9: goto L58;
            case 10: goto L53;
            case 11: goto L48;
            case 12: goto L43;
            case 13: goto L38;
            case 14: goto L33;
            case 15: goto L28;
            case 16: goto L23;
            case 17: goto L18;
            case 18: goto L14;
            case 19: goto L14;
            case 20: goto L14;
            case 21: goto L14;
            case 22: goto L14;
            case 23: goto L14;
            case 24: goto L14;
            case 25: goto L14;
            case 26: goto L14;
            case 27: goto L14;
            case 28: goto L14;
            case 29: goto L14;
            case 30: goto L14;
            case 31: goto L14;
            case 32: goto L14;
            case 33: goto L14;
            case 34: goto L14;
            case 35: goto L14;
            case 36: goto L14;
            case 37: goto L14;
            case 38: goto L14;
            case 39: goto L14;
            case 40: goto L14;
            case 41: goto L14;
            case 42: goto L14;
            case 43: goto L14;
            case 44: goto L14;
            case 45: goto L14;
            case 46: goto L14;
            case 47: goto L14;
            case 48: goto L14;
            case 49: goto L14;
            case 50: goto L13;
            case 51: goto L8;
            case 52: goto L8;
            case 53: goto L8;
            case 54: goto L8;
            case 55: goto L8;
            case 56: goto L8;
            case 57: goto L8;
            case 58: goto L8;
            case 59: goto L8;
            case 60: goto L8;
            case 61: goto L8;
            case 62: goto L8;
            case 63: goto L8;
            case 64: goto L8;
            case 65: goto L8;
            case 66: goto L8;
            case 67: goto L8;
            case 68: goto L8;
            default: goto L106;
        };
    L8:
        long r22 = zzp(r1) & 1048575;
        if (zzho.zzc(r8, r22) != zzho.zzc(r9, r22)) goto L107;
        if (zzgx.zzE(zzho.zzf(r8, r4), zzho.zzf(r9, r4)) == true) goto L106;
    L107:
        return false;
    L13:
        boolean r23 = zzgx.zzE(zzho.zzf(r8, r4), zzho.zzf(r9, r4));
    L15:
        if (r23 == true) goto L106;
    L14:
        r23 = zzgx.zzE(zzho.zzf(r8, r4), zzho.zzf(r9, r4));
        goto L15
    L18:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzgx.zzE(zzho.zzf(r8, r4), zzho.zzf(r9, r4)) == false) goto L107;
    L23:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzd(r8, r4) != zzho.zzd(r9, r4)) goto L107;
    L28:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzc(r8, r4) != zzho.zzc(r9, r4)) goto L107;
    L33:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzd(r8, r4) != zzho.zzd(r9, r4)) goto L107;
    L38:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzc(r8, r4) != zzho.zzc(r9, r4)) goto L107;
    L43:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzc(r8, r4) != zzho.zzc(r9, r4)) goto L107;
    L48:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzc(r8, r4) != zzho.zzc(r9, r4)) goto L107;
    L53:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzgx.zzE(zzho.zzf(r8, r4), zzho.zzf(r9, r4)) == false) goto L107;
    L58:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzgx.zzE(zzho.zzf(r8, r4), zzho.zzf(r9, r4)) == false) goto L107;
    L63:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzgx.zzE(zzho.zzf(r8, r4), zzho.zzf(r9, r4)) == false) goto L107;
    L68:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzw(r8, r4) != zzho.zzw(r9, r4)) goto L107;
    L73:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzc(r8, r4) != zzho.zzc(r9, r4)) goto L107;
    L78:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzd(r8, r4) != zzho.zzd(r9, r4)) goto L107;
    L83:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzc(r8, r4) != zzho.zzc(r9, r4)) goto L107;
    L88:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzd(r8, r4) != zzho.zzd(r9, r4)) goto L107;
    L93:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (zzho.zzd(r8, r4) != zzho.zzd(r9, r4)) goto L107;
    L98:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (Float.floatToIntBits(zzho.zzb(r8, r4)) != Float.floatToIntBits(zzho.zzb(r9, r4))) goto L107;
    L103:
        if (zzH(r8, r9, r1) == false) goto L107;
        if (Double.doubleToLongBits(zzho.zza(r8, r4)) != Double.doubleToLongBits(zzho.zza(r9, r4))) goto L107;
    L106:
        r1 = r1 + 3;
        goto L4
    L109:
        if (((zzfi) r8).zzc.equals(((zzfi) r9).zzc) == true) goto L112;
        return false;
    L112:
        if (this.zzh == true) goto L115;
        return true;
    L115:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r8);
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final boolean zzk(Object r16) {
        int r2 = 0;
        int r8 = 0;
        int r0 = 1048575;
    L4:
        if (r8 >= this.zzj) goto L59;
        int[] r3 = this.zzi;
        int[] r5 = this.zzc;
        int r32 = r3[r8];
        int r10 = r5[r32];
        int r11 = zzs(r32);
        int r52 = r5[r32 + 2];
        int r12 = r52 & 1048575;
        int r53 = 1 << (r52 >>> 20);
        if (r12 == r0) goto L10;
        if (r12 == 1048575) goto L9;
        r2 = zzb.getInt(r16, r12);
    L9:
        int r4 = r2;
        r0 = r12;
    L12:
        if ((268435456 & r11) == 0) goto L17;
        int r22 = r32;
        int r33 = r0;
        if (zzJ(r16, r22, r33, r4, r53) == false) goto L16;
    L18:
        int r122 = zzr(r11);
        if (r122 == 9) goto L53;
        if (r122 == 17) goto L53;
        if (r122 != 27) goto L25;
    L43:
        List r54 = (List) zzho.zzf(r16, r11 & 1048575);
        if (r54.isEmpty() == true) goto L57;
        zzgv r23 = zzv(r22);
        int r9 = 0;
    L47:
        if (r9 >= r54.size()) goto L57;
        if (r23.zzk(r54.get(r9)) == false) goto L50;
        r9 = r9 + 1;
        goto L47
    L50:
        return false;
    L57:
        r8 = r8 + 1;
        r0 = r33;
        r2 = r4;
        goto L4
    L25:
        if (r122 == 60) goto L39;
        if (r122 == 68) goto L39;
        if (r122 == 49) goto L43;
        if (r122 != 50) goto L57;
        if (((zzgf) zzho.zzf(r16, r11 & 1048575)).isEmpty() == true) goto L57;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(zzw(r22));
        throw null;
    L39:
        if (zzM(r16, r10, r22) == false) goto L57;
        if (zzK(r16, r11, zzv(r22)) == true) goto L57;
        return false;
    L53:
        if (zzJ(r16, r22, r33, r4, r53) == false) goto L57;
        if (zzK(r16, r11, zzv(r22)) == true) goto L57;
        return false;
    L16:
        return false;
    L17:
        r22 = r32;
        r33 = r0;
        goto L18
    L10:
        r4 = r2;
        goto L12
    L59:
        if (this.zzh == true) goto L61;
        return true;
    L61:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r16);
        throw null;
    }
}
