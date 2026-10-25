package com.google.android.gms.internal.cast;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public abstract class zzwi extends zzwg implements ListenableFuture {
    public zzwi() {
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable r1, Executor r2) {
        zzc().addListener(r1, r2);
    }

    public abstract ListenableFuture zzc();
}
