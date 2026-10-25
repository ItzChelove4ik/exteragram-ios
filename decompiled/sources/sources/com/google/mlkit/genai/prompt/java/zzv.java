package com.google.mlkit.genai.prompt.java;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.mlkit.genai.prompt.GenerateContentRequest;

/* loaded from: classes5.dex */
final /* synthetic */ class zzv implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzal zza;
    private final /* synthetic */ GenerateContentRequest zzb;

    public /* synthetic */ zzv(zzal r1, GenerateContentRequest r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r2) {
        return zzal.zzf(this.zza, this.zzb, r2);
    }
}
