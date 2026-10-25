package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzje extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzjj zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ RecaptchaAction zzd;
    private /* synthetic */ Object zze;

    public zzje(zzjj r1, long r2, RecaptchaAction r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzje r0 = new zzje(this.zzb, this.zzc, this.zzd, r8);
        r0.zze = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzje) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r11);     // Catch: Throwable -> L19 zzhg -> L22
    L16:
        Object r10 = Result.m2018constructorimpl(r11);     // Catch: Throwable -> L19 zzhg -> L22
    L25:
        return Result.m2017boximpl(r10);
    L7:
        zzmg r12 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r11);     // Catch: Throwable -> L19 zzhg -> L22
    L13:
        this.zze = null;     // Catch: Throwable -> L19 zzhg -> L22
        this.zza = 2;     // Catch: Throwable -> L19 zzhg -> L22
        r11 = ((zzmb) r11).zza(r12, this);     // Catch: Throwable -> L19 zzhg -> L22
        if (r11 != r0) goto L16;
    L18:
        return r0;
    L10:
        ResultKt.throwOnFailure(r11);
        r12 = (zzmg) this.zze;
        Result.Companion r112 = Result.Companion;     // Catch: Throwable -> L19 zzhg -> L22
        zzjj r5 = this.zzb;     // Catch: Throwable -> L19 zzhg -> L22
        long r6 = this.zzc;     // Catch: Throwable -> L19 zzhg -> L22
        RecaptchaAction r8 = this.zzd;     // Catch: Throwable -> L19 zzhg -> L22
        this.zze = r12;     // Catch: Throwable -> L19 zzhg -> L22
        this.zza = 1;     // Catch: Throwable -> L19 zzhg -> L22
        r11 = new zzmb(9, new zzji(r5, r6, r8, null), null);     // Catch: Throwable -> L19 zzhg -> L22
        if (r11 == r0) goto L18;
    L22:
        e = move-exception;
        Result.Companion r113 = Result.Companion;
        r10 = Result.m2018constructorimpl(ResultKt.createFailure(e.zzd()));
    L19:
        th = move-exception;
        throw th;
    }
}
