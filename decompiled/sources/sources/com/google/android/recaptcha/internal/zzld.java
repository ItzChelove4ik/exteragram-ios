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
final class zzld extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzmg zzd;
    final /* synthetic */ zzlf zze;
    final /* synthetic */ zzamf zzf;
    final /* synthetic */ long zzg;

    public zzld(zzmg r1, zzlf r2, zzamf r3, long r4, Continuation r6) {
        this.zzd = r1;
        this.zze = r2;
        this.zzf = r3;
        this.zzg = r4;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        return new zzld(this.zzd, this.zze, this.zzf, this.zzg, r9);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzld) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzc;
        if (r1 == 0) goto L17;
        if (r1 == 1) goto L14;
        if (r1 == 2) goto L12;
        if (r1 == 3) goto L10;
        zzakg r13 = (zzakg) this.zza;
        ResultKt.throwOnFailure(r14);     // Catch: zzhg -> L31
    L28:
        Object r132 = Result.m2018constructorimpl(r13);     // Catch: zzhg -> L31
    L34:
        return Result.m2017boximpl(r132);
    L10:
        zzmg r12 = (zzmg) this.zzb;
        zzakg r2 = (zzakg) this.zza;
        ResultKt.throwOnFailure(r14);     // Catch: zzhg -> L31
        Object r22 = r14;
        zzakg r142 = r2;
    L25:
        this.zza = r142;     // Catch: zzhg -> L31
        this.zzb = null;     // Catch: zzhg -> L31
        this.zzc = 4;     // Catch: zzhg -> L31
        if (((zzmc) r22).zza(r12, this) == r0) goto L30;
        r13 = r142;
    L30:
        return r0;
    L12:
        ResultKt.throwOnFailure(r14);     // Catch: zzhg -> L31
    L22:
        r142 = (zzakg) r14;     // Catch: zzhg -> L31
        r12 = this.zzd;     // Catch: zzhg -> L31
        zzon r3 = zzlf.zzb(this.zze);     // Catch: zzhg -> L31
        this.zza = r142;     // Catch: zzhg -> L31
        this.zzb = r12;     // Catch: zzhg -> L31
        this.zzc = 3;     // Catch: zzhg -> L31
        r22 = r3.zzd(r142, this);     // Catch: zzhg -> L31
        if (r22 != r0) goto L25;
    L14:
        zzmg r15 = (zzmg) this.zza;
        ResultKt.throwOnFailure(r14);     // Catch: zzhg -> L31
    L20:
        this.zza = null;     // Catch: zzhg -> L31
        this.zzc = 2;     // Catch: zzhg -> L31
        r14 = ((zzmb) r14).zza(r15, this);     // Catch: zzhg -> L31
        if (r14 == r0) goto L30;
    L17:
        ResultKt.throwOnFailure(r14);
        r15 = this.zzd;     // Catch: zzhg -> L31
        zzlf r9 = this.zze;     // Catch: zzhg -> L31
        zzamf r10 = this.zzf;     // Catch: zzhg -> L31
        long r7 = this.zzg;     // Catch: zzhg -> L31
        this.zza = r15;     // Catch: zzhg -> L31
        this.zzc = 1;     // Catch: zzhg -> L31
        r14 = new zzmb(55, new zzkz(r7, r9, r10, null), null);     // Catch: zzhg -> L31
        if (r14 == r0) goto L30;
    L31:
        e = move-exception;
        Result.Companion r143 = Result.Companion;
        r132 = Result.m2018constructorimpl(ResultKt.createFailure(e));
        goto L34
    }
}
