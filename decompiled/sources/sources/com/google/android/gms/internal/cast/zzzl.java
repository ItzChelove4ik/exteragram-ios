package com.google.android.gms.internal.cast;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline0;
import sun.misc.Unsafe;

/* loaded from: classes4.dex */
final class zzzl<T> implements zzzs<T> {
    private static final int[] zza = null;
    private static final Unsafe zzb = null;
    private final int[] zzc;
    private final Object[] zzd;
    private final zzzi zze;
    private final boolean zzf;
    private final int[] zzg;
    private final int zzh;
    private final zzaad zzi;
    private final zzxs zzj;

    static {
        zza = new int[0];
        zzb = zzaak.zzq();
    }

    private zzzl(int[] r1, Object[] r2, int r3, int r4, zzzi r5, boolean r6, int[] r7, int r8, int r9, zzzn r10, zzyv r11, zzaad r12, zzxs r13, zzzd r14) {
        this.zzc = r1;
        this.zzd = r2;
        this.zzf = false;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r12;
        this.zzj = r13;
        this.zze = r5;
    }

    private final boolean zzA(Object r8, int r9) {
        int r0 = zzq(r9);
        long r2 = r0 & 1048575;
        if (r2 != 1048575) goto L89;
        int r7 = zzp(r9);
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
        Object r72 = zzaak.zzn(r8, r02);
        if ((r72 instanceof String) == false) goto L51;
        if (((String) r72).isEmpty() == true) goto L49;
        return true;
    L49:
        return false;
    L51:
        if ((r72 instanceof zzxk) == true) goto L53;
        Segment$$ExternalSyntheticBUOutline0.m();
        return false;
    L53:
        if (zzxk.zza.equals(r72) == true) goto L55;
        return true;
    L55:
        return false;
    L9:
        if (zzaak.zzn(r8, r02) == null) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (zzaak.zzf(r8, r02) == 0) goto L15;
        return true;
    L15:
        return false;
    L17:
        if (zzaak.zzd(r8, r02) == 0) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (zzaak.zzf(r8, r02) == 0) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (zzaak.zzd(r8, r02) == 0) goto L27;
        return true;
    L27:
        return false;
    L29:
        if (zzaak.zzd(r8, r02) == 0) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (zzaak.zzd(r8, r02) == 0) goto L35;
        return true;
    L35:
        return false;
    L37:
        if (zzxk.zza.equals(zzaak.zzn(r8, r02)) == true) goto L39;
        return true;
    L39:
        return false;
    L41:
        if (zzaak.zzn(r8, r02) == null) goto L43;
        return true;
    L43:
        return false;
    L59:
        return zzaak.zzh(r8, r02);
    L61:
        if (zzaak.zzd(r8, r02) == 0) goto L63;
        return true;
    L63:
        return false;
    L65:
        if (zzaak.zzf(r8, r02) == 0) goto L67;
        return true;
    L67:
        return false;
    L69:
        if (zzaak.zzd(r8, r02) == 0) goto L71;
        return true;
    L71:
        return false;
    L73:
        if (zzaak.zzf(r8, r02) == 0) goto L75;
        return true;
    L75:
        return false;
    L77:
        if (zzaak.zzf(r8, r02) == 0) goto L79;
        return true;
    L79:
        return false;
    L81:
        if (Float.floatToRawIntBits(zzaak.zzj(r8, r02)) == 0) goto L83;
        return true;
    L83:
        return false;
    L85:
        if (Double.doubleToRawLongBits(zzaak.zzl(r8, r02)) == 0) goto L87;
        return true;
    L87:
        return false;
    L89:
        if (((1 << (r0 >>> 20)) & zzaak.zzd(r8, r2)) == 0) goto L91;
        return true;
    L91:
        return false;
    }

    private final void zzB(Object r5, int r6) {
        int r4 = zzq(r6);
        long r0 = 1048575 & r4;
        if (r0 != 1048575) goto L5;
        return;
    L5:
        zzaak.zze(r5, r0, (1 << (r4 >>> 20)) | zzaak.zzd(r5, r0));
    }

    private final boolean zzC(Object r3, int r4, int r5) {
        if (zzaak.zzd(r3, zzq(r5) & 1048575) != r4) goto L6;
        return true;
    L6:
        return false;
    }

    private final void zzD(Object r3, int r4, int r5) {
        zzaak.zze(r3, zzq(r5) & 1048575, r4);
    }

    private static final void zzE(int r1, Object r2, zzaar r3) {
        if ((r2 instanceof String) == false) goto L6;
        r3.zzm(r1, (String) r2);
        return;
    L6:
        r3.zzn(r1, (zzxk) r2);
    }

