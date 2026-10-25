package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final class zzus implements zzth {
    final /* synthetic */ zzut zza;

    public /* synthetic */ zzus(zzut r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.measurement.zzth
    public final ListenableFuture zza(AsyncFunction r3, Executor r4) {
        return zzuy.zza(Futures.transformAsync(Futures.nonCancellationPropagating(this.zza.zzg().zza()), zzxa.zzc(new zzur(this, r3, r4)), MoreExecutors.directExecutor()));
    }
}
