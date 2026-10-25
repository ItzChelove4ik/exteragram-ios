package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzkr extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzkw zzb;
    final /* synthetic */ zzala zzc;

    public zzkr(zzkw r1, zzala r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzkr(this.zzb, this.zzc, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzkr) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r5);
        if (r1 == 0) goto L5;
        return r5;
    L5:
        zzkq r2 = new zzkq(this.zzb, this.zzc, null);
        this.zza = 1;
        zzmb r4 = new zzmb(48, r2, null);
        if (r4 == r0) goto L8;
        return r4;
    L8:
        return r0;
    }
}
