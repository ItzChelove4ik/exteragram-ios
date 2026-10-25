package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
final class zzon extends zzom {
    private final ListenableFuture zza;

    public zzon(ListenableFuture r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc, com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable r1, Executor r2) {
        this.zza.addListener(r1, r2);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc, java.util.concurrent.Future
    public final boolean cancel(boolean r1) {
        return this.zza.cancel(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc, java.util.concurrent.Future
    public final Object get() {
        return this.zza.get();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc, java.util.concurrent.Future
    public final Object get(long r1, TimeUnit r3) {
        return this.zza.get(r1, r3);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc, java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.zza.isCancelled();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc, java.util.concurrent.Future
    public final boolean isDone() {
        return this.zza.isDone();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoc
    public final String toString() {
        return this.zza.toString();
    }
}
