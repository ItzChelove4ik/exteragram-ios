package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzmf implements zzmc {
    final List zza;

    public zzmf(Context r2, zzme r3) {
        ArrayList r0 = new ArrayList();
        this.zza = r0;
        if (r3.zzc() == false) goto L6;
        r0.add(new zzmp(r2, r3));
        return;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmc
    public final void zza(zzmb r2) {
        Iterator r1 = this.zza.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((zzmc) r1.next()).zza(r2);
        goto L4
    }
}
