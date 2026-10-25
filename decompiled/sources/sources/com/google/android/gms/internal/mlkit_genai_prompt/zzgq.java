package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Objects;
import java.util.concurrent.CancellationException;

/* loaded from: classes4.dex */
final class zzgq implements zzor {
    final /* synthetic */ CallbackToFutureAdapter.Completer zza;
    final /* synthetic */ Runnable zzb;
    final /* synthetic */ zzgu zzc;

    public zzgq(zzgu r1, CallbackToFutureAdapter.Completer r2, Runnable r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzor
    public final void zza(Throwable r4) {
        if ((r4 instanceof zzdo) == false) goto L5;
        this.zza.setException(r4);
    L9:
        this.zzc.zzd(this.zzb);
        return;
    L5:
        boolean r0 = r4 instanceof CancellationException;
        CallbackToFutureAdapter.Completer r1 = this.zza;
        if (r0 == false) goto L8;
        r1.setException(zzdo.zze(7, "Operation was cancelled.", r4));
        goto L9
    L8:
        r1.setException(zzdo.zze(0, "AiCore request could not be completed.", r4));
        goto L9
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzor
    public final void zzb(Object r2) {
        this.zza.set(r2);
        this.zzc.zzd(this.zzb);
    }
}
