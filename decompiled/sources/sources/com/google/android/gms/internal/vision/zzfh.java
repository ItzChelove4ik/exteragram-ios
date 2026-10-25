package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
final class zzfh extends zzfd {
    private final zzfg zza;

    public zzfh() {
        this.zza = new zzfg();
    }

    @Override // com.google.android.gms.internal.vision.zzfd
    public final void zza(Throwable r4) {
        r4.printStackTrace();
        List<Throwable> r3 = this.zza.zza(r4, false);
        if (r3 != null) goto L5;
        return;
    L5:
        monitor-enter(r3);
        Iterator<Throwable> r42 = r3.iterator();     // Catch: Throwable -> L10
    L8:
        if (r42.hasNext() == false) goto L12;
        Throwable r0 = r42.next();     // Catch: Throwable -> L10
        System.err.print("Suppressed: ");     // Catch: Throwable -> L10
        r0.printStackTrace();     // Catch: Throwable -> L10
        goto L8
    L12:
        monitor-exit(r3);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }
}
