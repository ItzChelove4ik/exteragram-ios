package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
final class zzxw extends zzyl {
    public zzxw(String r1, Class r2, boolean r3) {
        super("tags", r2, false);
    }

    @Override // com.google.android.gms.internal.measurement.zzyl
    public final /* bridge */ /* synthetic */ void zzb(Object r4, zzyk r5) {
        zzabe r42 = (zzabe) r4;
        if (r42 == null) goto L15;
        Iterator r3 = r42.zzb().entrySet().iterator();
    L7:
        if (r3.hasNext() == false) goto L22;
        Map.Entry r43 = (Map.Entry) r3.next();
        if (((Set) r43.getValue()).isEmpty() == false) goto L10;
        r5.zza((String) r43.getKey(), null);
        goto L7
    L10:
        Iterator r0 = ((Set) r43.getValue()).iterator();
    L12:
        if (r0.hasNext() == false) goto L7;
        Object r1 = r0.next();
        r5.zza((String) r43.getKey(), r1);
        goto L12
    L22:
        return;
    }
}
