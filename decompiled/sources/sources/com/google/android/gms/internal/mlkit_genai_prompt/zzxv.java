package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes4.dex */
final class zzxv extends ContinuationImpl {
    Object zza;
    Object zzb;
    Object zzc;
    int zzd;
    /* synthetic */ Object zze;
    final /* synthetic */ zzyd zzf;
    int zzg;
    zzyd zzh;

    public zzxv(zzyd r1, Continuation r2) {
        this.zzf = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.zze = r2;
        this.zzg |= Integer.MIN_VALUE;
        return zzyd.zza(this.zzf, this);
    }
}
