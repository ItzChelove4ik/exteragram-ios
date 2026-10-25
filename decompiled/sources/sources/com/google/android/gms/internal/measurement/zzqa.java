package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzqa implements AsyncFunction {
    private final /* synthetic */ zzqe zza;
    private final /* synthetic */ zzqm zzb;

    public /* synthetic */ zzqa(zzqe r1, zzqm r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r2) {
        return this.zza.zzb(this.zzb, (Void) r2);
    }
}
