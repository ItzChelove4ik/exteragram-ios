package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Objects;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final class zzpl extends zzpa {
    final /* synthetic */ zzpm zza;
    private final Callable zzb;

    public zzpl(zzpm r1, Callable r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        r2.getClass();
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzpa
    public final boolean zza() {
        return this.zza.isDone();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzpa
    public final Object zzb() {
        return this.zzb.call();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzpa
    public final void zzc(Object r1) {
        this.zza.zzh(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzpa
    public final void zzd(Throwable r1) {
        this.zza.zzi(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzpa
    public final String zzf() {
        return this.zzb.toString();
    }
}
