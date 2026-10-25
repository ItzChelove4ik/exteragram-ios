package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzdr extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzdv zzb;
    final /* synthetic */ zzalg zzc;

    public zzdr(zzdv r1, zzalg r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzdr(this.zzb, this.zzc, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdr) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r5);
        if (r1 == 0) goto L5;
        return r5;
    L5:
        zzdq r2 = new zzdq(this.zzb, this.zzc, null);
        this.zza = 1;
        zzmb r4 = new zzmb(64, r2, null);
        if (r4 == r0) goto L8;
        return r4;
    L8:
        return r0;
    }
}
