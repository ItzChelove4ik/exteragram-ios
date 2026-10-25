package com.google.android.gms.internal.measurement;

import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzuo implements AsyncFunction {
    private final /* synthetic */ Function zza;

    public /* synthetic */ zzuo(Function r1) {
        this.zza = r1;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r1) {
        return Futures.immediateFuture(this.zza.apply(r1));
    }
}
