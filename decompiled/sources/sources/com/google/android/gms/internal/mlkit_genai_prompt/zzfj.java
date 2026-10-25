package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzfj implements zzoj {
    private final /* synthetic */ zzfv zza;
    private final /* synthetic */ Object zzb;

    public /* synthetic */ zzfj(zzfv r1, Object r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoj
    public final /* synthetic */ ListenableFuture zza(Object r3) {
        zzfu r32 = (zzfu) r3;
        ListenableFuture r2 = CallbackToFutureAdapter.getFuture(new zzfa(this.zza, r32, this.zzb));
        return r32.zzb().zza(r2, zzfb.zza);
    }
}
