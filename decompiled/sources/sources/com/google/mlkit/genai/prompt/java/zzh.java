package com.google.mlkit.genai.prompt.java;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.mlkit.genai.prompt.CreateCachedContextRequest;

/* loaded from: classes5.dex */
final /* synthetic */ class zzh implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ CreateCachedContextRequest zzb;

    public /* synthetic */ zzh(zzm r1, CreateCachedContextRequest r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r2) {
        return zzm.zzb(this.zza, this.zzb, r2);
    }
}
