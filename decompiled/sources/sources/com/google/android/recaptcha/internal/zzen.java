package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;

/* loaded from: classes5.dex */
final class zzen extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzakg zzb;
    final /* synthetic */ zzeo zzc;
    private /* synthetic */ Object zzd;

    public zzen(zzakg r1, zzeo r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzen r0 = new zzen(this.zzb, this.zzc, r4);
        r0.zzd = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzen) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r6);
        if (r1 == 0) goto L5;
        return r6;
    L5:
        zzmg r62 = (zzmg) this.zzd;
        zzem r3 = new zzem(this.zzb, this.zzc, r62, null);
        this.zza = 1;
        Object r5 = CoroutineScopeKt.coroutineScope(r3, this);
        if (r5 != r0) goto L8;
        return r0;
    L8:
        return r5;
    }
}
