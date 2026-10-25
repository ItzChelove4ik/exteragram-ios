package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes4.dex */
final /* synthetic */ class zzyv implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzzy zza;

    public /* synthetic */ zzyv(zzzy r1) {
        this.zza = r1;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r1) {
        return zzzy.zzs(this.zza, r1);
    }
}
