package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzgr implements Runnable {
    private final /* synthetic */ ListenableFuture zza;

    public /* synthetic */ zzgr(ListenableFuture r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.cancel(false);
    }
}
