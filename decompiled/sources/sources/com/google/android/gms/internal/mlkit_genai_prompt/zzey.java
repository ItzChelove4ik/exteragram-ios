package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzey implements zzor {
    final /* synthetic */ zzfv zza;

    public zzey(zzfv r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzor
    public final void zza(Throwable r2) {
        zzfv r1 = this.zza;
        Object r22 = r1.zzu();
        monitor-enter(r22);
        r1.zzv(null);     // Catch: Throwable -> L8
        monitor-exit(r22);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzor
    public final /* bridge */ /* synthetic */ void zzb(Object r1) {
        zzfu r12 = (zzfu) r1;
    }
}
