package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class zzal implements zzao, zzak {
    final Map zza;

    public zzal() {
        this.zza = new HashMap();
    }

    public final boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzal) == true) goto L10;
        return false;
    L10:
        return this.zza.equals(((zzal) r2).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("{");
        Map r4 = this.zza;
        if (r4.isEmpty() == true) goto L9;
        Iterator r1 = r4.keySet().iterator();
    L6:
        if (r1.hasNext() == false) goto L8;
        String r2 = (String) r1.next();
        r0.append(String.format("%s: %s,", new Object[]{r2, r4.get(r2)}));
        goto L6
    L8:
        r0.deleteCharAt(r0.lastIndexOf(","));
    L9:
        r0.append("}");
        return r0.toString();
    }

    public final List zzb() {
        return new ArrayList(this.zza.keySet());
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final String zzc() {
        return "[object Object]";
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public zzao zzcG(String r2, zzg r3, List r4) {
        if ("toString".equals(r2) == false) goto L7;
        return new zzas(toString());
    L7:
        return zzak.zzu(this, new zzas(r2), r3, r4);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Double zzd() {
        return Double.valueOf(Double.NaN);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Boolean zze() {
        return Boolean.TRUE;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Iterator zzf() {
        return zzak.zzv(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final boolean zzj(String r1) {
        return this.zza.containsKey(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final zzao zzk(String r2) {
        Map r1 = this.zza;
        if (r1.containsKey(r2) == false) goto L7;
        return (zzao) r1.get(r2);
    L7:
        return zzao.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final void zzm(String r1, zzao r2) {
        Map r0 = this.zza;
        if (r2 != null) goto L6;
        r0.remove(r1);
        return;
    L6:
        r0.put(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzt() {
        zzal r0 = new zzal();
        Iterator r4 = this.zza.entrySet().iterator();
    L4:
        if (r4.hasNext() == false) goto L9;
        Map.Entry r1 = (Map.Entry) r4.next();
        boolean r2 = r1.getValue() instanceof zzak;
        Map r3 = r0.zza;
        if (r2 == true) goto L7;
        r3.put((String) r1.getKey(), ((zzao) r1.getValue()).zzt());
        goto L4
    L7:
        r3.put((String) r1.getKey(), (zzao) r1.getValue());
        goto L4
    L9:
        return r0;
    }
}
