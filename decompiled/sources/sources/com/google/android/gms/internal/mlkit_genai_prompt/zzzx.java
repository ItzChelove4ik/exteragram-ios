package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes4.dex */
final class zzzx extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzzy zzb;
    final /* synthetic */ CallbackToFutureAdapter.Completer zzc;

    public zzzx(zzzy r1, CallbackToFutureAdapter.Completer r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzzx(this.zzb, this.zzc, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzzx) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r3) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L8;
        ResultKt.throwOnFailure(r3);     // Catch: Exception -> L6
    L12:
        this.zzc.set(null);     // Catch: Exception -> L6
    L16:
        return Unit.INSTANCE;
    L8:
        ResultKt.throwOnFailure(r3);
        zzzy r32 = this.zzb;     // Catch: Exception -> L6
        this.zza = 1;     // Catch: Exception -> L6
        if (r32.warmup(this) != r0) goto L12;
        return r0;
    L6:
        e = move-exception;
        this.zzc.setException(e);
        goto L16
    }
}
