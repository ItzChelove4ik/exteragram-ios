package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzub implements AsyncCallable {
    private final /* synthetic */ zzui zza;

    public /* synthetic */ zzub(zzui r1) {
        this.zza = r1;
    }

    @Override // com.google.common.util.concurrent.AsyncCallable
    public final /* synthetic */ ListenableFuture call() {
        return this.zza.zzf();
    }
}
