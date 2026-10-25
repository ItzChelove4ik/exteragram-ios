package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzaw {
    final Map zza;
    final zzbi zzb;

    public zzaw() {
        this.zza = new HashMap();
        this.zzb = new zzbi();
        zza(new zzau());
        zza(new zzax());
        zza(new zzay());
        zza(new zzbb());
        zza(new zzbg());
        zza(new zzbh());
        zza(new zzbj());
    }

    public final void zza(zzav r4) {
        Iterator r0 = r4.zza.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        String r1 = ((zzbk) r0.next()).zzb().toString();
        this.zza.put(r1, r4);
        goto L4
    }

    public final zzao zzb(zzg r4, zzao r5) {
        zzh.zzl(r4);
        if ((r5 instanceof zzap) == false) goto L10;
        zzap r52 = (zzap) r5;
        ArrayList r0 = r52.zzg();
        String r53 = r52.zzb();
        Map r1 = this.zza;
        if (r1.containsKey(r53) == false) goto L7;
        zzav r3 = (zzav) r1.get(r53);
    L9:
        return r3.zza(r53, r4, r0);
    L7:
        r3 = this.zzb;
        goto L9
    L10:
        return r5;
    }
}
