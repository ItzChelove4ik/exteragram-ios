package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzte implements zztb {
    final List zza;

    public zzte(Context r2, zztd r3) {
        ArrayList r0 = new ArrayList();
        this.zza = r0;
        if (r3.zzc() == false) goto L6;
        r0.add(new zztt(r2, r3));
        return;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztb
    public final void zza(zzta r2) {
        Iterator r1 = this.zza.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((zztb) r1.next()).zza(r2);
        goto L4
    }
}
