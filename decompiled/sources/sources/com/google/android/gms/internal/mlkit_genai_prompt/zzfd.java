package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes4.dex */
final /* synthetic */ class zzfd implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzfv zza;
    private final /* synthetic */ zzfu zzb;

    public /* synthetic */ zzfd(zzfv r1, zzfu r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r2) {
        return this.zza.zzq(this.zzb, r2);
    }
}
