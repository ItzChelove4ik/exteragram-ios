package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.mlkit.genai.common.DownloadCallback;

/* loaded from: classes4.dex */
final /* synthetic */ class zzyu implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzzy zza;
    private final /* synthetic */ DownloadCallback zzb;

    public /* synthetic */ zzyu(zzzy r1, DownloadCallback r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r2) {
        return zzzy.zzr(this.zza, this.zzb, r2);
    }
}
