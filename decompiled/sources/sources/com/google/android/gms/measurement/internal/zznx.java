package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zznx implements Runnable {
    final long zza;
    final long zzb;
    final /* synthetic */ zzny zzc;

    public zznx(zzny r1, long r2, long r4) {
        Objects.requireNonNull(r1);
        this.zzc = r1;
        this.zza = r2;
        this.zzb = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzu.zzaX().zzj(new zznw(this));
    }
}
