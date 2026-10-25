package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzu implements Executor {
    private final Handler zza;

    public zzu() {
        this.zza = new com.google.android.gms.internal.tasks.zza(Looper.getMainLooper());
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        this.zza.post(r1);
    }
}
