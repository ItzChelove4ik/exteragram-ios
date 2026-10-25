package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes4.dex */
final /* synthetic */ class zzfg implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzfv zza;
    private final /* synthetic */ Object zzb;
    private final /* synthetic */ zzfu zzc;

    public /* synthetic */ zzfg(zzfv r1, Object r2, boolean r3, zzfu r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r4) {
        zzfu r0 = this.zzc;
        return this.zza.zzs(this.zzb, true, r0, r4);
    }
}
