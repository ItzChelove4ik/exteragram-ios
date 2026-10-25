package com.google.android.recaptcha.internal;

import java.util.Optional;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzge extends SuspendLambda implements Function2 {
    final /* synthetic */ Long zza;
    final /* synthetic */ zzgo zzb;
    final /* synthetic */ Optional zzc;

    public zzge(Long r1, zzgo r2, Optional r3, Continuation r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzge(this.zza, this.zzb, this.zzc, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzge) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r6);
        Long r62 = this.zza;     // Catch: Exception -> L17
        zzd r0 = null;
        if (r62 == null) goto L10;
        zzgo r1 = this.zzb;     // Catch: Exception -> L17
        Optional r2 = this.zzc;     // Catch: Exception -> L17
        long r3 = r62.longValue();     // Catch: Exception -> L17
        zzd r63 = zzgo.zzq(r1);     // Catch: Exception -> L17
        if (r63 != null) goto L8;
        r63 = null;
    L8:
        Object r64 = r63.zzd(r3, r2);     // Catch: Exception -> L17
        if (r64 == null) goto L10;
    L15:
        Object r5 = Result.m2018constructorimpl(r64);     // Catch: Exception -> L17
    L20:
        return Result.m2017boximpl(r5);
    L10:
        zzd r65 = zzgo.zzq(this.zzb);     // Catch: Exception -> L17
        if (r65 == null) goto L14;
        r0 = r65;
    L14:
        r64 = r0.zzc(this.zzc);     // Catch: Exception -> L17
    L17:
        e = move-exception;
        Result.Companion r66 = Result.Companion;
        r5 = Result.m2018constructorimpl(ResultKt.createFailure(e));
        goto L20
    }
}
