package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.TimeoutKt;

/* loaded from: classes5.dex */
final class zzjx extends SuspendLambda implements Function1 {
    int zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ zzke zzc;

    public zzjx(long r1, zzke r3, Continuation r4) {
        this.zzb = r1;
        this.zzc = r3;
        super(1, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation r4) {
        return new zzjx(this.zzb, this.zzc, r4);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object r1) {
        return ((zzjx) create((Continuation) r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r6);
        if (r1 != 0) goto L9;
        long r12 = this.zzb;
        zzjw r3 = new zzjw(this.zzc, null);
        this.zza = 1;
        if (TimeoutKt.withTimeout(r12, r3, this) != r0) goto L9;
        return r0;
    L9:
        return Unit.INSTANCE;
    }
}
