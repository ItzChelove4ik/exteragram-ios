package com.google.mlkit.common.sdkinternal;

import java.lang.ref.ReferenceQueue;
import java.util.Set;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzb implements Runnable {
    public final /* synthetic */ ReferenceQueue zza;
    public final /* synthetic */ Set zzb;

    public /* synthetic */ zzb(ReferenceQueue r1, Set r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ReferenceQueue r0 = this.zza;
    L4:
        if (this.zzb.isEmpty() == true) goto L7;
        ((zzd) r0.remove()).clean();     // Catch: InterruptedException -> L8
        goto L4
    }
}
