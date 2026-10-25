package com.google.mlkit.genai.prompt.java;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.mlkit.genai.common.StreamingCallback;
import com.google.mlkit.genai.prompt.GenerateContentRequest;
import com.google.mlkit.genai.prompt.GenerativeModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzaf extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ CallbackToFutureAdapter.Completer zzc;
    final /* synthetic */ zzal zzd;
    final /* synthetic */ GenerateContentRequest zze;
    final /* synthetic */ StreamingCallback zzf;

    public zzaf(CallbackToFutureAdapter.Completer r1, zzal r2, GenerateContentRequest r3, StreamingCallback r4, Continuation r5) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        return new zzaf(this.zzc, this.zzd, this.zze, this.zzf, r8);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzaf) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zzb == 0) goto L9;
        CallbackToFutureAdapter.Completer r02 = (CallbackToFutureAdapter.Completer) this.zza;
        ResultKt.throwOnFailure(r6);     // Catch: Exception -> L7
    L13:
        r02.set(r6);     // Catch: Exception -> L7
    L18:
        return Unit.INSTANCE;
    L9:
        ResultKt.throwOnFailure(r6);
        CallbackToFutureAdapter.Completer r62 = this.zzc;     // Catch: Exception -> L7
        GenerativeModel r1 = zzal.zza(this.zzd);     // Catch: Exception -> L7
        GenerateContentRequest r2 = this.zze;     // Catch: Exception -> L7
        StreamingCallback r3 = this.zzf;     // Catch: Exception -> L7
        this.zza = r62;     // Catch: Exception -> L7
        this.zzb = 1;     // Catch: Exception -> L7
        Object r12 = r1.generateContent(r2, r3, this);     // Catch: Exception -> L7
        if (r12 == r0) goto L15;
        r02 = r62;
        r6 = r12;
        goto L13
    L15:
        return r0;
    L7:
        e = move-exception;
        this.zzc.setException(e);
        goto L18
    }
}
