package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzay extends zzav {
    public zzay() {
        this.zza.add(zzbk.zzc);
        this.zza.add(zzbk.zzl);
        this.zza.add(zzbk.zzm);
        this.zza.add(zzbk.zzn);
        this.zza.add(zzbk.zzt);
        this.zza.add(zzbk.zzp);
        this.zza.add(zzbk.zzu);
        this.zza.add(zzbk.zzz);
        this.zza.add(zzbk.zzP);
        this.zza.add(zzbk.zzac);
        this.zza.add(zzbk.zzaf);
        this.zza.add(zzbk.zzai);
        this.zza.add(zzbk.zzaj);
    }

    private static zzao zzc(zzg r5, List r6) {
        zzh.zzb(zzbk.zzz.name(), 2, r6);
        zzao r0 = r5.zza((zzao) r6.get(0));
        zzao r2 = r5.zza((zzao) r6.get(1));
        if ((r2 instanceof zzae) == false) goto L9;
        List r22 = ((zzae) r2).zzb();
        List r3 = new ArrayList();
        if (r6.size() <= 2) goto L8;
        r3 = r6.subList(2, r6.size());
    L8:
        return new zzan(r0.zzc(), r22, r3, r5);
    L9:
        FieldSet$$ExternalSyntheticBUOutline0.m("FN requires an ArrayValue of parameter names found %s", new Object[]{r2.getClass().getCanonicalName()});
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzav
    public final zzao zza(String r9, zzg r10, List r11) {
        zzbk r0 = zzbk.zza;
        int r02 = zzh.zze(r9).ordinal();
        zzao r1 = null;
        if (r02 != 2) goto L5;
        zzh.zza(zzbk.zzc.name(), 3, r11);
        zzao r8 = r10.zza((zzao) r11.get(0));
        String r92 = r10.zza((zzao) r11.get(1)).zzc();
        zzao r112 = r10.zza((zzao) r11.get(2));
        if ((r112 instanceof zzae) == true) goto L116;
        FieldSet$$ExternalSyntheticBUOutline0.m("Function arguments for Apply are not a list found %s", new Object[]{r112.getClass().getCanonicalName()});
        return null;
    L116:
        if (r92.isEmpty() == false) goto L118;
        f$$ExternalSyntheticBUOutline1.m("Function name for apply is undefined");
        return null;
    L118:
        return r8.zzcG(r92, r10, ((zzae) r112).zzb());
    L5:
        if (r02 != 15) goto L7;
        zzh.zza(zzbk.zzm.name(), 0, r11);
        return zzao.zzh;
    L7:
        if (r02 == 25) goto L110;
        if (r02 != 41) goto L11;
        zzh.zzb(zzbk.zzP.name(), 2, r11);
        zzao r82 = r10.zza((zzao) r11.get(0));
        zzao r93 = r10.zza((zzao) r11.get(1));
        if (r11.size() <= 2) goto L99;
        r1 = r10.zza((zzao) r11.get(2));
    L99:
        zzao r113 = zzao.zzf;
        if (r82.zze().booleanValue() == false) goto L102;
        zzao r83 = r10.zzb((zzae) r93);
    L106:
        if (true == (r83 instanceof zzag)) goto L108;
        return r113;
    L108:
        return r83;
    L102:
        if (r1 == null) goto L104;
        r83 = r10.zzb((zzae) r1);
        goto L106
    L104:
        r83 = r113;
        goto L106
    L11:
        if (r02 == 54) goto L95;
        if (r02 == 57) goto L89;
        if (r02 == 19) goto L79;
        if (r02 != 20) goto L19;
        zzh.zzb(zzbk.zzu.name(), 2, r11);
        zzan r84 = (zzan) zzc(r10, r11);
        if (r84.zzg() != null) goto L76;
        r10.zze(_UrlKt.FRAGMENT_ENCODE_SET, r84);
        return r84;
    L76:
        r10.zze(r84.zzg(), r84);
        return r84;
    L19:
        if (r02 != 60) goto L21;
        zzh.zza(zzbk.zzai.name(), 3, r11);
        zzao r85 = r10.zza((zzao) r11.get(0));
        zzao r94 = r10.zza((zzao) r11.get(1));
        zzao r114 = r10.zza((zzao) r11.get(2));
        if ((r94 instanceof zzae) == true) goto L38;
        f$$ExternalSyntheticBUOutline1.m("Malformed SWITCH statement, cases are not a list");
        return null;
    L38:
        if ((r114 instanceof zzae) == false) goto L68;
        zzae r95 = (zzae) r94;
        zzae r115 = (zzae) r114;
        int r03 = 0;
        boolean r12 = false;
    L41:
        if (r03 >= r95.zzh()) goto L57;
        if (r12 == false) goto L44;
    L47:
        zzao r13 = r10.zza(r115.zzl(r03));
        if ((r13 instanceof zzag) == true) goto L50;
        r12 = true;
    L55:
        r03 = r03 + 1;
        goto L41
    L50:
        if (((zzag) r13).zzg().equals("break") == true) goto L52;
        return r13;
    L52:
        return zzao.zzf;
    L44:
        if (r85.equals(r10.zza(r95.zzl(r03))) == true) goto L47;
        r12 = false;
        goto L55
    L57:
        if ((r95.zzh() + 1) != r115.zzh()) goto L67;
        zzao r86 = r10.zza(r115.zzl(r95.zzh()));
        if ((r86 instanceof zzag) == false) goto L67;
        String r96 = ((zzag) r86).zzg();
        if (r96.equals("return") == false) goto L63;
    L65:
        return r86;
    L63:
        if (r96.equals("continue") == true) goto L65;
    L67:
        return zzao.zzf;
    L68:
        f$$ExternalSyntheticBUOutline1.m("Malformed SWITCH statement, case statements are not a list");
        return null;
    L21:
        if (r02 == 61) goto L29;
        switch(r02) {
            case 11: goto L28;
            case 12: goto L25;
            case 13: goto L79;
            default: goto L24;
        };
    L25:
        zzh.zza(zzbk.zzm.name(), 0, r11);
        return zzao.zzi;
    L24:
        return super.zzb(r9);
    L28:
        return r10.zzc().zzb(new zzae(r11));
    L29:
        zzh.zza(zzbk.zzaj.name(), 3, r11);
        if (r10.zza((zzao) r11.get(0)).zze().booleanValue() == false) goto L34;
        return r10.zza((zzao) r11.get(1));
    L34:
        return r10.zza((zzao) r11.get(2));
    L79:
        if (r11.isEmpty() == true) goto L81;
        zzao r87 = r10.zza((zzao) r11.get(0));
        if ((r87 instanceof zzae) == false) goto L87;
        return r10.zzb((zzae) r87);
    L87:
        return zzao.zzf;
    L81:
        return zzao.zzf;
    L89:
        if (r11.isEmpty() == true) goto L91;
        zzh.zza(zzbk.zzaf.name(), 1, r11);
        return new zzag("return", r10.zza((zzao) r11.get(0)));
    L91:
        return zzao.zzj;
    L95:
        return new zzae(r11);
    L110:
        return zzc(r10, r11);
    }
}
