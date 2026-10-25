package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.common.DownloadStatus;
import com.google.mlkit.genai.common.GenAiException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProducerScope;

/* loaded from: classes4.dex */
final class zzyn extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ Function2 zzb;
    final /* synthetic */ zzym zzc;
    final /* synthetic */ ProducerScope zzd;

    public zzyn(Function2 r1, zzym r2, ProducerScope r3, Continuation r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzyn(this.zzb, this.zzc, this.zzd, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzyn) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v9 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L10;
        ResultKt.throwOnFailure(r4);     // Catch: Throwable -> L6 GenAiException -> L8
        zzyn this = this;
    L21:
    L20:
        return Unit.INSTANCE;
    L10:
        ResultKt.throwOnFailure(r4);
        Function2 r42 = this.zzb;     // Catch: Throwable -> L6 GenAiException -> L8
        zzym r1 = this.zzc;     // Catch: Throwable -> L6 GenAiException -> L8
        this.zza = 1;     // Catch: Throwable -> L6 GenAiException -> L8
        Object r3 = r42.invoke(r1, this);     // Catch: Throwable -> L6 GenAiException -> L8
        this = r3;
        if (r3 != r0) goto L21;
        return r0;
    L8:
        e = move-exception;
        ProducerScope r32 = this.zzd;
        r32.mo3532trySendJP2dKIU(new DownloadStatus.DownloadFailed(e));
        r32.close(e);
    L6:
        th = move-exception;
        String r12 = th.getMessage();
        if (r12 != null) goto L17;
        r12 = "Unknown error";
    L17:
        GenAiException r02 = new GenAiException(r12, th, 0);
        ProducerScope r33 = this.zzd;
        r33.mo3532trySendJP2dKIU(new DownloadStatus.DownloadFailed(r02));
        r33.close(r02);
        goto L20
    }
}
