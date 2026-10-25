package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzho extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ Function1 zzb;

    public zzho(int r1, long r2, long r4, double r6, Function1 r8, Continuation r9) {
        this.zzb = r8;
        super(2, r9);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r11, Continuation r12) {
        return new zzho(20, 100, 1000, 2.0d, this.zzb, r12);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzho) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r13) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r13);
        if (r1 == 0) goto L5;
        return r13;
    L5:
        Function1 r10 = this.zzb;
        zzhp r2 = zzhp.zza;
        this.zza = 1;
        Object r12 = r2.zza(20, 100, 1000, 2.0d, r10, this);
        if (r12 != r0) goto L8;
        return r0;
    L8:
        return r12;
    }
}
