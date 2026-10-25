package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzok extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzon zzb;
    final /* synthetic */ zzakg zzc;
    private /* synthetic */ Object zzd;

    public zzok(zzon r1, zzakg r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzok r0 = new zzok(this.zzb, this.zzc, r4);
        r0.zzd = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzok) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r7);
    L13:
        return Unit.INSTANCE;
    L6:
        zzmg r12 = (zzmg) this.zzd;
        ResultKt.throwOnFailure(r7);
    L9:
        this.zzd = null;
        this.zza = 2;
        if (((zzmb) r7).zza(r12, this) != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r7);
        r12 = (zzmg) this.zzd;
        zzoj r5 = new zzoj(this.zzb, this.zzc, r12, null);
        this.zzd = r12;
        this.zza = 1;
        r7 = new zzmb(74, r5, null);
        if (r7 == r0) goto L14;
        goto L9
    }
}