    public static zzzl zzi(Class r32, zzzf r33, zzzn r34, zzyv r35, zzaad r36, zzxs r37, zzzd r38) {
        if ((r33 instanceof zzzr) == false) goto L208;
        zzzr r0 = (zzzr) r33;
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
        Class<?> r3 = r0.zzb().getClass();
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
        zzzr r252 = r0;
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
        r76 = zzj(r3, (String) r75);
        r15[r68] = r76;
        goto L133
    L128:
        r29 = zzj(r3, (String) r28);
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
        Field r211 = zzj(r3, (String) r15[r102]);
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
        r610 = zzj(r3, (String) r69);
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
        return new zzzl(r112, r94, r12, r13, r0.zzb(), false, r16, r17, r18, r34, r35, r36, r37, r38);
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

    private static Field zzj(Class r6, String r7) {
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

    private final void zzk(Object r6, Object r7, int r8) {
        if (zzA(r7, r8) == true) goto L5;
        return;
    L5:
        int r0 = zzp(r8) & 1048575;
        Unsafe r1 = zzb;
        long r2 = r0;
        Object r02 = r1.getObject(r7, r2);
        if (r02 == null) goto L20;
        zzzs r72 = zzm(r8);
        if (zzA(r6, r8) == false) goto L10;
        Object r5 = r1.getObject(r6, r2);
        if (zzs(r5) == true) goto L18;
        Object r82 = r72.zza();
        r72.zzd(r82, r5);
        r1.putObject(r6, r2, r82);
        r5 = r82;
    L18:
        r72.zzd(r5, r02);
        return;
    L10:
        if (zzs(r02) == true) goto L12;
        r1.putObject(r6, r2, r02);
    L13:
        zzB(r6, r8);
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

    private final void zzl(Object r7, Object r8, int r9) {
        int[] r0 = this.zzc;
        int r1 = r0[r9];
        if (zzC(r8, r1, r9) == true) goto L5;
        return;
    L5:
        int r2 = zzp(r9) & 1048575;
        Unsafe r3 = zzb;
        long r4 = r2;
        Object r22 = r3.getObject(r8, r4);
        if (r22 == null) goto L20;
        zzzs r82 = zzm(r9);
        if (zzC(r7, r1, r9) == false) goto L10;
        Object r6 = r3.getObject(r7, r4);
        if (zzs(r6) == true) goto L18;
        Object r92 = r82.zza();
        r82.zzd(r92, r6);
        r3.putObject(r7, r4, r92);
        r6 = r92;
    L18:
        r82.zzd(r6, r22);
        return;
    L10:
        if (zzs(r22) == true) goto L12;
        r3.putObject(r7, r4, r22);
    L13:
        zzD(r7, r1, r9);
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

    private final zzzs zzm(int r3) {
        Object[] r2 = this.zzd;
        int r32 = r3 / 3;
        int r33 = r32 + r32;
        zzzs r0 = (zzzs) r2[r33];
        if (r0 == null) goto L5;
        return r0;
    L5:
        zzzs r02 = zzzp.zza().zzb((Class) r2[r33 + 1]);
        r2[r33] = r02;
        return r02;
    }

    private final Object zzn(int r1) {
        int r12 = r1 / 3;
        return this.zzd[r12 + r12];
    }

    private static boolean zzo(Object r2, int r3, zzzs r4) {
        return r4.zzh(zzaak.zzn(r2, r3 & 1048575));
    }

    private final int zzp(int r1) {
        return this.zzc[r1 + 1];
    }

    private final int zzq(int r1) {
        return this.zzc[r1 + 2];
    }

    private static int zzr(int r0) {
        return (r0 >>> 20) & 255;
    }

    private static boolean zzs(Object r1) {
        if (r1 != null) goto L6;
        return false;
    L6:
        if ((r1 instanceof zzyd) == true) goto L8;
        return true;
    L8:
        return ((zzyd) r1).zzv();
    }

    private static double zzt(Object r0, long r1) {
        return ((Double) zzaak.zzn(r0, r1)).doubleValue();
    }

    private static float zzu(Object r0, long r1) {
        return ((Float) zzaak.zzn(r0, r1)).floatValue();
    }

    private static int zzv(Object r0, long r1) {
        return ((Integer) zzaak.zzn(r0, r1)).intValue();
    }

    private static long zzw(Object r0, long r1) {
        return ((Long) zzaak.zzn(r0, r1)).longValue();
    }

    private static boolean zzx(Object r0, long r1) {
        return ((Boolean) zzaak.zzn(r0, r1)).booleanValue();
    }

    private final boolean zzy(Object r1, Object r2, int r3) {
        if (zzA(r1, r3) != zzA(r2, r3)) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean zzz(Object r2, int r3, int r4, int r5, int r6) {
        if (r4 != 1048575) goto L7;
        return zzA(r2, r3);
    L7:
        if ((r5 & r6) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final Object zza() {
        return ((zzyd) this.zze).zzy();
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final boolean zzb(Object r8, Object r9) {
        int r1 = 0;
    L4:
        if (r1 >= this.zzc.length) goto L109;
        int r2 = zzp(r1);
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
        long r22 = zzq(r1) & 1048575;
        if (zzaak.zzd(r8, r22) != zzaak.zzd(r9, r22)) goto L107;
        if (zzzu.zzC(zzaak.zzn(r8, r4), zzaak.zzn(r9, r4)) == true) goto L106;
    L107:
        return false;
    L13:
        boolean r23 = zzzu.zzC(zzaak.zzn(r8, r4), zzaak.zzn(r9, r4));
    L15:
        if (r23 == true) goto L106;
    L14:
        r23 = zzzu.zzC(zzaak.zzn(r8, r4), zzaak.zzn(r9, r4));
        goto L15
    L18:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzzu.zzC(zzaak.zzn(r8, r4), zzaak.zzn(r9, r4)) == false) goto L107;
    L23:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzf(r8, r4) != zzaak.zzf(r9, r4)) goto L107;
    L28:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzd(r8, r4) != zzaak.zzd(r9, r4)) goto L107;
    L33:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzf(r8, r4) != zzaak.zzf(r9, r4)) goto L107;
    L38:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzd(r8, r4) != zzaak.zzd(r9, r4)) goto L107;
    L43:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzd(r8, r4) != zzaak.zzd(r9, r4)) goto L107;
    L48:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzd(r8, r4) != zzaak.zzd(r9, r4)) goto L107;
    L53:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzzu.zzC(zzaak.zzn(r8, r4), zzaak.zzn(r9, r4)) == false) goto L107;
    L58:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzzu.zzC(zzaak.zzn(r8, r4), zzaak.zzn(r9, r4)) == false) goto L107;
    L63:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzzu.zzC(zzaak.zzn(r8, r4), zzaak.zzn(r9, r4)) == false) goto L107;
    L68:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzh(r8, r4) != zzaak.zzh(r9, r4)) goto L107;
    L73:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzd(r8, r4) != zzaak.zzd(r9, r4)) goto L107;
    L78:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzf(r8, r4) != zzaak.zzf(r9, r4)) goto L107;
    L83:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzd(r8, r4) != zzaak.zzd(r9, r4)) goto L107;
    L88:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzf(r8, r4) != zzaak.zzf(r9, r4)) goto L107;
    L93:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (zzaak.zzf(r8, r4) != zzaak.zzf(r9, r4)) goto L107;
    L98:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (Float.floatToIntBits(zzaak.zzj(r8, r4)) != Float.floatToIntBits(zzaak.zzj(r9, r4))) goto L107;
    L103:
        if (zzy(r8, r9, r1) == false) goto L107;
        if (Double.doubleToLongBits(zzaak.zzl(r8, r4)) != Double.doubleToLongBits(zzaak.zzl(r9, r4))) goto L107;
    L106:
        r1 = r1 + 3;
        goto L4
    L109:
        if (((zzyd) r8).zzc.equals(((zzyd) r9).zzc) == true) goto L112;
        return false;
    L112:
        if (this.zzf == true) goto L115;
        return true;
    L115:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r8);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final int zzc(Object r9) {
        int r0 = 0;
        int r1 = 0;
    L3:
        int[] r2 = this.zzc;
        if (r0 >= r2.length) goto L90;
        int r3 = zzp(r0);
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
        int r23 = zzaak.zzn(r9, r42).hashCode();
    L11:
        r1 = r12 + r23;
        goto L89
    L65:
        r12 = r1 * 53;
        r23 = zzaak.zzn(r9, r42).hashCode();
        goto L11
    L66:
        int r13 = r1 * 53;
        Object r24 = zzaak.zzn(r9, r42);
        if (r24 == null) goto L69;
        r6 = r24.hashCode();
    L69:
        r1 = r13 + r6;
        goto L89
    L70:
        r12 = r1 * 53;
        long r25 = zzaak.zzf(r9, r42);
        byte[] r43 = zzym.zzb;
    L15:
        r23 = (int) (r25 ^ (r25 >>> 32));
        goto L11
    L71:
        r12 = r1 * 53;
        r23 = zzaak.zzd(r9, r42);
        goto L11
    L72:
        r12 = r1 * 53;
        r25 = zzaak.zzf(r9, r42);
        byte[] r44 = zzym.zzb;
        goto L15
    L73:
        r12 = r1 * 53;
        r23 = zzaak.zzd(r9, r42);
        goto L11
    L74:
        r12 = r1 * 53;
        r23 = zzaak.zzd(r9, r42);
        goto L11
    L75:
        r12 = r1 * 53;
        r23 = zzaak.zzd(r9, r42);
        goto L11
    L76:
        r12 = r1 * 53;
        r23 = zzaak.zzn(r9, r42).hashCode();
        goto L11
    L77:
        r13 = r1 * 53;
        Object r26 = zzaak.zzn(r9, r42);
        if (r26 == null) goto L69;
        r6 = r26.hashCode();
        goto L69
    L80:
        r12 = r1 * 53;
        r23 = ((String) zzaak.zzn(r9, r42)).hashCode();
        goto L11
    L81:
        r12 = r1 * 53;
        r23 = zzym.zza(zzaak.zzh(r9, r42));
        goto L11
    L82:
        r12 = r1 * 53;
        r23 = zzaak.zzd(r9, r42);
        goto L11
    L83:
        r12 = r1 * 53;
        r25 = zzaak.zzf(r9, r42);
        byte[] r45 = zzym.zzb;
        goto L15
    L84:
        r12 = r1 * 53;
        r23 = zzaak.zzd(r9, r42);
        goto L11
    L85:
        r12 = r1 * 53;
        r25 = zzaak.zzf(r9, r42);
        byte[] r46 = zzym.zzb;
        goto L15
    L86:
        r12 = r1 * 53;
        r25 = zzaak.zzf(r9, r42);
        byte[] r47 = zzym.zzb;
        goto L15
    L87:
        r12 = r1 * 53;
        r23 = Float.floatToIntBits(zzaak.zzj(r9, r42));
        goto L11
    L88:
        r12 = r1 * 53;
        r25 = Double.doubleToLongBits(zzaak.zzl(r9, r42));
        byte[] r48 = zzym.zzb;
        goto L15
    L9:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzaak.zzn(r9, r42).hashCode();
        goto L11
    L13:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = zzw(r9, r42);
        byte[] r49 = zzym.zzb;
        goto L15
    L17:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzv(r9, r42);
        goto L11
    L20:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = zzw(r9, r42);
        byte[] r410 = zzym.zzb;
        goto L15
    L23:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzv(r9, r42);
        goto L11
    L26:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzv(r9, r42);
        goto L11
    L29:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzv(r9, r42);
        goto L11
    L32:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzaak.zzn(r9, r42).hashCode();
        goto L11
    L35:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzaak.zzn(r9, r42).hashCode();
        goto L11
    L38:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = ((String) zzaak.zzn(r9, r42)).hashCode();
        goto L11
    L41:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzym.zza(zzx(r9, r42));
        goto L11
    L44:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzv(r9, r42);
        goto L11
    L47:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = zzw(r9, r42);
        byte[] r411 = zzym.zzb;
        goto L15
    L50:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = zzv(r9, r42);
        goto L11
    L53:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = zzw(r9, r42);
        byte[] r412 = zzym.zzb;
        goto L15
    L56:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = zzw(r9, r42);
        byte[] r413 = zzym.zzb;
        goto L15
    L59:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r23 = Float.floatToIntBits(zzu(r9, r42));
        goto L11
    L62:
        if (zzC(r9, r22, r0) == false) goto L89;
        r12 = r1 * 53;
        r25 = Double.doubleToLongBits(zzt(r9, r42));
        byte[] r414 = zzym.zzb;
    L89:
        r0 = r0 + 3;
        goto L3
    L90:
        int r14 = (r1 * 53) + ((zzyd) r9).zzc.hashCode();
        if (this.zzf == true) goto L93;
        return r14;
    L93:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r9);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final void zzd(Object r9, Object r10) {
        if (zzs(r9) == false) goto L86;
        r10.getClass();
        int r0 = 0;
    L5:
        int[] r1 = this.zzc;
        if (r0 >= r1.length) goto L82;
        int r2 = zzp(r0);
        int r3 = 1048575 & r2;
        int r22 = zzr(r2);
        int r12 = r1[r0];
        long r32 = r3;
        switch(r22) {
            case 0: goto L79;
            case 1: goto L76;
            case 2: goto L73;
            case 3: goto L70;
            case 4: goto L67;
            case 5: goto L64;
            case 6: goto L61;
            case 7: goto L58;
            case 8: goto L55;
            case 9: goto L53;
            case 10: goto L51;
            case 11: goto L48;
            case 12: goto L45;
            case 13: goto L42;
            case 14: goto L39;
            case 15: goto L36;
            case 16: goto L33;
            case 17: goto L31;
            case 18: goto L20;
            case 19: goto L20;
            case 20: goto L20;
            case 21: goto L20;
            case 22: goto L20;
            case 23: goto L20;
            case 24: goto L20;
            case 25: goto L20;
            case 26: goto L20;
            case 27: goto L20;
            case 28: goto L20;
            case 29: goto L20;
            case 30: goto L20;
            case 31: goto L20;
            case 32: goto L20;
            case 33: goto L20;
            case 34: goto L20;
            case 35: goto L20;
            case 36: goto L20;
            case 37: goto L20;
            case 38: goto L20;
            case 39: goto L20;
            case 40: goto L20;
            case 41: goto L20;
            case 42: goto L20;
            case 43: goto L20;
            case 44: goto L20;
            case 45: goto L20;
            case 46: goto L20;
            case 47: goto L20;
            case 48: goto L20;
            case 49: goto L20;
            case 50: goto L18;
            case 51: goto L16;
            case 52: goto L16;
            case 53: goto L16;
            case 54: goto L16;
            case 55: goto L16;
            case 56: goto L16;
            case 57: goto L16;
            case 58: goto L16;
            case 59: goto L16;
            case 60: goto L14;
            case 61: goto L12;
            case 62: goto L12;
            case 63: goto L12;
            case 64: goto L12;
            case 65: goto L12;
            case 66: goto L12;
            case 67: goto L12;
            case 68: goto L10;
            default: goto L81;
        };
    L10:
        zzl(r9, r10, r0);
        goto L81
    L14:
        zzl(r9, r10, r0);
        goto L81
    L20:
        zzyl r13 = (zzyl) zzaak.zzn(r9, r32);
        zzyl r23 = (zzyl) zzaak.zzn(r10, r32);
        int r5 = r13.size();
        int r6 = r23.size();
        if (r5 <= 0) goto L27;
        if (r6 <= 0) goto L27;
        if (r13.zza() == true) goto L26;
        r13 = r13.zzf(r6 + r5);
    L26:
        r13.addAll(r23);
    L27:
        if (r5 <= 0) goto L30;
        r23 = r13;
    L30:
        zzaak.zzo(r9, r32, r23);
        goto L81
    L31:
        zzk(r9, r10, r0);
        goto L81
    L53:
        zzk(r9, r10, r0);
        goto L81
    L12:
        if (zzC(r10, r12, r0) == false) goto L81;
        zzaak.zzo(r9, r32, zzaak.zzn(r10, r32));
        zzD(r9, r12, r0);
        goto L81
    L16:
        if (zzC(r10, r12, r0) == false) goto L81;
        zzaak.zzo(r9, r32, zzaak.zzn(r10, r32));
        zzD(r9, r12, r0);
        goto L81
    L33:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zzg(r9, r32, zzaak.zzf(r10, r32));
        zzB(r9, r0);
        goto L81
    L36:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zze(r9, r32, zzaak.zzd(r10, r32));
        zzB(r9, r0);
        goto L81
    L39:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zzg(r9, r32, zzaak.zzf(r10, r32));
        zzB(r9, r0);
        goto L81
    L42:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zze(r9, r32, zzaak.zzd(r10, r32));
        zzB(r9, r0);
        goto L81
    L45:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zze(r9, r32, zzaak.zzd(r10, r32));
        zzB(r9, r0);
        goto L81
    L48:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zze(r9, r32, zzaak.zzd(r10, r32));
        zzB(r9, r0);
        goto L81
    L51:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zzo(r9, r32, zzaak.zzn(r10, r32));
        zzB(r9, r0);
        goto L81
    L55:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zzo(r9, r32, zzaak.zzn(r10, r32));
        zzB(r9, r0);
        goto L81
    L58:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zzi(r9, r32, zzaak.zzh(r10, r32));
        zzB(r9, r0);
        goto L81
    L61:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zze(r9, r32, zzaak.zzd(r10, r32));
        zzB(r9, r0);
        goto L81
    L64:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zzg(r9, r32, zzaak.zzf(r10, r32));
        zzB(r9, r0);
        goto L81
    L67:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zze(r9, r32, zzaak.zzd(r10, r32));
        zzB(r9, r0);
        goto L81
    L70:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zzg(r9, r32, zzaak.zzf(r10, r32));
        zzB(r9, r0);
        goto L81
    L73:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zzg(r9, r32, zzaak.zzf(r10, r32));
        zzB(r9, r0);
        goto L81
    L76:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zzk(r9, r32, zzaak.zzj(r10, r32));
        zzB(r9, r0);
        goto L81
    L79:
        if (zzA(r10, r0) == false) goto L81;
        zzaak.zzm(r9, r32, zzaak.zzl(r10, r32));
        zzB(r9, r0);
        goto L81
    L18:
        int r8 = zzzu.$r8$clinit;
        Object r82 = zzaak.zzn(r9, r32);
        Object r92 = zzaak.zzn(r10, r32);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r82);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r92);
        throw null;
    L81:
        r0 = r0 + 3;
        goto L5
    L82:
        zzzu.zzE(this.zzi, r9, r10);
        if (this.zzf == false) goto L132;
        zzzu.zzD(this.zzj, r9, r10);
        return;
    L132:
        return;
    L86:
        f$$ExternalSyntheticBUOutline1.m("Mutating immutable message: ".concat(String.valueOf(r9)));
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final int zze(Object r18) {
        zzzl<T> r0 = this;
        Object r1 = r18;
        Unsafe r6 = zzb;
        int r2 = 0;
        int r4 = 0;
        int r9 = 0;
        int r3 = 1048575;
    L3:
        int[] r5 = r0.zzc;
        if (r2 >= r5.length) goto L274;
        int r10 = r0.zzp(r2);
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
        if (r12 < zzxx.zzJ.zza()) goto L17;
        zzxx.zzW.zza();
    L17:
        long r142 = r102;
        switch(r12) {
            case 0: goto L271;
            case 1: goto L268;
            case 2: goto L265;
            case 3: goto L262;
            case 4: goto L259;
            case 5: goto L256;
            case 6: goto L253;
            case 7: goto L250;
            case 8: goto L244;
            case 9: goto L241;
            case 10: goto L237;
            case 11: goto L234;
            case 12: goto L231;
            case 13: goto L227;
            case 14: goto L223;
            case 15: goto L220;
            case 16: goto L214;
            case 17: goto L211;
            case 18: goto L209;
            case 19: goto L208;
            case 20: goto L204;
            case 21: goto L200;
            case 22: goto L196;
            case 23: goto L195;
            case 24: goto L194;
            case 25: goto L190;
            case 26: goto L168;
            case 27: goto L161;
            case 28: goto L154;
            case 29: goto L150;
            case 30: goto L146;
            case 31: goto L145;
            case 32: goto L144;
            case 33: goto L140;
            case 34: goto L135;
            case 35: goto L132;
            case 36: goto L129;
            case 37: goto L126;
            case 38: goto L123;
            case 39: goto L120;
            case 40: goto L117;
            case 41: goto L114;
            case 42: goto L111;
            case 43: goto L108;
            case 44: goto L105;
            case 45: goto L102;
            case 46: goto L99;
            case 47: goto L96;
            case 48: goto L91;
            case 49: goto L84;
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
            default: goto L273;
        };
    L84:
        List r54 = (List) r6.getObject(r1, r142);
        zzzs r103 = r0.zzm(r2);
        int r11 = zzzu.$r8$clinit;
        int r112 = r54.size();
        if (r112 != 0) goto L87;
        int r143 = 0;
    L90:
        r9 = r9 + r143;
        goto L273
    L87:
        int r122 = 0;
        r143 = 0;
    L88:
        if (r122 >= r112) goto L90;
        r143 = r143 + zzzu.zzA(r13, (zzzi) r54.get(r122), r103);
        r122 = r122 + 1;
        goto L88
    L91:
        int r55 = zzzu.zzq((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        int r104 = zzxp.zzv(r13 << 3);
        int r113 = zzxp.zzv(r55);
    L94:
        int r105 = (r104 + r113) + r55;
    L95:
        r9 = r9 + r105;
        goto L273
    L96:
        r55 = zzzu.zzu((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L99:
        r55 = zzzu.zzx((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L102:
        r55 = zzzu.zzv((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L105:
        r55 = zzzu.zzr((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L108:
        r55 = zzzu.zzt((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L111:
        List r56 = (List) r6.getObject(r1, r142);
        int r106 = zzzu.$r8$clinit;
        r55 = r56.size();
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L114:
        r55 = zzzu.zzv((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L117:
        r55 = zzzu.zzx((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L120:
        r55 = zzzu.zzs((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L123:
        r55 = zzzu.zzp((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L126:
        r55 = zzzu.zzo((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L129:
        r55 = zzzu.zzv((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L132:
        r55 = zzzu.zzx((List) r6.getObject(r1, r142));
        if (r55 <= 0) goto L273;
        r104 = zzxp.zzv(r13 << 3);
        r113 = zzxp.zzv(r55);
        goto L94
    L135:
        List r57 = (List) r6.getObject(r1, r142);
        int r107 = zzzu.$r8$clinit;
        int r108 = r57.size();
        if (r108 != 0) goto L138;
    L137:
        int r58 = 0;
    L23:
        r9 = r9 + r58;
        goto L273
    L138:
        int r59 = zzzu.zzq(r57);
        int r114 = zzxp.zzv(r13 << 3);
    L139:
        int r109 = r108 * r114;
    L27:
        r58 = r59 + r109;
        goto L23
    L140:
        List r510 = (List) r6.getObject(r1, r142);
        int r1010 = zzzu.$r8$clinit;
        r108 = r510.size();
        if (r108 == 0) goto L137;
        r59 = zzzu.zzu(r510);
        r114 = zzxp.zzv(r13 << 3);
        goto L139
    L144:
        r58 = zzzu.zzy(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L145:
        r58 = zzzu.zzw(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L146:
        List r511 = (List) r6.getObject(r1, r142);
        int r1011 = zzzu.$r8$clinit;
        r108 = r511.size();
        if (r108 == 0) goto L137;
        r59 = zzzu.zzr(r511);
        r114 = zzxp.zzv(r13 << 3);
        goto L139
    L150:
        List r512 = (List) r6.getObject(r1, r142);
        int r1012 = zzzu.$r8$clinit;
        r108 = r512.size();
        if (r108 == 0) goto L137;
        r59 = zzzu.zzt(r512);
        r114 = zzxp.zzv(r13 << 3);
        goto L139
    L154:
        List r513 = (List) r6.getObject(r1, r142);
        int r1013 = zzzu.$r8$clinit;
        int r1014 = r513.size();
        if (r1014 != 0) goto L157;
        r105 = 0;
        goto L95
    L157:
        r105 = r1014 * zzxp.zzv(r13 << 3);
        int r115 = 0;
    L159:
        if (r115 >= r513.size()) goto L95;
        int r123 = ((zzxk) r513.get(r115)).zzc();
        r105 = r105 + (zzxp.zzv(r123) + r123);
        r115 = r115 + 1;
        goto L159
    L161:
        List r514 = (List) r6.getObject(r1, r142);
        zzzs r1015 = r0.zzm(r2);
        int r116 = zzzu.$r8$clinit;
        int r117 = r514.size();
        if (r117 != 0) goto L164;
        int r124 = 0;
    L167:
        r9 = r9 + r124;
        goto L273
    L164:
        r124 = zzxp.zzv(r13 << 3) * r117;
        int r132 = 0;
    L165:
        if (r132 >= r117) goto L167;
        int r144 = ((zzwz) r514.get(r132)).zzt(r1015);
        r124 = r124 + (zzxp.zzv(r144) + r144);
        r132 = r132 + 1;
        goto L165
    L168:
        List r515 = (List) r6.getObject(r1, r142);
        int r1016 = zzzu.$r8$clinit;
        int r1017 = r515.size();
        if (r1017 != 0) goto L171;
    L170:
        int r118 = 0;
    L189:
        r9 = r9 + r118;
        goto L273
    L171:
        r118 = zzxp.zzv(r13 << 3) * r1017;
        if ((r515 instanceof zzyu) == false) goto L181;
        zzyu r516 = (zzyu) r515;
        int r125 = 0;
    L174:
        if (r125 >= r1017) goto L189;
        Object r133 = r516.zza();
        if ((r133 instanceof zzxk) == false) goto L179;
        int r134 = ((zzxk) r133).zzc();
        int r145 = zzxp.zzv(r134);
    L178:
        r118 = r118 + (r145 + r134);
        r125 = r125 + 1;
        goto L174
    L179:
        r134 = zzaao.zza((String) r133);
        r145 = zzxp.zzv(r134);
        goto L178
    L181:
        int r126 = 0;
    L182:
        if (r126 >= r1017) goto L189;
        Object r135 = r515.get(r126);
        if ((r135 instanceof zzxk) == false) goto L187;
        int r136 = ((zzxk) r135).zzc();
        int r146 = zzxp.zzv(r136);
    L186:
        r118 = r118 + (r146 + r136);
        r126 = r126 + 1;
        goto L182
    L187:
        r136 = zzaao.zza((String) r135);
        r146 = zzxp.zzv(r136);
        goto L186
    L190:
        List r517 = (List) r6.getObject(r1, r142);
        int r1018 = zzzu.$r8$clinit;
        int r518 = r517.size();
        if (r518 == 0) goto L137;
        r58 = r518 * (zzxp.zzv(r13 << 3) + 1);
        goto L23
    L194:
        r58 = zzzu.zzw(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L195:
        r58 = zzzu.zzy(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L196:
        List r519 = (List) r6.getObject(r1, r142);
        int r1019 = zzzu.$r8$clinit;
        r108 = r519.size();
        if (r108 == 0) goto L137;
        r59 = zzzu.zzs(r519);
        r114 = zzxp.zzv(r13 << 3);
        goto L139
    L200:
        List r520 = (List) r6.getObject(r1, r142);
        int r1020 = zzzu.$r8$clinit;
        r108 = r520.size();
        if (r108 == 0) goto L137;
        r59 = zzzu.zzp(r520);
        r114 = zzxp.zzv(r13 << 3);
        goto L139
    L204:
        List r521 = (List) r6.getObject(r1, r142);
        int r1021 = zzzu.$r8$clinit;
        if (r521.size() == 0) goto L170;
        r118 = zzzu.zzo(r521) + (r521.size() * zzxp.zzv(r13 << 3));
        goto L189
    L208:
        r58 = zzzu.zzw(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L209:
        r58 = zzzu.zzy(r13, (List) r6.getObject(r1, r142), false);
        goto L23
    L21:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        r58 = zzzu.zzA(r13, (zzzi) r6.getObject(r1, r142), r0.zzm(r2));
        goto L23
    L25:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        long r119 = zzw(r1, r142);
        r59 = zzxp.zzv(r13 << 3);
        r109 = zzxp.zzw((r119 >> 63) ^ (r119 + r119));
        goto L27
    L29:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        int r1022 = zzv(r1, r142);
        r59 = zzxp.zzv(r13 << 3);
        r109 = zzxp.zzv((r1022 >> 31) ^ (r1022 + r1022));
        goto L27
    L32:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        int r522 = zzxp.zzv(r13 << 3);
    L34:
        r58 = r522 + 8;
        goto L23
    L36:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        int r523 = zzxp.zzv(r13 << 3);
    L38:
        r58 = r523 + 4;
        goto L23
    L40:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        long r1023 = zzv(r1, r142);
        r59 = zzxp.zzv(r13 << 3);
        r109 = zzxp.zzw(r1023);
        goto L27
    L43:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        int r1024 = zzv(r1, r142);
        r59 = zzxp.zzv(r13 << 3);
        r109 = zzxp.zzv(r1024);
        goto L27
    L46:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        zzxk r1025 = (zzxk) r6.getObject(r1, r142);
        int r524 = zzxp.zzv(r13 << 3);
        int r1026 = r1025.zzc();
        int r1110 = zzxp.zzv(r1026);
    L48:
        r58 = r524 + (r1110 + r1026);
        goto L23
    L50:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        r58 = zzzu.zzz(r13, r6.getObject(r1, r142), r0.zzm(r2));
        goto L23
    L53:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        int r525 = r13 << 3;
        Object r1027 = r6.getObject(r1, r142);
        if ((r1027 instanceof zzxk) == false) goto L57;
        r524 = zzxp.zzv(r525);
        r1026 = ((zzxk) r1027).zzc();
        r1110 = zzxp.zzv(r1026);
        goto L48
    L57:
        r524 = zzxp.zzv(r525);
        r1026 = zzaao.zza((String) r1027);
        r1110 = zzxp.zzv(r1026);
        goto L48
    L59:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        r58 = zzxp.zzv(r13 << 3) + 1;
        goto L23
    L62:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        r523 = zzxp.zzv(r13 << 3);
        goto L38
    L65:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        r522 = zzxp.zzv(r13 << 3);
        goto L34
    L68:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        long r1028 = zzv(r1, r142);
        r59 = zzxp.zzv(r13 << 3);
        r109 = zzxp.zzw(r1028);
        goto L27
    L71:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        long r1029 = zzw(r1, r142);
        r59 = zzxp.zzv(r13 << 3);
        r109 = zzxp.zzw(r1029);
        goto L27
    L74:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        long r1030 = zzw(r1, r142);
        r59 = zzxp.zzv(r13 << 3);
        r109 = zzxp.zzw(r1030);
        goto L27
    L77:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        r523 = zzxp.zzv(r13 << 3);
        goto L38
    L80:
        if (r0.zzC(r1, r13, r2) == false) goto L273;
        r522 = zzxp.zzv(r13 << 3);
        goto L34
    L211:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L273;
        r58 = zzzu.zzA(r13, (zzzi) r6.getObject(r1, r142), r0.zzm(r2));
        goto L23
    L214:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        long r1111 = r6.getLong(r1, r142);
        int r02 = zzxp.zzv(r13 << 3);
        int r526 = zzxp.zzw((r1111 >> 63) ^ (r1111 + r1111));
    L216:
        int r03 = r02 + r526;
    L217:
        r9 = r9 + r03;
    L218:
        r0 = this;
        goto L273
    L220:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        int r527 = r6.getInt(r1, r142);
        r02 = zzxp.zzv(r13 << 3);
        r526 = zzxp.zzv((r527 >> 31) ^ (r527 + r527));
        goto L216
    L223:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        int r04 = zzxp.zzv(r13 << 3);
    L225:
        r03 = r04 + 8;
        goto L217
    L227:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        int r05 = zzxp.zzv(r13 << 3);
    L229:
        r03 = r05 + 4;
        goto L217
    L231:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        long r1031 = r6.getInt(r1, r142);
        r02 = zzxp.zzv(r13 << 3);
        r526 = zzxp.zzw(r1031);
        goto L216
    L234:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        int r528 = r6.getInt(r1, r142);
        r02 = zzxp.zzv(r13 << 3);
        r526 = zzxp.zzv(r528);
        goto L216
    L237:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        zzxk r529 = (zzxk) r6.getObject(r1, r142);
        int r06 = zzxp.zzv(r13 << 3);
        int r530 = r529.zzc();
        int r1032 = zzxp.zzv(r530);
    L239:
        r03 = r06 + (r1032 + r530);
        goto L217
    L241:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L273;
        r58 = zzzu.zzz(r13, r6.getObject(r1, r142), r0.zzm(r2));
        goto L23
    L244:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        int r07 = r13 << 3;
        Object r531 = r6.getObject(r1, r142);
        if ((r531 instanceof zzxk) == false) goto L248;
        r06 = zzxp.zzv(r07);
        r530 = ((zzxk) r531).zzc();
        r1032 = zzxp.zzv(r530);
        goto L239
    L248:
        r06 = zzxp.zzv(r07);
        r530 = zzaao.zza((String) r531);
        r1032 = zzxp.zzv(r530);
        goto L239
    L250:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        r03 = zzxp.zzv(r13 << 3) + 1;
        goto L217
    L253:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        r05 = zzxp.zzv(r13 << 3);
        goto L229
    L256:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        r04 = zzxp.zzv(r13 << 3);
        goto L225
    L259:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        long r1033 = r6.getInt(r1, r142);
        r02 = zzxp.zzv(r13 << 3);
        r526 = zzxp.zzw(r1033);
        goto L216
    L262:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        long r1034 = r6.getLong(r1, r142);
        r02 = zzxp.zzv(r13 << 3);
        r526 = zzxp.zzw(r1034);
        goto L216
    L265:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        long r1035 = r6.getLong(r1, r142);
        r02 = zzxp.zzv(r13 << 3);
        r526 = zzxp.zzw(r1035);
        goto L216
    L268:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L218;
        r05 = zzxp.zzv(r13 << 3);
        goto L229
    L271:
        if (r0.zzz(r1, r2, r3, r4, r53) == false) goto L273;
        r9 = r9 + (zzxp.zzv(r13 << 3) + 8);
        goto L273
    L82:
        Object r15 = r6.getObject(r1, r142);
        Object r08 = r0.zzn(r2);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r15);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r08);
        throw null;
    L273:
        r2 = r2 + 3;
        r1 = r18;
        goto L3
    L13:
        r53 = 0;
        goto L14
    L274:
        int r92 = r9 + ((zzyd) r18).zzc.zzf();
        if (r0.zzf == true) goto L277;
        return r92;
    L277:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r18);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final void zzf(Object r18, zzaar r19) {
        zzzl<T> r0 = this;
        if (r0.zzf == true) goto L190;
        int[] r8 = r0.zzc;
        Unsafe r9 = zzb;
        int r10 = 1048575;
        int r3 = 1048575;
        int r2 = 0;
        int r4 = 0;
    L6:
        if (r2 >= r8.length) goto L188;
        int r5 = r0.zzp(r2);
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
        zzzs r103 = r0.zzm(r2);
        int r11 = zzzu.$r8$clinit;
        if (r73 == null) goto L187;
        if (r73.isEmpty() == true) goto L187;
        int r112 = 0;
    L85:
        if (r112 >= r73.size()) goto L187;
        ((zzxq) r19).zzs(r52, r73.get(r112), r103);
        r112 = r112 + 1;
        goto L85
    L87:
        zzzu.zze(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L88:
        zzzu.zzj(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L89:
        zzzu.zzg(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L90:
        zzzu.zzl(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L91:
        zzzu.zzm(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L92:
        zzzu.zzi(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L93:
        zzzu.zzn(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L94:
        zzzu.zzk(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L95:
        zzzu.zzf(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L96:
        zzzu.zzh(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L97:
        zzzu.zzd(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L98:
        zzzu.zzc(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L99:
        zzzu.zzb(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L100:
        zzzu.zza(r8[r2], (List) r9.getObject(r18, r102), r19, true);
        goto L187
    L101:
        zzzu.zze(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L102:
        zzzu.zzj(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L103:
        zzzu.zzg(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L104:
        zzzu.zzl(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L105:
        zzzu.zzm(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L106:
        zzzu.zzi(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L107:
        int r53 = r8[r2];
        List r74 = (List) r9.getObject(r18, r102);
        int r104 = zzzu.$r8$clinit;
        if (r74 == null) goto L187;
        if (r74.isEmpty() == true) goto L187;
        r19.zzE(r53, r74);
        goto L187
    L112:
        int r54 = r8[r2];
        List r75 = (List) r9.getObject(r18, r102);
        zzzs r105 = r0.zzm(r2);
        int r113 = zzzu.$r8$clinit;
        if (r75 == null) goto L187;
        if (r75.isEmpty() == true) goto L187;
        int r122 = 0;
    L118:
        if (r122 >= r75.size()) goto L187;
        ((zzxq) r19).zzr(r54, r75.get(r122), r105);
        r122 = r122 + 1;
        goto L118
    L120:
        int r55 = r8[r2];
        List r76 = (List) r9.getObject(r18, r102);
        int r106 = zzzu.$r8$clinit;
        if (r76 == null) goto L187;
        if (r76.isEmpty() == true) goto L187;
        r19.zzD(r55, r76);
        goto L187
    L125:
        zzzu.zzn(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L126:
        zzzu.zzk(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L127:
        zzzu.zzf(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L128:
        zzzu.zzh(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L129:
        zzzu.zzd(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L130:
        zzzu.zzc(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L131:
        zzzu.zzb(r8[r2], (List) r9.getObject(r18, r102), r19, false);
        goto L187
    L132:
        zzzu.zza(r8[r2], (List) r9.getObject(r18, r102), r19, false);
    L187:
        r2 = r2 + 3;
        r10 = 1048575;
        r0 = this;
        goto L6
    L21:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzs(r13, r9.getObject(r18, r102), r0.zzm(r2));
        goto L187
    L24:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzq(r13, zzw(r18, r102));
        goto L187
    L27:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzp(r13, zzv(r18, r102));
        goto L187
    L30:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzd(r13, zzw(r18, r102));
        goto L187
    L33:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzb(r13, zzv(r18, r102));
        goto L187
    L36:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzg(r13, zzv(r18, r102));
        goto L187
    L39:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzo(r13, zzv(r18, r102));
        goto L187
    L42:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzn(r13, (zzxk) r9.getObject(r18, r102));
        goto L187
    L45:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzr(r13, r9.getObject(r18, r102), r0.zzm(r2));
        goto L187
    L48:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        zzE(r13, r9.getObject(r18, r102), r19);
        goto L187
    L51:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzl(r13, zzx(r18, r102));
        goto L187
    L54:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzk(r13, zzv(r18, r102));
        goto L187
    L57:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzj(r13, zzw(r18, r102));
        goto L187
    L60:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzi(r13, zzv(r18, r102));
        goto L187
    L63:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzh(r13, zzw(r18, r102));
        goto L187
    L66:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzc(r13, zzw(r18, r102));
        goto L187
    L69:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zze(r13, zzu(r18, r102));
        goto L187
    L72:
        if (r0.zzC(r18, r13, r2) == false) goto L187;
        r19.zzf(r13, zzt(r18, r102));
        goto L187
    L75:
        if (r9.getObject(r18, r102) == null) goto L187;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.zzn(r2));
        throw r16;
    L134:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzs(r13, r9.getObject(r18, r102), r0.zzm(r2));
        goto L187
    L137:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzq(r13, r9.getLong(r18, r102));
        goto L187
    L140:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzp(r13, r9.getInt(r18, r102));
        goto L187
    L143:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzd(r13, r9.getLong(r18, r102));
        goto L187
    L146:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzb(r13, r9.getInt(r18, r102));
        goto L187
    L149:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzg(r13, r9.getInt(r18, r102));
        goto L187
    L152:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzo(r13, r9.getInt(r18, r102));
        goto L187
    L155:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzn(r13, (zzxk) r9.getObject(r18, r102));
        goto L187
    L158:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzr(r13, r9.getObject(r18, r102), r0.zzm(r2));
        goto L187
    L161:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        zzE(r13, r9.getObject(r18, r102), r19);
        goto L187
    L164:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzl(r13, zzaak.zzh(r18, r102));
        goto L187
    L167:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzk(r13, r9.getInt(r18, r102));
        goto L187
    L170:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzj(r13, r9.getLong(r18, r102));
        goto L187
    L173:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzi(r13, r9.getInt(r18, r102));
        goto L187
    L176:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzh(r13, r9.getLong(r18, r102));
        goto L187
    L179:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzc(r13, r9.getLong(r18, r102));
        goto L187
    L182:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zze(r13, zzaak.zzj(r18, r102));
        goto L187
    L185:
        if (r0.zzz(r18, r2, r3, r4, r72) == false) goto L187;
        r19.zzf(r13, zzaak.zzl(r18, r102));
        goto L187
    L16:
        r16 = null;
        r72 = 0;
        goto L17
    L188:
        zzaae r02 = ((zzyd) r18).zzc;
        return;
    L190:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r18);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final void zzg(Object r7) {
        if (zzs(r7) == false) goto L46;
        int r1 = 0;
        if ((r7 instanceof zzyd) == false) goto L8;
        zzyd r0 = (zzyd) r7;
        r0.zzC(Integer.MAX_VALUE);
        r0.zza = 0;
        r0.zzw();
    L8:
        int[] r02 = this.zzc;
    L10:
        if (r1 >= r02.length) goto L32;
        int r2 = zzp(r1);
        int r3 = 1048575 & r2;
        int r22 = zzr(r2);
        long r32 = r3;
        if (r22 == 9) goto L29;
        if (r22 == 60) goto L26;
        if (r22 == 68) goto L26;
        switch(r22) {
            case 17: goto L29;
            case 18: goto L24;
            case 19: goto L24;
            case 20: goto L24;
            case 21: goto L24;
            case 22: goto L24;
            case 23: goto L24;
            case 24: goto L24;
            case 25: goto L24;
            case 26: goto L24;
            case 27: goto L24;
            case 28: goto L24;
            case 29: goto L24;
            case 30: goto L24;
            case 31: goto L24;
            case 32: goto L24;
            case 33: goto L24;
            case 34: goto L24;
            case 35: goto L24;
            case 36: goto L24;
            case 37: goto L24;
            case 38: goto L24;
            case 39: goto L24;
            case 40: goto L24;
            case 41: goto L24;
            case 42: goto L24;
            case 43: goto L24;
            case 44: goto L24;
            case 45: goto L24;
            case 46: goto L24;
            case 47: goto L24;
            case 48: goto L24;
            case 49: goto L24;
            case 50: goto L19;
            default: goto L31;
        };
    L19:
        Object r23 = zzb.getObject(r7, r32);
        if (r23 == null) goto L31;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r23);
        throw null;
    L24:
        ((zzyl) zzaak.zzn(r7, r32)).zzb();
    L31:
        r1 = r1 + 3;
    L26:
        if (zzC(r7, r02[r1], r1) == false) goto L31;
        zzm(r1).zzg(zzb.getObject(r7, r32));
    L29:
        if (zzA(r7, r1) == false) goto L31;
        zzm(r1).zzg(zzb.getObject(r7, r32));
        goto L31
    L32:
        this.zzi.zza(r7);
        if (this.zzf == false) goto L45;
        this.zzj.zza(r7);
        return;
    L45:
        return;
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final boolean zzh(Object r16) {
        int r2 = 0;
        int r8 = 0;
        int r0 = 1048575;
    L4:
        if (r8 >= this.zzh) goto L55;
        int[] r3 = this.zzg;
        int[] r5 = this.zzc;
        int r32 = r3[r8];
        int r10 = r5[r32];
        int r11 = zzp(r32);
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
        if ((268435456 & r11) == 0) goto L16;
        int r22 = r32;
        int r33 = r0;
        if (zzz(r16, r22, r33, r4, r53) == false) goto L15;
    L17:
        int r122 = zzr(r11);
        if (r122 == 9) goto L49;
        if (r122 == 17) goto L49;
        if (r122 != 27) goto L24;
    L39:
        List r54 = (List) zzaak.zzn(r16, r11 & 1048575);
        if (r54.isEmpty() == true) goto L53;
        zzzs r23 = zzm(r22);
        int r9 = 0;
    L43:
        if (r9 >= r54.size()) goto L53;
        if (r23.zzh(r54.get(r9)) == false) goto L46;
        r9 = r9 + 1;
        goto L43
    L46:
        return false;
    L53:
        r8 = r8 + 1;
        r0 = r33;
        r2 = r4;
        goto L4
    L24:
        if (r122 == 60) goto L35;
        if (r122 == 68) goto L35;
        if (r122 == 49) goto L39;
        if (r122 != 50) goto L53;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(zzaak.zzn(r16, r11 & 1048575));
        throw null;
    L35:
        if (zzC(r16, r10, r22) == false) goto L53;
        if (zzo(r16, r11, zzm(r22)) == true) goto L53;
        return false;
    L49:
        if (zzz(r16, r22, r33, r4, r53) == false) goto L53;
        if (zzo(r16, r11, zzm(r22)) == true) goto L53;
        return false;
    L15:
        return false;
    L16:
        r22 = r32;
        r33 = r0;
        goto L17
    L10:
        r4 = r2;
        goto L12
    L55:
        if (this.zzf == true) goto L57;
        return true;
    L57:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r16);
        throw null;
    }
}
