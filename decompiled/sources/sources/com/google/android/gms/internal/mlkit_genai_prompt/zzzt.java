package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes4.dex */
final class zzzt extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzzy zzb;
    int zzc;

    public zzzt(zzzy r1, Continuation r2) {
        this.zzb = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.zza = r2;
        this.zzc |= Integer.MIN_VALUE;
        return this.zzb.isSystemPromptAvailable(this);
    }
}
