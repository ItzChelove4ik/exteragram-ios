package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzfs implements zzoj {
    private final /* synthetic */ zzfv zza;
    private final /* synthetic */ Object zzb;

    public /* synthetic */ zzfs(zzfv r1, Object r2, boolean r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoj
    public final /* synthetic */ ListenableFuture zza(Object r4) {
        zzfu r42 = (zzfu) r4;
        ListenableFuture r3 = CallbackToFutureAdapter.getFuture(new zzfg(this.zza, this.zzb, true, r42));
        return r42.zzb().zza(r3, zzfh.zza);
    }
}
