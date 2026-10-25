package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes4.dex */
abstract class zzdj {
    private static final ScheduledExecutorService zza = null;

    static {
        zza = Executors.newScheduledThreadPool(1, new zzdm("AiCoreClientScheduler"));
    }

    public static /* synthetic */ ScheduledExecutorService zza() {
        return zza;
    }
}
