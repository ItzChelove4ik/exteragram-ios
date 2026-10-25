package com.google.mlkit.genai.prompt.java;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.mlkit.genai.common.StreamingCallback;
import com.google.mlkit.genai.prompt.GenerateContentRequest;

/* loaded from: classes5.dex */
final /* synthetic */ class zzw implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzal zza;
    private final /* synthetic */ GenerateContentRequest zzb;
    private final /* synthetic */ StreamingCallback zzc;

    public /* synthetic */ zzw(zzal r1, GenerateContentRequest r2, StreamingCallback r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r3) {
        return zzal.zzg(this.zza, this.zzb, this.zzc, r3);
    }
}
