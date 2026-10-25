package com.google.android.gms.internal.fido;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class zzbc {
    public static final Appendable zza(Appendable r2, Iterator r3, zzbd r4, String r5) {
        if (r3.hasNext() == false) goto L8;
        Map.Entry r52 = (Map.Entry) r3.next();
        r2.append(zzbd.zzd(r52.getKey()));
        r2.append(" : ");
        r2.append(zzbd.zzd(r52.getValue()));
    L6:
        if (r3.hasNext() == false) goto L8;
        r2.append(zzbd.zzb(r4));
        Map.Entry r53 = (Map.Entry) r3.next();
        r2.append(zzbd.zzd(r53.getKey()));
        r2.append(" : ");
        r2.append(zzbd.zzd(r53.getValue()));
    L8:
        return r2;
    }
}
