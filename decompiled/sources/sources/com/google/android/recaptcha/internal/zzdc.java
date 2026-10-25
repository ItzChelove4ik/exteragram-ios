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
final class zzdc extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzlp zzc;
    final /* synthetic */ zzdo zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ zzaka zzf;

    public zzdc(zzlp r1, zzdo r2, String r3, zzaka r4, Continuation r5) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        return new zzdc(this.zzc, this.zzd, this.zze, this.zzf, r8);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdc) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r6);     // Catch: Exception -> L19
    L17:
        Object r5 = Result.m2018constructorimpl(r6);     // Catch: Exception -> L19
    L22:
        return Result.m2017boximpl(r5);
    L7:
        zzlp r12 = (zzlp) this.zza;
        ResultKt.throwOnFailure(r6);     // Catch: Exception -> L19
    L14:
        this.zza = null;     // Catch: Exception -> L19
        this.zzb = 2;     // Catch: Exception -> L19
        r6 = ((zzmb) r6).zza(r12.zza(), this);     // Catch: Exception -> L19
        if (r6 != r0) goto L17;
    L16:
        return r0;
    L10:
        ResultKt.throwOnFailure(r6);
        Result.Companion r62 = Result.Companion;     // Catch: Exception -> L19
        r12 = this.zzc;     // Catch: Exception -> L19
        zzdo r63 = this.zzd;     // Catch: Exception -> L19
        String r3 = this.zze;     // Catch: Exception -> L19
        zzaka r4 = this.zzf;     // Catch: Exception -> L19
        this.zza = r12;     // Catch: Exception -> L19
        this.zzb = 1;     // Catch: Exception -> L19
        r6 = zzdo.zzf(r63, r3, r4, this);     // Catch: Exception -> L19
        if (r6 != r0) goto L14;
    L19:
        e = move-exception;
        Result.Companion r64 = Result.Companion;
        r5 = Result.m2018constructorimpl(ResultKt.createFailure(e));
        goto L22
    }
}
