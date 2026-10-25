package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzua implements AsyncFunction {
    private final /* synthetic */ zzui zza;

    public /* synthetic */ zzua(zzui r1) {
        this.zza = r1;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r1) {
        return this.zza.zze(r1);
    }
}
