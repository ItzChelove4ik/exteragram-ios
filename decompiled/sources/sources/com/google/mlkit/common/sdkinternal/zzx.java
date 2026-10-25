package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.Closeable;

/* loaded from: classes5.dex */
final class zzx implements Closeable {
    final /* synthetic */ TaskQueue zza;

    public /* synthetic */ zzx(TaskQueue r1, zzw r2) {
        this.zza = r1;
        if (((Thread) TaskQueue.zza(r1).getAndSet(Thread.currentThread())) != null) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(r0);
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        TaskQueue.zza(this.zza).set(null);
        TaskQueue.zzb(this.zza);
    }
}
