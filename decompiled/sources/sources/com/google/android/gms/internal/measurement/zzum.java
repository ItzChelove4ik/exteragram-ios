package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzum implements AsyncCallable {
    private final /* synthetic */ ListenableFuture zza;

    public /* synthetic */ zzum(ListenableFuture r1) {
        this.zza = r1;
    }

    @Override // com.google.common.util.concurrent.AsyncCallable
    public final /* synthetic */ ListenableFuture call() {
        return this.zza;
    }
}
