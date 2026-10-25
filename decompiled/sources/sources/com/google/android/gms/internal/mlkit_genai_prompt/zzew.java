package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzew extends zzbo {
    final /* synthetic */ CallbackToFutureAdapter.Completer zza;

    public zzew(zzfv r1, CallbackToFutureAdapter.Completer r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbp
    public final void zzb(int r3) {
        this.zza.setException(zzdo.zzd(14, "GetTokenInfo should not invoke onTokenizationSuccess method.", null));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbp
    public final void zzc(int r3) {
        this.zza.setException(zzdo.zzd(r3, "Tokenization failed.", null));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbp
    public final void zzd(zzct r1) {
        this.zza.set(zzdz.zzb(r1));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbp
    public final void zze(zzq r1) {
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
