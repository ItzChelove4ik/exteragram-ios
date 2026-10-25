package com.google.android.gms.internal.mlkit_genai_prompt;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.cast.zzzl$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.cast.zzzl$$ExternalSyntheticBUOutline1;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;
import sun.misc.Unsafe;

/* loaded from: classes4.dex */
final class zzaxz<T> implements zzayg<T> {
    private static final int[] zza = null;
    private static final Unsafe zzb = null;
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzaxw zzg;
    private final boolean zzh;
    private final int[] zzi;
    private final int zzj;
    private final int zzk;
    private final zzayq zzl;
    private final zzawm zzm;

    static {
        zza = new int[0];
        zzb = zzayx.zzn();
    }

    private zzaxz(int[] r1, Object[] r2, int r3, int r4, zzaxw r5, boolean r6, int[] r7, int r8, int r9, zzayb r10, zzaxn r11, zzayq r12, zzawm r13, zzaxs r14) {
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

    private final boolean zzA(Object r1, Object r2, int r3) {
        if (zzC(r1, r3) != zzC(r2, r3)) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean zzB(Object r2, int r3, int r4, int r5, int r6) {
        if (r4 != 1048575) goto L7;
        return zzC(r2, r3);
    L7:
        if ((r5 & r6) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    private final boolean zzC(Object r8, int r9) {
        int r0 = zzw(r9);
        long r2 = r0 & 1048575;
        if (r2 != 1048575) goto L89;
        int r92 = zzv(r9);
        long r02 = r92 & 1048575;
        switch(zzx(r92)) {
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
            default: goto L7;
        };
    L44:
        Object r82 = zzayx.zzl(r8, r02);
        if ((r82 instanceof String) == false) goto L51;
        if (((String) r82).isEmpty() == true) goto L49;
        return true;
    L49:
        return false;
    L51:
        if ((r82 instanceof zzawb) == false) goto L57;
        if (zzawb.zza.equals(r82) == true) goto L55;
        return true;
    L55:
        return false;
    L57:
        return zzI();
    L7:
        return zzI();
    L9:
        if (zzayx.zzl(r8, r02) == null) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (zzayx.zzd(r8, r02) == 0) goto L15;
        return true;
    L15:
        return false;
    L17:
        if (zzayx.zzb(r8, r02) == 0) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (zzayx.zzd(r8, r02) == 0) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (zzayx.zzb(r8, r02) == 0) goto L27;
        return true;
    L27:
        return false;
    L29:
        if (zzayx.zzb(r8, r02) == 0) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (zzayx.zzb(r8, r02) == 0) goto L35;
        return true;
    L35:
        return false;
    L37:
        if (zzawb.zza.equals(zzayx.zzl(r8, r02)) == true) goto L39;
        return true;
    L39:
        return false;
    L41:
        if (zzayx.zzl(r8, r02) == null) goto L43;
        return true;
    L43:
        return false;
    L59:
        return zzayx.zzf(r8, r02);
    L61:
        if (zzayx.zzb(r8, r02) == 0) goto L63;
        return true;
    L63:
        return false;
    L65:
        if (zzayx.zzd(r8, r02) == 0) goto L67;
        return true;
    L67:
        return false;
    L69:
        if (zzayx.zzb(r8, r02) == 0) goto L71;
        return true;
    L71:
        return false;
    L73:
        if (zzayx.zzd(r8, r02) == 0) goto L75;
        return true;
    L75:
        return false;
    L77:
        if (zzayx.zzd(r8, r02) == 0) goto L79;
        return true;
    L79:
        return false;
    L81:
        if (Float.floatToRawIntBits(zzayx.zzh(r8, r02)) == 0) goto L83;
        return true;
    L83:
        return false;
    L85:
        if (Double.doubleToRawLongBits(zzayx.zzj(r8, r02)) == 0) goto L87;
        return true;
    L87:
        return false;
    L89:
        if (((1 << (r0 >>> 20)) & zzayx.zzb(r8, r2)) == 0) goto L91;
        return true;
    L91:
        return false;
    }

    private final void zzD(Object r5, int r6) {
        int r4 = zzw(r6);
        long r0 = 1048575 & r4;
        if (r0 != 1048575) goto L5;
        return;
    L5:
        zzayx.zzc(r5, r0, (1 << (r4 >>> 20)) | zzayx.zzb(r5, r0));
    }

    private final boolean zzE(Object r3, int r4, int r5) {
        if (zzayx.zzb(r3, zzw(r5) & 1048575) != r4) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean zzF(Object r3, Object r4, int r5) {
        long r0 = zzw(r5) & 1048575;
        if (zzayx.zzb(r3, r0) != zzayx.zzb(r4, r0)) goto L6;
        return true;
    L6:
        return false;
    }

    private final void zzG(Object r3, int r4, int r5) {
        zzayx.zzc(r3, zzw(r5) & 1048575, r4);
    }

    private final int zzH(int r6, int r7) {
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

    private boolean zzI() {
        throw new IllegalArgumentException();
    }

    public static zzayr zze(Object r2) {
        zzawy r22 = (zzawy) r2;
        zzayr r0 = r22.zzc;
        if (r0 != zzayr.zza()) goto L6;
        zzayr r02 = zzayr.zzb();
        r22.zzc = r02;
        return r02;
    L6:
        return r0;
    }

    public static zzaxz zzj(Class r32, zzaxu r33, zzayb r34, zzaxn r35, zzayq r36, zzawm r37, zzaxs r38) {
        Unsafe r1 = zzb;
        if (r1 != null) goto L5;
        GlShader$$ExternalSyntheticBUOutline1.m("Lite gencode is primarily intended for Android use and uses sun.misc.Unsafe which is not available in the current environment. To run in this environment, you may need to switch to standard gencode.");
        return null;
    L5:
        if ((r33 instanceof zzayf) == false) goto L202;
        zzayf r0 = (zzayf) r33;
        String r2 = r0.zzd();
        int r3 = r2.length();
        int r4 = 0;
        char r6 = 55296;
        if (r2.charAt(0) < 55296) goto L12;
        int r5 = 1;
    L9:
        int r8 = r5 + 1;
        if (r2.charAt(r5) < 55296) goto L13;
        r5 = r8;
    L13:
        int r52 = r8 + 1;
        int r82 = r2.charAt(r8);
        if (r82 < 55296) goto L20;
        int r83 = r82 & 8191;
        int r10 = 13;
    L16:
        int r11 = r52 + 1;
        char r53 = r2.charAt(r52);
        if (r53 < 55296) goto L19;
        r83 = r83 | ((r53 & 8191) << r10);
        r10 = r10 + 13;
        r52 = r11;
        goto L16
    L19:
        r82 = r83 | (r53 << r10);
        r52 = r11;
    L20:
        if (r82 != 0) goto L22;
        int r12 = 0;
        int r13 = 0;
        int r14 = 0;
        int r16 = 0;
        int r18 = 0;
        int[] r17 = zza;
        int r84 = 0;
    L78:
        Object[] r102 = r0.zze();
        Class<?> r112 = r0.zzb().getClass();
        int r19 = r18 + r84;
        int r85 = r12 + r12;
        int[] r122 = new int[r12 * 3];
        Object[] r86 = new Object[r85];
        int r22 = r18;
        int r21 = r19;
        int r15 = 0;
        int r20 = 0;
    L79:
        if (r52 >= r3) goto L201;
        int r23 = r52 + 1;
        int r54 = r2.charAt(r52);
        if (r54 < r6) goto L87;
        int r55 = r54 & 8191;
        int r9 = r23;
        int r232 = 13;
    L83:
        int r24 = r9 + 1;
        char r92 = r2.charAt(r9);
        if (r92 < r6) goto L86;
        r55 = r55 | ((r92 & 8191) << r232);
        r232 = r232 + 13;
        r9 = r24;
        goto L83
    L86:
        r54 = r55 | (r92 << r232);
        int r93 = r24;
    L88:
        int r233 = r93 + 1;
        int r94 = r2.charAt(r93);
        if (r94 < r6) goto L95;
        int r95 = r94 & 8191;
        int r7 = r233;
        int r234 = 13;
    L91:
        int r25 = r7 + 1;
        char r72 = r2.charAt(r7);
        if (r72 < r6) goto L94;
        r95 = r95 | ((r72 & 8191) << r234);
        r234 = r234 + 13;
        r7 = r25;
        goto L91
    L94:
        r94 = r95 | (r72 << r234);
        int r73 = r25;
    L97:
        if ((r94 & 1024) == 0) goto L99;
        r17[r15] = r20;
        r15 = r15 + 1;
    L99:
        int r62 = r94 & 255;
        zzayf r252 = r0;
        int r26 = r94 & 2048;
        if (r62 < 51) goto L136;
        int r02 = r73 + 1;
        int r74 = r2.charAt(r73);
        char r03 = 55296;
        if (r74 < 55296) goto L108;
        int r27 = r74 & 8191;
        int r75 = r02;
        int r30 = 13;
    L104:
        int r31 = r75 + 1;
        char r76 = r2.charAt(r75);
        if (r76 < r03) goto L107;
        r27 = r27 | ((r76 & 8191) << r30);
        r30 = r30 + 13;
        r75 = r31;
        r03 = 55296;
        goto L104
    L107:
        r74 = r27 | (r76 << r30);
        int r04 = r31;
    L109:
        int r272 = r04;
        int r05 = r62 - 51;
        int r302 = r3;
        if (r05 == 9) goto L124;
        if (r05 == 17) goto L124;
        if (r05 == 12) goto L117;
    L123:
        int r06 = r26;
    L125:
        int r77 = r74 + r74;
        Object r39 = r102[r77];
        int r262 = r06;
        if ((r39 instanceof Field) == false) goto L129;
        Field r310 = (Field) r39;
    L128:
        int r07 = r4;
        int r311 = (int) r1.objectFieldOffset(r310);
        int r78 = r77 + 1;
        Object r42 = r102[r78];
        int r312 = r07;
        if ((r42 instanceof Field) == false) goto L134;
        Field r43 = (Field) r42;
    L135:
        String r28 = r2;
        r73 = r272;
        int r29 = 0;
        int r273 = r16;
        int r162 = (int) r1.objectFieldOffset(r43);
        int r313 = r311;
        int r08 = r262;
    L188:
        int r44 = r20 + 1;
        r122[r20] = r54;
        int r56 = r20 + 2;
        int r263 = r08;
        if ((r94 & 512) == 0) goto L191;
        int r09 = 536870912;
    L193:
        if ((r94 & 256) == 0) goto L195;
        int r96 = 268435456;
    L196:
        if (r263 == 0) goto L198;
        int r264 = Integer.MIN_VALUE;
    L199:
        r122[r44] = (((r09 | r96) | r264) | (r62 << 20)) | r313;
        r20 = r20 + 3;
        r122[r56] = (r29 << 20) | r162;
        r52 = r73;
        r0 = r252;
        r16 = r273;
        r2 = r28;
        r3 = r302;
        r4 = r312;
        r6 = 55296;
        goto L79
    L198:
        r264 = 0;
        goto L199
    L195:
        r96 = 0;
        goto L196
    L191:
        r09 = 0;
        goto L193
    L134:
        r43 = zzk(r112, (String) r42);
        r102[r78] = r43;
        goto L135
    L129:
        r310 = zzk(r112, (String) r39);
        r102[r77] = r310;
        r17[r21] = r20;
        r21 = r21 + 1;
        goto L128
    L117:
        if (r252.zzc() == 1) goto L121;
        if (r26 != 0) goto L121;
        r06 = 0;
    L121:
        int r010 = r16 + 1;
        int r242 = r20 / 3;
        r86[(r242 + r242) + 1] = r102[r16];
    L122:
        r16 = r010;
    L124:
        r010 = r16 + 1;
        int r243 = r20 / 3;
        r86[(r243 + r243) + 1] = r102[r16];
        goto L122
    L108:
        r04 = r02;
        goto L109
    L136:
        r302 = r3;
        r312 = r4;
        int r011 = r16 + 1;
        Field r314 = zzk(r112, (String) r102[r16]);
        if (r62 != 9) goto L139;
    L140:
        r273 = r011;
        int r45 = r20 / 3;
        r86[(r45 + r45) + 1] = r314.getType();
    L170:
        r313 = (int) r1.objectFieldOffset(r314);
        r162 = 1048575;
        if ((r94 & 4096) != 0) goto L173;
    L187:
        r28 = r2;
        r08 = r26;
        r29 = 0;
        goto L188
    L173:
        if (r62 > 17) goto L187;
        int r46 = r73 + 1;
        int r79 = r2.charAt(r73);
        if (r79 < 55296) goto L181;
        int r710 = r79 & 8191;
        int r163 = 13;
    L177:
        int r235 = r46 + 1;
        char r47 = r2.charAt(r46);
        if (r47 < 55296) goto L180;
        r710 = r710 | ((r47 & 8191) << r163);
        r163 = r163 + 13;
        r46 = r235;
        goto L177
    L180:
        r79 = r710 | (r47 << r163);
        r46 = r235;
    L181:
        int r164 = (r312 + r312) + (r79 / 32);
        Object r012 = r102[r164];
        r28 = r2;
        if ((r012 instanceof Field) == false) goto L185;
        Field r013 = (Field) r012;
    L186:
        r162 = (int) r1.objectFieldOffset(r013);
        r29 = r79 % 32;
        r08 = r26;
        r313 = r313;
        r73 = r46;
        goto L188
    L185:
        r013 = zzk(r112, (String) r012);
        r102[r164] = r013;
        goto L186
    L139:
        if (r62 == 17) goto L140;
        if (r62 != 27) goto L144;
        int r274 = r011;
        int r014 = 1;
        int r165 = r16 + 2;
    L168:
        int r48 = r20 / 3;
        r86[(r48 + r48) + r014] = r102[r274];
    L166:
        r273 = r165;
        goto L170
    L144:
        if (r62 != 49) goto L147;
        r165 = r16 + 2;
        r274 = r011;
        r014 = 1;
        goto L168
    L147:
        if (r62 != 12) goto L149;
    L160:
        r273 = r011;
        if (r252.zzc() == 1) goto L165;
        if (r26 != 0) goto L165;
        r26 = 0;
    L165:
        r165 = r16 + 2;
        int r49 = r20 / 3;
        r86[(r49 + r49) + 1] = r102[r273];
        goto L166
    L149:
        if (r62 == 30) goto L160;
        if (r62 == 44) goto L160;
        if (r62 != 50) goto L158;
        int r410 = r16 + 2;
        int r275 = r22 + 1;
        r17[r22] = r20;
        int r222 = r20 / 3;
        int r223 = r222 + r222;
        r86[r223] = r102[r011];
        if (r26 == 0) goto L159;
        r011 = r16 + 3;
        r86[r223 + 1] = r102[r410];
        r22 = r275;
        goto L158
    L159:
        r22 = r275;
        r26 = 0;
        r273 = r410;
    L158:
        r273 = r011;
        goto L170
    L95:
        r73 = r233;
        goto L97
    L87:
        r93 = r23;
        goto L88
    L201:
        return new zzaxz(r122, r86, r13, r14, r0.zzb(), false, r17, r18, r19, r34, r35, r36, r37, r38);
    L22:
        int r87 = r52 + 1;
        int r57 = r2.charAt(r52);
        if (r57 < 55296) goto L29;
        int r58 = r57 & 8191;
        int r103 = 13;
    L25:
        int r113 = r87 + 1;
        char r88 = r2.charAt(r87);
        if (r88 < 55296) goto L28;
        r58 = r58 | ((r88 & 8191) << r103);
        r103 = r103 + 13;
        r87 = r113;
        goto L25
    L28:
        r57 = r58 | (r88 << r103);
        r87 = r113;
    L29:
        int r104 = r87 + 1;
        int r89 = r2.charAt(r87);
        if (r89 < 55296) goto L36;
        int r810 = r89 & 8191;
        int r114 = 13;
    L32:
        int r123 = r104 + 1;
        char r105 = r2.charAt(r104);
        if (r105 < 55296) goto L35;
        r810 = r810 | ((r105 & 8191) << r114);
        r114 = r114 + 13;
        r104 = r123;
        goto L32
    L35:
        r89 = r810 | (r105 << r114);
        r104 = r123;
    L36:
        int r115 = r104 + 1;
        int r106 = r2.charAt(r104);
        if (r106 < 55296) goto L43;
        int r107 = r106 & 8191;
        int r124 = 13;
    L39:
        int r132 = r115 + 1;
        char r116 = r2.charAt(r115);
        if (r116 < 55296) goto L42;
        r107 = r107 | ((r116 & 8191) << r124);
        r124 = r124 + 13;
        r115 = r132;
        goto L39
    L42:
        r106 = r107 | (r116 << r124);
        r115 = r132;
    L43:
        int r125 = r115 + 1;
        int r117 = r2.charAt(r115);
        if (r117 < 55296) goto L50;
        int r118 = r117 & 8191;
        int r133 = 13;
    L46:
        int r142 = r125 + 1;
        char r126 = r2.charAt(r125);
        if (r126 < 55296) goto L49;
        r118 = r118 | ((r126 & 8191) << r133);
        r133 = r133 + 13;
        r125 = r142;
        goto L46
    L49:
        r117 = r118 | (r126 << r133);
        r125 = r142;
    L50:
        int r134 = r125 + 1;
        r12 = r2.charAt(r125);
        if (r12 < 55296) goto L57;
        int r127 = r12 & 8191;
        int r143 = 13;
    L53:
        int r152 = r134 + 1;
        char r135 = r2.charAt(r134);
        if (r135 < 55296) goto L56;
        r127 = r127 | ((r135 & 8191) << r143);
        r143 = r143 + 13;
        r134 = r152;
        goto L53
    L56:
        r12 = r127 | (r135 << r143);
        r134 = r152;
    L57:
        int r144 = r134 + 1;
        int r136 = r2.charAt(r134);
        if (r136 < 55296) goto L64;
        int r137 = r136 & 8191;
        int r153 = 13;
    L60:
        int r166 = r144 + 1;
        char r145 = r2.charAt(r144);
        if (r145 < 55296) goto L63;
        r137 = r137 | ((r145 & 8191) << r153);
        r153 = r153 + 13;
        r144 = r166;
        goto L60
    L63:
        r136 = r137 | (r145 << r153);
        r144 = r166;
    L64:
        int r154 = r144 + 1;
        if (r2.charAt(r144) < 55296) goto L70;
    L66:
        int r146 = r154 + 1;
        if (r2.charAt(r154) < 55296) goto L69;
        r154 = r146;
        goto L66
    L69:
        r154 = r146;
    L70:
        int r147 = r154 + 1;
        int r155 = r2.charAt(r154);
        if (r155 < 55296) goto L77;
        int r156 = r155 & 8191;
        int r167 = 13;
    L73:
        int r172 = r147 + 1;
        char r148 = r2.charAt(r147);
        if (r148 < 55296) goto L76;
        r156 = r156 | ((r148 & 8191) << r167);
        r167 = r167 + 13;
        r147 = r172;
        goto L73
    L76:
        r155 = r156 | (r148 << r167);
        r147 = r172;
    L77:
        r16 = (r57 + r57) + r89;
        int[] r811 = new int[(r155 + r136) + r57];
        r4 = r57;
        r17 = r811;
        r84 = r136;
        r52 = r147;
        r18 = r155;
        r13 = r106;
        r14 = r117;
        goto L78
    L12:
        r8 = 1;
        goto L13
    L202:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r33);
        throw null;
    }

    private static Field zzk(Class r6, String r7) {
        return r6.getDeclaredField(r7);
    L4:
        e = move-exception;
        Field[] r1 = r6.getDeclaredFields();
        int r2 = r1.length;
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L11;
        Field r4 = r1[r3];
        if (r7.equals(r4.getName()) == true) goto L9;
        r3 = r3 + 1;
        goto L6
    L9:
        return r4;
    L11:
        String r62 = r6.getName();
        String r12 = Arrays.toString(r1);
        int r22 = String.valueOf(r7).length() + 11;
        int r32 = r62.length();
        zzzl$$ExternalSyntheticBUOutline1.m(((r22 + r32) + 29) + String.valueOf(r12).length(), r7, r62, r12, e);
        return null;
    }

    private final void zzl(Object r6, Object r7, int r8) {
        if (zzC(r7, r8) == true) goto L5;
        return;
    L5:
        int r0 = zzv(r8) & 1048575;
        Unsafe r1 = zzb;
        long r2 = r0;
        Object r02 = r1.getObject(r7, r2);
        if (r02 == null) goto L20;
        zzayg r72 = zzn(r8);
        if (zzC(r6, r8) == false) goto L10;
        Object r5 = r1.getObject(r6, r2);
        if (zzy(r5) == true) goto L18;
        Object r82 = r72.zza();
        r72.zzd(r82, r5);
        r1.putObject(r6, r2, r82);
        r5 = r82;
    L18:
        r72.zzd(r5, r02);
        return;
    L10:
        if (zzy(r02) == true) goto L12;
        r1.putObject(r6, r2, r02);
    L13:
        zzD(r6, r8);
        return;
    L12:
        Object r4 = r72.zza();
        r72.zzd(r4, r02);
        r1.putObject(r6, r2, r4);
        goto L13
    L20:
        int r52 = this.zzc[r8];
        String r62 = r7.toString();
        zzzl$$ExternalSyntheticBUOutline0.m((String.valueOf(r52).length() + 38) + r62.length(), r52, r62);
    }

    private final void zzm(Object r7, Object r8, int r9) {
        int[] r0 = this.zzc;
        int r1 = r0[r9];
        if (zzE(r8, r1, r9) == true) goto L5;
        return;
    L5:
        int r2 = zzv(r9) & 1048575;
        Unsafe r3 = zzb;
        long r4 = r2;
        Object r22 = r3.getObject(r8, r4);
        if (r22 == null) goto L20;
        zzayg r82 = zzn(r9);
        if (zzE(r7, r1, r9) == false) goto L10;
        Object r6 = r3.getObject(r7, r4);
        if (zzy(r6) == true) goto L18;
        Object r92 = r82.zza();
        r82.zzd(r92, r6);
        r3.putObject(r7, r4, r92);
        r6 = r92;
    L18:
        r82.zzd(r6, r22);
        return;
    L10:
        if (zzy(r22) == true) goto L12;
        r3.putObject(r7, r4, r22);
    L13:
        zzG(r7, r1, r9);
        return;
    L12:
        Object r02 = r82.zza();
        r82.zzd(r02, r22);
        r3.putObject(r7, r4, r02);
        goto L13
    L20:
        int r62 = r0[r9];
        String r72 = r8.toString();
        zzzl$$ExternalSyntheticBUOutline0.m((String.valueOf(r62).length() + 38) + r72.length(), r62, r72);
    }

    private final zzayg zzn(int r3) {
        Object[] r2 = this.zzd;
        int r32 = r3 / 3;
        int r33 = r32 + r32;
        zzayg r0 = (zzayg) r2[r33];
        if (r0 == null) goto L5;
        return r0;
    L5:
        zzayg r02 = zzayd.zza().zzb((Class) r2[r33 + 1]);
        r2[r33] = r02;
        return r02;
    }

    private final Object zzo(int r1) {
        int r12 = r1 / 3;
        return this.zzd[r12 + r12];
    }

    private final zzaxd zzp(int r1) {
        int r12 = r1 / 3;
        return (zzaxd) this.zzd[(r12 + r12) + 1];
    }

    private final Object zzq(Object r4, int r5) {
        zzayg r0 = zzn(r5);
        int r1 = zzv(r5) & 1048575;
        if (zzC(r4, r5) == false) goto L5;
        Object r3 = zzb.getObject(r4, r1);
        if (zzy(r3) == false) goto L9;
        return r3;
    L9:
        Object r42 = r0.zza();
        if (r3 == null) goto L12;
        r0.zzd(r42, r3);
    L12:
        return r42;
    L5:
        return r0.zza();
    }

    private final void zzr(Object r4, int r5, Object r6) {
        zzb.putObject(r4, zzv(r5) & 1048575, r6);
        zzD(r4, r5);
    }

    private final Object zzs(Object r4, int r5, int r6) {
        zzayg r0 = zzn(r6);
        if (zzE(r4, r5, r6) == false) goto L5;
        Object r3 = zzb.getObject(r4, zzv(r6) & 1048575);
        if (zzy(r3) == false) goto L9;
        return r3;
    L9:
        Object r42 = r0.zza();
        if (r3 == null) goto L12;
        r0.zzd(r42, r3);
    L12:
        return r42;
    L5:
        return r0.zza();
    }

    private final void zzt(Object r4, int r5, int r6, Object r7) {
        zzb.putObject(r4, zzv(r6) & 1048575, r7);
        zzG(r4, r5, r6);
    }

    private static boolean zzu(Object r2, int r3, zzayg r4) {
        return r4.zzi(zzayx.zzl(r2, r3 & 1048575));
    }

    private final int zzv(int r1) {
        return this.zzc[r1 + 1];
    }

    private final int zzw(int r1) {
        return this.zzc[r1 + 2];
    }

    private static int zzx(int r0) {
        return (r0 >>> 20) & 255;
    }

    private static boolean zzy(Object r1) {
        if (r1 != null) goto L6;
        return false;
    L6:
        if ((r1 instanceof zzawy) == true) goto L8;
        return true;
    L8:
        return ((zzawy) r1).zzE();
    }

    private static void zzz(Object r1) {
        if (zzy(r1) == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Mutating immutable message: ".concat(String.valueOf(r1)));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final Object zza() {
        return ((zzawy) this.zzg).zzG();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final boolean zzb(Object r7, Object r8) {
        int r1 = 0;
    L4:
        if (r1 >= this.zzc.length) goto L113;
        int r2 = zzv(r1);
        int r4 = zzx(r2);
        if (r4 > 50) goto L8;
    L10:
        long r22 = r2 & 1048575;
        switch(r4) {
            case 0: goto L108;
            case 1: goto L103;
            case 2: goto L98;
            case 3: goto L93;
            case 4: goto L88;
            case 5: goto L83;
            case 6: goto L78;
            case 7: goto L73;
            case 8: goto L68;
            case 9: goto L63;
            case 10: goto L58;
            case 11: goto L53;
            case 12: goto L48;
            case 13: goto L43;
            case 14: goto L38;
            case 15: goto L33;
            case 16: goto L28;
            case 17: goto L23;
            case 18: goto L19;
            case 19: goto L19;
            case 20: goto L19;
            case 21: goto L19;
            case 22: goto L19;
            case 23: goto L19;
            case 24: goto L19;
            case 25: goto L19;
            case 26: goto L19;
            case 27: goto L19;
            case 28: goto L19;
            case 29: goto L19;
            case 30: goto L19;
            case 31: goto L19;
            case 32: goto L19;
            case 33: goto L19;
            case 34: goto L19;
            case 35: goto L19;
            case 36: goto L19;
            case 37: goto L19;
            case 38: goto L19;
            case 39: goto L19;
            case 40: goto L19;
            case 41: goto L19;
            case 42: goto L19;
            case 43: goto L19;
            case 44: goto L19;
            case 45: goto L19;
            case 46: goto L19;
            case 47: goto L19;
            case 48: goto L19;
            case 49: goto L19;
            case 50: goto L18;
            case 51: goto L14;
            case 52: goto L14;
            case 53: goto L14;
            case 54: goto L14;
            case 55: goto L14;
            case 56: goto L14;
            case 57: goto L14;
            case 58: goto L14;
            case 59: goto L14;
            case 60: goto L14;
            case 61: goto L14;
            case 62: goto L14;
            case 63: goto L14;
            case 64: goto L14;
            case 65: goto L14;
            case 66: goto L14;
            case 67: goto L14;
            case 68: goto L14;
            default: goto L111;
        };
    L18:
        boolean r23 = zzayh.zzb(zzayx.zzl(r7, r22), zzayx.zzl(r8, r22));
    L20:
        if (r23 == true) goto L111;
    L112:
        return false;
    L19:
        r23 = zzayh.zzb(zzayx.zzl(r7, r22), zzayx.zzl(r8, r22));
        goto L20
    L14:
        if (zzF(r7, r8, r1) == false) goto L112;
        if (zzayh.zzb(zzayx.zzl(r7, r22), zzayx.zzl(r8, r22)) == false) goto L112;
    L23:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayh.zzb(zzayx.zzl(r7, r22), zzayx.zzl(r8, r22)) == false) goto L112;
    L28:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzd(r7, r22) != zzayx.zzd(r8, r22)) goto L112;
    L33:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzb(r7, r22) != zzayx.zzb(r8, r22)) goto L112;
    L38:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzd(r7, r22) != zzayx.zzd(r8, r22)) goto L112;
    L43:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzb(r7, r22) != zzayx.zzb(r8, r22)) goto L112;
    L48:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzb(r7, r22) != zzayx.zzb(r8, r22)) goto L112;
    L53:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzb(r7, r22) != zzayx.zzb(r8, r22)) goto L112;
    L58:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayh.zzb(zzayx.zzl(r7, r22), zzayx.zzl(r8, r22)) == false) goto L112;
    L63:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayh.zzb(zzayx.zzl(r7, r22), zzayx.zzl(r8, r22)) == false) goto L112;
    L68:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayh.zzb(zzayx.zzl(r7, r22), zzayx.zzl(r8, r22)) == false) goto L112;
    L73:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzf(r7, r22) != zzayx.zzf(r8, r22)) goto L112;
    L78:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzb(r7, r22) != zzayx.zzb(r8, r22)) goto L112;
    L83:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzd(r7, r22) != zzayx.zzd(r8, r22)) goto L112;
    L88:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzb(r7, r22) != zzayx.zzb(r8, r22)) goto L112;
    L93:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzd(r7, r22) != zzayx.zzd(r8, r22)) goto L112;
    L98:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (zzayx.zzd(r7, r22) != zzayx.zzd(r8, r22)) goto L112;
    L103:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (Float.floatToIntBits(zzayx.zzh(r7, r22)) != Float.floatToIntBits(zzayx.zzh(r8, r22))) goto L112;
    L108:
        if (zzA(r7, r8, r1) == false) goto L112;
        if (Double.doubleToLongBits(zzayx.zzj(r7, r22)) != Double.doubleToLongBits(zzayx.zzj(r8, r22))) goto L112;
    L111:
        r1 = r1 + 3;
        goto L4
    L8:
        if (r4 >= 69) goto L10;
    L113:
        int r12 = this.zzk;
    L114:
        int[] r24 = this.zzi;
        if (r12 >= r24.length) goto L127;
        int r25 = r24[r12];
        if (zzF(r7, r8, r25) == false) goto L118;
        if (zzE(r7, 0, r25) == true) goto L125;
        long r42 = zzv(r25) & 1048575;
        if (zzayh.zzb(zzayx.zzl(r7, r42), zzayx.zzl(r8, r42)) == true) goto L125;
        return false;
    L125:
        r12 = r12 + 1;
        goto L114
    L118:
        return false;
    L127:
        if (((zzawy) r7).zzc.equals(((zzawy) r8).zzc) == true) goto L130;
        return false;
    L130:
        if (this.zzh == true) goto L133;
        return true;
    L133:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r7);
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final int zzc(Object r9) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= this.zzc.length) goto L40;
        int r3 = zzv(r1);
        int r5 = zzx(r3);
        if (r5 > 50) goto L8;
    L9:
        long r32 = r3 & 1048575;
        int r6 = 37;
        switch(r5) {
            case 0: goto L38;
            case 1: goto L37;
            case 2: goto L36;
            case 3: goto L35;
            case 4: goto L34;
            case 5: goto L33;
            case 6: goto L32;
            case 7: goto L31;
            case 8: goto L30;
            case 9: goto L27;
            case 10: goto L26;
            case 11: goto L25;
            case 12: goto L24;
            case 13: goto L23;
            case 14: goto L22;
            case 15: goto L21;
            case 16: goto L19;
            case 17: goto L15;
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
            case 50: goto L12;
            default: goto L39;
        };
    L12:
        int r22 = r2 * 53;
        int r33 = zzayx.zzl(r9, r32).hashCode();
    L13:
        r2 = r22 + r33;
        goto L39
    L14:
        r22 = r2 * 53;
        r33 = zzayx.zzl(r9, r32).hashCode();
        goto L13
    L15:
        int r23 = r2 * 53;
        Object r34 = zzayx.zzl(r9, r32);
        if (r34 == null) goto L18;
        r6 = r34.hashCode();
    L18:
        r2 = r23 + r6;
        goto L39
    L19:
        r22 = r2 * 53;
        long r35 = zzayx.zzd(r9, r32);
        byte[] r52 = zzaxi.zza;
    L20:
        r33 = (int) (r35 ^ (r35 >>> 32));
        goto L13
    L21:
        r22 = r2 * 53;
        r33 = zzayx.zzb(r9, r32);
        goto L13
    L22:
        r22 = r2 * 53;
        r35 = zzayx.zzd(r9, r32);
        byte[] r53 = zzaxi.zza;
        goto L20
    L23:
        r22 = r2 * 53;
        r33 = zzayx.zzb(r9, r32);
        goto L13
    L24:
        r22 = r2 * 53;
        r33 = zzayx.zzb(r9, r32);
        goto L13
    L25:
        r22 = r2 * 53;
        r33 = zzayx.zzb(r9, r32);
        goto L13
    L26:
        r22 = r2 * 53;
        r33 = zzayx.zzl(r9, r32).hashCode();
        goto L13
    L27:
        r23 = r2 * 53;
        Object r36 = zzayx.zzl(r9, r32);
        if (r36 == null) goto L18;
        r6 = r36.hashCode();
        goto L18
    L30:
        r22 = r2 * 53;
        r33 = ((String) zzayx.zzl(r9, r32)).hashCode();
        goto L13
    L31:
        r22 = r2 * 53;
        r33 = zzaxi.zza(zzayx.zzf(r9, r32));
        goto L13
    L32:
        r22 = r2 * 53;
        r33 = zzayx.zzb(r9, r32);
        goto L13
    L33:
        r22 = r2 * 53;
        r35 = zzayx.zzd(r9, r32);
        byte[] r54 = zzaxi.zza;
        goto L20
    L34:
        r22 = r2 * 53;
        r33 = zzayx.zzb(r9, r32);
        goto L13
    L35:
        r22 = r2 * 53;
        r35 = zzayx.zzd(r9, r32);
        byte[] r55 = zzaxi.zza;
        goto L20
    L36:
        r22 = r2 * 53;
        r35 = zzayx.zzd(r9, r32);
        byte[] r56 = zzaxi.zza;
        goto L20
    L37:
        r22 = r2 * 53;
        r33 = Float.floatToIntBits(zzayx.zzh(r9, r32));
        goto L13
    L38:
        r22 = r2 * 53;
        r35 = Double.doubleToLongBits(zzayx.zzj(r9, r32));
        byte[] r57 = zzaxi.zza;
    L39:
        r1 = r1 + 3;
        goto L4
    L8:
        if (r5 < 69) goto L39;
    L40:
        int r12 = this.zzk;
    L41:
        int[] r37 = this.zzi;
        if (r12 >= r37.length) goto L47;
        if (zzE(r9, 0, r37[r12]) == true) goto L46;
        r2 = (r2 * 53) + zzayx.zzl(r9, zzv(r3) & 1048575).hashCode();
    L46:
        r12 = r12 + 1;
        goto L41
    L47:
        int r24 = (r2 * 53) + ((zzawy) r9).zzc.hashCode();
        if (this.zzh == true) goto L50;
        return r24;
    L50:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r9);
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final void zzd(Object r9, Object r10) {
        zzz(r9);
        r10.getClass();
        int r0 = 0;
    L3:
        int[] r1 = this.zzc;
        if (r0 >= r1.length) goto L79;
        int r2 = zzv(r0);
        int r3 = 1048575 & r2;
        int r22 = zzx(r2);
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
        zzm(r9, r10, r0);
        goto L78
    L12:
        zzm(r9, r10, r0);
        goto L78
    L16:
        int r13 = zzayh.$r8$clinit;
        zzayx.zzm(r9, r32, zzaxs.zza(zzayx.zzl(r9, r32), zzayx.zzl(r10, r32)));
        goto L78
    L17:
        zzaxh r14 = (zzaxh) zzayx.zzl(r9, r32);
        zzaxh r23 = (zzaxh) zzayx.zzl(r10, r32);
        int r5 = r14.size();
        int r6 = r23.size();
        if (r5 <= 0) goto L24;
        if (r6 <= 0) goto L24;
        if (r14.zza() == true) goto L23;
        r14 = r14.zzf(r6 + r5);
    L23:
        r14.addAll(r23);
    L24:
        if (r5 <= 0) goto L27;
        r23 = r14;
    L27:
        zzayx.zzm(r9, r32, r23);
        goto L78
    L28:
        zzl(r9, r10, r0);
        goto L78
    L50:
        zzl(r9, r10, r0);
        goto L78
    L10:
        if (zzE(r10, r12, r0) == false) goto L78;
        zzayx.zzm(r9, r32, zzayx.zzl(r10, r32));
        zzG(r9, r12, r0);
        goto L78
    L14:
        if (zzE(r10, r12, r0) == false) goto L78;
        zzayx.zzm(r9, r32, zzayx.zzl(r10, r32));
        zzG(r9, r12, r0);
        goto L78
    L30:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zze(r9, r32, zzayx.zzd(r10, r32));
        zzD(r9, r0);
        goto L78
    L33:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zzc(r9, r32, zzayx.zzb(r10, r32));
        zzD(r9, r0);
        goto L78
    L36:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zze(r9, r32, zzayx.zzd(r10, r32));
        zzD(r9, r0);
        goto L78
    L39:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zzc(r9, r32, zzayx.zzb(r10, r32));
        zzD(r9, r0);
        goto L78
    L42:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zzc(r9, r32, zzayx.zzb(r10, r32));
        zzD(r9, r0);
        goto L78
    L45:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zzc(r9, r32, zzayx.zzb(r10, r32));
        zzD(r9, r0);
        goto L78
    L48:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zzm(r9, r32, zzayx.zzl(r10, r32));
        zzD(r9, r0);
        goto L78
    L52:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zzm(r9, r32, zzayx.zzl(r10, r32));
        zzD(r9, r0);
        goto L78
    L55:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zzg(r9, r32, zzayx.zzf(r10, r32));
        zzD(r9, r0);
        goto L78
    L58:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zzc(r9, r32, zzayx.zzb(r10, r32));
        zzD(r9, r0);
        goto L78
    L61:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zze(r9, r32, zzayx.zzd(r10, r32));
        zzD(r9, r0);
        goto L78
    L64:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zzc(r9, r32, zzayx.zzb(r10, r32));
        zzD(r9, r0);
        goto L78
    L67:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zze(r9, r32, zzayx.zzd(r10, r32));
        zzD(r9, r0);
        goto L78
    L70:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zze(r9, r32, zzayx.zzd(r10, r32));
        zzD(r9, r0);
        goto L78
    L73:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zzi(r9, r32, zzayx.zzh(r10, r32));
        zzD(r9, r0);
        goto L78
    L76:
        if (zzC(r10, r0) == false) goto L78;
        zzayx.zzk(r9, r32, zzayx.zzj(r10, r32));
        zzD(r9, r0);
    L78:
        r0 = r0 + 3;
        goto L3
    L79:
        zzayh.zzd(this.zzl, r9, r10);
        if (this.zzh == false) goto L127;
        zzayh.zzc(this.zzm, r9, r10);
        return;
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    public final int zzf(java.lang.Object r36, byte[] r37, int r38, int r39, int r40, com.google.android.gms.internal.mlkit_genai_prompt.zzavr r41) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: MOVE (r3v0 ?? I:??[OBJECT, ARRAY]) = (r37v0 ?? I:??[OBJECT, ARRAY]) A[DECLARE_VAR] in method: com.google.android.gms.internal.mlkit_genai_prompt.zzaxz.zzf(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.mlkit_genai_prompt.zzavr):int, file: classes4.dex
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
            Method dump skipped, instructions count: 3548
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_genai_prompt.zzaxz.zzf(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.mlkit_genai_prompt.zzavr):int");
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final void zzg(Object r8, byte[] r9, int r10, int r11, zzavr r12) {
        zzf(r8, r9, r10, r11, 0, r12);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final void zzh(Object r8) {
        if (zzy(r8) == false) goto L44;
        int r1 = 0;
        if ((r8 instanceof zzawy) == false) goto L8;
        zzawy r0 = (zzawy) r8;
        r0.zzL(Integer.MAX_VALUE);
        r0.zza = 0;
        r0.zzF();
    L8:
        int[] r02 = this.zzc;
    L10:
        if (r1 >= r02.length) goto L30;
        int r2 = zzv(r1);
        int r3 = 1048575 & r2;
        int r22 = zzx(r2);
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
        ((zzaxr) r5).zzd();
        r23.putObject(r8, r32, r5);
        goto L29
    L22:
        ((zzaxh) zzayx.zzl(r8, r32)).zzb();
    L29:
        r1 = r1 + 3;
    L24:
        if (zzE(r8, r02[r1], r1) == false) goto L29;
        zzn(r1).zzh(zzb.getObject(r8, r32));
    L27:
        if (zzC(r8, r1) == false) goto L29;
        zzn(r1).zzh(zzb.getObject(r8, r32));
        goto L29
    L30:
        ((zzawy) r8).zzc.zzd();
        if (this.zzh == true) goto L33;
        return;
    L33:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r8);
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzayg
    public final boolean zzi(Object r16) {
        int r2 = 0;
        int r8 = 0;
        int r0 = 1048575;
    L4:
        if (r8 >= this.zzj) goto L58;
        int r3 = this.zzi[r8];
        int r10 = zzv(r3);
        int[] r11 = this.zzc;
        int r5 = r11[r3 + 2];
        int r12 = r5 & 1048575;
        int r52 = 1 << (r5 >>> 20);
        if (r12 == r0) goto L10;
        if (r12 == 1048575) goto L9;
        r2 = zzb.getInt(r16, r12);
    L9:
        int r4 = r2;
        r0 = r12;
    L12:
        if ((268435456 & r10) == 0) goto L16;
        int r22 = r3;
        int r32 = r0;
        if (zzB(r16, r22, r32, r4, r52) == false) goto L15;
    L17:
        int r122 = zzx(r10);
        if (r122 == 9) goto L52;
        if (r122 == 17) goto L52;
        if (r122 != 27) goto L24;
    L42:
        List r53 = (List) zzayx.zzl(r16, r10 & 1048575);
        if (r53.isEmpty() == true) goto L56;
        zzayg r23 = zzn(r22);
        int r9 = 0;
    L46:
        if (r9 >= r53.size()) goto L56;
        if (r23.zzi(r53.get(r9)) == false) goto L49;
        r9 = r9 + 1;
        goto L46
    L49:
        return false;
    L56:
        r8 = r8 + 1;
        r0 = r32;
        r2 = r4;
        goto L4
    L24:
        if (r122 == 60) goto L38;
        if (r122 == 68) goto L38;
        if (r122 == 49) goto L42;
        if (r122 != 50) goto L56;
        if (((zzaxr) zzayx.zzl(r16, r10 & 1048575)).isEmpty() == true) goto L56;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(zzo(r22));
        throw null;
    L38:
        if (zzE(r16, r11[r22], r22) == false) goto L56;
        if (zzu(r16, r10, zzn(r22)) == true) goto L56;
        return false;
    L52:
        if (zzB(r16, r22, r32, r4, r52) == false) goto L56;
        if (zzu(r16, r10, zzn(r22)) == true) goto L56;
        return false;
    L15:
        return false;
    L16:
        r22 = r3;
        r32 = r0;
        goto L17
    L10:
        r4 = r2;
        goto L12
    L58:
        if (this.zzh == true) goto L60;
        return true;
    L60:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r16);
        throw null;
    }
}
