package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzauy implements zzauv {
    final List zza;

    public zzauy(Context r2, zzaux r3) {
        ArrayList r0 = new ArrayList();
        this.zza = r0;
        if (r3.zzb() == false) goto L6;
        r0.add(new zzavi(r2, r3));
        return;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzauv
    public final void zza(zzauu r2) {
        Iterator r1 = this.zza.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((zzauv) r1.next()).zza(r2);
        goto L4
    }
}
