package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzet extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzev zzb;
    final /* synthetic */ zzakk zzc;
    private /* synthetic */ Object zzd;

    public zzet(zzev r1, zzakk r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzet r0 = new zzet(this.zzb, this.zzc, r4);
        r0.zzd = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzet) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r5);
    L13:
        return Unit.INSTANCE;
    L6:
        zzlp r12 = (zzlp) this.zzd;
        ResultKt.throwOnFailure(r5);
    L9:
        this.zzd = null;
        this.zza = 2;
        if (((zzmc) r5).zza(r12.zza(), this) != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r5);
        r12 = (zzlp) this.zzd;
        zzev r52 = this.zzb;
        zzakk r3 = this.zzc;
        this.zzd = r12;
        this.zza = 1;
        r5 = r52.zzk(r3, this);
        if (r5 == r0) goto L14;
        goto L9
    }
}
