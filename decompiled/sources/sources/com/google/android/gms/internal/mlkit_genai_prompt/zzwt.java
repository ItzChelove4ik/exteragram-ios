package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes4.dex */
final class zzwt extends SuspendLambda implements Function2 {
    /* synthetic */ Object zza;
    final /* synthetic */ Map zzb;

    public zzwt(Map r1, Continuation r2) {
        this.zzb = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzwt r0 = new zzwt(this.zzb, r3);
        r0.zza = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzwt) create((Map) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r1) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r1);
        Map r12 = (Map) this.zza;
        r12.clear();
        r12.putAll(this.zzb);
        return Unit.INSTANCE;
    }
}
