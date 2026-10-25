package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.DownloadStatus;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

/* loaded from: classes4.dex */
final class zzzl extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzzy zzb;
    final /* synthetic */ CallbackToFutureAdapter.Completer zzc;
    final /* synthetic */ DownloadCallback zzd;

    public zzzl(zzzy r1, CallbackToFutureAdapter.Completer r2, DownloadCallback r3, Continuation r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzzl(this.zzb, this.zzc, this.zzd, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzzl) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L8;
        ResultKt.throwOnFailure(r4);     // Catch: Exception -> L6
    L12:
        this.zzc.set(null);     // Catch: Exception -> L6
    L16:
        return Unit.INSTANCE;
    L8:
        ResultKt.throwOnFailure(r4);
        Flow<DownloadStatus> r42 = this.zzb.download();     // Catch: Exception -> L6
        zzzk r1 = new zzzk(this.zzd);     // Catch: Exception -> L6
        this.zza = 1;     // Catch: Exception -> L6
        if (r42.collect(r1, this) != r0) goto L12;
        return r0;
    L6:
        e = move-exception;
        this.zzc.setException(e);
        goto L16
    }
}
