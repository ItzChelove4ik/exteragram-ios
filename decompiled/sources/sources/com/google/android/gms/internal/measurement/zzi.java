package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class zzi {
    public static zzao zza(Object r5) {
        if (r5 != null) goto L6;
        return zzao.zzg;
    L6:
        if ((r5 instanceof String) == false) goto L10;
        return new zzas((String) r5);
    L10:
        if ((r5 instanceof Double) == false) goto L14;
        return new zzah((Double) r5);
    L14:
        if ((r5 instanceof Long) == false) goto L18;
        return new zzah(Double.valueOf(((Long) r5).doubleValue()));
    L18:
        if ((r5 instanceof Integer) == false) goto L22;
        return new zzah(Double.valueOf(((Integer) r5).doubleValue()));
    L22:
        if ((r5 instanceof Boolean) == false) goto L26;
        return new zzaf((Boolean) r5);
    L26:
        if ((r5 instanceof Map) == false) goto L38;
        zzal r0 = new zzal();
        Map r52 = (Map) r5;
        Iterator r1 = r52.keySet().iterator();
    L29:
        if (r1.hasNext() == false) goto L36;
        Object r2 = r1.next();
        zzao r3 = zza(r52.get(r2));
        if (r2 == null) goto L29;
        if ((r2 instanceof String) == true) goto L35;
        r2 = r2.toString();
    L35:
        r0.zzm((String) r2, r3);
        goto L29
    L36:
        return r0;
    L38:
        if ((r5 instanceof List) == false) goto L44;
        zzae r02 = new zzae();
        Iterator r53 = ((List) r5).iterator();
    L41:
        if (r53.hasNext() == false) goto L43;
        zzao r12 = zza(r53.next());
        r02.zzn(r02.zzh(), r12);
        goto L41
    L43:
        return r02;
    L44:
        f$$ExternalSyntheticBUOutline1.m("Invalid value type");
        return null;
    }

    public static zzao zzb(zzje r3) {
        if (r3 == null) goto L4;
        int r0 = r3.zzk() - 1;
        if (r0 == 1) goto L34;
        if (r0 == 2) goto L28;
        if (r0 == 3) goto L22;
        if (r0 != 4) goto L19;
        List r02 = r3.zza();
        ArrayList r1 = new ArrayList();
        Iterator r03 = r02.iterator();
    L15:
        if (r03.hasNext() == false) goto L18;
        r1.add(zzb((zzje) r03.next()));
        goto L15
    L18:
        return new zzap(r3.zzb(), r1);
    L19:
        f$$ExternalSyntheticBUOutline1.m("Unknown type found. Cannot convert entity");
        return null;
    L22:
        if (r3.zze() == false) goto L26;
        return new zzaf(Boolean.valueOf(r3.zzf()));
    L26:
        return new zzaf(null);
    L28:
        if (r3.zzh() == false) goto L32;
        return new zzah(Double.valueOf(r3.zzi()));
    L32:
        return new zzah(null);
    L34:
        if (r3.zzc() == false) goto L38;
        return new zzas(r3.zzd());
    L38:
        return zzao.zzm;
    L4:
        return zzao.zzf;
    }
}
