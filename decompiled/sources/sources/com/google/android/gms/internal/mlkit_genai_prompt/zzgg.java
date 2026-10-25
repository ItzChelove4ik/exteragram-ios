package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes4.dex */
final /* synthetic */ class zzgg implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzgl zza;
    private final /* synthetic */ zzdh zzb;
    private final /* synthetic */ zzdq zzc;
    private final /* synthetic */ zzdy zzd;

    public /* synthetic */ zzgg(zzgl r1, zzdh r2, zzdq r3, zzdy r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r4) {
        return this.zza.zzn(this.zzb, this.zzc, this.zzd, r4);
    }
}
