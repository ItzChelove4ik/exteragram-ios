package com.google.mlkit.genai.prompt.java;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes5.dex */
final /* synthetic */ class zzc implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ String zzb;

    public /* synthetic */ zzc(zzm r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r2) {
        return zzm.zze(this.zza, this.zzb, r2);
    }
}
