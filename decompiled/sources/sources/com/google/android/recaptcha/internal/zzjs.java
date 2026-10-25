package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes5.dex */
final class zzjs extends SuspendLambda implements Function2 {
    final /* synthetic */ zzjj zza;
    final /* synthetic */ String zzb;

    public zzjs(zzjj r1, String r2, Continuation r3) {
        this.zza = r1;
        this.zzb = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzjs(this.zza, this.zzb, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzjs) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r8);
        zzjj r82 = this.zza;
        if (Intrinsics.areEqual(r82.zzb(), this.zzb) == false) goto L6;
        return r82;
    L6:
        throw new zzhg(zzhf.zzd, zzhe.zzx, null, null, 12, null);
    }
}
