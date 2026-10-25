package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzgj extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzmg zzb;
    final /* synthetic */ zzgo zzc;
    final /* synthetic */ zzakg zzd;

    public zzgj(zzmg r1, zzgo r2, zzakg r3, Continuation r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzgj(this.zzb, this.zzc, this.zzd, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgj) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L6;
        ResultKt.throwOnFailure(r6);     // Catch: Exception -> L12
    L10:
        Object r5 = ((Result) r6).m2027unboximpl();     // Catch: Exception -> L12
    L15:
        return Result.m2017boximpl(r5);
    L6:
        ResultKt.throwOnFailure(r6);
        zzmg r62 = this.zzb;     // Catch: Exception -> L12
        zzgi r1 = new zzgi(this.zzc, this.zzd, r62, null);     // Catch: Exception -> L12
        this.zza = 1;     // Catch: Exception -> L12
        r6 = r62.zze(76, null, r1, this);     // Catch: Exception -> L12
        if (r6 != r0) goto L10;
        return r0;
    L12:
        e = move-exception;
        Result.Companion r63 = Result.Companion;
        r5 = Result.m2018constructorimpl(ResultKt.createFailure(e));
        goto L15
    }
}
