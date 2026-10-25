package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.common.DownloadCallback;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes4.dex */
final class zzxz extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzyd zzb;
    final /* synthetic */ zzaur zzc;
    final /* synthetic */ DownloadCallback zzd;
    final /* synthetic */ CompletableDeferred zze;
    final /* synthetic */ AtomicInteger zzf;
    final /* synthetic */ int zzg;

    public zzxz(zzyd r1, zzaur r2, DownloadCallback r3, CompletableDeferred r4, AtomicInteger r5, int r6, Continuation r7) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        this.zzf = r5;
        this.zzg = r6;
        super(2, r7);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r9, Continuation r10) {
        return new zzxz(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, r10);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzxz) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r10);
        if (r1 != 0) goto L9;
        zzyd r2 = this.zzb;
        zzaur r3 = this.zzc;
        DownloadCallback r4 = this.zzd;
        CompletableDeferred r6 = this.zze;
        zzxy r7 = new zzxy(this.zzf, this.zzg, r4);
        this.zza = 1;
        if (zzyd.zzh(r2, r3, r4, true, r6, r7, this) != r0) goto L9;
        return r0;
    L9:
        return Unit.INSTANCE;
    }
}
