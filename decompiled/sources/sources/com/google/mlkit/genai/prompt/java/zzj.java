package com.google.mlkit.genai.prompt.java;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.mlkit.genai.prompt.Caches;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzj extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ CallbackToFutureAdapter.Completer zzc;
    final /* synthetic */ zzm zzd;
    final /* synthetic */ String zze;

    public zzj(CallbackToFutureAdapter.Completer r1, zzm r2, String r3, Continuation r4) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzj(this.zzc, this.zzd, this.zze, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzj) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zzb == 0) goto L9;
        CallbackToFutureAdapter.Completer r02 = (CallbackToFutureAdapter.Completer) this.zza;
        ResultKt.throwOnFailure(r5);     // Catch: Exception -> L7
    L13:
        r02.set(r5);     // Catch: Exception -> L7
    L18:
        return Unit.INSTANCE;
    L9:
        ResultKt.throwOnFailure(r5);
        CallbackToFutureAdapter.Completer r52 = this.zzc;     // Catch: Exception -> L7
        Caches r1 = zzm.zza(this.zzd);     // Catch: Exception -> L7
        String r2 = this.zze;     // Catch: Exception -> L7
        this.zza = r52;     // Catch: Exception -> L7
        this.zzb = 1;     // Catch: Exception -> L7
        Object r12 = r1.delete(r2, this);     // Catch: Exception -> L7
        if (r12 == r0) goto L15;
        r02 = r52;
        r5 = r12;
        goto L13
    L15:
        return r0;
    L7:
        e = move-exception;
        this.zzc.setException(e);
        goto L18
    }
}
