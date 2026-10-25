package com.google.android.gms.common.util.concurrent;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.internal.common.zzg;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class HandlerExecutor implements Executor {
    private final Handler zza;

    public HandlerExecutor(Looper r2) {
        this.zza = new zzg(r2);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        this.zza.post(r1);
    }
}
