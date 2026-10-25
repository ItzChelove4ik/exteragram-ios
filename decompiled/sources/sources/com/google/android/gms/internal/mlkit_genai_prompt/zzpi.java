package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes4.dex */
public abstract class zzpi {
    public static Executor zza() {
        return zzok.zza;
    }

    public static zzpc zzb(ExecutorService r1) {
        if ((r1 instanceof zzpc) == false) goto L7;
        return (zzpc) r1;
    L7:
        if ((r1 instanceof ScheduledExecutorService) == false) goto L11;
        return new zzph((ScheduledExecutorService) r1);
    L11:
        return new zzpd(r1);
    }

    public static Executor zzc(Executor r1, zzoc r2) {
        r1.getClass();
        if (r1 != zzok.zza) goto L6;
        return r1;
    L6:
        return new zzpe(r1, r2);
    }

    public static /* synthetic */ void zzd(Executor r0, zzoc r1, Runnable r2) {
        zze(r0, r1, r2);
    }

    private static /* synthetic */ void zze(Executor r0, zzoc r1, Runnable r2) {
        r0.execute(r2);     // Catch: RejectedExecutionException -> L4
        return;
    L4:
        e = move-exception;
        r1.zzi(e);
    }
}
