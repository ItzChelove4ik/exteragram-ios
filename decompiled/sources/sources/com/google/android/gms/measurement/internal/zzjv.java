package com.google.android.gms.measurement.internal;

import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
final class zzjv implements Executor {
    final /* synthetic */ zzlj zza;

    public zzjv(zzlj r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        this.zza.zzu.zzaX().zzj(r1);
    }
}
