package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmz implements Runnable {
    final /* synthetic */ ComponentName zza;
    final /* synthetic */ zznf zzb;

    public zzmz(zznf r1, ComponentName r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzW(this.zza);
    }
}
