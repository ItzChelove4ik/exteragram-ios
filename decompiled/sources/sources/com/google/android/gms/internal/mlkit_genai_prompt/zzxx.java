package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.common.DownloadCallback;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes4.dex */
final class zzxx extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzyd zzb;
    final /* synthetic */ zzaur zzc;
    final /* synthetic */ DownloadCallback zzd;
    final /* synthetic */ AtomicInteger zze;
    final /* synthetic */ int zzf;

    public zzxx(zzyd r1, zzaur r2, DownloadCallback r3, AtomicInteger r4, int r5, Continuation r6) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        this.zzf = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        return new zzxx(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, r9);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzxx) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r12);
        if (r1 != 0) goto L9;
        zzyd r2 = this.zzb;
        zzaur r3 = this.zzc;
        DownloadCallback r4 = this.zzd;
        zzxw r7 = new zzxw(this.zze, this.zzf, r4);
        this.zza = 1;
        if (zzyd.zzc(r2, r3, r4, false, null, r7, this, 8, null) != r0) goto L9;
        return r0;
    L9:
        return Unit.INSTANCE;
    }
}
