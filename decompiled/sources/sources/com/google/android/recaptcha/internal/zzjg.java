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
import kotlinx.coroutines.sync.Mutex;

/* loaded from: classes5.dex */
final class zzjg extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    long zzd;
    int zze;
    final /* synthetic */ zzjj zzf;
    final /* synthetic */ long zzg;
    final /* synthetic */ RecaptchaAction zzh;

    public zzjg(zzjj r1, long r2, RecaptchaAction r4, Continuation r5) {
        this.zzf = r1;
        this.zzg = r2;
        this.zzh = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        return new zzjg(this.zzf, this.zzg, this.zzh, r8);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzjg) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zze;
        if (r1 == 0) goto L12;
        if (r1 == 1) goto L10;
        Mutex r11 = (Mutex) this.zza;
        ResultKt.throwOnFailure(r12);     // Catch: Throwable -> L8
    L18:
        Result r122 = Result.m2017boximpl(((Result) r12).m2027unboximpl());     // Catch: Throwable -> L8
        r11.unlock(null);
        return r122;
    L8:
        th = move-exception;
        Throwable r123 = th;
    L23:
        r11.unlock(null);
        throw r123;
    L10:
        long r13 = this.zzd;
        RecaptchaAction r4 = (RecaptchaAction) this.zzc;
        zzjj r5 = (zzjj) this.zzb;
        Mutex r6 = (Mutex) this.zza;
        ResultKt.throwOnFailure(r12);
        Mutex r124 = r6;
        long r62 = r13;
    L11:
        RecaptchaAction r8 = r4;
        zzmd r14 = zzjj.zze(r5);     // Catch: Throwable -> L21
        zzjf r42 = new zzjf(r5, r62, r8, null);     // Catch: Throwable -> L21
        this.zza = r124;     // Catch: Throwable -> L21
        this.zzb = null;     // Catch: Throwable -> L21
        this.zzc = null;     // Catch: Throwable -> L21
        this.zze = 2;     // Catch: Throwable -> L21
        Object r112 = r42.invoke(r14, this);     // Catch: Throwable -> L21
        if (r112 == r0) goto L25;
        Mutex r10 = r124;
        r12 = r112;
        r11 = r10;
    L25:
        return r0;
    L21:
        th = move-exception;
        Mutex r102 = r124;
        r123 = th;
        r11 = r102;
        goto L23
    L12:
        ResultKt.throwOnFailure(r12);
        r5 = this.zzf;
        r62 = this.zzg;
        r4 = this.zzh;
        r124 = zzjj.zzd(r5);
        this.zza = r124;
        this.zzb = r5;
        this.zzc = r4;
        this.zzd = r62;
        this.zze = 1;
        if (r124.lock(null, this) == r0) goto L25;
        goto L25
    }
}
