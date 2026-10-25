package com.google.android.gms.internal.vision;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline0;
import sun.misc.Unsafe;

/* loaded from: classes5.dex */
final class zzko<T> implements zzlc<T> {
    private static final int[] zza = null;
    private static final Unsafe zzb = null;
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzkk zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zzks zzo;
    private final zzju zzp;
    private final zzlu<?, ?> zzq;
    private final zziq<?> zzr;
    private final zzkh zzs;

    static {
        zza = new int[0];
        zzb = zzma.zzc();
    }

    private zzko(int[] r1, Object[] r2, int r3, int r4, zzkk r5, boolean r6, boolean r7, int[] r8, int r9, int r10, zzks r11, zzju r12, zzlu<?, ?> r13, zziq<?> r14, zzkh r15) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzi = r5 instanceof zzjb;
        this.zzj = r6;
        if (r14 != null) goto L5;
    L7:
        boolean r22 = false;
    L8:
        this.zzh = r22;
        this.zzk = false;
        this.zzl = r8;
        this.zzm = r9;
        this.zzn = r10;
        this.zzo = r11;
        this.zzp = r12;
        this.zzq = r13;
        this.zzr = r14;
        this.zzg = r5;
        this.zzs = r15;
        return;
    L5:
        if (r14.zza(r5) == false) goto L7;
        r22 = true;
        goto L8
    }

    private final int zza(int r2, int r3) {
        if (r2 >= this.zze) goto L5;
        return -1;
    L5:
        if (r2 <= this.zzf) goto L7;
        return -1;
    L7:
        return zzb(r2, r3);
    }

    private static <UT, UB> int zza(zzlu<UT, UB> r0, T r1) {
        return r0.zzf(r0.zzb(r1));
    }

    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, zzhn r29) {
        Unsafe r11 = zzb;
        long r12 = this.zzc[r28 + 2] & 1048575;
        Object r14 = null;
        boolean r6 = true;
        switch(r25) {
            case 51: goto L81;
            case 52: goto L78;
            case 53: goto L76;
            case 54: goto L76;
            case 55: goto L73;
            case 56: goto L68;
            case 57: goto L64;
            case 58: goto L58;
            case 59: goto L43;
            case 60: goto L33;
            case 61: goto L30;
            case 62: goto L73;
            case 63: goto L20;
            case 64: goto L64;
            case 65: goto L68;
            case 66: goto L17;
            case 67: goto L14;
            case 68: goto L6;
            default: goto L86;
        };
    L64:
        int r15 = r19;
        if (r23 != 5) goto L94;
        r11.putObject(r17, r26, Integer.valueOf(zzhl.zza(r18, r19)));
    L67:
        int r0 = r15 + 4;
    L84:
        r11.putInt(r17, r12, r22);
        return r0;
    L94:
        return r15;
    L68:
        int r152 = r19;
        if (r23 != 1) goto L95;
        r11.putObject(r17, r26, Long.valueOf(zzhl.zzb(r18, r19)));
    L71:
        r0 = r152 + 8;
        goto L84
    L95:
        return r152;
    L78:
        r15 = r19;
        if (r23 != 5) goto L98;
        r11.putObject(r17, r26, Float.valueOf(zzhl.zzd(r18, r19)));
        goto L67
    L98:
        return r15;
    L81:
        r152 = r19;
        if (r23 != 1) goto L99;
        r11.putObject(r17, r26, Double.valueOf(zzhl.zzc(r18, r19)));
        goto L71
    L99:
        return r152;
    L86:
        return r19;
    L6:
        if (r23 != 3) goto L86;
        r0 = zzhl.zza(zza(r28), r18, r19, r20, (r21 & (-8)) | 4, r29);
        if (r11.getInt(r17, r12) != r22) goto L10;
        r14 = r11.getObject(r17, r26);
    L10:
        if (r14 != null) goto L12;
        r11.putObject(r17, r26, r29.zzc);
        goto L84
    L12:
        r11.putObject(r17, r26, zzjf.zza(r14, r29.zzc));
        goto L84
    L14:
        if (r23 != 0) goto L87;
        r0 = zzhl.zzb(r18, r19, r29);
        r11.putObject(r17, r26, Long.valueOf(zzif.zza(r29.zzb)));
        goto L84
    L87:
        return r19;
    L17:
        if (r23 != 0) goto L88;
        r0 = zzhl.zza(r18, r19, r29);
        r11.putObject(r17, r26, Integer.valueOf(zzif.zze(r29.zza)));
        goto L84
    L88:
        return r19;
    L20:
        if (r23 != 0) goto L89;
        int r3 = zzhl.zza(r18, r19, r29);
        int r5 = r29.zza;
        zzjg r02 = zzc(r28);
        if (r02 != null) goto L24;
    L28:
        r11.putObject(r17, r26, Integer.valueOf(r5));
        r0 = r3;
        goto L84
    L24:
        if (r02.zza(r5) == true) goto L28;
        zze(r17).zza(r21, Long.valueOf(r5));
        return r3;
    L89:
        return r19;
    L30:
        if (r23 != 2) goto L90;
        r0 = zzhl.zze(r18, r19, r29);
        r11.putObject(r17, r26, r29.zzc);
        goto L84
    L90:
        return r19;
    L33:
        if (r23 != 2) goto L91;
        int r03 = zzhl.zza(zza(r28), r18, r19, r20, r29);
        if (r11.getInt(r17, r12) != r22) goto L37;
        r14 = r11.getObject(r17, r26);
    L37:
        if (r14 != null) goto L39;
        r11.putObject(r17, r26, r29.zzc);
    L40:
        r11.putInt(r17, r12, r22);
        return r03;
    L39:
        r11.putObject(r17, r26, zzjf.zza(r14, r29.zzc));
        goto L40
    L91:
        return r19;
    L43:
        if (r23 != 2) goto L92;
        int r04 = zzhl.zza(r18, r19, r29);
        int r2 = r29.zza;
        if (r2 != 0) goto L48;
        r11.putObject(r17, r26, _UrlKt.FRAGMENT_ENCODE_SET);
    L55:
        r11.putInt(r17, r12, r22);
        return r04;
    L48:
        if ((r24 & 536870912) != 0) goto L50;
    L54:
        r11.putObject(r17, r26, new String(r18, r04, r2, zzjf.zza));
        r04 = r04 + r2;
        goto L55
    L50:
        if (zzmd.zza(r18, r04, r04 + r2) == true) goto L54;
        throw zzjk.zzh();
    L92:
        return r19;
    L58:
        if (r23 != 0) goto L93;
        r0 = zzhl.zzb(r18, r19, r29);
        if (r29.zzb != 0) goto L63;
        r6 = false;
    L63:
        r11.putObject(r17, r26, Boolean.valueOf(r6));
        goto L84
    L93:
        return r19;
    L73:
        if (r23 != 0) goto L96;
        r0 = zzhl.zza(r18, r19, r29);
        r11.putObject(r17, r26, Integer.valueOf(r29.zza));
        goto L84
    L96:
        return r19;
    L76:
        if (r23 != 0) goto L97;
        r0 = zzhl.zzb(r18, r19, r29);
        r11.putObject(r17, r26, Long.valueOf(r29.zzb));
        goto L84
    L97:
        return r19;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int zza(T r10, byte[] r11, int r12, int r13, int r14, int r15, int r16, int r17, long r18, int r20, long r21, zzhn r23) {
        Unsafe r4 = zzb;
        zzjl r5 = (zzjl) r4.getObject(r10, r21);
        if (r5.zza() == true) goto L9;
        int r7 = r5.size();
        if (r7 != 0) goto L7;
        int r72 = 10;
    L8:
        r5 = r5.zza(r72);
        r4.putObject(r10, r21, r5);
        goto L9
    L7:
        r72 = r7 << 1;
    L9:
        zzjl r42 = r5;
        zzlx r73 = null;
        switch(r20) {
            case 18: goto L226;
            case 19: goto L212;
            case 20: goto L198;
            case 21: goto L198;
            case 22: goto L191;
            case 23: goto L177;
            case 24: goto L161;
            case 25: goto L147;
            case 26: goto L97;
            case 27: goto L93;
            case 28: goto L64;
            case 29: goto L191;
            case 30: goto L51;
            case 31: goto L161;
            case 32: goto L177;
            case 33: goto L35;
            case 34: goto L21;
            case 35: goto L226;
            case 36: goto L212;
            case 37: goto L198;
            case 38: goto L198;
            case 39: goto L191;
            case 40: goto L177;
            case 41: goto L161;
            case 42: goto L147;
            case 43: goto L191;
            case 44: goto L51;
            case 45: goto L161;
            case 46: goto L177;
            case 47: goto L35;
            case 48: goto L21;
            case 49: goto L13;
            default: goto L236;
        };
    L236:
        return r12;
    L13:
        if (r16 != 3) goto L236;
        zzlc r9 = zza(r17);
        int r102 = (r14 & (-8)) | 4;
        int r92 = zzhl.zza(r9, r11, r12, r13, r102, r23);
        zzhn r52 = r23;
        r42.add(r52.zzc);
    L15:
        if (r92 >= r13) goto L19;
        int r2 = zzhl.zza(r11, r92, r52);
        if (r14 != r52.zza) goto L19;
        zzhn r202 = r52;
        r92 = zzhl.zza(r9, r11, r2, r13, r102, r202);
        r42.add(r202.zzc);
        r52 = r202;
    L19:
        return r92;
    L21:
        if (r16 != 2) goto L30;
        zzjy r43 = (zzjy) r42;
        int r93 = zzhl.zza(r11, r12, r23);
        int r103 = r23.zza + r93;
        if (r93 < r103) goto L28;
        if (r93 != r103) goto L27;
        return r93;
    L27:
        throw zzjk.zza();
    L28:
        zzhl.zzb(r11, r93, r23);
        zzif.zza(r23.zzb);
        throw null;
    L30:
        if (r16 != 0) goto L236;
        zzjy r44 = (zzjy) r42;
        zzhl.zzb(r11, r12, r23);
        zzif.zza(r23.zzb);
        throw null;
    L35:
        if (r16 != 2) goto L43;
        zzjd r45 = (zzjd) r42;
        int r94 = zzhl.zza(r11, r12, r23);
        int r104 = r23.zza + r94;
    L37:
        if (r94 >= r104) goto L39;
        r94 = zzhl.zza(r11, r94, r23);
        r45.zzc(zzif.zze(r23.zza));
        goto L37
    L39:
        if (r94 != r104) goto L42;
        return r94;
    L42:
        throw zzjk.zza();
    L43:
        if (r16 != 0) goto L236;
        zzjd r46 = (zzjd) r42;
        int r95 = zzhl.zza(r11, r12, r23);
        r46.zzc(zzif.zze(r23.zza));
    L45:
        if (r95 >= r13) goto L49;
        int r105 = zzhl.zza(r11, r95, r23);
        if (r14 != r23.zza) goto L49;
        r95 = zzhl.zza(r11, r105, r23);
        r46.zzc(zzif.zze(r23.zza));
    L49:
        return r95;
    L51:
        if (r16 != 2) goto L53;
        int r112 = zzhl.zza(r11, r12, r42, r23);
    L55:
        zzjb r106 = (zzjb) r10;
        zzlx r122 = r106.zzb;
        if (r122 == zzlx.zza()) goto L59;
        r73 = r122;
    L59:
        zzlx r96 = (zzlx) zzle.zza(r15, r42, zzc(r17), r73, this.zzq);
        if (r96 == null) goto L62;
        r106.zzb = r96;
    L62:
        return r112;
    L53:
        if (r16 != 0) goto L236;
        r112 = zzhl.zza(r14, r11, r12, r13, r42, r23);
        goto L55
    L64:
        if (r16 != 2) goto L236;
        int r97 = zzhl.zza(r11, r12, r23);
        int r123 = r23.zza;
        if (r123 < 0) goto L91;
        if (r123 > (r11.length - r97)) goto L89;
        if (r123 != 0) goto L71;
        r42.add(zzht.zza);
    L73:
        if (r97 >= r13) goto L87;
        int r124 = zzhl.zza(r11, r97, r23);
        if (r14 != r23.zza) goto L87;
        r97 = zzhl.zza(r11, r124, r23);
        int r125 = r23.zza;
        if (r125 < 0) goto L86;
        if (r125 > (r11.length - r97)) goto L84;
        if (r125 == 0) goto L81;
        r42.add(zzht.zza(r11, r97, r125));
        r97 = r97 + r125;
        goto L73
    L81:
        r42.add(zzht.zza);
        goto L73
    L84:
        throw zzjk.zza();
    L86:
        throw zzjk.zzb();
    L87:
        return r97;
    L71:
        r42.add(zzht.zza(r11, r97, r123));
        r97 = r97 + r123;
        goto L73
    L89:
        throw zzjk.zza();
    L91:
        throw zzjk.zzb();
    L93:
        if (r16 != 2) goto L236;
        return zzhl.zza(zza(r17), r14, r11, r12, r13, r42, r23);
    L97:
        if (r16 != 2) goto L236;
        if ((r18 & 536870912) != 0) goto L119;
        int r126 = zzhl.zza(r11, r12, r23);
        int r1 = r23.zza;
        if (r1 < 0) goto L118;
        if (r1 != 0) goto L104;
        r42.add(_UrlKt.FRAGMENT_ENCODE_SET);
    L106:
        if (r126 >= r13) goto L116;
        int r19 = zzhl.zza(r11, r126, r23);
        if (r14 != r23.zza) goto L116;
        r126 = zzhl.zza(r11, r19, r23);
        int r110 = r23.zza;
        if (r110 < 0) goto L115;
        if (r110 == 0) goto L112;
        r42.add(new String(r11, r126, r110, zzjf.zza));
        r126 = r126 + r110;
        goto L106
    L112:
        r42.add(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L106
    L115:
        throw zzjk.zzb();
    L116:
        return r126;
    L104:
        r42.add(new String(r11, r126, r1, zzjf.zza));
        r126 = r126 + r1;
        goto L106
    L118:
        throw zzjk.zzb();
    L119:
        int r127 = zzhl.zza(r11, r12, r23);
        int r111 = r23.zza;
        if (r111 < 0) goto L145;
        if (r111 != 0) goto L123;
        r42.add(_UrlKt.FRAGMENT_ENCODE_SET);
    L127:
        if (r127 >= r13) goto L141;
        int r113 = zzhl.zza(r11, r127, r23);
        if (r14 != r23.zza) goto L141;
        r127 = zzhl.zza(r11, r113, r23);
        int r114 = r23.zza;
        if (r114 < 0) goto L140;
        if (r114 == 0) goto L133;
        int r3 = r127 + r114;
        if (zzmd.zza(r11, r127, r3) == false) goto L138;
        r42.add(new String(r11, r127, r114, zzjf.zza));
        r127 = r3;
        goto L127
    L138:
        throw zzjk.zzh();
    L133:
        r42.add(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L127
    L140:
        throw zzjk.zzb();
    L141:
        return r127;
    L123:
        int r32 = r127 + r111;
        if (zzmd.zza(r11, r127, r32) == false) goto L143;
        r42.add(new String(r11, r127, r111, zzjf.zza));
        r127 = r32;
        goto L127
    L143:
        throw zzjk.zzh();
    L145:
        throw zzjk.zzb();
    L147:
        if (r16 != 2) goto L156;
        zzhr r47 = (zzhr) r42;
        int r128 = zzhl.zza(r11, r12, r23);
        int r132 = r23.zza + r128;
        if (r128 < r132) goto L154;
        if (r128 != r132) goto L153;
        return r128;
    L153:
        throw zzjk.zza();
    L154:
        zzhl.zzb(r11, r128, r23);
        throw null;
    L156:
        if (r16 != 0) goto L236;
        zzhr r48 = (zzhr) r42;
        zzhl.zzb(r11, r12, r23);
        long r98 = r23.zzb;
        throw null;
    L161:
        if (r16 != 2) goto L169;
        zzjd r49 = (zzjd) r42;
        int r129 = zzhl.zza(r11, r12, r23);
        int r99 = r23.zza + r129;
    L163:
        if (r129 >= r99) goto L165;
        r49.zzc(zzhl.zza(r11, r129));
        r129 = r129 + 4;
        goto L163
    L165:
        if (r129 != r99) goto L168;
        return r129;
    L168:
        throw zzjk.zza();
    L169:
        if (r16 != 5) goto L236;
        zzjd r410 = (zzjd) r42;
        r410.zzc(zzhl.zza(r11, r12));
        int r1210 = r12 + 4;
    L171:
        if (r1210 >= r13) goto L175;
        int r115 = zzhl.zza(r11, r1210, r23);
        if (r14 != r23.zza) goto L175;
        r410.zzc(zzhl.zza(r11, r115));
        r1210 = r115 + 4;
    L175:
        return r1210;
    L177:
        if (r16 != 2) goto L186;
        zzjy r411 = (zzjy) r42;
        int r1211 = zzhl.zza(r11, r12, r23);
        int r910 = r23.zza + r1211;
        if (r1211 < r910) goto L184;
        if (r1211 != r910) goto L183;
        return r1211;
    L183:
        throw zzjk.zza();
    L184:
        zzhl.zzb(r11, r1211);
        throw null;
    L186:
        if (r16 != 1) goto L236;
        zzjy r412 = (zzjy) r42;
        zzhl.zzb(r11, r12);
        throw null;
    L191:
        if (r16 == 2) goto L193;
        if (r16 != 0) goto L236;
        return zzhl.zza(r14, r11, r12, r13, r42, r23);
    L193:
        return zzhl.zza(r11, r12, r42, r23);
    L198:
        if (r16 != 2) goto L207;
        zzjy r413 = (zzjy) r42;
        int r911 = zzhl.zza(r11, r12, r23);
        int r1212 = r23.zza + r911;
        if (r911 < r1212) goto L205;
        if (r911 != r1212) goto L204;
        return r911;
    L204:
        throw zzjk.zza();
    L205:
        zzhl.zzb(r11, r911, r23);
        throw null;
    L207:
        if (r16 != 0) goto L236;
        zzjy r414 = (zzjy) r42;
        zzhl.zzb(r11, r12, r23);
        long r912 = r23.zzb;
        throw null;
    L212:
        if (r16 != 2) goto L221;
        zzja r415 = (zzja) r42;
        int r913 = zzhl.zza(r11, r12, r23);
        int r1213 = r23.zza + r913;
        if (r913 < r1213) goto L219;
        if (r913 != r1213) goto L218;
        return r913;
    L218:
        throw zzjk.zza();
    L219:
        zzhl.zzd(r11, r913);
        throw null;
    L221:
        if (r16 != 5) goto L236;
        zzja r416 = (zzja) r42;
        zzhl.zzd(r11, r12);
        throw null;
    L226:
        if (r16 != 2) goto L235;
        zzin r417 = (zzin) r42;
        int r914 = zzhl.zza(r11, r12, r23);
        int r1214 = r23.zza + r914;
        if (r914 < r1214) goto L233;
        if (r914 != r1214) goto L232;
        return r914;
    L232:
        throw zzjk.zza();
    L233:
        zzhl.zzc(r11, r914);
        throw null;
    L235:
        if (r16 != 1) goto L236;
        zzin r418 = (zzin) r42;
        zzhl.zzc(r11, r12);
        throw null;
    }

    private final <K, V> int zza(T r5, byte[] r6, int r7, int r8, int r9, long r10, zzhn r12) {
        Unsafe r0 = zzb;
        Object r92 = zzb(r9);
        Object r1 = r0.getObject(r5, r10);
        if (this.zzs.zzd(r1) == false) goto L5;
        Object r2 = this.zzs.zzf(r92);
        this.zzs.zza(r2, r1);
        r0.putObject(r5, r10, r2);
        r1 = r2;
    L5:
        this.zzs.zzb(r92);
        this.zzs.zza(r1);
        int r4 = zzhl.zza(r6, r7, r12);
        int r52 = r12.zza;
        if (r52 < 0) goto L13;
        if (r52 > (r8 - r4)) goto L13;
        throw null;
    L13:
        throw zzjk.zza();
    }

    public static <T> zzko<T> zza(Class<T> r33, zzki r34, zzks r35, zzju r36, zzlu<?, ?> r37, zziq<?> r38, zzkh r39) {
        if ((r34 instanceof zzla) == false) goto L197;
        zzla r0 = (zzla) r34;
        int r3 = 0;
        if (r0.zza() != zzkz.zzb) goto L7;
        boolean r11 = true;
    L8:
        String r1 = r0.zzd();
        int r2 = r1.length();
        if (r1.charAt(0) < 55296) goto L14;
        int r5 = 1;
    L11:
        int r7 = r5 + 1;
        if (r1.charAt(r5) < 55296) goto L15;
        r5 = r7;
    L15:
        int r52 = r7 + 1;
        int r72 = r1.charAt(r7);
        if (r72 < 55296) goto L22;
        int r73 = r72 & 8191;
        int r9 = 13;
    L18:
        int r10 = r52 + 1;
        char r53 = r1.charAt(r52);
        if (r53 < 55296) goto L21;
        r73 = r73 | ((r53 & 8191) << r9);
        r9 = r9 + 13;
        r52 = r10;
        goto L18
    L21:
        r72 = r73 | (r53 << r9);
        r52 = r10;
    L22:
        if (r72 != 0) goto L25;
        int r92 = 0;
        int r102 = 0;
        int r12 = 0;
        int r14 = 0;
        int r15 = 0;
        int[] r13 = zza;
        int r74 = 0;
    L82:
        Unsafe r4 = zzb;
        Object[] r16 = r0.zze();
        Class<?> r8 = r0.zzc().getClass();
        int[] r6 = new int[r12 * 3];
        Object[] r122 = new Object[r12 << 1];
        int r75 = r74 + r15;
        int r22 = r75;
        int r21 = r15;
        int r19 = 0;
        int r20 = 0;
    L83:
        if (r52 >= r2) goto L196;
        int r23 = r52 + 1;
        int r54 = r1.charAt(r52);
        zzla r24 = r0;
        if (r54 < 55296) goto L91;
        int r55 = r54 & 8191;
        int r02 = r23;
        int r232 = 13;
    L87:
        int r25 = r02 + 1;
        char r03 = r1.charAt(r02);
        int r26 = r2;
        if (r03 < 55296) goto L90;
        r55 = r55 | ((r03 & 8191) << r232);
        r232 = r232 + 13;
        r02 = r25;
        r2 = r26;
        goto L87
    L90:
        r54 = r55 | (r03 << r232);
        int r04 = r25;
    L92:
        int r27 = r04 + 1;
        int r05 = r1.charAt(r04);
        if (r05 < 55296) goto L99;
        int r06 = r05 & 8191;
        int r28 = r27;
        int r233 = 13;
    L95:
        int r252 = r28 + 1;
        char r29 = r1.charAt(r28);
        int r272 = r06;
        if (r29 < 55296) goto L98;
        r06 = r272 | ((r29 & 8191) << r233);
        r233 = r233 + 13;
        r28 = r252;
        goto L95
    L98:
        r05 = r272 | (r29 << r233);
        int r210 = r252;
    L100:
        int r234 = r3;
        int r32 = r05 & 255;
        int r253 = r54;
        if ((r05 & 1024) == 0) goto L103;
        r13[r19] = r20;
        r19 = r19 + 1;
    L103:
        int[] r292 = r6;
        if (r32 < 51) goto L133;
        int r56 = r210 + 1;
        int r211 = r1.charAt(r210);
        char r62 = 55296;
        if (r211 < 55296) goto L112;
        int r212 = r211 & 8191;
        int r31 = 13;
    L108:
        int r322 = r56 + 1;
        char r57 = r1.charAt(r56);
        if (r57 < r62) goto L111;
        r212 = r212 | ((r57 & 8191) << r31);
        r31 = r31 + 13;
        r56 = r322;
        r62 = 55296;
        goto L108
    L111:
        r211 = r212 | (r57 << r31);
        r56 = r322;
    L112:
        int r63 = r32 - 51;
        int r312 = r211;
        if (r63 != 9) goto L115;
    L122:
        int r64 = r14 + 1;
        r122[((r20 / 3) << 1) + 1] = r16[r14];
    L121:
        r14 = r64;
    L123:
        int r213 = r312 << 1;
        Object r65 = r16[r213];
        if ((r65 instanceof Field) == false) goto L127;
        Field r66 = (Field) r65;
    L126:
        int r214 = r56;
        int r58 = (int) r4.objectFieldOffset(r66);
        int r67 = r213 + 1;
        Object r215 = r16[r67];
        if ((r215 instanceof Field) == false) goto L131;
        Field r216 = (Field) r215;
    L132:
        String r30 = r1;
        int r17 = (int) r4.objectFieldOffset(r216);
        int r59 = r58;
        int r217 = 0;
        int r282 = r214;
    L186:
        int r68 = r20 + 1;
        r292[r20] = r253;
        int r254 = r20 + 2;
        int r273 = r17;
        if ((r05 & 512) == 0) goto L189;
        int r18 = 536870912;
    L191:
        if ((r05 & 256) == 0) goto L193;
        int r07 = 268435456;
    L194:
        r292[r68] = ((r07 | r18) | (r32 << 20)) | r59;
        r20 = r20 + 3;
        r292[r254] = (r217 << 20) | r273;
        r3 = r234;
        r0 = r24;
        r2 = r26;
        r52 = r282;
        r6 = r292;
        r1 = r30;
        goto L83
    L193:
        r07 = 0;
        goto L194
    L189:
        r18 = 0;
        goto L191
    L131:
        r216 = zza(r8, (String) r215);
        r16[r67] = r216;
        goto L132
    L127:
        r66 = zza(r8, (String) r65);
        r16[r213] = r66;
        goto L126
    L115:
        if (r63 == 17) goto L122;
        if (r63 != 12) goto L123;
        if (r11 == true) goto L123;
        r64 = r14 + 1;
        r122[((r20 / 3) << 1) + 1] = r16[r14];
        goto L121
    L133:
        int r510 = r14 + 1;
        Field r69 = zza(r8, (String) r16[r14]);
        if (r32 != 9) goto L136;
    L160:
        r122[((r20 / 3) << 1) + 1] = r69.getType();
    L161:
        r14 = r510;
    L162:
        int r511 = (int) r4.objectFieldOffset(r69);
        if ((r05 & 4096) == 4096) goto L165;
    L179:
        r30 = r1;
        r17 = 1048575;
        r282 = r210;
        r217 = 0;
    L181:
        if (r32 >= 18) goto L183;
    L185:
        r59 = r511;
        goto L186
    L183:
        if (r32 > 49) goto L185;
        r13[r22] = r511;
        r22 = r22 + 1;
        goto L185
    L165:
        if (r32 > 17) goto L179;
        int r512 = r210 + 1;
        int r218 = r1.charAt(r210);
        if (r218 < 55296) goto L173;
        int r219 = r218 & 8191;
        int r172 = 13;
    L169:
        r282 = r512 + 1;
        char r513 = r1.charAt(r512);
        if (r513 < 55296) goto L172;
        r219 = r219 | ((r513 & 8191) << r172);
        r172 = r172 + 13;
        r512 = r282;
        goto L169
    L172:
        r218 = r219 | (r513 << r172);
    L174:
        int r514 = (r234 << 1) + (r218 / 32);
        Object r610 = r16[r514];
        r30 = r1;
        if ((r610 instanceof Field) == false) goto L177;
        Field r611 = (Field) r610;
    L178:
        r17 = (int) r4.objectFieldOffset(r611);
        r217 = r218 % 32;
        goto L181
    L177:
        r611 = zza(r8, (String) r610);
        r16[r514] = r611;
        goto L178
    L173:
        r282 = r512;
        goto L174
    L136:
        if (r32 == 17) goto L160;
        if (r32 != 27) goto L141;
    L159:
        r14 = r14 + 2;
        r122[((r20 / 3) << 1) + 1] = r16[r510];
        goto L162
    L141:
        if (r32 == 49) goto L159;
        if (r32 != 12) goto L146;
    L157:
        if (r11 == true) goto L161;
        r14 = r14 + 2;
        r122[((r20 / 3) << 1) + 1] = r16[r510];
        goto L162
    L146:
        if (r32 == 30) goto L157;
        if (r32 == 44) goto L157;
        if (r32 != 50) goto L161;
        int r515 = r21 + 1;
        r13[r21] = r20;
        int r2110 = (r20 / 3) << 1;
        int r274 = r14 + 2;
        r122[r2110] = r16[r510];
        if ((r05 & 2048) == 0) goto L156;
        r122[r2110 + 1] = r16[r274];
        r14 = r14 + 3;
    L155:
        r21 = r515;
        goto L162
    L156:
        r14 = r274;
        goto L155
    L99:
        r210 = r27;
        goto L100
    L91:
        r26 = r2;
        r04 = r23;
        goto L92
    L196:
        return new zzko(r6, r122, r92, r102, r0.zzc(), r11, false, r13, r15, r75, r35, r36, r37, r38, r39);
    L25:
        int r76 = r52 + 1;
        int r516 = r1.charAt(r52);
        if (r516 < 55296) goto L32;
        int r517 = r516 & 8191;
        int r93 = 13;
    L28:
        int r103 = r76 + 1;
        char r77 = r1.charAt(r76);
        if (r77 < 55296) goto L31;
        r517 = r517 | ((r77 & 8191) << r93);
        r93 = r93 + 13;
        r76 = r103;
        goto L28
    L31:
        r516 = r517 | (r77 << r93);
        r76 = r103;
    L32:
        int r94 = r76 + 1;
        int r78 = r1.charAt(r76);
        if (r78 < 55296) goto L39;
        int r79 = r78 & 8191;
        int r104 = 13;
    L35:
        int r123 = r94 + 1;
        char r95 = r1.charAt(r94);
        if (r95 < 55296) goto L38;
        r79 = r79 | ((r95 & 8191) << r104);
        r104 = r104 + 13;
        r94 = r123;
        goto L35
    L38:
        r78 = r79 | (r95 << r104);
        r94 = r123;
    L39:
        int r105 = r94 + 1;
        r92 = r1.charAt(r94);
        if (r92 < 55296) goto L46;
        int r96 = r92 & 8191;
        int r124 = 13;
    L42:
        int r132 = r105 + 1;
        char r106 = r1.charAt(r105);
        if (r106 < 55296) goto L45;
        r96 = r96 | ((r106 & 8191) << r124);
        r124 = r124 + 13;
        r105 = r132;
        goto L42
    L45:
        r92 = r96 | (r106 << r124);
        r105 = r132;
    L46:
        int r125 = r105 + 1;
        r102 = r1.charAt(r105);
        if (r102 < 55296) goto L53;
        int r107 = r102 & 8191;
        int r133 = 13;
    L49:
        int r142 = r125 + 1;
        char r126 = r1.charAt(r125);
        if (r126 < 55296) goto L52;
        r107 = r107 | ((r126 & 8191) << r133);
        r133 = r133 + 13;
        r125 = r142;
        goto L49
    L52:
        r102 = r107 | (r126 << r133);
        r125 = r142;
    L53:
        int r134 = r125 + 1;
        r12 = r1.charAt(r125);
        if (r12 < 55296) goto L60;
        int r127 = r12 & 8191;
        int r143 = 13;
    L56:
        int r152 = r134 + 1;
        char r135 = r1.charAt(r134);
        if (r135 < 55296) goto L59;
        r127 = r127 | ((r135 & 8191) << r143);
        r143 = r143 + 13;
        r134 = r152;
        goto L56
    L59:
        r12 = r127 | (r135 << r143);
        r134 = r152;
    L60:
        int r144 = r134 + 1;
        int r136 = r1.charAt(r134);
        if (r136 < 55296) goto L67;
        int r137 = r136 & 8191;
        int r153 = 13;
    L63:
        int r162 = r144 + 1;
        char r145 = r1.charAt(r144);
        if (r145 < 55296) goto L66;
        r137 = r137 | ((r145 & 8191) << r153);
        r153 = r153 + 13;
        r144 = r162;
        goto L63
    L66:
        r136 = r137 | (r145 << r153);
        r144 = r162;
    L67:
        int r154 = r144 + 1;
        int r146 = r1.charAt(r144);
        if (r146 < 55296) goto L74;
        int r147 = r146 & 8191;
        int r163 = 13;
    L70:
        int r173 = r154 + 1;
        char r155 = r1.charAt(r154);
        if (r155 < 55296) goto L73;
        r147 = r147 | ((r155 & 8191) << r163);
        r163 = r163 + 13;
        r154 = r173;
        goto L70
    L73:
        r146 = r147 | (r155 << r163);
        r154 = r173;
    L74:
        int r164 = r154 + 1;
        r15 = r1.charAt(r154);
        if (r15 < 55296) goto L81;
        int r156 = r15 & 8191;
        int r310 = r164;
        int r165 = 13;
    L77:
        int r174 = r310 + 1;
        char r311 = r1.charAt(r310);
        if (r311 < 55296) goto L80;
        r156 = r156 | ((r311 & 8191) << r165);
        r165 = r165 + 13;
        r310 = r174;
        goto L77
    L80:
        r15 = r156 | (r311 << r165);
        r164 = r174;
    L81:
        int[] r313 = new int[(r15 + r136) + r146];
        r14 = (r516 << 1) + r78;
        r74 = r136;
        r13 = r313;
        r3 = r516;
        r52 = r164;
        goto L82
    L14:
        r7 = 1;
        goto L15
    L7:
        r11 = false;
        goto L8
    L197:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r34);
        throw null;
    }

    private final zzlc zza(int r4) {
        int r42 = (r4 / 3) << 1;
        zzlc r0 = (zzlc) this.zzd[r42];
        if (r0 == null) goto L5;
        return r0;
    L5:
        zzlc<T> r02 = zzky.zza().zza((Class) this.zzd[r42 + 1]);
        this.zzd[r42] = r02;
        return r02;
    }

    private final <K, V, UT, UB> UB zza(int r4, int r5, Map<K, V> r6, zzjg r7, UB r8, zzlu<UT, UB> r9) {
        this.zzs.zzb(zzb(r4));
        Iterator<Map.Entry<K, V>> r3 = r6.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L15;
        Map.Entry<K, V> r42 = r3.next();
        if (r7.zza(((Integer) r42.getValue()).intValue()) == true) goto L4;
        if (r8 != null) goto L9;
        r8 = r9.zza();
    L9:
        zzib r62 = zzht.zzc(zzkc.zza(null, r42.getKey(), r42.getValue()));
        zzii r0 = r62.zzb();
        zzkc.zza(r0, null, r42.getKey(), r42.getValue());     // Catch: IOException -> L12
        UB r82 = r8;
        r9.zza(r82, r5, r62.zza());
        r3.remove();
    L12:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L15:
        return r8;
    }

    private final <UT, UB> UB zza(Object r9, int r10, UB r11, zzlu<UT, UB> r12) {
        int r3 = this.zzc[r10];
        Object r92 = zzma.zzf(r9, zzd(r10) & 1048575);
        if (r92 == null) goto L7;
        zzjg r5 = zzc(r10);
        if (r5 == null) goto L7;
        return (UB) zza(r10, r3, this.zzs.zza(r92), r5, r11, r12);
    L7:
        return r11;
    }

    private static Field zza(Class<?> r5, String r6) {
        return r5.getDeclaredField(r6);
    L4:
        Field[] r0 = r5.getDeclaredFields();
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L10;
        Field r3 = r0[r2];
        if (r6.equals(r3.getName()) == true) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        return r3;
    L10:
        String r52 = r5.getName();
        String r02 = Arrays.toString(r0);
        StringBuilder r32 = new StringBuilder(((String.valueOf(r6).length() + 40) + r52.length()) + String.valueOf(r02).length());
        r32.append("Field ");
        r32.append(r6);
        r32.append(" for ");
        r32.append(r52);
        r32.append(" not found. Known fields are ");
        r32.append(r02);
        throw new RuntimeException(r32.toString());
    }

    private static List<?> zza(Object r0, long r1) {
        return (List) zzma.zzf(r0, r1);
    }

    private static void zza(int r1, Object r2, zzmr r3) {
        if ((r2 instanceof String) == false) goto L6;
        r3.zza(r1, (String) r2);
        return;
    L6:
        r3.zza(r1, (zzht) r2);
    }

    private static <UT, UB> void zza(zzlu<UT, UB> r0, T r1, zzmr r2) {
        r0.zza(r0.zzb(r1), r2);
    }

    private final <K, V> void zza(zzmr r2, int r3, Object r4, int r5) {
        if (r4 == null) goto L5;
        this.zzs.zzb(zzb(r5));
        r2.zza(r3, null, this.zzs.zzc(r4));
        return;
    }

    private final void zza(T r4, T r5, int r6) {
        long r0 = zzd(r6) & 1048575;
        if (zza(r5, r6) == false) goto L14;
        Object r2 = zzma.zzf(r4, r0);
        Object r52 = zzma.zzf(r5, r0);
        if (r2 == null) goto L10;
        if (r52 == null) goto L10;
        zzma.zza(r4, r0, zzjf.zza(r2, r52));
        zzb(r4, r6);
        return;
    L10:
        if (r52 == null) goto L13;
        zzma.zza(r4, r0, r52);
        zzb(r4, r6);
        return;
    L13:
        return;
    }

    private final boolean zza(T r8, int r9) {
        int r0 = zze(r9);
        long r2 = r0 & 1048575;
        if (r2 != 1048575) goto L89;
        int r7 = zzd(r9);
        long r02 = r7 & 1048575;
        switch(((r7 & 267386880) >>> 20)) {
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
        Object r72 = zzma.zzf(r8, r02);
        if ((r72 instanceof String) == false) goto L51;
        if (((String) r72).isEmpty() == true) goto L49;
        return true;
    L49:
        return false;
    L51:
        if ((r72 instanceof zzht) == true) goto L53;
        Segment$$ExternalSyntheticBUOutline0.m();
        return false;
    L53:
        if (zzht.zza.equals(r72) == true) goto L55;
        return true;
    L55:
        return false;
    L9:
        if (zzma.zzf(r8, r02) == null) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (zzma.zzb(r8, r02) == 0) goto L15;
        return true;
    L15:
        return false;
    L17:
        if (zzma.zza(r8, r02) == 0) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (zzma.zzb(r8, r02) == 0) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (zzma.zza(r8, r02) == 0) goto L27;
        return true;
    L27:
        return false;
    L29:
        if (zzma.zza(r8, r02) == 0) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (zzma.zza(r8, r02) == 0) goto L35;
        return true;
    L35:
        return false;
    L37:
        if (zzht.zza.equals(zzma.zzf(r8, r02)) == true) goto L39;
        return true;
    L39:
        return false;
    L41:
        if (zzma.zzf(r8, r02) == null) goto L43;
        return true;
    L43:
        return false;
    L59:
        return zzma.zzc(r8, r02);
    L61:
        if (zzma.zza(r8, r02) == 0) goto L63;
        return true;
    L63:
        return false;
    L65:
        if (zzma.zzb(r8, r02) == 0) goto L67;
        return true;
    L67:
        return false;
    L69:
        if (zzma.zza(r8, r02) == 0) goto L71;
        return true;
    L71:
        return false;
    L73:
        if (zzma.zzb(r8, r02) == 0) goto L75;
        return true;
    L75:
        return false;
    L77:
        if (zzma.zzb(r8, r02) == 0) goto L79;
        return true;
    L79:
        return false;
    L81:
        if (zzma.zzd(r8, r02) == 0.0f) goto L83;
        return true;
    L83:
        return false;
    L85:
        if (zzma.zze(r8, r02) == 0.0d) goto L87;
        return true;
    L87:
        return false;
    L89:
        if (((1 << (r0 >>> 20)) & zzma.zza(r8, r2)) == 0) goto L91;
        return true;
    L91:
        return false;
    }

    private final boolean zza(T r3, int r4, int r5) {
        if (zzma.zza(r3, zze(r5) & 1048575) != r4) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean zza(T r2, int r3, int r4, int r5, int r6) {
        if (r4 != 1048575) goto L7;
        return zza(r2, r3);
    L7:
        if ((r5 & r6) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object r2, int r3, zzlc r4) {
        return r4.zzd(zzma.zzf(r2, r3 & 1048575));
    }

    private static <T> double zzb(T r0, long r1) {
        return ((Double) zzma.zzf(r0, r1)).doubleValue();
    }

    private final int zzb(int r5, int r6) {
        int r0 = (this.zzc.length / 3) - 1;
    L3:
        if (r6 > r0) goto L10;
        int r1 = (r0 + r6) >>> 1;
        int r2 = r1 * 3;
        int r3 = this.zzc[r2];
        if (r5 == r3) goto L6;
        if (r5 < r3) goto L8;
        r6 = r1 + 1;
        goto L3
    L8:
        r0 = r1 - 1;
        goto L3
    L6:
        return r2;
    L10:
        return -1;
    }

    private final Object zzb(int r1) {
        return this.zzd[(r1 / 3) << 1];
    }

    private final void zzb(T r5, int r6) {
        int r4 = zze(r6);
        long r0 = 1048575 & r4;
        if (r0 != 1048575) goto L5;
        return;
    L5:
        zzma.zza(r5, r0, (1 << (r4 >>> 20)) | zzma.zza(r5, r0));
    }

    private final void zzb(T r3, int r4, int r5) {
        zzma.zza(r3, zze(r5) & 1048575, r4);
    }

    private final void zzb(T r18, zzmr r19) {
        if (this.zzh == false) goto L7;
        zziu<T> r3 = this.zzr.zza(r18);
        if (r3.zza.isEmpty() == true) goto L7;
        Iterator r32 = r3.zzd();
        Map.Entry<?, ?> r5 = (Map.Entry) r32.next();
    L8:
        int r6 = this.zzc.length;
        Unsafe r7 = zzb;
        int r10 = 0;
        int r11 = 1048575;
        int r12 = 0;
    L9:
        if (r10 >= r6) goto L169;
        int r13 = zzd(r10);
        int[] r14 = this.zzc;
        int r15 = r14[r10];
        int r4 = (r13 & 267386880) >>> 20;
        if (r4 > 17) goto L16;
        int r8 = r14[r10 + 2];
        int r142 = r8 & 1048575;
        if (r142 == r11) goto L15;
        r12 = r7.getInt(r18, r142);
        r11 = r142;
    L15:
        int r82 = 1 << (r8 >>> 20);
    L17:
        if (r5 == null) goto L24;
        if (this.zzr.zza(r5) > r15) goto L24;
        this.zzr.zza(r19, r5);
        if (r32.hasNext() == true) goto L22;
        r5 = null;
        goto L17
    L22:
        r5 = (Map.Entry) r32.next();
    L24:
        long r132 = r13 & 1048575;
        switch(r4) {
            case 0: goto L166;
            case 1: goto L163;
            case 2: goto L160;
            case 3: goto L157;
            case 4: goto L154;
            case 5: goto L151;
            case 6: goto L148;
            case 7: goto L145;
            case 8: goto L142;
            case 9: goto L139;
            case 10: goto L136;
            case 11: goto L133;
            case 12: goto L130;
            case 13: goto L127;
            case 14: goto L124;
            case 15: goto L121;
            case 16: goto L118;
            case 17: goto L115;
            case 18: goto L113;
            case 19: goto L112;
            case 20: goto L111;
            case 21: goto L110;
            case 22: goto L109;
            case 23: goto L108;
            case 24: goto L107;
            case 25: goto L106;
            case 26: goto L105;
            case 27: goto L104;
            case 28: goto L103;
            case 29: goto L102;
            case 30: goto L101;
            case 31: goto L100;
            case 32: goto L99;
            case 33: goto L98;
            case 34: goto L97;
            case 35: goto L96;
            case 36: goto L95;
            case 37: goto L94;
            case 38: goto L93;
            case 39: goto L92;
            case 40: goto L91;
            case 41: goto L90;
            case 42: goto L89;
            case 43: goto L88;
            case 44: goto L87;
            case 45: goto L86;
            case 46: goto L85;
            case 47: goto L84;
            case 48: goto L83;
            case 49: goto L82;
            case 50: goto L81;
            case 51: goto L79;
            case 52: goto L76;
            case 53: goto L73;
            case 54: goto L70;
            case 55: goto L67;
            case 56: goto L64;
            case 57: goto L61;
            case 58: goto L58;
            case 59: goto L55;
            case 60: goto L52;
            case 61: goto L49;
            case 62: goto L46;
            case 63: goto L43;
            case 64: goto L40;
            case 65: goto L37;
            case 66: goto L34;
            case 67: goto L31;
            case 68: goto L28;
            default: goto L168;
        };
    L81:
        zza(r19, r15, r7.getObject(r18, r132), r10);
        goto L168
    L82:
        zzle.zzb(this.zzc[r10], (List) r7.getObject(r18, r132), r19, zza(r10));
        goto L168
    L83:
        zzle.zze(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L84:
        zzle.zzj(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L85:
        zzle.zzg(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L86:
        zzle.zzl(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L87:
        zzle.zzm(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L88:
        zzle.zzi(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L89:
        zzle.zzn(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L90:
        zzle.zzk(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L91:
        zzle.zzf(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L92:
        zzle.zzh(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L93:
        zzle.zzd(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L94:
        zzle.zzc(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L95:
        zzle.zzb(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L96:
        zzle.zza(this.zzc[r10], (List) r7.getObject(r18, r132), r19, true);
        goto L168
    L97:
        zzle.zze(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L98:
        zzle.zzj(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L99:
        zzle.zzg(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L100:
        zzle.zzl(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L101:
        zzle.zzm(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L102:
        zzle.zzi(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L103:
        zzle.zzb(this.zzc[r10], (List) r7.getObject(r18, r132), r19);
        goto L168
    L104:
        zzle.zza(this.zzc[r10], (List) r7.getObject(r18, r132), r19, zza(r10));
        goto L168
    L105:
        zzle.zza(this.zzc[r10], (List) r7.getObject(r18, r132), r19);
        goto L168
    L106:
        zzle.zzn(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L107:
        zzle.zzk(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L108:
        zzle.zzf(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L109:
        zzle.zzh(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L110:
        zzle.zzd(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L111:
        zzle.zzc(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L112:
        zzle.zzb(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
        goto L168
    L113:
        zzle.zza(this.zzc[r10], (List) r7.getObject(r18, r132), r19, false);
    L168:
        r10 = r10 + 3;
        goto L9
    L28:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zzb(r15, r7.getObject(r18, r132), zza(r10));
        goto L168
    L31:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zze(r15, zze(r18, r132));
        goto L168
    L34:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zzf(r15, zzd(r18, r132));
        goto L168
    L37:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zzb(r15, zze(r18, r132));
        goto L168
    L40:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zza(r15, zzd(r18, r132));
        goto L168
    L43:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zzb(r15, zzd(r18, r132));
        goto L168
    L46:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zze(r15, zzd(r18, r132));
        goto L168
    L49:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zza(r15, (zzht) r7.getObject(r18, r132));
        goto L168
    L52:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zza(r15, r7.getObject(r18, r132), zza(r10));
        goto L168
    L55:
        if (zza(r18, r15, r10) == false) goto L168;
        zza(r15, r7.getObject(r18, r132), r19);
        goto L168
    L58:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zza(r15, zzf(r18, r132));
        goto L168
    L61:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zzd(r15, zzd(r18, r132));
        goto L168
    L64:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zzd(r15, zze(r18, r132));
        goto L168
    L67:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zzc(r15, zzd(r18, r132));
        goto L168
    L70:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zzc(r15, zze(r18, r132));
        goto L168
    L73:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zza(r15, zze(r18, r132));
        goto L168
    L76:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zza(r15, zzc(r18, r132));
        goto L168
    L79:
        if (zza(r18, r15, r10) == false) goto L168;
        r19.zza(r15, zzb(r18, r132));
        goto L168
    L115:
        if ((r12 & r82) == 0) goto L168;
        r19.zzb(r15, r7.getObject(r18, r132), zza(r10));
        goto L168
    L118:
        if ((r12 & r82) == 0) goto L168;
        r19.zze(r15, r7.getLong(r18, r132));
        goto L168
    L121:
        if ((r12 & r82) == 0) goto L168;
        r19.zzf(r15, r7.getInt(r18, r132));
        goto L168
    L124:
        if ((r12 & r82) == 0) goto L168;
        r19.zzb(r15, r7.getLong(r18, r132));
        goto L168
    L127:
        if ((r12 & r82) == 0) goto L168;
        r19.zza(r15, r7.getInt(r18, r132));
        goto L168
    L130:
        if ((r12 & r82) == 0) goto L168;
        r19.zzb(r15, r7.getInt(r18, r132));
        goto L168
    L133:
        if ((r12 & r82) == 0) goto L168;
        r19.zze(r15, r7.getInt(r18, r132));
        goto L168
    L136:
        if ((r12 & r82) == 0) goto L168;
        r19.zza(r15, (zzht) r7.getObject(r18, r132));
        goto L168
    L139:
        if ((r12 & r82) == 0) goto L168;
        r19.zza(r15, r7.getObject(r18, r132), zza(r10));
        goto L168
    L142:
        if ((r12 & r82) == 0) goto L168;
        zza(r15, r7.getObject(r18, r132), r19);
        goto L168
    L145:
        if ((r12 & r82) == 0) goto L168;
        r19.zza(r15, zzma.zzc(r18, r132));
        goto L168
    L148:
        if ((r12 & r82) == 0) goto L168;
        r19.zzd(r15, r7.getInt(r18, r132));
        goto L168
    L151:
        if ((r12 & r82) == 0) goto L168;
        r19.zzd(r15, r7.getLong(r18, r132));
        goto L168
    L154:
        if ((r12 & r82) == 0) goto L168;
        r19.zzc(r15, r7.getInt(r18, r132));
        goto L168
    L157:
        if ((r12 & r82) == 0) goto L168;
        r19.zzc(r15, r7.getLong(r18, r132));
        goto L168
    L160:
        if ((r12 & r82) == 0) goto L168;
        r19.zza(r15, r7.getLong(r18, r132));
        goto L168
    L163:
        if ((r12 & r82) == 0) goto L168;
        r19.zza(r15, zzma.zzd(r18, r132));
        goto L168
    L166:
        if ((r12 & r82) == 0) goto L168;
        r19.zza(r15, zzma.zze(r18, r132));
        goto L168
    L16:
        r82 = 0;
    L169:
        if (r5 == null) goto L174;
        this.zzr.zza(r19, r5);
        if (r32.hasNext() == true) goto L172;
        r5 = null;
        goto L169
    L172:
        r5 = (Map.Entry) r32.next();
        goto L169
    L174:
        zza(this.zzq, r18, r19);
        return;
    L7:
        r32 = null;
        r5 = null;
        goto L8
    }

    private final void zzb(T r5, T r6, int r7) {
        int r0 = zzd(r7);
        int r1 = this.zzc[r7];
        long r2 = r0 & 1048575;
        if (zza(r6, r1, r7) == true) goto L6;
        return;
    L6:
        if (zza(r5, r1, r7) == false) goto L8;
        Object r02 = zzma.zzf(r5, r2);
    L9:
        Object r62 = zzma.zzf(r6, r2);
        if (r02 == null) goto L14;
        if (r62 == null) goto L14;
        zzma.zza(r5, r2, zzjf.zza(r02, r62));
        zzb(r5, r1, r7);
        return;
    L14:
        if (r62 == null) goto L17;
        zzma.zza(r5, r2, r62);
        zzb(r5, r1, r7);
        return;
    L17:
        return;
    L8:
        r02 = null;
        goto L9
    }

    private static <T> float zzc(T r0, long r1) {
        return ((Float) zzma.zzf(r0, r1)).floatValue();
    }

    private final zzjg zzc(int r1) {
        return (zzjg) this.zzd[((r1 / 3) << 1) + 1];
    }

    private final boolean zzc(T r1, T r2, int r3) {
        if (zza(r1, r3) != zza(r2, r3)) goto L6;
        return true;
    L6:
        return false;
    }

    private final int zzd(int r1) {
        return this.zzc[r1 + 1];
    }

    private static <T> int zzd(T r0, long r1) {
        return ((Integer) zzma.zzf(r0, r1)).intValue();
    }

    private final int zze(int r1) {
        return this.zzc[r1 + 2];
    }

    private static <T> long zze(T r0, long r1) {
        return ((Long) zzma.zzf(r0, r1)).longValue();
    }

    private static zzlx zze(Object r2) {
        zzjb r22 = (zzjb) r2;
        zzlx r0 = r22.zzb;
        if (r0 != zzlx.zza()) goto L6;
        zzlx r02 = zzlx.zzb();
        r22.zzb = r02;
        return r02;
    L6:
        return r0;
    }

    private static <T> boolean zzf(T r0, long r1) {
        return ((Boolean) zzma.zzf(r0, r1)).booleanValue();
    }

    private final int zzg(int r2) {
        if (r2 >= this.zze) goto L5;
        return -1;
    L5:
        if (r2 <= this.zzf) goto L7;
        return -1;
    L7:
        return zzb(r2, 0);
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final int zza(T r9) {
        int r0 = this.zzc.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L88;
        int r3 = zzd(r1);
        int r4 = this.zzc[r1];
        long r5 = 1048575 & r3;
        int r7 = 37;
        switch(((r3 & 267386880) >>> 20)) {
            case 0: goto L86;
            case 1: goto L85;
            case 2: goto L84;
            case 3: goto L83;
            case 4: goto L82;
            case 5: goto L81;
            case 6: goto L80;
            case 7: goto L79;
            case 8: goto L78;
            case 9: goto L75;
            case 10: goto L74;
            case 11: goto L73;
            case 12: goto L72;
            case 13: goto L71;
            case 14: goto L70;
            case 15: goto L69;
            case 16: goto L68;
            case 17: goto L64;
            case 18: goto L63;
            case 19: goto L63;
            case 20: goto L63;
            case 21: goto L63;
            case 22: goto L63;
            case 23: goto L63;
            case 24: goto L63;
            case 25: goto L63;
            case 26: goto L63;
            case 27: goto L63;
            case 28: goto L63;
            case 29: goto L63;
            case 30: goto L63;
            case 31: goto L63;
            case 32: goto L63;
            case 33: goto L63;
            case 34: goto L63;
            case 35: goto L63;
            case 36: goto L63;
            case 37: goto L63;
            case 38: goto L63;
            case 39: goto L63;
            case 40: goto L63;
            case 41: goto L63;
            case 42: goto L63;
            case 43: goto L63;
            case 44: goto L63;
            case 45: goto L63;
            case 46: goto L63;
            case 47: goto L63;
            case 48: goto L63;
            case 49: goto L63;
            case 50: goto L62;
            case 51: goto L60;
            case 52: goto L57;
            case 53: goto L54;
            case 54: goto L51;
            case 55: goto L48;
            case 56: goto L45;
            case 57: goto L42;
            case 58: goto L39;
            case 59: goto L36;
            case 60: goto L33;
            case 61: goto L30;
            case 62: goto L27;
            case 63: goto L24;
            case 64: goto L21;
            case 65: goto L18;
            case 66: goto L15;
            case 67: goto L12;
            case 68: goto L8;
            default: goto L87;
        };
    L62:
        int r22 = r2 * 53;
        int r32 = zzma.zzf(r9, r5).hashCode();
    L10:
        r2 = r22 + r32;
        goto L87
    L63:
        r22 = r2 * 53;
        r32 = zzma.zzf(r9, r5).hashCode();
        goto L10
    L64:
        Object r33 = zzma.zzf(r9, r5);
        if (r33 == null) goto L67;
        r7 = r33.hashCode();
    L67:
        r2 = (r2 * 53) + r7;
        goto L87
    L68:
        r22 = r2 * 53;
        r32 = zzjf.zza(zzma.zzb(r9, r5));
        goto L10
    L69:
        r22 = r2 * 53;
        r32 = zzma.zza(r9, r5);
        goto L10
    L70:
        r22 = r2 * 53;
        r32 = zzjf.zza(zzma.zzb(r9, r5));
        goto L10
    L71:
        r22 = r2 * 53;
        r32 = zzma.zza(r9, r5);
        goto L10
    L72:
        r22 = r2 * 53;
        r32 = zzma.zza(r9, r5);
        goto L10
    L73:
        r22 = r2 * 53;
        r32 = zzma.zza(r9, r5);
        goto L10
    L74:
        r22 = r2 * 53;
        r32 = zzma.zzf(r9, r5).hashCode();
        goto L10
    L75:
        Object r34 = zzma.zzf(r9, r5);
        if (r34 == null) goto L67;
        r7 = r34.hashCode();
        goto L67
    L78:
        r22 = r2 * 53;
        r32 = ((String) zzma.zzf(r9, r5)).hashCode();
        goto L10
    L79:
        r22 = r2 * 53;
        r32 = zzjf.zza(zzma.zzc(r9, r5));
        goto L10
    L80:
        r22 = r2 * 53;
        r32 = zzma.zza(r9, r5);
        goto L10
    L81:
        r22 = r2 * 53;
        r32 = zzjf.zza(zzma.zzb(r9, r5));
        goto L10
    L82:
        r22 = r2 * 53;
        r32 = zzma.zza(r9, r5);
        goto L10
    L83:
        r22 = r2 * 53;
        r32 = zzjf.zza(zzma.zzb(r9, r5));
        goto L10
    L84:
        r22 = r2 * 53;
        r32 = zzjf.zza(zzma.zzb(r9, r5));
        goto L10
    L85:
        r22 = r2 * 53;
        r32 = Float.floatToIntBits(zzma.zzd(r9, r5));
        goto L10
    L86:
        r22 = r2 * 53;
        r32 = zzjf.zza(Double.doubleToLongBits(zzma.zze(r9, r5)));
        goto L10
    L8:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzma.zzf(r9, r5).hashCode();
        goto L10
    L12:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzjf.zza(zze(r9, r5));
        goto L10
    L15:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzd(r9, r5);
        goto L10
    L18:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzjf.zza(zze(r9, r5));
        goto L10
    L21:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzd(r9, r5);
        goto L10
    L24:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzd(r9, r5);
        goto L10
    L27:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzd(r9, r5);
        goto L10
    L30:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzma.zzf(r9, r5).hashCode();
        goto L10
    L33:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzma.zzf(r9, r5).hashCode();
        goto L10
    L36:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = ((String) zzma.zzf(r9, r5)).hashCode();
        goto L10
    L39:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzjf.zza(zzf(r9, r5));
        goto L10
    L42:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzd(r9, r5);
        goto L10
    L45:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzjf.zza(zze(r9, r5));
        goto L10
    L48:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzd(r9, r5);
        goto L10
    L51:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzjf.zza(zze(r9, r5));
        goto L10
    L54:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzjf.zza(zze(r9, r5));
        goto L10
    L57:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = Float.floatToIntBits(zzc(r9, r5));
        goto L10
    L60:
        if (zza(r9, r4, r1) == false) goto L87;
        r22 = r2 * 53;
        r32 = zzjf.zza(Double.doubleToLongBits(zzb(r9, r5)));
    L87:
        r1 = r1 + 3;
        goto L3
    L88:
        int r23 = (r2 * 53) + this.zzq.zzb(r9).hashCode();
        if (this.zzh == true) goto L91;
        return r23;
    L91:
        return (r23 * 53) + this.zzr.zza(r9).hashCode();
    }

    public final int zza(T r27, byte[] r28, int r29, int r30, int r31, zzhn r32) {
        zzko<T> r0 = this;
        T r1 = r27;
        byte[] r2 = r28;
        int r4 = r30;
        zzhn r5 = r32;
        Unsafe r8 = zzb;
        int r10 = -1;
        int r3 = r29;
        int r6 = -1;
        int r7 = 0;
        int r12 = 0;
        int r13 = 0;
        int r11 = 1048575;
    L3:
        if (r3 >= r4) goto L150;
        int r132 = r3 + 1;
        int r33 = r2[r3];
        if (r33 >= 0) goto L7;
        r132 = zzhl.zza(r33, r2, r132, r5);
        r33 = r5.zza;
    L7:
        int r24 = r132;
        r13 = r33;
        int r14 = r13 >>> 3;
        int r17 = r7;
        int r72 = r13 & 7;
        if (r14 <= r6) goto L10;
        int r62 = r0.zza(r14, r17 / 3);
    L11:
        if (r62 == r10) goto L12;
        int[] r102 = r0.zzc;
        int r9 = r102[r62 + 1];
        int r22 = (r9 & 267386880) >>> 20;
        long r142 = r9 & 1048575;
        if (r22 <= 17) goto L16;
        int r103 = r62;
        Unsafe r42 = r8;
        zzhn r143 = r5;
        if (r22 == 27) goto L107;
        int r34 = r24;
        if (r22 > 49) goto L126;
        int r82 = r103;
        int r172 = r11;
        int r18 = r12;
        Unsafe r19 = r42;
        int r15 = 1048575;
        int r20 = -1;
        int r73 = r0.zza(r27, r28, r34, r30, r13, r14, r72, r82, r9, r22, r142, r32);
        r13 = r13;
        int r104 = r14;
        if (r73 == r34) goto L121;
        r0 = this;
        r1 = r27;
        r2 = r28;
        r4 = r30;
        r5 = r32;
        r3 = r73;
    L124:
        r7 = r82;
        r6 = r104;
    L125:
        r11 = r172;
        r12 = r18;
        r8 = r19;
        r10 = r20;
        goto L3
    L121:
        zzko<T> r92 = this;
        int r144 = r31;
        r5 = r32;
        int r23 = r73;
    L122:
        r7 = r82;
        r6 = r104;
        r11 = r172;
        r12 = r18;
        T r105 = r27;
    L138:
        if (r13 != r144) goto L143;
        if (r144 == 0) goto L143;
        r4 = r30;
        r3 = r23;
    L151:
        if (r11 == r15) goto L153;
        r19.putInt(r105, r11, r12);
    L153:
        int r02 = r92.zzm;
        zzlx r16 = null;
    L155:
        if (r02 >= r92.zzn) goto L157;
        r16 = (zzlx) r92.zza(r105, r92.zzl[r02], r16, r92.zzq);
        r02 = r02 + 1;
        goto L155
    L157:
        if (r16 == null) goto L159;
        r92.zzq.zzb(r105, r16);
    L159:
        if (r144 != 0) goto L164;
        if (r3 != r4) goto L163;
    L166:
        return r3;
    L163:
        throw zzjk.zzg();
    L164:
        if (r3 > r4) goto L168;
        if (r13 == r144) goto L166;
    L168:
        throw zzjk.zzg();
    L143:
        if (r92.zzh == true) goto L145;
    L148:
        r5 = r32;
        int r25 = zzhl.zza(r13, r28, r23, r30, zze(r105), r5);
        r4 = r30;
        r0 = r92;
        r1 = r105;
        r8 = r19;
        r10 = r20;
        r3 = r25;
    L147:
        r2 = r28;
        goto L3
    L145:
        if (r5.zzd == zzio.zzb()) goto L148;
        r5.zzd.zza(r92.zzg, r6);
        int r26 = zzhl.zza(r13, r28, r23, r30, zze(r105), r5);
        r4 = r30;
        r5 = r32;
        r3 = r26;
        r0 = r92;
        r1 = r105;
        r8 = r19;
        r10 = r20;
        goto L147
    L126:
        r82 = r103;
        r172 = r11;
        r18 = r12;
        r19 = r42;
        r104 = r14;
        r15 = 1048575;
        r20 = -1;
        if (r22 == 50) goto L129;
        r6 = r104;
        int r74 = zza(r27, r28, r34, r30, r13, r6, r72, r9, r22, r142, r82, r32);
        r13 = r13;
        r5 = r32;
        r92 = this;
        r105 = r27;
        r144 = r31;
        if (r74 == r34) goto L137;
        r2 = r28;
        r4 = r30;
        r5 = r32;
        r3 = r74;
        r7 = r82;
        r0 = r92;
        r1 = r105;
        goto L125
    L137:
        r23 = r74;
        r7 = r82;
        r11 = r172;
        r12 = r18;
        goto L138
    L129:
        if (r72 != 2) goto L134;
        int r63 = zza(r27, r28, r34, r30, r82, r142, r32);
        r82 = r82;
        if (r63 == r34) goto L132;
        r0 = this;
        r1 = r27;
        r2 = r28;
        r4 = r30;
        r5 = r32;
        r3 = r63;
        goto L124
    L132:
        r92 = this;
        r144 = r31;
        r5 = r32;
        r23 = r63;
    L134:
        r92 = this;
        r144 = r31;
        r5 = r32;
        r23 = r34;
        goto L122
    L107:
        if (r72 != 2) goto L116;
        zzjl r210 = (zzjl) r42.getObject(r1, r142);
        if (r210.zza() == true) goto L115;
        int r35 = r210.size();
        if (r35 != 0) goto L113;
        int r36 = 10;
    L114:
        r210 = r210.zza(r36);
        r42.putObject(r1, r142, r210);
        goto L115
    L113:
        r36 = r35 << 1;
    L115:
        int r110 = zzhl.zza(r0.zza(r103), r13, r28, r24, r30, r210, r143);
        r2 = r28;
        r4 = r30;
        r5 = r32;
        r3 = r110;
        r7 = r103;
        r8 = r42;
        r6 = r14;
        r10 = -1;
        r1 = r27;
        goto L3
    L116:
        r82 = r103;
        r172 = r11;
        r18 = r12;
        r34 = r24;
        r104 = r14;
        r15 = 1048575;
        r20 = -1;
        r19 = r42;
        goto L134
    L16:
        int r37 = r102[r62 + 2];
        int r106 = 1 << (r37 >>> 20);
        int r38 = r37 & 1048575;
        if (r38 == r11) goto L23;
        if (r11 == 1048575) goto L21;
        r8.putInt(r1, r11, r12);
    L21:
        r11 = r38;
        r12 = r8.getInt(r1, r38);
    L23:
        switch(r22) {
            case 0: goto L100;
            case 1: goto L96;
            case 2: goto L92;
            case 3: goto L92;
            case 4: goto L89;
            case 5: goto L86;
            case 6: goto L82;
            case 7: goto L72;
            case 8: goto L64;
            case 9: goto L58;
            case 10: goto L55;
            case 11: goto L89;
            case 12: goto L46;
            case 13: goto L82;
            case 14: goto L86;
            case 15: goto L41;
            case 16: goto L34;
            case 17: goto L27;
            default: goto L24;
        };
    L24:
        zzhn r145 = r5;
        int r107 = r62;
    L25:
        Unsafe r43 = r8;
        int r93 = r24;
    L103:
        r19 = r43;
        r23 = r93;
        r7 = r107;
        r5 = r145;
        r6 = r14;
        r15 = 1048575;
        r20 = -1;
        r144 = r31;
        r92 = r0;
    L13:
        r105 = r1;
        goto L138
    L34:
        byte[] r94 = r28;
        zzhn r39 = r5;
        r107 = r62;
        int r211 = r24;
        if (r72 != 0) goto L39;
        int r75 = zzhl.zzb(r94, r211, r39);
        r145 = r39;
        T r212 = r1;
        Unsafe r111 = r8;
        int r83 = r30;
        r111.putLong(r212, r142, zzif.zza(r39.zzb));
        Unsafe r52 = r111;
        r1 = r212;
        r12 = r12 | r106;
        r3 = r75;
    L37:
        r4 = r83;
        r2 = r94;
        r7 = r107;
    L38:
        r6 = r14;
        r10 = -1;
        r8 = r52;
        r5 = r145;
        goto L3
    L39:
        r145 = r39;
        r52 = r8;
    L40:
        r43 = r52;
        r93 = r211;
        goto L103
    L41:
        r94 = r28;
        r107 = r62;
        r211 = r24;
        r145 = r5;
        r52 = r8;
        r83 = r30;
        if (r72 != 0) goto L40;
        int r213 = zzhl.zza(r94, r211, r145);
        r52.putInt(r1, r142, zzif.zze(r145.zza));
    L44:
        r12 = r12 | r106;
    L45:
        r3 = r213;
        goto L37
    L46:
        r94 = r28;
        r107 = r62;
        r211 = r24;
        r145 = r5;
        r52 = r8;
        r83 = r30;
        if (r72 != 0) goto L40;
        r213 = zzhl.zza(r94, r211, r145);
        int r64 = r145.zza;
        zzjg r76 = r0.zzc(r107);
        if (r76 != null) goto L51;
    L54:
        r52.putInt(r1, r142, r64);
        goto L44
    L51:
        if (r76.zza(r64) == true) goto L54;
        zze(r1).zza(r13, Long.valueOf(r64));
        goto L45
    L55:
        r94 = r28;
        r107 = r62;
        r211 = r24;
        r145 = r5;
        r52 = r8;
        r83 = r30;
        if (r72 != 2) goto L40;
        r213 = zzhl.zze(r94, r211, r145);
        r52.putObject(r1, r142, r145.zzc);
        goto L44
    L58:
        r94 = r28;
        r107 = r62;
        r211 = r24;
        r145 = r5;
        r52 = r8;
        r83 = r30;
        if (r72 != 2) goto L40;
        r213 = zzhl.zza(r0.zza(r107), r94, r211, r83, r145);
        if ((r12 & r106) != 0) goto L63;
        r52.putObject(r1, r142, r145.zzc);
        goto L44
    L63:
        r52.putObject(r1, r142, zzjf.zza(r52.getObject(r1, r142), r145.zzc));
        goto L44
    L64:
        r107 = r62;
        r145 = r5;
        r52 = r8;
        if (r72 != 2) goto L71;
        if ((536870912 & r9) != 0) goto L69;
        int r214 = zzhl.zzc(r28, r24, r145);
    L70:
        r52.putObject(r1, r142, r145.zzc);
        r12 = r12 | r106;
        r3 = r214;
        r4 = r30;
        r7 = r107;
        r2 = r28;
        goto L38
    L69:
        r214 = zzhl.zzd(r28, r24, r145);
        goto L70
    L71:
        r93 = r24;
        r43 = r52;
        goto L103
    L72:
        r145 = r5;
        byte[] r152 = r28;
        r107 = r62;
        Unsafe r65 = r8;
        int r215 = r24;
        int r84 = r30;
        if (r72 != 0) goto L80;
        int r310 = zzhl.zzb(r152, r215, r145);
        if (r145.zzb == 0) goto L77;
        boolean r216 = true;
    L78:
        zzma.zza(r1, r142, r216);
        r12 = r12 | r106;
        r3 = r310;
    L79:
        r4 = r84;
        r7 = r107;
        r5 = r145;
        r2 = r152;
        r10 = -1;
        r8 = r65;
        r6 = r14;
        goto L3
    L77:
        r216 = false;
    L80:
        r93 = r215;
    L81:
        r43 = r65;
        goto L103
    L82:
        r145 = r5;
        r152 = r28;
        r107 = r62;
        r65 = r8;
        r215 = r24;
        r84 = r30;
        if (r72 != 5) goto L80;
        r65.putInt(r1, r142, zzhl.zza(r152, r215));
        r3 = r215 + 4;
    L85:
        r12 = r12 | r106;
        goto L79
    L86:
        r107 = r62;
        r65 = r8;
        r215 = r24;
        r84 = r30;
        r152 = r28;
        r145 = r5;
        if (r72 != 1) goto L80;
        r65.putLong(r27, r142, zzhl.zzb(r152, r215));
        r65 = r65;
        r1 = r27;
        r3 = r215 + 8;
        goto L85
    L89:
        r107 = r62;
        r65 = r8;
        r93 = r24;
        r152 = r28;
        r84 = r30;
        r145 = r5;
        if (r72 != 0) goto L81;
        int r217 = zzhl.zza(r152, r93, r145);
        r65.putInt(r1, r142, r145.zza);
        r12 = r12 | r106;
        r3 = r217;
        goto L79
    L92:
        r107 = r62;
        r65 = r8;
        r93 = r24;
        byte[] r153 = r28;
        r145 = r5;
        if (r72 != 0) goto L81;
        int r77 = zzhl.zzb(r153, r93, r145);
        r65.putLong(r27, r142, r145.zzb);
        r1 = r27;
        r12 = r12 | r106;
        r8 = r65;
        r4 = r30;
        r3 = r77;
    L95:
        r7 = r107;
        r5 = r145;
        r2 = r153;
        r6 = r14;
        r10 = -1;
        goto L3
    L96:
        r107 = r62;
        r43 = r8;
        r93 = r24;
        int r85 = r30;
        r153 = r28;
        r145 = r5;
        if (r72 != 5) goto L103;
        zzma.zza(r1, r142, zzhl.zzd(r153, r93));
        r3 = r93 + 4;
    L99:
        r12 = r12 | r106;
        int r218 = r85;
        r8 = r43;
        r4 = r218;
        goto L95
    L100:
        r107 = r62;
        r43 = r8;
        r93 = r24;
        r85 = r30;
        r153 = r28;
        r145 = r5;
        if (r72 != 1) goto L103;
        zzma.zza(r1, r142, zzhl.zzc(r153, r93));
        r3 = r93 + 8;
        goto L99
    L27:
        if (r72 != 3) goto L33;
        zzhn r78 = r5;
        int r108 = r62;
        int r219 = zzhl.zza(r0.zza(r62), r28, r24, r30, (r14 << 3) | 4, r78);
        if ((r12 & r106) != 0) goto L31;
        r8.putObject(r1, r142, r78.zzc);
    L32:
        r12 = r12 | r106;
        r4 = r30;
        r5 = r78;
        r7 = r108;
        r6 = r14;
        r10 = -1;
        r3 = r219;
        r2 = r28;
        goto L3
    L31:
        r8.putObject(r1, r142, zzjf.zza(r8.getObject(r1, r142), r78.zzc));
        goto L32
    L33:
        r107 = r62;
        r145 = r5;
        goto L25
    L12:
        r92 = r0;
        r23 = r24;
        r19 = r8;
        r20 = r10;
        r6 = r14;
        r7 = 0;
        r15 = 1048575;
        r144 = r31;
        goto L13
    L10:
        r62 = r0.zzg(r14);
        goto L11
    L150:
        r144 = r31;
        r92 = r0;
        r105 = r1;
        r19 = r8;
        r15 = 1048575;
        goto L151
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final T zza() {
        return (T) this.zzo.zza(this.zzg);
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zza(T r14, zzmr r15) {
        if (r15.zza() != zzmq.zzb) goto L172;
        zza(this.zzq, r14, r15);
        if (this.zzh == false) goto L9;
        zziu<T> r0 = this.zzr.zza(r14);
        if (r0.zza.isEmpty() == true) goto L9;
        Iterator r02 = r0.zze();
        Map.Entry<?, ?> r1 = (Map.Entry) r02.next();
    L10:
        int r7 = this.zzc.length - 3;
    L11:
        if (r7 < 0) goto L165;
        int r8 = zzd(r7);
        int r9 = this.zzc[r7];
    L13:
        if (r1 == null) goto L21;
        if (this.zzr.zza(r1) <= r9) goto L21;
        this.zzr.zza(r15, r1);
        if (r02.hasNext() == true) goto L18;
        r1 = null;
        goto L13
    L18:
        r1 = (Map.Entry) r02.next();
    L21:
        switch(((r8 & 267386880) >>> 20)) {
            case 0: goto L162;
            case 1: goto L159;
            case 2: goto L156;
            case 3: goto L153;
            case 4: goto L150;
            case 5: goto L147;
            case 6: goto L144;
            case 7: goto L141;
            case 8: goto L138;
            case 9: goto L135;
            case 10: goto L132;
            case 11: goto L129;
            case 12: goto L126;
            case 13: goto L123;
            case 14: goto L120;
            case 15: goto L117;
            case 16: goto L114;
            case 17: goto L111;
            case 18: goto L109;
            case 19: goto L108;
            case 20: goto L107;
            case 21: goto L106;
            case 22: goto L105;
            case 23: goto L104;
            case 24: goto L103;
            case 25: goto L102;
            case 26: goto L101;
            case 27: goto L100;
            case 28: goto L99;
            case 29: goto L98;
            case 30: goto L97;
            case 31: goto L96;
            case 32: goto L95;
            case 33: goto L94;
            case 34: goto L93;
            case 35: goto L92;
            case 36: goto L91;
            case 37: goto L90;
            case 38: goto L89;
            case 39: goto L88;
            case 40: goto L87;
            case 41: goto L86;
            case 42: goto L85;
            case 43: goto L84;
            case 44: goto L83;
            case 45: goto L82;
            case 46: goto L81;
            case 47: goto L80;
            case 48: goto L79;
            case 49: goto L78;
            case 50: goto L77;
            case 51: goto L75;
            case 52: goto L72;
            case 53: goto L69;
            case 54: goto L66;
            case 55: goto L63;
            case 56: goto L60;
            case 57: goto L57;
            case 58: goto L54;
            case 59: goto L51;
            case 60: goto L48;
            case 61: goto L45;
            case 62: goto L42;
            case 63: goto L39;
            case 64: goto L36;
            case 65: goto L33;
            case 66: goto L30;
            case 67: goto L27;
            case 68: goto L24;
            default: goto L164;
        };
    L77:
        zza(r15, r9, zzma.zzf(r14, r8 & 1048575), r7);
        goto L164
    L78:
        zzle.zzb(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, zza(r7));
        goto L164
    L79:
        zzle.zze(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L80:
        zzle.zzj(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L81:
        zzle.zzg(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L82:
        zzle.zzl(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L83:
        zzle.zzm(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L84:
        zzle.zzi(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L85:
        zzle.zzn(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L86:
        zzle.zzk(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L87:
        zzle.zzf(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L88:
        zzle.zzh(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L89:
        zzle.zzd(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L90:
        zzle.zzc(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L91:
        zzle.zzb(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L92:
        zzle.zza(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, true);
        goto L164
    L93:
        zzle.zze(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L94:
        zzle.zzj(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L95:
        zzle.zzg(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L96:
        zzle.zzl(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L97:
        zzle.zzm(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L98:
        zzle.zzi(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L99:
        zzle.zzb(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15);
        goto L164
    L100:
        zzle.zza(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, zza(r7));
        goto L164
    L101:
        zzle.zza(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15);
        goto L164
    L102:
        zzle.zzn(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L103:
        zzle.zzk(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L104:
        zzle.zzf(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L105:
        zzle.zzh(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L106:
        zzle.zzd(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L107:
        zzle.zzc(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L108:
        zzle.zzb(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L109:
        zzle.zza(this.zzc[r7], (List) zzma.zzf(r14, r8 & 1048575), r15, false);
        goto L164
    L24:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zzb(r9, zzma.zzf(r14, r8 & 1048575), zza(r7));
        goto L164
    L27:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zze(r9, zze(r14, r8 & 1048575));
        goto L164
    L30:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zzf(r9, zzd(r14, r8 & 1048575));
        goto L164
    L33:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zzb(r9, zze(r14, r8 & 1048575));
        goto L164
    L36:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zza(r9, zzd(r14, r8 & 1048575));
        goto L164
    L39:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zzb(r9, zzd(r14, r8 & 1048575));
        goto L164
    L42:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zze(r9, zzd(r14, r8 & 1048575));
        goto L164
    L45:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zza(r9, (zzht) zzma.zzf(r14, r8 & 1048575));
        goto L164
    L48:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zza(r9, zzma.zzf(r14, r8 & 1048575), zza(r7));
        goto L164
    L51:
        if (zza(r14, r9, r7) == false) goto L164;
        zza(r9, zzma.zzf(r14, r8 & 1048575), r15);
        goto L164
    L54:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zza(r9, zzf(r14, r8 & 1048575));
        goto L164
    L57:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zzd(r9, zzd(r14, r8 & 1048575));
        goto L164
    L60:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zzd(r9, zze(r14, r8 & 1048575));
        goto L164
    L63:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zzc(r9, zzd(r14, r8 & 1048575));
        goto L164
    L66:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zzc(r9, zze(r14, r8 & 1048575));
        goto L164
    L69:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zza(r9, zze(r14, r8 & 1048575));
        goto L164
    L72:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zza(r9, zzc(r14, r8 & 1048575));
        goto L164
    L75:
        if (zza(r14, r9, r7) == false) goto L164;
        r15.zza(r9, zzb(r14, r8 & 1048575));
        goto L164
    L111:
        if (zza(r14, r7) == false) goto L164;
        r15.zzb(r9, zzma.zzf(r14, r8 & 1048575), zza(r7));
        goto L164
    L114:
        if (zza(r14, r7) == false) goto L164;
        r15.zze(r9, zzma.zzb(r14, r8 & 1048575));
        goto L164
    L117:
        if (zza(r14, r7) == false) goto L164;
        r15.zzf(r9, zzma.zza(r14, r8 & 1048575));
        goto L164
    L120:
        if (zza(r14, r7) == false) goto L164;
        r15.zzb(r9, zzma.zzb(r14, r8 & 1048575));
        goto L164
    L123:
        if (zza(r14, r7) == false) goto L164;
        r15.zza(r9, zzma.zza(r14, r8 & 1048575));
        goto L164
    L126:
        if (zza(r14, r7) == false) goto L164;
        r15.zzb(r9, zzma.zza(r14, r8 & 1048575));
        goto L164
    L129:
        if (zza(r14, r7) == false) goto L164;
        r15.zze(r9, zzma.zza(r14, r8 & 1048575));
        goto L164
    L132:
        if (zza(r14, r7) == false) goto L164;
        r15.zza(r9, (zzht) zzma.zzf(r14, r8 & 1048575));
        goto L164
    L135:
        if (zza(r14, r7) == false) goto L164;
        r15.zza(r9, zzma.zzf(r14, r8 & 1048575), zza(r7));
        goto L164
    L138:
        if (zza(r14, r7) == false) goto L164;
        zza(r9, zzma.zzf(r14, r8 & 1048575), r15);
        goto L164
    L141:
        if (zza(r14, r7) == false) goto L164;
        r15.zza(r9, zzma.zzc(r14, r8 & 1048575));
        goto L164
    L144:
        if (zza(r14, r7) == false) goto L164;
        r15.zzd(r9, zzma.zza(r14, r8 & 1048575));
        goto L164
    L147:
        if (zza(r14, r7) == false) goto L164;
        r15.zzd(r9, zzma.zzb(r14, r8 & 1048575));
        goto L164
    L150:
        if (zza(r14, r7) == false) goto L164;
        r15.zzc(r9, zzma.zza(r14, r8 & 1048575));
        goto L164
    L153:
        if (zza(r14, r7) == false) goto L164;
        r15.zzc(r9, zzma.zzb(r14, r8 & 1048575));
        goto L164
    L156:
        if (zza(r14, r7) == false) goto L164;
        r15.zza(r9, zzma.zzb(r14, r8 & 1048575));
        goto L164
    L159:
        if (zza(r14, r7) == false) goto L164;
        r15.zza(r9, zzma.zzd(r14, r8 & 1048575));
        goto L164
    L162:
        if (zza(r14, r7) == false) goto L164;
        r15.zza(r9, zzma.zze(r14, r8 & 1048575));
    L164:
        r7 = r7 - 3;
    L165:
        if (r1 == null) goto L170;
        this.zzr.zza(r15, r1);
        if (r02.hasNext() == true) goto L168;
        r1 = null;
        goto L165
    L168:
        r1 = (Map.Entry) r02.next();
        goto L165
    L170:
        return;
    L9:
        r02 = null;
        r1 = null;
        goto L10
    L172:
        if (this.zzj == true) goto L174;
        zzb(r14, r15);
        return;
    L174:
        if (this.zzh == false) goto L178;
        zziu<T> r03 = this.zzr.zza(r14);
        if (r03.zza.isEmpty() == true) goto L178;
        Iterator r04 = r03.zzd();
        Map.Entry<?, ?> r12 = (Map.Entry) r04.next();
    L179:
        int r72 = this.zzc.length;
        int r82 = 0;
    L180:
        if (r82 >= r72) goto L334;
        int r92 = zzd(r82);
        int r10 = this.zzc[r82];
    L182:
        if (r12 == null) goto L190;
        if (this.zzr.zza(r12) > r10) goto L190;
        this.zzr.zza(r15, r12);
        if (r04.hasNext() == true) goto L187;
        r12 = null;
        goto L182
    L187:
        r12 = (Map.Entry) r04.next();
    L190:
        switch(((r92 & 267386880) >>> 20)) {
            case 0: goto L331;
            case 1: goto L328;
            case 2: goto L325;
            case 3: goto L322;
            case 4: goto L319;
            case 5: goto L316;
            case 6: goto L313;
            case 7: goto L310;
            case 8: goto L307;
            case 9: goto L304;
            case 10: goto L301;
            case 11: goto L298;
            case 12: goto L295;
            case 13: goto L292;
            case 14: goto L289;
            case 15: goto L286;
            case 16: goto L283;
            case 17: goto L280;
            case 18: goto L278;
            case 19: goto L277;
            case 20: goto L276;
            case 21: goto L275;
            case 22: goto L274;
            case 23: goto L273;
            case 24: goto L272;
            case 25: goto L271;
            case 26: goto L270;
            case 27: goto L269;
            case 28: goto L268;
            case 29: goto L267;
            case 30: goto L266;
            case 31: goto L265;
            case 32: goto L264;
            case 33: goto L263;
            case 34: goto L262;
            case 35: goto L261;
            case 36: goto L260;
            case 37: goto L259;
            case 38: goto L258;
            case 39: goto L257;
            case 40: goto L256;
            case 41: goto L255;
            case 42: goto L254;
            case 43: goto L253;
            case 44: goto L252;
            case 45: goto L251;
            case 46: goto L250;
            case 47: goto L249;
            case 48: goto L248;
            case 49: goto L247;
            case 50: goto L246;
            case 51: goto L244;
            case 52: goto L241;
            case 53: goto L238;
            case 54: goto L235;
            case 55: goto L232;
            case 56: goto L229;
            case 57: goto L226;
            case 58: goto L223;
            case 59: goto L220;
            case 60: goto L217;
            case 61: goto L214;
            case 62: goto L211;
            case 63: goto L208;
            case 64: goto L205;
            case 65: goto L202;
            case 66: goto L199;
            case 67: goto L196;
            case 68: goto L193;
            default: goto L333;
        };
    L246:
        zza(r15, r10, zzma.zzf(r14, r92 & 1048575), r82);
        goto L333
    L247:
        zzle.zzb(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, zza(r82));
        goto L333
    L248:
        zzle.zze(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L249:
        zzle.zzj(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L250:
        zzle.zzg(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L251:
        zzle.zzl(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L252:
        zzle.zzm(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L253:
        zzle.zzi(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L254:
        zzle.zzn(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L255:
        zzle.zzk(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L256:
        zzle.zzf(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L257:
        zzle.zzh(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L258:
        zzle.zzd(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L259:
        zzle.zzc(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L260:
        zzle.zzb(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L261:
        zzle.zza(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, true);
        goto L333
    L262:
        zzle.zze(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L263:
        zzle.zzj(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L264:
        zzle.zzg(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L265:
        zzle.zzl(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L266:
        zzle.zzm(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L267:
        zzle.zzi(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L268:
        zzle.zzb(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15);
        goto L333
    L269:
        zzle.zza(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, zza(r82));
        goto L333
    L270:
        zzle.zza(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15);
        goto L333
    L271:
        zzle.zzn(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L272:
        zzle.zzk(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L273:
        zzle.zzf(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L274:
        zzle.zzh(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L275:
        zzle.zzd(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L276:
        zzle.zzc(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L277:
        zzle.zzb(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L278:
        zzle.zza(this.zzc[r82], (List) zzma.zzf(r14, r92 & 1048575), r15, false);
        goto L333
    L193:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zzb(r10, zzma.zzf(r14, r92 & 1048575), zza(r82));
        goto L333
    L196:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zze(r10, zze(r14, r92 & 1048575));
        goto L333
    L199:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zzf(r10, zzd(r14, r92 & 1048575));
        goto L333
    L202:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zzb(r10, zze(r14, r92 & 1048575));
        goto L333
    L205:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zza(r10, zzd(r14, r92 & 1048575));
        goto L333
    L208:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zzb(r10, zzd(r14, r92 & 1048575));
        goto L333
    L211:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zze(r10, zzd(r14, r92 & 1048575));
        goto L333
    L214:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zza(r10, (zzht) zzma.zzf(r14, r92 & 1048575));
        goto L333
    L217:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zza(r10, zzma.zzf(r14, r92 & 1048575), zza(r82));
        goto L333
    L220:
        if (zza(r14, r10, r82) == false) goto L333;
        zza(r10, zzma.zzf(r14, r92 & 1048575), r15);
        goto L333
    L223:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zza(r10, zzf(r14, r92 & 1048575));
        goto L333
    L226:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zzd(r10, zzd(r14, r92 & 1048575));
        goto L333
    L229:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zzd(r10, zze(r14, r92 & 1048575));
        goto L333
    L232:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zzc(r10, zzd(r14, r92 & 1048575));
        goto L333
    L235:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zzc(r10, zze(r14, r92 & 1048575));
        goto L333
    L238:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zza(r10, zze(r14, r92 & 1048575));
        goto L333
    L241:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zza(r10, zzc(r14, r92 & 1048575));
        goto L333
    L244:
        if (zza(r14, r10, r82) == false) goto L333;
        r15.zza(r10, zzb(r14, r92 & 1048575));
        goto L333
    L280:
        if (zza(r14, r82) == false) goto L333;
        r15.zzb(r10, zzma.zzf(r14, r92 & 1048575), zza(r82));
        goto L333
    L283:
        if (zza(r14, r82) == false) goto L333;
        r15.zze(r10, zzma.zzb(r14, r92 & 1048575));
        goto L333
    L286:
        if (zza(r14, r82) == false) goto L333;
        r15.zzf(r10, zzma.zza(r14, r92 & 1048575));
        goto L333
    L289:
        if (zza(r14, r82) == false) goto L333;
        r15.zzb(r10, zzma.zzb(r14, r92 & 1048575));
        goto L333
    L292:
        if (zza(r14, r82) == false) goto L333;
        r15.zza(r10, zzma.zza(r14, r92 & 1048575));
        goto L333
    L295:
        if (zza(r14, r82) == false) goto L333;
        r15.zzb(r10, zzma.zza(r14, r92 & 1048575));
        goto L333
    L298:
        if (zza(r14, r82) == false) goto L333;
        r15.zze(r10, zzma.zza(r14, r92 & 1048575));
        goto L333
    L301:
        if (zza(r14, r82) == false) goto L333;
        r15.zza(r10, (zzht) zzma.zzf(r14, r92 & 1048575));
        goto L333
    L304:
        if (zza(r14, r82) == false) goto L333;
        r15.zza(r10, zzma.zzf(r14, r92 & 1048575), zza(r82));
        goto L333
    L307:
        if (zza(r14, r82) == false) goto L333;
        zza(r10, zzma.zzf(r14, r92 & 1048575), r15);
        goto L333
    L310:
        if (zza(r14, r82) == false) goto L333;
        r15.zza(r10, zzma.zzc(r14, r92 & 1048575));
        goto L333
    L313:
        if (zza(r14, r82) == false) goto L333;
        r15.zzd(r10, zzma.zza(r14, r92 & 1048575));
        goto L333
    L316:
        if (zza(r14, r82) == false) goto L333;
        r15.zzd(r10, zzma.zzb(r14, r92 & 1048575));
        goto L333
    L319:
        if (zza(r14, r82) == false) goto L333;
        r15.zzc(r10, zzma.zza(r14, r92 & 1048575));
        goto L333
    L322:
        if (zza(r14, r82) == false) goto L333;
        r15.zzc(r10, zzma.zzb(r14, r92 & 1048575));
        goto L333
    L325:
        if (zza(r14, r82) == false) goto L333;
        r15.zza(r10, zzma.zzb(r14, r92 & 1048575));
        goto L333
    L328:
        if (zza(r14, r82) == false) goto L333;
        r15.zza(r10, zzma.zzd(r14, r92 & 1048575));
        goto L333
    L331:
        if (zza(r14, r82) == false) goto L333;
        r15.zza(r10, zzma.zze(r14, r92 & 1048575));
    L333:
        r82 = r82 + 3;
    L334:
        if (r12 == null) goto L339;
        this.zzr.zza(r15, r12);
        if (r04.hasNext() == true) goto L337;
        r12 = null;
        goto L334
    L337:
        r12 = (Map.Entry) r04.next();
        goto L334
    L339:
        zza(this.zzq, r14, r15);
        return;
    L178:
        r04 = null;
        r12 = null;
        goto L179
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zza(T r27, byte[] r28, int r29, int r30, zzhn r31) {
        zzko<T> r0 = this;
        T r1 = r27;
        byte[] r7 = r28;
        int r8 = r30;
        zzhn r13 = r31;
        if (r0.zzj == false) goto L130;
        Unsafe r2 = zzb;
        int r15 = -1;
        int r3 = r29;
        int r4 = -1;
        int r5 = 0;
        int r11 = 0;
        int r10 = 1048575;
    L5:
        if (r3 >= r8) goto L123;
        int r6 = r3 + 1;
        int r32 = r7[r3];
        if (r32 >= 0) goto L9;
        r6 = zzhl.zza(r32, r7, r6, r13);
        r32 = r13.zza;
    L9:
        int r12 = r6;
        int r14 = r32 >>> 3;
        int r62 = r32 & 7;
        if (r14 <= r4) goto L12;
        int r42 = r0.zza(r14, r5 / 3);
    L13:
        if (r42 == r15) goto L14;
        int[] r52 = r0.zzc;
        int r152 = r52[r42 + 1];
        int r9 = (r152 & 267386880) >>> 20;
        int r292 = r32;
        int r17 = r42;
        long r33 = r152 & 1048575;
        if (r9 > 17) goto L87;
        int r34 = r52[r17 + 2];
        int r21 = 1 << (r34 >>> 20);
        int r35 = r34 & 1048575;
        int r53 = 1048575;
        if (r35 == r10) goto L25;
        if (r10 == 1048575) goto L21;
        r2.putInt(r1, r10, r11);
        r53 = 1048575;
    L21:
        if (r35 == r53) goto L23;
        r11 = r2.getInt(r1, r35);
    L23:
        r10 = r35;
    L25:
        switch(r9) {
            case 0: goto L83;
            case 1: goto L80;
            case 2: goto L75;
            case 3: goto L75;
            case 4: goto L72;
            case 5: goto L68;
            case 6: goto L64;
            case 7: goto L56;
            case 8: goto L48;
            case 9: goto L42;
            case 10: goto L39;
            case 11: goto L72;
            case 12: goto L36;
            case 13: goto L64;
            case 14: goto L68;
            case 15: goto L31;
            case 16: goto L27;
            default: goto L26;
        };
    L26:
        int r22 = r53;
    L86:
        T r92 = r1;
        Unsafe r23 = r2;
        int r63 = r14;
        int r82 = r17;
        int r24 = r292;
    L121:
        int r02 = zzhl.zza(r24, r28, r12, r30, zze(r92), r31);
        r7 = r28;
        r13 = r31;
        r4 = r63;
        r5 = r82;
        r1 = r92;
        r2 = r23;
        r15 = -1;
        r8 = r30;
        r3 = r02;
        r0 = this;
        goto L5
    L75:
        r22 = r53;
        if (r62 != 0) goto L86;
        int r83 = zzhl.zzb(r7, r12, r13);
        Unsafe r242 = r2;
        T r25 = r1;
        r242.putLong(r25, r33, r13.zzb);
        r2 = r242;
        r1 = r25;
        r11 = r11 | r21;
        r3 = r83;
        r4 = r14;
        r5 = r17;
    L78:
        r15 = -1;
    L79:
        r8 = r30;
        goto L5
    L27:
        if (r62 != 0) goto L26;
        int r93 = zzhl.zzb(r7, r12, r13);
        Unsafe r64 = r2;
        T r26 = r1;
        int r153 = r17;
        r64.putLong(r26, r33, zzif.zza(r13.zzb));
        r2 = r64;
        r1 = r26;
        r11 = r11 | r21;
        r3 = r93;
    L29:
        r4 = r14;
        r5 = r153;
    L30:
        r15 = -1;
        goto L5
    L31:
        r22 = r53;
        r153 = r17;
        if (r62 != 0) goto L35;
        r3 = zzhl.zza(r7, r12, r13);
        r2.putInt(r1, r33, zzif.zze(r13.zza));
    L34:
        r11 = r11 | r21;
    L35:
        r17 = r153;
        goto L86
    L36:
        r22 = r53;
        r153 = r17;
        if (r62 != 0) goto L35;
        r3 = zzhl.zza(r7, r12, r13);
        r2.putInt(r1, r33, r13.zza);
        goto L34
    L39:
        r22 = r53;
        r153 = r17;
        if (r62 != 2) goto L35;
        r3 = zzhl.zze(r7, r12, r13);
        r2.putObject(r1, r33, r13.zzc);
        goto L34
    L42:
        r22 = r53;
        r153 = r17;
        if (r62 != 2) goto L35;
        r3 = zzhl.zza(r0.zza(r153), r7, r12, r8, r13);
        Object r65 = r2.getObject(r1, r33);
        if (r65 != null) goto L47;
        r2.putObject(r1, r33, r13.zzc);
        goto L34
    L47:
        r2.putObject(r1, r33, zzjf.zza(r65, r13.zzc));
        goto L34
    L48:
        r22 = r53;
        if (r62 != 2) goto L86;
        if ((536870912 & r152) != 0) goto L53;
        r3 = zzhl.zzc(r7, r12, r13);
    L54:
        r2.putObject(r1, r33, r13.zzc);
        r11 = r11 | r21;
    L55:
        r4 = r14;
        r5 = r17;
        goto L30
    L53:
        r3 = zzhl.zzd(r7, r12, r13);
        goto L54
    L56:
        r22 = r53;
        if (r62 != 0) goto L86;
        r3 = zzhl.zzb(r7, r12, r13);
        if (r13.zzb == 0) goto L61;
        boolean r66 = true;
    L62:
        zzma.zza(r1, r33, r66);
    L63:
        r11 = r11 | r21;
        r8 = r30;
        goto L55
    L61:
        r66 = false;
        goto L62
    L64:
        r22 = r53;
        if (r62 != 5) goto L86;
        r2.putInt(r1, r33, zzhl.zza(r7, r12));
    L67:
        r3 = r12 + 4;
        goto L63
    L68:
        r22 = r53;
        if (r62 != 1) goto L86;
        Unsafe r243 = r2;
        T r210 = r1;
        r243.putLong(r210, r33, zzhl.zzb(r7, r12));
        r2 = r243;
        r1 = r210;
    L71:
        r3 = r12 + 8;
        goto L63
    L72:
        r22 = r53;
        if (r62 != 0) goto L86;
        int r54 = zzhl.zza(r7, r12, r13);
        r2.putInt(r1, r33, r13.zza);
        r11 = r11 | r21;
        r8 = r30;
        r3 = r54;
        goto L55
    L80:
        r22 = r53;
        if (r62 != 5) goto L86;
        zzma.zza(r1, r33, zzhl.zzd(r7, r12));
        goto L67
    L83:
        r22 = r53;
        if (r62 != 1) goto L86;
        zzma.zza(r1, r33, zzhl.zzc(r7, r12));
        goto L71
    L87:
        r82 = r17;
        if (r9 == 27) goto L90;
        int r36 = r12;
        Unsafe r122 = r2;
        if (r9 > 49) goto L107;
        int r16 = r10;
        int r172 = r11;
        r23 = r122;
        int r72 = r0.zza(r27, r28, r36, r30, r292, r14, r62, r82, r152, r9, r33, r31);
        if (r72 == r36) goto L104;
        r0 = this;
        r1 = r27;
        r10 = r16;
        r13 = r31;
        r3 = r72;
        r5 = r82;
        r4 = r14;
    L106:
        r11 = r172;
        r2 = r23;
        r15 = -1;
        r7 = r28;
        goto L79
    L104:
        r12 = r72;
        r63 = r14;
        r24 = r292;
        r11 = r172;
        r92 = r27;
        r10 = r16;
        goto L121
    L107:
        r172 = r11;
        r23 = r122;
        int r94 = r14;
        int r142 = r10;
        int r102 = r292;
        if (r9 == 50) goto L110;
        r63 = r94;
        int r73 = zza(r27, r28, r36, r30, r102, r63, r62, r152, r9, r33, r82, r31);
        r92 = r27;
        r24 = r102;
        r82 = r82;
        if (r73 == r36) goto L120;
        r0 = this;
        r13 = r31;
        r4 = r63;
        r3 = r73;
        r5 = r82;
        r1 = r92;
        r10 = r142;
        goto L106
    L120:
        r12 = r73;
        r10 = r142;
        r11 = r172;
        goto L121
    L110:
        if (r62 != 2) goto L116;
        int r67 = zza(r27, r28, r36, r30, r82, r33, r31);
        r82 = r82;
        if (r67 == r36) goto L113;
        r0 = this;
        r1 = r27;
        r7 = r28;
        r13 = r31;
        r3 = r67;
        r5 = r82;
        r4 = r94;
        r10 = r142;
        r11 = r172;
        r2 = r23;
        goto L78
    L113:
        r12 = r67;
    L114:
        r63 = r94;
        r24 = r102;
        r10 = r142;
        r11 = r172;
        r92 = r27;
        goto L121
    L116:
        r82 = r82;
    L117:
        r12 = r36;
        goto L114
    L90:
        if (r62 != 2) goto L99;
        zzjl r37 = (zzjl) r2.getObject(r1, r33);
        if (r37.zza() == true) goto L98;
        int r68 = r37.size();
        if (r68 != 0) goto L96;
        int r69 = 10;
    L97:
        r37 = r37.zza(r69);
        r2.putObject(r1, r33, r37);
        goto L98
    L96:
        r69 = r68 << 1;
    L98:
        int r18 = zzhl.zza(r0.zza(r82), r292, r7, r12, r30, r37, r13);
        r7 = r28;
        r13 = r31;
        r3 = r18;
        r5 = r82;
        r2 = r2;
        r4 = r14;
        r15 = -1;
        r1 = r27;
        goto L79
    L99:
        r36 = r12;
        r172 = r11;
        r23 = r2;
        r94 = r14;
        r142 = r10;
        r102 = r292;
        goto L117
    L14:
        r92 = r1;
        r23 = r2;
        r24 = r32;
        r63 = r14;
        r82 = 0;
        goto L121
    L12:
        r42 = r0.zzg(r14);
        goto L13
    L123:
        T r95 = r1;
        Unsafe r232 = r2;
        int r43 = r8;
        int r143 = r10;
        int r173 = r11;
        if (r143 == 1048575) goto L126;
        r232.putInt(r95, r143, r173);
    L126:
        if (r3 != r43) goto L129;
        return;
    L129:
        throw zzjk.zzg();
    L130:
        zza(r1, r28, r29, r8, 0, r31);
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final boolean zza(T r10, T r11) {
        int r0 = this.zzc.length;
        int r2 = 0;
    L3:
        boolean r3 = true;
        if (r2 >= r0) goto L109;
        int r4 = zzd(r2);
        long r6 = r4 & 1048575;
        switch(((r4 & 267386880) >>> 20)) {
            case 0: goto L101;
            case 1: goto L96;
            case 2: goto L91;
            case 3: goto L86;
            case 4: goto L81;
            case 5: goto L76;
            case 6: goto L71;
            case 7: goto L66;
            case 8: goto L61;
            case 9: goto L56;
            case 10: goto L51;
            case 11: goto L46;
            case 12: goto L41;
            case 13: goto L36;
            case 14: goto L31;
            case 15: goto L26;
            case 16: goto L21;
            case 17: goto L16;
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
            default: goto L105;
        };
    L8:
        long r42 = zze(r2) & 1048575;
        if (zzma.zza(r10, r42) == zzma.zza(r11, r42)) goto L11;
    L12:
        r3 = false;
        goto L105
    L11:
        if (zzle.zza(zzma.zzf(r10, r6), zzma.zzf(r11, r6)) == true) goto L105;
    L13:
        r3 = zzle.zza(zzma.zzf(r10, r6), zzma.zzf(r11, r6));
        goto L105
    L14:
        r3 = zzle.zza(zzma.zzf(r10, r6), zzma.zzf(r11, r6));
    L105:
        if (r3 == false) goto L106;
        r2 = r2 + 3;
        goto L3
    L106:
        return false;
    L16:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzle.zza(zzma.zzf(r10, r6), zzma.zzf(r11, r6)) == true) goto L105;
    L21:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zzb(r10, r6) == zzma.zzb(r11, r6)) goto L105;
    L26:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zza(r10, r6) == zzma.zza(r11, r6)) goto L105;
    L31:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zzb(r10, r6) == zzma.zzb(r11, r6)) goto L105;
    L36:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zza(r10, r6) == zzma.zza(r11, r6)) goto L105;
    L41:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zza(r10, r6) == zzma.zza(r11, r6)) goto L105;
    L46:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zza(r10, r6) == zzma.zza(r11, r6)) goto L105;
    L51:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzle.zza(zzma.zzf(r10, r6), zzma.zzf(r11, r6)) == true) goto L105;
    L56:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzle.zza(zzma.zzf(r10, r6), zzma.zzf(r11, r6)) == true) goto L105;
    L61:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzle.zza(zzma.zzf(r10, r6), zzma.zzf(r11, r6)) == true) goto L105;
    L66:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zzc(r10, r6) == zzma.zzc(r11, r6)) goto L105;
    L71:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zza(r10, r6) == zzma.zza(r11, r6)) goto L105;
    L76:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zzb(r10, r6) == zzma.zzb(r11, r6)) goto L105;
    L81:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zza(r10, r6) == zzma.zza(r11, r6)) goto L105;
    L86:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zzb(r10, r6) == zzma.zzb(r11, r6)) goto L105;
    L91:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzma.zzb(r10, r6) == zzma.zzb(r11, r6)) goto L105;
    L96:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (Float.floatToIntBits(zzma.zzd(r10, r6)) == Float.floatToIntBits(zzma.zzd(r11, r6))) goto L105;
    L101:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (Double.doubleToLongBits(zzma.zze(r10, r6)) == Double.doubleToLongBits(zzma.zze(r11, r6))) goto L105;
    L109:
        if (this.zzq.zzb(r10).equals(this.zzq.zzb(r11)) == true) goto L112;
        return false;
    L112:
        if (this.zzh == true) goto L114;
        return true;
    L114:
        return this.zzr.zza(r10).equals(this.zzr.zza(r11));
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final int zzb(T r20) {
        int r3 = 267386880;
        int r7 = 1048575;
        int r11 = 0;
        if (this.zzj == false) goto L194;
        Unsafe r2 = zzb;
        int r12 = 0;
        int r13 = 0;
    L6:
        if (r12 >= this.zzc.length) goto L193;
        int r14 = zzd(r12);
        int r15 = (r14 & r3) >>> 20;
        int r16 = r3;
        int r32 = this.zzc[r12];
        long r4 = r14 & 1048575;
        if (r15 >= zziv.zza.zza()) goto L10;
    L12:
        switch(r15) {
            case 0: goto L189;
            case 1: goto L186;
            case 2: goto L183;
            case 3: goto L180;
            case 4: goto L177;
            case 5: goto L174;
            case 6: goto L171;
            case 7: goto L168;
            case 8: goto L162;
            case 9: goto L159;
            case 10: goto L156;
            case 11: goto L153;
            case 12: goto L150;
            case 13: goto L147;
            case 14: goto L144;
            case 15: goto L141;
            case 16: goto L138;
            case 17: goto L135;
            case 18: goto L133;
            case 19: goto L132;
            case 20: goto L131;
            case 21: goto L130;
            case 22: goto L129;
            case 23: goto L128;
            case 24: goto L127;
            case 25: goto L126;
            case 26: goto L125;
            case 27: goto L124;
            case 28: goto L123;
            case 29: goto L122;
            case 30: goto L121;
            case 31: goto L120;
            case 32: goto L119;
            case 33: goto L118;
            case 34: goto L117;
            case 35: goto L114;
            case 36: goto L111;
            case 37: goto L108;
            case 38: goto L105;
            case 39: goto L102;
            case 40: goto L99;
            case 41: goto L96;
            case 42: goto L93;
            case 43: goto L90;
            case 44: goto L87;
            case 45: goto L84;
            case 46: goto L81;
            case 47: goto L78;
            case 48: goto L74;
            case 49: goto L73;
            case 50: goto L72;
            case 51: goto L70;
            case 52: goto L67;
            case 53: goto L64;
            case 54: goto L61;
            case 55: goto L58;
            case 56: goto L55;
            case 57: goto L52;
            case 58: goto L49;
            case 59: goto L43;
            case 60: goto L40;
            case 61: goto L37;
            case 62: goto L34;
            case 63: goto L31;
            case 64: goto L28;
            case 65: goto L25;
            case 66: goto L22;
            case 67: goto L19;
            case 68: goto L15;
            default: goto L191;
        };
    L72:
        int r33 = this.zzs.zza(r32, zzma.zzf(r20, r4), zzb(r12));
    L17:
        r13 = r13 + r33;
        goto L191
    L73:
        r33 = zzle.zzb(r32, zza(r20, r4), zza(r12));
        goto L17
    L74:
        int r42 = zzle.zzc((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        int r34 = zzii.zze(r32);
        int r5 = zzii.zzg(r42);
    L77:
        r33 = (r34 + r5) + r42;
        goto L17
    L78:
        r42 = zzle.zzg((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L81:
        r42 = zzle.zzi((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L84:
        r42 = zzle.zzh((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L87:
        r42 = zzle.zzd((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L90:
        r42 = zzle.zzf((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L93:
        r42 = zzle.zzj((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L96:
        r42 = zzle.zzh((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L99:
        r42 = zzle.zzi((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L102:
        r42 = zzle.zze((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L105:
        r42 = zzle.zzb((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L108:
        r42 = zzle.zza((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L111:
        r42 = zzle.zzh((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L114:
        r42 = zzle.zzi((List) r2.getObject(r20, r4));
        if (r42 <= 0) goto L191;
        r34 = zzii.zze(r32);
        r5 = zzii.zzg(r42);
        goto L77
    L117:
        r33 = zzle.zzc(r32, zza(r20, r4), false);
        goto L17
    L118:
        r33 = zzle.zzg(r32, zza(r20, r4), false);
        goto L17
    L119:
        r33 = zzle.zzi(r32, zza(r20, r4), false);
        goto L17
    L120:
        r33 = zzle.zzh(r32, zza(r20, r4), false);
        goto L17
    L121:
        r33 = zzle.zzd(r32, zza(r20, r4), false);
        goto L17
    L122:
        r33 = zzle.zzf(r32, zza(r20, r4), false);
        goto L17
    L123:
        r33 = zzle.zzb(r32, zza(r20, r4));
        goto L17
    L124:
        r33 = zzle.zza(r32, zza(r20, r4), zza(r12));
        goto L17
    L125:
        r33 = zzle.zza(r32, zza(r20, r4));
        goto L17
    L126:
        r33 = zzle.zzj(r32, zza(r20, r4), false);
        goto L17
    L127:
        r33 = zzle.zzh(r32, zza(r20, r4), false);
        goto L17
    L128:
        r33 = zzle.zzi(r32, zza(r20, r4), false);
        goto L17
    L129:
        r33 = zzle.zze(r32, zza(r20, r4), false);
        goto L17
    L130:
        r33 = zzle.zzb(r32, zza(r20, r4), false);
        goto L17
    L131:
        r33 = zzle.zza(r32, zza(r20, r4), false);
        goto L17
    L132:
        r33 = zzle.zzh(r32, zza(r20, r4), false);
        goto L17
    L133:
        r33 = zzle.zzi(r32, zza(r20, r4), false);
        goto L17
    L15:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzc(r32, (zzkk) zzma.zzf(r20, r4), zza(r12));
        goto L17
    L19:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzf(r32, zze(r20, r4));
        goto L17
    L22:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzh(r32, zzd(r20, r4));
        goto L17
    L25:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzh(r32, 0);
        goto L17
    L28:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzj(r32, 0);
        goto L17
    L31:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzk(r32, zzd(r20, r4));
        goto L17
    L34:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzg(r32, zzd(r20, r4));
        goto L17
    L37:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzc(r32, (zzht) zzma.zzf(r20, r4));
        goto L17
    L40:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzle.zza(r32, zzma.zzf(r20, r4), zza(r12));
        goto L17
    L43:
        if (zza(r20, r32, r12) == false) goto L191;
        Object r43 = zzma.zzf(r20, r4);
        if ((r43 instanceof zzht) == false) goto L47;
        r33 = zzii.zzc(r32, (zzht) r43);
        goto L17
    L47:
        r33 = zzii.zzb(r32, (String) r43);
        goto L17
    L49:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzb(r32, true);
        goto L17
    L52:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzi(r32, 0);
        goto L17
    L55:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzg(r32, 0);
        goto L17
    L58:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzf(r32, zzd(r20, r4));
        goto L17
    L61:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zze(r32, zze(r20, r4));
        goto L17
    L64:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzd(r32, zze(r20, r4));
        goto L17
    L67:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzb(r32, 0.0f);
        goto L17
    L70:
        if (zza(r20, r32, r12) == false) goto L191;
        r33 = zzii.zzb(r32, 0.0d);
        goto L17
    L135:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzc(r32, (zzkk) zzma.zzf(r20, r4), zza(r12));
        goto L17
    L138:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzf(r32, zzma.zzb(r20, r4));
        goto L17
    L141:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzh(r32, zzma.zza(r20, r4));
        goto L17
    L144:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzh(r32, 0);
        goto L17
    L147:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzj(r32, 0);
        goto L17
    L150:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzk(r32, zzma.zza(r20, r4));
        goto L17
    L153:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzg(r32, zzma.zza(r20, r4));
        goto L17
    L156:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzc(r32, (zzht) zzma.zzf(r20, r4));
        goto L17
    L159:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzle.zza(r32, zzma.zzf(r20, r4), zza(r12));
        goto L17
    L162:
        if (zza(r20, r12) == false) goto L191;
        Object r44 = zzma.zzf(r20, r4);
        if ((r44 instanceof zzht) == false) goto L166;
        r33 = zzii.zzc(r32, (zzht) r44);
        goto L17
    L166:
        r33 = zzii.zzb(r32, (String) r44);
        goto L17
    L168:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzb(r32, true);
        goto L17
    L171:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzi(r32, 0);
        goto L17
    L174:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzg(r32, 0);
        goto L17
    L177:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzf(r32, zzma.zza(r20, r4));
        goto L17
    L180:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zze(r32, zzma.zzb(r20, r4));
        goto L17
    L183:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzd(r32, zzma.zzb(r20, r4));
        goto L17
    L186:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzb(r32, 0.0f);
        goto L17
    L189:
        if (zza(r20, r12) == false) goto L191;
        r33 = zzii.zzb(r32, 0.0d);
    L191:
        r12 = r12 + 3;
        r3 = r16;
        goto L6
    L10:
        if (r15 > zziv.zzb.zza()) goto L12;
        int r142 = this.zzc[r12 + 2];
        goto L12
    L193:
        return r13 + zza(this.zzq, r20);
    L194:
        Unsafe r22 = zzb;
        int r52 = 1048575;
        int r35 = 0;
        int r45 = 0;
        int r122 = 0;
    L196:
        if (r35 >= this.zzc.length) goto L389;
        int r132 = zzd(r35);
        int[] r143 = this.zzc;
        int r152 = r143[r35];
        int r18 = r7;
        int r72 = (r132 & 267386880) >>> 20;
        if (r72 > 17) goto L202;
        int r6 = r143[r35 + 2];
        int r144 = r6 & r18;
        int r62 = 1 << (r6 >>> 20);
        if (r144 == r52) goto L203;
        r122 = r22.getInt(r20, r144);
        r52 = r144;
    L203:
        long r133 = r132 & r18;
        switch(r72) {
            case 0: goto L385;
            case 1: goto L382;
            case 2: goto L379;
            case 3: goto L376;
            case 4: goto L373;
            case 5: goto L370;
            case 6: goto L368;
            case 7: goto L365;
            case 8: goto L359;
            case 9: goto L356;
            case 10: goto L353;
            case 11: goto L350;
            case 12: goto L347;
            case 13: goto L344;
            case 14: goto L341;
            case 15: goto L338;
            case 16: goto L335;
            case 17: goto L332;
            case 18: goto L330;
            case 19: goto L329;
            case 20: goto L328;
            case 21: goto L327;
            case 22: goto L326;
            case 23: goto L325;
            case 24: goto L324;
            case 25: goto L323;
            case 26: goto L322;
            case 27: goto L321;
            case 28: goto L320;
            case 29: goto L319;
            case 30: goto L318;
            case 31: goto L317;
            case 32: goto L316;
            case 33: goto L315;
            case 34: goto L313;
            case 35: goto L310;
            case 36: goto L307;
            case 37: goto L304;
            case 38: goto L301;
            case 39: goto L298;
            case 40: goto L295;
            case 41: goto L292;
            case 42: goto L289;
            case 43: goto L286;
            case 44: goto L283;
            case 45: goto L280;
            case 46: goto L277;
            case 47: goto L274;
            case 48: goto L270;
            case 49: goto L269;
            case 50: goto L268;
            case 51: goto L266;
            case 52: goto L263;
            case 53: goto L260;
            case 54: goto L257;
            case 55: goto L254;
            case 56: goto L251;
            case 57: goto L248;
            case 58: goto L245;
            case 59: goto L239;
            case 60: goto L236;
            case 61: goto L233;
            case 62: goto L230;
            case 63: goto L227;
            case 64: goto L223;
            case 65: goto L220;
            case 66: goto L217;
            case 67: goto L214;
            case 68: goto L207;
            default: goto L210;
        };
    L268:
        int r63 = this.zzs.zza(r152, r22.getObject(r20, r133), zzb(r35));
    L209:
        r45 = r45 + r63;
        goto L210
    L269:
        r63 = zzle.zzb(r152, (List) r22.getObject(r20, r133), zza(r35));
        goto L209
    L270:
        int r64 = zzle.zzc((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        int r73 = zzii.zze(r152);
        int r112 = zzii.zzg(r64);
    L273:
        int r74 = (r73 + r112) + r64;
    L225:
        r45 = r45 + r74;
        goto L210
    L274:
        r64 = zzle.zzg((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L277:
        r64 = zzle.zzi((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L280:
        r64 = zzle.zzh((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L283:
        r64 = zzle.zzd((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L286:
        r64 = zzle.zzf((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L289:
        r64 = zzle.zzj((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L292:
        r64 = zzle.zzh((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L295:
        r64 = zzle.zzi((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L298:
        r64 = zzle.zze((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L301:
        r64 = zzle.zzb((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L304:
        r64 = zzle.zza((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L307:
        r64 = zzle.zzh((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L310:
        r64 = zzle.zzi((List) r22.getObject(r20, r133));
        if (r64 <= 0) goto L210;
        r73 = zzii.zze(r152);
        r112 = zzii.zzg(r64);
        goto L273
    L313:
        int r75 = 0;
        int r65 = zzle.zzc(r152, (List) r22.getObject(r20, r133), false);
    L314:
        r45 = r45 + r65;
    L211:
        boolean r113 = false;
    L388:
        r35 = r35 + 3;
        r11 = r75;
        r7 = r18;
        goto L196
    L315:
        r75 = 0;
        r65 = zzle.zzg(r152, (List) r22.getObject(r20, r133), false);
        goto L314
    L316:
        r75 = 0;
        r65 = zzle.zzi(r152, (List) r22.getObject(r20, r133), false);
        goto L314
    L317:
        r75 = 0;
        r65 = zzle.zzh(r152, (List) r22.getObject(r20, r133), false);
        goto L314
    L318:
        r75 = 0;
        r65 = zzle.zzd(r152, (List) r22.getObject(r20, r133), false);
        goto L314
    L319:
        r63 = zzle.zzf(r152, (List) r22.getObject(r20, r133), false);
        goto L209
    L320:
        r63 = zzle.zzb(r152, (List) r22.getObject(r20, r133));
        goto L209
    L321:
        r63 = zzle.zza(r152, (List) r22.getObject(r20, r133), zza(r35));
        goto L209
    L322:
        r63 = zzle.zza(r152, (List) r22.getObject(r20, r133));
        goto L209
    L323:
        r75 = 0;
        r65 = zzle.zzj(r152, (List) r22.getObject(r20, r133), false);
        goto L314
    L324:
        r75 = 0;
        r65 = zzle.zzh(r152, (List) r22.getObject(r20, r133), false);
        goto L314
    L325:
        r75 = 0;
        r65 = zzle.zzi(r152, (List) r22.getObject(r20, r133), false);
        goto L314
    L326:
        r75 = 0;
        r65 = zzle.zze(r152, (List) r22.getObject(r20, r133), false);
        goto L314
    L327:
        r75 = 0;
        r65 = zzle.zzb(r152, (List) r22.getObject(r20, r133), false);
        goto L314
    L328:
        r75 = 0;
        r65 = zzle.zza(r152, (List) r22.getObject(r20, r133), false);
        goto L314
    L329:
        r75 = 0;
        r65 = zzle.zzh(r152, (List) r22.getObject(r20, r133), false);
        goto L314
    L330:
        r63 = zzle.zzi(r152, (List) r22.getObject(r20, r133), false);
        goto L209
    L370:
        r75 = 0;
        if ((r62 & r122) == 0) goto L211;
        r65 = zzii.zzg(r152, 0);
        goto L314
    L373:
        r75 = 0;
        if ((r62 & r122) == 0) goto L211;
        r65 = zzii.zzf(r152, r22.getInt(r20, r133));
        goto L314
    L376:
        r75 = 0;
        if ((r62 & r122) == 0) goto L211;
        r65 = zzii.zze(r152, r22.getLong(r20, r133));
        goto L314
    L379:
        r75 = 0;
        if ((r62 & r122) == 0) goto L211;
        r65 = zzii.zzd(r152, r22.getLong(r20, r133));
        goto L314
    L382:
        r75 = 0;
        if ((r62 & r122) == 0) goto L211;
        r113 = false;
        r45 = r45 + zzii.zzb(r152, 0.0f);
        goto L388
    L385:
        r75 = 0;
        r113 = false;
        if ((r62 & r122) == 0) goto L388;
        r45 = r45 + zzii.zzb(r152, 0.0d);
    L210:
        r75 = 0;
        goto L211
    L207:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zzc(r152, (zzkk) r22.getObject(r20, r133), zza(r35));
        goto L209
    L214:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zzf(r152, zze(r20, r133));
        goto L209
    L217:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zzh(r152, zzd(r20, r133));
        goto L209
    L220:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zzh(r152, 0);
        goto L209
    L223:
        if (zza(r20, r152, r35) == false) goto L210;
        r74 = zzii.zzj(r152, 0);
        goto L225
    L227:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zzk(r152, zzd(r20, r133));
        goto L209
    L230:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zzg(r152, zzd(r20, r133));
        goto L209
    L233:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zzc(r152, (zzht) r22.getObject(r20, r133));
        goto L209
    L236:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzle.zza(r152, r22.getObject(r20, r133), zza(r35));
        goto L209
    L239:
        if (zza(r20, r152, r35) == false) goto L210;
        Object r66 = r22.getObject(r20, r133);
        if ((r66 instanceof zzht) == false) goto L243;
        r63 = zzii.zzc(r152, (zzht) r66);
        goto L209
    L243:
        r63 = zzii.zzb(r152, (String) r66);
        goto L209
    L245:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zzb(r152, true);
        goto L209
    L248:
        if (zza(r20, r152, r35) == false) goto L210;
        r74 = zzii.zzi(r152, 0);
        goto L225
    L251:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zzg(r152, 0);
        goto L209
    L254:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zzf(r152, zzd(r20, r133));
        goto L209
    L257:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zze(r152, zze(r20, r133));
        goto L209
    L260:
        if (zza(r20, r152, r35) == false) goto L210;
        r63 = zzii.zzd(r152, zze(r20, r133));
        goto L209
    L263:
        if (zza(r20, r152, r35) == false) goto L210;
        r74 = zzii.zzb(r152, 0.0f);
        goto L225
    L266:
        if (zza(r20, r152, r35) == false) goto L210;
        r45 = r45 + zzii.zzb(r152, 0.0d);
        goto L210
    L332:
        if ((r62 & r122) == 0) goto L210;
        r63 = zzii.zzc(r152, (zzkk) r22.getObject(r20, r133), zza(r35));
        goto L209
    L335:
        if ((r62 & r122) == 0) goto L210;
        r63 = zzii.zzf(r152, r22.getLong(r20, r133));
        goto L209
    L338:
        if ((r62 & r122) == 0) goto L210;
        r63 = zzii.zzh(r152, r22.getInt(r20, r133));
        goto L209
    L341:
        if ((r62 & r122) == 0) goto L210;
        r63 = zzii.zzh(r152, 0);
        goto L209
    L344:
        if ((r62 & r122) == 0) goto L210;
        r74 = zzii.zzj(r152, 0);
        goto L225
    L347:
        if ((r62 & r122) == 0) goto L210;
        r63 = zzii.zzk(r152, r22.getInt(r20, r133));
        goto L209
    L350:
        if ((r62 & r122) == 0) goto L210;
        r63 = zzii.zzg(r152, r22.getInt(r20, r133));
        goto L209
    L353:
        if ((r62 & r122) == 0) goto L210;
        r63 = zzii.zzc(r152, (zzht) r22.getObject(r20, r133));
        goto L209
    L356:
        if ((r62 & r122) == 0) goto L210;
        r63 = zzle.zza(r152, r22.getObject(r20, r133), zza(r35));
        goto L209
    L359:
        if ((r62 & r122) == 0) goto L210;
        Object r67 = r22.getObject(r20, r133);
        if ((r67 instanceof zzht) == false) goto L363;
        r63 = zzii.zzc(r152, (zzht) r67);
        goto L209
    L363:
        r63 = zzii.zzb(r152, (String) r67);
        goto L209
    L365:
        if ((r62 & r122) == 0) goto L210;
        r63 = zzii.zzb(r152, true);
        goto L209
    L368:
        if ((r62 & r122) == 0) goto L210;
        r75 = 0;
        r65 = zzii.zzi(r152, 0);
        goto L314
    L202:
        r62 = 0;
        goto L203
    L389:
        int r76 = r11;
        int r46 = r45 + zza(this.zzq, r20);
        if (this.zzh == false) goto L460;
        zziu<T> r0 = this.zzr.zza(r20);
        int r114 = r76;
    L392:
        int r1 = r0.zza.zzc();
        zzlh<T, Object> r23 = r0.zza;
        if (r114 >= r1) goto L395;
        Map.Entry r17 = r23.zzb(r114);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r17.getKey());
        r76 = r76 + zziu.zzc(null, r17.getValue());
        r114 = r114 + 1;
        goto L392
    L395:
        Iterator<T> r02 = r23.zzd().iterator();
    L397:
        if (r02.hasNext() == false) goto L400;
        Map.Entry r19 = (Map.Entry) r02.next();
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r19.getKey());
        r76 = r76 + zziu.zzc(null, r19.getValue());
        goto L397
    L400:
        return r46 + r76;
    L460:
        return r46;
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zzb(T r7, T r8) {
        r8.getClass();
        int r0 = 0;
    L4:
        if (r0 >= this.zzc.length) goto L69;
        int r1 = zzd(r0);
        long r2 = 1048575 & r1;
        int r4 = this.zzc[r0];
        switch(((r1 & 267386880) >>> 20)) {
            case 0: goto L66;
            case 1: goto L63;
            case 2: goto L60;
            case 3: goto L57;
            case 4: goto L54;
            case 5: goto L51;
            case 6: goto L48;
            case 7: goto L45;
            case 8: goto L42;
            case 9: goto L40;
            case 10: goto L38;
            case 11: goto L35;
            case 12: goto L32;
            case 13: goto L29;
            case 14: goto L26;
            case 15: goto L23;
            case 16: goto L20;
            case 17: goto L18;
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
            default: goto L68;
        };
    L8:
        zzb(r7, r8, r0);
        goto L68
    L12:
        zzb(r7, r8, r0);
        goto L68
    L16:
        zzle.zza(this.zzs, r7, r8, r2);
        goto L68
    L17:
        this.zzp.zza(r7, r8, r2);
        goto L68
    L18:
        zza(r7, r8, r0);
        goto L68
    L40:
        zza(r7, r8, r0);
        goto L68
    L10:
        if (zza(r8, r4, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zzf(r8, r2));
        zzb(r7, r4, r0);
        goto L68
    L14:
        if (zza(r8, r4, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zzf(r8, r2));
        zzb(r7, r4, r0);
        goto L68
    L20:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zzb(r8, r2));
        zzb(r7, r0);
        goto L68
    L23:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zza(r8, r2));
        zzb(r7, r0);
        goto L68
    L26:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zzb(r8, r2));
        zzb(r7, r0);
        goto L68
    L29:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zza(r8, r2));
        zzb(r7, r0);
        goto L68
    L32:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zza(r8, r2));
        zzb(r7, r0);
        goto L68
    L35:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zza(r8, r2));
        zzb(r7, r0);
        goto L68
    L38:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zzf(r8, r2));
        zzb(r7, r0);
        goto L68
    L42:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zzf(r8, r2));
        zzb(r7, r0);
        goto L68
    L45:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zzc(r8, r2));
        zzb(r7, r0);
        goto L68
    L48:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zza(r8, r2));
        zzb(r7, r0);
        goto L68
    L51:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zzb(r8, r2));
        zzb(r7, r0);
        goto L68
    L54:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zza(r8, r2));
        zzb(r7, r0);
        goto L68
    L57:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zzb(r8, r2));
        zzb(r7, r0);
        goto L68
    L60:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zzb(r8, r2));
        zzb(r7, r0);
        goto L68
    L63:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zzd(r8, r2));
        zzb(r7, r0);
        goto L68
    L66:
        if (zza(r8, r0) == false) goto L68;
        zzma.zza(r7, r2, zzma.zze(r8, r2));
        zzb(r7, r0);
    L68:
        r0 = r0 + 3;
        goto L4
    L69:
        zzle.zza(this.zzq, r7, r8);
        if (this.zzh == false) goto L117;
        zzle.zza(this.zzr, r7, r8);
        return;
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zzc(T r6) {
        int r0 = this.zzm;
    L3:
        int r1 = this.zzn;
        int[] r2 = this.zzl;
        if (r0 >= r1) goto L9;
        long r12 = zzd(r2[r0]) & 1048575;
        Object r3 = zzma.zzf(r6, r12);
        if (r3 == null) goto L8;
        zzma.zza(r6, r12, this.zzs.zze(r3));
    L8:
        r0 = r0 + 1;
        goto L3
    L9:
        int r02 = r2.length;
    L10:
        if (r1 >= r02) goto L12;
        this.zzp.zzb(r6, this.zzl[r1]);
        r1 = r1 + 1;
        goto L10
    L12:
        this.zzq.zzd(r6);
        if (this.zzh == false) goto L20;
        this.zzr.zzc(r6);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzlc
    public final boolean zzd(T r15) {
        int r3 = 1048575;
        int r2 = 0;
        int r4 = 0;
    L4:
        if (r2 >= this.zzm) goto L57;
        int r9 = this.zzl[r2];
        int r5 = this.zzc[r9];
        int r13 = this.zzd(r9);
        int r7 = this.zzc[r9 + 2];
        int r8 = r7 & 1048575;
        int r12 = 1 << (r7 >>> 20);
        if (r8 == r3) goto L10;
        if (r8 == 1048575) goto L9;
        r4 = zzb.getInt(r15, r8);
    L9:
        int r11 = r4;
        int r10 = r8;
    L12:
        if ((268435456 & r13) == 0) goto L16;
        zzko<T> r72 = this;
        T r82 = r15;
        if (r72.zza(r82, r9, r10, r11, r12) == false) goto L15;
    L17:
        int r14 = (267386880 & r13) >>> 20;
        if (r14 == 9) goto L52;
        if (r14 == 17) goto L52;
        if (r14 != 27) goto L24;
    L42:
        List r142 = (List) zzma.zzf(r82, r13 & 1048575);
        if (r142.isEmpty() == true) goto L56;
        zzlc r152 = r72.zza(r9);
        int r32 = 0;
    L46:
        if (r32 >= r142.size()) goto L56;
        if (r152.zzd(r142.get(r32)) == false) goto L49;
        r32 = r32 + 1;
        goto L46
    L49:
        return false;
    L56:
        r2 = r2 + 1;
        zzko<T> this = r72;
        r15 = r82;
        r3 = r10;
        r4 = r11;
        goto L4
    L24:
        if (r14 == 60) goto L38;
        if (r14 == 68) goto L38;
        if (r14 == 49) goto L42;
        if (r14 != 50) goto L56;
        if (r72.zzs.zzc(zzma.zzf(r82, r13 & 1048575)).isEmpty() == true) goto L56;
        r72.zzs.zzb(r72.zzb(r9));
        throw null;
    L38:
        if (r72.zza(r82, r5, r9) == false) goto L56;
        if (zza(r82, r13, r72.zza(r9)) == true) goto L56;
        return false;
    L52:
        if (r72.zza(r82, r9, r10, r11, r12) == false) goto L56;
        if (zza(r82, r13, r72.zza(r9)) == true) goto L56;
        return false;
    L15:
        return false;
    L16:
        r72 = this;
        r82 = r15;
        goto L17
    L10:
        r10 = r3;
        r11 = r4;
        goto L12
    L57:
        zzko<T> r73 = this;
        T r83 = r15;
        if (r73.zzh == true) goto L60;
    L62:
        return true;
    L60:
        if (r73.zzr.zza(r83).zzf() == true) goto L62;
        return false;
    }
}
