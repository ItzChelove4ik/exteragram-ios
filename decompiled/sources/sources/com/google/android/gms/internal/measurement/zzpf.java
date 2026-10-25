package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzpf implements AsyncFunction {
    private final /* synthetic */ zzqt zza;

    public /* synthetic */ zzpf(zzqt r1) {
        this.zza = r1;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r1) {
        return this.zza.zzc((zzqv) r1);
    }
}
