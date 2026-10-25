package com.google.android.gms.internal.mlkit_language_id_common;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzld implements zzlh {
    final List zza;

    public zzld(Context r2, zzlc r3) {
        ArrayList r0 = new ArrayList();
        this.zza = r0;
        if (r3.zzc() == false) goto L6;
        r0.add(new zzlq(r2, r3));
        return;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzlh
    public final void zza(zzla r2) {
        Iterator r1 = this.zza.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((zzlh) r1.next()).zza(r2);
        goto L4
    }
}
