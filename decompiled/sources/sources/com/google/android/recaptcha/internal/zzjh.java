package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzjh extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzjj zzb;
    final /* synthetic */ RecaptchaAction zzc;
    final /* synthetic */ long zzd;

    public zzjh(zzjj r1, RecaptchaAction r2, long r3, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        return new zzjh(this.zzb, this.zzc, this.zzd, r8);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzjh) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r6);
        if (r1 == 0) goto L5;
        Object r5 = ((Result) r6).m2027unboximpl();
    L8:
        ResultKt.throwOnFailure(r5);
        return r5;
    L5:
        zzjj r62 = this.zzb;
        RecaptchaAction r12 = this.zzc;
        long r2 = this.zzd;
        this.zza = 1;
        r5 = zzjj.zzc(r62, r12, r2, this);
        if (r5 != r0) goto L8;
        return r0;
    }
}
