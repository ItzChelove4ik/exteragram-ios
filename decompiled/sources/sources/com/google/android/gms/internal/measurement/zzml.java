package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.ApiException;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzml implements AsyncFunction {
    static final /* synthetic */ zzml zza = null;

    static {
        zza = new zzml();
    }

    private /* synthetic */ zzml() {
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r3) {
        ApiException r32 = (ApiException) r3;
        throw new zzmk(r32.getStatusCode(), r32.getMessage(), r32);
    }
}
