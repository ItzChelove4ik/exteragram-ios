package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;

/* loaded from: classes5.dex */
final class zziu extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ zziv zzc;
    final /* synthetic */ zzdp zzd;
    final /* synthetic */ zzakg zze;
    private /* synthetic */ Object zzf;

    public zziu(long r1, zziv r3, zzdp r4, zzakg r5, Continuation r6) {
        this.zzb = r1;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        zziu r0 = new zziu(this.zzb, this.zzc, this.zzd, this.zze, r9);
        r0.zzf = r8;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zziu) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L6;
        ResultKt.throwOnFailure(r10);     // Catch: Exception -> L12 TimeoutCancellationException -> L15 zzhg -> L18
    L10:
        return Unit.INSTANCE;
    L6:
        ResultKt.throwOnFailure(r10);
        zzmg r2 = (zzmg) this.zzf;
        long r6 = this.zzb;     // Catch: Exception -> L12 TimeoutCancellationException -> L15 zzhg -> L18
        zzit r1 = new zzit(r2, this.zzc, this.zzd, this.zze, r6, null);     // Catch: Exception -> L12 TimeoutCancellationException -> L15 zzhg -> L18
        this.zza = 1;     // Catch: Exception -> L12 TimeoutCancellationException -> L15 zzhg -> L18
        if (TimeoutKt.withTimeout(r6, r1, this) != r0) goto L10;
        return r0;
    L18:
        e = move-exception;
        throw e;
    L15:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzb, e.getMessage(), null, 8, null);
    L12:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzz, e.getMessage(), null, 8, null);
    }
}
