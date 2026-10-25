package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzud implements AsyncFunction {
    private final /* synthetic */ zzui zza;
    private final /* synthetic */ ListenableFuture zzb;

    public /* synthetic */ zzud(zzui r1, ListenableFuture r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r2) {
        return this.zza.zzh(this.zzb, r2);
    }
}
