package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes4.dex */
final /* synthetic */ class zzgs implements Runnable {
    private final /* synthetic */ CallbackToFutureAdapter.Completer zza;
    private final /* synthetic */ zzlc zzb;

    public /* synthetic */ zzgs(CallbackToFutureAdapter.Completer r1, zzlc r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        Throwable r0 = (Throwable) this.zzb.zza();
        this.zza.setException(r0);
    }
}
