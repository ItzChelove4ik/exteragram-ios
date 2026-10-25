package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: classes4.dex */
public final class zacu {
    public static final Status zaa = null;
    final Set zab;
    private final zact zac;

    static {
        zaa = new Status(8, "The connection to Google Play services was lost");
    }

    public zacu() {
        this.zab = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
        this.zac = new zact(this);
    }

    public final void zaa(BasePendingResult r2) {
        this.zab.add(r2);
        r2.zan(this.zac);
    }

    public final void zab() {
        Set r5 = this.zab;
        int r0 = 0;
        BasePendingResult[] r1 = (BasePendingResult[]) r5.toArray(new BasePendingResult[0]);
        int r2 = r1.length;
    L3:
        if (r0 >= r2) goto L8;
        BasePendingResult r3 = r1[r0];
        r3.zan(null);
        if (r3.zaj() == false) goto L7;
        r5.remove(r3);
    L7:
        r0 = r0 + 1;
        goto L3
    }
}
