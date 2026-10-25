package com.google.android.gms.internal.clearcut;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.gms.internal.clearcut.zzcg;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline0;
import sun.misc.Unsafe;

/* loaded from: classes4.dex */
final class zzds<T> implements zzef<T> {
    private static final Unsafe zzmh = null;
    private final int[] zzmi;
    private final Object[] zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final zzdo zzmn;
    private final boolean zzmo;
    private final boolean zzmp;
    private final boolean zzmq;
    private final boolean zzmr;
    private final int[] zzms;
    private final int[] zzmt;
    private final int[] zzmu;
    private final zzdw zzmv;
    private final zzcy zzmw;
    private final zzex<?, ?> zzmx;
    private final zzbu<?> zzmy;
    private final zzdj zzmz;

    static {
        zzmh = zzfd.zzef();
    }

    private zzds(int[] r1, Object[] r2, int r3, int r4, int r5, zzdo r6, boolean r7, boolean r8, int[] r9, int[] r10, int[] r11, zzdw r12, zzcy r13, zzex<?, ?> r14, zzbu<?> r15, zzdj r16) {
        this.zzmi = r1;
        this.zzmj = r2;
        this.zzmk = r3;
        this.zzml = r4;
        this.zzmm = r5;
        this.zzmp = r6 instanceof zzcg;
        this.zzmq = r7;
        if (r15 != null) goto L5;
    L7:
        boolean r22 = false;
    L8:
        this.zzmo = r22;
        this.zzmr = false;
        this.zzms = r9;
        this.zzmt = r10;
        this.zzmu = r11;
        this.zzmv = r12;
        this.zzmw = r13;
        this.zzmx = r14;
        this.zzmy = r15;
        this.zzmn = r6;
        this.zzmz = r16;
        return;
    L5:
        if (r15.zze(r6) == false) goto L7;
        r22 = true;
        goto L8
    }

    private static int zza(int r0, byte[] r1, int r2, int r3, Object r4, zzay r5) {
        return zzax.zza(r0, r1, r2, r3, zzn(r4), r5);
    }

