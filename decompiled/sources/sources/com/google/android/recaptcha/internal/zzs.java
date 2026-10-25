package com.google.android.recaptcha.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzs implements zzaj {
    public static final /* synthetic */ zzs zza = null;
    public static final /* synthetic */ zzs zzb = null;
    public static final /* synthetic */ zzs zzc = null;
    public static final /* synthetic */ zzs zzd = null;
    public static final /* synthetic */ zzs zze = null;
    public static final /* synthetic */ zzs zzf = null;
    public static final /* synthetic */ zzs zzg = null;
    public static final /* synthetic */ zzs zzh = null;
    public static final /* synthetic */ zzs zzi = null;
    public static final /* synthetic */ zzs zzj = null;
    public static final /* synthetic */ zzs zzk = null;
    public static final /* synthetic */ zzs zzl = null;
    public static final /* synthetic */ zzs zzm = null;
    public static final /* synthetic */ zzs zzn = null;
    public static final /* synthetic */ zzs zzo = null;
    public static final /* synthetic */ zzs zzp = null;
    private final /* synthetic */ int zzq;

    static {
        int r0 = (((((~386839851) & 502322068) | 1879579667) + ((386839851 & 1341449093) | 1376723995)) - (-1804183289)) ^ (2118801173 % 1119399015);
        int r7 = (((((~310242589) & 1847460522) | 916960512) + ((310242589 & 1486357226) | 346393668)) - 2000278179) ^ (2125023787 % 550804899);
        int r14 = (((((~2025251990) & 688917531) | 1994836270) + ((2025251990 & 152053269) | 883831534)) - (-1390790891)) ^ (1069117832 % 942581876);
        int r1 = (((((~1650994571) & 1371186185) | 1050017619) + ((1650994571 & 1227505800) | 205643505)) - (-1965943233)) ^ (1992865128 % 986478257);
        int r15 = (((((~1199680559) & 1141516237) | 463752220) + ((1199680559 & 1150952403) | 43369530)) - 1260832081) ^ (1435001171 % 1047196295);
        int r12 = (((((~1456339643) & 675645608) | 1411114002) + ((1456339643 & 693897896) | 1427718916)) - (-1019776501)) ^ (1561812722 % 1322623287);
        int r10 = (((((~1978701535) & 466139520) | 1762924265) + ((1978701535 & 315141952) | 1765037774)) - (-506179056)) ^ (1934660183 % 1880346039);
        int r8 = (((((~222028828) & 1142841824) | 166034459) + ((222028828 & 1142555104) | 723801104)) - 1805567702) ^ (829570037 % 301373537);
        int r5 = (((((~1396918184) & 1248054849) | 133905852) + ((1396918184 & 1770570357) | 870268086)) - 2138058592) ^ (1934392735 % 620089368);
        int r3 = (((((~1889804310) & 69748736) | 707083889) + ((1889804310 & 604795188) | 951435263)) - 1284100921) ^ (1663080928 % 610506582);
        int r2 = (((((~672139932) & 1821026947) | 1629321417) + ((672139932 & 214090242) | 828986457)) - (-2020274916)) ^ (1579068977 % 395191309);
        int r4 = (((((~99568484) & 3432473) | 44796710) + ((99568484 & 403994653) | 1028298918)) - 934561551) ^ (1076919591 % 234262547);
        zzp = new zzs((((((~1759592334) & 1334334268) | 1043988652) + ((1759592334 & 1099191056) | 509413516)) - (-1669029722)) ^ (599529154 % 33713861));
        zzo = new zzs((((((~1160063179) & 1841305796) | 575720514) + ((1160063179 & 1300254868) | 1886521)) - 1545499812) ^ (1060494695 % 728149010));
        zzn = new zzs(r4);
        zzm = new zzs(r14);
        zzl = new zzs(r1);
        zzk = new zzs(r15);
        zzj = new zzs(r7);
        zzi = new zzs(r12);
        zzh = new zzs(r10);
        zzg = new zzs(r8);
        zzf = new zzs(r0);
        zze = new zzs(r5);
        zzd = new zzs(r3);
        zzc = new zzs(r2);
        zzb = new zzs(1);
        zza = new zzs(0);
    }

    private /* synthetic */ zzs(int r1) {
        this.zzq = r1;
    }

    @Override // java.util.function.Function
    public final /* synthetic */ Object apply(Object r10) {
        int r1 = ((((~1301053842) & 15767913) | 186261585) + ((1301053842 & (-2132756056)) | (-887741291))) - (-855860664);
        int r2 = 1616992200 % 241315329;
        switch(this.zzq) {
            case 0: goto L116;
            case 1: goto L119;
            case 2: goto L88;
            case 3: goto L79;
            case 4: goto L74;
            case 5: goto L71;
            case 6: goto L67;
            case 7: goto L63;
            case 8: goto L59;
            case 9: goto L53;
            case 10: goto L48;
            case 11: goto L42;
            case 12: goto L31;
            case 13: goto L24;
            case 14: goto L16;
            default: goto L123;
        };
    L16:
        zzam r102 = (zzam) r10;
        r102.zzb.zzb(zzar.zzg(r102.zzb.zzc().zzl()));     // Catch: zzah -> L21 zzao -> L23
        return Optional.empty();
    L23:
        zzc r9 = zzc.zzp;
    L114:
        return Optional.of(r9);
    L24:
        zzam r103 = (zzam) r10;
        zzai r92 = r103.zzb;     // Catch: Throwable -> L14 Throwable -> L15 Throwable -> L28 zzad -> L29 zzae -> L30
        long r0 = r92.zzc().zzm();     // Catch: Throwable -> L14 Throwable -> L15 Throwable -> L28 zzad -> L29 zzae -> L30
        long r5 = r92.zzc().zzm();     // Catch: Throwable -> L14 Throwable -> L15 Throwable -> L28 zzad -> L29 zzae -> L30
        zzaf r22 = r103.zzc;     // Catch: Throwable -> L14 Throwable -> L15 Throwable -> L28 zzad -> L29 zzae -> L30
        zzaa r93 = r103.zzd;     // Catch: Throwable -> L14 Throwable -> L15 Throwable -> L28 zzad -> L29 zzae -> L30
        r22.zza(r93.zzb(), r5, r22.zzb().zzb);     // Catch: Throwable -> L14 Throwable -> L15 Throwable -> L28 zzad -> L29 zzae -> L30
        r93.zza(r0);     // Catch: Throwable -> L14 Throwable -> L15 Throwable -> L28 zzad -> L29 zzae -> L30
    L27:
        return Optional.empty();
    L29:
        r9 = zzc.zzB;
    L30:
        r9 = zzc.zzw;
    L28:
        r9 = zzc.zzr;
        goto L114
    L31:
        zzam r104 = (zzam) r10;
        long r02 = r104.zzb.zzc().zzm();     // Catch: Throwable -> L14 Throwable -> L15
        zzai r94 = r104.zzb;     // Catch: zzah -> L21 zzag -> L41
        int r23 = ((((~1349029729) & 1683806466) | 298308136) + ((1349029729 & (-199751405)) | (-1830723495))) - 438321650;
        int r52 = 1478326644 % 593443203;
        if (r02 != 0) goto L37;
        zzar r105 = r94.zzc();     // Catch: zzah -> L21 zzag -> L41
    L38:
        r94.zzb(r105);     // Catch: zzah -> L21 zzag -> L41
        return Optional.empty();
    L37:
        int r106 = r94.zza(r02);     // Catch: zzah -> L21 zzag -> L41
        r94.zzb += r23 ^ r52;
        r105 = (zzar) r94.zza.remove(r106);     // Catch: zzah -> L21 zzag -> L41
    L41:
        r9 = zzc.zzg;
        goto L114
    L42:
        zzam r107 = (zzam) r10;
        long r24 = r107.zzc.zzb().zzb + r107.zzd.zze();     // Catch: Throwable -> L14
        zzar r95 = r107.zzb.zzc();     // Catch: Throwable -> L14
        zzai r108 = r107.zzb;     // Catch: zzah -> L21 zzag -> L47
        long r03 = -r24;
        zzar r25 = r108.zzd(r03);     // Catch: zzah -> L21 zzag -> L47
        r108.zze(r03, r95);     // Catch: zzah -> L21 zzag -> L47
        r108.zzb(r25);     // Catch: zzah -> L21 zzag -> L47
        return Optional.empty();
    L48:
        zzam r109 = (zzam) r10;
        zzai r96 = r109.zzb;     // Catch: Throwable -> L14 Throwable -> L14 Throwable -> L15
        long r26 = r109.zzc.zzb().zzb + r96.zzc().zzm();     // Catch: Throwable -> L14 Throwable -> L14 Throwable -> L15
        zzar r97 = r96.zzc();     // Catch: Throwable -> L14 Throwable -> L14 Throwable -> L15
        zzai r1010 = r109.zzb;     // Catch: zzah -> L21 zzag -> L47
        long r04 = -r26;
        zzar r27 = r1010.zzd(r04);     // Catch: zzah -> L21 zzag -> L47
        r1010.zze(r04, r97);     // Catch: zzah -> L21 zzag -> L47
        r1010.zzb(r27);     // Catch: zzah -> L21 zzag -> L47
        return Optional.empty();
    L53:
        zzam r1011 = (zzam) r10;
        zzai r98 = r1011.zzb;     // Catch: Throwable -> L14 Throwable -> L15
        long r05 = r98.zzc().zzm();     // Catch: Throwable -> L14 Throwable -> L15
        zzar r99 = r98.zzc();     // Catch: Throwable -> L14 Throwable -> L15
        zzai r1012 = r1011.zzb;     // Catch: zzah -> L21 zzag -> L47
        zzar r28 = r1012.zzd(r05);     // Catch: zzah -> L21 zzag -> L47
        r1012.zze(r05, r99);     // Catch: zzah -> L21 zzag -> L47
        r1012.zzb(r28);     // Catch: zzah -> L21 zzag -> L47
        return Optional.empty();
    L74:
        zzam r1013 = (zzam) r10;
        long r29 = r1013.zzc.zzb().zzb + r1013.zzd.zze();     // Catch: Throwable -> L14 Throwable -> L14 Throwable -> L15 Throwable -> L15
        r1013.zzb.zze(-r29, r1013.zzb.zzc());     // Catch: zzag -> L47
        return Optional.empty();
    L79:
        zzam r1014 = (zzam) r10;
        zzai r910 = r1014.zzb;     // Catch: Throwable -> L14 Throwable -> L15
        zzar r06 = r910.zzc();     // Catch: Throwable -> L14 Throwable -> L15
        zzv r911 = r910.zzc().zzn();     // Catch: Throwable -> L14 Throwable -> L15
        ByteArrayOutputStream r3 = new ByteArrayOutputStream();
        zzr.zzb(r06.zzm(), new zzaq(r3, r1 ^ r2), false);     // Catch: zzao -> L87 IOException -> L96
        r1014.zzb.zzb(zzar.zzd(r911.zzd(zzv.zze(r3.toByteArray()))));     // Catch: zzah -> L21
        return Optional.empty();
    L88:
        zzam r1015 = (zzam) r10;
        zzai r912 = r1015.zzb;     // Catch: Throwable -> L14 Throwable -> L15
        zzar r07 = r912.zzc();     // Catch: Throwable -> L14 Throwable -> L15
        zzv r913 = r912.zzc().zzn();     // Catch: Throwable -> L14 Throwable -> L15
        ByteArrayOutputStream r12 = new ByteArrayOutputStream();
        r07.zzk(r12);     // Catch: zzao -> L87 IOException -> L96
        r1015.zzb.zzb(zzar.zzd(r913.zzd(zzv.zze(r12.toByteArray()))));     // Catch: zzah -> L21
        return Optional.empty();
    L123:
        zzai r914 = ((zzam) r10).zzb;     // Catch: zzah -> L12 Throwable -> L14 Throwable -> L15
        Iterator r1016 = r914.zzc().zzo().iterator();     // Catch: zzah -> L12 Throwable -> L14 Throwable -> L15
    L6:
        if (r1016.hasNext() == false) goto L11;
        r914.zzb((zzar) r1016.next());     // Catch: zzah -> L12 Throwable -> L14 Throwable -> L15
        goto L6
    L11:
        return Optional.empty();
    L12:
        r9 = zzc.zza;
        goto L114
    L59:
        zzai r915 = ((zzam) r10).zzb;     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21
        r915.zzb(zzar.zzb(r915.zzc().zzm() - r915.zzc().zzm()));     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21
        return Optional.empty();
    L63:
        zzai r916 = ((zzam) r10).zzb;     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21
        r916.zzb(zzar.zzc(r916.zzc().zzq() - r916.zzc().zzq()));     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21
        return Optional.empty();
    L67:
        zzai r917 = ((zzam) r10).zzb;     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21
        r917.zzb(zzar.zzb(r917.zzc().zzm() >>> ((int) r917.zzc().zzm())));     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21
        return Optional.empty();
    L71:
        zzai r918 = ((zzam) r10).zzb;     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21
        r918.zzb(zzar.zzb(r918.zzc().zzm() << ((int) r918.zzc().zzm())));     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21
        return Optional.empty();
    L119:
        zzai r919 = ((zzam) r10).zzb;     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21 zzap -> L113
        long r08 = r919.zzc().zzm();     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21 zzap -> L113
        zzar r1017 = r919.zzc();     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21 zzap -> L113
        List r210 = r1017.zzo();     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21 zzap -> L113
        if (r08 >= 0) goto L103;
        r08 = r08 + r210.size();     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21 zzap -> L113
    L103:
        if (r08 < 0) goto L110;
        if (r08 >= r210.size()) goto L110;
        r210.remove((int) r08);     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21 zzap -> L113
        r919.zzb(r1017);     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21 zzap -> L113
        return Optional.empty();
    L110:
        throw new zzap();     // Catch: Throwable -> L14 Throwable -> L15 zzah -> L21 zzap -> L113
    L113:
        r9 = zzc.zzI;
        goto L114
    L116:
        return ((zzam) r10).zza();
    L14:
        r9 = zzc.zzx;
    L21:
        e = e;
    L112:
        throw new AssertionError(zzt.zza("CEiv6BFfPnitUE+D"), e);
    L15:
        r9 = zzc.zzy;
    L47:
        r9 = zzc.zzh;
    L87:
        r9 = zzc.zzk;
    L96:
        e = e;
        goto L112
    }
}
