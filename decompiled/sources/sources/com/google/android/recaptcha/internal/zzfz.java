package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzfz extends SuspendLambda implements Function2 {
    final /* synthetic */ zzakg zza;

    public zzfz(zzakg r1, Continuation r2) {
        this.zza = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r1, Continuation r2) {
        return new zzfz(this.zza, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzfz) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r8);
        zzakg r7 = this.zza;
        zzaer r82 = r7.zzh().zza();
        zzaer r72 = r7.zzh().zzb();
        if (r82.zzr() == true) goto L7;
        zzall r0 = zzalm.zzc();
        r0.zza(r82);
        r0.zzb(r72);
        return r0.zzp();
    L7:
        throw new zzhg(zzhf.zzb, zzhe.zzah, null, null, 12, null);
    }
}
