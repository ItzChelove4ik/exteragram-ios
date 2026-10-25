package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zzxv extends zzyl {
    public zzxv(String r1, Class r2, boolean r3) {
        super("group_by", r2, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzyl
    public final void zza(Iterator r4, zzyk r5) {
        if (r4.hasNext() == false) goto L15;
        Object r0 = r4.next();
        if (r4.hasNext() == true) goto L8;
        r5.zza(zzd(), r0);
        return;
    L8:
        StringBuilder r1 = new StringBuilder("[");
        r1.append(r0);
    L9:
        r1.append(',');
        r1.append(r4.next());
        if (r4.hasNext() == true) goto L9;
        String r3 = zzd();
        r1.append(']');
        r5.zza(r3, r1.toString());
        return;
    }
}
