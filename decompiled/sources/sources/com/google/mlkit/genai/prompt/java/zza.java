package com.google.mlkit.genai.prompt.java;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes5.dex */
final /* synthetic */ class zza implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzm zza;

    public /* synthetic */ zza(zzm r1) {
        this.zza = r1;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r1) {
        return zzm.zzc(this.zza, r1);
    }
}
