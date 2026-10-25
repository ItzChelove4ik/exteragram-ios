package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzex extends zzbm {
    final /* synthetic */ CallbackToFutureAdapter.Completer zza;

    public zzex(zzfv r1, CallbackToFutureAdapter.Completer r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbn
    public final void zzb() {
        this.zza.set(null);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbn
    public final void zzc(int r3) {
        this.zza.setException(zzdo.zzc(r3, "Preparation failed.", null));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbn
    public final void zzd(zzq r1) {
        if (r1 != null) goto L4;
        int r12 = 0;
    L5:
        zzc(r12);
        return;
    L4:
        r12 = r1.zzb();
        goto L5
    }
}
