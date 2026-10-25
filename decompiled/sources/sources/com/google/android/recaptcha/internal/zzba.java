package com.google.android.recaptcha.internal;

import android.view.MotionEvent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzba extends SuspendLambda implements Function2 {
    final /* synthetic */ zzbb zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ MotionEvent zzc;

    public zzba(zzbb r1, String r2, MotionEvent r3, Continuation r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzba(this.zza, this.zzb, this.zzc, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzba) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r3) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r3);
        zzbb r32 = this.zza;     // Catch: Throwable -> L9
        if (r32.zzb() == false) goto L11;
        zzay r33 = r32.zza();     // Catch: Throwable -> L9
        if (r33 == null) goto L11;
        r33.zza(this.zzb, this.zzc);     // Catch: Throwable -> L9
    L11:
        this.zzc.recycle();
        return Unit.INSTANCE;
    L9:
        th = move-exception;
        this.zzc.recycle();
        throw th;
    }
}
