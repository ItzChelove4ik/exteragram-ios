package com.google.android.gms.cast;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbk implements Runnable {
    private final /* synthetic */ zzbl zza;
    private final /* synthetic */ int zzb;

    public /* synthetic */ zzbk(zzbl r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzbl r0 = this.zza;
        zzbm r1 = r0.zza;
        r1.zzL();
        r1.zzU(1);
        List r2 = r1.zzT();
        int r4 = this.zzb;
        monitor-enter(r2);
        Iterator r12 = r1.zzT().iterator();     // Catch: Throwable -> L8
    L6:
        if (r12.hasNext() == false) goto L10;
        ((zzp) r12.next()).zzd(r4);     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r2);     // Catch: Throwable -> L8
        zzbm r42 = r0.zza;
        r42.zzD();
        r42.zzC(r42.zza);
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
