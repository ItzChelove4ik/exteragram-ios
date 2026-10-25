package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes4.dex */
final class zzxe extends ContinuationImpl {
    Object zza;
    Object zzb;
    /* synthetic */ Object zzc;
    final /* synthetic */ zzxg zzd;
    int zze;

    public zzxe(zzxg r1, Continuation r2) {
        this.zzd = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.zzc = r2;
        this.zze |= Integer.MIN_VALUE;
        return zzxg.zzo(this.zzd, null, this);
    }
}
