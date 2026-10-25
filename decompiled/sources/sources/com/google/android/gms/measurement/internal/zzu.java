package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final /* synthetic */ class zzu implements Runnable {
    private final /* synthetic */ zzic zza;

    public /* synthetic */ zzu(zzic r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzic r2 = this.zza;
        if (r2.zzk().zzU() == true) goto L6;
        r2.zzaW().zze().zza("registerTrigger called but app not eligible");
        return;
    L6:
        r2.zzj().zzv();
        zzlj r22 = r2.zzj();
        Objects.requireNonNull(r22);
        new Thread(new zzv(r22)).start();
    }
}
