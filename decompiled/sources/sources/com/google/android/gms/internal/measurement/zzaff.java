package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.cast.zzzl$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.cast.zzzl$$ExternalSyntheticBUOutline1;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;
import sun.misc.Unsafe;

/* loaded from: classes4.dex */
final class zzaff<T> implements zzafp<T> {
    private static final int[] zza = null;
    private static final Unsafe zzb = null;
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzafc zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzafz zzm;
    private final zzadg zzn;

    static {
        zza = new int[0];
        zzb = zzagg.zzp();
    }

    private zzaff(int[] r1, Object[] r2, int r3, int r4, zzafc r5, boolean r6, int[] r7, int r8, int r9, zzafh r10, zzaeo r11, zzafz r12, zzadg r13, zzaex r14) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzi = r5 instanceof zzadu;
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
        if ((r1 instanceof zzadu) == true) goto L8;
        return true;
    L8:
        return ((zzadu) r1).zzch();
    }

    private static void zzF(Object r1) {
        if (zzE(r1) == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Mutating immutable message: ".concat(String.valueOf(r1)));
    }

    private static int zzG(Object r0, long r1) {
        return ((Integer) zzagg.zzm(r0, r1)).intValue();
    }

    private static long zzH(Object r0, long r1) {
        return ((Long) zzagg.zzm(r0, r1)).longValue();
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
        Object r82 = zzagg.zzm(r8, r02);
        if ((r82 instanceof String) == false) goto L51;
        if (((String) r82).isEmpty() == true) goto L49;
        return true;
    L49:
        return false;
    L51:
        if ((r82 instanceof zzacr) == false) goto L57;
        if (zzacr.zza.equals(r82) == true) goto L55;
        return true;
    L55:
        return false;
    L57:
        return zzR();
    L7:
        return zzR();
    L9:
        if (zzagg.zzm(r8, r02) == null) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (zzagg.zze(r8, r02) == 0) goto L15;
        return true;
    L15:
        return false;
    L17:
        if (zzagg.zzc(r8, r02) == 0) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (zzagg.zze(r8, r02) == 0) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (zzagg.zzc(r8, r02) == 0) goto L27;
        return true;
    L27:
        return false;
    L29:
        if (zzagg.zzc(r8, r02) == 0) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (zzagg.zzc(r8, r02) == 0) goto L35;
        return true;
    L35:
        return false;
    L37:
        if (zzacr.zza.equals(zzagg.zzm(r8, r02)) == true) goto L39;
        return true;
    L39:
        return false;
    L41:
        if (zzagg.zzm(r8, r02) == null) goto L43;
        return true;
    L43:
        return false;
    L59:
        return zzagg.zzg(r8, r02);
    L61:
        if (zzagg.zzc(r8, r02) == 0) goto L63;
        return true;
    L63:
        return false;
    L65:
        if (zzagg.zze(r8, r02) == 0) goto L67;
        return true;
    L67:
        return false;
    L69:
        if (zzagg.zzc(r8, r02) == 0) goto L71;
        return true;
    L71:
        return false;
    L73:
        if (zzagg.zze(r8, r02) == 0) goto L75;
        return true;
    L75:
        return false;
    L77:
        if (zzagg.zze(r8, r02) == 0) goto L79;
        return true;
    L79:
        return false;
    L81:
        if (Float.floatToRawIntBits(zzagg.zzi(r8, r02)) == 0) goto L83;
        return true;
    L83:
        return false;
    L85:
        if (Double.doubleToRawLongBits(zzagg.zzk(r8, r02)) == 0) goto L87;
        return true;
    L87:
        return false;
    L89:
        if (((1 << (r0 >>> 20)) & zzagg.zzc(r8, r2)) == 0) goto L91;
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
        zzagg.zzd(r5, r0, (1 << (r4 >>> 20)) | zzagg.zzc(r5, r0));
    }

    private final boolean zzM(Object r3, int r4, int r5) {
        if (zzagg.zzc(r3, zzB(r5) & 1048575) != r4) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean zzN(Object r3, Object r4, int r5) {
        long r0 = zzB(r5) & 1048575;
        if (zzagg.zzc(r3, r0) != zzagg.zzc(r4, r0)) goto L6;
        return true;
    L6:
        return false;
    }

    private final void zzO(Object r3, int r4, int r5) {
        zzagg.zzd(r3, zzB(r5) & 1048575, r4);
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

    private static final int zzS(byte[] r1, int r2, int r3, zzagm r4, Class r5, zzacg r6) {
        zzagm r0 = zzagm.zza;
        boolean r02 = false;
        switch(r4.ordinal()) {
            case 0: goto L31;
            case 1: goto L29;
            case 2: goto L27;
            case 3: goto L27;
            case 4: goto L25;
            case 5: goto L23;
            case 6: goto L21;
            case 7: goto L16;
            case 8: goto L15;
            case 9: goto L4;
            case 10: goto L13;
            case 11: goto L11;
            case 12: goto L25;
            case 13: goto L25;
            case 14: goto L21;
            case 15: goto L23;
            case 16: goto L8;
            case 17: goto L6;
            default: goto L4;
        };
    L4:
        GlShader$$ExternalSyntheticBUOutline1.m("unsupported field type.");
        return 0;
    L6:
        int r12 = zzach.zzc(r1, r2, r6);
        r6.zzc = Long.valueOf(zzacv.zzS(r6.zzb));
        return r12;
    L8:
        int r13 = zzach.zza(r1, r2, r6);
        r6.zzc = Integer.valueOf(zzacv.zzR(r6.zza));
        return r13;
    L16:
        int r14 = zzach.zzc(r1, r2, r6);
        if (r6.zzb == 0) goto L19;
        r02 = true;
    L19:
        r6.zzc = Boolean.valueOf(r02);
        return r14;
    L21:
        int r32 = r2 + 4;
        r6.zzc = Integer.valueOf(zzach.zzd(r1, r2));
        return r32;
    L23:
        int r33 = r2 + 8;
        r6.zzc = Long.valueOf(zzach.zze(r1, r2));
        return r33;
    L25:
        int r15 = zzach.zza(r1, r2, r6);
        r6.zzc = Integer.valueOf(r6.zza);
        return r15;
    L27:
        int r16 = zzach.zzc(r1, r2, r6);
        r6.zzc = Long.valueOf(r6.zzb);
        return r16;
    L29:
        int r34 = r2 + 4;
        r6.zzc = Float.valueOf(Float.intBitsToFloat(zzach.zzd(r1, r2)));
        return r34;
    L31:
        int r35 = r2 + 8;
        r6.zzc = Double.valueOf(Double.longBitsToDouble(zzach.zze(r1, r2)));
        return r35;
    L11:
        return zzach.zzg(r1, r2, r6);
    L13:
        return zzach.zzh(zzafl.zza().zzb(r5), r1, r2, r3, r6);
    L15:
        return zzach.zzf(r1, r2, r6);
    }

    private static final void zzT(int r1, Object r2, zzago r3) {
        if ((r2 instanceof String) == false) goto L6;
        r3.zzm(r1, (String) r2);
        return;
    L6:
        r3.zzn(r1, (zzacr) r2);
    }

    public static zzaga zzh(Object r2) {
        zzadu r22 = (zzadu) r2;
        zzaga r0 = r22.zzc;
        if (r0 != zzaga.zza()) goto L6;
        zzaga r02 = zzaga.zzb();
        r22.zzc = r02;
        return r02;
    L6:
        return r0;
    }

    public static zzaff zzm(Class r31, zzaez r32, zzafh r33, zzaeo r34, zzafz r35, zzadg r36, zzaex r37) {
        if ((r32 instanceof zzafn) == false) goto L201;
        zzafn r0 = (zzafn) r32;
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
    L76:
        Unsafe r14 = zzb;
        Object[] r15 = r0.zze();
        Class<?> r3 = r0.zzb().getClass();
        int r18 = r17 + r92;
        int r93 = r11 + r11;
        int[] r112 = new int[r11 * 3];
        Object[] r94 = new Object[r93];
        int r22 = r17;
        int r21 = r18;
        int r19 = 0;
        int r20 = 0;
    L77:
        if (r42 >= r2) goto L200;
        int r23 = r42 + 1;
        int r44 = r1.charAt(r42);
        if (r44 < r5) goto L85;
        int r45 = r44 & 8191;
        int r8 = r23;
        int r232 = 13;
    L81:
        int r24 = r8 + 1;
        char r82 = r1.charAt(r8);
        if (r82 < r5) goto L84;
        r45 = r45 | ((r82 & 8191) << r232);
        r232 = r232 + 13;
        r8 = r24;
        goto L81
    L84:
        r44 = r45 | (r82 << r232);
        int r83 = r24;
    L86:
        int r233 = r83 + 1;
        int r84 = r1.charAt(r83);
        if (r84 < r5) goto L93;
        int r85 = r84 & 8191;
        int r6 = r233;
        int r234 = 13;
    L89:
        int r25 = r6 + 1;
        char r62 = r1.charAt(r6);
        if (r62 < r5) goto L92;
        r85 = r85 | ((r62 & 8191) << r234);
        r234 = r234 + 13;
        r6 = r25;
        goto L89
    L92:
        r84 = r85 | (r62 << r234);
        int r63 = r25;
    L95:
        if ((r84 & 1024) == 0) goto L97;
        r16[r19] = r20;
        r19 = r19 + 1;
    L97:
        int r52 = r84 & 255;
        zzafn r252 = r0;
        int r26 = r84 & 2048;
        if (r52 < 51) goto L134;
        int r02 = r63 + 1;
        int r64 = r1.charAt(r63);
        char r03 = 55296;
        if (r64 < 55296) goto L106;
        int r27 = r64 & 8191;
        int r65 = r02;
        int r29 = 13;
    L102:
        int r30 = r65 + 1;
        char r66 = r1.charAt(r65);
        if (r66 < r03) goto L105;
        r27 = r27 | ((r66 & 8191) << r29);
        r29 = r29 + 13;
        r65 = r30;
        r03 = 55296;
        goto L102
    L105:
        r64 = r27 | (r66 << r29);
        int r04 = r30;
    L107:
        int r272 = r04;
        int r05 = r52 - 51;
        int r292 = r2;
        if (r05 == 9) goto L122;
        if (r05 == 17) goto L122;
        if (r05 == 12) goto L115;
    L121:
        int r06 = r26;
    L123:
        int r67 = r64 + r64;
        Object r28 = r15[r67];
        r26 = r06;
        if ((r28 instanceof Field) == false) goto L127;
        Field r210 = (Field) r28;
    L126:
        int r07 = r74;
        int r211 = (int) r14.objectFieldOffset(r210);
        int r68 = r67 + 1;
        Object r75 = r15[r68];
        int r302 = r07;
        if ((r75 instanceof Field) == false) goto L131;
        Field r76 = (Field) r75;
    L132:
        int r08 = (int) r14.objectFieldOffset(r76);
        String r77 = r1;
        int r69 = r211;
        int r212 = 0;
        char r235 = 55296;
    L133:
        int r110 = r08;
        int r09 = r26;
    L187:
        int r262 = r20 + 1;
        r112[r20] = r44;
        int r46 = r20 + 2;
        int r282 = r09;
        if ((r84 & 512) == 0) goto L190;
        int r010 = 536870912;
    L192:
        if ((r84 & 256) == 0) goto L194;
        int r86 = 268435456;
    L195:
        if (r282 == 0) goto L197;
        int r283 = Integer.MIN_VALUE;
    L198:
        r112[r262] = (((r010 | r86) | r283) | (r52 << 20)) | r69;
        r20 = r20 + 3;
        r112[r46] = (r212 << 20) | r110;
        r1 = r77;
        r5 = r235;
        r0 = r252;
        r42 = r272;
        r2 = r292;
        r74 = r302;
        goto L77
    L197:
        r283 = 0;
        goto L198
    L194:
        r86 = 0;
        goto L195
    L190:
        r010 = 0;
        goto L192
    L131:
        r76 = zzn(r3, (String) r75);
        r15[r68] = r76;
        goto L132
    L127:
        r210 = zzn(r3, (String) r28);
        r15[r67] = r210;
        r16[r21] = r20;
        r21 = r21 + 1;
        goto L126
    L115:
        if (r252.zzc() == 1) goto L119;
        if (r26 != 0) goto L119;
        r06 = 0;
    L119:
        int r011 = r102 + 1;
        int r242 = r20 / 3;
        r94[(r242 + r242) + 1] = r15[r102];
    L120:
        r102 = r011;
    L122:
        r011 = r102 + 1;
        int r243 = r20 / 3;
        r94[(r243 + r243) + 1] = r15[r102];
        goto L120
    L106:
        r04 = r02;
        goto L107
    L134:
        r292 = r2;
        r302 = r74;
        int r012 = r102 + 1;
        Field r213 = zzn(r3, (String) r15[r102]);
        if (r52 != 9) goto L137;
    L138:
        int r273 = r012;
        int r78 = r20 / 3;
        r94[(r78 + r78) + 1] = r213.getType();
    L168:
        r77 = r1;
        r102 = r273;
    L169:
        int r214 = (int) r14.objectFieldOffset(r213);
        r110 = 1048575;
        if ((r84 & 4096) != 0) goto L172;
    L186:
        r235 = 55296;
        r272 = r63;
        r09 = r26;
        r69 = r214;
        r212 = 0;
        goto L187
    L172:
        if (r52 > 17) goto L186;
        int r013 = r63 + 1;
        int r111 = r77.charAt(r63);
        if (r111 < 55296) goto L180;
        int r113 = r111 & 8191;
        int r236 = 13;
    L176:
        int r274 = r013 + 1;
        char r014 = r77.charAt(r013);
        if (r014 < 55296) goto L179;
        r113 = r113 | ((r014 & 8191) << r236);
        r236 = r236 + 13;
        r013 = r274;
        goto L176
    L179:
        r111 = r113 | (r014 << r236);
        r013 = r274;
    L180:
        int r237 = (r302 + r302) + (r111 / 32);
        Object r610 = r15[r237];
        int r284 = r013;
        if ((r610 instanceof Field) == false) goto L184;
        Field r611 = (Field) r610;
    L183:
        int r238 = r111;
        r08 = (int) r14.objectFieldOffset(r611);
        int r114 = r238 % 32;
        r69 = r214;
        r272 = r284;
        r235 = 55296;
        r212 = r114;
        goto L133
    L184:
        r611 = zzn(r3, (String) r610);
        r15[r237] = r611;
        goto L183
    L137:
        if (r52 == 17) goto L138;
        if (r52 != 27) goto L142;
        int r275 = r012;
        int r015 = 1;
        r102 = r102 + 2;
    L166:
        int r79 = r20 / 3;
        r94[(r79 + r79) + r015] = r15[r275];
    L157:
        r77 = r1;
        goto L169
    L142:
        if (r52 != 49) goto L145;
        r102 = r102 + 2;
        r275 = r012;
        r015 = 1;
        goto L166
    L145:
        if (r52 == 12) goto L160;
        if (r52 == 30) goto L160;
        if (r52 == 44) goto L160;
        if (r52 != 50) goto L158;
        int r710 = r102 + 2;
        int r276 = r22 + 1;
        r16[r22] = r20;
        int r222 = r20 / 3;
        int r223 = r222 + r222;
        r94[r223] = r15[r012];
        if (r26 == 0) goto L156;
        r94[r223 + 1] = r15[r710];
        r102 = r102 + 3;
        r77 = r1;
        r22 = r276;
        goto L169
    L156:
        r102 = r710;
        r22 = r276;
        r26 = 0;
        goto L157
    L158:
        r273 = r012;
    L160:
        if (r252.zzc() == 1) goto L164;
        if (r26 != 0) goto L164;
        r77 = r1;
        r102 = r012;
        r26 = 0;
    L164:
        r102 = r102 + 2;
        int r711 = r20 / 3;
        r94[(r711 + r711) + 1] = r15[r012];
        goto L157
    L93:
        r63 = r233;
        goto L95
    L85:
        r83 = r23;
        goto L86
    L200:
        return new zzaff(r112, r94, r12, r13, r0.zzb(), false, r16, r17, r18, r33, r34, r35, r36, r37);
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
        int r115 = r96 + 1;
        char r97 = r1.charAt(r96);
        if (r97 < 55296) goto L33;
        r715 = r715 | ((r97 & 8191) << r104);
        r104 = r104 + 13;
        r96 = r115;
        goto L30
    L33:
        r714 = r715 | (r97 << r104);
        r96 = r115;
    L34:
        int r105 = r96 + 1;
        int r98 = r1.charAt(r96);
        if (r98 < 55296) goto L41;
        int r99 = r98 & 8191;
        int r116 = 13;
    L37:
        int r122 = r105 + 1;
        char r106 = r1.charAt(r105);
        if (r106 < 55296) goto L40;
        r99 = r99 | ((r106 & 8191) << r116);
        r116 = r116 + 13;
        r105 = r122;
        goto L37
    L40:
        r98 = r99 | (r106 << r116);
        r105 = r122;
    L41:
        int r117 = r105 + 1;
        int r107 = r1.charAt(r105);
        if (r107 < 55296) goto L48;
        int r108 = r107 & 8191;
        int r123 = 13;
    L44:
        int r132 = r117 + 1;
        char r118 = r1.charAt(r117);
        if (r118 < 55296) goto L47;
        r108 = r108 | ((r118 & 8191) << r123);
        r123 = r123 + 13;
        r117 = r132;
        goto L44
    L47:
        r107 = r108 | (r118 << r123);
        r117 = r132;
    L48:
        int r124 = r117 + 1;
        r11 = r1.charAt(r117);
        if (r11 < 55296) goto L55;
        int r119 = r11 & 8191;
        int r133 = 13;
    L51:
        int r142 = r124 + 1;
        char r125 = r1.charAt(r124);
        if (r125 < 55296) goto L54;
        r119 = r119 | ((r125 & 8191) << r133);
        r133 = r133 + 13;
        r124 = r142;
        goto L51
    L54:
        r11 = r119 | (r125 << r133);
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
        if (r1.charAt(r134) < 55296) goto L68;
    L64:
        int r136 = r144 + 1;
        if (r1.charAt(r144) < 55296) goto L67;
        r144 = r136;
        goto L64
    L67:
        r144 = r136;
    L68:
        int r137 = r144 + 1;
        int r145 = r1.charAt(r144);
        if (r145 < 55296) goto L75;
        int r146 = r145 & 8191;
        int r153 = 13;
    L71:
        int r162 = r137 + 1;
        char r138 = r1.charAt(r137);
        if (r138 < 55296) goto L74;
        r146 = r146 | ((r138 & 8191) << r153);
        r153 = r153 + 13;
        r137 = r162;
        goto L71
    L74:
        r145 = r146 | (r138 << r153);
        r137 = r162;
    L75:
        int r163 = (r47 + r47) + r714;
        int[] r716 = new int[(r145 + r126) + r47];
        r74 = r47;
        r42 = r137;
        r13 = r107;
        r102 = r163;
        r16 = r716;
        int r172 = r126;
        r12 = r98;
        r92 = r172;
        r17 = r145;
        goto L76
    L10:
        r7 = 1;
        goto L11
    L201:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r32);
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
        zzafp r72 = zzq(r8);
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
        zzafp r82 = zzq(r9);
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

    private final zzafp zzq(int r3) {
        Object[] r2 = this.zzd;
        int r32 = r3 / 3;
        int r33 = r32 + r32;
        zzafp r0 = (zzafp) r2[r33];
        if (r0 == null) goto L5;
        return r0;
    L5:
        zzafp r02 = zzafl.zza().zzb((Class) r2[r33 + 1]);
        r2[r33] = r02;
        return r02;
    }

    private final Object zzr(int r1) {
        int r12 = r1 / 3;
        return this.zzd[r12 + r12];
    }

    private final zzadz zzs(int r1) {
        int r12 = r1 / 3;
        return (zzadz) this.zzd[(r12 + r12) + 1];
    }

    private final Object zzt(Object r4, int r5) {
        zzafp r0 = zzq(r5);
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
        zzafp r0 = zzq(r6);
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

    private final Object zzx(Object r7, int r8, Object r9, zzafz r10, Object r11) {
        int r0 = this.zzc[r8];
        Object r72 = zzagg.zzm(r7, zzA(r8) & 1048575);
        if (r72 == null) goto L7;
        zzadz r1 = zzs(r8);
        if (r1 == null) goto L7;
        zzaeu r6 = ((zzaev) zzr(r8)).zze();
        Iterator r73 = ((zzaew) r72).entrySet().iterator();
    L10:
        if (r73.hasNext() == false) goto L21;
        Map.Entry r82 = (Map.Entry) r73.next();
        if (r1.zza(((Integer) r82.getValue()).intValue()) == true) goto L10;
        if (r9 != null) goto L15;
        r9 = r10.zzh(r11);
    L15:
        int r2 = zzaev.zzc(r6, r82.getKey(), r82.getValue());
        zzacr r3 = zzacr.zza;
        byte[] r32 = new byte[r2];
        int r4 = zzada.$r8$clinit;
        zzacx r42 = new zzacx(r32, 0, r2);
        zzaev.zzb(r42, r6, r82.getKey(), r82.getValue());     // Catch: IOException -> L18
        r10.zzd(r9, r0, zzaco.zza(r42, r32));
        r73.remove();
    L18:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L21:
        return r9;
    L7:
        return r9;
    }

    private static boolean zzy(Object r2, int r3, zzafp r4) {
        return r4.zzl(zzagg.zzm(r2, r3 & 1048575));
    }

    private final void zzz(Object r4, int r5, zzafo r6) {
        long r1 = r5 & 1048575;
        if (zzD(r5) == false) goto L7;
        zzagg.zzn(r4, r1, r6.zzn());
        return;
    L7:
        if (this.zzi == false) goto L10;
        zzagg.zzn(r4, r1, r6.zzm());
        return;
    L10:
        zzagg.zzn(r4, r1, r6.zzq());
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final Object zza() {
        return ((zzadu) this.zzg).zzck();
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
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
        boolean r23 = zzafq.zzC(zzagg.zzm(r7, r22), zzagg.zzm(r8, r22));
    L20:
        if (r23 == true) goto L111;
    L112:
        return false;
    L19:
        r23 = zzafq.zzC(zzagg.zzm(r7, r22), zzagg.zzm(r8, r22));
        goto L20
    L14:
        if (zzN(r7, r8, r1) == false) goto L112;
        if (zzafq.zzC(zzagg.zzm(r7, r22), zzagg.zzm(r8, r22)) == false) goto L112;
    L23:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzafq.zzC(zzagg.zzm(r7, r22), zzagg.zzm(r8, r22)) == false) goto L112;
    L28:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zze(r7, r22) != zzagg.zze(r8, r22)) goto L112;
    L33:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zzc(r7, r22) != zzagg.zzc(r8, r22)) goto L112;
    L38:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zze(r7, r22) != zzagg.zze(r8, r22)) goto L112;
    L43:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zzc(r7, r22) != zzagg.zzc(r8, r22)) goto L112;
    L48:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zzc(r7, r22) != zzagg.zzc(r8, r22)) goto L112;
    L53:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zzc(r7, r22) != zzagg.zzc(r8, r22)) goto L112;
    L58:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzafq.zzC(zzagg.zzm(r7, r22), zzagg.zzm(r8, r22)) == false) goto L112;
    L63:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzafq.zzC(zzagg.zzm(r7, r22), zzagg.zzm(r8, r22)) == false) goto L112;
    L68:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzafq.zzC(zzagg.zzm(r7, r22), zzagg.zzm(r8, r22)) == false) goto L112;
    L73:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zzg(r7, r22) != zzagg.zzg(r8, r22)) goto L112;
    L78:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zzc(r7, r22) != zzagg.zzc(r8, r22)) goto L112;
    L83:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zze(r7, r22) != zzagg.zze(r8, r22)) goto L112;
    L88:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zzc(r7, r22) != zzagg.zzc(r8, r22)) goto L112;
    L93:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zze(r7, r22) != zzagg.zze(r8, r22)) goto L112;
    L98:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (zzagg.zze(r7, r22) != zzagg.zze(r8, r22)) goto L112;
    L103:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (Float.floatToIntBits(zzagg.zzi(r7, r22)) != Float.floatToIntBits(zzagg.zzi(r8, r22))) goto L112;
    L108:
        if (zzI(r7, r8, r1) == false) goto L112;
        if (Double.doubleToLongBits(zzagg.zzk(r7, r22)) != Double.doubleToLongBits(zzagg.zzk(r8, r22))) goto L112;
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
        if (zzafq.zzC(zzagg.zzm(r7, r42), zzagg.zzm(r8, r42)) == true) goto L125;
        return false;
    L125:
        r12 = r12 + 1;
        goto L114
    L118:
        return false;
    L127:
        if (((zzadu) r7).zzc.equals(((zzadu) r8).zzc) == true) goto L130;
        return false;
    L130:
        if (this.zzh == true) goto L133;
        return true;
    L133:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r7);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
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
        int r33 = zzagg.zzm(r9, r32).hashCode();
    L13:
        r2 = r22 + r33;
        goto L39
    L14:
        r22 = r2 * 53;
        r33 = zzagg.zzm(r9, r32).hashCode();
        goto L13
    L15:
        int r23 = r2 * 53;
        Object r34 = zzagg.zzm(r9, r32);
        if (r34 == null) goto L18;
        r6 = r34.hashCode();
    L18:
        r2 = r23 + r6;
        goto L39
    L19:
        r22 = r2 * 53;
        long r35 = zzagg.zze(r9, r32);
        byte[] r52 = zzaed.zza;
    L20:
        r33 = (int) (r35 ^ (r35 >>> 32));
        goto L13
    L21:
        r22 = r2 * 53;
        r33 = zzagg.zzc(r9, r32);
        goto L13
    L22:
        r22 = r2 * 53;
        r35 = zzagg.zze(r9, r32);
        byte[] r53 = zzaed.zza;
        goto L20
    L23:
        r22 = r2 * 53;
        r33 = zzagg.zzc(r9, r32);
        goto L13
    L24:
        r22 = r2 * 53;
        r33 = zzagg.zzc(r9, r32);
        goto L13
    L25:
        r22 = r2 * 53;
        r33 = zzagg.zzc(r9, r32);
        goto L13
    L26:
        r22 = r2 * 53;
        r33 = zzagg.zzm(r9, r32).hashCode();
        goto L13
    L27:
        r23 = r2 * 53;
        Object r36 = zzagg.zzm(r9, r32);
        if (r36 == null) goto L18;
        r6 = r36.hashCode();
        goto L18
    L30:
        r22 = r2 * 53;
        r33 = ((String) zzagg.zzm(r9, r32)).hashCode();
        goto L13
    L31:
        r22 = r2 * 53;
        r33 = zzaed.zza(zzagg.zzg(r9, r32));
        goto L13
    L32:
        r22 = r2 * 53;
        r33 = zzagg.zzc(r9, r32);
        goto L13
    L33:
        r22 = r2 * 53;
        r35 = zzagg.zze(r9, r32);
        byte[] r54 = zzaed.zza;
        goto L20
    L34:
        r22 = r2 * 53;
        r33 = zzagg.zzc(r9, r32);
        goto L13
    L35:
        r22 = r2 * 53;
        r35 = zzagg.zze(r9, r32);
        byte[] r55 = zzaed.zza;
        goto L20
    L36:
        r22 = r2 * 53;
        r35 = zzagg.zze(r9, r32);
        byte[] r56 = zzaed.zza;
        goto L20
    L37:
        r22 = r2 * 53;
        r33 = Float.floatToIntBits(zzagg.zzi(r9, r32));
        goto L13
    L38:
        r22 = r2 * 53;
        r35 = Double.doubleToLongBits(zzagg.zzk(r9, r32));
        byte[] r57 = zzaed.zza;
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
        r2 = (r2 * 53) + zzagg.zzm(r9, zzA(r3) & 1048575).hashCode();
    L46:
        r12 = r12 + 1;
        goto L41
    L47:
        int r24 = (r2 * 53) + ((zzadu) r9).zzc.hashCode();
        if (this.zzh == true) goto L50;
        return r24;
    L50:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r9);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
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
        int r13 = zzafq.$r8$clinit;
        zzagg.zzn(r9, r32, zzaex.zzb(zzagg.zzm(r9, r32), zzagg.zzm(r10, r32)));
        goto L78
    L17:
        zzaef r14 = (zzaef) zzagg.zzm(r9, r32);
        zzaef r23 = (zzaef) zzagg.zzm(r10, r32);
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
        zzagg.zzn(r9, r32, r23);
        goto L78
    L28:
        zzo(r9, r10, r0);
        goto L78
    L50:
        zzo(r9, r10, r0);
        goto L78
    L10:
        if (zzM(r10, r12, r0) == false) goto L78;
        zzagg.zzn(r9, r32, zzagg.zzm(r10, r32));
        zzO(r9, r12, r0);
        goto L78
    L14:
        if (zzM(r10, r12, r0) == false) goto L78;
        zzagg.zzn(r9, r32, zzagg.zzm(r10, r32));
        zzO(r9, r12, r0);
        goto L78
    L30:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzf(r9, r32, zzagg.zze(r10, r32));
        zzL(r9, r0);
        goto L78
    L33:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzd(r9, r32, zzagg.zzc(r10, r32));
        zzL(r9, r0);
        goto L78
    L36:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzf(r9, r32, zzagg.zze(r10, r32));
        zzL(r9, r0);
        goto L78
    L39:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzd(r9, r32, zzagg.zzc(r10, r32));
        zzL(r9, r0);
        goto L78
    L42:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzd(r9, r32, zzagg.zzc(r10, r32));
        zzL(r9, r0);
        goto L78
    L45:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzd(r9, r32, zzagg.zzc(r10, r32));
        zzL(r9, r0);
        goto L78
    L48:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzn(r9, r32, zzagg.zzm(r10, r32));
        zzL(r9, r0);
        goto L78
    L52:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzn(r9, r32, zzagg.zzm(r10, r32));
        zzL(r9, r0);
        goto L78
    L55:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzh(r9, r32, zzagg.zzg(r10, r32));
        zzL(r9, r0);
        goto L78
    L58:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzd(r9, r32, zzagg.zzc(r10, r32));
        zzL(r9, r0);
        goto L78
    L61:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzf(r9, r32, zzagg.zze(r10, r32));
        zzL(r9, r0);
        goto L78
    L64:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzd(r9, r32, zzagg.zzc(r10, r32));
        zzL(r9, r0);
        goto L78
    L67:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzf(r9, r32, zzagg.zze(r10, r32));
        zzL(r9, r0);
        goto L78
    L70:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzf(r9, r32, zzagg.zze(r10, r32));
        zzL(r9, r0);
        goto L78
    L73:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzj(r9, r32, zzagg.zzi(r10, r32));
        zzL(r9, r0);
        goto L78
    L76:
        if (zzK(r10, r0) == false) goto L78;
        zzagg.zzl(r9, r32, zzagg.zzk(r10, r32));
        zzL(r9, r0);
    L78:
        r0 = r0 + 3;
        goto L3
    L79:
        zzafq.zzE(this.zzm, r9, r10);
        if (this.zzh == false) goto L127;
        zzafq.zzD(this.zzn, r9, r10);
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final int zze(Object r17) {
        zzaff<T> r0 = this;
        Object r1 = r17;
        Unsafe r6 = zzb;
        int r2 = 0;
        int r4 = 0;
        int r9 = 0;
        int r3 = 1048575;
    L3:
        int[] r5 = r0.zzc;
        if (r2 >= r5.length) goto L279;
        int r10 = r0.zzA(r2);
        int r11 = zzC(r10);
        int r12 = r5[r2];
        int r52 = r5[r2 + 2];
        int r13 = r52 & 1048575;
        if (r11 > 17) goto L13;
        if (r13 == r3) goto L12;
        if (r13 != 1048575) goto L10;
        r4 = 0;
    L11:
        r3 = r13;
        goto L12
    L10:
        r4 = r6.getInt(r1, r13);
    L12:
        int r53 = 1 << (r52 >>> 20);
    L14:
        int r102 = r10 & 1048575;
        if (r11 < zzadl.zzJ.zza()) goto L17;
        zzadl.zzW.zza();
    L17:
        long r132 = r102;
        switch(r11) {
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
            case 26: goto L174;
            case 27: goto L167;
            case 28: goto L160;
            case 29: goto L156;
            case 30: goto L152;
            case 31: goto L151;
            case 32: goto L150;
            case 33: goto L146;
            case 34: goto L141;
            case 35: goto L138;
            case 36: goto L135;
            case 37: goto L132;
            case 38: goto L129;
            case 39: goto L126;
            case 40: goto L123;
            case 41: goto L120;
            case 42: goto L117;
            case 43: goto L114;
            case 44: goto L111;
            case 45: goto L108;
            case 46: goto L105;
            case 47: goto L102;
            case 48: goto L97;
            case 49: goto L90;
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
        zzaew r54 = (zzaew) r6.getObject(r1, r132);
        zzaev r103 = (zzaev) r0.zzr(r2);
        if (r54.isEmpty() == false) goto L85;
    L84:
        int r112 = 0;
    L89:
        r9 = r9 + r112;
        goto L278
    L85:
        Iterator r55 = r54.entrySet().iterator();
        r112 = 0;
    L87:
        if (r55.hasNext() == false) goto L89;
        Map.Entry r133 = (Map.Entry) r55.next();
        r112 = r112 + r103.zzd(r12, r133.getKey(), r133.getValue());
        goto L87
    L90:
        List r56 = (List) r6.getObject(r1, r132);
        zzafp r104 = r0.zzq(r2);
        int r113 = zzafq.$r8$clinit;
        int r114 = r56.size();
        if (r114 != 0) goto L93;
        int r14 = 0;
    L96:
        r9 = r9 + r14;
        goto L278
    L93:
        int r134 = 0;
        r14 = 0;
    L94:
        if (r134 >= r114) goto L96;
        r14 = r14 + zzafq.zzA(r12, (zzafc) r56.get(r134), r104);
        r134 = r134 + 1;
        goto L94
    L97:
        int r57 = zzafq.zzq((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        int r105 = zzada.zzE(r12 << 3);
        int r115 = zzada.zzE(r57);
    L100:
        int r106 = (r105 + r115) + r57;
    L101:
        r9 = r9 + r106;
        goto L278
    L102:
        r57 = zzafq.zzu((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L105:
        r57 = zzafq.zzx((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L108:
        r57 = zzafq.zzv((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L111:
        r57 = zzafq.zzr((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L114:
        r57 = zzafq.zzt((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L117:
        List r58 = (List) r6.getObject(r1, r132);
        int r107 = zzafq.$r8$clinit;
        r57 = r58.size();
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L120:
        r57 = zzafq.zzv((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L123:
        r57 = zzafq.zzx((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L126:
        r57 = zzafq.zzs((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L129:
        r57 = zzafq.zzp((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L132:
        r57 = zzafq.zzo((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L135:
        r57 = zzafq.zzv((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L138:
        r57 = zzafq.zzx((List) r6.getObject(r1, r132));
        if (r57 <= 0) goto L278;
        r105 = zzada.zzE(r12 << 3);
        r115 = zzada.zzE(r57);
        goto L100
    L141:
        List r59 = (List) r6.getObject(r1, r132);
        int r108 = zzafq.$r8$clinit;
        int r109 = r59.size();
        if (r109 != 0) goto L144;
    L143:
        int r510 = 0;
    L23:
        r9 = r9 + r510;
        goto L278
    L144:
        int r511 = zzafq.zzq(r59);
        int r116 = zzada.zzE(r12 << 3);
    L145:
        int r1010 = r109 * r116;
    L27:
        r510 = r511 + r1010;
        goto L23
    L146:
        List r512 = (List) r6.getObject(r1, r132);
        int r1011 = zzafq.$r8$clinit;
        r109 = r512.size();
        if (r109 == 0) goto L143;
        r511 = zzafq.zzu(r512);
        r116 = zzada.zzE(r12 << 3);
        goto L145
    L150:
        r510 = zzafq.zzy(r12, (List) r6.getObject(r1, r132), false);
        goto L23
    L151:
        r510 = zzafq.zzw(r12, (List) r6.getObject(r1, r132), false);
        goto L23
    L152:
        List r513 = (List) r6.getObject(r1, r132);
        int r1012 = zzafq.$r8$clinit;
        r109 = r513.size();
        if (r109 == 0) goto L143;
        r511 = zzafq.zzr(r513);
        r116 = zzada.zzE(r12 << 3);
        goto L145
    L156:
        List r514 = (List) r6.getObject(r1, r132);
        int r1013 = zzafq.$r8$clinit;
        r109 = r514.size();
        if (r109 == 0) goto L143;
        r511 = zzafq.zzt(r514);
        r116 = zzada.zzE(r12 << 3);
        goto L145
    L160:
        List r515 = (List) r6.getObject(r1, r132);
        int r1014 = zzafq.$r8$clinit;
        int r1015 = r515.size();
        if (r1015 != 0) goto L163;
        r106 = 0;
        goto L101
    L163:
        r106 = r1015 * zzada.zzE(r12 << 3);
        int r117 = 0;
    L165:
        if (r117 >= r515.size()) goto L101;
        int r122 = ((zzacr) r515.get(r117)).zzb();
        r106 = r106 + (zzada.zzE(r122) + r122);
        r117 = r117 + 1;
        goto L165
    L167:
        List r516 = (List) r6.getObject(r1, r132);
        zzafp r1016 = r0.zzq(r2);
        int r118 = zzafq.$r8$clinit;
        int r119 = r516.size();
        if (r119 != 0) goto L170;
        int r123 = 0;
    L173:
        r9 = r9 + r123;
        goto L278
    L170:
        r123 = zzada.zzE(r12 << 3) * r119;
        int r135 = 0;
    L171:
        if (r135 >= r119) goto L173;
        int r142 = ((zzacb) r516.get(r135)).zzcf(r1016);
        r123 = r123 + (zzada.zzE(r142) + r142);
        r135 = r135 + 1;
        goto L171
    L174:
        List r517 = (List) r6.getObject(r1, r132);
        int r1017 = zzafq.$r8$clinit;
        int r1018 = r517.size();
        if (r1018 == 0) goto L84;
        r112 = zzada.zzE(r12 << 3) * r1018;
        if ((r517 instanceof zzaen) == false) goto L187;
        zzaen r518 = (zzaen) r517;
        int r124 = 0;
    L180:
        if (r124 >= r1018) goto L89;
        Object r136 = r518.zzc();
        if ((r136 instanceof zzacr) == false) goto L185;
        int r137 = ((zzacr) r136).zzb();
        int r143 = zzada.zzE(r137);
    L184:
        r112 = r112 + (r143 + r137);
        r124 = r124 + 1;
        goto L180
    L185:
        r137 = zzagl.zzb((String) r136);
        r143 = zzada.zzE(r137);
        goto L184
    L187:
        int r125 = 0;
    L188:
        if (r125 >= r1018) goto L89;
        Object r138 = r517.get(r125);
        if ((r138 instanceof zzacr) == false) goto L193;
        int r139 = ((zzacr) r138).zzb();
        int r144 = zzada.zzE(r139);
    L192:
        r112 = r112 + (r144 + r139);
        r125 = r125 + 1;
        goto L188
    L193:
        r139 = zzagl.zzb((String) r138);
        r144 = zzada.zzE(r139);
        goto L192
    L195:
        List r519 = (List) r6.getObject(r1, r132);
        int r1019 = zzafq.$r8$clinit;
        int r520 = r519.size();
        if (r520 == 0) goto L143;
        r510 = r520 * (zzada.zzE(r12 << 3) + 1);
        goto L23
    L199:
        r510 = zzafq.zzw(r12, (List) r6.getObject(r1, r132), false);
        goto L23
    L200:
        r510 = zzafq.zzy(r12, (List) r6.getObject(r1, r132), false);
        goto L23
    L201:
        List r521 = (List) r6.getObject(r1, r132);
        int r1020 = zzafq.$r8$clinit;
        r109 = r521.size();
        if (r109 == 0) goto L143;
        r511 = zzafq.zzs(r521);
        r116 = zzada.zzE(r12 << 3);
        goto L145
    L205:
        List r522 = (List) r6.getObject(r1, r132);
        int r1021 = zzafq.$r8$clinit;
        r109 = r522.size();
        if (r109 == 0) goto L143;
        r511 = zzafq.zzp(r522);
        r116 = zzada.zzE(r12 << 3);
        goto L145
    L209:
        List r523 = (List) r6.getObject(r1, r132);
        int r1022 = zzafq.$r8$clinit;
        if (r523.size() == 0) goto L84;
        r112 = zzafq.zzo(r523) + (r523.size() * zzada.zzE(r12 << 3));
        goto L89
    L213:
        r510 = zzafq.zzw(r12, (List) r6.getObject(r1, r132), false);
        goto L23
    L214:
        r510 = zzafq.zzy(r12, (List) r6.getObject(r1, r132), false);
        goto L23
    L21:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        r510 = zzafq.zzA(r12, (zzafc) r6.getObject(r1, r132), r0.zzq(r2));
        goto L23
    L25:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        long r1110 = zzH(r1, r132);
        r511 = zzada.zzE(r12 << 3);
        r1010 = zzada.zzF((r1110 >> 63) ^ (r1110 + r1110));
        goto L27
    L29:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        int r1023 = zzG(r1, r132);
        r511 = zzada.zzE(r12 << 3);
        r1010 = zzada.zzE((r1023 >> 31) ^ (r1023 + r1023));
        goto L27
    L32:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        int r524 = zzada.zzE(r12 << 3);
    L34:
        r510 = r524 + 8;
        goto L23
    L36:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        int r525 = zzada.zzE(r12 << 3);
    L38:
        r510 = r525 + 4;
        goto L23
    L40:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        long r1024 = zzG(r1, r132);
        r511 = zzada.zzE(r12 << 3);
        r1010 = zzada.zzF(r1024);
        goto L27
    L43:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        int r1025 = zzG(r1, r132);
        r511 = zzada.zzE(r12 << 3);
        r1010 = zzada.zzE(r1025);
        goto L27
    L46:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        zzacr r1026 = (zzacr) r6.getObject(r1, r132);
        int r526 = zzada.zzE(r12 << 3);
        int r1027 = r1026.zzb();
        int r1111 = zzada.zzE(r1027);
    L48:
        r510 = r526 + (r1111 + r1027);
        goto L23
    L50:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        r510 = zzafq.zzz(r12, r6.getObject(r1, r132), r0.zzq(r2));
        goto L23
    L53:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        int r527 = r12 << 3;
        Object r1028 = r6.getObject(r1, r132);
        if ((r1028 instanceof zzacr) == false) goto L57;
        r526 = zzada.zzE(r527);
        r1027 = ((zzacr) r1028).zzb();
        r1111 = zzada.zzE(r1027);
        goto L48
    L57:
        r526 = zzada.zzE(r527);
        r1027 = zzagl.zzb((String) r1028);
        r1111 = zzada.zzE(r1027);
        goto L48
    L59:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        r510 = zzada.zzE(r12 << 3) + 1;
        goto L23
    L62:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        r525 = zzada.zzE(r12 << 3);
        goto L38
    L65:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        r524 = zzada.zzE(r12 << 3);
        goto L34
    L68:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        long r1029 = zzG(r1, r132);
        r511 = zzada.zzE(r12 << 3);
        r1010 = zzada.zzF(r1029);
        goto L27
    L71:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        long r1030 = zzH(r1, r132);
        r511 = zzada.zzE(r12 << 3);
        r1010 = zzada.zzF(r1030);
        goto L27
    L74:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        long r1031 = zzH(r1, r132);
        r511 = zzada.zzE(r12 << 3);
        r1010 = zzada.zzF(r1031);
        goto L27
    L77:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        r525 = zzada.zzE(r12 << 3);
        goto L38
    L80:
        if (r0.zzM(r1, r12, r2) == false) goto L278;
        r524 = zzada.zzE(r12 << 3);
        goto L34
    L216:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L278;
        r510 = zzafq.zzA(r12, (zzafc) r6.getObject(r1, r132), r0.zzq(r2));
        goto L23
    L219:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        long r1112 = r6.getLong(r1, r132);
        int r02 = zzada.zzE(r12 << 3);
        int r528 = zzada.zzF((r1112 >> 63) ^ (r1112 + r1112));
    L221:
        int r03 = r02 + r528;
    L222:
        r9 = r9 + r03;
    L223:
        r0 = this;
        goto L278
    L225:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        int r529 = r6.getInt(r1, r132);
        r02 = zzada.zzE(r12 << 3);
        r528 = zzada.zzE((r529 >> 31) ^ (r529 + r529));
        goto L221
    L228:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        int r04 = zzada.zzE(r12 << 3);
    L230:
        r03 = r04 + 8;
        goto L222
    L232:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        int r05 = zzada.zzE(r12 << 3);
    L234:
        r03 = r05 + 4;
        goto L222
    L236:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        long r1032 = r6.getInt(r1, r132);
        r02 = zzada.zzE(r12 << 3);
        r528 = zzada.zzF(r1032);
        goto L221
    L239:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        int r530 = r6.getInt(r1, r132);
        r02 = zzada.zzE(r12 << 3);
        r528 = zzada.zzE(r530);
        goto L221
    L242:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        zzacr r531 = (zzacr) r6.getObject(r1, r132);
        int r06 = zzada.zzE(r12 << 3);
        int r532 = r531.zzb();
        int r1033 = zzada.zzE(r532);
    L244:
        r03 = r06 + (r1033 + r532);
        goto L222
    L246:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L278;
        r510 = zzafq.zzz(r12, r6.getObject(r1, r132), r0.zzq(r2));
        goto L23
    L249:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        int r07 = r12 << 3;
        Object r533 = r6.getObject(r1, r132);
        if ((r533 instanceof zzacr) == false) goto L253;
        r06 = zzada.zzE(r07);
        r532 = ((zzacr) r533).zzb();
        r1033 = zzada.zzE(r532);
        goto L244
    L253:
        r06 = zzada.zzE(r07);
        r532 = zzagl.zzb((String) r533);
        r1033 = zzada.zzE(r532);
        goto L244
    L255:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        r03 = zzada.zzE(r12 << 3) + 1;
        goto L222
    L258:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        r05 = zzada.zzE(r12 << 3);
        goto L234
    L261:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        r04 = zzada.zzE(r12 << 3);
        goto L230
    L264:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        long r1034 = r6.getInt(r1, r132);
        r02 = zzada.zzE(r12 << 3);
        r528 = zzada.zzF(r1034);
        goto L221
    L267:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        long r1035 = r6.getLong(r1, r132);
        r02 = zzada.zzE(r12 << 3);
        r528 = zzada.zzF(r1035);
        goto L221
    L270:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        long r1036 = r6.getLong(r1, r132);
        r02 = zzada.zzE(r12 << 3);
        r528 = zzada.zzF(r1036);
        goto L221
    L273:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L223;
        r05 = zzada.zzE(r12 << 3);
        goto L234
    L276:
        if (r0.zzJ(r1, r2, r3, r4, r53) == false) goto L278;
        r9 = r9 + (zzada.zzE(r12 << 3) + 8);
    L278:
        r2 = r2 + 3;
        r1 = r17;
        goto L3
    L13:
        r53 = 0;
        goto L14
    L279:
        int r92 = r9 + ((zzadu) r17).zzc.zzi();
        if (r0.zzh == true) goto L282;
        return r92;
    L282:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r17);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final void zzf(Object r17, zzago r18) {
        zzaff<T> r0 = this;
        if (r0.zzh == true) goto L188;
        int[] r7 = r0.zzc;
        Unsafe r8 = zzb;
        int r9 = 1048575;
        int r3 = 1048575;
        int r2 = 0;
        int r4 = 0;
    L6:
        if (r2 >= r7.length) goto L186;
        int r5 = r0.zzA(r2);
        int r11 = zzC(r5);
        int r12 = r7[r2];
        if (r11 > 17) goto L16;
        int r13 = r7[r2 + 2];
        int r15 = r13 & r9;
        if (r15 == r3) goto L15;
        if (r15 != r9) goto L13;
        r4 = 0;
    L14:
        r3 = r15;
        goto L15
    L13:
        r4 = r8.getInt(r17, r15);
    L15:
        int r132 = 1 << (r13 >>> 20);
    L17:
        long r92 = r5 & r9;
        switch(r11) {
            case 0: goto L183;
            case 1: goto L180;
            case 2: goto L177;
            case 3: goto L174;
            case 4: goto L171;
            case 5: goto L168;
            case 6: goto L165;
            case 7: goto L162;
            case 8: goto L159;
            case 9: goto L156;
            case 10: goto L153;
            case 11: goto L150;
            case 12: goto L147;
            case 13: goto L144;
            case 14: goto L141;
            case 15: goto L138;
            case 16: goto L135;
            case 17: goto L132;
            case 18: goto L130;
            case 19: goto L129;
            case 20: goto L128;
            case 21: goto L127;
            case 22: goto L126;
            case 23: goto L125;
            case 24: goto L124;
            case 25: goto L123;
            case 26: goto L118;
            case 27: goto L110;
            case 28: goto L105;
            case 29: goto L104;
            case 30: goto L103;
            case 31: goto L102;
            case 32: goto L101;
            case 33: goto L100;
            case 34: goto L99;
            case 35: goto L98;
            case 36: goto L97;
            case 37: goto L96;
            case 38: goto L95;
            case 39: goto L94;
            case 40: goto L93;
            case 41: goto L92;
            case 42: goto L91;
            case 43: goto L90;
            case 44: goto L89;
            case 45: goto L88;
            case 46: goto L87;
            case 47: goto L86;
            case 48: goto L85;
            case 49: goto L77;
            case 50: goto L74;
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
            default: goto L185;
        };
    L74:
        Object r52 = r8.getObject(r17, r92);
        if (r52 == null) goto L185;
        r18.zzM(r12, ((zzaev) r0.zzr(r2)).zze(), (zzaew) r52);
        goto L185
    L77:
        int r53 = r7[r2];
        List r93 = (List) r8.getObject(r17, r92);
        zzafp r10 = r0.zzq(r2);
        int r112 = zzafq.$r8$clinit;
        if (r93 == null) goto L185;
        if (r93.isEmpty() == true) goto L185;
        int r113 = 0;
    L83:
        if (r113 >= r93.size()) goto L185;
        ((zzadb) r18).zzs(r53, r93.get(r113), r10);
        r113 = r113 + 1;
        goto L83
    L85:
        zzafq.zze(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L86:
        zzafq.zzj(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L87:
        zzafq.zzg(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L88:
        zzafq.zzl(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L89:
        zzafq.zzm(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L90:
        zzafq.zzi(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L91:
        zzafq.zzn(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L92:
        zzafq.zzk(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L93:
        zzafq.zzf(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L94:
        zzafq.zzh(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L95:
        zzafq.zzd(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L96:
        zzafq.zzc(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L97:
        zzafq.zzb(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L98:
        zzafq.zza(r7[r2], (List) r8.getObject(r17, r92), r18, true);
        goto L185
    L99:
        zzafq.zze(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L100:
        zzafq.zzj(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L101:
        zzafq.zzg(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L102:
        zzafq.zzl(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L103:
        zzafq.zzm(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L104:
        zzafq.zzi(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L105:
        int r54 = r7[r2];
        List r94 = (List) r8.getObject(r17, r92);
        int r102 = zzafq.$r8$clinit;
        if (r94 == null) goto L185;
        if (r94.isEmpty() == true) goto L185;
        r18.zzG(r54, r94);
        goto L185
    L110:
        int r55 = r7[r2];
        List r95 = (List) r8.getObject(r17, r92);
        zzafp r103 = r0.zzq(r2);
        int r114 = zzafq.$r8$clinit;
        if (r95 == null) goto L185;
        if (r95.isEmpty() == true) goto L185;
        int r115 = 0;
    L116:
        if (r115 >= r95.size()) goto L185;
        ((zzadb) r18).zzr(r55, r95.get(r115), r103);
        r115 = r115 + 1;
        goto L116
    L118:
        int r56 = r7[r2];
        List r96 = (List) r8.getObject(r17, r92);
        int r104 = zzafq.$r8$clinit;
        if (r96 == null) goto L185;
        if (r96.isEmpty() == true) goto L185;
        r18.zzF(r56, r96);
        goto L185
    L123:
        zzafq.zzn(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L124:
        zzafq.zzk(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L125:
        zzafq.zzf(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L126:
        zzafq.zzh(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L127:
        zzafq.zzd(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L128:
        zzafq.zzc(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L129:
        zzafq.zzb(r7[r2], (List) r8.getObject(r17, r92), r18, false);
        goto L185
    L130:
        zzafq.zza(r7[r2], (List) r8.getObject(r17, r92), r18, false);
    L185:
        r2 = r2 + 3;
        r9 = 1048575;
        r0 = this;
        goto L6
    L21:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzs(r12, r8.getObject(r17, r92), r0.zzq(r2));
        goto L185
    L24:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzq(r12, zzH(r17, r92));
        goto L185
    L27:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzp(r12, zzG(r17, r92));
        goto L185
    L30:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzd(r12, zzH(r17, r92));
        goto L185
    L33:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzb(r12, zzG(r17, r92));
        goto L185
    L36:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzg(r12, zzG(r17, r92));
        goto L185
    L39:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzo(r12, zzG(r17, r92));
        goto L185
    L42:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzn(r12, (zzacr) r8.getObject(r17, r92));
        goto L185
    L45:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzr(r12, r8.getObject(r17, r92), r0.zzq(r2));
        goto L185
    L48:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        zzT(r12, r8.getObject(r17, r92), r18);
        goto L185
    L51:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzl(r12, ((Boolean) zzagg.zzm(r17, r92)).booleanValue());
        goto L185
    L54:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzk(r12, zzG(r17, r92));
        goto L185
    L57:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzj(r12, zzH(r17, r92));
        goto L185
    L60:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzi(r12, zzG(r17, r92));
        goto L185
    L63:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzh(r12, zzH(r17, r92));
        goto L185
    L66:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzc(r12, zzH(r17, r92));
        goto L185
    L69:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zze(r12, ((Float) zzagg.zzm(r17, r92)).floatValue());
        goto L185
    L72:
        if (r0.zzM(r17, r12, r2) == false) goto L185;
        r18.zzf(r12, ((Double) zzagg.zzm(r17, r92)).doubleValue());
        goto L185
    L132:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzs(r12, r8.getObject(r17, r92), r0.zzq(r2));
        goto L185
    L135:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzq(r12, r8.getLong(r17, r92));
        goto L185
    L138:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzp(r12, r8.getInt(r17, r92));
        goto L185
    L141:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzd(r12, r8.getLong(r17, r92));
        goto L185
    L144:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzb(r12, r8.getInt(r17, r92));
        goto L185
    L147:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzg(r12, r8.getInt(r17, r92));
        goto L185
    L150:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzo(r12, r8.getInt(r17, r92));
        goto L185
    L153:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzn(r12, (zzacr) r8.getObject(r17, r92));
        goto L185
    L156:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzr(r12, r8.getObject(r17, r92), r0.zzq(r2));
        goto L185
    L159:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        zzT(r12, r8.getObject(r17, r92), r18);
        goto L185
    L162:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzl(r12, zzagg.zzg(r17, r92));
        goto L185
    L165:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzk(r12, r8.getInt(r17, r92));
        goto L185
    L168:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzj(r12, r8.getLong(r17, r92));
        goto L185
    L171:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzi(r12, r8.getInt(r17, r92));
        goto L185
    L174:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzh(r12, r8.getLong(r17, r92));
        goto L185
    L177:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzc(r12, r8.getLong(r17, r92));
        goto L185
    L180:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zze(r12, zzagg.zzi(r17, r92));
        goto L185
    L183:
        if (r0.zzJ(r17, r2, r3, r4, r132) == false) goto L185;
        r18.zzf(r12, zzagg.zzk(r17, r92));
        goto L185
    L16:
        r132 = 0;
        goto L17
    L186:
        ((zzadu) r17).zzc.zzg(r18);
        return;
    L188:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r17);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final void zzg(Object r12, zzafo r13, zzadf r14) {
        r14.getClass();
        zzF(r12);
        zzafz r5 = this.zzm;
        Object r1 = null;
    L176:
        int r2 = r13.zzb();     // Catch: Throwable -> L88
        int r0 = zzP(r2);     // Catch: Throwable -> L88
        if (r0 < 0) goto L7;
        int r3 = zzA(r0);     // Catch: Throwable -> L88
        switch(zzC(r3)) {
            case 0: goto L149;
            case 1: goto L148;
            case 2: goto L147;
            case 3: goto L146;
            case 4: goto L145;
            case 5: goto L144;
            case 6: goto L143;
            case 7: goto L142;
            case 8: goto L141;
            case 9: goto L140;
            case 10: goto L139;
            case 11: goto L138;
            case 12: goto L131;
            case 13: goto L130;
            case 14: goto L129;
            case 15: goto L128;
            case 16: goto L127;
            case 17: goto L126;
            case 18: goto L125;
            case 19: goto L124;
            case 20: goto L123;
            case 21: goto L122;
            case 22: goto L121;
            case 23: goto L120;
            case 24: goto L119;
            case 25: goto L118;
            case 26: goto L114;
            case 27: goto L113;
            case 28: goto L112;
            case 29: goto L110;
            case 30: goto L106;
            case 31: goto L103;
            case 32: goto L102;
            case 33: goto L101;
            case 34: goto L100;
            case 35: goto L99;
            case 36: goto L98;
            case 37: goto L97;
            case 38: goto L96;
            case 39: goto L95;
            case 40: goto L94;
            case 41: goto L93;
            case 42: goto L92;
            case 43: goto L90;
            case 44: goto L79;
            case 45: goto L76;
            case 46: goto L75;
            case 47: goto L74;
            case 48: goto L73;
            case 49: goto L72;
            case 50: goto L65;
            case 51: goto L64;
            case 52: goto L63;
            case 53: goto L62;
            case 54: goto L61;
            case 55: goto L60;
            case 56: goto L59;
            case 57: goto L58;
            case 58: goto L57;
            case 59: goto L56;
            case 60: goto L55;
            case 61: goto L54;
            case 62: goto L53;
            case 63: goto L46;
            case 64: goto L45;
            case 65: goto L44;
            case 66: goto L43;
            case 67: goto L42;
            case 68: goto L40;
            default: goto L31;
        };
    L40:
        zzafc r32 = (zzafc) zzv(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        r13.zzp(r32, zzq(r0), r14);     // Catch: Throwable -> L25 zzaeg -> L39
        zzw(r12, r2, r0, r32);     // Catch: Throwable -> L25 zzaeg -> L39
    L41:
        Object r22 = r12;
    L84:
        r12 = r22;
        goto L176
    L42:
        zzagg.zzn(r12, r3 & 1048575, Long.valueOf(r13.zzw()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L43:
        zzagg.zzn(r12, r3 & 1048575, Integer.valueOf(r13.zzv()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L44:
        zzagg.zzn(r12, r3 & 1048575, Long.valueOf(r13.zzu()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L45:
        zzagg.zzn(r12, r3 & 1048575, Integer.valueOf(r13.zzt()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L46:
        int r4 = r13.zzs();     // Catch: Throwable -> L25 zzaeg -> L39
        zzadz r8 = zzs(r0);     // Catch: Throwable -> L25 zzaeg -> L39
        if (r8 == null) goto L52;
        if (r8.zza(r4) == true) goto L52;
        r1 = zzafq.zzG(r12, r2, r4, r1, r5);     // Catch: Throwable -> L25 zzaeg -> L39
    L52:
        zzagg.zzn(r12, r3 & 1048575, Integer.valueOf(r4));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L53:
        zzagg.zzn(r12, r3 & 1048575, Integer.valueOf(r13.zzr()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L54:
        zzagg.zzn(r12, r3 & 1048575, r13.zzq());     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L55:
        zzafc r33 = (zzafc) zzv(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        r13.zzo(r33, zzq(r0), r14);     // Catch: Throwable -> L25 zzaeg -> L39
        zzw(r12, r2, r0, r33);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L56:
        zzz(r12, r3, r13);     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L57:
        zzagg.zzn(r12, r3 & 1048575, Boolean.valueOf(r13.zzl()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L58:
        zzagg.zzn(r12, r3 & 1048575, Integer.valueOf(r13.zzk()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L59:
        zzagg.zzn(r12, r3 & 1048575, Long.valueOf(r13.zzj()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L60:
        zzagg.zzn(r12, r3 & 1048575, Integer.valueOf(r13.zzi()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L61:
        zzagg.zzn(r12, r3 & 1048575, Long.valueOf(r13.zzg()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L62:
        zzagg.zzn(r12, r3 & 1048575, Long.valueOf(r13.zzh()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L63:
        zzagg.zzn(r12, r3 & 1048575, Float.valueOf(r13.zzf()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L64:
        zzagg.zzn(r12, r3 & 1048575, Double.valueOf(r13.zze()));     // Catch: Throwable -> L25 zzaeg -> L39
        zzO(r12, r2, r0);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L65:
        Object r23 = zzr(r0);     // Catch: Throwable -> L25 zzaeg -> L39
        long r34 = zzA(r0) & 1048575;     // Catch: Throwable -> L25 zzaeg -> L39
        Object r02 = zzagg.zzm(r12, r34);     // Catch: Throwable -> L25 zzaeg -> L39
        if (r02 != null) goto L69;
        r02 = zzaew.zza().zzc();     // Catch: Throwable -> L25 zzaeg -> L39
        zzagg.zzn(r12, r34, r02);     // Catch: Throwable -> L25 zzaeg -> L39
    L71:
        r13.zzP((zzaew) r02, ((zzaev) r23).zze(), r14);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L69:
        if (zzaex.zza(r02) == false) goto L71;
        Object r6 = zzaew.zza().zzc();     // Catch: Throwable -> L25 zzaeg -> L39
        zzaex.zzb(r6, r02);     // Catch: Throwable -> L25 zzaeg -> L39
        zzagg.zzn(r12, r34, r6);     // Catch: Throwable -> L25 zzaeg -> L39
        r02 = r6;
        goto L71
    L72:
        r13.zzH(zzaeo.zza(r12, r3 & 1048575), zzq(r0), r14);     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L73:
        r13.zzO(zzaeo.zza(r12, r3 & 1048575));     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L74:
        r13.zzN(zzaeo.zza(r12, r3 & 1048575));     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L75:
        r13.zzM(zzaeo.zza(r12, r3 & 1048575));     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L76:
        r13.zzL(zzaeo.zza(r12, r3 & 1048575));     // Catch: Throwable -> L25 zzaeg -> L39
        goto L41
    L79:
        List r35 = zzaeo.zza(r12, r3 & 1048575);     // Catch: zzaeg -> L39 Throwable -> L88
        r13.zzK(r35);     // Catch: zzaeg -> L39 Throwable -> L88
        zzafz r62 = r5;
        Object r52 = r1;
        Object r15 = r12;
        Object r122 = zzafq.zzF(r15, r2, r35, zzs(r0), r52, r62);     // Catch: Throwable -> L85 zzaeg -> L87
        r22 = r15;
        r5 = r62;
    L83:
        r1 = r122;
        goto L84
    L87:
        r22 = r15;
        r1 = r52;
        r5 = r62;
    L151:
        if (r1 == null) goto L178;
    L155:
        if (r5.zzk(r1, r13, 0) == true) goto L84;
        int r123 = this.zzk;
        Object r42 = r1;
    L159:
        if (r123 >= this.zzl) goto L161;
        r42 = zzx(r22, this.zzj[r123], r42, r5, r22);
        r123 = r123 + 1;
    L161:
        if (r42 == null) goto L273;
        r5.zzi(r22, r42);
        return;
    L273:
        return;
    L153:
        th = th;
    L154:
        Throwable r132 = th;
    L164:
        int r124 = this.zzk;
        Object r43 = r1;
    L166:
        if (r124 >= this.zzl) goto L168;
        r43 = zzx(r22, this.zzj[r124], r43, r5, r22);
        r124 = r124 + 1;
        goto L166
    L168:
        if (r43 == null) goto L274;
        r5.zzi(r22, r43);
        throw r132;
    L274:
        throw r132;
    L178:
        r1 = r5.zzh(r22);     // Catch: Throwable -> L153
    L85:
        th = th;
        r22 = r15;
        r1 = r52;
        r5 = r62;
        goto L154
    L90:
        r22 = r12;
        r13.zzJ(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
    L92:
        r22 = r12;
        r13.zzE(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L93:
        r22 = r12;
        r13.zzD(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L94:
        r22 = r12;
        r13.zzC(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L95:
        r22 = r12;
        r13.zzB(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L96:
        r22 = r12;
        r13.zzz(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L97:
        r22 = r12;
        r13.zzA(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L98:
        r22 = r12;
        r13.zzy(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L99:
        r22 = r12;
        r13.zzx(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L100:
        r22 = r12;
        r13.zzO(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L101:
        r22 = r12;
        r13.zzN(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L102:
        r22 = r12;
        r13.zzM(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L103:
        r22 = r12;
        r13.zzL(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L106:
        List r36 = zzaeo.zza(r12, r3 & 1048575);     // Catch: zzaeg -> L39 Throwable -> L88
        r13.zzK(r36);     // Catch: zzaeg -> L39 Throwable -> L88
        zzafz r63 = r5;
        Object r53 = r1;
        Object r16 = r12;
        r122 = zzafq.zzF(r16, r2, r36, zzs(r0), r53, r63);     // Catch: Throwable -> L85 zzaeg -> L87
        r22 = r16;
        r5 = r63;
        goto L83
    L110:
        r22 = r12;
        r13.zzJ(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L112:
        r22 = r12;
        r13.zzI(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L113:
        r22 = r12;
        r13.zzG(zzaeo.zza(r22, r3 & 1048575), zzq(r0), r14);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L114:
        r22 = r12;
        if (zzD(r3) == false) goto L117;
        ((zzacw) r13).zzF(zzaeo.zza(r22, r3 & 1048575), true);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L117:
        ((zzacw) r13).zzF(zzaeo.zza(r22, r3 & 1048575), false);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L118:
        r22 = r12;
        r13.zzE(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L119:
        r22 = r12;
        r13.zzD(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L120:
        r22 = r12;
        r13.zzC(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L121:
        r22 = r12;
        r13.zzB(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L122:
        r22 = r12;
        r13.zzz(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L123:
        r22 = r12;
        r13.zzA(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L124:
        r22 = r12;
        r13.zzy(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L125:
        r22 = r12;
        r13.zzx(zzaeo.zza(r22, r3 & 1048575));     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L126:
        r22 = r12;
        zzafc r125 = (zzafc) zzt(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        r13.zzp(r125, zzq(r0), r14);     // Catch: zzaeg -> L171 Throwable -> L153
        zzu(r22, r0, r125);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L127:
        r22 = r12;
        zzagg.zzf(r22, r3 & 1048575, r13.zzw());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L128:
        r22 = r12;
        zzagg.zzd(r22, r3 & 1048575, r13.zzv());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L129:
        r22 = r12;
        zzagg.zzf(r22, r3 & 1048575, r13.zzu());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L130:
        r22 = r12;
        zzagg.zzd(r22, r3 & 1048575, r13.zzt());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L131:
        r22 = r12;
        int r44 = r13.zzs();     // Catch: zzaeg -> L171 Throwable -> L153
        zzadz r82 = zzs(r0);     // Catch: zzaeg -> L171 Throwable -> L153
        if (r82 != null) goto L134;
    L137:
        zzagg.zzd(r22, r3 & 1048575, r44);     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L134:
        if (r82.zza(r44) == true) goto L137;
        r1 = zzafq.zzG(r22, r2, r44, r1, r5);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L138:
        r22 = r12;
        zzagg.zzd(r22, r3 & 1048575, r13.zzr());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L139:
        r22 = r12;
        zzagg.zzn(r22, r3 & 1048575, r13.zzq());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L140:
        r22 = r12;
        zzafc r126 = (zzafc) zzt(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        r13.zzo(r126, zzq(r0), r14);     // Catch: zzaeg -> L171 Throwable -> L153
        zzu(r22, r0, r126);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L141:
        r22 = r12;
        zzz(r22, r3, r13);     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L142:
        r22 = r12;
        zzagg.zzh(r22, r3 & 1048575, r13.zzl());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L143:
        r22 = r12;
        zzagg.zzd(r22, r3 & 1048575, r13.zzk());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L144:
        r22 = r12;
        zzagg.zzf(r22, r3 & 1048575, r13.zzj());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L145:
        r22 = r12;
        zzagg.zzd(r22, r3 & 1048575, r13.zzi());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L146:
        r22 = r12;
        zzagg.zzf(r22, r3 & 1048575, r13.zzg());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L147:
        r22 = r12;
        zzagg.zzf(r22, r3 & 1048575, r13.zzh());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L148:
        r22 = r12;
        zzagg.zzj(r22, r3 & 1048575, r13.zzf());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L149:
        r22 = r12;
        zzagg.zzl(r22, r3 & 1048575, r13.zze());     // Catch: zzaeg -> L171 Throwable -> L153
        zzL(r22, r0);     // Catch: zzaeg -> L171 Throwable -> L153
        goto L84
    L31:
        if (r1 != null) goto L34;
        r1 = r5.zzh(r12);     // Catch: Throwable -> L25 zzaeg -> L39
    L34:
        if (r5.zzk(r1, r13, 0) == true) goto L176;
        int r133 = this.zzk;
        r42 = r1;
    L37:
        if (r133 >= this.zzl) goto L12;
        r42 = zzx(r12, this.zzj[r133], r42, r5, r12);
        r133 = r133 + 1;
    L12:
        r22 = r12;
    L39:
        r22 = r12;
        goto L151
    L7:
        if (r2 == Integer.MAX_VALUE) goto L8;
        if (this.zzh == false) goto L17;
        r14.zzc(this.zzg, r2);     // Catch: Throwable -> L25
    L17:
        if (r1 != null) goto L20;
        r1 = r5.zzh(r12);     // Catch: Throwable -> L25
    L20:
        if (r5.zzk(r1, r13, 0) == true) goto L176;
        int r134 = this.zzk;
        r42 = r1;
    L23:
        if (r134 >= this.zzl) goto L12;
        r42 = zzx(r12, this.zzj[r134], r42, r5, r12);
        r134 = r134 + 1;
    L25:
        th = move-exception;
        r132 = th;
        r22 = r12;
        goto L164
    L8:
        int r135 = this.zzk;
        r42 = r1;
    L10:
        if (r135 >= this.zzl) goto L12;
        r42 = zzx(r12, this.zzj[r135], r42, r5, r12);
        r135 = r135 + 1;
    L88:
        th = th;
        r22 = r12;
        goto L154
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    public final int zzi(java.lang.Object r35, byte[] r36, int r37, int r38, int r39, com.google.android.gms.internal.measurement.zzacg r40) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: MOVE (r3v0 ?? I:??[OBJECT, ARRAY]) = (r36v0 ?? I:??[OBJECT, ARRAY]) A[DECLARE_VAR] in method: com.google.android.gms.internal.measurement.zzaff.zzi(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzacg):int, file: classes4.dex
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
            Method dump skipped, instructions count: 3470
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzaff.zzi(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzacg):int");
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final void zzj(Object r8, byte[] r9, int r10, int r11, zzacg r12) {
        zzi(r8, r9, r10, r11, 0, r12);
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final void zzk(Object r8) {
        if (zzE(r8) == false) goto L44;
        int r1 = 0;
        if ((r8 instanceof zzadu) == false) goto L8;
        zzadu r0 = (zzadu) r8;
        r0.zzcp(Integer.MAX_VALUE);
        r0.zza = 0;
        r0.zzci();
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
        ((zzaew) r5).zzd();
        r23.putObject(r8, r32, r5);
        goto L29
    L22:
        ((zzaef) zzagg.zzm(r8, r32)).zzb();
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
        this.zzm.zzj(r8);
        if (this.zzh == false) goto L43;
        this.zzn.zza(r8);
        return;
    L43:
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final boolean zzl(Object r16) {
        int r2 = 0;
        int r8 = 0;
        int r0 = 1048575;
    L3:
        zzafp r9 = null;
        if (r8 >= this.zzk) goto L66;
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
        if (r122 == 9) goto L60;
        if (r122 == 17) goto L60;
        if (r122 != 27) goto L24;
    L50:
        List r53 = (List) zzagg.zzm(r16, r10 & 1048575);
        if (r53.isEmpty() == true) goto L64;
        zzafp r23 = zzq(r22);
        int r92 = 0;
    L54:
        if (r92 >= r53.size()) goto L64;
        if (r23.zzl(r53.get(r92)) == false) goto L57;
        r92 = r92 + 1;
        goto L54
    L57:
        return false;
    L64:
        r8 = r8 + 1;
        r0 = r32;
        r2 = r4;
        goto L3
    L24:
        if (r122 == 60) goto L46;
        if (r122 == 68) goto L46;
        if (r122 == 49) goto L50;
        if (r122 != 50) goto L64;
        zzaew r54 = (zzaew) zzagg.zzm(r16, r10 & 1048575);
        if (r54.isEmpty() == true) goto L64;
        if (((zzaev) zzr(r22)).zze().zzc.zza() != zzagn.zzi) goto L64;
        Iterator r24 = r54.values().iterator();
    L38:
        if (r24.hasNext() == false) goto L64;
        Object r55 = r24.next();
        if (r9 != null) goto L43;
        r9 = zzafl.zza().zzb(r55.getClass());
    L43:
        if (r9.zzl(r55) == true) goto L38;
        return false;
    L46:
        if (zzM(r16, r11[r22], r22) == false) goto L64;
        if (zzy(r16, r10, zzq(r22)) == true) goto L64;
        return false;
    L60:
        if (zzJ(r16, r22, r32, r4, r52) == false) goto L64;
        if (zzy(r16, r10, zzq(r22)) == true) goto L64;
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
    L66:
        if (this.zzh == true) goto L68;
        return true;
    L68:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r16);
        throw null;
    }
}
