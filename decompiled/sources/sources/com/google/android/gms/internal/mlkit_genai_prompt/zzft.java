package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzft implements zzgw {
    private final CallbackToFutureAdapter.Completer zza;

    public zzft(zzfv r1, CallbackToFutureAdapter.Completer r2) {
        Objects.requireNonNull(r1);
        this.zza = r2;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzgw
    public final void zza(Object r1) {
        this.zza.set(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzgw
    public final void zzb(int r3) {
        this.zza.setException(zzdo.zzb(r3, "Inference failed.", null));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzgw
    public final void zzc(int r2, zzq r3) {
        CallbackToFutureAdapter.Completer r1 = this.zza;
        if (r3 == null) goto L6;
        r1.setException(zzdo.zzi(r3));
        return;
    L6:
        r1.setException(zzdo.zzb(r2, "Inference failed.", null));
    }
}
