package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzgt implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzgu zza;
    private final /* synthetic */ ListenableFuture zzb;
    private final /* synthetic */ zzlc zzc;

    public /* synthetic */ zzgt(zzgu r1, ListenableFuture r2, zzlc r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r3) {
        return this.zza.zzc(this.zzb, this.zzc, r3);
    }
}
