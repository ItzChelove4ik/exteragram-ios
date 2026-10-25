package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import j$.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes4.dex */
public abstract class zzdn {
    public zzdn() {
    }

    public static zzdk zzh(Context r1) {
        zzdr r0 = new zzdr();
        r0.zzk(r1);
        r0.zzb(zzdl.zza);
        r0.zzc(true);
        r0.zzd(Duration.ZERO);
        r0.zzf(Optional.empty());
        return r0;
    }

    public abstract Context zza();

    public abstract ExecutorService zzb();

    public abstract Executor zzc();

    public abstract boolean zzd();

    public abstract Duration zze();

    public abstract ScheduledExecutorService zzf();

    public abstract Optional zzg();
}
