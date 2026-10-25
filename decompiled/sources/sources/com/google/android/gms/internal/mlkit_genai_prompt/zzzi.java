package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes4.dex */
final class zzzi extends ContinuationImpl {
    Object zza;
    Object zzb;
    boolean zzc;
    /* synthetic */ Object zzd;
    final /* synthetic */ zzzy zze;
    int zzf;
    String zzg;

    public zzzi(zzzy r1, Continuation r2) {
        this.zze = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r3) {
        this.zzd = r3;
        this.zzf |= Integer.MIN_VALUE;
        return zzzy.zzh(this.zze, null, null, false, this);
    }
}
