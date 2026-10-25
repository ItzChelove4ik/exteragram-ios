package com.google.android.gms.internal.mlkit_genai_prompt;

import j$.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes4.dex */
public abstract class zzdk {
    public zzdk() {
    }

    public abstract zzdk zza(ExecutorService r1);

    public abstract zzdk zzb(Executor r1);

    public abstract zzdk zzc(boolean r1);

    public abstract zzdk zzd(Duration r1);

    public abstract zzdk zze(ScheduledExecutorService r1);

    public abstract zzdk zzf(Optional r1);

    public abstract Optional zzg();

    public abstract Optional zzh();

    public final zzdn zzi() {
        if (zzg().isPresent() == true) goto L6;
        zza(Executors.newCachedThreadPool(new zzdm("AiCoreClientWorker")));
    L6:
        if (zzh().isPresent() == true) goto L9;
        zze(zzdj.zza());
    L9:
        return zzj();
    }

    public abstract zzdn zzj();
}
