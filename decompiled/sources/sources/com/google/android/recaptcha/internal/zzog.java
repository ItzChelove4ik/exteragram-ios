package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzog extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzon zzb;
    private /* synthetic */ Object zzc;

    public zzog(zzon r1, Continuation r2) {
        this.zzb = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzog r0 = new zzog(this.zzb, r3);
        r0.zzc = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzog) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r4);
    L13:
        return (zzaka) r4;
    L6:
        zzmg r12 = (zzmg) this.zzc;
        ResultKt.throwOnFailure(r4);
    L9:
        this.zzc = null;
        this.zza = 2;
        r4 = ((zzmc) r4).zza(r12, this);
        if (r4 != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r4);
        r12 = (zzmg) this.zzc;
        zzng r42 = this.zzb.zzb();
        this.zzc = r12;
        this.zza = 1;
        r4 = zzoa.zzi(r42, this);
        if (r4 == r0) goto L14;
        goto L9
    }
}
