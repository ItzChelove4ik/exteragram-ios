package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final /* synthetic */ class zzkx implements AsyncCallable {
    private final /* synthetic */ Callable zza;

    public /* synthetic */ zzkx(Callable r1) {
        this.zza = r1;
    }

    @Override // com.google.common.util.concurrent.AsyncCallable
    public final /* synthetic */ ListenableFuture call() {
        int r0 = zzky.$r8$clinit;
        return Futures.submit(this.zza, MoreExecutors.directExecutor());
    }
}
