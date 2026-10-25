package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzgr extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzgy zzb;
    private /* synthetic */ Object zzc;

    public zzgr(zzgy r1, Continuation r2) {
        this.zzb = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzgr r0 = new zzgr(this.zzb, r3);
        r0.zzc = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgr) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L9;
        if (r1 == 1) goto L8;
        if (r1 == 2) goto L7;
        ResultKt.throwOnFailure(r5);
    L18:
        return Unit.INSTANCE;
    L7:
        zzmg r12 = (zzmg) this.zzc;
        ResultKt.throwOnFailure(r5);
    L14:
        this.zzc = null;
        this.zza = 3;
        if (((zzmc) r5).zza(r12, this) != r0) goto L18;
    L19:
        return r0;
    L8:
        r12 = (zzmg) this.zzc;
        ResultKt.throwOnFailure(r5);
    L12:
        zzgy r52 = this.zzb;
        this.zzc = r12;
        this.zza = 2;
        r5 = r52.zzb(this);
        if (r5 == r0) goto L19;
    L9:
        ResultKt.throwOnFailure(r5);
        zzmg r53 = (zzmg) this.zzc;
        zzne r13 = this.zzb.zza();
        this.zzc = r53;
        this.zza = 1;
        if (r13.zza("ovk", this) == r0) goto L19;
        r12 = r53;
        goto L12
    }
}
