package com.google.android.recaptcha.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.cast.zzzl$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.cast.zzzl$$ExternalSyntheticBUOutline1;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;
import sun.misc.Unsafe;

/* loaded from: classes5.dex */
final class zzaha<T> implements zzahn<T> {
    private static final int[] zza = null;
    private static final Unsafe zzb = null;
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzagx zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzahz zzm;
    private final zzafi zzn;

    static {
        zza = new int[0];
        zzb = zzaig.zzn();
    }

    private zzaha(int[] r1, Object[] r2, int r3, int r4, zzagx r5, boolean r6, int[] r7, int r8, int r9, zzahc r10, zzago r11, zzahz r12, zzafi r13, zzagt r14) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzi = r5 instanceof zzafv;
        this.zzh = false;
        this.zzj = r7;
        this.zzk = r8;
        this.zzl = r9;
        this.zzm = r12;
        this.zzn = r13;
        this.zzg = r5;
    }

    private final int zzA(int r1) {
        return this.zzc[r1 + 1];
    }

    private final int zzB(int r1) {
        return this.zzc[r1 + 2];
    }

    private static int zzC(int r0) {
        return (r0 >>> 20) & 255;
    }

    private static boolean zzD(int r1) {
        if ((r1 & 536870912) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean zzE(Object r1) {
        if (r1 != null) goto L6;
        return false;
    L6:
        if ((r1 instanceof zzafv) == true) goto L8;
        return true;
    L8:
        return ((zzafv) r1).zzz();
    }

    private static void zzF(Object r1) {
        if (zzE(r1) == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Mutating immutable message: ".concat(String.valueOf(r1)));
    }

    private static int zzG(Object r0, long r1) {
        return ((Integer) zzaig.zzl(r0, r1)).intValue();
    }

    private static long zzH(Object r0, long r1) {
        return ((Long) zzaig.zzl(r0, r1)).longValue();
    }

    private final boolean zzI(Object r1, Object r2, int r3) {
        if (zzK(r1, r3) != zzK(r2, r3)) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean zzJ(Object r2, int r3, int r4, int r5, int r6) {
        if (r4 != 1048575) goto L7;
        return zzK(r2, r3);
    L7:
        if ((r5 & r6) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    private final boolean zzK(Object r8, int r9) {
        int r0 = zzB(r9);
        long r2 = r0 & 1048575;
        if (r2 != 1048575) goto L89;
        int r92 = zzA(r9);
        long r02 = r92 & 1048575;
        switch(zzC(r92)) {
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
        Object r82 = zzaig.zzl(r8, r02);
        if ((r82 instanceof String) == false) goto L51;
        if (((String) r82).isEmpty() == true) goto L49;
        return true;
    L49:
        return false;
    L51:
        if ((r82 instanceof zzaer) == false) goto L57;
        if (zzaer.zza.equals(r82) == true) goto L55;
        return true;
    L55:
        return false;
    L57:
        return zzR();
    L7:
        return zzR();
    L9:
        if (zzaig.zzl(r8, r02) == null) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (zzaig.zzd(r8, r02) == 0) goto L15;
        return true;
    L15:
        return false;
    L17:
        if (zzaig.zzb(r8, r02) == 0) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (zzaig.zzd(r8, r02) == 0) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (zzaig.zzb(r8, r02) == 0) goto L27;
        return true;
    L27:
        return false;
    L29:
        if (zzaig.zzb(r8, r02) == 0) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (zzaig.zzb(r8, r02) == 0) goto L35;
        return true;
    L35:
        return false;
    L37:
        if (zzaer.zza.equals(zzaig.zzl(r8, r02)) == true) goto L39;
        return true;
    L39:
        return false;
    L41:
        if (zzaig.zzl(r8, r02) == null) goto L43;
        return true;
    L43:
        return false;
    L59:
        return zzaig.zzf(r8, r02);
    L61:
        if (zzaig.zzb(r8, r02) == 0) goto L63;
        return true;
    L63:
        return false;
    L65:
        if (zzaig.zzd(r8, r02) == 0) goto L67;
        return true;
    L67:
        return false;
    L69:
        if (zzaig.zzb(r8, r02) == 0) goto L71;
        return true;
    L71:
        return false;
    L73:
        if (zzaig.zzd(r8, r02) == 0) goto L75;
        return true;
    L75:
        return false;
    L77:
        if (zzaig.zzd(r8, r02) == 0) goto L79;
        return true;
    L79:
        return false;
    L81:
        if (Float.floatToRawIntBits(zzaig.zzh(r8, r02)) == 0) goto L83;
        return true;
    L83:
        return false;
    L85:
        if (Double.doubleToRawLongBits(zzaig.zzj(r8, r02)) == 0) goto L87;
        return true;
    L87:
        return false;
    L89:
        if (((1 << (r0 >>> 20)) & zzaig.zzb(r8, r2)) == 0) goto L91;
        return true;
    L91:
        return false;
    }

    private final void zzL(Object r5, int r6) {
        int r4 = zzB(r6);
        long r0 = 1048575 & r4;
        if (r0 != 1048575) goto L5;
        return;
    L5:
        zzaig.zzc(r5, r0, (1 << (r4 >>> 20)) | zzaig.zzb(r5, r0));
    }

    private final boolean zzM(Object r3, int r4, int r5) {
        if (zzaig.zzb(r3, zzB(r5) & 1048575) != r4) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean zzN(Object r3, Object r4, int r5) {
        long r0 = zzB(r5) & 1048575;
        if (zzaig.zzb(r3, r0) != zzaig.zzb(r4, r0)) goto L6;
        return true;
    L6:
        return false;
    }

    private final void zzO(Object r3, int r4, int r5) {
        zzaig.zzc(r3, zzB(r5) & 1048575, r4);
    }

    private final int zzP(int r2) {
        if (r2 >= this.zze) goto L5;
        return -1;
    L5:
        if (r2 <= this.zzf) goto L7;
        return -1;
    L7:
        return zzQ(r2, 0);
    }

    private final int zzQ(int r6, int r7) {
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

    private boolean zzR() {
        throw new IllegalArgumentException();
    }

    private static final void zzS(int r1, Object r2, zzafb r3) {
        if ((r2 instanceof String) == false) goto L6;
        r3.zzm(r1, (String) r2);
        return;
    L6:
        r3.zzn(r1, (zzaer) r2);
    }

    public static zzaia zzh(Object r2) {
        zzafv r22 = (zzafv) r2;
        zzaia r0 = r22.zzc;
        if (r0 != zzaia.zza()) goto L6;
        zzaia r02 = zzaia.zzb();
        r22.zzc = r02;
        return r02;
    L6:
        return r0;
    }

    public static zzaha zzm(Class r32, zzagv r33, zzahc r34, zzago r35, zzahz r36, zzafi r37, zzagt r38) {
        Unsafe r1 = zzb;
        if (r1 != null) goto L5;
        GlShader$$ExternalSyntheticBUOutline1.m("Lite gencode is primarily intended for Android use and uses sun.misc.Unsafe which is not available in the current environment. To run in this environment, you may need to switch to standard gencode.");
        return null;
    L5:
        if ((r33 instanceof zzahi) == false) goto L202;
        zzahi r0 = (zzahi) r33;
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
        zzahi r252 = r0;
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
        r43 = zzn(r112, (String) r42);
        r102[r78] = r43;
        goto L135
    L129:
        r310 = zzn(r112, (String) r39);
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
        Field r314 = zzn(r112, (String) r102[r16]);
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
        r013 = zzn(r112, (String) r012);
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
        return new zzaha(r122, r86, r13, r14, r0.zzb(), false, r17, r18, r19, r34, r35, r36, r37, r38);
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

    private static Field zzn(Class r6, String r7) {
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

    private final void zzo(Object r6, Object r7, int r8) {
        if (zzK(r7, r8) == true) goto L5;
        return;
    L5:
        int r0 = zzA(r8) & 1048575;
        Unsafe r1 = zzb;
        long r2 = r0;
        Object r02 = r1.getObject(r7, r2);
        if (r02 == null) goto L20;
        zzahn r72 = zzq(r8);
        if (zzK(r6, r8) == false) goto L10;
        Object r5 = r1.getObject(r6, r2);
        if (zzE(r5) == true) goto L18;
        Object r82 = r72.zza();
        r72.zzd(r82, r5);
        r1.putObject(r6, r2, r82);
        r5 = r82;
    L18:
        r72.zzd(r5, r02);
        return;
    L10:
        if (zzE(r02) == true) goto L12;
        r1.putObject(r6, r2, r02);
    L13:
        zzL(r6, r8);
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

    private final void zzp(Object r7, Object r8, int r9) {
        int[] r0 = this.zzc;
        int r1 = r0[r9];
        if (zzM(r8, r1, r9) == true) goto L5;
        return;
    L5:
        int r2 = zzA(r9) & 1048575;
        Unsafe r3 = zzb;
        long r4 = r2;
        Object r22 = r3.getObject(r8, r4);
        if (r22 == null) goto L20;
        zzahn r82 = zzq(r9);
        if (zzM(r7, r1, r9) == false) goto L10;
        Object r6 = r3.getObject(r7, r4);
        if (zzE(r6) == true) goto L18;
        Object r92 = r82.zza();
        r82.zzd(r92, r6);
        r3.putObject(r7, r4, r92);
        r6 = r92;
    L18:
        r82.zzd(r6, r22);
        return;
    L10:
        if (zzE(r22) == true) goto L12;
        r3.putObject(r7, r4, r22);
    L13:
        zzO(r7, r1, r9);
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

    private final zzahn zzq(int r3) {
        Object[] r2 = this.zzd;
        int r32 = r3 / 3;
        int r33 = r32 + r32;
        zzahn r0 = (zzahn) r2[r33];
        if (r0 == null) goto L5;
        return r0;
    L5:
        zzahn r02 = zzahg.zza().zzb((Class) r2[r33 + 1]);
        r2[r33] = r02;
        return r02;
    }

    private final Object zzr(int r1) {
        int r12 = r1 / 3;
        return this.zzd[r12 + r12];
    }

    private final zzaga zzs(int r1) {
        int r12 = r1 / 3;
        return (zzaga) this.zzd[(r12 + r12) + 1];
    }

    private final Object zzt(Object r4, int r5) {
        zzahn r0 = zzq(r5);
        int r1 = zzA(r5) & 1048575;
        if (zzK(r4, r5) == false) goto L5;
        Object r3 = zzb.getObject(r4, r1);
        if (zzE(r3) == false) goto L9;
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

    private final void zzu(Object r4, int r5, Object r6) {
        zzb.putObject(r4, zzA(r5) & 1048575, r6);
        zzL(r4, r5);
    }

    private final Object zzv(Object r4, int r5, int r6) {
        zzahn r0 = zzq(r6);
        if (zzM(r4, r5, r6) == false) goto L5;
        Object r3 = zzb.getObject(r4, zzA(r6) & 1048575);
        if (zzE(r3) == false) goto L9;
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

    private final void zzw(Object r4, int r5, int r6, Object r7) {
        zzb.putObject(r4, zzA(r6) & 1048575, r7);
        zzO(r4, r5, r6);
    }

    private final Object zzx(Object r1, int r2, Object r3, zzahz r4, Object r5) {
        int r42 = this.zzc[r2];
        Object r12 = zzaig.zzl(r1, zzA(r2) & 1048575);
        if (r12 != null) goto L6;
    L7:
        return r3;
    L6:
        if (zzs(r2) == null) goto L7;
        zzags r13 = (zzags) r12;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(zzr(r2));
        throw null;
    }

    private static boolean zzy(Object r2, int r3, zzahn r4) {
        return r4.zzl(zzaig.zzl(r2, r3 & 1048575));
    }

    private final void zzz(Object r4, int r5, zzaew r6) {
        long r1 = r5 & 1048575;
        if (zzD(r5) == false) goto L7;
        zzaig.zzm(r4, r1, r6.zzm());
        return;
    L7:
        if (this.zzi == false) goto L10;
        zzaig.zzm(r4, r1, r6.zzl());
        return;
    L10:
        zzaig.zzm(r4, r1, r6.zzp());
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final Object zza() {
        return ((zzafv) this.zzg).zzC();
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final boolean zzb(Object r7, Object r8) {
        int r1 = 0;
    L4:
        if (r1 >= this.zzc.length) goto L113;
        int r2 = zzA(r1);
        int r4 = zzC(r2);
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
        boolean r23 = zzaho.zzB(zzaig.zzl(r7, r22), zzaig.zzl(r8, r22));
    L20:
        if (r23 == true) goto L111;
    L112:
        return false;
    L19:
        r23 = zzaho.zzB(zzaig.zzl(r7, r22), zzaig.zzl(r8, r22));
        goto L20
    L14:
        if (zzN(r7, r8, r1) == false) goto L112;
        if (zzaho.zzB(zzaig.zzl(r7, r22), zzaig.zzl(r8, r22)) == false) goto L112;
    L23:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaho.zzB(zzaig.zzl(r7, r22), zzaig.zzl(r8, r22)) == false) goto L112;
    L28:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzd(r7, r22) != zzaig.zzd(r8, r22)) goto L112;
    L33:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzb(r7, r22) != zzaig.zzb(r8, r22)) goto L112;
    L38:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzd(r7, r22) != zzaig.zzd(r8, r22)) goto L112;
    L43:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzb(r7, r22) != zzaig.zzb(r8, r22)) goto L112;
    L48:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzb(r7, r22) != zzaig.zzb(r8, r22)) goto L112;
    L53:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzb(r7, r22) != zzaig.zzb(r8, r22)) goto L112;
    L58:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaho.zzB(zzaig.zzl(r7, r22), zzaig.zzl(r8, r22)) == false) goto L112;
    L63:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaho.zzB(zzaig.zzl(r7, r22), zzaig.zzl(r8, r22)) == false) goto L112;
    L68:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaho.zzB(zzaig.zzl(r7, r22), zzaig.zzl(r8, r22)) == false) goto L112;
    L73:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzf(r7, r22) != zzaig.zzf(r8, r22)) goto L112;
    L78:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzb(r7, r22) != zzaig.zzb(r8, r22)) goto L112;
    L83:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzd(r7, r22) != zzaig.zzd(r8, r22)) goto L112;
    L88:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzb(r7, r22) != zzaig.zzb(r8, r22)) goto L112;
    L93:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzd(r7, r22) != zzaig.zzd(r8, r22)) goto L112;
    L98:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzaig.zzd(r7, r22) != zzaig.zzd(r8, r22)) goto L112;
    L103:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (Float.floatToIntBits(zzaig.zzh(r7, r22)) != Float.floatToIntBits(zzaig.zzh(r8, r22))) goto L112;
    L108:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (Double.doubleToLongBits(zzaig.zzj(r7, r22)) != Double.doubleToLongBits(zzaig.zzj(r8, r22))) goto L112;
    L111:
        r1 = r1 + 3;
        goto L4
    L8:
        if (r4 >= 69) goto L10;
    L113:
        int r12 = this.zzl;
    L114:
        int[] r24 = this.zzj;
        if (r12 >= r24.length) goto L127;
        int r25 = r24[r12];
        if (zzN(r7, r8, r25) == false) goto L118;
        if (zzM(r7, 0, r25) == true) goto L125;
        long r42 = zzA(r25) & 1048575;
        if (zzaho.zzB(zzaig.zzl(r7, r42), zzaig.zzl(r8, r42)) == true) goto L125;
        return false;
    L125:
        r12 = r12 + 1;
        goto L114
    L118:
        return false;
    L127:
        if (((zzafv) r7).zzc.equals(((zzafv) r8).zzc) == true) goto L130;
        return false;
    L130:
        if (this.zzh == true) goto L133;
        return true;
    L133:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r7);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final int zzc(Object r9) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= this.zzc.length) goto L40;
        int r3 = zzA(r1);
        int r5 = zzC(r3);
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
        int r33 = zzaig.zzl(r9, r32).hashCode();
    L13:
        r2 = r22 + r33;
        goto L39
    L14:
        r22 = r2 * 53;
        r33 = zzaig.zzl(r9, r32).hashCode();
        goto L13
    L15:
        int r23 = r2 * 53;
        Object r34 = zzaig.zzl(r9, r32);
        if (r34 == null) goto L18;
        r6 = r34.hashCode();
    L18:
        r2 = r23 + r6;
        goto L39
    L19:
        r22 = r2 * 53;
        long r35 = zzaig.zzd(r9, r32);
        byte[] r52 = zzagf.zza;
    L20:
        r33 = (int) (r35 ^ (r35 >>> 32));
        goto L13
    L21:
        r22 = r2 * 53;
        r33 = zzaig.zzb(r9, r32);
        goto L13
    L22:
        r22 = r2 * 53;
        r35 = zzaig.zzd(r9, r32);
        byte[] r53 = zzagf.zza;
        goto L20
    L23:
        r22 = r2 * 53;
        r33 = zzaig.zzb(r9, r32);
        goto L13
    L24:
        r22 = r2 * 53;
        r33 = zzaig.zzb(r9, r32);
        goto L13
    L25:
        r22 = r2 * 53;
        r33 = zzaig.zzb(r9, r32);
        goto L13
    L26:
        r22 = r2 * 53;
        r33 = zzaig.zzl(r9, r32).hashCode();
        goto L13
    L27:
        r23 = r2 * 53;
        Object r36 = zzaig.zzl(r9, r32);
        if (r36 == null) goto L18;
        r6 = r36.hashCode();
        goto L18
    L30:
        r22 = r2 * 53;
        r33 = ((String) zzaig.zzl(r9, r32)).hashCode();
        goto L13
    L31:
        r22 = r2 * 53;
        r33 = zzagf.zzb(zzaig.zzf(r9, r32));
        goto L13
    L32:
        r22 = r2 * 53;
        r33 = zzaig.zzb(r9, r32);
        goto L13
    L33:
        r22 = r2 * 53;
        r35 = zzaig.zzd(r9, r32);
        byte[] r54 = zzagf.zza;
        goto L20
    L34:
        r22 = r2 * 53;
        r33 = zzaig.zzb(r9, r32);
        goto L13
    L35:
        r22 = r2 * 53;
        r35 = zzaig.zzd(r9, r32);
        byte[] r55 = zzagf.zza;
        goto L20
    L36:
        r22 = r2 * 53;
        r35 = zzaig.zzd(r9, r32);
        byte[] r56 = zzagf.zza;
        goto L20
    L37:
        r22 = r2 * 53;
        r33 = Float.floatToIntBits(zzaig.zzh(r9, r32));
        goto L13
    L38:
        r22 = r2 * 53;
        r35 = Double.doubleToLongBits(zzaig.zzj(r9, r32));
        byte[] r57 = zzagf.zza;
    L39:
        r1 = r1 + 3;
        goto L4
    L8:
        if (r5 < 69) goto L39;
    L40:
        int r12 = this.zzl;
    L41:
        int[] r37 = this.zzj;
        if (r12 >= r37.length) goto L47;
        if (zzM(r9, 0, r37[r12]) == true) goto L46;
        r2 = (r2 * 53) + zzaig.zzl(r9, zzA(r3) & 1048575).hashCode();
    L46:
        r12 = r12 + 1;
        goto L41
    L47:
        int r24 = (r2 * 53) + ((zzafv) r9).zzc.hashCode();
        if (this.zzh == true) goto L50;
        return r24;
    L50:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r9);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final void zzd(Object r9, Object r10) {
        zzF(r9);
        r10.getClass();
        int r0 = 0;
    L3:
        int[] r1 = this.zzc;
        if (r0 >= r1.length) goto L79;
        int r2 = zzA(r0);
        int r3 = 1048575 & r2;
        int r22 = zzC(r2);
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
        zzp(r9, r10, r0);
        goto L78
    L12:
        zzp(r9, r10, r0);
        goto L78
    L16:
        int r13 = zzaho.$r8$clinit;
        zzaig.zzm(r9, r32, zzagt.zzb(zzaig.zzl(r9, r32), zzaig.zzl(r10, r32)));
        goto L78
    L17:
        zzage r14 = (zzage) zzaig.zzl(r9, r32);
        zzage r23 = (zzage) zzaig.zzl(r10, r32);
        int r5 = r14.size();
        int r6 = r23.size();
        if (r5 <= 0) goto L24;
        if (r6 <= 0) goto L24;
        if (r14.zza() == true) goto L23;
        r14 = r14.zzg(r6 + r5);
    L23:
        r14.addAll(r23);
    L24:
        if (r5 <= 0) goto L27;
        r23 = r14;
    L27:
        zzaig.zzm(r9, r32, r23);
        goto L78
    L28:
        zzo(r9, r10, r0);
        goto L78
    L50:
        zzo(r9, r10, r0);
        goto L78
    L10:
        if (zzM(r10, r12, r0) == false) goto L78;
        zzaig.zzm(r9, r32, zzaig.zzl(r10, r32));
        zzO(r9, r12, r0);
        goto L78
    L14:
        if (zzM(r10, r12, r0) == false) goto L78;
        zzaig.zzm(r9, r32, zzaig.zzl(r10, r32));
        zzO(r9, r12, r0);
        goto L78
    L30:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zze(r9, r32, zzaig.zzd(r10, r32));
        zzL(r9, r0);
        goto L78
    L33:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zzc(r9, r32, zzaig.zzb(r10, r32));
        zzL(r9, r0);
        goto L78
    L36:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zze(r9, r32, zzaig.zzd(r10, r32));
        zzL(r9, r0);
        goto L78
    L39:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zzc(r9, r32, zzaig.zzb(r10, r32));
        zzL(r9, r0);
        goto L78
    L42:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zzc(r9, r32, zzaig.zzb(r10, r32));
        zzL(r9, r0);
        goto L78
    L45:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zzc(r9, r32, zzaig.zzb(r10, r32));
        zzL(r9, r0);
        goto L78
    L48:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zzm(r9, r32, zzaig.zzl(r10, r32));
        zzL(r9, r0);
        goto L78
    L52:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zzm(r9, r32, zzaig.zzl(r10, r32));
        zzL(r9, r0);
        goto L78
    L55:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zzg(r9, r32, zzaig.zzf(r10, r32));
        zzL(r9, r0);
        goto L78
    L58:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zzc(r9, r32, zzaig.zzb(r10, r32));
        zzL(r9, r0);
        goto L78
    L61:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zze(r9, r32, zzaig.zzd(r10, r32));
        zzL(r9, r0);
        goto L78
    L64:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zzc(r9, r32, zzaig.zzb(r10, r32));
        zzL(r9, r0);
        goto L78
    L67:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zze(r9, r32, zzaig.zzd(r10, r32));
        zzL(r9, r0);
        goto L78
    L70:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zze(r9, r32, zzaig.zzd(r10, r32));
        zzL(r9, r0);
        goto L78
    L73:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zzi(r9, r32, zzaig.zzh(r10, r32));
        zzL(r9, r0);
        goto L78
    L76:
        if (zzK(r10, r0) == false) goto L78;
        zzaig.zzk(r9, r32, zzaig.zzj(r10, r32));
        zzL(r9, r0);
    L78:
        r0 = r0 + 3;
        goto L3
    L79:
        zzaho.zzD(this.zzm, r9, r10);
        if (this.zzh == false) goto L127;
        zzaho.zzC(this.zzn, r9, r10);
        return;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final int zze(Object r18) {
        zzaha<T> r0 = this;
        Object r1 = r18;
        Unsafe r6 = zzb;
        int r2 = 0;
        int r4 = 0;
        int r9 = 0;
        int r3 = 1048575;
    L3:
        int[] r5 = r0.zzc;
        if (r2 >= r5.length) goto L279;
        int r10 = r0.zzA(r2);
        int r12 = zzC(r10);
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
        if (r12 < zzafn.zzJ.zza()) goto L17;
        zzafn.zzW.zza();
    L17:
        long r142 = r102;
        switch(r12) {
            case 0: goto L276;
            case 1: goto L273;
            case 2: goto L270;
            case 3: goto L267;
            case 4: goto L264;
            case 5: goto L261;
            case 6: goto L258;
            case 7: goto L255;
            case 8: goto L249;
            case 9: goto L246;
            case 10: goto L242;
            case 11: goto L239;
            case 12: goto L236;
            case 13: goto L232;
            case 14: goto L228;
            case 15: goto L225;
            case 16: goto L219;
            case 17: goto L216;
            case 18: goto L214;
            case 19: goto L213;
            case 20: goto L209;
            case 21: goto L205;
            case 22: goto L201;
            case 23: goto L200;
            case 24: goto L199;
            case 25: goto L195;
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
            default: goto L278;
        };
    L82:
        zzags r54 = (zzags) r6.getObject(r1, r142);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.zzr(r2));
        if (r54.isEmpty() == true) goto L278;
        Iterator r55 = r54.entrySet().iterator();
        if (r55.hasNext() == false) goto L278;
        Map.Entry r02 = (Map.Entry) r55.next();
        r02.getKey();
        r02.getValue();
        throw null;
    L89:
        List r56 = (List) r6.getObject(r1, r142);
        zzahn r103 = r0.zzq(r2);
        int r11 = zzaho.$r8$clinit;
        int r112 = r56.size();
        if (r112 != 0) goto L92;
        int r143 = 0;
    L95:
        r9 = r9 + r143;
        goto L278
    L92:
        int r122 = 0;
        r143 = 0;
    L93:
        if (r122 >= r112) goto L95;
        r143 = r143 + zzaho.zzz(r13, (zzagx) r56.get(r122), r103);
        r122 = r122 + 1;
        goto L93
    L96:
        int r57 = zzaho.zzq((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        int r104 = zzafa.zzu(r13 << 3);
        int r113 = zzafa.zzu(r57);
    L99:
        int r105 = (r104 + r113) + r57;
    L100:
        r9 = r9 + r105;
        goto L278
    L101:
        r57 = zzaho.zzu((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L104:
        r57 = zzaho.zzx((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L107:
        r57 = zzaho.zzv((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L110:
        r57 = zzaho.zzr((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L113:
        r57 = zzaho.zzt((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L116:
        List r58 = (List) r6.getObject(r1, r142);
        int r106 = zzaho.$r8$clinit;
        r57 = r58.size();
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L119:
        r57 = zzaho.zzv((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L122:
        r57 = zzaho.zzx((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L125:
        r57 = zzaho.zzs((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L128:
        r57 = zzaho.zzp((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L131:
        r57 = zzaho.zzo((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L134:
        r57 = zzaho.zzv((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L137:
        r57 = zzaho.zzx((List) r6.getObject(r1, r142));
        if (r57 <= 0) goto L278;
        r104 = zzafa.zzu(r13 << 3);
        r113 = zzafa.zzu(r57);
        goto L99
    L140:
        List r59 = (List) r6.getObject(r1, r142);
        int r107 = zzaho.$r8$clinit;
        int r108 = r59.size();
        if (r108 != 0) goto L143;
    L142:
        int r510 = 0;
    L23:
        r9 = r9 + r510;
        goto L278
    L143:
        int r511 = zzaho.zzq(r59);
        int r114 = zzafa.zzu(r13 << 3);
    L144:
        int r109 = r108 * r114;
    L27:
        r510 = r511 + r109;
        goto L23
    L145:
        List r512 = (List) r6.getObject(r1, r142);
        int r1010 = zzaho.$r8$clinit;
        r108 = r512.size();
        if (r108 == 0) goto L142;
        r511 = zzaho.zzu(r512);
        r114 = zzafa.zzu(r13 << 3);
        goto L144
    L149:
        r510 = zzaho.zzy(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L150:
        r510 = zzaho.zzw(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L151:
        List r513 = (List) r6.getObject(r1, r142);
        int r1011 = zzaho.$r8$clinit;
        r108 = r513.size();
        if (r108 == 0) goto L142;
        r511 = zzaho.zzr(r513);
        r114 = zzafa.zzu(r13 << 3);
        goto L144
    L155:
        List r514 = (List) r6.getObject(r1, r142);
        int r1012 = zzaho.$r8$clinit;
        r108 = r514.size();
        if (r108 == 0) goto L142;
        r511 = zzaho.zzt(r514);
        r114 = zzafa.zzu(r13 << 3);
        goto L144
    L159:
        List r515 = (List) r6.getObject(r1, r142);
        int r1013 = zzaho.$r8$clinit;
        int r1014 = r515.size();
        if (r1014 != 0) goto L162;
        r105 = 0;
        goto L100
    L162:
        r105 = r1014 * zzafa.zzu(r13 << 3);
        int r115 = 0;
    L164:
        if (r115 >= r515.size()) goto L100;
        int r123 = ((zzaer) r515.get(r115)).zzb();
        r105 = r105 + (zzafa.zzu(r123) + r123);
        r115 = r115 + 1;
        goto L164
    L166:
        List r516 = (List) r6.getObject(r1, r142);
        zzahn r1015 = r0.zzq(r2);
        int r116 = zzaho.$r8$clinit;
        int r117 = r516.size();
        if (r117 != 0) goto L169;
        int r124 = 0;
    L172:
        r9 = r9 + r124;
        goto L278
    L169:
        r124 = zzafa.zzu(r13 << 3) * r117;
        int r132 = 0;
    L170:
        if (r132 >= r117) goto L172;
        int r144 = ((zzaec) r516.get(r132)).zzx(r1015);
        r124 = r124 + (zzafa.zzu(r144) + r144);
        r132 = r132 + 1;
        goto L170
    L173:
        List r517 = (List) r6.getObject(r1, r142);
        int r1016 = zzaho.$r8$clinit;
        int r1017 = r517.size();
        if (r1017 != 0) goto L176;
    L175:
        int r118 = 0;
    L194:
        r9 = r9 + r118;
        goto L278
    L176:
        r118 = zzafa.zzu(r13 << 3) * r1017;
        if ((r517 instanceof zzagn) == false) goto L186;
        zzagn r518 = (zzagn) r517;
        int r125 = 0;
    L179:
        if (r125 >= r1017) goto L194;
        Object r133 = r518.zzc();
        if ((r133 instanceof zzaer) == false) goto L184;
        int r134 = ((zzaer) r133).zzb();
        int r145 = zzafa.zzu(r134);
    L183:
        r118 = r118 + (r145 + r134);
        r125 = r125 + 1;
        goto L179
    L184:
        int r146 = zzail.$r8$clinit;
        r134 = zzaij.zzb((String) r133);
        r145 = zzafa.zzu(r134);
        goto L183
    L186:
        int r126 = 0;
    L187:
        if (r126 >= r1017) goto L194;
        Object r135 = r517.get(r126);
        if ((r135 instanceof zzaer) == false) goto L192;
        int r136 = ((zzaer) r135).zzb();
        int r147 = zzafa.zzu(r136);
    L191:
        r118 = r118 + (r147 + r136);
        r126 = r126 + 1;
        goto L187
    L192:
        int r148 = zzail.$r8$clinit;
        r136 = zzaij.zzb((String) r135);
        r147 = zzafa.zzu(r136);
        goto L191
    L195:
        List r519 = (List) r6.getObject(r1, r142);
        int r1018 = zzaho.$r8$clinit;
        int r520 = r519.size();
        if (r520 == 0) goto L142;
        r510 = r520 * (zzafa.zzu(r13 << 3) + 1);
        goto L23
    L199:
        r510 = zzaho.zzw(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L200:
        r510 = zzaho.zzy(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L201:
        List r521 = (List) r6.getObject(r1, r142);
        int r1019 = zzaho.$r8$clinit;
        r108 = r521.size();
        if (r108 == 0) goto L142;
        r511 = zzaho.zzs(r521);
        r114 = zzafa.zzu(r13 << 3);
        goto L144
    L205:
        List r522 = (List) r6.getObject(r1, r142);
        int r1020 = zzaho.$r8$clinit;
        r108 = r522.size();
        if (r108 == 0) goto L142;
        r511 = zzaho.zzp(r522);
        r114 = zzafa.zzu(r13 << 3);
        goto L144
    L209:
        List r523 = (List) r6.getObject(r1, r142);
        int r1021 = zzaho.$r8$clinit;
        if (r523.size() == 0) goto L175;
        r118 = zzaho.zzo(r523) + (r523.size() * zzafa.zzu(r13 << 3));
        goto L194
    L213:
        r510 = zzaho.zzw(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L214:
        r510 = zzaho.zzy(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L21:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        r510 = zzaho.zzz(r13, (zzagx) r6.getObject(r1, r142), r0.zzq(r2));
        goto L23
    L25:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        long r119 = zzH(r1, r142);
        r511 = zzafa.zzu(r13 << 3);
        r109 = zzafa.zzv((r119 >> 63) ^ (r119 + r119));
        goto L27
    L29:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        int r1022 = zzG(r1, r142);
        r511 = zzafa.zzu(r13 << 3);
        r109 = zzafa.zzu((r1022 >> 31) ^ (r1022 + r1022));
        goto L27
    L32:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        int r524 = zzafa.zzu(r13 << 3);
    L34:
        r510 = r524 + 8;
        goto L23
    L36:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        int r525 = zzafa.zzu(r13 << 3);
    L38:
        r510 = r525 + 4;
        goto L23
    L40:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        long r1023 = zzG(r1, r142);
        r511 = zzafa.zzu(r13 << 3);
        r109 = zzafa.zzv(r1023);
        goto L27
    L43:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        int r1024 = zzG(r1, r142);
        r511 = zzafa.zzu(r13 << 3);
        r109 = zzafa.zzu(r1024);
        goto L27
    L46:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        zzaer r1025 = (zzaer) r6.getObject(r1, r142);
        int r526 = zzafa.zzu(r13 << 3);
        int r1026 = r1025.zzb();
        int r1110 = zzafa.zzu(r1026);
    L48:
        r510 = r526 + (r1110 + r1026);
        goto L23
    L50:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        Object r1027 = r6.getObject(r1, r142);
        zzahn r1111 = r0.zzq(r2);
        int r127 = zzaho.$r8$clinit;
        r526 = zzafa.zzu(r13 << 3);
        r1026 = ((zzaec) r1027).zzx(r1111);
        r1110 = zzafa.zzu(r1026);
        goto L48
    L53:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        int r527 = r13 << 3;
        Object r1028 = r6.getObject(r1, r142);
        if ((r1028 instanceof zzaer) == false) goto L57;
        r526 = zzafa.zzu(r527);
        r1026 = ((zzaer) r1028).zzb();
        r1110 = zzafa.zzu(r1026);
        goto L48
    L57:
        r526 = zzafa.zzu(r527);
        int r1112 = zzail.$r8$clinit;
        r1026 = zzaij.zzb((String) r1028);
        r1110 = zzafa.zzu(r1026);
        goto L48
    L59:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        r510 = zzafa.zzu(r13 << 3) + 1;
        goto L23
    L62:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        r525 = zzafa.zzu(r13 << 3);
        goto L38
    L65:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        r524 = zzafa.zzu(r13 << 3);
        goto L34
    L68:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        long r1029 = zzG(r1, r142);
        r511 = zzafa.zzu(r13 << 3);
        r109 = zzafa.zzv(r1029);
        goto L27
    L71:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        long r1030 = zzH(r1, r142);
        r511 = zzafa.zzu(r13 << 3);
        r109 = zzafa.zzv(r1030);
        goto L27
    L74:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        long r1031 = zzH(r1, r142);
        r511 = zzafa.zzu(r13 << 3);
        r109 = zzafa.zzv(r1031);
        goto L27
    L77:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        r525 = zzafa.zzu(r13 << 3);
        goto L38
    L80:
        if (r0.zzM(r1, r13, r2) == false) goto L278;
        r524 = zzafa.zzu(r13 << 3);
        goto L34
    L216:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L278;
        r510 = zzaho.zzz(r13, (zzagx) r6.getObject(r1, r142), r0.zzq(r2));
        goto L23
    L219:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        long r1113 = r6.getLong(r1, r142);
        int r03 = zzafa.zzu(r13 << 3);
        int r528 = zzafa.zzv((r1113 >> 63) ^ (r1113 + r1113));
    L221:
        int r04 = r03 + r528;
    L222:
        r9 = r9 + r04;
    L223:
        r0 = this;
        goto L278
    L225:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        int r529 = r6.getInt(r1, r142);
        r03 = zzafa.zzu(r13 << 3);
        r528 = zzafa.zzu((r529 >> 31) ^ (r529 + r529));
        goto L221
    L228:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        int r05 = zzafa.zzu(r13 << 3);
    L230:
        r04 = r05 + 8;
        goto L222
    L232:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        int r06 = zzafa.zzu(r13 << 3);
    L234:
        r04 = r06 + 4;
        goto L222
    L236:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        long r1032 = r6.getInt(r1, r142);
        r03 = zzafa.zzu(r13 << 3);
        r528 = zzafa.zzv(r1032);
        goto L221
    L239:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        int r530 = r6.getInt(r1, r142);
        r03 = zzafa.zzu(r13 << 3);
        r528 = zzafa.zzu(r530);
        goto L221
    L242:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        zzaer r531 = (zzaer) r6.getObject(r1, r142);
        int r07 = zzafa.zzu(r13 << 3);
        int r532 = r531.zzb();
        int r1033 = zzafa.zzu(r532);
    L244:
        r04 = r07 + (r1033 + r532);
        goto L222
    L246:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L278;
        Object r1034 = r6.getObject(r1, r142);
        zzahn r1114 = r0.zzq(r2);
        int r128 = zzaho.$r8$clinit;
        r526 = zzafa.zzu(r13 << 3);
        r1026 = ((zzaec) r1034).zzx(r1114);
        r1110 = zzafa.zzu(r1026);
        goto L48
    L249:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        int r08 = r13 << 3;
        Object r533 = r6.getObject(r1, r142);
        if ((r533 instanceof zzaer) == false) goto L253;
        r07 = zzafa.zzu(r08);
        r532 = ((zzaer) r533).zzb();
        r1033 = zzafa.zzu(r532);
        goto L244
    L253:
        r07 = zzafa.zzu(r08);
        int r1035 = zzail.$r8$clinit;
        r532 = zzaij.zzb((String) r533);
        r1033 = zzafa.zzu(r532);
        goto L244
    L255:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        r04 = zzafa.zzu(r13 << 3) + 1;
        goto L222
    L258:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        r06 = zzafa.zzu(r13 << 3);
        goto L234
    L261:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        r05 = zzafa.zzu(r13 << 3);
        goto L230
    L264:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        long r1036 = r6.getInt(r1, r142);
        r03 = zzafa.zzu(r13 << 3);
        r528 = zzafa.zzv(r1036);
        goto L221
    L267:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        long r1037 = r6.getLong(r1, r142);
        r03 = zzafa.zzu(r13 << 3);
        r528 = zzafa.zzv(r1037);
        goto L221
    L270:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        long r1038 = r6.getLong(r1, r142);
        r03 = zzafa.zzu(r13 << 3);
        r528 = zzafa.zzv(r1038);
        goto L221
    L273:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        r06 = zzafa.zzu(r13 << 3);
        goto L234
    L276:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L278;
        r9 = r9 + (zzafa.zzu(r13 << 3) + 8);
    L278:
        r2 = r2 + 3;
        r1 = r18;
        goto L3
    L13:
        r53 = 0;
        goto L14
    L279:
        int r92 = r9 + ((zzafv) r18).zzc.zzi();
        if (r0.zzh == true) goto L282;
        return r92;
    L282:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r18);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final void zzf(Object r18, zzafb r19) {
        zzaha<T> r0 = this;
        if (r0.zzh == true) goto L190;
        int[] r8 = r0.zzc;
        Unsafe r9 = zzb;
        int r10 = 1048575;
        int r3 = 1048575;
        int r2 = 0;
        int r4 = 0;
    L6:
        if (r2 >= r8.length) goto L188;
        int r5 = r0.zzA(r2);
        int r12 = zzC(r5);
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
        zzahn r103 = r0.zzq(r2);
        int r11 = zzaho.$r8$clinit;
        if (r73 == null) goto L187;
        if (r73.isEmpty() == true) goto L187;
        int r112 = 0;
    L85:
        if (r112 >= r73.size()) goto L187;
        r19.zzs(r52, r73.get(r112), r103);
        r112 = r112 + 1;
        goto L85
    L87:
        zzaho.zze(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L88:
        zzaho.zzj(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L89:
        zzaho.zzg(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L90:
        zzaho.zzl(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L91:
        zzaho.zzm(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L92:
        zzaho.zzi(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L93:
        zzaho.zzn(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L94:
        zzaho.zzk(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L95:
        zzaho.zzf(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L96:
        zzaho.zzh(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L97:
        zzaho.zzd(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L98:
        zzaho.zzc(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L99:
        zzaho.zzb(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L100:
        zzaho.zza(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L101:
        zzaho.zze(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L102:
        zzaho.zzj(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L103:
        zzaho.zzg(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L104:
        zzaho.zzl(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L105:
        zzaho.zzm(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L106:
        zzaho.zzi(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L107:
        int r53 = r8[r2];
        List r74 = (List) r9.getObject(r18, r102);
        int r104 = zzaho.$r8$clinit;
        if (r74 == null) goto L187;
        if (r74.isEmpty() == true) goto L187;
        r19.zzG(r53, r74);
        goto L187
    L112:
        int r54 = r8[r2];
        List r75 = (List) r9.getObject(r18, r102);
        zzahn r105 = r0.zzq(r2);
        int r113 = zzaho.$r8$clinit;
        if (r75 == null) goto L187;
        if (r75.isEmpty() == true) goto L187;
        int r122 = 0;
    L118:
        if (r122 >= r75.size()) goto L187;
        r19.zzr(r54, r75.get(r122), r105);
        r122 = r122 + 1;
        goto L118
    L120:
        int r55 = r8[r2];
        List r76 = (List) r9.getObject(r18, r102);
        int r106 = zzaho.$r8$clinit;
        if (r76 == null) goto L187;
        if (r76.isEmpty() == true) goto L187;
        r19.zzF(r55, r76);
        goto L187
    L125:
        zzaho.zzn(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L126:
        zzaho.zzk(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L127:
        zzaho.zzf(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L128:
        zzaho.zzh(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L129:
        zzaho.zzd(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L130:
        zzaho.zzc(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L131:
        zzaho.zzb(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L132:
        zzaho.zza(r8[r2], (List) r9.getObject(r18, r102), r19, false);
    L187:
        r2 = r2 + 3;
        r10 = 1048575;
        r0 = this;
        goto L6
    L21:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzs(r13, r9.getObject(r18, r102), r0.zzq(r2));
        goto L187
    L24:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzq(r13, zzH(r18, r102));
        goto L187
    L27:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzp(r13, zzG(r18, r102));
        goto L187
    L30:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzd(r13, zzH(r18, r102));
        goto L187
    L33:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzb(r13, zzG(r18, r102));
        goto L187
    L36:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzg(r13, zzG(r18, r102));
        goto L187
    L39:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzo(r13, zzG(r18, r102));
        goto L187
    L42:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzn(r13, (zzaer) r9.getObject(r18, r102));
        goto L187
    L45:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzr(r13, r9.getObject(r18, r102), r0.zzq(r2));
        goto L187
    L48:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        zzS(r13, r9.getObject(r18, r102), r19);
        goto L187
    L51:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzl(r13, ((Boolean) zzaig.zzl(r18, r102)).booleanValue());
        goto L187
    L54:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzk(r13, zzG(r18, r102));
        goto L187
    L57:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzj(r13, zzH(r18, r102));
        goto L187
    L60:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzi(r13, zzG(r18, r102));
        goto L187
    L63:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzh(r13, zzH(r18, r102));
        goto L187
    L66:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzc(r13, zzH(r18, r102));
        goto L187
    L69:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zze(r13, ((Float) zzaig.zzl(r18, r102)).floatValue());
        goto L187
    L72:
        if (r0.zzM(r18, r13, r2) == false) goto L187;
        r19.zzf(r13, ((Double) zzaig.zzl(r18, r102)).doubleValue());
        goto L187
    L75:
        if (r9.getObject(r18, r102) == null) goto L187;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.zzr(r2));
        throw r16;
    L134:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzs(r13, r9.getObject(r18, r102), r0.zzq(r2));
        goto L187
    L137:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzq(r13, r9.getLong(r18, r102));
        goto L187
    L140:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzp(r13, r9.getInt(r18, r102));
        goto L187
    L143:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzd(r13, r9.getLong(r18, r102));
        goto L187
    L146:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzb(r13, r9.getInt(r18, r102));
        goto L187
    L149:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzg(r13, r9.getInt(r18, r102));
        goto L187
    L152:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzo(r13, r9.getInt(r18, r102));
        goto L187
    L155:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzn(r13, (zzaer) r9.getObject(r18, r102));
        goto L187
    L158:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzr(r13, r9.getObject(r18, r102), r0.zzq(r2));
        goto L187
    L161:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        zzS(r13, r9.getObject(r18, r102), r19);
        goto L187
    L164:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzl(r13, zzaig.zzf(r18, r102));
        goto L187
    L167:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzk(r13, r9.getInt(r18, r102));
        goto L187
    L170:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzj(r13, r9.getLong(r18, r102));
        goto L187
    L173:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzi(r13, r9.getInt(r18, r102));
        goto L187
    L176:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzh(r13, r9.getLong(r18, r102));
        goto L187
    L179:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzc(r13, r9.getLong(r18, r102));
        goto L187
    L182:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zze(r13, zzaig.zzh(r18, r102));
        goto L187
    L185:
        if (r0.zzJ(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzf(r13, zzaig.zzj(r18, r102));
        goto L187
    L16:
        r16 = null;
        r72 = 0;
        goto L17
    L188:
        ((zzafv) r18).zzc.zzg(r19);
        return;
    L190:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r18);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final void zzg(Object r13, zzaew r14, zzafh r15) {
        r15.getClass();
        zzF(r13);
        zzahz r5 = this.zzm;
        Object r4 = null;
    L223:
        int r2 = r14.zzb();     // Catch: Throwable -> L201
        int r1 = this.zzP(r2);     // Catch: Throwable -> L201
        if (r1 < 0) goto L7;
        Object r3 = r13;
        zzahz r6 = r5;
        Object r52 = r4;
        int r132 = this.zzA(r1);     // Catch: Throwable -> L199
        switch(zzC(r132)) {
            case 0: goto L180;
            case 1: goto L179;
            case 2: goto L178;
            case 3: goto L177;
            case 4: goto L176;
            case 5: goto L175;
            case 6: goto L174;
            case 7: goto L173;
            case 8: goto L172;
            case 9: goto L171;
            case 10: goto L170;
            case 11: goto L169;
            case 12: goto L162;
            case 13: goto L161;
            case 14: goto L160;
            case 15: goto L159;
            case 16: goto L158;
            case 17: goto L157;
            case 18: goto L156;
            case 19: goto L155;
            case 20: goto L154;
            case 21: goto L153;
            case 22: goto L152;
            case 23: goto L151;
            case 24: goto L150;
            case 25: goto L149;
            case 26: goto L145;
            case 27: goto L144;
            case 28: goto L143;
            case 29: goto L141;
            case 30: goto L131;
            case 31: goto L129;
            case 32: goto L128;
            case 33: goto L127;
            case 34: goto L126;
            case 35: goto L125;
            case 36: goto L124;
            case 37: goto L123;
            case 38: goto L122;
            case 39: goto L121;
            case 40: goto L120;
            case 41: goto L119;
            case 42: goto L118;
            case 43: goto L116;
            case 44: goto L105;
            case 45: goto L102;
            case 46: goto L101;
            case 47: goto L100;
            case 48: goto L99;
            case 49: goto L98;
            case 50: goto L90;
            case 51: goto L89;
            case 52: goto L88;
            case 53: goto L87;
            case 54: goto L86;
            case 55: goto L85;
            case 56: goto L84;
            case 57: goto L83;
            case 58: goto L82;
            case 59: goto L81;
            case 60: goto L80;
            case 61: goto L79;
            case 62: goto L78;
            case 63: goto L71;
            case 64: goto L70;
            case 65: goto L69;
            case 66: goto L68;
            case 67: goto L67;
            case 68: goto L65;
            default: goto L46;
        };
    L46:
        if (r52 == null) goto L226;
        r4 = r52;
    L232:
    L55:
        if (r6.zzh(r4, r14, 0) == false) goto L56;
    L33:
        r13 = r3;
        r5 = r6;
        goto L223
    L56:
        int r133 = this.zzk;
    L58:
        if (r133 >= this.zzl) goto L60;
        zzahz r53 = r6;
        Object r22 = r3;
        this.zzx(r22, this.zzj[r133], r4, r53, r3);
        r3 = r22;
        r6 = r53;
        r133 = r133 + 1;
        goto L58
    L60:
        Object r23 = r3;
    L193:
        if (r4 == null) goto L324;
        ((zzafv) r23).zzc = (zzaia) r4;
        return;
    L324:
        return;
    L63:
        r23 = r3;
    L64:
        r5 = r6;
    L182:
        if (r4 != null) goto L217;
        r4 = zzaib.zzj(r23);     // Catch: Throwable -> L185
    L185:
        th = th;
    L186:
        Throwable r134 = th;
        zzaha<T> r12 = this;
    L203:
        int r122 = r12.zzk;
    L205:
        if (r122 >= r12.zzl) goto L207;
        r12.zzx(r23, r12.zzj[r122], r4, r5, r23);
        r122 = r122 + 1;
        goto L205
    L207:
        if (r4 == null) goto L325;
        ((zzafv) r23).zzc = (zzaia) r4;
        throw r134;
    L325:
        throw r134;
    L217:
        if (r5.zzh(r4, r14, 0) == false) goto L189;
    L109:
        r13 = r23;
        goto L223
    L189:
        int r135 = this.zzk;
    L191:
        if (r135 >= this.zzl) goto L193;
        this.zzx(r23, this.zzj[r135], r4, r5, r23);
        r135 = r135 + 1;
    L196:
        th = th;
        r12 = this;
    L198:
        r134 = th;
    L61:
        th = move-exception;
        r134 = th;
        r12 = this;
    L36:
        r23 = r3;
    L37:
        r5 = r6;
        goto L203
    L226:
        r4 = zzaib.zzj(r3);     // Catch: Throwable -> L49 zzagj -> L51
    L49:
        th = move-exception;
        r134 = th;
        r12 = this;
    L40:
        r23 = r3;
    L23:
        r4 = r52;
        goto L37
    L71:
        int r42 = r14.zzr();     // Catch: Throwable -> L49 zzagj -> L51
        zzaga r9 = this.zzs(r1);     // Catch: Throwable -> L49 zzagj -> L51
        if (r9 == null) goto L77;
        if (r9.zza(r42) == true) goto L77;
        r4 = zzaho.zzF(r3, r2, r42, r52, r6);     // Catch: Throwable -> L49 zzagj -> L51
    L77:
        zzaig.zzm(r3, r132 & 1048575, Integer.valueOf(r42));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
    L66:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        goto L109
    L65:
        zzagx r136 = (zzagx) this.zzv(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        r14.zzo(r136, this.zzq(r1), r15);     // Catch: Throwable -> L49 zzagj -> L51
        this.zzw(r3, r2, r1, r136);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L67:
        zzaig.zzm(r3, r132 & 1048575, Long.valueOf(r14.zzv()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L68:
        zzaig.zzm(r3, r132 & 1048575, Integer.valueOf(r14.zzu()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L69:
        zzaig.zzm(r3, r132 & 1048575, Long.valueOf(r14.zzt()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L70:
        zzaig.zzm(r3, r132 & 1048575, Integer.valueOf(r14.zzs()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L78:
        zzaig.zzm(r3, r132 & 1048575, Integer.valueOf(r14.zzq()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L79:
        zzaig.zzm(r3, r132 & 1048575, r14.zzp());     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L80:
        zzagx r137 = (zzagx) this.zzv(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        r14.zzn(r137, this.zzq(r1), r15);     // Catch: Throwable -> L49 zzagj -> L51
        this.zzw(r3, r2, r1, r137);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L81:
        this.zzz(r3, r132, r14);     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L82:
        zzaig.zzm(r3, r132 & 1048575, Boolean.valueOf(r14.zzk()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L83:
        zzaig.zzm(r3, r132 & 1048575, Integer.valueOf(r14.zzj()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L84:
        zzaig.zzm(r3, r132 & 1048575, Long.valueOf(r14.zzi()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L85:
        zzaig.zzm(r3, r132 & 1048575, Integer.valueOf(r14.zzh()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L86:
        zzaig.zzm(r3, r132 & 1048575, Long.valueOf(r14.zzf()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L87:
        zzaig.zzm(r3, r132 & 1048575, Long.valueOf(r14.zzg()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L88:
        zzaig.zzm(r3, r132 & 1048575, Float.valueOf(r14.zze()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L89:
        zzaig.zzm(r3, r132 & 1048575, Double.valueOf(r14.zzd()));     // Catch: Throwable -> L49 zzagj -> L51
        this.zzO(r3, r2, r1);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L90:
        Object r138 = this.zzr(r1);     // Catch: Throwable -> L49 zzagj -> L51
        long r16 = this.zzA(r1) & 1048575;     // Catch: Throwable -> L49 zzagj -> L51
        Object r43 = zzaig.zzl(r3, r16);     // Catch: Throwable -> L49 zzagj -> L51
        if (r43 != null) goto L93;
        r43 = zzags.zza().zzc();     // Catch: Throwable -> L49 zzagj -> L51
        zzaig.zzm(r3, r16, r43);     // Catch: Throwable -> L49 zzagj -> L51
    L96:
        zzags r44 = (zzags) r43;     // Catch: Throwable -> L49 zzagj -> L51
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r138);     // Catch: Throwable -> L49 zzagj -> L51
        throw null;     // Catch: Throwable -> L49 zzagj -> L51
    L93:
        if (zzagt.zza(r43) == false) goto L96;
        Object r8 = zzags.zza().zzc();     // Catch: Throwable -> L49 zzagj -> L51
        zzagt.zzb(r8, r43);     // Catch: Throwable -> L49 zzagj -> L51
        zzaig.zzm(r3, r16, r8);     // Catch: Throwable -> L49 zzagj -> L51
        r43 = r8;
        goto L96
    L98:
        r14.zzG(zzago.zza(r3, r132 & 1048575), this.zzq(r1), r15);     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L99:
        r14.zzN(zzago.zza(r3, r132 & 1048575));     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L100:
        r14.zzM(zzago.zza(r3, r132 & 1048575));     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L101:
        r14.zzL(zzago.zza(r3, r132 & 1048575));     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L102:
        r14.zzK(zzago.zza(r3, r132 & 1048575));     // Catch: Throwable -> L49 zzagj -> L51
        goto L66
    L105:
        List r139 = zzago.zza(r3, r132 & 1048575);     // Catch: zzagj -> L51 Throwable -> L114
        r14.zzJ(r139);     // Catch: zzagj -> L51 Throwable -> L114
        r4 = zzaho.zzE(r3, r2, r139, this.zzs(r1), r52, r6);     // Catch: Throwable -> L110 zzagj -> L113
        r23 = r3;
        r5 = r6;
        goto L109
    L113:
        r23 = r3;
    L52:
        r4 = r52;
    L110:
        th = th;
        r23 = r3;
    L112:
        r4 = r52;
        r5 = r6;
        goto L186
    L116:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzI(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
    L118:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzD(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L119:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzC(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L120:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzB(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L121:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzA(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L122:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzy(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L123:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzz(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L124:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzx(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L125:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzw(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L126:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzN(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L127:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzM(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L128:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzL(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L129:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzK(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L131:
        r4 = r52;
        r5 = r6;
        List r1310 = zzago.zza(r3, r132 & 1048575);     // Catch: Throwable -> L138 zzagj -> L140
        r14.zzJ(r1310);     // Catch: Throwable -> L138 zzagj -> L140
    L136:
        r4 = zzaho.zzE(r3, r2, r1310, this.zzs(r1), r4, r5);     // Catch: Throwable -> L110 zzagj -> L113
        r23 = r3;
        r5 = r5;
    L140:
        r23 = r3;
    L138:
        th = th;
        r23 = r3;
        goto L186
    L141:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzI(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L143:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzH(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L144:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzF(zzago.zza(r23, r132 & 1048575), this.zzq(r1), r15);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L145:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        if (zzD(r132) == false) goto L148;
        r14.zzE(zzago.zza(r23, r132 & 1048575), true);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L148:
        r14.zzE(zzago.zza(r23, r132 & 1048575), false);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L149:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzD(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L150:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzC(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L151:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzB(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L152:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzA(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L153:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzy(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L154:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzz(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L155:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzx(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L156:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        r14.zzw(zzago.zza(r23, r132 & 1048575));     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L157:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzagx r1311 = (zzagx) this.zzt(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        r14.zzo(r1311, this.zzq(r1), r15);     // Catch: zzagj -> L210 Throwable -> L185
        this.zzu(r23, r1, r1311);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L158:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zze(r23, r132 & 1048575, r14.zzv());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L159:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zzc(r23, r132 & 1048575, r14.zzu());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L160:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zze(r23, r132 & 1048575, r14.zzt());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L161:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zzc(r23, r132 & 1048575, r14.zzs());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L162:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        int r62 = r14.zzr();     // Catch: zzagj -> L210 Throwable -> L185
        zzaga r92 = this.zzs(r1);     // Catch: zzagj -> L210 Throwable -> L185
        if (r92 != null) goto L165;
    L168:
        zzaig.zzc(r23, r132 & 1048575, r62);     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L165:
        if (r92.zza(r62) == true) goto L168;
        r4 = zzaho.zzF(r23, r2, r62, r4, r5);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L169:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zzc(r23, r132 & 1048575, r14.zzq());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L170:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zzm(r23, r132 & 1048575, r14.zzp());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L171:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzagx r1312 = (zzagx) this.zzt(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        r14.zzn(r1312, this.zzq(r1), r15);     // Catch: zzagj -> L210 Throwable -> L185
        this.zzu(r23, r1, r1312);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L172:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        this.zzz(r23, r132, r14);     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L173:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zzg(r23, r132 & 1048575, r14.zzk());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L174:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zzc(r23, r132 & 1048575, r14.zzj());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L175:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zze(r23, r132 & 1048575, r14.zzi());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L176:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zzc(r23, r132 & 1048575, r14.zzh());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L177:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zze(r23, r132 & 1048575, r14.zzf());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L178:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zze(r23, r132 & 1048575, r14.zzg());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L179:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zzi(r23, r132 & 1048575, r14.zze());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
        goto L109
    L180:
        r23 = r3;
        r4 = r52;
        r5 = r6;
        zzaig.zzk(r23, r132 & 1048575, r14.zzd());     // Catch: zzagj -> L210 Throwable -> L185
        this.zzL(r23, r1);     // Catch: zzagj -> L210 Throwable -> L185
    L114:
        th = th;
        r23 = r3;
    L51:
        r23 = r3;
    L199:
        th = th;
        r12 = this;
        r23 = r3;
        r4 = r52;
        r5 = r6;
        goto L198
    L7:
        if (r2 == Integer.MAX_VALUE) goto L8;
        r12 = this;
        Object r123 = r13;
        r6 = r5;
        r52 = r4;
        if (r12.zzh == false) goto L18;
        r15.zzc(r12.zzg, r2);     // Catch: Throwable -> L38
    L18:
        if (r52 == null) goto L215;
        r4 = r52;
    L221:
        if (r6.zzh(r4, r14, 0) == false) goto L27;
        r3 = r123;
        zzaha<T> this = r12;
        goto L33
    L27:
        int r1313 = r12.zzk;
    L29:
        if (r1313 >= r12.zzl) goto L31;
        zzahz r54 = r6;
        Object r24 = r123;
        r12.zzx(r24, r12.zzj[r1313], r4, r54, r123);
        r6 = r54;
        r1313 = r1313 + 1;
        r123 = r24;
        goto L29
    L31:
        r23 = r123;
    L34:
        th = move-exception;
        r3 = r123;
        r134 = th;
        goto L36
    L215:
        r4 = zzaib.zzj(r123);     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        r134 = th;
        r23 = r123;
    L38:
        th = move-exception;
        r3 = r123;
        r134 = th;
        goto L40
    L8:
        int r142 = this.zzk;
    L10:
        if (r142 >= this.zzl) goto L12;
        zzaha<T> r17 = this;
        Object r25 = r13;
        r17.zzx(r25, this.zzj[r142], r4, r5, r13);
        r142 = r142 + 1;
        r13 = r25;
        this = r17;
        r5 = r5;
        goto L10
    L12:
        r23 = r13;
    L201:
        th = th;
        r12 = this;
        r23 = r13;
        goto L198
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    public final int zzi(java.lang.Object r32, byte[] r33, int r34, int r35, int r36, com.google.android.recaptcha.internal.zzaeg r37) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: MOVE (r3v0 ?? I:??[OBJECT, ARRAY]) = (r33v0 ?? I:??[OBJECT, ARRAY]) A[DECLARE_VAR] in method: com.google.android.recaptcha.internal.zzaha.zzi(java.lang.Object, byte[], int, int, int, com.google.android.recaptcha.internal.zzaeg):int, file: classes5.dex
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
            Method dump skipped, instructions count: 3540
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzaha.zzi(java.lang.Object, byte[], int, int, int, com.google.android.recaptcha.internal.zzaeg):int");
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final void zzj(Object r8, byte[] r9, int r10, int r11, zzaeg r12) {
        zzi(r8, r9, r10, r11, 0, r12);
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final void zzk(Object r8) {
        if (zzE(r8) == false) goto L44;
        int r1 = 0;
        if ((r8 instanceof zzafv) == false) goto L8;
        zzafv r0 = (zzafv) r8;
        r0.zzH(Integer.MAX_VALUE);
        r0.zzb = 0;
        r0.zzA();
    L8:
        int[] r02 = this.zzc;
    L10:
        if (r1 >= r02.length) goto L30;
        int r2 = zzA(r1);
        int r3 = 1048575 & r2;
        int r22 = zzC(r2);
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
        ((zzags) r5).zzd();
        r23.putObject(r8, r32, r5);
        goto L29
    L22:
        ((zzage) zzaig.zzl(r8, r32)).zzb();
    L29:
        r1 = r1 + 3;
    L24:
        if (zzM(r8, r02[r1], r1) == false) goto L29;
        zzq(r1).zzk(zzb.getObject(r8, r32));
    L27:
        if (zzK(r8, r1) == false) goto L29;
        zzq(r1).zzk(zzb.getObject(r8, r32));
        goto L29
    L30:
        ((zzafv) r8).zzc.zzd();
        if (this.zzh == true) goto L33;
        return;
    L33:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r8);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final boolean zzl(Object r16) {
        int r2 = 0;
        int r8 = 0;
        int r0 = 1048575;
    L4:
        if (r8 >= this.zzk) goto L58;
        int r3 = this.zzj[r8];
        int r10 = zzA(r3);
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
        if (zzJ(r16, r22, r32, r4, r52) == false) goto L15;
    L17:
        int r122 = zzC(r10);
        if (r122 == 9) goto L52;
        if (r122 == 17) goto L52;
        if (r122 != 27) goto L24;
    L42:
        List r53 = (List) zzaig.zzl(r16, r10 & 1048575);
        if (r53.isEmpty() == true) goto L56;
        zzahn r23 = zzq(r22);
        int r9 = 0;
    L46:
        if (r9 >= r53.size()) goto L56;
        if (r23.zzl(r53.get(r9)) == false) goto L49;
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
        if (((zzags) zzaig.zzl(r16, r10 & 1048575)).isEmpty() == true) goto L56;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(zzr(r22));
        throw null;
    L38:
        if (zzM(r16, r11[r22], r22) == false) goto L56;
        if (zzy(r16, r10, zzq(r22)) == true) goto L56;
        return false;
    L52:
        if (zzJ(r16, r22, r32, r4, r52) == false) goto L56;
        if (zzy(r16, r10, zzq(r22)) == true) goto L56;
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
