package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzba {
    public static zzao zza(String r10, zzae r11, zzg r12, List r13) {
        Double r0 = Double.valueOf(-1.0d);
        String r2 = ",";
        double r4 = 0.0d;
        zzai r6 = null;
        int r7 = 2;
        int r9 = 0;
        switch(r10.hashCode()) {
            case -1776922004: goto L312;
            case -1354795244: goto L293;
            case -1274492040: goto L278;
            case -934873754: goto L274;
            case -895859076: goto L242;
            case -678635926: goto L230;
            case -467511597: goto L200;
            case -277637751: goto L178;
            case 107868: goto L166;
            case 111185: goto L158;
            case 3267882: goto L141;
            case 3452698: goto L131;
            case 3536116: goto L110;
            case 3536286: goto L94;
            case 96891675: goto L78;
            case 109407362: goto L70;
            case 109526418: goto L49;
            case 965561430: goto L45;
            case 1099846370: goto L32;
            case 1943291465: goto L6;
            default: goto L315;
        };
    L315:
        f$$ExternalSyntheticBUOutline1.m("Command not supported");
        return null;
    L6:
        if (r10.equals("indexOf") == false) goto L315;
        zzh.zzc("indexOf", 2, r13);
        zzao r102 = zzao.zzf;
        if (r13.isEmpty() == true) goto L11;
        r102 = r12.zza((zzao) r13.get(0));
    L11:
        if (r13.size() <= 1) goto L20;
        double r122 = zzh.zzi(r12.zza((zzao) r13.get(1)).zzd().doubleValue());
        if (r122 < r11.zzh()) goto L17;
        return new zzah(r0);
    L17:
        if (r122 >= 0.0d) goto L19;
        r4 = r11.zzh() + r122;
        goto L20
    L19:
        r4 = r122;
    L20:
        Iterator r123 = r11.zzg();
    L22:
        if (r123.hasNext() == false) goto L30;
        int r132 = ((Integer) r123.next()).intValue();
        double r1 = r132;
        if (r1 < r4) goto L22;
        if (zzh.zzf(r11.zzl(r132), r102) == false) goto L22;
        return new zzah(Double.valueOf(r1));
    L30:
        return new zzah(r0);
    L32:
        if (r10.equals("reverse") == false) goto L315;
        zzh.zza("reverse", 0, r13);
        int r103 = r11.zzh();
        if (r103 != 0) goto L36;
    L108:
        return r11;
    L36:
        if (r9 >= (r103 / 2)) goto L108;
        if (r11.zzo(r9) == false) goto L43;
        zzao r124 = r11.zzl(r9);
        r11.zzn(r9, null);
        int r133 = (r103 - 1) - r9;
        if (r11.zzo(r133) == false) goto L42;
        r11.zzn(r9, r11.zzl(r133));
    L42:
        r11.zzn(r133, r124);
    L43:
        r9 = r9 + 1;
        goto L36
    L45:
        if (r10.equals("reduceRight") == false) goto L315;
        return zzb(r11, r12, r13, false);
    L49:
        if (r10.equals("slice") == false) goto L315;
        zzh.zzc("slice", 2, r13);
        if (r13.isEmpty() == true) goto L53;
        double r02 = r11.zzh();
        double r22 = zzh.zzi(r12.zza((zzao) r13.get(0)).zzd().doubleValue());
        if (r22 >= 0.0d) goto L57;
        double r23 = Math.max(r22 + r02, 0.0d);
    L59:
        if (r13.size() != 2) goto L64;
        double r125 = zzh.zzi(r12.zza((zzao) r13.get(1)).zzd().doubleValue());
        if (r125 >= 0.0d) goto L63;
        r02 = Math.max(r02 + r125, 0.0d);
        goto L64
    L63:
        r02 = Math.min(r02, r125);
    L64:
        zzae r104 = new zzae();
        int r126 = (int) r23;
    L66:
        if (r126 >= r02) goto L68;
        r104.zzn(r104.zzh(), r11.zzl(r126));
        r126 = r126 + 1;
        goto L66
    L68:
        return r104;
    L57:
        r23 = Math.min(r22, r02);
        goto L59
    L53:
        return r11.zzt();
    L70:
        if (r10.equals("shift") == false) goto L315;
        zzh.zza("shift", 0, r13);
        if (r11.zzh() == 0) goto L74;
        zzao r105 = r11.zzl(0);
        r11.zzr(0);
        return r105;
    L74:
        return zzao.zzf;
    L78:
        if (r10.equals("every") == false) goto L315;
        zzh.zza("every", 1, r13);
        zzao r106 = r12.zza((zzao) r13.get(0));
        if ((r106 instanceof zzan) == true) goto L82;
        f$$ExternalSyntheticBUOutline1.m("Callback should be a method");
        return null;
    L82:
        if (r11.zzh() != 0) goto L86;
        return zzao.zzk;
    L86:
        if (zzc(r11, r12, (zzan) r106, Boolean.FALSE, Boolean.TRUE).zzh() == r11.zzh()) goto L90;
        return zzao.zzl;
    L90:
        return zzao.zzk;
    L94:
        if (r10.equals("sort") == false) goto L315;
        zzh.zzc("sort", 1, r13);
        if (r11.zzh() < 2) goto L108;
        List r107 = r11.zzb();
        if (r13.isEmpty() == true) goto L104;
        zzao r134 = r12.zza((zzao) r13.get(0));
        if ((r134 instanceof zzai) == false) goto L102;
        r6 = (zzai) r134;
        goto L104
    L102:
        f$$ExternalSyntheticBUOutline1.m("Comparator should be a method");
        return null;
    L104:
        Collections.sort(r107, new zzaz(r6, r12));
        r11.zzp();
        Iterator r108 = r107.iterator();
    L106:
        if (r108.hasNext() == false) goto L108;
        r11.zzn(r9, (zzao) r108.next());
        r9 = r9 + 1;
        goto L106
    L110:
        if (r10.equals("some") == false) goto L315;
        zzh.zza("some", 1, r13);
        zzao r109 = r12.zza((zzao) r13.get(0));
        if ((r109 instanceof zzai) == true) goto L114;
        f$$ExternalSyntheticBUOutline1.m("Callback should be a method");
        return null;
    L114:
        if (r11.zzh() == 0) goto L116;
        zzai r1010 = (zzai) r109;
        Iterator r135 = r11.zzg();
    L119:
        if (r135.hasNext() == false) goto L127;
        int r03 = ((Integer) r135.next()).intValue();
        if (r11.zzo(r03) == false) goto L119;
        if (r1010.zza(r12, Arrays.asList(new zzao[]{r11.zzl(r03), new zzah(Double.valueOf(r03)), r11})).zze().booleanValue() == false) goto L119;
        return zzao.zzk;
    L127:
        return zzao.zzl;
    L116:
        return zzao.zzl;
    L131:
        if (r10.equals("push") == false) goto L315;
        if (r13.isEmpty() == true) goto L139;
        Iterator r1011 = r13.iterator();
    L136:
        if (r1011.hasNext() == false) goto L139;
        r11.zzn(r11.zzh(), r12.zza((zzao) r1011.next()));
    L139:
        return new zzah(Double.valueOf(r11.zzh()));
    L141:
        if (r10.equals("join") == false) goto L315;
        zzh.zzc("join", 1, r13);
        if (r11.zzh() != 0) goto L147;
        return zzao.zzm;
    L147:
        if (r13.isEmpty() == true) goto L156;
        zzao r1012 = r12.zza((zzao) r13.get(0));
        if ((r1012 instanceof zzam) == false) goto L151;
    L154:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L156
    L151:
        if ((r1012 instanceof zzat) == true) goto L154;
        r2 = r1012.zzc();
    L156:
        return new zzas(r11.zzs(r2));
    L158:
        if (r10.equals("pop") == false) goto L315;
        zzh.zza("pop", 0, r13);
        int r1013 = r11.zzh();
        if (r1013 == 0) goto L162;
        int r1014 = r1013 - 1;
        zzao r127 = r11.zzl(r1014);
        r11.zzr(r1014);
        return r127;
    L162:
        return zzao.zzf;
    L166:
        if (r10.equals("map") == false) goto L315;
        zzh.zza("map", 1, r13);
        zzao r1015 = r12.zza((zzao) r13.get(0));
        if ((r1015 instanceof zzan) == true) goto L170;
        f$$ExternalSyntheticBUOutline1.m("Callback should be a method");
        return null;
    L170:
        if (r11.zzh() != 0) goto L174;
        return new zzae();
    L174:
        return zzc(r11, r12, (zzan) r1015, null, null);
    L178:
        if (r10.equals("unshift") == false) goto L315;
        if (r13.isEmpty() == true) goto L198;
        zzae r1016 = new zzae();
        Iterator r136 = r13.iterator();
    L183:
        if (r136.hasNext() == false) goto L189;
        zzao r04 = r12.zza((zzao) r136.next());
        if ((r04 instanceof zzag) == true) goto L187;
        r1016.zzn(r1016.zzh(), r04);
        goto L183
    L187:
        Segment$$ExternalSyntheticBUOutline1.m("Argument evaluation failed");
        return null;
    L189:
        int r128 = r1016.zzh();
        Iterator r137 = r11.zzg();
    L191:
        if (r137.hasNext() == false) goto L193;
        Integer r05 = (Integer) r137.next();
        r1016.zzn(r05.intValue() + r128, r11.zzl(r05.intValue()));
        goto L191
    L193:
        r11.zzp();
        Iterator r129 = r1016.zzg();
    L195:
        if (r129.hasNext() == false) goto L198;
        Integer r138 = (Integer) r129.next();
        r11.zzn(r138.intValue(), r1016.zzl(r138.intValue()));
    L198:
        return new zzah(Double.valueOf(r11.zzh()));
    L200:
        if (r10.equals("lastIndexOf") == false) goto L315;
        zzh.zzc("lastIndexOf", 2, r13);
        zzao r1017 = zzao.zzf;
        if (r13.isEmpty() == true) goto L204;
        r1017 = r12.zza((zzao) r13.get(0));
    L204:
        int r14 = r11.zzh() - 1;
        if (r13.size() <= 1) goto L213;
        zzao r1210 = r12.zza((zzao) r13.get(1));
        if (Double.isNaN(r1210.zzd().doubleValue()) == false) goto L209;
        double r1211 = r11.zzh() - 1;
    L211:
        if (r1211 >= 0.0d) goto L215;
        r1211 = r1211 + r11.zzh();
    L215:
        if (r1211 < 0.0d) goto L217;
        int r1212 = (int) Math.min(r11.zzh(), r1211);
    L219:
        if (r1212 < 0) goto L228;
        if (r11.zzo(r1212) == false) goto L226;
        if (zzh.zzf(r11.zzl(r1212), r1017) == false) goto L226;
        return new zzah(Double.valueOf(r1212));
    L226:
        r1212 = r1212 - 1;
        goto L219
    L228:
        return new zzah(r0);
    L217:
        return new zzah(r0);
    L209:
        r1211 = zzh.zzi(r1210.zzd().doubleValue());
        goto L211
    L213:
        r1211 = r14;
        goto L215
    L230:
        if (r10.equals("forEach") == false) goto L315;
        zzh.zza("forEach", 1, r13);
        zzao r1018 = r12.zza((zzao) r13.get(0));
        if ((r1018 instanceof zzan) == true) goto L234;
        f$$ExternalSyntheticBUOutline1.m("Callback should be a method");
        return null;
    L234:
        if (r11.zzi() == 0) goto L236;
        zzc(r11, r12, (zzan) r1018, null, null);
        return zzao.zzf;
    L236:
        return zzao.zzf;
    L242:
        if (r10.equals("splice") == false) goto L315;
        if (r13.isEmpty() == true) goto L246;
        int r1019 = (int) zzh.zzi(r12.zza((zzao) r13.get(0)).zzd().doubleValue());
        if (r1019 >= 0) goto L251;
        r1019 = Math.max(0, r1019 + r11.zzh());
    L253:
        int r06 = r11.zzh();
        zzae r15 = new zzae();
        if (r13.size() <= 1) goto L270;
        int r24 = Math.max(0, (int) zzh.zzi(r12.zza((zzao) r13.get(1)).zzd().doubleValue()));
        if (r24 <= 0) goto L262;
        int r3 = r1019;
    L259:
        if (r3 >= Math.min(r06, r1019 + r24)) goto L262;
        r15.zzn(r15.zzh(), r11.zzl(r1019));
        r11.zzr(r1019);
        r3 = r3 + 1;
    L262:
        if (r13.size() > 2) goto L264;
    L272:
        return r15;
    L264:
        if (r7 >= r13.size()) goto L272;
        zzao r07 = r12.zza((zzao) r13.get(r7));
        if ((r07 instanceof zzag) == true) goto L268;
        r11.zzq((r1019 + r7) - 2, r07);
        r7 = r7 + 1;
        goto L264
    L268:
        f$$ExternalSyntheticBUOutline1.m("Failed to parse elements to add");
        return null;
    L270:
        if (r1019 >= r06) goto L272;
        r15.zzn(r15.zzh(), r11.zzl(r1019));
        r11.zzn(r1019, null);
        r1019 = r1019 + 1;
        goto L270
    L251:
        if (r1019 <= r11.zzh()) goto L253;
        r1019 = r11.zzh();
        goto L253
    L246:
        return new zzae();
    L274:
        if (r10.equals("reduce") == false) goto L315;
        return zzb(r11, r12, r13, true);
    L278:
        if (r10.equals("filter") == false) goto L315;
        zzh.zza("filter", 1, r13);
        zzao r1020 = r12.zza((zzao) r13.get(0));
        if ((r1020 instanceof zzan) == true) goto L282;
        f$$ExternalSyntheticBUOutline1.m("Callback should be a method");
        return null;
    L282:
        if (r11.zzi() == 0) goto L284;
        zzae r139 = (zzae) r11.zzt();
        zzae r1021 = zzc(r11, r12, (zzan) r1020, null, Boolean.TRUE);
        zzae r112 = new zzae();
        Iterator r1022 = r1021.zzg();
    L287:
        if (r1022.hasNext() == false) goto L289;
        r112.zzn(r112.zzh(), r139.zzl(((Integer) r1022.next()).intValue()));
        goto L287
    L289:
        return r112;
    L284:
        return new zzae();
    L293:
        if (r10.equals("concat") == false) goto L315;
        zzae r1023 = (zzae) r11.zzt();
        if (r13.isEmpty() == true) goto L310;
        Iterator r113 = r13.iterator();
    L298:
        if (r113.hasNext() == false) goto L310;
        zzao r1310 = r12.zza((zzao) r113.next());
        if ((r1310 instanceof zzag) == true) goto L308;
        int r08 = r1023.zzh();
        if ((r1310 instanceof zzae) == true) goto L303;
        r1023.zzn(r08, r1310);
        goto L298
    L303:
        zzae r1311 = (zzae) r1310;
        Iterator r16 = r1311.zzg();
    L305:
        if (r16.hasNext() == false) goto L298;
        Integer r25 = (Integer) r16.next();
        r1023.zzn(r25.intValue() + r08, r1311.zzl(r25.intValue()));
        goto L305
    L308:
        Segment$$ExternalSyntheticBUOutline1.m("Failed evaluation of arguments");
        return null;
    L310:
        return r1023;
    L312:
        if (r10.equals("toString") == false) goto L315;
        zzh.zza("toString", 0, r13);
        return new zzas(r11.zzs(","));
    }

    private static zzao zzb(zzae r11, zzg r12, List r13, boolean r14) {
        zzh.zzb("reduce", 1, r13);
        zzh.zzc("reduce", 2, r13);
        zzao r3 = r12.zza((zzao) r13.get(0));
        if ((r3 instanceof zzai) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Callback should be a method");
        return null;
    L5:
        if (r13.size() != 2) goto L12;
        zzao r132 = r12.zza((zzao) r13.get(1));
        if ((r132 instanceof zzag) == false) goto L14;
        f$$ExternalSyntheticBUOutline1.m("Failed to parse initial value");
        return null;
    L14:
        zzai r32 = (zzai) r3;
        int r4 = r11.zzh();
        if (r14 == false) goto L17;
        int r6 = 0;
    L18:
        int r7 = -1;
        if (r14 == false) goto L21;
        int r42 = r4 - 1;
    L22:
        if (true != r14) goto L25;
        r7 = 1;
    L25:
        if (r132 != null) goto L29;
        r132 = r11.zzl(r6);
        r6 = r6 + r7;
    L29:
        if (((r42 - r6) * r7) < 0) goto L37;
        if (r11.zzo(r6) == true) goto L32;
        r6 = r6 + r7;
        goto L29
    L32:
        r132 = r32.zza(r12, Arrays.asList(new zzao[]{r132, r11.zzl(r6), new zzah(Double.valueOf(r6)), r11}));
        if ((r132 instanceof zzag) == true) goto L35;
        r6 = r6 + r7;
        goto L29
    L35:
        Segment$$ExternalSyntheticBUOutline1.m("Reduce operation failed");
        return null;
    L37:
        return r132;
    L21:
        r42 = 0;
        goto L22
    L17:
        r6 = r4 - 1;
        goto L18
    L12:
        if (r11.zzh() == 0) goto L38;
        r132 = null;
        goto L14
    L38:
        Segment$$ExternalSyntheticBUOutline1.m("Empty array with no initial value error");
        return null;
    }

    private static zzae zzc(zzae r7, zzg r8, zzai r9, Boolean r10, Boolean r11) {
        zzae r0 = new zzae();
        Iterator r1 = r7.zzg();
    L4:
        if (r1.hasNext() == false) goto L14;
        int r2 = ((Integer) r1.next()).intValue();
        if (r7.zzo(r2) == false) goto L4;
        zzao r3 = r9.zza(r8, Arrays.asList(new zzao[]{r7.zzl(r2), new zzah(Double.valueOf(r2)), r7}));
        if (r3.zze().equals(r10) == true) goto L14;
        if (r11 == null) goto L13;
        if (r3.zze().equals(r11) == false) goto L4;
    L13:
        r0.zzn(r2, r3);
    L14:
        return r0;
    }
}
