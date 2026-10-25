package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final /* synthetic */ class zztz implements AsyncCallable {
    private final /* synthetic */ zzui zza;
    private final /* synthetic */ ListenableFuture zzb;
    private final /* synthetic */ AsyncFunction zzc;
    private final /* synthetic */ Executor zzd;

    public /* synthetic */ zztz(zzui r1, ListenableFuture r2, AsyncFunction r3, Executor r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // com.google.common.util.concurrent.AsyncCallable
    public final /* synthetic */ ListenableFuture call() {
        zzui r1 = this.zza;
        zzua r0 = new zzua(r1);
        Executor r2 = MoreExecutors.directExecutor();
        ListenableFuture r02 = Futures.transformAsync(this.zzb, r0, r2);
        ListenableFuture r4 = Futures.transformAsync(r02, this.zzc, this.zzd);
        return Futures.transformAsync(r4, zzxa.zzc(new zzuc(r1, r02, r4)), MoreExecutors.directExecutor());
    }
}
