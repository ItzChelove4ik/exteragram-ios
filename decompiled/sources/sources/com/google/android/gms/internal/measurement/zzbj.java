package com.google.android.gms.internal.measurement;

import com.android.dex.EncodedValueReader$$ExternalSyntheticBUOutline0;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.Iterator;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzbj extends zzav {
    public zzbj() {
        this.zza.add(zzbk.zzd);
        this.zza.add(zzbk.zzo);
        this.zza.add(zzbk.zzr);
        this.zza.add(zzbk.zzs);
        this.zza.add(zzbk.zzy);
        this.zza.add(zzbk.zzH);
        this.zza.add(zzbk.zzJ);
        this.zza.add(zzbk.zzK);
        this.zza.add(zzbk.zzX);
        this.zza.add(zzbk.zzag);
        this.zza.add(zzbk.zzak);
        this.zza.add(zzbk.zzal);
        this.zza.add(zzbk.zzam);
    }

    @Override // com.google.android.gms.internal.measurement.zzav
    public final zzao zza(String r8, zzg r9, List r10) {
        zzbk r0 = zzbk.zza;
        int r02 = zzh.zze(r8).ordinal();
        int r3 = 0;
        if (r02 != 3) goto L5;
        zzh.zza(zzbk.zzd.name(), 2, r10);
        zzao r7 = r9.zza((zzao) r10.get(0));
        if ((r7 instanceof zzas) == true) goto L167;
        FieldSet$$ExternalSyntheticBUOutline0.m("Expected string for assign var. got %s", new Object[]{r7.getClass().getCanonicalName()});
        return null;
    L167:
        if (r9.zzd(r7.zzc()) == false) goto L170;
        zzao r82 = r9.zza((zzao) r10.get(1));
        r9.zze(r7.zzc(), r82);
        return r82;
    L170:
        FieldSet$$ExternalSyntheticBUOutline0.m("Attempting to assign undefined value %s", new Object[]{r7.zzc()});
        return null;
    L5:
        if (r02 != 14) goto L7;
        zzh.zzb(zzbk.zzo.name(), 2, r10);
        if ((r10.size() % 2) == 0) goto L154;
        FieldSet$$ExternalSyntheticBUOutline0.m("CONST requires an even number of arguments, found %s", new Object[]{Integer.valueOf(r10.size())});
        return null;
    L154:
        if (r3 >= (r10.size() - 1)) goto L161;
        zzao r72 = r9.zza((zzao) r10.get(r3));
        if ((r72 instanceof zzas) == false) goto L158;
        r9.zzg(r72.zzc(), r9.zza((zzao) r10.get(r3 + 1)));
        r3 = r3 + 2;
        goto L154
    L158:
        FieldSet$$ExternalSyntheticBUOutline0.m("Expected string for const name. got %s", new Object[]{r72.getClass().getCanonicalName()});
        return null;
    L161:
        return zzao.zzf;
    L7:
        if (r02 != 24) goto L9;
        zzh.zzb(zzbk.zzy.name(), 1, r10);
        zzao r73 = zzao.zzf;
    L144:
        if (r3 >= r10.size()) goto L150;
        r73 = r9.zza((zzao) r10.get(r3));
        if ((r73 instanceof zzag) == true) goto L148;
        r3 = r3 + 1;
        goto L144
    L148:
        Segment$$ExternalSyntheticBUOutline1.m("ControlValue cannot be in an expression list");
        return null;
    L150:
        return r73;
    L9:
        if (r02 != 33) goto L11;
        zzh.zza(zzbk.zzH.name(), 1, r10);
        zzao r74 = r9.zza((zzao) r10.get(0));
        if ((r74 instanceof zzas) == true) goto L139;
        FieldSet$$ExternalSyntheticBUOutline0.m("Expected string for get var. got %s", new Object[]{r74.getClass().getCanonicalName()});
        return null;
    L139:
        return r9.zzh(r74.zzc());
    L11:
        if (r02 != 49) goto L13;
        zzh.zza(zzbk.zzX.name(), 0, r10);
        return zzao.zzg;
    L13:
        if (r02 != 58) goto L15;
        zzh.zza(zzbk.zzag.name(), 3, r10);
        zzao r75 = r9.zza((zzao) r10.get(0));
        zzao r83 = r9.zza((zzao) r10.get(1));
        zzao r92 = r9.zza((zzao) r10.get(2));
        if (r75 != zzao.zzf) goto L120;
    L132:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m("Can't set property %s of %s", new Object[]{r83.zzc(), r75.zzc()});
        return null;
    L120:
        if (r75 == zzao.zzg) goto L132;
        if ((r75 instanceof zzae) == false) goto L128;
        if ((r83 instanceof zzah) == false) goto L128;
        ((zzae) r75).zzn(r83.zzd().intValue(), r92);
        return r92;
    L128:
        if ((r75 instanceof zzak) == true) goto L130;
        return r92;
    L130:
        ((zzak) r75).zzm(r83.zzc(), r92);
        return r92;
    L15:
        if (r02 == 17) goto L105;
        if (r02 == 18) goto L86;
        if (r02 != 35) goto L21;
    L61:
        zzh.zza(zzbk.zzK.name(), 2, r10);
        zzao r76 = r9.zza((zzao) r10.get(0));
        zzao r84 = r9.zza((zzao) r10.get(1));
        if ((r76 instanceof zzae) == false) goto L68;
        if (zzh.zzd(r84) == false) goto L68;
        return ((zzae) r76).zzl(r84.zzd().intValue());
    L68:
        if ((r76 instanceof zzak) == false) goto L72;
        return ((zzak) r76).zzk(r84.zzc());
    L72:
        if ((r76 instanceof zzas) == false) goto L84;
        if ("length".equals(r84.zzc()) == false) goto L78;
        return new zzah(Double.valueOf(r76.zzc().length()));
    L78:
        if (zzh.zzd(r84) == false) goto L84;
        if (r84.zzd().doubleValue() >= r76.zzc().length()) goto L84;
        return new zzas(String.valueOf(r76.zzc().charAt(r84.zzd().intValue())));
    L84:
        return zzao.zzf;
    L21:
        if (r02 == 36) goto L61;
        switch(r02) {
            case 62: goto L37;
            case 63: goto L35;
            case 64: goto L25;
            default: goto L24;
        };
    L25:
        zzh.zzb(zzbk.zzam.name(), 1, r10);
        Iterator r77 = r10.iterator();
    L27:
        if (r77.hasNext() == false) goto L34;
        zzao r85 = r9.zza((zzao) r77.next());
        if ((r85 instanceof zzas) == false) goto L31;
        r9.zzf(r85.zzc(), zzao.zzf);
        goto L27
    L31:
        FieldSet$$ExternalSyntheticBUOutline0.m("Expected string for var name. got %s", new Object[]{r85.getClass().getCanonicalName()});
        return null;
    L34:
        return zzao.zzf;
    L35:
        zzh.zza(zzbk.zzal.name(), 0, r10);
        return zzao.zzf;
    L37:
        zzh.zza(zzbk.zzak.name(), 1, r10);
        zzao r78 = r9.zza((zzao) r10.get(0));
        if ((r78 instanceof zzat) == false) goto L41;
        String r79 = "undefined";
    L58:
        return new zzas(r79);
    L41:
        if ((r78 instanceof zzaf) == false) goto L44;
        r79 = "boolean";
        goto L58
    L44:
        if ((r78 instanceof zzah) == false) goto L47;
        r79 = "number";
        goto L58
    L47:
        if ((r78 instanceof zzas) == false) goto L50;
        r79 = "string";
        goto L58
    L50:
        if ((r78 instanceof zzan) == false) goto L53;
        r79 = "function";
        goto L58
    L53:
        if ((r78 instanceof zzap) == false) goto L55;
    L59:
        FieldSet$$ExternalSyntheticBUOutline0.m("Unsupported value type %s in typeof", new Object[]{r78});
        return null;
    L55:
        if ((r78 instanceof zzag) == true) goto L59;
        r79 = "object";
        goto L58
    L24:
        return super.zzb(r8);
    L86:
        if (r10.isEmpty() == false) goto L90;
        return new zzal();
    L90:
        if ((r10.size() % 2) != 0) goto L102;
        zzal r710 = new zzal();
    L93:
        if (r3 >= (r10.size() - 1)) goto L101;
        zzao r86 = r9.zza((zzao) r10.get(r3));
        zzao r03 = r9.zza((zzao) r10.get(r3 + 1));
        if ((r86 instanceof zzag) == true) goto L99;
        if ((r03 instanceof zzag) == true) goto L99;
        r710.zzm(r86.zzc(), r03);
        r3 = r3 + 2;
    L99:
        Segment$$ExternalSyntheticBUOutline1.m("Failed to evaluate map entry");
        return null;
    L101:
        return r710;
    L102:
        FieldSet$$ExternalSyntheticBUOutline0.m("CREATE_OBJECT requires an even number of arguments, found %s", new Object[]{Integer.valueOf(r10.size())});
        return null;
    L105:
        if (r10.isEmpty() == true) goto L107;
        zzae r711 = new zzae();
        Iterator r87 = r10.iterator();
    L110:
        if (r87.hasNext() == false) goto L116;
        zzao r102 = r9.zza((zzao) r87.next());
        if ((r102 instanceof zzag) == true) goto L114;
        r711.zzn(r3, r102);
        r3 = r3 + 1;
        goto L110
    L114:
        Segment$$ExternalSyntheticBUOutline1.m("Failed to evaluate array element");
        return null;
    L116:
        return r711;
    L107:
        return new zzae();
    }
}
