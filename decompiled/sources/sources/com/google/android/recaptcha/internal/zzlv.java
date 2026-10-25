package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzlv extends SuspendLambda implements Function2 {
    final /* synthetic */ zzlx zza;
    final /* synthetic */ zzamk zzb;

    public zzlv(zzlx r1, zzamk r2, Continuation r3) {
        this.zza = r1;
        this.zzb = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzlv(this.zza, this.zzb, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzlv) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r3) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r3);
        zzlx r32 = this.zza;     // Catch: Exception -> L6
        zzlx.zzc(r32).zzb(this.zzb);     // Catch: Exception -> L6
        zzlx.zzb(r32, 0, 1, null);     // Catch: Exception -> L6
    L5:
        return Unit.INSTANCE;
    }
}
