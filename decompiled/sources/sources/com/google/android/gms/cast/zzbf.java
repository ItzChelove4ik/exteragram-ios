package com.google.android.gms.cast;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbf implements Runnable {
    private final /* synthetic */ zzbl zza;
    private final /* synthetic */ int zzb;

    public /* synthetic */ zzbf(zzbl r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzbm r0 = this.zza.zza;
        r0.zzU(4);
        List r1 = r0.zzT();
        int r3 = this.zzb;
        monitor-enter(r1);
        Iterator r02 = r0.zzT().iterator();     // Catch: Throwable -> L8
    L6:
        if (r02.hasNext() == false) goto L10;
        ((zzp) r02.next()).zzc(r3);     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
