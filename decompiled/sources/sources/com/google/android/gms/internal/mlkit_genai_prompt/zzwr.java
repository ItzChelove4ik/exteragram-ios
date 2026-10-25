package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes4.dex */
final class zzwr extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzxg zzb;
    final /* synthetic */ Context zzc;

    public zzwr(zzxg r1, Context r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzwr(this.zzb, this.zzc, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzwr) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r4);
        if (r1 != 0) goto L9;
        zzxg r42 = this.zzb;
        Context r12 = this.zzc;
        this.zza = 1;
        if (zzxg.zzl(r42, r12, this) != r0) goto L9;
        return r0;
    L9:
        return Unit.INSTANCE;
    }
}
