package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.util.SparseArray;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
final /* synthetic */ class zzlb implements Runnable {
    private final /* synthetic */ zzlj zza;
    private final /* synthetic */ List zzb;

    public /* synthetic */ zzlb(zzlj r1, List r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzlj r0 = this.zza;
        r0.zzg();
        if (Build.VERSION.SDK_INT >= 30) goto L5;
        return;
    L5:
        List r7 = this.zzb;
        SparseArray r1 = r0.zzu.zzd().zzf();
        Iterator r72 = r7.iterator();
    L7:
        if (r72.hasNext() == false) goto L13;
        zzoh r2 = (zzoh) r72.next();
        int r3 = r2.zzc;
        if (r1.contains(r3) == false) goto L12;
        if (((Long) r1.get(r3)).longValue() >= r2.zzb) goto L7;
    L12:
        r0.zzx().add(r2);
        goto L7
    L13:
        r0.zzy();
    }
}
