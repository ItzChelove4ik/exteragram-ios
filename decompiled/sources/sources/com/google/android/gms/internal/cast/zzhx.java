package com.google.android.gms.internal.cast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzhx {
    Object[] zza;
    int zzb;
    zzhw zzc;

    public zzhx(int r1) {
        this.zza = new Object[r1 + r1];
        this.zzb = 0;
    }

    private final void zzb(int r3) {
        Object[] r0 = this.zza;
        int r1 = r0.length;
        int r32 = r3 + r3;
        if (r32 <= r1) goto L6;
        this.zza = Arrays.copyOf(r0, zzhq.zza(r1, r32));
        return;
    }

    public final zzhx zza(Iterable r6) {
        if ((r6 instanceof Collection) == false) goto L5;
        zzb(this.zzb + ((Collection) r6).size());
    L5:
        Iterator r62 = r6.iterator();
    L7:
        if (r62.hasNext() == false) goto L9;
        Map.Entry r0 = (Map.Entry) r62.next();
        Object r1 = r0.getKey();
        Object r02 = r0.getValue();
        zzb(this.zzb + 1);
        zzhm.zza(r1, r02);
        Object[] r2 = this.zza;
        int r3 = this.zzb;
        int r4 = r3 + r3;
        r2[r4] = r1;
        r2[r4 + 1] = r02;
        this.zzb = r3 + 1;
        goto L7
    L9:
        return this;
    }
}
