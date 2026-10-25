package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzlx implements Runnable {
    final /* synthetic */ zzmb zza;

    public zzlx(zzmb r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzmb r1 = this.zza;
        r1.zza = r1.zzv();
    }
}
