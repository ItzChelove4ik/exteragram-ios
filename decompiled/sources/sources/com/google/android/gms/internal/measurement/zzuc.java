package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzuc implements AsyncFunction {
    private final /* synthetic */ zzui zza;
    private final /* synthetic */ ListenableFuture zzb;
    private final /* synthetic */ ListenableFuture zzc;

    public /* synthetic */ zzuc(zzui r1, ListenableFuture r2, ListenableFuture r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r3) {
        return this.zza.zzg(this.zzb, this.zzc, r3);
    }
}
