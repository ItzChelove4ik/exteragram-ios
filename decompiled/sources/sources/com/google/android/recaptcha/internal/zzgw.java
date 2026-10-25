package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* loaded from: classes5.dex */
final class zzgw extends SuspendLambda implements Function1 {
    int zza;
    final /* synthetic */ zzlp zzb;
    final /* synthetic */ zzgy zzc;

    public zzgw(zzlp r1, zzgy r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(1, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation r3) {
        return new zzgw(this.zzb, this.zzc, r3);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object r1) {
        return ((zzgw) create((Continuation) r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r5);
        if (r1 == 0) goto L5;
        return r5;
    L5:
        zzlp r52 = this.zzb;
        zzgv r2 = new zzgv(this.zzc, null);
        this.zza = 1;
        Object r4 = r52.zzd(54, null, r2, this);
        if (r4 != r0) goto L8;
        return r0;
    L8:
        return r4;
    }
}
