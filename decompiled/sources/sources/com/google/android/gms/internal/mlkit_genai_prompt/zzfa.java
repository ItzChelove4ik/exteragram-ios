package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes4.dex */
final /* synthetic */ class zzfa implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzfv zza;
    private final /* synthetic */ zzfu zzb;
    private final /* synthetic */ Object zzc;

    public /* synthetic */ zzfa(zzfv r1, zzfu r2, Object r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r3) {
        return this.zza.zzo(this.zzb, this.zzc, r3);
    }
}
