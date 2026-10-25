package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzas implements Iterable, zzao {
    private final String zza;

    public zzas(String r1) {
        if (r1 == null) goto L6;
        this.zza = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("StringValue cannot be null.");
        throw null;
    }

    public final boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzas) == true) goto L10;
        return false;
    L10:
        return this.zza.equals(((zzas) r2).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new zzar(this);
    }

    public final String toString() {
        String r2 = this.zza;
        StringBuilder r0 = new StringBuilder(r2.length() + 2);
        r0.append("\"");
        r0.append(r2);
        r0.append("\"");
        return r0.toString();
    }

    public final /* synthetic */ String zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final String zzc() {
        return this.zza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzcG(String r27, zzg r28, List r29) {
        if ("charAt".equals(r27) == false) goto L5;
    L39:
        String r17 = "hasOwnProperty";
        String r3 = "trim";
    L40:
        String r20 = "undefined";
        int r11 = 0;
        boolean r112 = false;
        r112 = false;
        int r113 = 0;
        switch(r27.hashCode()) {
            case -1789698943: goto L220;
            case -1776922004: goto L217;
            case -1464939364: goto L213;
            case -1361633751: goto L199;
            case -1354795244: goto L187;
            case -1137582698: goto L184;
            case -906336856: goto L173;
            case -726908483: goto L169;
            case -467511597: goto L153;
            case -399551817: goto L149;
            case 3568674: goto L145;
            case 103668165: goto L133;
            case 109526418: goto L113;
            case 109648666: goto L83;
            case 530542161: goto L71;
            case 1094496948: goto L56;
            case 1943291465: goto L44;
            default: goto L236;
        };
    L56:
        zzas r0 = this;
        if (r27.equals("replace") == false) goto L236;
        zzh.zzc("replace", 2, r29);
        zzao r1 = zzao.zzf;
        if (r29.isEmpty() == true) goto L63;
        r20 = r28.zza((zzao) r29.get(0)).zzc();
        if (r29.size() <= 1) goto L63;
        r1 = r28.zza((zzao) r29.get(1));
    L63:
        String r2 = r20;
        String r4 = r0.zza;
        int r5 = r4.indexOf(r2);
        if (r5 >= 0) goto L66;
    L197:
        return r0;
    L66:
        if ((r1 instanceof zzai) == false) goto L68;
        r1 = ((zzai) r1).zza(r28, Arrays.asList(new zzao[]{new zzas(r2), new zzah(Double.valueOf(r5)), r0}));
    L68:
        String r32 = r4.substring(0, r5);
        String r12 = r1.zzc();
        String r22 = r4.substring(r5 + r2.length());
        StringBuilder r7 = new StringBuilder((r32.length() + String.valueOf(r12).length()) + r22.length());
        r7.append(r32);
        r7.append(r12);
        r7.append(r22);
        return new zzas(r7.toString());
    L187:
        r0 = this;
        if (r27.equals("concat") == false) goto L236;
        if (r29.isEmpty() == true) goto L197;
        StringBuilder r13 = new StringBuilder(r0.zza);
    L193:
        if (r113 >= r29.size()) goto L196;
        r13.append(r28.zza((zzao) r29.get(r113)).zzc());
        r113 = r113 + 1;
        goto L193
    L196:
        return new zzas(r13.toString());
    L220:
        String r42 = r17;
        if (r27.equals(r42) == false) goto L236;
        zzh.zza(r42, 1, r29);
        String r02 = this.zza;
        zzao r14 = r28.zza((zzao) r29.get(0));
        if ("length".equals(r14.zzc()) == true) goto L225;
        double r15 = r14.zzd().doubleValue();
        if (r15 != Math.floor(r15)) goto L235;
        int r16 = (int) r15;
        if (r16 < 0) goto L235;
        if (r16 >= r02.length()) goto L235;
        return zzao.zzk;
    L235:
        return zzao.zzl;
    L225:
        return zzao.zzk;
    L236:
        f$$ExternalSyntheticBUOutline1.m("Command not supported");
        return null;
    L44:
        if (r27.equals("indexOf") == false) goto L236;
        zzh.zzc("indexOf", 2, r29);
        String r03 = this.zza;
        if (r29.size() > 0) goto L49;
        zzg r33 = r28;
    L48:
        String r18 = r20;
        if (r29.size() >= 2) goto L53;
        double r10 = 0.0d;
    L55:
        return new zzah(Double.valueOf(r03.indexOf(r18, (int) zzh.zzi(r10))));
    L53:
        r10 = r33.zza((zzao) r29.get(1)).zzd().doubleValue();
        goto L55
    L49:
        r33 = r28;
        r20 = r33.zza((zzao) r29.get(0)).zzc();
        goto L48
    L71:
        if (r27.equals("substring") == false) goto L236;
        zzh.zzc("substring", 2, r29);
        String r04 = this.zza;
        if (r29.isEmpty() == true) goto L75;
        int r19 = (int) zzh.zzi(r28.zza((zzao) r29.get(0)).zzd().doubleValue());
    L77:
        if (r29.size() <= 1) goto L79;
        int r23 = (int) zzh.zzi(r28.zza((zzao) r29.get(1)).zzd().doubleValue());
    L80:
        int r110 = Math.min(Math.max(r19, 0), r04.length());
        int r24 = Math.min(Math.max(r23, 0), r04.length());
        return new zzas(r04.substring(Math.min(r110, r24), Math.max(r110, r24)));
    L79:
        r23 = r04.length();
        goto L80
    L75:
        r19 = 0;
        goto L77
    L83:
        if (r27.equals("split") == false) goto L236;
        zzh.zzc("split", 2, r29);
        String r111 = this.zza;
        if (r111.length() == 0) goto L87;
        ArrayList r25 = new ArrayList();
        if (r29.isEmpty() == false) goto L91;
        r25.add(this);
    L111:
        return new zzae(r25);
    L91:
        String r05 = r28.zza((zzao) r29.get(0)).zzc();
        if (r29.size() <= 1) goto L94;
        long r34 = zzh.zzh(r28.zza((zzao) r29.get(1)).zzd().doubleValue());
    L96:
        if (r34 == 0) goto L98;
        String[] r114 = r111.split(Pattern.quote(r05), ((int) r34) + 1);
        int r52 = r114.length;
        if (r05.isEmpty() == false) goto L104;
        if (r52 <= 0) goto L104;
        boolean r115 = r114[0].isEmpty();
        int r06 = r52 - 1;
        int r116 = r115;
        r112 = r115;
        if (r114[r06].isEmpty() == false) goto L104;
    L106:
        if (r52 <= r34) goto L108;
        r06 = r06 - 1;
    L108:
        if (r116 >= r06) goto L111;
        r25.add(new zzas(r114[r116]));
        r116 = r116 + 1;
    L104:
        r06 = r52;
        r116 = r112;
        goto L106
    L98:
        return new zzae();
    L94:
        r34 = 2147483647L;
        goto L96
    L87:
        return new zzae(Arrays.asList(new zzao[]{this}));
    L113:
        if (r27.equals("slice") == false) goto L236;
        zzh.zzc("slice", 2, r29);
        String r07 = this.zza;
        if (r29.isEmpty() == true) goto L117;
        double r117 = r28.zza((zzao) r29.get(0)).zzd().doubleValue();
    L118:
        double r118 = zzh.zzi(r117);
        if (r118 >= 0.0d) goto L121;
        double r43 = Math.max(r07.length() + r118, 0.0d);
    L123:
        if (r29.size() <= 1) goto L125;
        double r119 = r28.zza((zzao) r29.get(1)).zzd().doubleValue();
    L126:
        double r120 = zzh.zzi(r119);
        if (r120 >= 0.0d) goto L129;
        double r121 = Math.max(r07.length() + r120, 0.0d);
    L130:
        int r35 = (int) r43;
        return new zzas(r07.substring(r35, Math.max(0, ((int) r121) - r35) + r35));
    L129:
        r121 = Math.min(r120, r07.length());
        goto L130
    L125:
        r119 = r07.length();
        goto L126
    L121:
        r43 = Math.min(r118, r07.length());
        goto L123
    L117:
        r117 = 0.0d;
        goto L118
    L133:
        if (r27.equals("match") == false) goto L236;
        zzh.zzc("match", 1, r29);
        String r08 = this.zza;
        if (r29.size() > 0) goto L137;
        String r122 = _UrlKt.FRAGMENT_ENCODE_SET;
    L138:
        Matcher r09 = Pattern.compile(r122).matcher(r08);
        if (r09.find() == false) goto L143;
        return new zzae(Arrays.asList(new zzao[]{new zzas(r09.group())}));
    L143:
        return zzao.zzg;
    L137:
        r122 = r28.zza((zzao) r29.get(0)).zzc();
        goto L138
    L145:
        if (r27.equals(r3) == false) goto L236;
        zzh.zza("toUpperCase", 0, r29);
        return new zzas(this.zza.trim());
    L149:
        if (r27.equals("toUpperCase") == false) goto L236;
        zzh.zza("toUpperCase", 0, r29);
        return new zzas(this.zza.toUpperCase(Locale.ENGLISH));
    L153:
        if (r27.equals("lastIndexOf") == false) goto L236;
        zzh.zzc("lastIndexOf", 2, r29);
        String r010 = this.zza;
        if (r29.size() > 0) goto L157;
    L156:
        String r123 = r20;
        if (r29.size() >= 2) goto L161;
        double r26 = Double.NaN;
    L163:
        if (Double.isNaN(r26) == false) goto L165;
        double r210 = Double.POSITIVE_INFINITY;
    L167:
        return new zzah(Double.valueOf(r010.lastIndexOf(r123, (int) r210)));
    L165:
        r210 = zzh.zzi(r26);
        goto L167
    L161:
        r26 = r28.zza((zzao) r29.get(1)).zzd().doubleValue();
        goto L163
    L157:
        r20 = r28.zza((zzao) r29.get(0)).zzc();
        goto L156
    L169:
        if (r27.equals("toLocaleUpperCase") == false) goto L236;
        zzh.zza("toLocaleUpperCase", 0, r29);
        return new zzas(this.zza.toUpperCase());
    L173:
        if (r27.equals("search") == false) goto L236;
        zzh.zzc("search", 1, r29);
        if (r29.isEmpty() == true) goto L178;
        r20 = r28.zza((zzao) r29.get(0)).zzc();
    L178:
        if (Pattern.compile(r20).matcher(this.zza).find() == false) goto L182;
        return new zzah(Double.valueOf(r0.start()));
    L182:
        return new zzah(Double.valueOf(-1.0d));
    L184:
        if (r27.equals("toLowerCase") == false) goto L236;
        zzh.zza("toLowerCase", 0, r29);
        return new zzas(this.zza.toLowerCase(Locale.ENGLISH));
    L199:
        if (r27.equals("charAt") == false) goto L236;
        zzh.zzc("charAt", 1, r29);
        if (r29.isEmpty() == true) goto L203;
        r11 = (int) zzh.zzi(r28.zza((zzao) r29.get(0)).zzd().doubleValue());
    L203:
        String r011 = this.zza;
        if (r11 < 0) goto L211;
        if (r11 >= r011.length()) goto L211;
        return new zzas(String.valueOf(r011.charAt(r11)));
    L211:
        return zzao.zzm;
    L213:
        if (r27.equals("toLocaleLowerCase") == false) goto L236;
        zzh.zza("toLocaleLowerCase", 0, r29);
        return new zzas(this.zza.toLowerCase());
    L217:
        if (r27.equals("toString") == false) goto L236;
        zzh.zza("toString", 0, r29);
        return this;
    L5:
        if ("concat".equals(r27) == true) goto L39;
        if ("hasOwnProperty".equals(r27) == true) goto L39;
        if ("indexOf".equals(r27) == true) goto L39;
        if ("lastIndexOf".equals(r27) == true) goto L39;
        if ("match".equals(r27) == true) goto L39;
        if ("replace".equals(r27) == true) goto L39;
        if ("search".equals(r27) == true) goto L39;
        if ("slice".equals(r27) == true) goto L39;
        if ("split".equals(r27) == true) goto L39;
        if ("substring".equals(r27) == true) goto L39;
        if ("toLowerCase".equals(r27) == true) goto L39;
        if ("toLocaleLowerCase".equals(r27) == true) goto L39;
        if ("toString".equals(r27) == true) goto L39;
        if ("toUpperCase".equals(r27) == true) goto L39;
        if ("toLocaleUpperCase".equals(r27) == true) goto L39;
        r17 = "hasOwnProperty";
        r3 = "trim";
        if (r3.equals(r27) == true) goto L40;
        FieldSet$$ExternalSyntheticBUOutline0.m("%s is not a String function", new Object[]{r27});
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Double zzd() {
        String r2 = this.zza;
        if (r2.isEmpty() == true) goto L9;
        return Double.valueOf(r2);
    L7:
        return Double.valueOf(Double.NaN);
    L9:
        return Double.valueOf(0.0d);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Boolean zze() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Iterator zzf() {
        return new zzaq(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzt() {
        return new zzas(this.zza);
    }
}
