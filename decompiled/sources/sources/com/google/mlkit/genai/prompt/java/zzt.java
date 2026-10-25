package com.google.mlkit.genai.prompt.java;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes5.dex */
final /* synthetic */ class zzt implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzal zza;

    public /* synthetic */ zzt(zzal r1) {
        this.zza = r1;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r1) {
        return zzal.zzd(this.zza, r1);
    }
}
