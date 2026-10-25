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
final class zzcq extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzmg zzc;
    final /* synthetic */ zzcl zzd;
    final /* synthetic */ long zze;
    final /* synthetic */ zzakg zzf;

    public zzcq(zzmg r1, zzcl r2, long r3, zzakg r5, Continuation r6) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        return new zzcq(this.zzc, this.zzd, this.zze, this.zzf, r9);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzcq) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r11);     // Catch: zzhg -> L20
    L17:
        Result.Companion r10 = Result.Companion;     // Catch: zzhg -> L20
        Object r102 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: zzhg -> L20
    L23:
        return Result.m2017boximpl(r102);
    L7:
        zzmg r12 = (zzmg) this.zza;
        ResultKt.throwOnFailure(r11);     // Catch: zzhg -> L20
    L14:
        this.zza = null;     // Catch: zzhg -> L20
        this.zzb = 2;     // Catch: zzhg -> L20
        if (((zzmb) r11).zza(r12, this) != r0) goto L17;
    L19:
        return r0;
    L10:
        ResultKt.throwOnFailure(r11);
        r12 = this.zzc;     // Catch: zzhg -> L20
        zzcl r5 = this.zzd;     // Catch: zzhg -> L20
        long r6 = this.zze;     // Catch: zzhg -> L20
        zzakg r8 = this.zzf;     // Catch: zzhg -> L20
        this.zza = r12;     // Catch: zzhg -> L20
        this.zzb = 1;     // Catch: zzhg -> L20
        zzmb r3 = new zzmb(r5.zzf(), new zzci(r5, r6, r8, null), null);     // Catch: zzhg -> L20
        if (r3 == r0) goto L19;
        r11 = r3;
    L20:
        e = move-exception;
        Result.Companion r112 = Result.Companion;
        r102 = Result.m2018constructorimpl(ResultKt.createFailure(e));
        goto L23
    }
}
