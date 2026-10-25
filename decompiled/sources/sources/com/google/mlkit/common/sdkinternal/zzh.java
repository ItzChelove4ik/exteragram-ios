package com.google.mlkit.common.sdkinternal;

import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
enum zzh extends Enum implements Executor {
    public static final zzh zza = null;
    private static final /* synthetic */ zzh[] zzb = null;

    static {
        zzh r0 = new zzh("INSTANCE", 0);
        zza = r0;
        zzb = new zzh[]{r0};
    }

    zzh(String r1, int r2) {
    }

    public static zzh[] values() {
        return (zzh[]) zzb.clone();
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        MLTaskExecutor.zza(MLTaskExecutor.getInstance()).post(r1);
    }
}
