package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public abstract class zzcj {
    public static PendingResult zza(Task r2, zzcg r3, zzcg r4) {
        zzcf r0 = new zzcf(r4);
        r2.addOnSuccessListener(new zzci(r0, r3)).addOnFailureListener(new zzch(r0, r4));
        return r0;
    }
}
