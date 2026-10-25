package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutCancellationException;

/* loaded from: classes5.dex */
final class zzgn extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzgo zzc;
    private /* synthetic */ Object zzd;

    public zzgn(zzgo r1, Continuation r2) {
        this.zzc = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzgn r0 = new zzgn(this.zzc, r3);
        r0.zzd = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgn) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.zzb;
        if (r0 == 0) goto L19;
        if (r0 == 1) goto L12;
        if (r0 == 2) goto L11;
        if (r0 == 3) goto L10;
        if (r0 != 4) goto L9;
        zzmg r02 = (zzmg) this.zza;
        Exception r2 = (Exception) this.zzd;
        ResultKt.throwOnFailure(r10);
    L31:
        this.zzd = r2;
        this.zza = null;
        this.zzb = 5;
        if (((zzmc) r10).zza(r02, this) == r1) goto L48;
        Exception r9 = r2;
    L37:
        throw new zzhg(zzhf.zzb, zzhe.zzak, r9.getMessage(), null, 8, null);
    L48:
        return r1;
    L9:
        r9 = (Exception) this.zzd;
        ResultKt.throwOnFailure(r10);
        goto L37
    L10:
        zzhg r92 = (zzhg) this.zzd;
        ResultKt.throwOnFailure(r10);
        throw r92;
    L11:
        zzmg r03 = (zzmg) this.zza;
        zzhg r22 = (zzhg) this.zzd;
        ResultKt.throwOnFailure(r10);
        Object r23 = r10;
        zzhg r102 = r22;
    L46:
        this.zzd = r102;
        this.zza = null;
        this.zzb = 3;
        if (((zzmc) r23).zza(r03, this) == r1) goto L48;
    L50:
        throw r102;
    L12:
        zzmg r7 = (zzmg) this.zzd;
        ResultKt.throwOnFailure(r10);     // Catch: Exception -> L15 zzhg -> L17 TimeoutCancellationException -> L38
    L22:
        ResultKt.throwOnFailure(((Result) r10).m2027unboximpl());     // Catch: Exception -> L15 zzhg -> L17 TimeoutCancellationException -> L38
    L24:
        return Unit.INSTANCE;
    L19:
        ResultKt.throwOnFailure(r10);
        r7 = (zzmg) this.zzd;
        Deferred r103 = this.zzc.zzi();     // Catch: Exception -> L15 zzhg -> L17 TimeoutCancellationException -> L38
        this.zzd = r7;     // Catch: Exception -> L15 zzhg -> L17 TimeoutCancellationException -> L38
        this.zzb = 1;     // Catch: Exception -> L15 zzhg -> L17 TimeoutCancellationException -> L38
        r10 = r103.await(this);     // Catch: Exception -> L15 zzhg -> L17 TimeoutCancellationException -> L38
        if (r10 == r1) goto L48;
    L17:
        e = move-exception;
        r102 = e;
        r03 = r7;
        zzgo r24 = this.zzc;
        Job.cancel$default(r24.zzi(), null, 1, null);
        zzakg r5 = zzgo.zzx(r24);
        if (r5 == null) goto L50;
        this.zzd = r102;
        this.zza = r03;
        this.zzb = 2;
        r23 = zzgo.zzk(r24, r5, this);
        if (r23 != r1) goto L46;
    L38:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzar, e.getMessage(), null, 8, null);
    L15:
        e = move-exception;
        r02 = r7;
        zzgo r3 = this.zzc;
        Job.cancel$default(r3.zzi(), null, 1, null);
        zzakg r4 = zzgo.zzx(r3);
        if (r4 == null) goto L35;
        this.zzd = e;
        this.zza = r02;
        this.zzb = 4;
        Object r25 = zzgo.zzk(r3, r4, this);
        if (r25 == r1) goto L48;
        r2 = e;
        r10 = r25;
        goto L31
    L35:
        r9 = e;
        goto L37
    }
}
