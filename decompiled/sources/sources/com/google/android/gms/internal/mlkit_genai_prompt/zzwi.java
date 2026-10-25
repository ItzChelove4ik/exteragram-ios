package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.guava.ListenableFutureKt;

/* loaded from: classes4.dex */
public final class zzwi extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ ListenableFuture zzb;

    public zzwi(ListenableFuture r1, Continuation r2) {
        this.zzb = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r1, Continuation r2) {
        return new zzwi(this.zzb, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzwi) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r3) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r3);
        if (r1 == 0) goto L5;
        return r3;
    L5:
        ListenableFuture r32 = this.zzb;
        this.zza = 1;
        Object r2 = ListenableFutureKt.await(r32, this);
        if (r2 != r0) goto L8;
        return r0;
    L8:
        return r2;
    }
}
