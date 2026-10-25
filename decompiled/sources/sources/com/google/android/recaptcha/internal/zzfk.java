package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzfk extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzfp zzc;
    final /* synthetic */ zzmg zzd;

    public zzfk(zzfp r1, zzmg r2, Continuation r3) {
        this.zzc = r1;
        this.zzd = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzfk(this.zzc, this.zzd, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzfk) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r4);
    L13:
        return Unit.INSTANCE;
    L6:
        zzmg r12 = (zzmg) this.zza;
        ResultKt.throwOnFailure(r4);
    L9:
        this.zza = null;
        this.zzb = 2;
        if (((zzmc) r4).zza(r12, this) != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r4);
        zzfp r42 = this.zzc;
        zzfp.zzf(r42, zzfq.zza);
        r12 = this.zzd;
        this.zza = r12;
        this.zzb = 1;
        r4 = r42.zzc(this);
        if (r4 == r0) goto L14;
        goto L9
    }
}
