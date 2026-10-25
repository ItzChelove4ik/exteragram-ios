package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final /* synthetic */ class zzla implements Runnable {
    private final /* synthetic */ zzlj zza;
    private final /* synthetic */ AtomicReference zzb;

    public /* synthetic */ zzla(zzlj r1, AtomicReference r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzlj r0 = this.zza;
        Bundle r1 = r0.zzu.zzd().zzi.zza();
        r0.zzu.zzt().zzw(this.zzb, r1);
    }
}
