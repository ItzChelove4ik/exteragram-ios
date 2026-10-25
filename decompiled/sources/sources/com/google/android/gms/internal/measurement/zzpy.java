package com.google.android.gms.internal.measurement;

import android.util.Log;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzpy implements Runnable {
    private final /* synthetic */ ListenableFuture zza;

    public /* synthetic */ zzpy(ListenableFuture r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        Futures.getDone(this.zza);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        Log.w("PhFlagUpdateRegistry", "Failed to register flag update listener which may lead to stale flags.", e);
    }
}
