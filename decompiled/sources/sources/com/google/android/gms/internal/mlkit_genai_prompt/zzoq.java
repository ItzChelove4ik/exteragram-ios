package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public abstract class zzoq extends zzoo implements ListenableFuture {
    public zzoq() {
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable r1, Executor r2) {
        zzc().addListener(r1, r2);
    }

    public abstract ListenableFuture zzc();
}
