package com.google.android.recaptcha.internal;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public final class zzqc extends zzpy {
    public zzqc() {
    }

    public final zzqc zzb(Object r5, Object... r6) {
        List r62 = Arrays.asList(r6);
        boolean r0 = true;
        if (r5 != null) goto L12;
        Iterator r52 = r62.iterator();
        StringBuilder r63 = new StringBuilder("[");
    L6:
        if (r52.hasNext() == false) goto L10;
        if (r0 == true) goto L9;
        r63.append(", ");
    L9:
        r63.append(r52.next());
        r0 = false;
        goto L6
    L10:
        r63.append(']');
        throw new NullPointerException("null key in entry: null=".concat(r63.toString()));
    L12:
        Iterator r1 = r62.iterator();
        if (r1.hasNext() == false) goto L24;
        zzpp r2 = (zzpp) zza().get(r5);
        if (r2 != null) goto L22;
        int r3 = 4;
        if ((r62 instanceof Set) == false) goto L20;
        r3 = Math.max(4, ((Set) r62).size());
    L20:
        zzpa.zzb(r3, "expectedSize");
        r2 = new zzqa(r3, true);
        zza().put(r5, r2);
    L22:
        if (r1.hasNext() == false) goto L24;
        Object r64 = r1.next();
        zzpa.zza(r5, r64);
        r2.zzb(r64);
    L24:
        return this;
    }

    public final zzqe zzc() {
        Map r5 = this.zza;
        if (r5 == null) goto L5;
        Set r52 = r5.entrySet();
        if (r52.isEmpty() == true) goto L9;
        zzpv r0 = new zzpv(r52.size());
        Iterator r53 = r52.iterator();
        int r1 = 0;
    L12:
        if (r53.hasNext() == false) goto L17;
        Map.Entry r2 = (Map.Entry) r53.next();
        Object r3 = r2.getKey();
        zzqb r22 = zzqb.zzh(((zzqa) r2.getValue()).zzd());
        if (r22.isEmpty() == true) goto L12;
        r0.zza(r3, r22);
        r1 = r1 + r22.size();
        goto L12
    L17:
        return new zzqe(r0.zzb(), r1, null);
    L9:
        return zzpm.zza;
    L5:
        return zzpm.zza;
    }
}
