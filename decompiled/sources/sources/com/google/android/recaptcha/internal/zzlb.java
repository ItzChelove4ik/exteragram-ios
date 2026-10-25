package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzlb extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzlf zzb;
    final /* synthetic */ zzamf zzc;

    public zzlb(zzlf r1, zzamf r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzlb(this.zzb, this.zzc, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzlb) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r5);
        if (r1 != 0) goto L9;
        zzlf r52 = this.zzb;
        zzmt r12 = zzlf.zzg(r52);
        String r53 = zzlf.zzh(r52).zza();
        zzamf r2 = this.zzc;
        this.zza = 1;
        r5 = r12.zza(r53, r2, this);
        if (r5 != r0) goto L9;
        return r0;
    L9:
        return (zzakg) r5;
    }
}
