package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final class zzog extends zzoi {
    public zzog(ListenableFuture r1, zzoj r2) {
        super(r1, r2);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoi
    public final /* synthetic */ void zza(Object r1) {
        zzj((ListenableFuture) r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoi
    public final /* bridge */ /* synthetic */ Object zzb(Object r1, Object r2) {
        zzoj r12 = (zzoj) r1;
        ListenableFuture r0 = r12.zza(r2);
        zzla.zze(r0, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", r12);
        return r0;
    }
}
