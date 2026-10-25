package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzoi extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzon zzb;
    private /* synthetic */ Object zzc;

    public zzoi(zzon r1, Continuation r2) {
        this.zzb = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzoi r0 = new zzoi(this.zzb, r3);
        r0.zzc = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzoi) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r6);
        return r6;
    L7:
        zzmg r12 = (zzmg) this.zzc;
        ResultKt.throwOnFailure(r6);
    L10:
        this.zzc = null;
        this.zza = 2;
        Object r5 = ((zzmb) r6).zza(r12, this);
        if (r5 == r0) goto L14;
        return r5;
    L14:
        return r0;
    L8:
        ResultKt.throwOnFailure(r6);
        r12 = (zzmg) this.zzc;
        zzoh r4 = new zzoh(this.zzb, r12, null);
        this.zzc = r12;
        this.zza = 1;
        r6 = new zzmb(75, r4, null);
        if (r6 == r0) goto L14;
        goto L10
    }
}
