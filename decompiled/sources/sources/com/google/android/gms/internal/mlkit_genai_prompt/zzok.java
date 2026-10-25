package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
enum zzok extends Enum implements Executor {
    public static final zzok zza = null;
    private static final /* synthetic */ zzok[] zzb = null;

    static {
        zzok r0 = new zzok("INSTANCE", 0);
        zza = r0;
        zzb = new zzok[]{r0};
    }

    zzok(String r1, int r2) {
    }

    public static zzok[] values() {
        return (zzok[]) zzb.clone();
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        r1.run();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "MoreExecutors.directExecutor()";
    }
}
