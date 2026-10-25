package com.google.android.gms.cast;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbe implements Runnable {
    private final /* synthetic */ zzbl zza;
    private final /* synthetic */ int zzb;

    public /* synthetic */ zzbe(zzbl r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        int r0 = this.zzb;
        zzbl r4 = this.zza;
        if (r0 != 0) goto L16;
        zzbm r42 = r4.zza;
        r42.zzU(3);
        r42.zzN(true);
        r42.zzO(true);
        List r2 = r42.zzT();
        monitor-enter(r2);
        Iterator r43 = r42.zzT().iterator();     // Catch: Throwable -> L10
    L8:
        if (r43.hasNext() == false) goto L12;
        ((zzp) r43.next()).zza();     // Catch: Throwable -> L10
        goto L8
    L12:
        monitor-exit(r2);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    L16:
        zzbm r22 = r4.zza;
        r22.zzU(1);
        List r1 = r22.zzT();
        monitor-enter(r1);
        Iterator r23 = r22.zzT().iterator();     // Catch: Throwable -> L22
    L20:
        if (r23.hasNext() == false) goto L24;
        ((zzp) r23.next()).zzb(r0);     // Catch: Throwable -> L22
        goto L20
    L24:
        monitor-exit(r1);     // Catch: Throwable -> L22
        r4.zza.zzD();
        return;
    L22:
        th = move-exception;
        throw th;
    }
}
