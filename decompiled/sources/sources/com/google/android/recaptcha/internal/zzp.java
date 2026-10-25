package com.google.android.recaptcha.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzp implements zzaj {
    public static final /* synthetic */ zzp zza = null;
    public static final /* synthetic */ zzp zzb = null;
    public static final /* synthetic */ zzp zzc = null;
    public static final /* synthetic */ zzp zzd = null;
    public static final /* synthetic */ zzp zze = null;
    public static final /* synthetic */ zzp zzf = null;
    public static final /* synthetic */ zzp zzg = null;
    public static final /* synthetic */ zzp zzh = null;
    public static final /* synthetic */ zzp zzi = null;
    public static final /* synthetic */ zzp zzj = null;
    public static final /* synthetic */ zzp zzk = null;
    public static final /* synthetic */ zzp zzl = null;
    public static final /* synthetic */ zzp zzm = null;
    public static final /* synthetic */ zzp zzn = null;
    public static final /* synthetic */ zzp zzo = null;
    public static final /* synthetic */ zzp zzp = null;
    public static final /* synthetic */ zzp zzq = null;
    public static final /* synthetic */ zzp zzr = null;
    public static final /* synthetic */ zzp zzs = null;
    public static final /* synthetic */ zzp zzt = null;
    public static final /* synthetic */ zzp zzu = null;
    private final /* synthetic */ int zzv;

    static {
        int r0 = (((((~1163384280) & 546336878) | 505597078) + ((1163384280 & 546323048) | 358992768)) - 1346988615) ^ (1124734562 % 530406424);
        int r7 = (((((~86086317) & 1644315690) | 229847203) + ((86086317 & 1644710940) | 145693589)) - 1464982814) ^ (2016408437 % 730832933);
        int r1 = (((((~306851320) & 1264822912) | 1640587974) + ((306851320 & 171608133) | 77403775)) - 1652838498) ^ (1764892438 % 764851988);
        int r14 = (((((~1245644428) & 268473443) | 2019232271) + ((1245644428 & 2266720) | 1693582210)) - (-827594177)) ^ (1308581515 % 354367395);
        int r2 = (((((~1659373349) & 67158599) | 37727454) + ((1659373349 & 1158693377) | 1765350732)) - 1760416061) ^ (1773595097 % 554602408);
        int r3 = (((((~1702943003) & 814688134) | 1149507933) + ((1702943003 & 806043266) | 1183897648)) - (-1377255939)) ^ (1704572553 % 1482841197);
        int r4 = (((((~263043320) & 60114514) | 41169773) + ((263043320 & (-2122202061)) | (-1707691999))) - 2084316433) ^ (1802491982 % 1233114544);
        zzu = new zzp((((((~1183912267) & 781500502) | 1683555200) + ((1183912267 & 180666614) | 541077665)) - (-1949988631)) ^ (1527793660 % 245277883));
        zzt = new zzp((((((~960188534) & 168264392) | 75092503) + ((960188534 & 171312328) | 1886434865)) - 1988142396) ^ (2097657371 % 978053418));
        zzs = new zzp((((((~2113556942) & 940077738) | 1775573937) + ((2113556942 & 269019163) | 1630701617)) - (-641336821)) ^ (1412017135 % 1390387708));
        zzr = new zzp(r4);
        zzq = new zzp(r14);
        zzp = new zzp(r2);
        zzp r22 = new zzp(r3);
        int r32 = (((((~1173002606) & 1578625684) | 461390155) + ((1173002606 & 1150790292) | 63439977)) - 1301607397) ^ (1316505735 % 950881304);
        int r42 = (((((~1701475883) & 1167691919) | 1798790684) + ((1701475883 & 617250947) | 846663956)) - (-1618778093)) ^ (118993446 % 73673339);
        int r5 = (((((~1389079342) & 405954796) | 5768195) + ((1389079342 & 967468028) | 1640566546)) - 1618010498) ^ (1588695568 % 1155465115);
        int r6 = (((((~338346092) & 646267921) | 2030210832) + ((338346092 & 109431171) | 1768591350)) - (-956795161)) ^ (1912163036 % 671068506);
        int r10 = (((((~2107654819) & 15073922) | 1957914701) + ((2107654819 & (-2142502266)) | (-1902504939))) - (-100222276)) ^ (1835342733 % 837626799);
        int r8 = (((((~1666231349) & 289538435) | 621649448) + ((1666231349 & 406985107) | 264059440)) - 1093855299) ^ (1698487330 % 272312086);
        int r9 = (((((~1111088131) & 1881672162) | 1222111317) + ((1111088131 & 807995811) | 38123093)) - 1508183853) ^ (1348361729 % 788380902);
        int r11 = (((((~592456289) & 122915509) | 1765820355) + ((592456289 & (-1911322572)) | (-1735916853))) - (-404346116)) ^ (1987235624 % 1451042659);
        int r12 = (((((~1020406649) & 1025610818) | 1309027208) + ((1020406649 & 1931582530) | 1180322452)) - (-1047179772)) ^ (1444311956 % 153162844);
        zzo = r22;
        zzn = new zzp(r1);
        zzm = new zzp(r32);
        zzl = new zzp(r42);
        zzk = new zzp(r5);
        zzj = new zzp(r7);
        zzi = new zzp(r6);
        zzh = new zzp(r10);
        zzg = new zzp(r8);
        zzf = new zzp(r0);
        zze = new zzp(r9);
        zzd = new zzp(r11);
        zzc = new zzp(r12);
        zzb = new zzp(1);
        zza = new zzp(0);
    }

    private /* synthetic */ zzp(int r1) {
        this.zzv = r1;
    }

    @Override // java.util.function.Function
    public final /* synthetic */ Object apply(Object r15) {
        int r1 = 0;
        long r3 = 1;
        long r5 = 0;
        switch(this.zzv) {
            case 0: goto L163;
            case 1: goto L177;
            case 2: goto L140;
            case 3: goto L137;
            case 4: goto L133;
            case 5: goto L129;
            case 6: goto L119;
            case 7: goto L111;
            case 8: goto L101;
            case 9: goto L88;
            case 10: goto L74;
            case 11: goto L175;
            case 12: goto L62;
            case 13: goto L58;
            case 14: goto L54;
            case 15: goto L46;
            case 16: goto L38;
            case 17: goto L34;
            case 18: goto L29;
            case 19: goto L25;
            default: goto L4;
        };
    L4:
        long r32 = ((((~1638823523) & 1470327826) | 268480728) + ((1638823523 & 1872973067) | 672433085)) - 448653836;
        long r7 = 1829878520 % 1376539012;
        long r9 = ((((~257620370) & 608506385) | 363629956) + ((257620370 & 943920785) | 1530817674)) - 1774142815;
        long r11 = 1500173960 % 838733761;
        zzam r152 = (zzam) r15;
        zzaa r14 = r152.zzd;     // Catch: zzy -> L14 zzz -> L21 zzah -> L23 zzx -> L24
        long r12 = r14.zze();     // Catch: zzy -> L14 zzz -> L21 zzah -> L23 zzx -> L24
        if (r12 < 0) goto L20;
        if (r12 > (r32 ^ r7)) goto L20;
        if (((r9 ^ r11) & r12) != 0) goto L13;
        r152.zzb.zzb(zzar.zzd(r14.zzf(r12)));     // Catch: zzy -> L14 zzz -> L21 zzah -> L23 zzx -> L24
        return Optional.empty();
    L13:
        return Optional.of(zzc.zze);
    L20:
        return Optional.of(zzc.zzz);
    L25:
        zzam r153 = (zzam) r15;
        r153.zzb.zzb(zzar.zzb(r153.zzd.zze()));     // Catch: zzz -> L21 zzah -> L23 zzx -> L24
        return Optional.empty();
    L29:
        zzam r154 = (zzam) r15;
        ByteBuffer r142 = ByteBuffer.wrap(r154.zzd.zzf((((((~2093882409) & 1745263598) | 1919681973) + ((2093882409 & 201570906) | 608510005)) - 2293625313L) ^ (2017117507 % 549387536)).zza());     // Catch: zzy -> L14 zzz -> L21 zzah -> L23
        r142.order(ByteOrder.LITTLE_ENDIAN);     // Catch: zzy -> L14 zzz -> L21 zzah -> L23
        r154.zzb.zzb(zzar.zzc(Double.longBitsToDouble(r142.getLong())));     // Catch: zzy -> L14 zzz -> L21 zzah -> L23
        return Optional.empty();
    L74:
        int[] r143 = {13146790, 575946145, 1620790320, 1246507401, 1292535914, -1004059703, 819634850, 1194559277, 714481123};
        int r33 = r143[0];
        int r2 = r143[1];
        int r4 = r143[2];
        int r52 = r143[3];
        int r6 = r143[4];
        int r72 = r143[5];
        int r8 = r143[6];
        int r144 = r143[7];
        int r82 = r8 + ((((r2 & (~r33)) | r4) + ((r33 & r52) | r6)) - r72);
        int r145 = r144 % 714481123;
        zzam r155 = (zzam) r15;
        zzai r22 = r155.zzb;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 Throwable -> L87
        long r34 = r22.zzc().zzm();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 Throwable -> L87
        zzh r62 = r155.zza;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 Throwable -> L87
        zzai r73 = new zzai(r145 ^ r82);     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 Throwable -> L87
    L173:
        if (r1 >= r22.zzb) goto L82;
        r73.zzb(zzar.zzj((zzar) r22.zza.get(r1)));     // Catch: zzao -> L43 zzag -> L44 zzah -> L80 Throwable -> L87 Throwable -> L87
        r1 = r1 + 1;
        goto L173
    L82:
        zzaa r146 = r155.zzd;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 Throwable -> L87 Throwable -> L87
        new zzam(r62, r73, new zzaa(r146.zzb, r146.zza, r146.zzc.zzc(), r146.zzd)).zzd.zza(r34);     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 Throwable -> L87 Throwable -> L87
        r22.zzb(zzar.zza(new zzr()));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 Throwable -> L87 Throwable -> L87
        return Optional.empty();
    L80:
        e = move-exception;
        throw new AssertionError(zzt.zza("CEiv6BFfPnitUE+D"), e);     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 Throwable -> L87 Throwable -> L87
    L87:
        zzc r147 = zzc.zzt;
    L180:
        return Optional.of(r147);
    L88:
        zzam r156 = (zzam) r15;
        zzai r148 = r156.zzb;     // Catch: zzah -> L41 zzag -> L44
        zzar r23 = r148.zzc();     // Catch: zzah -> L41 zzag -> L44
        int r149 = new zzan(false).compare(r148.zzc(), r23);     // Catch: zzah -> L41 zzag -> L44 IllegalArgumentException -> L98
        zzai r157 = r156.zzb;     // Catch: zzah -> L41 zzag -> L44
        if (r149 < 0) goto L95;
        r3 = 0;
    L95:
        r157.zzb(zzar.zzb(r3));     // Catch: zzah -> L41 zzag -> L44
        return Optional.empty();
    L181:
        return Optional.of(zzc.zzd);
    L140:
        zzam r158 = (zzam) r15;
        zzai r1410 = r158.zzb;     // Catch: zzao -> L43 zzag -> L44 Throwable -> L146
        long r0 = r1410.zzc().zzm();     // Catch: zzao -> L43 zzag -> L44 Throwable -> L146
        if (r1410.zzc().zzm() != 0) goto L145;
        r158.zzd.zza(r0);     // Catch: zzao -> L43 zzag -> L44 Throwable -> L146
    L145:
        return Optional.empty();
    L163:
        zzam r159 = (zzam) r15;
        zzai r1411 = r159.zzb;     // Catch: zzao -> L43 zzag -> L44 Throwable -> L146 Throwable -> L146
        long r02 = r1411.zzc().zzm();     // Catch: zzao -> L43 zzag -> L44 Throwable -> L146 Throwable -> L146
        if (r1411.zzc().zzm() == 0) goto L168;
        r159.zzd.zza(r02);     // Catch: zzao -> L43 zzag -> L44 Throwable -> L146 Throwable -> L146
    L168:
        return Optional.empty();
    L34:
        ((zzam) r15).zzb.zzb(zzar.zza(null));     // Catch: zzah -> L23
        return Optional.empty();
    L38:
        zzai r1412 = ((zzam) r15).zzb;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        r1412.zzb(zzar.zzc(Math.pow(r1412.zzc().zzq(), r1412.zzc().zzq())));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        return Optional.empty();
    L46:
        zzai r1413 = ((zzam) r15).zzb;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        long r13 = r1413.zzc().zzm();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        ArrayList r1510 = new ArrayList();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
    L48:
        if (r5 >= r13) goto L50;
        r1510.add(r1413.zzc());     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        r5 = r5 + 1;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        goto L48
    L50:
        Collections.reverse(r1510);     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        r1413.zzb(zzar.zze(r1510));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        return Optional.empty();
    L54:
        zzai r1414 = ((zzam) r15).zzb;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        r1414.zzb(zzar.zzb(r1414.zzc().zzm() * r1414.zzc().zzm()));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        return Optional.empty();
    L58:
        zzai r1415 = ((zzam) r15).zzb;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        r1415.zzb(zzar.zzc(r1415.zzc().zzq() * r1415.zzc().zzq()));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        return Optional.empty();
    L62:
        zzai r1416 = ((zzam) r15).zzb;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        long r16 = r1416.zzc().zzm();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        long r35 = r1416.zzc().zzm();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        if (r16 == 0) goto L65;
        r1416.zzb(zzar.zzb(r35 % r16));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        return Optional.empty();
    L65:
        return Optional.of(zzc.zzF);
    L175:
        zzai r1417 = ((zzam) r15).zzb;     // Catch: zzah -> L41 zzag -> L44 zzao -> L73
        r1417.zzb(zzar.zza(r1417.zzc().zzi((Class) r1417.zzc().zzl())));     // Catch: zzah -> L41 zzag -> L44 zzao -> L73
        return Optional.empty();
    L73:
        r147 = zzc.zzp;
        goto L180
    L101:
        zzai r1418 = ((zzam) r15).zzb;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        long r17 = r1418.zzc().zzm();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        long r74 = r1418.zzc().zzm();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        if (r17 == 0) goto L104;
    L107:
        r1418.zzb(zzar.zzb(r3));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        return Optional.empty();
    L104:
        if (r74 != 0) goto L107;
        r3 = 0;
        goto L107
    L111:
        zzai r1419 = ((zzam) r15).zzb;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        if (r1419.zzc().zzm() == 0) goto L115;
        r3 = 0;
    L115:
        r1419.zzb(zzar.zzb(r3));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        return Optional.empty();
    L119:
        zzai r1420 = ((zzam) r15).zzb;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        long r18 = r1420.zzc().zzm();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        long r75 = r1420.zzc().zzm();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        if (r18 != 0) goto L122;
    L124:
        r3 = 0;
    L125:
        r1420.zzb(zzar.zzb(r3));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        return Optional.empty();
    L122:
        if (r75 == 0) goto L124;
    L129:
        zzai r1421 = ((zzam) r15).zzb;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        r1421.zzb(zzar.zzc(Math.log(r1421.zzc().zzq())));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        return Optional.empty();
    L133:
        ((zzam) r15).zzb.zzb(zzar.zzb(r14.zzc().zzn().zza.length));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        return Optional.empty();
    L137:
        ((zzam) r15).zzb.zzb(zzar.zzb(r14.zzc().zzo().size()));     // Catch: zzah -> L41 zzao -> L43 zzag -> L44
        return Optional.empty();
    L177:
        zzai r1422 = ((zzam) r15).zzb;     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 zzap -> L162
        long r36 = r1422.zzc().zzm();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 zzap -> L162
        zzar r1511 = r1422.zzc();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 zzap -> L162
        zzar r19 = r1422.zzc();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 zzap -> L162
        List r76 = r19.zzo();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 zzap -> L162
        if (r36 >= 0) goto L152;
        r36 = r36 + (r76.size() + 1);     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 zzap -> L162
    L152:
        if (r36 < 0) goto L159;
        if (r36 >= (r76.size() + 1)) goto L159;
        r76.add((int) r36, r1511);     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 zzap -> L162
        r1422.zzb(r19);     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 zzap -> L162
        return Optional.empty();
    L159:
        throw new zzap();     // Catch: zzah -> L41 zzao -> L43 zzag -> L44 zzap -> L162
    L162:
        r147 = zzc.zzI;
    L24:
        r147 = zzc.zzD;
    L146:
        r147 = zzc.zzi;
    L44:
        r147 = zzc.zzx;
    L14:
        e = e;
    L161:
        throw new AssertionError(zzt.zza("CEiv6BFfPnitUE+D"), e);
    L41:
        e = e;
    L23:
        r147 = zzc.zza;
    L43:
        r147 = zzc.zzy;
    L21:
        r147 = zzc.zzz;
        goto L180
    }
}
