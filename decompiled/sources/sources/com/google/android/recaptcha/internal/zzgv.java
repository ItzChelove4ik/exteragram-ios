package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzgv extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzgy zzc;

    public zzgv(zzgy r1, Continuation r2) {
        this.zzc = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r1, Continuation r2) {
        return new zzgv(this.zzc, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgv) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L7;
        String r3 = (String) this.zza;
        ResultKt.throwOnFailure(r4);
        return r3;
    L7:
        ResultKt.throwOnFailure(r4);
    L10:
        String r42 = (String) r4;
        zzne r12 = this.zzc.zza();
        this.zza = r42;
        this.zzb = 2;
        if (r12.zzb("ovk", r42, this) == r0) goto L13;
        return r42;
    L13:
        return r0;
    L8:
        ResultKt.throwOnFailure(r4);
        zzmq r43 = this.zzc.zzf();
        this.zzb = 1;
        r4 = r43.zza(this);
        if (r4 == r0) goto L13;
        goto L10
    }
}
