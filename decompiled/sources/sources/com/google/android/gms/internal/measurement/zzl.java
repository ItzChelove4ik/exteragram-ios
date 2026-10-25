package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzl extends zzal {
    private final zzab zzb;

    public zzl(zzab r1) {
        this.zzb = r1;
    }

    @Override // com.google.android.gms.internal.measurement.zzal, com.google.android.gms.internal.measurement.zzao
    public final zzao zzcG(String r5, zzg r6, List r7) {
        switch(r5.hashCode()) {
            case 21624207: goto L35;
            case 45521504: goto L31;
            case 146575578: goto L27;
            case 700587132: goto L20;
            case 920706790: goto L16;
            case 1570616835: goto L6;
            default: goto L39;
        };
    L6:
        if (r5.equals("setEventName") == false) goto L39;
        zzh.zza("setEventName", 1, r7);
        zzao r52 = r6.zza((zzao) r7.get(0));
        if (zzao.zzf.equals(r52) == false) goto L10;
    L13:
        f$$ExternalSyntheticBUOutline1.m("Illegal event name");
        return null;
    L10:
        if (zzao.zzg.equals(r52) == true) goto L13;
        this.zzb.zzc().zzc(r52.zzc());
        return new zzas(r52.zzc());
    L16:
        if (r5.equals("setParamValue") == false) goto L39;
        zzh.zza("setParamValue", 2, r7);
        String r53 = r6.zza((zzao) r7.get(0)).zzc();
        zzao r62 = r6.zza((zzao) r7.get(1));
        this.zzb.zzc().zzd(r53, zzh.zzj(r62));
        return r62;
    L20:
        if (r5.equals("getParams") == false) goto L39;
        zzh.zza("getParams", 0, r7);
        Map r4 = this.zzb.zzc().zzf();
        zzal r54 = new zzal();
        Iterator r63 = r4.keySet().iterator();
    L23:
        if (r63.hasNext() == false) goto L25;
        String r72 = (String) r63.next();
        r54.zzm(r72, zzi.zza(r4.get(r72)));
        goto L23
    L25:
        return r54;
    L27:
        if (r5.equals("getParamValue") == false) goto L39;
        zzh.zza("getParamValue", 1, r7);
        return zzi.zza(this.zzb.zzc().zze(r6.zza((zzao) r7.get(0)).zzc()));
    L31:
        if (r5.equals("getTimestamp") == false) goto L39;
        zzh.zza("getTimestamp", 0, r7);
        return new zzah(Double.valueOf(this.zzb.zzc().zza()));
    L35:
        if (r5.equals("getEventName") == false) goto L39;
        zzh.zza("getEventName", 0, r7);
        return new zzas(this.zzb.zzc().zzb());
    L39:
        return super.zzcG(r5, r6, r7);
    }
}
