package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzez implements zzoj {
    private final /* synthetic */ zzfv zza;

    public /* synthetic */ zzez(zzfv r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoj
    public final /* synthetic */ ListenableFuture zza(Object r2) {
        zzfu r22 = (zzfu) r2;
        ListenableFuture r1 = CallbackToFutureAdapter.getFuture(new zzfd(this.zza, r22));
        return r22.zzb().zza(r1, zzfe.zza);
    }
}
