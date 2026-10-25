package com.google.mlkit.genai.prompt.java;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.mlkit.genai.prompt.GenerativeModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzai extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ CallbackToFutureAdapter.Completer zzc;
    final /* synthetic */ zzal zzd;

    public zzai(CallbackToFutureAdapter.Completer r1, zzal r2, Continuation r3) {
        this.zzc = r1;
        this.zzd = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzai(this.zzc, this.zzd, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzai) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zzb == 0) goto L9;
        CallbackToFutureAdapter.Completer r02 = (CallbackToFutureAdapter.Completer) this.zza;
        ResultKt.throwOnFailure(r4);     // Catch: Exception -> L7
    L13:
        r02.set(r4);     // Catch: Exception -> L7
    L18:
        return Unit.INSTANCE;
    L9:
        ResultKt.throwOnFailure(r4);
        CallbackToFutureAdapter.Completer r42 = this.zzc;     // Catch: Exception -> L7
        GenerativeModel r1 = zzal.zza(this.zzd);     // Catch: Exception -> L7
        this.zza = r42;     // Catch: Exception -> L7
        this.zzb = 1;     // Catch: Exception -> L7
        Object r12 = r1.getBaseModelName(this);     // Catch: Exception -> L7
        if (r12 == r0) goto L15;
        r02 = r42;
        r4 = r12;
        goto L13
    L15:
        return r0;
    L7:
        e = move-exception;
        this.zzc.setException(e);
        goto L18
    }
}