    private static int zza(zzef<?> r2, int r3, byte[] r4, int r5, int r6, zzcn<?> r7, zzay r8) {
        int r52 = zza(r2, r4, r5, r6, r8);
    L3:
        r7.add(r8.zzff);
        if (r52 >= r6) goto L8;
        int r0 = zzax.zza(r4, r52, r8);
        if (r3 != r8.zzfd) goto L8;
        r52 = zza(r2, r4, r0, r6, r8);
    L8:
        return r52;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int zza(zzef r7, byte[] r8, int r9, int r10, int r11, zzay r12) {
        zzds r0 = (zzds) r7;
        Object r1 = r0.newInstance();
        int r72 = r0.zza(r1, r8, r9, r10, r11, r12);
        r0.zzc(r1);
        r12.zzff = r1;
        return r72;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int zza(zzef r6, byte[] r7, int r8, int r9, zzay r10) {
        int r0 = r8 + 1;
        int r82 = r7[r8];
        if (r82 >= 0) goto L5;
        r0 = zzax.zza(r82, r7, r0, r10);
        r82 = r10.zzfd;
    L5:
        int r3 = r0;
        if (r82 < 0) goto L12;
        if (r82 > (r9 - r3)) goto L12;
        Object r1 = r6.newInstance();
        int r4 = r3 + r82;
        r6.zza(r1, r7, r3, r4, r10);
        r6.zzc(r1);
        r10.zzff = r1;
        return r4;
    L12:
        throw zzco.zzbl();
    }

    private static <UT, UB> int zza(zzex<UT, UB> r0, T r1) {
        return r0.zzm(r0.zzq(r1));
    }

    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, zzay r29) {
        Unsafe r11 = zzmh;
        long r12 = this.zzmi[r28 + 2] & 1048575;
        Object r14 = null;
        boolean r6 = true;
        switch(r25) {
            case 51: goto L91;
            case 52: goto L88;
            case 53: goto L86;
            case 54: goto L86;
            case 55: goto L83;
            case 56: goto L78;
            case 57: goto L74;
            case 58: goto L68;
            case 59: goto L53;
            case 60: goto L42;
            case 61: goto L34;
            case 62: goto L83;
            case 63: goto L24;
            case 64: goto L74;
            case 65: goto L78;
            case 66: goto L20;
            case 67: goto L15;
            case 68: goto L6;
            default: goto L96;
        };
    L74:
        int r15 = r19;
        if (r23 != 5) goto L104;
        Object r0 = Integer.valueOf(zzax.zzc(r18, r19));
    L77:
        r11.putObject(r17, r26, r0);
        int r02 = r15 + 4;
    L94:
        r11.putInt(r17, r12, r22);
        return r02;
    L104:
        return r15;
    L78:
        int r152 = r19;
        if (r23 != 1) goto L105;
        Object r03 = Long.valueOf(zzax.zzd(r18, r19));
    L81:
        r11.putObject(r17, r26, r03);
        r02 = r152 + 8;
        goto L94
    L105:
        return r152;
    L88:
        r15 = r19;
        if (r23 != 5) goto L108;
        r0 = Float.valueOf(zzax.zzf(r18, r19));
        goto L77
    L108:
        return r15;
    L91:
        r152 = r19;
        if (r23 != 1) goto L109;
        r03 = Double.valueOf(zzax.zze(r18, r19));
        goto L81
    L109:
        return r152;
    L96:
        return r19;
    L6:
        if (r23 != 3) goto L96;
        r02 = zza(zzad(r28), r18, r19, r20, (r21 & (-8)) | 4, r29);
        if (r11.getInt(r17, r12) != r22) goto L10;
        r14 = r11.getObject(r17, r26);
    L10:
        Object r2 = r29.zzff;
        if (r14 == null) goto L18;
        r2 = zzci.zza(r14, r2);
    L18:
        r11.putObject(r17, r26, r2);
        goto L94
    L15:
        if (r23 != 0) goto L97;
        r02 = zzax.zzb(r18, r19, r29);
        long r27 = zzbk.zza(r29.zzfe);
    L17:
        r2 = Long.valueOf(r27);
        goto L18
    L97:
        return r19;
    L20:
        if (r23 != 0) goto L98;
        r02 = zzax.zza(r18, r19, r29);
        int r210 = zzbk.zzm(r29.zzfd);
    L22:
        r2 = Integer.valueOf(r210);
        goto L18
    L98:
        return r19;
    L24:
        if (r23 != 0) goto L99;
        int r3 = zzax.zza(r18, r19, r29);
        int r5 = r29.zzfd;
        zzck<?> r04 = zzaf(r28);
        if (r04 != null) goto L28;
    L32:
        r11.putObject(r17, r26, Integer.valueOf(r5));
        r02 = r3;
        goto L94
    L28:
        if (r04.zzb(r5) != null) goto L32;
        zzn(r17).zzb(r21, Long.valueOf(r5));
        return r3;
    L99:
        return r19;
    L34:
        if (r23 != 2) goto L100;
        int r05 = zzax.zza(r18, r19, r29);
        int r211 = r29.zzfd;
        if (r211 != 0) goto L38;
        r11.putObject(r17, r26, zzbb.zzfi);
    L39:
        r11.putInt(r17, r12, r22);
        return r05;
    L38:
        r11.putObject(r17, r26, zzbb.zzb(r18, r05, r211));
        r05 = r05 + r211;
        goto L39
    L100:
        return r19;
    L42:
        if (r23 != 2) goto L101;
        int r06 = zza(zzad(r28), r18, r19, r20, r29);
        if (r11.getInt(r17, r12) != r22) goto L46;
        r14 = r11.getObject(r17, r26);
    L46:
        Object r212 = r29.zzff;
        if (r14 != null) goto L49;
    L48:
        r11.putObject(r17, r26, r212);
        r11.putInt(r17, r12, r22);
        return r06;
    L49:
        r212 = zzci.zza(r14, r212);
        goto L48
    L101:
        return r19;
    L53:
        if (r23 != 2) goto L102;
        int r07 = zzax.zza(r18, r19, r29);
        int r213 = r29.zzfd;
        if (r213 != 0) goto L58;
        r11.putObject(r17, r26, _UrlKt.FRAGMENT_ENCODE_SET);
    L65:
        r11.putInt(r17, r12, r22);
        return r07;
    L58:
        if ((r24 & 536870912) != 0) goto L60;
    L64:
        r11.putObject(r17, r26, new String(r18, r07, r213, zzci.UTF_8));
        r07 = r07 + r213;
        goto L65
    L60:
        if (zzff.zze(r18, r07, r07 + r213) == true) goto L64;
        throw zzco.zzbp();
    L102:
        return r19;
    L68:
        if (r23 != 0) goto L103;
        r02 = zzax.zzb(r18, r19, r29);
        if (r29.zzfe != 0) goto L73;
        r6 = false;
    L73:
        r2 = Boolean.valueOf(r6);
        goto L18
    L103:
        return r19;
    L83:
        if (r23 != 0) goto L106;
        r02 = zzax.zza(r18, r19, r29);
        r210 = r29.zzfd;
        goto L22
    L106:
        return r19;
    L86:
        if (r23 != 0) goto L107;
        r02 = zzax.zzb(r18, r19, r29);
        r27 = r29.zzfe;
        goto L17
    L107:
        return r19;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:104:0x01bb -> B:95:0x0194). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x012f -> B:65:0x0111). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:90:0x0186 -> B:83:0x0167). Please report as a decompilation issue!!! */
    private final int zza(T r10, byte[] r11, int r12, int r13, int r14, int r15, int r16, int r17, long r18, int r20, long r21, zzay r23) {
        Unsafe r4 = zzmh;
        zzcn r5 = (zzcn) r4.getObject(r10, r21);
        if (r5.zzu() == true) goto L9;
        int r7 = r5.size();
        if (r7 != 0) goto L7;
        int r72 = 10;
    L8:
        r5 = r5.zzi(r72);
        r4.putObject(r10, r21, r5);
        goto L9
    L7:
        r72 = r7 << 1;
    L9:
        zzcn r42 = r5;
        zzey r73 = null;
        switch(r20) {
            case 18: goto L192;
            case 19: goto L178;
            case 20: goto L164;
            case 21: goto L164;
            case 22: goto L157;
            case 23: goto L143;
            case 24: goto L129;
            case 25: goto L115;
            case 26: goto L79;
            case 27: goto L75;
            case 28: goto L62;
            case 29: goto L157;
            case 30: goto L49;
            case 31: goto L129;
            case 32: goto L143;
            case 33: goto L35;
            case 34: goto L21;
            case 35: goto L192;
            case 36: goto L178;
            case 37: goto L164;
            case 38: goto L164;
            case 39: goto L157;
            case 40: goto L143;
            case 41: goto L129;
            case 42: goto L115;
            case 43: goto L157;
            case 44: goto L49;
            case 45: goto L129;
            case 46: goto L143;
            case 47: goto L35;
            case 48: goto L21;
            case 49: goto L13;
            default: goto L202;
        };
    L202:
        return r12;
    L13:
        if (r16 != 3) goto L202;
        zzef r9 = zzad(r17);
        int r102 = (r14 & (-8)) | 4;
        int r92 = zza(r9, r11, r12, r13, r102, r23);
        r42.add(r23.zzff);
    L15:
        if (r92 >= r13) goto L19;
        int r2 = zzax.zza(r11, r92, r23);
        if (r14 != r23.zzfd) goto L19;
        r92 = zza(r9, r11, r2, r13, r102, r23);
        r42.add(r23.zzff);
    L19:
        return r92;
    L21:
        if (r16 != 2) goto L30;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        int r93 = zzax.zza(r11, r12, r23);
        int r103 = r23.zzfd + r93;
        if (r93 < r103) goto L28;
        if (r93 != r103) goto L27;
        return r93;
    L27:
        throw zzco.zzbl();
    L28:
        zzax.zzb(r11, r93, r23);
        zzbk.zza(r23.zzfe);
        throw null;
    L30:
        if (r16 != 0) goto L202;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        zzax.zzb(r11, r12, r23);
        zzbk.zza(r23.zzfe);
        throw null;
    L35:
        if (r16 != 2) goto L44;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        int r94 = zzax.zza(r11, r12, r23);
        int r104 = r23.zzfd + r94;
        if (r94 < r104) goto L42;
        if (r94 != r104) goto L41;
        return r94;
    L41:
        throw zzco.zzbl();
    L42:
        zzax.zza(r11, r94, r23);
        zzbk.zzm(r23.zzfd);
        throw null;
    L44:
        if (r16 != 0) goto L202;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        zzax.zza(r11, r12, r23);
        zzbk.zzm(r23.zzfd);
        throw null;
    L49:
        if (r16 != 2) goto L51;
        int r112 = zzax.zza(r11, r12, r42, r23);
    L53:
        zzcg r105 = (zzcg) r10;
        zzey r122 = r105.zzjp;
        if (r122 == zzey.zzea()) goto L57;
        r73 = r122;
    L57:
        zzey r95 = (zzey) zzeh.zza(r15, r42, zzaf(r17), r73, this.zzmx);
        if (r95 == null) goto L60;
        r105.zzjp = r95;
    L60:
        return r112;
    L51:
        if (r16 != 0) goto L202;
        r112 = zzax.zza(r14, r11, r12, r13, r42, r23);
        goto L53
    L62:
        if (r16 != 2) goto L202;
        int r96 = zzax.zza(r11, r12, r23);
        int r123 = r23.zzfd;
        if (r123 != 0) goto L66;
    L65:
        r42.add(zzbb.zzfi);
    L67:
        if (r96 >= r13) goto L73;
        int r124 = zzax.zza(r11, r96, r23);
        if (r14 != r23.zzfd) goto L73;
        r96 = zzax.zza(r11, r124, r23);
        int r125 = r23.zzfd;
        if (r125 == 0) goto L65;
    L66:
        r42.add(zzbb.zzb(r11, r96, r125));
        r96 = r96 + r125;
    L73:
        return r96;
    L205:
        r42.add(zzbb.zzb(r11, r96, r123));
        r96 = r96 + r123;
        goto L67
    L75:
        if (r16 != 2) goto L202;
        return zza(zzad(r17), r14, r11, r12, r13, r42, r23);
    L79:
        if (r16 != 2) goto L202;
        long r1 = r18 & 536870912;
        int r97 = zzax.zza(r11, r12, r23);
        int r126 = r23.zzfd;
        if (r1 != 0) goto L94;
        if (r126 != 0) goto L206;
    L83:
        r42.add(_UrlKt.FRAGMENT_ENCODE_SET);
    L86:
        if (r97 >= r13) goto L93;
        int r127 = zzax.zza(r11, r97, r23);
        if (r14 != r23.zzfd) goto L93;
        r97 = zzax.zza(r11, r127, r23);
        int r128 = r23.zzfd;
        if (r128 == 0) goto L83;
        r42.add(new String(r11, r97, r128, zzci.UTF_8));
        r97 = r97 + r128;
    L93:
        return r97;
    L206:
        r42.add(new String(r11, r97, r126, zzci.UTF_8));
        r97 = r97 + r126;
        goto L86
    L94:
        if (r126 != 0) goto L96;
    L95:
        r42.add(_UrlKt.FRAGMENT_ENCODE_SET);
    L100:
        if (r97 >= r13) goto L111;
        int r129 = zzax.zza(r11, r97, r23);
        if (r14 != r23.zzfd) goto L111;
        r97 = zzax.zza(r11, r129, r23);
        int r1210 = r23.zzfd;
        if (r1210 == 0) goto L95;
        int r19 = r97 + r1210;
        if (zzff.zze(r11, r97, r19) == false) goto L110;
        r42.add(new String(r11, r97, r1210, zzci.UTF_8));
        r97 = r19;
        goto L100
    L110:
        throw zzco.zzbp();
    L111:
        return r97;
    L96:
        int r110 = r97 + r126;
        if (zzff.zze(r11, r97, r110) == false) goto L113;
        r42.add(new String(r11, r97, r126, zzci.UTF_8));
        r97 = r110;
        goto L100
    L113:
        throw zzco.zzbp();
    L115:
        if (r16 != 2) goto L124;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        int r98 = zzax.zza(r11, r12, r23);
        int r1211 = r23.zzfd + r98;
        if (r98 < r1211) goto L122;
        if (r98 != r1211) goto L121;
        return r98;
    L121:
        throw zzco.zzbl();
    L122:
        zzax.zzb(r11, r98, r23);
        throw null;
    L124:
        if (r16 != 0) goto L202;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        zzax.zzb(r11, r12, r23);
        long r99 = r23.zzfe;
        throw null;
    L129:
        if (r16 != 2) goto L138;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        int r910 = zzax.zza(r11, r12, r23);
        int r1212 = r23.zzfd + r910;
        if (r910 < r1212) goto L136;
        if (r910 != r1212) goto L135;
        return r910;
    L135:
        throw zzco.zzbl();
    L136:
        zzax.zzc(r11, r910);
        throw null;
    L138:
        if (r16 != 5) goto L202;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        zzax.zzc(r11, r12);
        throw null;
    L143:
        if (r16 != 2) goto L152;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        int r911 = zzax.zza(r11, r12, r23);
        int r1213 = r23.zzfd + r911;
        if (r911 < r1213) goto L150;
        if (r911 != r1213) goto L149;
        return r911;
    L149:
        throw zzco.zzbl();
    L150:
        zzax.zzd(r11, r911);
        throw null;
    L152:
        if (r16 != 1) goto L202;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        zzax.zzd(r11, r12);
        throw null;
    L157:
        if (r16 == 2) goto L159;
        if (r16 != 0) goto L202;
        return zzax.zza(r14, r11, r12, r13, r42, r23);
    L159:
        return zzax.zza(r11, r12, r42, r23);
    L164:
        if (r16 != 2) goto L173;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        int r912 = zzax.zza(r11, r12, r23);
        int r1214 = r23.zzfd + r912;
        if (r912 < r1214) goto L171;
        if (r912 != r1214) goto L170;
        return r912;
    L170:
        throw zzco.zzbl();
    L171:
        zzax.zzb(r11, r912, r23);
        throw null;
    L173:
        if (r16 != 0) goto L202;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        zzax.zzb(r11, r12, r23);
        long r913 = r23.zzfe;
        throw null;
    L178:
        if (r16 != 2) goto L187;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        int r914 = zzax.zza(r11, r12, r23);
        int r1215 = r23.zzfd + r914;
        if (r914 < r1215) goto L185;
        if (r914 != r1215) goto L184;
        return r914;
    L184:
        throw zzco.zzbl();
    L185:
        zzax.zzf(r11, r914);
        throw null;
    L187:
        if (r16 != 5) goto L202;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        zzax.zzf(r11, r12);
        throw null;
    L192:
        if (r16 != 2) goto L201;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        int r915 = zzax.zza(r11, r12, r23);
        int r1216 = r23.zzfd + r915;
        if (r915 < r1216) goto L199;
        if (r915 != r1216) goto L198;
        return r915;
    L198:
        throw zzco.zzbl();
    L199:
        zzax.zze(r11, r915);
        throw null;
    L201:
        if (r16 != 1) goto L202;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42);
        zzax.zze(r11, r12);
        throw null;
    }

    private final <K, V> int zza(T r4, byte[] r5, int r6, int r7, int r8, int r9, long r10, zzay r12) {
        Unsafe r92 = zzmh;
        Object r82 = zzae(r8);
        Object r0 = r92.getObject(r4, r10);
        if (this.zzmz.zzi(r0) == false) goto L5;
        Object r1 = this.zzmz.zzk(r82);
        this.zzmz.zzb(r1, r0);
        r92.putObject(r4, r10, r1);
        r0 = r1;
    L5:
        this.zzmz.zzl(r82);
        this.zzmz.zzg(r0);
        int r3 = zzax.zza(r5, r6, r12);
        int r42 = r12.zzfd;
        if (r42 < 0) goto L13;
        if (r42 > (r7 - r3)) goto L13;
        throw null;
    L13:
        throw zzco.zzbl();
    }

    private final int zza(T r23, byte[] r24, int r25, int r26, int r27, zzay r28) {
        zzds<T> r0 = this;
        T r1 = r23;
        byte[] r2 = r24;
        int r4 = r26;
        zzay r7 = r28;
        Unsafe r8 = zzmh;
        int r9 = -1;
        int r3 = r25;
        int r10 = -1;
        int r5 = 0;
        int r11 = 0;
    L4:
        if (r3 >= r4) goto L142;
        int r52 = r3 + 1;
        int r32 = r2[r3];
        if (r32 >= 0) goto L8;
        r52 = zzax.zza(r32, r2, r52, r7);
        r32 = r7.zzfd;
    L8:
        int r12 = r32;
        r3 = r52;
        int r6 = r12 >>> 3;
        int r72 = r12 & 7;
        int r53 = r0.zzai(r6);
        if (r53 == r9) goto L134;
        int[] r13 = r0.zzmi;
        int r14 = r13[r53 + 1];
        int r92 = (r14 & 267386880) >>> 20;
        long r132 = r14 & 1048575;
        if (r92 <= 17) goto L12;
        Unsafe r33 = r8;
        int r15 = -1;
        int r82 = r4;
        if (r92 == 27) goto L102;
        r3 = r3;
        if (r92 <= 49) goto L115;
        Unsafe r18 = r33;
        int r252 = r10;
        int r19 = r11;
        int r102 = r12;
        if (r92 == 50) goto L124;
        int r62 = zza(r23, r24, r3, r26, r102, r6, r72, r14, r92, r132, r53, r28);
        zzds<T> r73 = this;
        int r22 = r102;
        if (r62 == r3) goto L132;
        r1 = r23;
        r10 = r252;
        r4 = r26;
        r5 = r22;
        r3 = r62;
        r0 = r73;
        r9 = -1;
        r8 = r18;
        r11 = r19;
        r2 = r24;
        r7 = r28;
        goto L4
    L132:
        r10 = r252;
        int r83 = r27;
        r3 = r62;
    L98:
        r11 = r19;
    L136:
        if (r22 != r83) goto L138;
        if (r83 == 0) goto L138;
        T r93 = r23;
        int r122 = r26;
        int r02 = r10;
        int r16 = r11;
        int r112 = r22;
    L140:
        int r103 = r3;
        if (r02 == r15) goto L145;
        r18.putInt(r93, r02, r16);
    L145:
        int[] r133 = r73.zzmt;
        if (r133 == null) goto L160;
        int r142 = r133.length;
        Object r54 = null;
        int r152 = 0;
    L148:
        if (r152 >= r142) goto L157;
        int r17 = r133[r152];
        zzex r63 = r73.zzmx;
        int r29 = r73.zzmi[r17];
        Object r03 = zzfd.zzo(r93, r73.zzag(r17) & 1048575);
        if (r03 == null) goto L154;
        zzck<?> r42 = r73.zzaf(r17);
        if (r42 == null) goto L154;
        Map r34 = r73.zzmz.zzg(r03);
        zzds<T> r04 = r73;
        r54 = r04.zza(r17, r29, r34, r42, r54, r63);
    L156:
        r54 = (zzey) r54;
        r152 = r152 + 1;
        r73 = r04;
    L154:
        r04 = r73;
        goto L156
    L157:
        zzds<T> r05 = r73;
        if (r54 == null) goto L160;
        r05.zzmx.zzf(r93, r54);
    L160:
        if (r83 != 0) goto L165;
        if (r103 != r122) goto L164;
    L167:
        return r103;
    L164:
        throw zzco.zzbo();
    L165:
        if (r103 > r122) goto L169;
        if (r112 == r83) goto L167;
    L169:
        throw zzco.zzbo();
    L138:
        int r06 = r22;
        int r210 = zza(r06, r24, r3, r26, r23, r28);
        r5 = r06;
        r1 = r23;
        r0 = r73;
        r9 = r15;
        r8 = r18;
        r7 = r28;
        r4 = r26;
        r3 = r210;
    L111:
        r2 = r24;
        goto L4
    L124:
        if (r72 != 2) goto L129;
        int r55 = zza(r23, r24, r3, r26, r53, r6, r132, r28);
        if (r55 == r3) goto L127;
        r0 = this;
        r1 = r23;
        r2 = r24;
        r4 = r26;
        r7 = r28;
        r3 = r55;
    L120:
        r5 = r102;
        r9 = -1;
        r8 = r18;
        r11 = r19;
        r10 = r252;
        goto L4
    L127:
        r73 = this;
        r83 = r27;
        r3 = r55;
    L118:
        r22 = r102;
        r11 = r19;
        r10 = r252;
        goto L136
    L129:
        r73 = this;
        r22 = r102;
    L135:
        r10 = r252;
        r83 = r27;
        goto L98
    L115:
        r19 = r11;
        r18 = r33;
        r252 = r10;
        int r64 = r0.zza(r23, r24, r3, r26, r12, r6, r72, r53, r14, r92, r132, r28);
        r102 = r12;
        if (r64 == r3) goto L117;
        r0 = this;
        r1 = r23;
        r2 = r24;
        r4 = r26;
        r7 = r28;
        r3 = r64;
        goto L120
    L117:
        r73 = this;
        r83 = r27;
        r3 = r64;
        goto L118
    L102:
        if (r72 != 2) goto L112;
        zzcn r211 = (zzcn) r33.getObject(r1, r132);
        if (r211.zzu() == true) goto L110;
        int r65 = r211.size();
        if (r65 != 0) goto L108;
        int r66 = 10;
    L109:
        r211 = r211.zzi(r66);
        r33.putObject(r1, r132, r211);
        goto L110
    L108:
        r66 = r65 << 1;
    L110:
        r7 = r28;
        r8 = r33;
        r4 = r26;
        r3 = zza(r0.zzad(r53), r12, r24, r3, r82, r211, r7);
        r5 = r12;
        r9 = -1;
        r1 = r23;
        goto L111
    L112:
        r73 = r0;
        r22 = r12;
        r18 = r33;
        r252 = r10;
        r3 = r3;
        r19 = r11;
        goto L135
    L12:
        int r212 = r13[r53 + 2];
        int r182 = 1 << (r212 >>> 20);
        int r213 = r212 & 1048575;
        r15 = -1;
        if (r213 == r10) goto L18;
        if (r10 == (-1)) goto L16;
        r8.putInt(r1, r10, r11);
    L16:
        r10 = r213;
        r11 = r8.getInt(r1, r213);
    L18:
        switch(r92) {
            case 0: goto L94;
            case 1: goto L90;
            case 2: goto L87;
            case 3: goto L87;
            case 4: goto L84;
            case 5: goto L81;
            case 6: goto L78;
            case 7: goto L71;
            case 8: goto L65;
            case 9: goto L59;
            case 10: goto L54;
            case 11: goto L84;
            case 12: goto L43;
            case 13: goto L78;
            case 14: goto L81;
            case 15: goto L39;
            case 16: goto L31;
            case 17: goto L22;
            default: goto L20;
        };
    L20:
        Unsafe r35 = r8;
        int r143 = r10;
        int r43 = r12;
        int r104 = r3;
        r19 = r11;
    L97:
        r83 = r27;
        r73 = r0;
        r18 = r35;
        r22 = r43;
        r3 = r104;
        r10 = r143;
        goto L98
    L31:
        int r44 = r3;
        if (r72 != 0) goto L35;
        int r74 = zzax.zzb(r24, r44, r28);
        zzay r134 = r28;
        T r214 = r1;
        Unsafe r110 = r8;
        r110.putLong(r214, r132, zzbk.zza(r28.zzfe));
        Unsafe r36 = r110;
        r1 = r214;
        r11 = r11 | r182;
        r4 = r26;
        r2 = r24;
        r5 = r12;
    L34:
        r9 = -1;
        r8 = r36;
        r3 = r74;
        r7 = r134;
        goto L4
    L35:
        r35 = r8;
    L36:
        r143 = r10;
        r19 = r11;
    L37:
        r104 = r44;
    L38:
        r43 = r12;
        goto L97
    L87:
        r35 = r8;
        r134 = r28;
        r143 = r10;
        r104 = r3;
        r19 = r11;
        if (r72 != 0) goto L38;
        r74 = zzax.zzb(r24, r104, r134);
        T r215 = r1;
        r35.putLong(r215, r132, r134.zzfe);
        r36 = r35;
        r1 = r215;
        r11 = r19 | r182;
        r4 = r26;
        r2 = r24;
        r5 = r12;
        r10 = r143;
        goto L34
    L22:
        if (r72 != 3) goto L20;
        int r216 = zza(r0.zzad(r53), r24, r3, r26, (r6 << 3) | 4, r28);
        byte[] r94 = r24;
        if ((r11 & r182) != 0) goto L27;
        Object r45 = r28.zzff;
    L26:
        r8.putObject(r1, r132, r45);
        r11 = r11 | r182;
        r4 = r26;
        r7 = r28;
        r5 = r12;
    L29:
        r3 = r216;
        r2 = r94;
        r9 = -1;
        goto L4
    L27:
        r45 = zzci.zza(r8.getObject(r1, r132), r28.zzff);
        goto L26
    L39:
        r94 = r24;
        r35 = r8;
        r44 = r3;
        if (r72 != 0) goto L36;
        r216 = zzax.zza(r94, r44, r28);
        r35.putInt(r1, r132, zzbk.zzm(r28.zzfd));
        r11 = r11 | r182;
        r4 = r26;
        r5 = r12;
        r7 = r28;
    L42:
        r8 = r35;
        goto L29
    L43:
        r94 = r24;
        r35 = r8;
        r44 = r3;
        int r84 = r26;
        r19 = r11;
        zzay r135 = r28;
        r143 = r10;
        if (r72 != 0) goto L37;
        r216 = zzax.zza(r94, r44, r135);
        int r46 = r135.zzfd;
        zzck<?> r56 = r0.zzaf(r53);
        if (r56 != null) goto L48;
    L51:
        r35.putInt(r1, r132, r46);
    L52:
        r11 = r19 | r182;
        r4 = r84;
        r5 = r12;
        r7 = r135;
    L53:
        r10 = r143;
        goto L42
    L48:
        if (r56.zzb(r46) != null) goto L51;
        zzn(r1).zzb(r12, Long.valueOf(r46));
        r4 = r84;
        r5 = r12;
        r7 = r135;
        r10 = r143;
        r11 = r19;
        goto L42
    L54:
        r94 = r24;
        r35 = r8;
        r44 = r3;
        r84 = r26;
        r19 = r11;
        r135 = r28;
        r143 = r10;
        long r105 = r132;
        if (r72 != 2) goto L37;
        r216 = zzax.zze(r94, r44, r135);
    L57:
        Object r47 = r135.zzff;
    L58:
        r35.putObject(r1, r105, r47);
        goto L52
    L59:
        r94 = r24;
        r35 = r8;
        r44 = r3;
        r84 = r26;
        r19 = r11;
        r135 = r28;
        r143 = r10;
        r105 = r132;
        if (r72 != 2) goto L37;
        r216 = zza(r0.zzad(r53), r94, r44, r84, r135);
        if ((r19 & r182) != 0) goto L64;
        r47 = r135.zzff;
        goto L58
    L64:
        r47 = zzci.zza(r35.getObject(r1, r105), r135.zzff);
        goto L58
    L65:
        r94 = r24;
        r35 = r8;
        r44 = r3;
        r84 = r26;
        r19 = r11;
        r135 = r28;
        r143 = r10;
        r105 = r132;
        if (r72 != 2) goto L37;
        if ((r14 & 536870912) != 0) goto L70;
        r216 = zzax.zzc(r94, r44, r135);
        goto L57
    L70:
        r216 = zzax.zzd(r94, r44, r135);
        goto L57
    L71:
        r94 = r24;
        r35 = r8;
        r44 = r3;
        r84 = r26;
        r19 = r11;
        r135 = r28;
        r143 = r10;
        if (r72 != 0) goto L37;
        r216 = zzax.zzb(r94, r44, r135);
        if (r135.zzfe == 0) goto L76;
        boolean r48 = true;
    L77:
        zzfd.zza(r1, r132, r48);
        goto L52
    L76:
        r48 = false;
        goto L77
    L78:
        r94 = r24;
        r35 = r8;
        r44 = r3;
        r84 = r26;
        r19 = r11;
        r135 = r28;
        r143 = r10;
        if (r72 != 5) goto L37;
        r35.putInt(r1, r132, zzax.zzc(r94, r44));
        r216 = r44 + 4;
        goto L52
    L81:
        r94 = r24;
        r35 = r8;
        r44 = r3;
        r84 = r26;
        r19 = r11;
        r135 = r28;
        r143 = r10;
        if (r72 != 1) goto L37;
        T r217 = r1;
        r35.putLong(r217, r132, zzax.zzd(r94, r44));
        r35 = r35;
        r1 = r217;
        r216 = r44 + 8;
        goto L52
    L84:
        r94 = r24;
        r35 = r8;
        r84 = r26;
        r135 = r28;
        r143 = r10;
        r104 = r3;
        r19 = r11;
        if (r72 != 0) goto L38;
        r216 = zzax.zza(r94, r104, r135);
        r35.putInt(r1, r132, r135.zzfd);
        goto L52
    L90:
        r94 = r24;
        r35 = r8;
        r43 = r12;
        int r85 = r26;
        r143 = r10;
        r104 = r3;
        r19 = r11;
        if (r72 != 5) goto L97;
        zzfd.zza(r1, r132, zzax.zzf(r94, r104));
        r216 = r104 + 4;
    L93:
        r11 = r19 | r182;
        r7 = r28;
        r5 = r43;
        r4 = r85;
        goto L53
    L94:
        r94 = r24;
        r35 = r8;
        r43 = r12;
        r85 = r26;
        r143 = r10;
        r104 = r3;
        r19 = r11;
        if (r72 != 1) goto L97;
        zzfd.zza(r1, r132, zzax.zze(r94, r104));
        r216 = r104 + 8;
        goto L93
    L134:
        r73 = r0;
        r18 = r8;
        r15 = r9;
        r252 = r10;
        r19 = r11;
        r22 = r12;
        goto L135
    L142:
        r73 = r0;
        r122 = r4;
        r18 = r8;
        r15 = r9;
        int r192 = r11;
        r83 = r27;
        r93 = r1;
        r02 = r10;
        r112 = r5;
        r16 = r192;
        goto L140
    }

    public static <T> zzds<T> zza(Class<T> r23, zzdm r24, zzdw r25, zzcy r26, zzex<?, ?> r27, zzbu<?> r28, zzdj r29) {
        int[] r2 = null;
        if ((r24 instanceof zzec) == false) goto L73;
        zzec r0 = (zzec) r24;
        if (r0.zzcf() != zzcg.zzg.zzkm) goto L7;
        boolean r13 = true;
    L9:
        if (r0.getFieldCount() != 0) goto L11;
        int r6 = 0;
        int r9 = 0;
        int r10 = 0;
    L12:
        int[] r7 = new int[r6 << 2];
        Object[] r8 = new Object[r6 << 1];
        if (r0.zzcr() <= 0) goto L15;
        int[] r16 = new int[r0.zzcr()];
    L17:
        if (r0.zzcs() <= 0) goto L19;
        r2 = new int[r0.zzcs()];
    L19:
        int[] r17 = r2;
        zzed r1 = r0.zzco();
        if (r1.next() == false) goto L72;
        int r22 = r1.zzcx();
        int r3 = 0;
        int r62 = 0;
        int r11 = 0;
    L23:
        if (r22 >= r0.zzcv()) goto L31;
        if (r3 >= ((r22 - r9) << 2)) goto L31;
        int r12 = 0;
    L28:
        if (r12 >= 4) goto L70;
        r7[r3 + r12] = -1;
        r12 = r12 + 1;
    L70:
        r3 = r3 + 4;
    L31:
        if (r1.zzda() == false) goto L33;
        int r210 = (int) zzfd.zza(r1.zzdb());
        int r122 = (int) zzfd.zza(r1.zzdc());
        int r14 = 0;
    L37:
        r7[r3] = r1.zzcx();
        int r15 = r3 + 1;
        if (r1.zzdi() == false) goto L40;
        int r18 = 536870912;
    L42:
        if (r1.zzdh() == false) goto L44;
        int r19 = 268435456;
    L45:
        r7[r15] = ((r18 | r19) | (r1.zzcy() << 20)) | r210;
        r7[r3 + 2] = r122 | (r14 << 20);
        if (r1.zzdl() == null) goto L54;
        int r211 = (r3 / 4) << 1;
        r8[r211] = r1.zzdl();
        if (r1.zzdj() == null) goto L51;
        r8[r211 + 1] = r1.zzdj();
    L59:
        int r212 = r1.zzcy();
        if (r212 != zzcb.zziw.ordinal()) goto L63;
        r16[r62] = r3;
        r62 = r62 + 1;
    L68:
        if (r1.next() == false) goto L72;
        r22 = r1.zzcx();
        goto L70
    L63:
        if (r212 < 18) goto L68;
        if (r212 > 49) goto L68;
        r17[r11] = r7[r15] & 1048575;
        r11 = r11 + 1;
        goto L68
    L51:
        if (r1.zzdk() == null) goto L59;
        r8[r211 + 1] = r1.zzdk();
        goto L59
    L54:
        if (r1.zzdj() == null) goto L57;
        r8[((r3 / 4) << 1) + 1] = r1.zzdj();
        goto L59
    L57:
        if (r1.zzdk() == null) goto L59;
        r8[((r3 / 4) << 1) + 1] = r1.zzdk();
        goto L59
    L44:
        r19 = 0;
        goto L45
    L40:
        r18 = 0;
        goto L42
    L33:
        r210 = (int) zzfd.zza(r1.zzdd());
        if (r1.zzde() == false) goto L36;
        r122 = (int) zzfd.zza(r1.zzdf());
        r14 = r1.zzdg();
        goto L37
    L36:
        r122 = 0;
        r14 = 0;
    L72:
        return new zzds(r7, r8, r9, r10, r0.zzcv(), r0.zzch(), r13, false, r0.zzct(), r16, r17, r25, r26, r27, r28, r29);
    L15:
        r16 = null;
        goto L17
    L11:
        int r110 = r0.zzcp();
        int r32 = r0.zzcq();
        r6 = r0.zzcu();
        r9 = r110;
        r10 = r32;
        goto L12
    L7:
        r13 = false;
        goto L9
    L73:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r24);
        throw null;
    }

    private final <K, V, UT, UB> UB zza(int r4, int r5, Map<K, V> r6, zzck<?> r7, UB r8, zzex<UT, UB> r9) {
        this.zzmz.zzl(zzae(r4));
        Iterator<Map.Entry<K, V>> r3 = r6.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L15;
        Map.Entry<K, V> r42 = r3.next();
        if (r7.zzb(((Integer) r42.getValue()).intValue()) != null) goto L4;
        if (r8 != null) goto L9;
        r8 = r9.zzdz();
    L9:
        zzbg r62 = zzbb.zzk(zzdg.zza(null, r42.getKey(), r42.getValue()));
        zzbn r0 = r62.zzae();
        zzdg.zza(r0, null, r42.getKey(), r42.getValue());     // Catch: IOException -> L12
        UB r82 = r8;
        r9.zza(r82, r5, r62.zzad());
        r3.remove();
    L12:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L15:
        return r8;
    }

    private static void zza(int r1, Object r2, zzfr r3) {
        if ((r2 instanceof String) == false) goto L6;
        r3.zza(r1, (String) r2);
        return;
    L6:
        r3.zza(r1, (zzbb) r2);
    }

    private static <UT, UB> void zza(zzex<UT, UB> r0, T r1, zzfr r2) {
        r0.zza(r0.zzq(r1), r2);
    }

    private final <K, V> void zza(zzfr r2, int r3, Object r4, int r5) {
        if (r4 == null) goto L5;
        this.zzmz.zzl(zzae(r5));
        r2.zza(r3, null, this.zzmz.zzh(r4));
        return;
    }

    private final void zza(T r4, T r5, int r6) {
        long r0 = zzag(r6) & 1048575;
        if (zza(r5, r6) == false) goto L14;
        Object r2 = zzfd.zzo(r4, r0);
        Object r52 = zzfd.zzo(r5, r0);
        if (r2 == null) goto L11;
        if (r52 == null) goto L11;
        r52 = zzci.zza(r2, r52);
    L9:
        zzfd.zza(r4, r0, r52);
        zzb(r4, r6);
        return;
    L11:
        if (r52 != null) goto L9;
        return;
    }

    private final boolean zza(T r7, int r8) {
        if (this.zzmq == false) goto L89;
        int r6 = zzag(r8);
        long r0 = r6 & 1048575;
        switch(((r6 & 267386880) >>> 20)) {
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
        Object r62 = zzfd.zzo(r7, r0);
        if ((r62 instanceof String) == false) goto L51;
        if (((String) r62).isEmpty() == true) goto L49;
        return true;
    L49:
        return false;
    L51:
        if ((r62 instanceof zzbb) == true) goto L53;
        Segment$$ExternalSyntheticBUOutline0.m();
        return false;
    L53:
        if (zzbb.zzfi.equals(r62) == true) goto L55;
        return true;
    L55:
        return false;
    L9:
        if (zzfd.zzo(r7, r0) == null) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (zzfd.zzk(r7, r0) == 0) goto L15;
        return true;
    L15:
        return false;
    L17:
        if (zzfd.zzj(r7, r0) == 0) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (zzfd.zzk(r7, r0) == 0) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (zzfd.zzj(r7, r0) == 0) goto L27;
        return true;
    L27:
        return false;
    L29:
        if (zzfd.zzj(r7, r0) == 0) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (zzfd.zzj(r7, r0) == 0) goto L35;
        return true;
    L35:
        return false;
    L37:
        if (zzbb.zzfi.equals(zzfd.zzo(r7, r0)) == true) goto L39;
        return true;
    L39:
        return false;
    L41:
        if (zzfd.zzo(r7, r0) == null) goto L43;
        return true;
    L43:
        return false;
    L59:
        return zzfd.zzl(r7, r0);
    L61:
        if (zzfd.zzj(r7, r0) == 0) goto L63;
        return true;
    L63:
        return false;
    L65:
        if (zzfd.zzk(r7, r0) == 0) goto L67;
        return true;
    L67:
        return false;
    L69:
        if (zzfd.zzj(r7, r0) == 0) goto L71;
        return true;
    L71:
        return false;
    L73:
        if (zzfd.zzk(r7, r0) == 0) goto L75;
        return true;
    L75:
        return false;
    L77:
        if (zzfd.zzk(r7, r0) == 0) goto L79;
        return true;
    L79:
        return false;
    L81:
        if (zzfd.zzm(r7, r0) == 0.0f) goto L83;
        return true;
    L83:
        return false;
    L85:
        if (zzfd.zzn(r7, r0) == 0.0d) goto L87;
        return true;
    L87:
        return false;
    L89:
        if ((zzfd.zzj(r7, r6 & 1048575) & (1 << (zzah(r8) >>> 20))) == 0) goto L91;
        return true;
    L91:
        return false;
    }

    private final boolean zza(T r3, int r4, int r5) {
        if (zzfd.zzj(r3, zzah(r5) & 1048575) != r4) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean zza(T r2, int r3, int r4, int r5) {
        if (this.zzmq == false) goto L7;
        return zza(r2, r3);
    L7:
        if ((r4 & r5) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object r2, int r3, zzef r4) {
        return r4.zzo(zzfd.zzo(r2, r3 & 1048575));
    }

    private final zzef zzad(int r4) {
        int r42 = (r4 / 4) << 1;
        zzef r0 = (zzef) this.zzmj[r42];
        if (r0 == null) goto L5;
        return r0;
    L5:
        zzef<T> r02 = zzea.zzcm().zze((Class) this.zzmj[r42 + 1]);
        this.zzmj[r42] = r02;
        return r02;
    }

    private final Object zzae(int r1) {
        return this.zzmj[(r1 / 4) << 1];
    }

    private final zzck<?> zzaf(int r1) {
        return (zzck) this.zzmj[((r1 / 4) << 1) + 1];
    }

    private final int zzag(int r1) {
        return this.zzmi[r1 + 1];
    }

    private final int zzah(int r1) {
        return this.zzmi[r1 + 2];
    }

    private final int zzai(int r7) {
        int r0 = this.zzmk;
        if (r7 < r0) goto L20;
        int r2 = this.zzmm;
        if (r7 >= r2) goto L11;
        int r02 = (r7 - r0) << 2;
        if (this.zzmi[r02] != r7) goto L9;
        return r02;
    L9:
        return -1;
    L11:
        if (r7 > this.zzml) goto L20;
        int r22 = r2 - r0;
        int r03 = (this.zzmi.length / 4) - 1;
    L13:
        if (r22 > r03) goto L20;
        int r3 = (r03 + r22) >>> 1;
        int r4 = r3 << 2;
        int r5 = this.zzmi[r4];
        if (r7 == r5) goto L16;
        if (r7 < r5) goto L18;
        r22 = r3 + 1;
        goto L13
    L18:
        r03 = r3 - 1;
        goto L13
    L16:
        return r4;
    L20:
        return -1;
    }

    private final void zzb(T r3, int r4) {
        if (this.zzmq == false) goto L5;
        return;
    L5:
        int r2 = zzah(r4);
        long r0 = r2 & 1048575;
        zzfd.zza(r3, r0, zzfd.zzj(r3, r0) | (1 << (r2 >>> 20)));
    }

    private final void zzb(T r3, int r4, int r5) {
        zzfd.zza(r3, zzah(r5) & 1048575, r4);
    }

    private final void zzb(T r19, zzfr r20) {
        if (this.zzmo == false) goto L7;
        zzby<?> r3 = this.zzmy.zza(r19);
        if (r3.isEmpty() == true) goto L7;
        Iterator<Map.Entry<?, Object>> r32 = r3.iterator();
        Map.Entry<?, ?> r5 = (Map.Entry) r32.next();
    L8:
        int r6 = this.zzmi.length;
        Unsafe r7 = zzmh;
        int r9 = -1;
        int r10 = 0;
        int r11 = 0;
    L9:
        if (r10 >= r6) goto L172;
        int r12 = zzag(r10);
        int[] r13 = this.zzmi;
        int r14 = r13[r10];
        int r15 = (267386880 & r12) >>> 20;
        if (this.zzmq == false) goto L13;
    L18:
        boolean r17 = true;
        int r4 = 0;
    L19:
        if (r5 == null) goto L26;
        if (this.zzmy.zza(r5) > r14) goto L26;
        this.zzmy.zza(r20, r5);
        if (r32.hasNext() == true) goto L24;
        r5 = null;
        goto L19
    L24:
        r5 = (Map.Entry) r32.next();
    L26:
        long r122 = r12 & 1048575;
        switch(r15) {
            case 0: goto L169;
            case 1: goto L166;
            case 2: goto L163;
            case 3: goto L160;
            case 4: goto L157;
            case 5: goto L154;
            case 6: goto L151;
            case 7: goto L148;
            case 8: goto L145;
            case 9: goto L142;
            case 10: goto L139;
            case 11: goto L136;
            case 12: goto L133;
            case 13: goto L130;
            case 14: goto L127;
            case 15: goto L124;
            case 16: goto L121;
            case 17: goto L118;
            case 18: goto L116;
            case 19: goto L115;
            case 20: goto L114;
            case 21: goto L113;
            case 22: goto L112;
            case 23: goto L111;
            case 24: goto L110;
            case 25: goto L109;
            case 26: goto L108;
            case 27: goto L107;
            case 28: goto L106;
            case 29: goto L105;
            case 30: goto L104;
            case 31: goto L103;
            case 32: goto L102;
            case 33: goto L101;
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
            case 49: goto L84;
            case 50: goto L83;
            case 51: goto L81;
            case 52: goto L78;
            case 53: goto L75;
            case 54: goto L72;
            case 55: goto L69;
            case 56: goto L66;
            case 57: goto L63;
            case 58: goto L60;
            case 59: goto L57;
            case 60: goto L54;
            case 61: goto L51;
            case 62: goto L48;
            case 63: goto L45;
            case 64: goto L42;
            case 65: goto L39;
            case 66: goto L36;
            case 67: goto L33;
            case 68: goto L30;
            default: goto L171;
        };
    L83:
        zza(r20, r14, r7.getObject(r19, r122), r10);
        goto L171
    L84:
        zzeh.zzb(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, zzad(r10));
        goto L171
    L85:
        zzeh.zze(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L86:
        zzeh.zzj(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L87:
        zzeh.zzg(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L88:
        zzeh.zzl(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L89:
        zzeh.zzm(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L90:
        zzeh.zzi(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L91:
        zzeh.zzn(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L92:
        zzeh.zzk(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L93:
        zzeh.zzf(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L94:
        zzeh.zzh(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L95:
        zzeh.zzd(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L96:
        zzeh.zzc(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L97:
        zzeh.zzb(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L98:
        zzeh.zza(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, r17);
        goto L171
    L99:
        boolean r142 = false;
        zzeh.zze(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L101:
        r142 = false;
        zzeh.zzj(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L102:
        r142 = false;
        zzeh.zzg(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L103:
        r142 = false;
        zzeh.zzl(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L104:
        r142 = false;
        zzeh.zzm(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L105:
        r142 = false;
        zzeh.zzi(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L106:
        zzeh.zzb(this.zzmi[r10], (List) r7.getObject(r19, r122), r20);
        goto L171
    L107:
        zzeh.zza(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, zzad(r10));
        goto L171
    L108:
        zzeh.zza(this.zzmi[r10], (List) r7.getObject(r19, r122), r20);
        goto L171
    L109:
        zzeh.zzn(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L110:
        zzeh.zzk(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L111:
        zzeh.zzf(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L112:
        zzeh.zzh(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L113:
        zzeh.zzd(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L114:
        zzeh.zzc(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L115:
        zzeh.zzb(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
        goto L171
    L116:
        zzeh.zza(this.zzmi[r10], (List) r7.getObject(r19, r122), r20, false);
    L171:
        r10 = r10 + 4;
        goto L9
    L30:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zzb(r14, r7.getObject(r19, r122), zzad(r10));
        goto L171
    L33:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zzb(r14, zzh(r19, r122));
        goto L171
    L36:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zze(r14, zzg(r19, r122));
        goto L171
    L39:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zzj(r14, zzh(r19, r122));
        goto L171
    L42:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zzm(r14, zzg(r19, r122));
        goto L171
    L45:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zzn(r14, zzg(r19, r122));
        goto L171
    L48:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zzd(r14, zzg(r19, r122));
        goto L171
    L51:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zza(r14, (zzbb) r7.getObject(r19, r122));
        goto L171
    L54:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zza(r14, r7.getObject(r19, r122), zzad(r10));
        goto L171
    L57:
        if (zza(r19, r14, r10) == false) goto L171;
        zza(r14, r7.getObject(r19, r122), r20);
        goto L171
    L60:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zzb(r14, zzi(r19, r122));
        goto L171
    L63:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zzf(r14, zzg(r19, r122));
        goto L171
    L66:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zzc(r14, zzh(r19, r122));
        goto L171
    L69:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zzc(r14, zzg(r19, r122));
        goto L171
    L72:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zza(r14, zzh(r19, r122));
        goto L171
    L75:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zzi(r14, zzh(r19, r122));
        goto L171
    L78:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zza(r14, zzf(r19, r122));
        goto L171
    L81:
        if (zza(r19, r14, r10) == false) goto L171;
        r20.zza(r14, zze(r19, r122));
        goto L171
    L118:
        if ((r4 & r11) == 0) goto L171;
        r20.zzb(r14, r7.getObject(r19, r122), zzad(r10));
        goto L171
    L121:
        if ((r4 & r11) == 0) goto L171;
        r20.zzb(r14, r7.getLong(r19, r122));
        goto L171
    L124:
        if ((r4 & r11) == 0) goto L171;
        r20.zze(r14, r7.getInt(r19, r122));
        goto L171
    L127:
        if ((r4 & r11) == 0) goto L171;
        r20.zzj(r14, r7.getLong(r19, r122));
        goto L171
    L130:
        if ((r4 & r11) == 0) goto L171;
        r20.zzm(r14, r7.getInt(r19, r122));
        goto L171
    L133:
        if ((r4 & r11) == 0) goto L171;
        r20.zzn(r14, r7.getInt(r19, r122));
        goto L171
    L136:
        if ((r4 & r11) == 0) goto L171;
        r20.zzd(r14, r7.getInt(r19, r122));
        goto L171
    L139:
        if ((r4 & r11) == 0) goto L171;
        r20.zza(r14, (zzbb) r7.getObject(r19, r122));
        goto L171
    L142:
        if ((r4 & r11) == 0) goto L171;
        r20.zza(r14, r7.getObject(r19, r122), zzad(r10));
        goto L171
    L145:
        if ((r4 & r11) == 0) goto L171;
        zza(r14, r7.getObject(r19, r122), r20);
        goto L171
    L148:
        if ((r4 & r11) == 0) goto L171;
        r20.zzb(r14, zzfd.zzl(r19, r122));
        goto L171
    L151:
        if ((r4 & r11) == 0) goto L171;
        r20.zzf(r14, r7.getInt(r19, r122));
        goto L171
    L154:
        if ((r4 & r11) == 0) goto L171;
        r20.zzc(r14, r7.getLong(r19, r122));
        goto L171
    L157:
        if ((r4 & r11) == 0) goto L171;
        r20.zzc(r14, r7.getInt(r19, r122));
        goto L171
    L160:
        if ((r4 & r11) == 0) goto L171;
        r20.zza(r14, r7.getLong(r19, r122));
        goto L171
    L163:
        if ((r4 & r11) == 0) goto L171;
        r20.zzi(r14, r7.getLong(r19, r122));
        goto L171
    L166:
        if ((r4 & r11) == 0) goto L171;
        r20.zza(r14, zzfd.zzm(r19, r122));
        goto L171
    L169:
        if ((r4 & r11) == 0) goto L171;
        r20.zza(r14, zzfd.zzn(r19, r122));
        goto L171
    L13:
        if (r15 > 17) goto L18;
        int r42 = r13[r10 + 2];
        int r132 = r42 & 1048575;
        r17 = true;
        if (r132 == r9) goto L17;
        r11 = r7.getInt(r19, r132);
        r9 = r132;
    L17:
        r4 = 1 << (r42 >>> 20);
    L172:
        if (r5 == null) goto L177;
        this.zzmy.zza(r20, r5);
        if (r32.hasNext() == true) goto L175;
        r5 = null;
        goto L172
    L175:
        r5 = (Map.Entry) r32.next();
        goto L172
    L177:
        zza(this.zzmx, r19, r20);
        return;
    L7:
        r32 = null;
        r5 = null;
        goto L8
    }

    private final void zzb(T r5, T r6, int r7) {
        int r0 = zzag(r7);
        int r1 = this.zzmi[r7];
        long r2 = r0 & 1048575;
        if (zza(r6, r1, r7) == false) goto L14;
        Object r02 = zzfd.zzo(r5, r2);
        Object r62 = zzfd.zzo(r6, r2);
        if (r02 == null) goto L11;
        if (r62 == null) goto L11;
        r62 = zzci.zza(r02, r62);
    L9:
        zzfd.zza(r5, r2, r62);
        zzb(r5, r1, r7);
        return;
    L11:
        if (r62 != null) goto L9;
        return;
    }

    private final boolean zzc(T r1, T r2, int r3) {
        if (zza(r1, r3) != zza(r2, r3)) goto L6;
        return true;
    L6:
        return false;
    }

    private static <E> List<E> zzd(Object r0, long r1) {
        return (List) zzfd.zzo(r0, r1);
    }

    private static <T> double zze(T r0, long r1) {
        return ((Double) zzfd.zzo(r0, r1)).doubleValue();
    }

    private static <T> float zzf(T r0, long r1) {
        return ((Float) zzfd.zzo(r0, r1)).floatValue();
    }

    private static <T> int zzg(T r0, long r1) {
        return ((Integer) zzfd.zzo(r0, r1)).intValue();
    }

    private static <T> long zzh(T r0, long r1) {
        return ((Long) zzfd.zzo(r0, r1)).longValue();
    }

    private static <T> boolean zzi(T r0, long r1) {
        return ((Boolean) zzfd.zzo(r0, r1)).booleanValue();
    }

    private static zzey zzn(Object r2) {
        zzcg r22 = (zzcg) r2;
        zzey r0 = r22.zzjp;
        if (r0 != zzey.zzea()) goto L6;
        zzey r02 = zzey.zzeb();
        r22.zzjp = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final boolean equals(T r10, T r11) {
        int r0 = this.zzmi.length;
        int r2 = 0;
    L3:
        boolean r3 = true;
        if (r2 >= r0) goto L108;
        int r4 = zzag(r2);
        long r6 = r4 & 1048575;
        switch(((r4 & 267386880) >>> 20)) {
            case 0: goto L100;
            case 1: goto L95;
            case 2: goto L90;
            case 3: goto L85;
            case 4: goto L80;
            case 5: goto L75;
            case 6: goto L70;
            case 7: goto L65;
            case 8: goto L60;
            case 9: goto L55;
            case 10: goto L50;
            case 11: goto L45;
            case 12: goto L40;
            case 13: goto L35;
            case 14: goto L30;
            case 15: goto L25;
            case 16: goto L20;
            case 17: goto L15;
            case 18: goto L13;
            case 19: goto L13;
            case 20: goto L13;
            case 21: goto L13;
            case 22: goto L13;
            case 23: goto L13;
            case 24: goto L13;
            case 25: goto L13;
            case 26: goto L13;
            case 27: goto L13;
            case 28: goto L13;
            case 29: goto L13;
            case 30: goto L13;
            case 31: goto L13;
            case 32: goto L13;
            case 33: goto L13;
            case 34: goto L13;
            case 35: goto L13;
            case 36: goto L13;
            case 37: goto L13;
            case 38: goto L13;
            case 39: goto L13;
            case 40: goto L13;
            case 41: goto L13;
            case 42: goto L13;
            case 43: goto L13;
            case 44: goto L13;
            case 45: goto L13;
            case 46: goto L13;
            case 47: goto L13;
            case 48: goto L13;
            case 49: goto L13;
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
            default: goto L104;
        };
    L8:
        long r42 = zzah(r2) & 1048575;
        if (zzfd.zzj(r10, r42) == zzfd.zzj(r11, r42)) goto L11;
    L12:
        r3 = false;
        goto L104
    L11:
        if (zzeh.zzd(zzfd.zzo(r10, r6), zzfd.zzo(r11, r6)) == true) goto L104;
    L13:
        r3 = zzeh.zzd(zzfd.zzo(r10, r6), zzfd.zzo(r11, r6));
    L104:
        if (r3 == false) goto L105;
        r2 = r2 + 4;
        goto L3
    L105:
        return false;
    L15:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzeh.zzd(zzfd.zzo(r10, r6), zzfd.zzo(r11, r6)) == true) goto L104;
    L20:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzk(r10, r6) == zzfd.zzk(r11, r6)) goto L104;
    L25:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzj(r10, r6) == zzfd.zzj(r11, r6)) goto L104;
    L30:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzk(r10, r6) == zzfd.zzk(r11, r6)) goto L104;
    L35:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzj(r10, r6) == zzfd.zzj(r11, r6)) goto L104;
    L40:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzj(r10, r6) == zzfd.zzj(r11, r6)) goto L104;
    L45:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzj(r10, r6) == zzfd.zzj(r11, r6)) goto L104;
    L50:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzeh.zzd(zzfd.zzo(r10, r6), zzfd.zzo(r11, r6)) == true) goto L104;
    L55:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzeh.zzd(zzfd.zzo(r10, r6), zzfd.zzo(r11, r6)) == true) goto L104;
    L60:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzeh.zzd(zzfd.zzo(r10, r6), zzfd.zzo(r11, r6)) == true) goto L104;
    L65:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzl(r10, r6) == zzfd.zzl(r11, r6)) goto L104;
    L70:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzj(r10, r6) == zzfd.zzj(r11, r6)) goto L104;
    L75:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzk(r10, r6) == zzfd.zzk(r11, r6)) goto L104;
    L80:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzj(r10, r6) == zzfd.zzj(r11, r6)) goto L104;
    L85:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzk(r10, r6) == zzfd.zzk(r11, r6)) goto L104;
    L90:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzk(r10, r6) == zzfd.zzk(r11, r6)) goto L104;
    L95:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzj(r10, r6) == zzfd.zzj(r11, r6)) goto L104;
    L100:
        if (zzc(r10, r11, r2) == false) goto L12;
        if (zzfd.zzk(r10, r6) == zzfd.zzk(r11, r6)) goto L104;
    L108:
        if (this.zzmx.zzq(r10).equals(this.zzmx.zzq(r11)) == true) goto L111;
        return false;
    L111:
        if (this.zzmo == true) goto L113;
        return true;
    L113:
        return this.zzmy.zza(r10).equals(this.zzmy.zza(r11));
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final int hashCode(T r9) {
        int r0 = this.zzmi.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L80;
        int r3 = zzag(r1);
        int r4 = this.zzmi[r1];
        long r5 = 1048575 & r3;
        int r7 = 37;
        switch(((r3 & 267386880) >>> 20)) {
            case 0: goto L78;
            case 1: goto L77;
            case 2: goto L71;
            case 3: goto L71;
            case 4: goto L72;
            case 5: goto L71;
            case 6: goto L72;
            case 7: goto L76;
            case 8: goto L39;
            case 9: goto L73;
            case 10: goto L33;
            case 11: goto L72;
            case 12: goto L72;
            case 13: goto L72;
            case 14: goto L71;
            case 15: goto L72;
            case 16: goto L71;
            case 17: goto L67;
            case 18: goto L33;
            case 19: goto L33;
            case 20: goto L33;
            case 21: goto L33;
            case 22: goto L33;
            case 23: goto L33;
            case 24: goto L33;
            case 25: goto L33;
            case 26: goto L33;
            case 27: goto L33;
            case 28: goto L33;
            case 29: goto L33;
            case 30: goto L33;
            case 31: goto L33;
            case 32: goto L33;
            case 33: goto L33;
            case 34: goto L33;
            case 35: goto L33;
            case 36: goto L33;
            case 37: goto L33;
            case 38: goto L33;
            case 39: goto L33;
            case 40: goto L33;
            case 41: goto L33;
            case 42: goto L33;
            case 43: goto L33;
            case 44: goto L33;
            case 45: goto L33;
            case 46: goto L33;
            case 47: goto L33;
            case 48: goto L33;
            case 49: goto L33;
            case 50: goto L33;
            case 51: goto L64;
            case 52: goto L60;
            case 53: goto L57;
            case 54: goto L54;
            case 55: goto L51;
            case 56: goto L48;
            case 57: goto L45;
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
            case 68: goto L8;
            default: goto L79;
        };
    L33:
        int r22 = r2 * 53;
        Object r32 = zzfd.zzo(r9, r5);
    L10:
        int r33 = r32.hashCode();
    L11:
        r2 = r22 + r33;
    L39:
        r22 = r2 * 53;
        r33 = ((String) zzfd.zzo(r9, r5)).hashCode();
        goto L11
    L67:
        Object r34 = zzfd.zzo(r9, r5);
        if (r34 == null) goto L70;
    L69:
        r7 = r34.hashCode();
    L70:
        r2 = (r2 * 53) + r7;
        goto L79
    L71:
        r22 = r2 * 53;
        long r35 = zzfd.zzk(r9, r5);
    L15:
        r33 = zzci.zzl(r35);
        goto L11
    L72:
        r22 = r2 * 53;
        r33 = zzfd.zzj(r9, r5);
        goto L11
    L73:
        r34 = zzfd.zzo(r9, r5);
        if (r34 == null) goto L70;
    L76:
        r22 = r2 * 53;
        boolean r36 = zzfd.zzl(r9, r5);
    L43:
        r33 = zzci.zzc(r36);
        goto L11
    L77:
        r22 = r2 * 53;
        float r37 = zzfd.zzm(r9, r5);
    L62:
        r33 = Float.floatToIntBits(r37);
        goto L11
    L78:
        r22 = r2 * 53;
        double r38 = zzfd.zzn(r9, r5);
    L66:
        r35 = Double.doubleToLongBits(r38);
        goto L15
    L8:
        if (zza(r9, r4, r1) == false) goto L79;
    L9:
        r32 = zzfd.zzo(r9, r5);
        r22 = r2 * 53;
        goto L10
    L13:
        if (zza(r9, r4, r1) == false) goto L79;
    L14:
        r22 = r2 * 53;
        r35 = zzh(r9, r5);
        goto L15
    L17:
        if (zza(r9, r4, r1) == false) goto L79;
    L18:
        r22 = r2 * 53;
        r33 = zzg(r9, r5);
        goto L11
    L20:
        if (zza(r9, r4, r1) == false) goto L79;
    L23:
        if (zza(r9, r4, r1) == false) goto L79;
    L26:
        if (zza(r9, r4, r1) == false) goto L79;
    L29:
        if (zza(r9, r4, r1) == false) goto L79;
    L32:
        if (zza(r9, r4, r1) == false) goto L79;
    L35:
        if (zza(r9, r4, r1) == false) goto L79;
    L38:
        if (zza(r9, r4, r1) == false) goto L79;
    L41:
        if (zza(r9, r4, r1) == false) goto L79;
        r22 = r2 * 53;
        r36 = zzi(r9, r5);
        goto L43
    L45:
        if (zza(r9, r4, r1) == false) goto L79;
    L48:
        if (zza(r9, r4, r1) == false) goto L79;
    L51:
        if (zza(r9, r4, r1) == false) goto L79;
    L54:
        if (zza(r9, r4, r1) == false) goto L79;
    L57:
        if (zza(r9, r4, r1) == false) goto L79;
    L60:
        if (zza(r9, r4, r1) == false) goto L79;
        r22 = r2 * 53;
        r37 = zzf(r9, r5);
        goto L62
    L64:
        if (zza(r9, r4, r1) == false) goto L79;
        r22 = r2 * 53;
        r38 = zze(r9, r5);
    L79:
        r1 = r1 + 4;
        goto L3
    L80:
        int r23 = (r2 * 53) + this.zzmx.zzq(r9).hashCode();
        if (this.zzmo == true) goto L83;
        return r23;
    L83:
        return (r23 * 53) + this.zzmy.zza(r9).hashCode();
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final T newInstance() {
        return (T) this.zzmv.newInstance(this.zzmn);
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zza(T r14, zzfr r15) {
        if (r15.zzaj() != zzcg.zzg.zzkp) goto L186;
        zza(this.zzmx, r14, r15);
        if (this.zzmo == false) goto L9;
        zzby<?> r0 = this.zzmy.zza(r14);
        if (r0.isEmpty() == true) goto L9;
        Iterator<Map.Entry<?, Object>> r02 = r0.descendingIterator();
        Map.Entry<?, ?> r1 = (Map.Entry) r02.next();
    L10:
        int r7 = this.zzmi.length - 4;
    L11:
        if (r7 < 0) goto L179;
        int r8 = zzag(r7);
        int r9 = this.zzmi[r7];
    L13:
        if (r1 == null) goto L21;
        if (this.zzmy.zza(r1) <= r9) goto L21;
        this.zzmy.zza(r15, r1);
        if (r02.hasNext() == true) goto L18;
        r1 = null;
        goto L13
    L18:
        r1 = (Map.Entry) r02.next();
    L21:
        switch(((r8 & 267386880) >>> 20)) {
            case 0: goto L176;
            case 1: goto L173;
            case 2: goto L170;
            case 3: goto L167;
            case 4: goto L164;
            case 5: goto L161;
            case 6: goto L158;
            case 7: goto L155;
            case 8: goto L152;
            case 9: goto L149;
            case 10: goto L146;
            case 11: goto L143;
            case 12: goto L140;
            case 13: goto L137;
            case 14: goto L134;
            case 15: goto L131;
            case 16: goto L128;
            case 17: goto L125;
            case 18: goto L123;
            case 19: goto L122;
            case 20: goto L121;
            case 21: goto L120;
            case 22: goto L119;
            case 23: goto L118;
            case 24: goto L117;
            case 25: goto L116;
            case 26: goto L115;
            case 27: goto L114;
            case 28: goto L113;
            case 29: goto L112;
            case 30: goto L111;
            case 31: goto L110;
            case 32: goto L109;
            case 33: goto L108;
            case 34: goto L107;
            case 35: goto L106;
            case 36: goto L105;
            case 37: goto L104;
            case 38: goto L103;
            case 39: goto L102;
            case 40: goto L101;
            case 41: goto L100;
            case 42: goto L99;
            case 43: goto L98;
            case 44: goto L97;
            case 45: goto L96;
            case 46: goto L95;
            case 47: goto L94;
            case 48: goto L93;
            case 49: goto L92;
            case 50: goto L91;
            case 51: goto L88;
            case 52: goto L84;
            case 53: goto L80;
            case 54: goto L76;
            case 55: goto L72;
            case 56: goto L68;
            case 57: goto L64;
            case 58: goto L60;
            case 59: goto L57;
            case 60: goto L54;
            case 61: goto L51;
            case 62: goto L47;
            case 63: goto L43;
            case 64: goto L39;
            case 65: goto L35;
            case 66: goto L31;
            case 67: goto L27;
            case 68: goto L24;
            default: goto L178;
        };
    L91:
        zza(r15, r9, zzfd.zzo(r14, r8 & 1048575), r7);
        goto L178
    L92:
        zzeh.zzb(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, zzad(r7));
        goto L178
    L93:
        zzeh.zze(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L94:
        zzeh.zzj(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L95:
        zzeh.zzg(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L96:
        zzeh.zzl(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L97:
        zzeh.zzm(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L98:
        zzeh.zzi(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L99:
        zzeh.zzn(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L100:
        zzeh.zzk(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L101:
        zzeh.zzf(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L102:
        zzeh.zzh(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L103:
        zzeh.zzd(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L104:
        zzeh.zzc(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L105:
        zzeh.zzb(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L106:
        zzeh.zza(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, true);
        goto L178
    L107:
        zzeh.zze(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L108:
        zzeh.zzj(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L109:
        zzeh.zzg(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L110:
        zzeh.zzl(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L111:
        zzeh.zzm(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L112:
        zzeh.zzi(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L113:
        zzeh.zzb(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15);
        goto L178
    L114:
        zzeh.zza(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, zzad(r7));
        goto L178
    L115:
        zzeh.zza(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15);
        goto L178
    L116:
        zzeh.zzn(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L117:
        zzeh.zzk(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L118:
        zzeh.zzf(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L119:
        zzeh.zzh(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L120:
        zzeh.zzd(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L121:
        zzeh.zzc(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L122:
        zzeh.zzb(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L123:
        zzeh.zza(this.zzmi[r7], (List) zzfd.zzo(r14, r8 & 1048575), r15, false);
        goto L178
    L24:
        if (zza(r14, r9, r7) == false) goto L178;
    L25:
        r15.zzb(r9, zzfd.zzo(r14, r8 & 1048575), zzad(r7));
        goto L178
    L27:
        if (zza(r14, r9, r7) == false) goto L178;
        long r10 = zzh(r14, r8 & 1048575);
    L29:
        r15.zzb(r9, r10);
        goto L178
    L31:
        if (zza(r14, r9, r7) == false) goto L178;
        int r82 = zzg(r14, r8 & 1048575);
    L33:
        r15.zze(r9, r82);
        goto L178
    L35:
        if (zza(r14, r9, r7) == false) goto L178;
        long r102 = zzh(r14, r8 & 1048575);
    L37:
        r15.zzj(r9, r102);
        goto L178
    L39:
        if (zza(r14, r9, r7) == false) goto L178;
        int r83 = zzg(r14, r8 & 1048575);
    L41:
        r15.zzm(r9, r83);
        goto L178
    L43:
        if (zza(r14, r9, r7) == false) goto L178;
        int r84 = zzg(r14, r8 & 1048575);
    L45:
        r15.zzn(r9, r84);
        goto L178
    L47:
        if (zza(r14, r9, r7) == false) goto L178;
        int r85 = zzg(r14, r8 & 1048575);
    L49:
        r15.zzd(r9, r85);
        goto L178
    L51:
        if (zza(r14, r9, r7) == false) goto L178;
    L52:
        r15.zza(r9, (zzbb) zzfd.zzo(r14, r8 & 1048575));
        goto L178
    L54:
        if (zza(r14, r9, r7) == false) goto L178;
    L55:
        r15.zza(r9, zzfd.zzo(r14, r8 & 1048575), zzad(r7));
        goto L178
    L57:
        if (zza(r14, r9, r7) == false) goto L178;
    L58:
        zza(r9, zzfd.zzo(r14, r8 & 1048575), r15);
        goto L178
    L60:
        if (zza(r14, r9, r7) == false) goto L178;
        boolean r86 = zzi(r14, r8 & 1048575);
    L62:
        r15.zzb(r9, r86);
        goto L178
    L64:
        if (zza(r14, r9, r7) == false) goto L178;
        int r87 = zzg(r14, r8 & 1048575);
    L66:
        r15.zzf(r9, r87);
        goto L178
    L68:
        if (zza(r14, r9, r7) == false) goto L178;
        long r103 = zzh(r14, r8 & 1048575);
    L70:
        r15.zzc(r9, r103);
        goto L178
    L72:
        if (zza(r14, r9, r7) == false) goto L178;
        int r88 = zzg(r14, r8 & 1048575);
    L74:
        r15.zzc(r9, r88);
        goto L178
    L76:
        if (zza(r14, r9, r7) == false) goto L178;
        long r104 = zzh(r14, r8 & 1048575);
    L78:
        r15.zza(r9, r104);
        goto L178
    L80:
        if (zza(r14, r9, r7) == false) goto L178;
        long r105 = zzh(r14, r8 & 1048575);
    L82:
        r15.zzi(r9, r105);
        goto L178
    L84:
        if (zza(r14, r9, r7) == false) goto L178;
        float r89 = zzf(r14, r8 & 1048575);
    L86:
        r15.zza(r9, r89);
        goto L178
    L88:
        if (zza(r14, r9, r7) == false) goto L178;
        double r106 = zze(r14, r8 & 1048575);
    L90:
        r15.zza(r9, r106);
        goto L178
    L125:
        if (zza(r14, r7) == false) goto L178;
    L128:
        if (zza(r14, r7) == false) goto L178;
        r10 = zzfd.zzk(r14, r8 & 1048575);
        goto L29
    L131:
        if (zza(r14, r7) == false) goto L178;
        r82 = zzfd.zzj(r14, r8 & 1048575);
        goto L33
    L134:
        if (zza(r14, r7) == false) goto L178;
        r102 = zzfd.zzk(r14, r8 & 1048575);
        goto L37
    L137:
        if (zza(r14, r7) == false) goto L178;
        r83 = zzfd.zzj(r14, r8 & 1048575);
        goto L41
    L140:
        if (zza(r14, r7) == false) goto L178;
        r84 = zzfd.zzj(r14, r8 & 1048575);
        goto L45
    L143:
        if (zza(r14, r7) == false) goto L178;
        r85 = zzfd.zzj(r14, r8 & 1048575);
        goto L49
    L146:
        if (zza(r14, r7) == false) goto L178;
    L149:
        if (zza(r14, r7) == false) goto L178;
    L152:
        if (zza(r14, r7) == false) goto L178;
    L155:
        if (zza(r14, r7) == false) goto L178;
        r86 = zzfd.zzl(r14, r8 & 1048575);
        goto L62
    L158:
        if (zza(r14, r7) == false) goto L178;
        r87 = zzfd.zzj(r14, r8 & 1048575);
        goto L66
    L161:
        if (zza(r14, r7) == false) goto L178;
        r103 = zzfd.zzk(r14, r8 & 1048575);
        goto L70
    L164:
        if (zza(r14, r7) == false) goto L178;
        r88 = zzfd.zzj(r14, r8 & 1048575);
        goto L74
    L167:
        if (zza(r14, r7) == false) goto L178;
        r104 = zzfd.zzk(r14, r8 & 1048575);
        goto L78
    L170:
        if (zza(r14, r7) == false) goto L178;
        r105 = zzfd.zzk(r14, r8 & 1048575);
        goto L82
    L173:
        if (zza(r14, r7) == false) goto L178;
        r89 = zzfd.zzm(r14, r8 & 1048575);
        goto L86
    L176:
        if (zza(r14, r7) == false) goto L178;
        r106 = zzfd.zzn(r14, r8 & 1048575);
    L178:
        r7 = r7 - 4;
    L179:
        if (r1 == null) goto L184;
        this.zzmy.zza(r15, r1);
        if (r02.hasNext() == true) goto L182;
        r1 = null;
        goto L179
    L182:
        r1 = (Map.Entry) r02.next();
        goto L179
    L184:
        return;
    L9:
        r02 = null;
        r1 = null;
        goto L10
    L186:
        if (this.zzmq == true) goto L188;
        zzb(r14, r15);
        return;
    L188:
        if (this.zzmo == false) goto L192;
        zzby<?> r03 = this.zzmy.zza(r14);
        if (r03.isEmpty() == true) goto L192;
        Iterator<Map.Entry<?, Object>> r04 = r03.iterator();
        Map.Entry<?, ?> r12 = (Map.Entry) r04.next();
    L193:
        int r72 = this.zzmi.length;
        int r810 = 0;
    L194:
        if (r810 >= r72) goto L362;
        int r92 = zzag(r810);
        int r107 = this.zzmi[r810];
    L196:
        if (r12 == null) goto L204;
        if (this.zzmy.zza(r12) > r107) goto L204;
        this.zzmy.zza(r15, r12);
        if (r04.hasNext() == true) goto L201;
        r12 = null;
        goto L196
    L201:
        r12 = (Map.Entry) r04.next();
    L204:
        switch(((r92 & 267386880) >>> 20)) {
            case 0: goto L359;
            case 1: goto L356;
            case 2: goto L353;
            case 3: goto L350;
            case 4: goto L347;
            case 5: goto L344;
            case 6: goto L341;
            case 7: goto L338;
            case 8: goto L335;
            case 9: goto L332;
            case 10: goto L329;
            case 11: goto L326;
            case 12: goto L323;
            case 13: goto L320;
            case 14: goto L317;
            case 15: goto L314;
            case 16: goto L311;
            case 17: goto L308;
            case 18: goto L306;
            case 19: goto L305;
            case 20: goto L304;
            case 21: goto L303;
            case 22: goto L302;
            case 23: goto L301;
            case 24: goto L300;
            case 25: goto L299;
            case 26: goto L298;
            case 27: goto L297;
            case 28: goto L296;
            case 29: goto L295;
            case 30: goto L294;
            case 31: goto L293;
            case 32: goto L292;
            case 33: goto L291;
            case 34: goto L290;
            case 35: goto L289;
            case 36: goto L288;
            case 37: goto L287;
            case 38: goto L286;
            case 39: goto L285;
            case 40: goto L284;
            case 41: goto L283;
            case 42: goto L282;
            case 43: goto L281;
            case 44: goto L280;
            case 45: goto L279;
            case 46: goto L278;
            case 47: goto L277;
            case 48: goto L276;
            case 49: goto L275;
            case 50: goto L274;
            case 51: goto L271;
            case 52: goto L267;
            case 53: goto L263;
            case 54: goto L259;
            case 55: goto L255;
            case 56: goto L251;
            case 57: goto L247;
            case 58: goto L243;
            case 59: goto L240;
            case 60: goto L237;
            case 61: goto L234;
            case 62: goto L230;
            case 63: goto L226;
            case 64: goto L222;
            case 65: goto L218;
            case 66: goto L214;
            case 67: goto L210;
            case 68: goto L207;
            default: goto L361;
        };
    L274:
        zza(r15, r107, zzfd.zzo(r14, r92 & 1048575), r810);
        goto L361
    L275:
        zzeh.zzb(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, zzad(r810));
        goto L361
    L276:
        zzeh.zze(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L277:
        zzeh.zzj(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L278:
        zzeh.zzg(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L279:
        zzeh.zzl(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L280:
        zzeh.zzm(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L281:
        zzeh.zzi(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L282:
        zzeh.zzn(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L283:
        zzeh.zzk(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L284:
        zzeh.zzf(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L285:
        zzeh.zzh(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L286:
        zzeh.zzd(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L287:
        zzeh.zzc(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L288:
        zzeh.zzb(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L289:
        zzeh.zza(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, true);
        goto L361
    L290:
        zzeh.zze(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L291:
        zzeh.zzj(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L292:
        zzeh.zzg(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L293:
        zzeh.zzl(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L294:
        zzeh.zzm(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L295:
        zzeh.zzi(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L296:
        zzeh.zzb(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15);
        goto L361
    L297:
        zzeh.zza(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, zzad(r810));
        goto L361
    L298:
        zzeh.zza(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15);
        goto L361
    L299:
        zzeh.zzn(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L300:
        zzeh.zzk(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L301:
        zzeh.zzf(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L302:
        zzeh.zzh(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L303:
        zzeh.zzd(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L304:
        zzeh.zzc(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L305:
        zzeh.zzb(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L306:
        zzeh.zza(this.zzmi[r810], (List) zzfd.zzo(r14, r92 & 1048575), r15, false);
        goto L361
    L207:
        if (zza(r14, r107, r810) == false) goto L361;
    L208:
        r15.zzb(r107, zzfd.zzo(r14, r92 & 1048575), zzad(r810));
        goto L361
    L210:
        if (zza(r14, r107, r810) == false) goto L361;
        long r11 = zzh(r14, r92 & 1048575);
    L212:
        r15.zzb(r107, r11);
        goto L361
    L214:
        if (zza(r14, r107, r810) == false) goto L361;
        int r93 = zzg(r14, r92 & 1048575);
    L216:
        r15.zze(r107, r93);
        goto L361
    L218:
        if (zza(r14, r107, r810) == false) goto L361;
        long r112 = zzh(r14, r92 & 1048575);
    L220:
        r15.zzj(r107, r112);
        goto L361
    L222:
        if (zza(r14, r107, r810) == false) goto L361;
        int r94 = zzg(r14, r92 & 1048575);
    L224:
        r15.zzm(r107, r94);
        goto L361
    L226:
        if (zza(r14, r107, r810) == false) goto L361;
        int r95 = zzg(r14, r92 & 1048575);
    L228:
        r15.zzn(r107, r95);
        goto L361
    L230:
        if (zza(r14, r107, r810) == false) goto L361;
        int r96 = zzg(r14, r92 & 1048575);
    L232:
        r15.zzd(r107, r96);
        goto L361
    L234:
        if (zza(r14, r107, r810) == false) goto L361;
    L235:
        r15.zza(r107, (zzbb) zzfd.zzo(r14, r92 & 1048575));
        goto L361
    L237:
        if (zza(r14, r107, r810) == false) goto L361;
    L238:
        r15.zza(r107, zzfd.zzo(r14, r92 & 1048575), zzad(r810));
        goto L361
    L240:
        if (zza(r14, r107, r810) == false) goto L361;
    L241:
        zza(r107, zzfd.zzo(r14, r92 & 1048575), r15);
        goto L361
    L243:
        if (zza(r14, r107, r810) == false) goto L361;
        boolean r97 = zzi(r14, r92 & 1048575);
    L245:
        r15.zzb(r107, r97);
        goto L361
    L247:
        if (zza(r14, r107, r810) == false) goto L361;
        int r98 = zzg(r14, r92 & 1048575);
    L249:
        r15.zzf(r107, r98);
        goto L361
    L251:
        if (zza(r14, r107, r810) == false) goto L361;
        long r113 = zzh(r14, r92 & 1048575);
    L253:
        r15.zzc(r107, r113);
        goto L361
    L255:
        if (zza(r14, r107, r810) == false) goto L361;
        int r99 = zzg(r14, r92 & 1048575);
    L257:
        r15.zzc(r107, r99);
        goto L361
    L259:
        if (zza(r14, r107, r810) == false) goto L361;
        long r114 = zzh(r14, r92 & 1048575);
    L261:
        r15.zza(r107, r114);
        goto L361
    L263:
        if (zza(r14, r107, r810) == false) goto L361;
        long r115 = zzh(r14, r92 & 1048575);
    L265:
        r15.zzi(r107, r115);
        goto L361
    L267:
        if (zza(r14, r107, r810) == false) goto L361;
        float r910 = zzf(r14, r92 & 1048575);
    L269:
        r15.zza(r107, r910);
        goto L361
    L271:
        if (zza(r14, r107, r810) == false) goto L361;
        double r116 = zze(r14, r92 & 1048575);
    L273:
        r15.zza(r107, r116);
        goto L361
    L308:
        if (zza(r14, r810) == false) goto L361;
    L311:
        if (zza(r14, r810) == false) goto L361;
        r11 = zzfd.zzk(r14, r92 & 1048575);
        goto L212
    L314:
        if (zza(r14, r810) == false) goto L361;
        r93 = zzfd.zzj(r14, r92 & 1048575);
        goto L216
    L317:
        if (zza(r14, r810) == false) goto L361;
        r112 = zzfd.zzk(r14, r92 & 1048575);
        goto L220
    L320:
        if (zza(r14, r810) == false) goto L361;
        r94 = zzfd.zzj(r14, r92 & 1048575);
        goto L224
    L323:
        if (zza(r14, r810) == false) goto L361;
        r95 = zzfd.zzj(r14, r92 & 1048575);
        goto L228
    L326:
        if (zza(r14, r810) == false) goto L361;
        r96 = zzfd.zzj(r14, r92 & 1048575);
        goto L232
    L329:
        if (zza(r14, r810) == false) goto L361;
    L332:
        if (zza(r14, r810) == false) goto L361;
    L335:
        if (zza(r14, r810) == false) goto L361;
    L338:
        if (zza(r14, r810) == false) goto L361;
        r97 = zzfd.zzl(r14, r92 & 1048575);
        goto L245
    L341:
        if (zza(r14, r810) == false) goto L361;
        r98 = zzfd.zzj(r14, r92 & 1048575);
        goto L249
    L344:
        if (zza(r14, r810) == false) goto L361;
        r113 = zzfd.zzk(r14, r92 & 1048575);
        goto L253
    L347:
        if (zza(r14, r810) == false) goto L361;
        r99 = zzfd.zzj(r14, r92 & 1048575);
        goto L257
    L350:
        if (zza(r14, r810) == false) goto L361;
        r114 = zzfd.zzk(r14, r92 & 1048575);
        goto L261
    L353:
        if (zza(r14, r810) == false) goto L361;
        r115 = zzfd.zzk(r14, r92 & 1048575);
        goto L265
    L356:
        if (zza(r14, r810) == false) goto L361;
        r910 = zzfd.zzm(r14, r92 & 1048575);
        goto L269
    L359:
        if (zza(r14, r810) == false) goto L361;
        r116 = zzfd.zzn(r14, r92 & 1048575);
    L361:
        r810 = r810 + 4;
    L362:
        if (r12 == null) goto L367;
        this.zzmy.zza(r15, r12);
        if (r04.hasNext() == true) goto L365;
        r12 = null;
        goto L362
    L365:
        r12 = (Map.Entry) r04.next();
        goto L362
    L367:
        zza(this.zzmx, r14, r15);
        return;
    L192:
        r04 = null;
        r12 = null;
        goto L193
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zza(T r17, byte[] r18, int r19, int r20, zzay r21) {
        zzds<T> r0 = this;
        byte[] r7 = r18;
        int r8 = r20;
        zzay r13 = r21;
        if (r0.zzmq == false) goto L110;
        Unsafe r1 = zzmh;
        int r2 = r19;
    L5:
        if (r2 >= r8) goto L106;
        int r3 = r2 + 1;
        int r22 = r7[r2];
        if (r22 >= 0) goto L9;
        r3 = zzax.zza(r22, r7, r3, r13);
        r22 = r13.zzfd;
    L9:
        int r5 = r22;
        int r9 = r3;
        int r6 = r5 >>> 3;
        int r23 = r5 & 7;
        int r12 = r0.zzai(r6);
        if (r12 < 0) goto L15;
        int r32 = r0.zzmi[r12 + 1];
        int r11 = (267386880 & r32) >>> 20;
        long r14 = 1048575 & r32;
        if (r11 > 17) goto L73;
        boolean r62 = true;
        switch(r11) {
            case 0: goto L70;
            case 1: goto L66;
            case 2: goto L62;
            case 3: goto L62;
            case 4: goto L59;
            case 5: goto L56;
            case 6: goto L53;
            case 7: goto L46;
            case 8: goto L40;
            case 9: goto L34;
            case 10: goto L29;
            case 11: goto L59;
            case 12: goto L26;
            case 13: goto L53;
            case 14: goto L56;
            case 15: goto L22;
            case 16: goto L17;
            default: goto L15;
        };
    L62:
        Unsafe r112 = r1;
        if (r23 != 0) goto L65;
        int r92 = zzax.zzb(r7, r9, r13);
        r1 = r112;
        r1.putLong(r17, r14, r13.zzfe);
        r2 = r92;
        goto L5
    L65:
        Unsafe r15 = r112;
    L16:
        int r33 = r9;
    L103:
        T r4 = r17;
        byte[] r16 = r18;
        int r24 = r33;
        int r02 = r5;
        int r34 = r20;
    L97:
        zzay r52 = r21;
    L104:
        r2 = zza(r02, r16, r24, r34, r4, r52);
        r0 = this;
        r7 = r18;
        r13 = r21;
        r8 = r34;
    L69:
        r1 = r15;
        goto L5
    L66:
        r15 = r1;
        if (r23 != 5) goto L16;
        zzfd.zza(r17, r14, zzax.zzf(r7, r9));
        r2 = r9 + 4;
        goto L69
    L70:
        r15 = r1;
        if (r23 != 1) goto L16;
        zzfd.zza(r17, r14, zzax.zze(r7, r9));
        r2 = r9 + 8;
        goto L69
    L17:
        if (r23 == 0) goto L18;
        Unsafe r113 = r1;
    L21:
        r33 = r9;
        r15 = r113;
        goto L103
    L18:
        int r93 = zzax.zzb(r7, r9, r13);
        r1.putLong(r17, r14, zzbk.zza(r13.zzfe));
        r113 = r1;
        r2 = r93;
    L19:
        r1 = r113;
        goto L5
    L22:
        r113 = r1;
        T r110 = r17;
        if (r23 != 0) goto L21;
        r2 = zzax.zza(r7, r9, r13);
        int r35 = zzbk.zzm(r13.zzfd);
    L25:
        r113.putInt(r110, r14, r35);
        goto L19
    L26:
        r113 = r1;
        r110 = r17;
        if (r23 != 0) goto L21;
        r2 = zzax.zza(r7, r9, r13);
        r35 = r13.zzfd;
        goto L25
    L29:
        r113 = r1;
        T r111 = r17;
        if (r23 != 2) goto L21;
        r2 = zzax.zze(r7, r9, r13);
    L32:
        Object r36 = r13.zzff;
    L33:
        r113.putObject(r111, r14, r36);
        goto L19
    L34:
        r113 = r1;
        r111 = r17;
        if (r23 != 2) goto L21;
        r2 = zza(r0.zzad(r12), r7, r9, r8, r13);
        Object r37 = r113.getObject(r111, r14);
        if (r37 != null) goto L39;
        r36 = r13.zzff;
        goto L33
    L39:
        r36 = zzci.zza(r37, r13.zzff);
        goto L33
    L40:
        r113 = r1;
        r111 = r17;
        if (r23 != 2) goto L21;
        if ((536870912 & r32) != 0) goto L45;
        r2 = zzax.zzc(r7, r9, r13);
        goto L32
    L45:
        r2 = zzax.zzd(r7, r9, r13);
        goto L32
    L46:
        r113 = r1;
        if (r23 != 0) goto L21;
        r2 = zzax.zzb(r7, r9, r13);
        if (r13.zzfe != 0) goto L52;
        r62 = false;
    L52:
        zzfd.zza(r17, r14, r62);
        goto L19
    L53:
        r113 = r1;
        if (r23 != 5) goto L21;
        r113.putInt(r17, r14, zzax.zzc(r7, r9));
        r2 = r9 + 4;
        goto L19
    L56:
        r113 = r1;
        if (r23 != 1) goto L21;
        r113.putLong(r17, r14, zzax.zzd(r7, r9));
        r2 = r9 + 8;
        goto L19
    L59:
        r113 = r1;
        if (r23 != 0) goto L21;
        r2 = zzax.zza(r7, r9, r13);
        r113.putInt(r17, r14, r13.zzfd);
        goto L19
    L73:
        r15 = r1;
        if (r11 != 27) goto L86;
        if (r23 != 2) goto L84;
        zzcn r114 = (zzcn) r15.getObject(r17, r14);
        if (r114.zzu() == true) goto L83;
        int r25 = r114.size();
        if (r25 != 0) goto L81;
        int r26 = 10;
    L82:
        r114 = r114.zzi(r26);
        r15.putObject(r17, r14, r114);
        goto L83
    L81:
        r26 = r25 << 1;
    L83:
        r2 = zza(r0.zzad(r12), r5, r7, r9, r8, r114, r13);
        r7 = r18;
        r8 = r20;
        r13 = r21;
        goto L69
    L84:
        r5 = r5;
        goto L16
    L86:
        if (r11 > 49) goto L92;
        int r63 = r0.zza(r17, r18, r9, r20, r5, r6, r23, r12, r32, r11, r14, r21);
        if (r63 != r9) goto L90;
        r4 = r17;
        r16 = r18;
        r34 = r20;
        r52 = r21;
        r24 = r63;
        r02 = r5;
    L90:
        r0 = this;
        r7 = r18;
        r8 = r20;
        r13 = r21;
        r2 = r63;
        goto L69
    L92:
        if (r11 != 50) goto L100;
        if (r23 != 2) goto L99;
        int r53 = zza(r17, r18, r9, r20, r12, r6, r14, r21);
        if (r53 != r9) goto L98;
        r4 = r17;
        r16 = r18;
        r34 = r20;
        r24 = r53;
        r02 = r5;
        goto L97
    L98:
        r0 = this;
        r7 = r18;
        r8 = r20;
        r13 = r21;
        r2 = r53;
        goto L69
    L99:
        r33 = r9;
        r5 = r5;
        goto L103
    L100:
        r63 = zza(r17, r18, r9, r20, r5, r6, r23, r32, r11, r14, r12, r21);
        if (r63 != r9) goto L90;
        r4 = r17;
        r16 = r18;
        r34 = r20;
        r02 = r5;
        r24 = r63;
    L15:
        r15 = r1;
        goto L16
    L106:
        if (r2 != r8) goto L109;
        return;
    L109:
        throw zzco.zzbo();
    L110:
        zza(r17, r18, r19, r8, 0, r21);
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zzc(T r9) {
        int[] r0 = this.zzmt;
        int r1 = 0;
        if (r0 == null) goto L10;
        int r2 = r0.length;
        int r3 = 0;
    L5:
        if (r3 >= r2) goto L10;
        long r4 = zzag(r0[r3]) & 1048575;
        Object r6 = zzfd.zzo(r9, r4);
        if (r6 == null) goto L9;
        zzfd.zza(r9, r4, this.zzmz.zzj(r6));
    L9:
        r3 = r3 + 1;
    L10:
        int[] r02 = this.zzmu;
        if (r02 == null) goto L15;
        int r22 = r02.length;
    L13:
        if (r1 >= r22) goto L15;
        this.zzmw.zza(r9, r02[r1]);
        r1 = r1 + 1;
    L15:
        this.zzmx.zzc(r9);
        if (this.zzmo == false) goto L23;
        this.zzmy.zzc(r9);
        return;
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zzc(T r7, T r8) {
        r8.getClass();
        int r0 = 0;
    L4:
        if (r0 >= this.zzmi.length) goto L69;
        int r1 = zzag(r0);
        long r2 = 1048575 & r1;
        int r4 = this.zzmi[r0];
        switch(((r1 & 267386880) >>> 20)) {
            case 0: goto L65;
            case 1: goto L62;
            case 2: goto L59;
            case 3: goto L56;
            case 4: goto L53;
            case 5: goto L50;
            case 6: goto L47;
            case 7: goto L44;
            case 8: goto L41;
            case 9: goto L17;
            case 10: goto L38;
            case 11: goto L35;
            case 12: goto L32;
            case 13: goto L29;
            case 14: goto L26;
            case 15: goto L23;
            case 16: goto L19;
            case 17: goto L17;
            case 18: goto L16;
            case 19: goto L16;
            case 20: goto L16;
            case 21: goto L16;
            case 22: goto L16;
            case 23: goto L16;
            case 24: goto L16;
            case 25: goto L16;
            case 26: goto L16;
            case 27: goto L16;
            case 28: goto L16;
            case 29: goto L16;
            case 30: goto L16;
            case 31: goto L16;
            case 32: goto L16;
            case 33: goto L16;
            case 34: goto L16;
            case 35: goto L16;
            case 36: goto L16;
            case 37: goto L16;
            case 38: goto L16;
            case 39: goto L16;
            case 40: goto L16;
            case 41: goto L16;
            case 42: goto L16;
            case 43: goto L16;
            case 44: goto L16;
            case 45: goto L16;
            case 46: goto L16;
            case 47: goto L16;
            case 48: goto L16;
            case 49: goto L16;
            case 50: goto L15;
            case 51: goto L13;
            case 52: goto L13;
            case 53: goto L13;
            case 54: goto L13;
            case 55: goto L13;
            case 56: goto L13;
            case 57: goto L13;
            case 58: goto L13;
            case 59: goto L13;
            case 60: goto L8;
            case 61: goto L10;
            case 62: goto L10;
            case 63: goto L10;
            case 64: goto L10;
            case 65: goto L10;
            case 66: goto L10;
            case 67: goto L10;
            case 68: goto L8;
            default: goto L67;
        };
    L8:
        zzb(r7, r8, r0);
        goto L67
    L15:
        zzeh.zza(this.zzmz, r7, r8, r2);
        goto L67
    L16:
        this.zzmw.zza(r7, r8, r2);
        goto L67
    L17:
        zza(r7, r8, r0);
        goto L67
    L10:
        if (zza(r8, r4, r0) == false) goto L67;
    L11:
        zzfd.zza(r7, r2, zzfd.zzo(r8, r2));
        zzb(r7, r4, r0);
        goto L67
    L13:
        if (zza(r8, r4, r0) == false) goto L67;
    L19:
        if (zza(r8, r0) == false) goto L67;
    L20:
        zzfd.zza(r7, r2, zzfd.zzk(r8, r2));
    L21:
        zzb(r7, r0);
        goto L67
    L23:
        if (zza(r8, r0) == false) goto L67;
    L24:
        zzfd.zza(r7, r2, zzfd.zzj(r8, r2));
        goto L21
    L26:
        if (zza(r8, r0) == false) goto L67;
    L29:
        if (zza(r8, r0) == false) goto L67;
    L32:
        if (zza(r8, r0) == false) goto L67;
    L35:
        if (zza(r8, r0) == false) goto L67;
    L38:
        if (zza(r8, r0) == false) goto L67;
    L39:
        zzfd.zza(r7, r2, zzfd.zzo(r8, r2));
        goto L21
    L41:
        if (zza(r8, r0) == false) goto L67;
    L44:
        if (zza(r8, r0) == false) goto L67;
        zzfd.zza(r7, r2, zzfd.zzl(r8, r2));
        goto L21
    L47:
        if (zza(r8, r0) == false) goto L67;
    L50:
        if (zza(r8, r0) == false) goto L67;
    L53:
        if (zza(r8, r0) == false) goto L67;
    L56:
        if (zza(r8, r0) == false) goto L67;
    L59:
        if (zza(r8, r0) == false) goto L67;
    L62:
        if (zza(r8, r0) == false) goto L67;
        zzfd.zza(r7, r2, zzfd.zzm(r8, r2));
        goto L21
    L65:
        if (zza(r8, r0) == false) goto L67;
        zzfd.zza(r7, r2, zzfd.zzn(r8, r2));
    L67:
        r0 = r0 + 4;
        goto L4
    L69:
        if (this.zzmq == true) goto L100;
        zzeh.zza(this.zzmx, r7, r8);
        if (this.zzmo == false) goto L101;
        zzeh.zza(this.zzmy, r7, r8);
        return;
    L101:
        return;
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final int zzm(T r21) {
        int r3 = 267386880;
        int r8 = 1048575;
        if (this.zzmq == false) goto L226;
        Unsafe r2 = zzmh;
        int r12 = 0;
        int r13 = 0;
    L6:
        if (r12 >= this.zzmi.length) goto L225;
        int r14 = zzag(r12);
        int r15 = (r14 & r3) >>> 20;
        int r16 = r3;
        int r32 = this.zzmi[r12];
        long r4 = r14 & 1048575;
        if (r15 >= zzcb.zzih.id()) goto L10;
    L12:
        int r142 = 0;
    L13:
        switch(r15) {
            case 0: goto L221;
            case 1: goto L218;
            case 2: goto L215;
            case 3: goto L212;
            case 4: goto L209;
            case 5: goto L206;
            case 6: goto L203;
            case 7: goto L200;
            case 8: goto L195;
            case 9: goto L192;
            case 10: goto L189;
            case 11: goto L186;
            case 12: goto L183;
            case 13: goto L180;
            case 14: goto L177;
            case 15: goto L174;
            case 16: goto L171;
            case 17: goto L168;
            case 18: goto L156;
            case 19: goto L157;
            case 20: goto L166;
            case 21: goto L165;
            case 22: goto L164;
            case 23: goto L156;
            case 24: goto L157;
            case 25: goto L163;
            case 26: goto L162;
            case 27: goto L161;
            case 28: goto L160;
            case 29: goto L159;
            case 30: goto L158;
            case 31: goto L157;
            case 32: goto L156;
            case 33: goto L155;
            case 34: goto L154;
            case 35: goto L149;
            case 36: goto L144;
            case 37: goto L139;
            case 38: goto L134;
            case 39: goto L129;
            case 40: goto L124;
            case 41: goto L119;
            case 42: goto L114;
            case 43: goto L109;
            case 44: goto L104;
            case 45: goto L99;
            case 46: goto L94;
            case 47: goto L89;
            case 48: goto L83;
            case 49: goto L82;
            case 50: goto L81;
            case 51: goto L79;
            case 52: goto L76;
            case 53: goto L72;
            case 54: goto L68;
            case 55: goto L64;
            case 56: goto L61;
            case 57: goto L58;
            case 58: goto L55;
            case 59: goto L49;
            case 60: goto L46;
            case 61: goto L42;
            case 62: goto L38;
            case 63: goto L34;
            case 64: goto L31;
            case 65: goto L28;
            case 66: goto L24;
            case 67: goto L20;
            case 68: goto L16;
            default: goto L223;
        };
    L81:
        int r33 = this.zzmz.zzb(r32, zzfd.zzo(r21, r4), zzae(r12));
    L18:
        r13 = r13 + r33;
        goto L223
    L82:
        r33 = zzeh.zzd(r32, zzd(r21, r4), zzad(r12));
        goto L18
    L83:
        int r42 = zzeh.zzc((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L87:
        r2.putInt(r21, r142, r42);
    L88:
        r33 = (zzbn.zzr(r32) + zzbn.zzt(r42)) + r42;
        goto L18
    L89:
        r42 = zzeh.zzg((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L94:
        r42 = zzeh.zzi((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L99:
        r42 = zzeh.zzh((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L104:
        r42 = zzeh.zzd((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L109:
        r42 = zzeh.zzf((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L114:
        r42 = zzeh.zzj((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L119:
        r42 = zzeh.zzh((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L124:
        r42 = zzeh.zzi((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L129:
        r42 = zzeh.zze((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L134:
        r42 = zzeh.zzb((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L139:
        r42 = zzeh.zza((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L144:
        r42 = zzeh.zzh((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L149:
        r42 = zzeh.zzi((List) r2.getObject(r21, r4));
        if (r42 <= 0) goto L223;
        if (this.zzmr == false) goto L88;
    L154:
        r33 = zzeh.zzq(r32, zzd(r21, r4), false);
        goto L18
    L155:
        r33 = zzeh.zzu(r32, zzd(r21, r4), false);
        goto L18
    L156:
        r33 = zzeh.zzw(r32, zzd(r21, r4), false);
        goto L18
    L157:
        r33 = zzeh.zzv(r32, zzd(r21, r4), false);
        goto L18
    L158:
        r33 = zzeh.zzr(r32, zzd(r21, r4), false);
        goto L18
    L159:
        r33 = zzeh.zzt(r32, zzd(r21, r4), false);
        goto L18
    L160:
        r33 = zzeh.zzd(r32, zzd(r21, r4));
        goto L18
    L161:
        r33 = zzeh.zzc(r32, zzd(r21, r4), zzad(r12));
        goto L18
    L162:
        r33 = zzeh.zzc(r32, zzd(r21, r4));
        goto L18
    L163:
        r33 = zzeh.zzx(r32, zzd(r21, r4), false);
        goto L18
    L164:
        r33 = zzeh.zzs(r32, zzd(r21, r4), false);
        goto L18
    L165:
        r33 = zzeh.zzp(r32, zzd(r21, r4), false);
        goto L18
    L166:
        r33 = zzeh.zzo(r32, zzd(r21, r4), false);
        goto L18
    L16:
        if (zza(r21, r32, r12) == false) goto L223;
    L17:
        r33 = zzbn.zzc(r32, (zzdo) zzfd.zzo(r21, r4), zzad(r12));
        goto L18
    L20:
        if (zza(r21, r32, r12) == false) goto L223;
        long r43 = zzh(r21, r4);
    L22:
        r33 = zzbn.zzf(r32, r43);
        goto L18
    L24:
        if (zza(r21, r32, r12) == false) goto L223;
        int r44 = zzg(r21, r4);
    L26:
        r33 = zzbn.zzi(r32, r44);
        goto L18
    L28:
        if (zza(r21, r32, r12) == false) goto L223;
    L29:
        r33 = zzbn.zzh(r32, 0);
        goto L18
    L31:
        if (zza(r21, r32, r12) == false) goto L223;
    L32:
        r33 = zzbn.zzk(r32, 0);
        goto L18
    L34:
        if (zza(r21, r32, r12) == false) goto L223;
        int r45 = zzg(r21, r4);
    L36:
        r33 = zzbn.zzl(r32, r45);
        goto L18
    L38:
        if (zza(r21, r32, r12) == false) goto L223;
        int r46 = zzg(r21, r4);
    L40:
        r33 = zzbn.zzh(r32, r46);
        goto L18
    L42:
        if (zza(r21, r32, r12) == false) goto L223;
    L43:
        zzbb r47 = (zzbb) zzfd.zzo(r21, r4);
    L44:
        r33 = zzbn.zzc(r32, r47);
        goto L18
    L46:
        if (zza(r21, r32, r12) == false) goto L223;
    L47:
        r33 = zzeh.zzc(r32, zzfd.zzo(r21, r4), zzad(r12));
        goto L18
    L49:
        if (zza(r21, r32, r12) == false) goto L223;
        Object r48 = zzfd.zzo(r21, r4);
        if ((r48 instanceof zzbb) == false) goto L53;
    L52:
        r47 = (zzbb) r48;
    L53:
        r33 = zzbn.zzb(r32, (String) r48);
        goto L18
    L55:
        if (zza(r21, r32, r12) == false) goto L223;
    L56:
        r33 = zzbn.zzc(r32, true);
        goto L18
    L58:
        if (zza(r21, r32, r12) == false) goto L223;
    L59:
        r33 = zzbn.zzj(r32, 0);
        goto L18
    L61:
        if (zza(r21, r32, r12) == false) goto L223;
    L62:
        r33 = zzbn.zzg(r32, 0);
        goto L18
    L64:
        if (zza(r21, r32, r12) == false) goto L223;
        int r49 = zzg(r21, r4);
    L66:
        r33 = zzbn.zzg(r32, r49);
        goto L18
    L68:
        if (zza(r21, r32, r12) == false) goto L223;
        long r410 = zzh(r21, r4);
    L70:
        r33 = zzbn.zze(r32, r410);
        goto L18
    L72:
        if (zza(r21, r32, r12) == false) goto L223;
        long r411 = zzh(r21, r4);
    L74:
        r33 = zzbn.zzd(r32, r411);
        goto L18
    L76:
        if (zza(r21, r32, r12) == false) goto L223;
    L77:
        r33 = zzbn.zzb(r32, 0.0f);
        goto L18
    L79:
        if (zza(r21, r32, r12) == false) goto L223;
    L80:
        r33 = zzbn.zzb(r32, 0.0d);
        goto L18
    L168:
        if (zza(r21, r12) == false) goto L223;
    L171:
        if (zza(r21, r12) == false) goto L223;
        r43 = zzfd.zzk(r21, r4);
        goto L22
    L174:
        if (zza(r21, r12) == false) goto L223;
        r44 = zzfd.zzj(r21, r4);
        goto L26
    L177:
        if (zza(r21, r12) == false) goto L223;
    L180:
        if (zza(r21, r12) == false) goto L223;
    L183:
        if (zza(r21, r12) == false) goto L223;
        r45 = zzfd.zzj(r21, r4);
        goto L36
    L186:
        if (zza(r21, r12) == false) goto L223;
        r46 = zzfd.zzj(r21, r4);
        goto L40
    L189:
        if (zza(r21, r12) == false) goto L223;
    L192:
        if (zza(r21, r12) == false) goto L223;
    L195:
        if (zza(r21, r12) == false) goto L223;
        r48 = zzfd.zzo(r21, r4);
        if ((r48 instanceof zzbb) == false) goto L53;
    L200:
        if (zza(r21, r12) == false) goto L223;
    L203:
        if (zza(r21, r12) == false) goto L223;
    L206:
        if (zza(r21, r12) == false) goto L223;
    L209:
        if (zza(r21, r12) == false) goto L223;
        r49 = zzfd.zzj(r21, r4);
        goto L66
    L212:
        if (zza(r21, r12) == false) goto L223;
        r410 = zzfd.zzk(r21, r4);
        goto L70
    L215:
        if (zza(r21, r12) == false) goto L223;
        r411 = zzfd.zzk(r21, r4);
        goto L74
    L218:
        if (zza(r21, r12) == false) goto L223;
    L221:
        if (zza(r21, r12) == true) goto L80;
    L223:
        r12 = r12 + 4;
        r3 = r16;
        goto L6
    L10:
        if (r15 > zzcb.zziu.id()) goto L12;
        r142 = this.zzmi[r12 + 2] & 1048575;
        goto L13
    L225:
        return r13 + zza(this.zzmx, r21);
    L226:
        Unsafe r22 = zzmh;
        int r34 = -1;
        int r412 = 0;
        int r5 = 0;
        int r122 = 0;
    L228:
        if (r412 >= this.zzmi.length) goto L459;
        int r132 = zzag(r412);
        int[] r143 = this.zzmi;
        int r152 = r143[r412];
        int r18 = r8;
        int r82 = (r132 & 267386880) >>> 20;
        if (r82 > 17) goto L235;
        int r6 = r143[r412 + 2];
        int r144 = r6 & r18;
        int r19 = 1 << (r6 >>> 20);
        if (r144 == r34) goto L243;
        r122 = r22.getInt(r21, r144);
        r34 = r144;
    L243:
        long r133 = r132 & r18;
        switch(r82) {
            case 0: goto L455;
            case 1: goto L452;
            case 2: goto L449;
            case 3: goto L446;
            case 4: goto L443;
            case 5: goto L440;
            case 6: goto L437;
            case 7: goto L434;
            case 8: goto L429;
            case 9: goto L426;
            case 10: goto L423;
            case 11: goto L420;
            case 12: goto L417;
            case 13: goto L414;
            case 14: goto L411;
            case 15: goto L408;
            case 16: goto L405;
            case 17: goto L402;
            case 18: goto L400;
            case 19: goto L390;
            case 20: goto L399;
            case 21: goto L398;
            case 22: goto L397;
            case 23: goto L389;
            case 24: goto L390;
            case 25: goto L396;
            case 26: goto L395;
            case 27: goto L394;
            case 28: goto L393;
            case 29: goto L392;
            case 30: goto L391;
            case 31: goto L390;
            case 32: goto L389;
            case 33: goto L388;
            case 34: goto L386;
            case 35: goto L381;
            case 36: goto L376;
            case 37: goto L371;
            case 38: goto L366;
            case 39: goto L361;
            case 40: goto L356;
            case 41: goto L351;
            case 42: goto L346;
            case 43: goto L341;
            case 44: goto L336;
            case 45: goto L331;
            case 46: goto L326;
            case 47: goto L321;
            case 48: goto L315;
            case 49: goto L314;
            case 50: goto L313;
            case 51: goto L311;
            case 52: goto L308;
            case 53: goto L305;
            case 54: goto L302;
            case 55: goto L299;
            case 56: goto L296;
            case 57: goto L293;
            case 58: goto L290;
            case 59: goto L284;
            case 60: goto L281;
            case 61: goto L277;
            case 62: goto L273;
            case 63: goto L269;
            case 64: goto L265;
            case 65: goto L262;
            case 66: goto L258;
            case 67: goto L254;
            case 68: goto L247;
            default: goto L250;
        };
    L313:
        int r62 = this.zzmz.zzb(r152, r22.getObject(r21, r133), zzae(r412));
    L249:
        r5 = r5 + r62;
        goto L250
    L314:
        r62 = zzeh.zzd(r152, (List) r22.getObject(r21, r133), zzad(r412));
        goto L249
    L315:
        int r83 = zzeh.zzc((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L319:
        r22.putInt(r21, r6, r83);
    L320:
        r62 = (zzbn.zzr(r152) + zzbn.zzt(r83)) + r83;
        goto L249
    L321:
        r83 = zzeh.zzg((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L326:
        r83 = zzeh.zzi((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L331:
        r83 = zzeh.zzh((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L336:
        r83 = zzeh.zzd((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L341:
        r83 = zzeh.zzf((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L346:
        r83 = zzeh.zzj((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L351:
        r83 = zzeh.zzh((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L356:
        r83 = zzeh.zzi((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L361:
        r83 = zzeh.zze((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L366:
        r83 = zzeh.zzb((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L371:
        r83 = zzeh.zza((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L376:
        r83 = zzeh.zzh((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L381:
        r83 = zzeh.zzi((List) r22.getObject(r21, r133));
        if (r83 <= 0) goto L250;
        if (this.zzmr == false) goto L320;
    L386:
        boolean r84 = false;
        int r63 = zzeh.zzq(r152, (List) r22.getObject(r21, r133), false);
    L387:
        r5 = r5 + r63;
        boolean r64 = r84;
    L251:
        boolean r85 = false;
    L458:
        r412 = r412 + 4;
        r8 = r18;
        goto L228
    L388:
        r84 = false;
        r63 = zzeh.zzu(r152, (List) r22.getObject(r21, r133), false);
        goto L387
    L389:
        r84 = false;
        r63 = zzeh.zzw(r152, (List) r22.getObject(r21, r133), false);
        goto L387
    L390:
        r84 = false;
        r63 = zzeh.zzv(r152, (List) r22.getObject(r21, r133), false);
        goto L387
    L391:
        r84 = false;
        r63 = zzeh.zzr(r152, (List) r22.getObject(r21, r133), false);
        goto L387
    L392:
        r62 = zzeh.zzt(r152, (List) r22.getObject(r21, r133), false);
        goto L249
    L393:
        r62 = zzeh.zzd(r152, (List) r22.getObject(r21, r133));
        goto L249
    L394:
        r62 = zzeh.zzc(r152, (List) r22.getObject(r21, r133), zzad(r412));
        goto L249
    L395:
        r62 = zzeh.zzc(r152, (List) r22.getObject(r21, r133));
        goto L249
    L396:
        r84 = false;
        r63 = zzeh.zzx(r152, (List) r22.getObject(r21, r133), false);
        goto L387
    L397:
        r84 = false;
        r63 = zzeh.zzs(r152, (List) r22.getObject(r21, r133), false);
        goto L387
    L398:
        r84 = false;
        r63 = zzeh.zzp(r152, (List) r22.getObject(r21, r133), false);
        goto L387
    L399:
        r84 = false;
        r63 = zzeh.zzo(r152, (List) r22.getObject(r21, r133), false);
        goto L387
    L400:
        r62 = zzeh.zzw(r152, (List) r22.getObject(r21, r133), false);
        goto L249
    L440:
        r64 = false;
        if ((r122 & r19) == 0) goto L251;
        int r86 = zzbn.zzg(r152, 0);
    L439:
        r5 = r5 + r86;
        goto L251
    L443:
        r64 = false;
        if ((r122 & r19) == 0) goto L251;
        r86 = zzbn.zzg(r152, r22.getInt(r21, r133));
        goto L439
    L446:
        r64 = false;
        if ((r122 & r19) == 0) goto L251;
        r86 = zzbn.zze(r152, r22.getLong(r21, r133));
        goto L439
    L449:
        r64 = false;
        if ((r122 & r19) == 0) goto L251;
        r86 = zzbn.zzd(r152, r22.getLong(r21, r133));
        goto L439
    L452:
        r64 = false;
        if ((r122 & r19) == 0) goto L251;
        r85 = false;
        r5 = r5 + zzbn.zzb(r152, 0.0f);
        goto L458
    L455:
        r64 = false;
        r85 = false;
        if ((r122 & r19) == 0) goto L458;
        r5 = r5 + zzbn.zzb(r152, 0.0d);
    L250:
        r64 = false;
        goto L251
    L247:
        if (zza(r21, r152, r412) == false) goto L250;
    L248:
        r62 = zzbn.zzc(r152, (zzdo) r22.getObject(r21, r133), zzad(r412));
        goto L249
    L254:
        if (zza(r21, r152, r412) == false) goto L250;
        long r134 = zzh(r21, r133);
    L256:
        r62 = zzbn.zzf(r152, r134);
        goto L249
    L258:
        if (zza(r21, r152, r412) == false) goto L250;
        int r65 = zzg(r21, r133);
    L260:
        r62 = zzbn.zzi(r152, r65);
        goto L249
    L262:
        if (zza(r21, r152, r412) == false) goto L250;
    L263:
        r62 = zzbn.zzh(r152, 0);
        goto L249
    L265:
        if (zza(r21, r152, r412) == false) goto L250;
    L266:
        int r87 = zzbn.zzk(r152, 0);
    L267:
        r5 = r5 + r87;
        goto L250
    L269:
        if (zza(r21, r152, r412) == false) goto L250;
        int r66 = zzg(r21, r133);
    L271:
        r62 = zzbn.zzl(r152, r66);
        goto L249
    L273:
        if (zza(r21, r152, r412) == false) goto L250;
        int r67 = zzg(r21, r133);
    L275:
        r62 = zzbn.zzh(r152, r67);
        goto L249
    L277:
        if (zza(r21, r152, r412) == false) goto L250;
    L278:
        zzbb r68 = (zzbb) r22.getObject(r21, r133);
    L279:
        r62 = zzbn.zzc(r152, r68);
        goto L249
    L281:
        if (zza(r21, r152, r412) == false) goto L250;
    L282:
        r62 = zzeh.zzc(r152, r22.getObject(r21, r133), zzad(r412));
        goto L249
    L284:
        if (zza(r21, r152, r412) == false) goto L250;
        Object r69 = r22.getObject(r21, r133);
        if ((r69 instanceof zzbb) == false) goto L288;
    L287:
        r68 = (zzbb) r69;
    L288:
        r62 = zzbn.zzb(r152, (String) r69);
        goto L249
    L290:
        if (zza(r21, r152, r412) == false) goto L250;
    L291:
        r62 = zzbn.zzc(r152, true);
        goto L249
    L293:
        if (zza(r21, r152, r412) == false) goto L250;
        r87 = zzbn.zzj(r152, 0);
        goto L267
    L296:
        if (zza(r21, r152, r412) == false) goto L250;
        r62 = zzbn.zzg(r152, 0);
        goto L249
    L299:
        if (zza(r21, r152, r412) == false) goto L250;
        r62 = zzbn.zzg(r152, zzg(r21, r133));
        goto L249
    L302:
        if (zza(r21, r152, r412) == false) goto L250;
        r62 = zzbn.zze(r152, zzh(r21, r133));
        goto L249
    L305:
        if (zza(r21, r152, r412) == false) goto L250;
        r62 = zzbn.zzd(r152, zzh(r21, r133));
        goto L249
    L308:
        if (zza(r21, r152, r412) == false) goto L250;
        r87 = zzbn.zzb(r152, 0.0f);
        goto L267
    L311:
        if (zza(r21, r152, r412) == false) goto L250;
        r62 = zzbn.zzb(r152, 0.0d);
        goto L249
    L402:
        if ((r122 & r19) == 0) goto L250;
    L405:
        if ((r122 & r19) == 0) goto L250;
        r134 = r22.getLong(r21, r133);
        goto L256
    L408:
        if ((r122 & r19) == 0) goto L250;
        r65 = r22.getInt(r21, r133);
        goto L260
    L411:
        if ((r122 & r19) == 0) goto L250;
    L414:
        if ((r122 & r19) == 0) goto L250;
    L417:
        if ((r122 & r19) == 0) goto L250;
        r66 = r22.getInt(r21, r133);
        goto L271
    L420:
        if ((r122 & r19) == 0) goto L250;
        r67 = r22.getInt(r21, r133);
        goto L275
    L423:
        if ((r122 & r19) == 0) goto L250;
    L426:
        if ((r122 & r19) == 0) goto L250;
    L429:
        if ((r122 & r19) == 0) goto L250;
        r69 = r22.getObject(r21, r133);
        if ((r69 instanceof zzbb) == false) goto L288;
    L434:
        if ((r122 & r19) == 0) goto L250;
    L437:
        if ((r122 & r19) == 0) goto L250;
        r64 = false;
        r86 = zzbn.zzj(r152, 0);
        goto L439
    L235:
        if (this.zzmr == true) goto L237;
    L242:
        r6 = 0;
    L241:
        r19 = 0;
        goto L243
    L237:
        if (r82 < zzcb.zzih.id()) goto L242;
        if (r82 > zzcb.zziu.id()) goto L242;
        r6 = this.zzmi[r412 + 2] & r18;
        goto L241
    L459:
        int r52 = r5 + zza(this.zzmx, r21);
        if (this.zzmo == true) goto L462;
        return r52;
    L462:
        return r52 + this.zzmy.zza(r21).zzas();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.clearcut.zzef
    public final boolean zzo(T r18) {
        int[] r2 = this.zzms;
        int r3 = 1;
        if (r2 == null) goto L66;
        if (r2.length == 0) goto L66;
        int r4 = r2.length;
        int r6 = -1;
        int r7 = 0;
        int r8 = 0;
    L8:
        if (r7 >= r4) goto L61;
        int r9 = r2[r7];
        int r10 = zzai(r9);
        int r11 = zzag(r10);
        if (this.zzmq == true) goto L15;
        int r12 = this.zzmi[r10 + 2];
        int r14 = r12 & 1048575;
        int r122 = r3 << (r12 >>> 20);
        if (r14 == r6) goto L14;
        int r15 = r3;
        int r16 = r4;
        r8 = zzmh.getInt(r18, r14);
        r6 = r14;
    L17:
        if ((268435456 & r11) != 0) goto L19;
    L21:
        int r32 = (267386880 & r11) >>> 20;
        if (r32 == 9) goto L56;
        if (r32 == 17) goto L56;
        if (r32 != 27) goto L28;
    L46:
        List r33 = (List) zzfd.zzo(r18, r11 & 1048575);
        if (r33.isEmpty() == true) goto L60;
        zzef r42 = zzad(r10);
        int r92 = 0;
    L50:
        if (r92 >= r33.size()) goto L60;
        if (r42.zzo(r33.get(r92)) == false) goto L53;
        r92 = r92 + 1;
        goto L50
    L53:
        return false;
    L60:
        r7 = r7 + 1;
        r3 = r15;
        r4 = r16;
        goto L8
    L28:
        if (r32 == 60) goto L42;
        if (r32 == 68) goto L42;
        if (r32 == 49) goto L46;
        if (r32 != 50) goto L60;
        if (this.zzmz.zzh(zzfd.zzo(r18, r11 & 1048575)).isEmpty() == true) goto L60;
        this.zzmz.zzl(zzae(r10));
        throw null;
    L42:
        if (zza(r18, r9, r10) == false) goto L60;
        if (zza(r18, r11, zzad(r10)) == true) goto L60;
        return false;
    L56:
        if (zza(r18, r10, r8, r122) == false) goto L60;
        if (zza(r18, r11, zzad(r10)) == true) goto L60;
        return false;
    L19:
        if (zza(r18, r10, r8, r122) == true) goto L21;
        return false;
    L14:
        r15 = r3;
        r16 = r4;
        goto L17
    L15:
        r15 = r3;
        r16 = r4;
        r122 = 0;
        goto L17
    L61:
        boolean r152 = r3;
        if (this.zzmo == true) goto L64;
        return r152;
    L64:
        if (this.zzmy.zza(r18).isInitialized() == true) goto L82;
        return false;
    L82:
        return r152;
    L66:
        return true;
    }
}
