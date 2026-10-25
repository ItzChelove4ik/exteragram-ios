package com.google.android.recaptcha.internal;

import java.util.Timer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzfm extends SuspendLambda implements Function2 {
    final /* synthetic */ zzfp zza;
    private /* synthetic */ Object zzb;

    public zzfm(zzfp r1, Continuation r2) {
        this.zza = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzfm r0 = new zzfm(this.zza, r3);
        r0.zzb = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzfm) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r8);
        zzmg r82 = (zzmg) this.zzb;
        zzfp r7 = this.zza;
        if (zzfp.zzh(r7) == true) goto L6;
        new Timer().schedule(new zzfl(r7, r82), 28800000, 28800000);
        zzfp.zzi(r7, true);
    L6:
        return Unit.INSTANCE;
    }
}
