package com.google.android.gms.internal.play_billing;

import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
enum zzcp extends Enum implements Executor {
    public static final zzcp zza = null;
    private static final /* synthetic */ zzcp[] zzb = null;

    static {
        zzcp r0 = new zzcp("INSTANCE", 0);
        zza = r0;
        zzb = new zzcp[]{r0};
    }

    zzcp(String r1, int r2) {
    }

    public static zzcp[] values() {
        return (zzcp[]) zzb.clone();
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
