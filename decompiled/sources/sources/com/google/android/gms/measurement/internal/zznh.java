package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final /* synthetic */ class zznh implements Runnable {
    private final /* synthetic */ zznl zza;
    private final /* synthetic */ AtomicReference zzb;
    private final /* synthetic */ zzr zzc;
    private final /* synthetic */ Bundle zzd;

    public /* synthetic */ zznh(zznl r1, AtomicReference r2, zzr r3, Bundle r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzS(this.zzb, this.zzc, this.zzd);
    }
}
