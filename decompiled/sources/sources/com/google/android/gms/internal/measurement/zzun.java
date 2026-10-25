package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final /* synthetic */ class zzun implements AsyncCallable {
    private final /* synthetic */ zzut zza;
    private final /* synthetic */ ListenableFuture zzb;
    private final /* synthetic */ AsyncFunction zzc;
    private final /* synthetic */ Executor zzd;

    public /* synthetic */ zzun(zzut r1, ListenableFuture r2, AsyncFunction r3, Executor r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // com.google.common.util.concurrent.AsyncCallable
    public final /* synthetic */ ListenableFuture call() {
        AsyncFunction r0 = zzxa.zzc(new zzup(this.zza, this.zzc, this.zzd));
        Executor r1 = MoreExecutors.directExecutor();
        return Futures.transformAsync(this.zzb, r0, r1);
    }
}
